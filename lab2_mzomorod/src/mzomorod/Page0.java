package mzomorod;

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
		throws ServletException {
		
		PrintWriter out = res.getWriter();
		
		/* Process Request Headers */
		
		/* Process Request */
		
		/* Perform Processing */
		String firstName = "";
		String lastName = "";
		
		/* Aggregate Response Payload */
		
		/* Set Response Headers */
		res.setStatus(HTTPServletResponse.SC_ACCEPTED);
		res.setContentType("text/html");
		
		/* Write Results */
		out.println("<html><head><title>Lab 2</title></head><body>");
		out.println("<form action=\"welcome\" method=\"post\">");
		out.println("<label>First Name: </label>");
		out.println("<input type=\"text\" name=\"fname\" value=\"" + firstName + "\"><br/><br/>")
		out.println("<label>Last Name: </label>");
		out.println("<input type=\"text\" name=\"lname\" value=\"" + lastName + "\"><br/><br/>");
		out.println("<label>Password: </label><input type=\"password\" name=\"pword\"><br/><br/>");
		out.println("<input type=\"submit\">");
		out.println("</form></body></html>");
	}
}