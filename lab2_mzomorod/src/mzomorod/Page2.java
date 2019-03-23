package mzomorod;

import javax.servlet.http.*;
import java.io.*;

public class Page2 extends HttpServlet {
	
	public void service(HttpServletRequest req, HttpServletResponse res)
		throws ServletException {
			
			PrintWriter out = null;
			String firstName = "";
			String lastName = "";
			
			try {
				out = res.getWriter();
				
				res.setStatus(HttpServletResponse.SC_OK);
				res.setContentType("text/html");
				
				out.println("<html><head><title>Lab 2</title></head><body>");
				out.println("<form action=\"controller\" method=\"post\">");
				out.println("<label>First Name: </label>");
				out.println("<input type=\"text\" name=\"fname\" value=\"" + firstName + "\"><br/><br/>")
				out.println("<label>Last Name: </label>");
				out.println("<input type=\"text\" name=\"lname\" value=\"" + lastName + "\"><br/><br/>");
				out.println("<input type=\"submit\" value=\"Next\">");
				out.println("</form></body></html>");
				
			} catch (IOException ioe) {
				
			} finally {
				out.close();
			}
			
		}
	
}