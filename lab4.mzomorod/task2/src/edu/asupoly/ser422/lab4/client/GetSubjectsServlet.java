package edu.asupoly.ser422.lab4.client;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

import edu.asupoly.ser422.lab4.adb.Lab4ServiceStub;
import edu.asupoly.ser422.lab4.adb.Lab4ServiceStub.*;

@SuppressWarnings("serial")
public class GetSubjectsServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) 
		throws ServletException, IOException {
		
		PrintWriter out= res.getWriter();
		
		res.setContentType("text/html");

		try {
			Lab4ServiceStub lab4Stub = new Lab4ServiceStub();
			GetSubjectsResponse gsResponse = lab4Stub.getSubjects(
				new Lab4ServiceStub.GetSubjects());
			String[] subjects = gsResponse.get_return();
			
			res.setStatus(HttpServletResponse.SC_OK);
			out.println("<html><head><title>LAB4</title></head><body>");
			out.println("<p>Subjects: " + Arrays.toString(subjects) + "</p>");
			out.println("</body></html>");
		} catch (java.rmi.RemoteException re) {
			res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			out.println("<html><head><title>LAB4</title></head><body>");
			out.println("<p>Server Error: " + re.toString() + "</p>");
			out.println("</body></html>");
		} catch (java.lang.Exception e) {
			res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			out.println("<html><head><title>LAB4</title></head><body>");
			out.println("<p>Server Error: " + e.toString() + "</p>");
			out.println("</body></html>");
		}
	}
}