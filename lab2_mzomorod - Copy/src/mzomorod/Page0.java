package mzomorod;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class Page0 extends HttpServlet {
	
	private static String _filename = null;
	
	public void init(ServletConfig config) throws ServletException {
		 
		super.init(config);
		_filename = config.getInitParameter("userlist");
		if (_filename == null || _filename.length() == 0) {
			throw new ServletException();
		}
		System.out.println("Loaded init param userlist with value " + _filename);
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException {

		PrintWriter out = res.getWriter();
		
		/* Process Request Headers */
		
		/* Process Request */
		
		/* Perform Processing */
		
		/* Aggregate Response Payload */
		
		/* Set Response Headers */
		res.setStatus(HttpServletResponse.SC_OK);
		res.setContentType("text/html");
		
		/* Write Results */
		out.println("<html><head><title>Lab 2</title></head><body>");
		out.println("<form action=\"welcome\" method=\"post\">");
		out.println("<label>First Name: </label>");
		out.println("<input type=\"text\" name=\"fname\"><br/><br/>");
		out.println("<label>Last Name: </label>");
		out.println("<input type=\"text\" name=\"lname\"><br/><br/>");
		out.println("<label>Password: </label><input type=\"password\" name=\"pword\"><br/><br/>");
		out.println("<input type=\"submit\">");
		out.println("</form></body></html>");
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException {
		
		PrintWriter out = res.getWriter();
		
		/* Set Response Headers */
		res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
		res.setContentType("text/html");
		
		out.println("<html><head><title>Lab 2</title></head><body>");
		out.println("<h1>Error 405: Method not allowed</h1><br/>");
		out.println("<p>POST not permitted on this URL.</p>");
		out.println("</body></html>");
	}
}