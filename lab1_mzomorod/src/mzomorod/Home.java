package mzomorod;

import javax.servlet.*;
import javax.servlet.http.*;

import java.io.*;

/**
 *	Home.java
 *	Matthew Zomorodi
 *	ASU-CIDSE
 *	SER 422 Spring 2019
 *	Professor Gary
 */

@SuppressWarnings("serial")
public class Home extends HttpServlet {
	
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) 
	throws ServletException, IOException {
		
		res.setStatus(200);
		res.setContentType("text/html");
		PrintWriter out= res.getWriter();
		out.println("<html><head><title>LAB1</title></head><body>");
		out.println("<form action=\"/Lab1/todos\" method=\"post\">");
		out.println("<label>Task Name: </label><input type=\"text\" name=\"name\"><br/><br/>");
		out.println("<label>Description: </label><input type=\"text\" name=\"desc\"><br/><br/>");
		out.println("<label>Task Days: </label><select name=\"days\" size=\"3\" multiple>");
		out.println("<option value=\"1\">1</option><option value=\"2\">2</option>");
		out.println("<option value=\"3\">3</option><option value=\"4\">4</option>");
		out.println("<option value=\"5\">5</option><option value=\"6\">6</option>");
		out.println("<option value=\"7\">7</option></select><br/><br/><br/>");
		out.println("<label>Duration (Hrs): </label><input type=\"text\" name=\"len\"><br/><br/>");
		out.println("<label>Owner: </label><input type=\"text\" name=\"owner\"><br/><br/>");
		out.println("<input type=\"submit\">");
		out.println("</form></body></html>");
	}
}