package mzomorod;

import javax.servlet.*;
import javax.servlet.http.*;

import java.io.*;

/**
 *	Lab1.java
 *	Matthew Zomorodi
 *	ASU-CIDSE
 *	SER 422 Spring 2019
 *	Professor Gary
 */

@SuppressWarnings("serial")
public class Lab1 extends HttpServlet {
	
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) 
	throws ServletException, IOException {
		
		res.setContentType("text/html");
		PrintWriter out= res.getWriter();
		out.println("<HTML><HEAD><TITLE>Phone List</TITLE></HEAD><BODY>Hello Lab1</BODY></HTML>");
	}
}