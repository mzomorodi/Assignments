package mzomorod;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

public class Page6 extends HttpServlet {
	
	public void service(HttpServletRequest req, HttpServletResponse res)
		throws ServletException {
		
		PrintWriter out = null;
		
		User user = (User) req.getAttribute("user");
		
		String firstName = user.getFirstName();
		String lastName = user.getLastName();
		String[] languages = user.getLanguages();
		String[] days = user.getDays();
		String state = user.getState();
		
		try {
			
			out = res.getWriter();
			
			res.setStatus(HttpServletResponse.SC_OK);
			res.setContentType("text/html");
			
			out.println("<html><head><title>Lab 2</title></head><body>");
			out.println("<h1>CONFIRM YOUR PROFILE</h1><br/><br/>");
			out.println("<h3 style=\"font-weight:bold;\">First Name: </h3><h3>" + firstName + "</h3><br/>");
			out.println("<h3 style=\"font-weight:bold;\">Last Name: </h3><h3>" + lastName + "</h3><br/>");
			out.println("<h3 style=\"font-weight:bold;\">Languages: </h3><h3>" + Arrays.toString(languages) + "</h3><br/>");
			out.println("<h3 style=\"font-weight:bold;\">Days: </h3><h3>" + Arrays.toString(days) + "</h3><br/>");
			out.println("<h3 style=\"font-weight:bold;\">State: </h3><h3>" + state + "</h3><br/><br/>");
			out.println("<form action=\"controller\" method=\"post\">");
			out.println("<input type=\"submit\" name=\"submit\" value=\"Cancel\">");
			out.println("<input type=\"submit\" name=\"submit\" value=\"Edit\">");
			out.println("<input type=\"submit\" name=\"submit\" value=\"Confirm\">");
			out.println("</form></body></html>");
			
		} catch (IOException ioe) {
			
		} finally {
			out.close();
		}
		
	}
	
}