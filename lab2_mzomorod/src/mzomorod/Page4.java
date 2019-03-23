package mzomorod;

import javax.servlet.http.*;
import java.io.*;

public class Page4 extends HttpServlet {
	
	public void service(HttpServletRequest req, HttpServletResponse res)
		throws ServletException {
		
		PrintWriter out = null;
		
		try {
			
			out = res.getWriter();
			
			res.setStatus(HttpServletResponse.SC_OK);
			res.setContentType("text/html");
			
			out.println("<html><head><title>Lab 2</title></head><body>");
			out.println("<form action=\"controller\" method=\"post\">");
			out.println("<label>Meeting Days: </label><br/>");
			out.println("<select name=\"days\" multiple>");
			out.println("<option value=\"Monday\">Monday</option>");
			out.println("<option value=\"Tuesday\">Tuesday</option>");
			out.println("<option value=\"Wednesday\">Wednesday</option>");
			out.println("<option value=\"Thursday\">Thursday</option>");
			out.println("<option value=\"Friday\">Friday</option>");
			out.println("<option value=\"Saturday\">Saturday</option>");
			out.println("<option value=\"Sunday\">Sunday</option>");
			out.println("</select>");
			out.println("<input type=\"submit\" value=\"Next\">");
			out.println("</form></body></html>");
			
		} catch (IOException ioe) {
			
		} finally {
			out.close();
		}
		
	}
	
}