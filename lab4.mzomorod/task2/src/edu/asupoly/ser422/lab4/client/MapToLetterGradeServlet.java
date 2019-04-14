package edu.asupoly.ser422.lab4.client;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import edu.asupoly.ser422.lab4.adb.Lab4ServiceStub;
import edu.asupoly.ser422.lab4.adb.Lab4ServiceStub.*;

@SuppressWarnings("serial")
public class MapToLetterGradeServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) 
		throws ServletException, IOException {
		
		PrintWriter out= res.getWriter();
		
		String grade = req.getParameter("grade");
		
		res.setContentType("text/html");
		if (grade == null || grade.isEmpty()) {
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			out.println("<html><head><title>LAB4</title></head><body>");
			out.print("<p>Invalid URL Query Parameters: ");
			out.print("http://localhost:8080/lab4-mzomorod/map/");
			out.println("?grade=&ltgrade&gt</p>");
			out.println("</body></html>");
			return;
		}
		try {
			Lab4ServiceStub lab4Stub = new Lab4ServiceStub();
			MapToLetterGrade mlg = new Lab4ServiceStub.MapToLetterGrade();
			mlg.setArgs0(Double.parseDouble(grade));
			MapToLetterGradeResponse mlgResponse = lab4Stub.mapToLetterGrade(mlg);
			
			res.setStatus(HttpServletResponse.SC_OK);
			out.println("<html><head><title>LAB4</title></head><body>");
			out.println("<p>Letter Grade: " + mlgResponse.get_return() + "</p>");
			out.println("</body></html>");
		} catch (NumberFormatException nfe) {
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			out.println("<html><head><title>LAB4</title></head><body>");
			out.print("<p>Invalid URL Query Parameters: ");
			out.print("http://localhost:8080/lab4-mzomorod/map/");
			out.println("?grade=&ltgrade&gt</p><br/>");
			out.println("<p>Parameter must represent an integer or decimal</p>");
			out.println("</body></html>");
		} catch (java.rmi.RemoteException re) {
			res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			out.println("<html><head><title>LAB4</title></head><body>");
			out.println("<p>Server Error: " + re.toString() + "</p>");
			out.println("</body></html>");
		}
	}
}