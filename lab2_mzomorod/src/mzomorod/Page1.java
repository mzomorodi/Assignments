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
			
			/* Set Response Headers */
			res.setStatus(HTTPServletResponse.SC_OK);
			res.setContentType("text/html");
			
			Cookie[] cookies = req.getCookies();
			Cookie pageCookie = null;
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("Lab2Page"))
					pageCookie = cookie;
			}
			
			if (pageCookie == null) {
				res.addCookie(new Cookie("Lab2Page", "2"));
			} else {
				pageCookie.setValue("2");
				res.addCookie(pageCookie);
			}
			
			
			
			out.println("<html><head><title>Lab 2</title></head><body>");
			out.println("<h1>Welcome " + firstName + " " + lastName + "!</h1><br/>");
			out.println("<a href=\"" + req.getContextPath() + "/\">LOGOUT</a><br/>");
			out.println("<p>You do not have a profile. Please enter one now.</p><br/>");
			out.println("<a href=\"" + req.getContextPath() + "/controller\">CREATE</a><br/>");
			out.println("</body></html>");
		} else {
			// Display Error HTTP Msg
			res.setStatus(HTTPServletResponse.SC_ACCEPTED);
			res.setContentType("text/html");
			
			out.println("<html><head><title>Lab 2</title></head><body>");
			out.println("<p>Password does not match.</p>");
			out.println("</body></html>");
		}
		
		/* Aggregate Response Payload */
		
		
		/* Set Response Headers */
		
		/* Write Results */
		
		//TODO: Add list of first&last name links to Page7
		
		//TODO: Display User Data
		//TODO: Display best 3 matches
		
	}
	
}