package mzomorod;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class Welcome extends HttpServlet{
	
	private static String _filename = null;
	
	public void init(ServletConfig config) throws ServletException {
		 
		super.init(config);
		_filename = config.getInitParameter("userlist");
		if (_filename == null || _filename.length() == 0) {
			throw new ServletException();
		}
		System.out.println("Loaded init param userlist with value " + _filename);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException {
		
		HttpSession session = null;
		PrintWriter out = res.getWriter();
		User user = new User();
		
		/* Process Request Headers */
		
		
		/* Process Request */
		String firstName = req.getParameter("fname");
		String lastName = req.getParameter("lname");
		String password = req.getParameter("pword");
		
		/* Perform Processing */
		if (password.equals("ser422") && !firstName.isEmpty() && !lastName.isEmpty()) {
			
			user.setFirstName(firstName);
			user.setLastName(lastName);
			
			session = req.getSession(true);
			session.setAttribute("user", user);
			
			/* Set Response Headers */
			res.setStatus(HttpServletResponse.SC_OK);
			res.setContentType("text/html");			
			
			out.println("<html><head><title>Lab 2</title></head><body>");
			out.println("<h1>Welcome " + firstName + " " + lastName + "!</h1><br/>");
			out.println("<a href=\"" + req.getContextPath() + "/\">LOGOUT</a><br/>");
			//TODO: Display list of names
			//TODO: Display user's set of data and EDIT hyperlink
			//TODO: Display best three matches
			out.println("<p>You do not have a profile. Please enter one now.</p><br/>");
			out.println("<a href=\"" + req.getContextPath() + "/controller\">CREATE</a><br/>");
			out.println("</body></html>");
		} else {
			// Display Error HTTP Msg
			res.setStatus(HttpServletResponse.SC_ACCEPTED);
			res.setContentType("text/html");
			
			out.println("<html><head><title>Lab 2</title></head><body>");
			out.println("<p>Invalid password or empty first or last name.</p><br/>");
			out.println("<a href=\"" + req.getContextPath() + "/\">SIGN IN</a><br/>");
			out.println("</body></html>");
		}
		
		/* Aggregate Response Payload */
		
		/* Set Response Headers */
		
		/* Write Results */
		
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException {
		
		PrintWriter out = res.getWriter();
		HttpSession session = req.getSession();
		User user = (User) session.getAttribute("user");
		
		String firstName = user.getFirstName();
		String lastName = user.getLastName();
		
		/* Set Response Headers */
		res.setStatus(HttpServletResponse.SC_OK);
		res.setContentType("text/html");
		
		out.println("<html><head><title>Lab 2</title></head><body>");
		out.println("<h1>Welcome " + firstName + " " + lastName + "!</h1><br/>");
		out.println("<a href=\"" + req.getContextPath() + "/\">LOGOUT</a><br/>");
		//TODO: Display list of names
		//TODO: Display user's set of data and EDIT hyperlink
		//TODO: Display best three matches
		out.println("<p>You do not have a profile. Please enter one now.</p><br/>");
		out.println("<a href=\"" + req.getContextPath() + "/controller\">CREATE</a><br/>");
		out.println("</body></html>");
		
	}
	
}