package mzomorod;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class Page3 extends HttpServlet {
	
	public void service(HttpServletRequest req, HttpServletResponse res)
		throws ServletException {
			
		PrintWriter out = null;
		
		try {
			
			out = res.getWriter();
			
			res.setStatus(HttpServletResponse.SC_OK);
			res.setContentType("text/html");
			
			out.println("<html><head><title>Lab 2</title></head><body>");
			out.println("<form action=\"controller\" method=\"post\">");
			out.println("<label>Languages: </label><br/>");
			out.println("<select name=\"languages\" multiple>");
			out.println("<option value=\"Java\">Java</option>");
			out.println("<option value=\"C++\">C++</option>");
			out.println("<option value=\"Javascript\">Javascript</option>");
			out.println("<option value=\"Verilog\">Verilog</option>");
			out.println("<option value=\"Ada\">Ada</option>");
			out.println("<option value=\"Lisp\">Lisp</option>");
			out.println("<option value=\"Assembly\">Assembly</option>");
			out.println("<option value=\"PHP\">PHP</option>");
			out.println("<option value=\"Python\">Python</option>");
			out.println("</select>");
			out.println("<input type=\"submit\" name=\"page3\" value=\"Next\">");
			out.println("</form>");
			out.println("<a href=\"" + req.getContextPath() + "/controller\">Previous</a><br/>");
			out.println("</body></html>");
			
		} catch (IOException ioe) {
			
		} finally {
			out.close();
		}
		
	}
	
}