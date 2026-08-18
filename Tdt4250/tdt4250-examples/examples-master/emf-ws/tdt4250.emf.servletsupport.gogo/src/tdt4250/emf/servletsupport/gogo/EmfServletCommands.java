package tdt4250.emf.servletsupport.gogo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;

import org.apache.felix.service.command.Descriptor;
import org.eclipse.emf.common.util.URI;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;
import org.osgi.service.cm.Configuration;
import org.osgi.service.cm.ConfigurationAdmin;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;

import tdt4250.ra.servlet.IResourceRequestProcessor;
import tdt4250.ra.servlet.IResourceRequestProcessor.RequestData;
import tdt4250.servletsupport.IResourceProvider;
import tdt4250.servletsupport.impl.ResourceProvider;

// see https://enroute.osgi.org/FAQ/500-gogo.html

@Component(
		service = EmfServletCommands.class,
		property = {
			"osgi.command.scope=emfservlet",
			"osgi.command.function=list",
			"osgi.command.function=get",
			"osgi.command.function=put",
			"osgi.command.function=post",
			"osgi.command.function=add",
			"osgi.command.function=remove",
		}
	)
public class EmfServletCommands {

	private Configuration getConfig(String resourceName) {
		try {
			Configuration[] configs = cm.listConfigurations("(&(" + ResourceProvider.RESOURCE_NAME_PROP + "=" + resourceName + ")(service.factoryPid=" + ResourceProvider.FACTORY_PID + "))");
			if (configs != null && configs.length >= 1) {
				return configs[0];
			}
		} catch (IOException | InvalidSyntaxException e) {
		}
		return null;
	}

	private <T> Optional<T> findService(Class<T> clazz, Predicate<T> consumer) {
		BundleContext bc = FrameworkUtil.getBundle(this.getClass()).getBundleContext();
		try {
			for (ServiceReference<T> serviceReference : bc.getServiceReferences(clazz, null)) {
				T service = bc.getService(serviceReference);
				try {
					if (service != null) {
						if (consumer.test(service)) {
							return Optional.of(service);
						}
					}
				} finally {
					bc.ungetService(serviceReference);
				}
			}
		} catch (InvalidSyntaxException e) {
		}
		return Optional.empty();
	}
	
	@Descriptor("list available resource providers")
	public void list() {
		System.out.print("Resources: ");
		findService(IResourceProvider.class, service -> {
			System.out.print(service.getName());
			if (getConfig(service.getName()) != null) {
				System.out.print("*");						
			}			
			System.out.print(" ");
			return false;
		});
		System.out.println();
	}

	private RequestData getRequestData(String name, String path) {
		RequestData requestData = new RequestData();
		Optional<IResourceProvider> resourceProvider = findService(IResourceProvider.class, service -> name.equals(service.getName()));
		if (resourceProvider.isPresent()) {
			requestData.resourceProvider = resourceProvider.get();			
		}
		List<String> segments = new ArrayList<>(Arrays.asList(path.split("/")));
		segments.removeIf(String::isEmpty);
		requestData.resourcePath = segments;
		return requestData;
	}
	
	private void handleRequest(RequestData requestData) {
		Optional<IResourceRequestProcessor> requestProcessor = findService(IResourceRequestProcessor.class, service -> true);
		if (requestProcessor.isPresent()) {
			try {
				System.out.println(requestProcessor.get().getResultString(requestData));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}		
	}
	
	@Descriptor("get a path, as if using a HTTP GET request")
	public void get(
			@Descriptor("the resource provider name")
			String name,
			@Descriptor("the path to get")
			String path
			) {
		RequestData requestData = getRequestData(name, path);
		handleRequest(requestData);
	}
	
	@Descriptor("post to a path, as if using a HTTP POST request")
	public void post(
			@Descriptor("the resource provider name")
			String name,
			@Descriptor("the path to post to")
			String path,
			@Descriptor("the post body")
			String body,
			@Descriptor("the operation to invoke")
			String op,
			@Descriptor("the operation arguments")
			Map<String, String> params
			) {
		RequestData requestData = getRequestData(name, path);
		requestData.body = body;
		requestData.op = op;
		requestData.params = new HashMap<>();
		for (Map.Entry<String, String> entry : params.entrySet()) {
			requestData.params.put(entry.getKey(), entry.getValue());
		}
		handleRequest(requestData);
	}

	@Reference(cardinality = ReferenceCardinality.MANDATORY)
	private ConfigurationAdmin cm;

	@Descriptor("add a resource, with content from a URL and/or specific words")
	public void add(
			@Descriptor("the name of the new resource")
			String name,
			@Descriptor("the URI of the resource")
			String urlString
			) throws IOException, IllegalArgumentException {
		URI uri = URI.createURI(urlString);
		// lookup existing configuration
		Configuration config = getConfig(name);
		String actionName = "updated";
		if (config == null) {
			// create a new one
			config = cm.createFactoryConfiguration(ResourceProvider.FACTORY_PID, "?");
			actionName = "added";
		}
		// keys are strings, values can be simple values or arrays of simple values
		Dictionary<String, Object> props = new Hashtable<>();
		props.put(ResourceProvider.RESOURCE_NAME_PROP, name);
		props.put(ResourceProvider.RESOURCE_URI_PROP, uri.toString());
		config.update(props);
		System.out.println("\"" + name + "\" resource (" + config.getPid() + ") " + actionName);
	}

	@Descriptor("remove a (manually added) resource")
	public void remove(
			@Descriptor("the name of the (manually added) resource to remove")
			String name
			) throws IOException {
		Configuration config = getConfig(name);
		boolean removed = false;
		if (config != null) {
			config.delete();
			removed = true;
		}
		System.out.println("\"" + name + "\" resource " + (removed ? "removed" : "was not added manually"));
	}
}
