package tdt4250.dict3.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.osgi.service.http.whiteboard.propertytypes.HttpWhiteboardServletPattern;
import org.osgi.service.log.Logger;
import org.osgi.service.log.LoggerFactory;

import tdt4250.dict3.api.Dict;
import tdt4250.dict3.api.DictSearch;
import tdt4250.dict3.api.DictSearchResult;

/**
 *@startuml
 *DictServlet -right-> "*" Dict: "dictionaries"
 *Dict <|.down. NbDict
 *@enduml
 */

/**
 * @startuml
 * circle Dict
 * component DictServlet
 * DictServlet -right-( "*" Dict: "dictionaries"
 * component NbDict
 * Dict -- NbDict
 *@enduml
 */

@Component
@HttpWhiteboardServletPattern("/dict/*")
public class DictServlet extends HttpServlet implements Servlet {

	private static final long serialVersionUID = 1L;

	private DictSearch dictSearch = new DictSearch();

	@Reference(
			cardinality = ReferenceCardinality.MULTIPLE,
			policy = ReferencePolicy.DYNAMIC,
			bind = "addDictionary",
			unbind = "removeDictionary"
	)
	public void addDictionary(Dict dict) {
		System.out.println("Adding dictionary " + dict.getDictName());
		dictSearch.addDictionary(dict);
	}
	public void removeDictionary(Dict dict) {
		System.out.println("Removing dictionary " + dict.getDictName());
		dictSearch.removeDictionary(dict);
	}

	@Reference(cardinality = ReferenceCardinality.OPTIONAL)
	private volatile LoggerFactory loggerFactory;
	
	private Logger getLogger() {
		if (loggerFactory != null) {
			return loggerFactory.getLogger(DictServlet.class);
		}
		return null;
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<String> segments = new ArrayList<>();
		String path = request.getPathTranslated();
		Logger logger = getLogger();
		logger.info("Received request for " + path);
		if (path != null) {
			segments.addAll(Arrays.asList(path.split("\\/")));
		}
		if (segments.size() > 0 && segments.get(0).length() == 0) {
			segments.remove(0);
		}
		if (segments.size() > 1) {
			response.sendError(HttpServletResponse.SC_NOT_ACCEPTABLE, "Request must contain max 1 path segment");
			return;
		}
		String q = request.getParameter("q");
		DictSearchResult result = (segments.size() == 0 ? dictSearch.search(q) : dictSearch.search(segments.get(0), q));
		logger.info("Search result " + (result.isSuccess() ? "succeeded" : "failed"));
		response.setContentType("text/plain");
		PrintWriter writer = response.getWriter();
		if (result.getLink() != null) {
			writer.print(result.getLink());
		}
		writer.print(result.getMessage());
	}
}
