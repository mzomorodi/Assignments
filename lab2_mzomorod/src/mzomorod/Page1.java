package mzomorod;

import javax.servlet.http.*;
import java.io.*;

public class Page1 extends HttpServlet{
	
	public void doPost(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException {
		
		PrintWriter out = res.getWriter();
		
		/* Process Request Headers */
		
		
		/* Process Request */
		String firstName = req.getParameter("fname");
		String lastName = req.getParameter("lname");
		String password = req.getParameter("pword");
		
		/* Perform Processing */
		if (password.equals("ser422")) {
			// Display Page 1
			req.setAttribute("firstname", firstName);
			req.setAttribute("lastname", lastName);
		} else {
			// Display Error HTTP Msg
		}
		
		/* Aggregate Response Payload */
		
		
		/* Set Response Headers */
		res.setStatus(HTTPServletResponse.SC_ACCEPTED);
		res.setContentType("text/html");
		
		/* Write Results */
		out.println("<html><head><title>Lab 2</title></head><body>");
		out.println("<h1>Welcome " + firstName + " " + lastName + "!</h1><br/>");
		out.println("<a href=\"" + req.getContextPath() + "/\">LOGOUT</a><br/>");
		out.println("<ul>");
		//TODO: Add list of first&last name links to Page7
		out.println("</ul>");
		//TODO: Display User Data
		//TODO: Display best 3 matches
		out.println("<a href=\"" + req.getContextPath() + "/controller\">EDIT</a><br/>");
	}
	
}