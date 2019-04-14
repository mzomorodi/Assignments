package edu.asupoly.ser422.lab4.client;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import edu.asupoly.ser422.lab4.adb.Lab4ServiceStub;
import edu.asupoly.ser422.lab4.adb.Lab4ServiceStub.*;

@SuppressWarnings("serial")
public class CalculateGradeServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) 
		throws ServletException, IOException {
		
		PrintWriter out= res.getWriter();
		
		String year = req.getParameter("year");
		String subject = req.getParameter("subject");
		
		res.setContentType("text/html");
		if (year == null || year.isEmpty() || subject == null || subject.isEmpty()) {
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			out.println("<html><head><title>LAB4</title></head><body>");
			out.print("<p>Invalid URL Query Parameters: ");
			out.print("http://localhost:8080/lab4-mzomorod/calculate/");
			out.println("?year=&ltyear&gt&subject=&ltsubject&gt</p>");
			out.println("</body></html>");
			return;
		}
		
		try {
			Lab4ServiceStub lab4Stub = new Lab4ServiceStub();
			CalculateGrade cg = new Lab4ServiceStub.CalculateGrade();
			cg.setArgs0(year);
			cg.setArgs1(subject);
			CalculateGradeResponse cgResponse = lab4Stub.calculateGrade(cg);
			
			res.setStatus(HttpServletResponse.SC_OK);
			out.println("<html><head><title>LAB4</title></head><body>");
			out.println("<p>Grade: " + cgResponse.get_return() + "</p>");
			out.println("</body></html>");
		} catch (java.rmi.RemoteException re) {
			res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			out.println("<html><head><title>LAB4</title></head><body>");
			out.println("<p>Server Error: " + re.toString() + "</p>");
			out.println("</body></html>");
		}
			
	}
	
}