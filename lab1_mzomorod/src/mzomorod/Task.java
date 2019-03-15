package mzomorod;

import javax.servlet.*;
import javax.servlet.http.*;

import java.io.*;

/**
 *	Task.java
 *	Matthew Zomorodi
 *	ASU-CIDSE
 *	SER 422 Spring 2019
 *	Professor Gary
 */

@SuppressWarnings("serial")
public class Task extends HttpServlet {
	
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) 
	throws ServletException, IOException {
		
		res.setStatus(200);
		res.setContentType("text/html");
		PrintWriter out= res.getWriter();
		out.println("<HTML><HEAD><TITLE>LAB1</TITLE></HEAD><BODY>Hello Lab1</BODY></HTML>");
	}
}