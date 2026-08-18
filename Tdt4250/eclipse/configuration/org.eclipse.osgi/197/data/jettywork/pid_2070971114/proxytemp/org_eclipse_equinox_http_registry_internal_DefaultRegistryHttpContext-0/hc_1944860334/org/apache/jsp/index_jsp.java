package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import org.eclipse.help.internal.webapp.data.*;
import org.eclipse.help.internal.webapp.HelpUi;
import java.util.Scanner;
import java.net.URL;
import java.util.UUID;
import org.eclipse.core.runtime.Platform;
import org.eclipse.help.internal.base.BaseHelpSystem;

public final class index_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			"/advanced/err.jsp", true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");

	request.setCharacterEncoding("UTF-8");
	ServerState.webappStarted(application,request, response);
	// Read the scope parameter
	RequestScope.setScopeFromRequest(request, response);
	LayoutData data = new LayoutData(application,request, response);

	if(data.isBot()){
		TocData tData = new TocData(application,request, response);

      out.write("\n");
      out.write("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">\n");
      out.write("<html lang=\"");
      out.print(ServletResources.getString("locale", request));
      out.write("\">\n");
      out.write("<head>\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("<title>");
      out.print(data.getWindowTitle());
      out.write("</title>\n");
      out.write("</head>\n");
      out.write("<body>\n");
 tData.generateLinks(out); 
      out.write("\n");
      out.write("</body>\n");
      out.write("</html>\t\n");

	} else {
		// For live help
		String token = request.getParameter("token"); //$NON-NLS-1$
		if (token != null && token.matches("[a-z0-9-]{36}")) { //$NON-NLS-1$
			if (BaseHelpSystem.getInstance().matchOnceLiveHelpToken(token)) {
				// Only one session can grab this
				if (request.getSession().getAttribute("XSESSION") == null) { //$NON-NLS-1$
					String token2 = UUID.randomUUID().toString();
					request.getSession().setAttribute("XSESSION", token2); //$NON-NLS-1$
					int port = request.getLocalPort();
					response.addHeader("Set-Cookie", "XSESSION-" + port + "=" + token2 + "; HttpOnly; SameSite=Strict"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
					String token3 = UUID.randomUUID().toString();
					request.getSession().setAttribute("LSESSION", token3); //$NON-NLS-1$
				}
			}
		}

		// Experimental UI: see bug 501718
		String experimentalUi = System.getProperty("org.eclipse.help.webapp.experimental.ui");
		if (request.getParameter("legacy") == null && experimentalUi != null) {
			try {
				// In a JSP forwarding to non JSP resources does not work
				// (page is shown, but "java.lang.IllegalStateException: STREAM" is thrown),
				// so read it as plug-in resource instead:
				String resource = experimentalUi.equalsIgnoreCase("true") ? "org.eclipse.help.webapp/m/index.html" : experimentalUi;
				String[] bundleAndPath = resource.split("/", 2);
				URL resourceAsUrl = Platform.getBundle(bundleAndPath[0]).getResource(bundleAndPath[1]);
				// Read it as InputStream and convert it to a String
				// (by using a Scanner with a delimiter that cannot be found: \A - start of input)
				Scanner scanAll = new Scanner(resourceAsUrl.openStream()).useDelimiter("\\A");
				response.getWriter().write(HelpUi.resolve(scanAll.hasNext() ? scanAll.next() : "", request));
			} catch (Exception e) {
				// Experimental UI resource not found, so fall back to legacy UI
				request.getRequestDispatcher("/advanced/index.jsp" + data.getQuery()).forward(request, response);
			}

		// legacy UI
		} else {
			request.getRequestDispatcher("/advanced/index.jsp" + data.getQuery()).forward(request, response);
		}

	}

      out.write('\n');
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
