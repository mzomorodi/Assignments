package mzomorod;

import javax.servlet.http.*;
import java.io.*;

public class Page5 extends HttpServlet {
	
	public void service(HttpServletRequest req, HttpServletResponse res)
		throws ServletException {
		
		PrintWriter out = null;
		
		try {
			
			out = res.getWriter();
			
			res.setStatus(HttpServletResponse.SC_OK);
			res.setContentType("text/html");
			
			out.println("<html><head><title>Lab 2</title></head><body>");
			out.println("<form action=\"controller\" method=\"post\">");
			out.println("<select name=\"days\">");
			out.println("<option value=\"AL\">Alabama</option><option value=\"AK\">Alaska</option>");
			out.println("<option value=\"AZ\">Arizona</option><option value=\"AR\">Arkansas</option>");
			out.println("<option value=\"CA\">California</option><option value=\"CO\">Colorado</option>");
			out.println("<option value=\"CT\">Connecticut</option><option value=\"DE\">Delaware</option>");
			out.println("<option value=\"FL\">Florida</option><option value=\"GA\">Georgia</option>");
			out.println("<option value=\"HI\">Hawaii</option><option value=\"ID\">Idaho</option>");
			out.println("<option value=\"IL\">Illinois</option><option value=\"IN\">Indiana</option>");
			out.println("<option value=\"IA\">Iowa</option><option value=\"KS\">Kansas</option>");
			out.println("<option value=\"KY\">Kentucky</option><option value=\"LA\">Louisiana</option>");
			out.println("<option value=\"ME\">Maine</option><option value=\"MD\">Maryland</option>");
			out.println("<option value=\"MA\">Massachusetts</option><option value=\"MI\">Michigan</option>");
			out.println("<option value=\"MN\">Minnesota</option><option value=\"MS\">Mississippi</option>");
			out.println("<option value=\"MO\">Missouri</option><option value=\"MT\">Montana</option>");
			out.println("<option value=\"NE\">Nebraska</option><option value=\"NV\">Nevada</option>");
			out.println("<option value=\"NH\">New Hampshire</option><option value=\"NJ\">New Jersey</option>");
			out.println("<option value=\"NM\">New Mexico</option><option value=\"NY\">New York</option>");
			out.println("<option value=\"NC\">North Carolina</option><option value=\"ND\">North Dakota</option>");
			out.println("<option value=\"OH\">Ohio</option><option value=\"OK\">Oklahoma</option>");
			out.println("<option value=\"OR\">Oregon</option><option value=\"PA\">Pennsylvania</option>");
			out.println("<option value=\"RI\">Rhode Island</option><option value=\"SC\">South Carolina</option>");
			out.println("<option value=\"SD\">South Dakota</option><option value=\"TN\">Tennessee</option>");
			out.println("<option value=\"TX\">Texas</option><option value=\"UT\">Utah</option>");
			out.println("<option value=\"VT\">Vermont</option><option value=\"VA\">Virginia</option>");
			out.println("<option value=\"WA\">Washington</option><option value=\"WV\">West Vriginia</option>");
			out.println("<option value=\"WI\">Wisconsin</option><option value=\"WY\">Wyoming</option>");
			out.println("</select>");
			out.println("<input type=\"submit\" value=\"Next\">");
			out.println("</form></body></html>");
			
		} catch(IOException ioe) {
			
		} finally {
			out.close();
		}
		
	}
	
}