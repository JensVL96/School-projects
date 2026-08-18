package tdt4250.ra.servlet;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.osgi.service.http.whiteboard.propertytypes.HttpWhiteboardServletName;
import org.osgi.service.http.whiteboard.propertytypes.HttpWhiteboardServletPattern;
import org.osgi.service.log.Logger;
import org.osgi.service.log.LoggerFactory;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.ValueNode;

import tdt4250.servletsupport.IPostHandler;
import tdt4250.servletsupport.IRequestPathResolver;
import tdt4250.servletsupport.IRequestQueryExecutor;
import tdt4250.servletsupport.IResourceProvider;
import tdt4250.servletsupport.IResponseSerializer;

@SuppressWarnings("serial")
@Component
@HttpWhiteboardServletName("data")
@HttpWhiteboardServletPattern("/data/*")
public class ResourceServlet extends HttpServlet implements Servlet {

	@Reference(policy=ReferencePolicy.DYNAMIC)
	protected volatile Collection<IResourceProvider> resourceProviders;
	
	protected Optional<IResourceProvider> getResourceProvider(final String name) {
		return resourceProviders.stream().filter(rp -> rp.getName().equals(name)).findFirst();
	}

	@Reference(policy=ReferencePolicy.DYNAMIC)
	protected volatile IRequestPathResolver requestPathResolver;
	
	@Reference(policy=ReferencePolicy.DYNAMIC)
	protected volatile IRequestQueryExecutor requestQueryExecutor;
	
	@Reference(policy=ReferencePolicy.DYNAMIC)
	protected volatile IResponseSerializer responseSerializer;

	@Reference(policy=ReferencePolicy.DYNAMIC)
	private volatile LoggerFactory loggerFactory;
	
	protected LoggerFactory getLoggerFactory() {
		return loggerFactory;
	}

	protected void doHelper(HttpServletRequest req, RequestData requestData, Writer responseWriter) throws Exception {
		Object result = getPathObject(requestData.resourceProvider, requestData.resourcePath, requestData.op, requestData.params);
		Object postBody = requestData.params.get("httpPostBody");
		if (postBody != null) {
			String contentType = req.getHeader("Content-Type");
			if (contentType == null) {
				throw new ServletException("No Content-Type header");
			}
			int pos = contentType.indexOf(';');
			if (pos > 0) {
				contentType = contentType.substring(0, pos);
			}
			IPostHandler postHandler = getPostHandler(contentType);
			if (postHandler == null) {
				throw new ServletException("No handler for " + contentType);
			}
			try {
				result = postHandler.handlePostBody(result, String.valueOf(postBody), requestData.params);
			} catch (Exception e) {
				throw new ServletException("Exception when handling post body for " + contentType);
			}
		}
		responseSerializer.serialize(result, responseWriter);
	}

	protected IPostHandler getPostHandler(String contentType) {
		BundleContext bundleContext = FrameworkUtil.getBundle(getClass()).getBundleContext();
		Collection<ServiceReference<IPostHandler>> serviceReferences = Collections.emptyList();
		try {
			serviceReferences = bundleContext.getServiceReferences(IPostHandler.class, "(postHandlerMimeType=" + contentType + ")");
		} catch (InvalidSyntaxException e) {
		}
		for (ServiceReference<IPostHandler> serviceReference : serviceReferences) {
			IPostHandler postHandler = bundleContext.getService(serviceReference);
			if (postHandler != null) {
				return postHandler;
			}
		}
		return null;
	}

	protected Logger logger;

	public Logger getLogger() {
		LoggerFactory loggerFactory = getLoggerFactory();
		if (logger == null && loggerFactory != null) {
			logger = loggerFactory.getLogger(this.getClass());
		} else if (loggerFactory == null) {
			logger = null;
		}
		return logger;
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Map<String, Object> params = decodeQuery(req, new HashMap<String, Object>());
		doHelper(req, resp, params);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Map<String, Object> params = decodeQuery(req, new HashMap<String, Object>());
		handlePostBody(req, params, resp);
	}

	protected void handlePostBody(HttpServletRequest req, Map<String, Object> params, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String body = getPostBody(req, params);
			handlePostBody(body, params);
			doHelper(req, resp, params);
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}

	protected void handlePostBody(String body, Map<String, Object> params) {
		params.put("httpPostBody", body);
		decodePostBody(body, params);
	}

	protected static class RequestData {
		IResourceProvider resourceProvider;
		List<String> resourcePath;
		String op;
		Map<String, Object> params;
	}

	protected RequestData getRequestData(HttpServletRequest req, HttpServletResponse resp, Map<String, Object> params) throws ServletException, IOException {
		String path = req.getPathInfo();
		List<String> segments = (path != null && path.length() > 0 ? Arrays.asList(path.substring(1).split("/")) : null);
		if (segments == null || segments.isEmpty()) {
			throw new ServletException("Path must at least have a resource identifier");
		}
		String resourceProviderName = segments.get(0);
		Optional<IResourceProvider> resourceProvider = getResourceProvider(resourceProviderName);
		if (! resourceProvider.isPresent()) {
			if (getLogger() != null) {
				getLogger().info("No resource provider for " + resourceProviderName);				
			}
			throw new ServletException("The resource identifier " + resourceProviderName + " is not recognized");
		}
		List<String> resourcePath = segments.subList(1, segments.size());
		String op = null;
		if (segments.size() > 1 && requestQueryExecutor != null) {
			op = segments.get(segments.size() - 1);
			resourcePath = segments.subList(1, segments.size() - 1);
		}
		if (getLogger() != null) {
			getLogger().info("Handling " + resourcePath + " + " + op + " with " + resourceProvider);
		}
		try {
			RequestData requestData = new RequestData();
			requestData.resourceProvider = resourceProvider.get();
			requestData.resourcePath = resourcePath;
			requestData.op = op;
			requestData.params = params;
			return requestData;
		} catch (Exception e) {
			String message = "Exception during request handling: " + e.getMessage();
			if (getLogger() != null) {
				getLogger().warn(message);
			}
			throw new ServletException(e);
		}
	}

	protected void doHelper(HttpServletRequest req, HttpServletResponse resp, Map<String, Object> params) throws ServletException, IOException {
		RequestData requestData = getRequestData(req, resp, params);
		if (requestData != null) {
			try {
				ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
				Writer writer = new OutputStreamWriter(outputStream);
				doHelper(req, requestData, writer);
				String responseString = new String(outputStream.toByteArray(), StandardCharsets.UTF_8);
				resp.getWriter().write(responseString);
			} catch (ServletException e) {
				throw e;
			} catch (Exception e) {
				throw new ServletException(e);
			}
		}
	}

	protected Object getPathObject(IResourceProvider resourceProvider, Collection<String> resourcePath, String op, Map<String, Object> params) throws Exception {
		Object object = requestPathResolver.getObjectForPath(resourceProvider.getRootObjects(), resourcePath.toArray(new String[0]));
		Object result = object;
		if (op != null) {
			Collection<?> target = (object instanceof Collection<?> ? (Collection<?>) object : Collections.singletonList(object));
			result = requestQueryExecutor.getRequestQueryResult(target, op, params);
		}
		return result;
	}

	//
	
	public static String getPostBody(HttpServletRequest req, Map<String, Object> params) throws IOException {
		StringBuilder buffer = new StringBuilder();
		Scanner scanner = new Scanner(req.getInputStream());
		try {
			while (scanner.hasNextLine()) {
				buffer.append(scanner.nextLine());
				buffer.append("\n");
			}
		} finally {
			scanner.close();
		}
		return buffer.toString();
	}

	public static Map<String, Object> decodeQuery(HttpServletRequest req, Map<String, Object> params) {
		String query = req.getQueryString();
		if (query != null) {
			for (String param : query.split("&")) {
				int pos = param.indexOf('=');
				if (pos > 0) {
					params.put(param.substring(0, pos), param.substring(pos + 1));
				} else {
					params.put(param, true);				
				}
			}
		}
		return params;
	}

	public static Map<String, Object> decodePostBody(String body, Map<String, Object> params) {
		ObjectMapper mapper = new ObjectMapper();
		JsonNode jsonTree = null;
		try {
			jsonTree = mapper.readTree(body);
		} catch (IOException e) {
		}
		if (jsonTree instanceof ObjectNode) {
			ObjectNode objectNode = (ObjectNode) jsonTree;
			Iterator<String> fieldNames = objectNode.fieldNames();
			while (fieldNames.hasNext()) {
				String fieldName = fieldNames.next();
				Object valueNode = objectNode.get(fieldName);
				if (valueNode instanceof ValueNode) {
					valueNode = ((ValueNode) valueNode).asText();
				}
				params.put(fieldName, valueNode);
			}
		}
		return params;
	}
}

/*
 * @startuml
 * interface ILogger {
 * 	void log(int severity, String message)
 * }
 * class SMSLogger {
 * }
 * class FileLogger {
 * }
 * ILogger <|.. SMSLogger 
 * ILogger <|.. FileLogger
 * @enduml

 * @startuml
 * class SMSLogger {
 * }
 * class FileLogger {
 * }
 * SMSLogger -() ILogger
 * FileLogger -() ILogger
 * @enduml

 * @startuml
 * [SMSLogger] -- ILogger
 * [FileLogger] -- ILogger
 * @enduml

 * @startuml
 * class HttpServerImpl {
 * }
 * interface Servlet {
 * }
 * class DataServlet {
 * }
 * class AppServlet {
 * }
 * Servlet <|.. DataServlet 
 * Servlet <|.. AppServlet
 * HttpServerImpl -> Servlet: servlets
 * @enduml

 * @startuml
 * [DataServlet] -- Servlet
 * [AppServlet] -- Servlet
 * Servlet <.. [HttpServerImpl]: use
 * @enduml
 */
