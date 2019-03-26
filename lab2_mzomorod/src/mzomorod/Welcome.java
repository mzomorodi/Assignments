package mzomorod;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

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
		User user = null;
		
		/* Process Request Headers */
		
		
		/* Process Request */
		String firstName = req.getParameter("fname");
		String lastName = req.getParameter("lname");
		String password = req.getParameter("pword");
		
		/* Perform Processing */
		if (password.equals("ser422") && !firstName.isEmpty() && !lastName.isEmpty()) {
			
			session = req.getSession(true);
			
			InputStream is = this.getClass().getClassLoader().getResourceAsStream(_filename);
			User[] users = UserData.getUserList(is);
			
			/* Set Response Headers */
			res.setStatus(HttpServletResponse.SC_OK);
			res.setContentType("text/html");			
			
			out.println("<html><head><title>Lab 2</title></head><body>");
			out.println("<h1>Welcome " + firstName + " " + lastName + "!</h1><br/>");
			out.println("<a href=\"" + req.getContextPath() + "/\">LOGOUT</a><br/>");
			out.println("<p>Current Users:</p><br/>");
			//Display list of names
			for (User u : users) {
				
				out.println("<a href=\"" + req.getContextPath() + "/usercontroller?firstname=\"" +
					u.getFirstName() + "\"&lastname=\"" + u.getLastName() + "\">" +
					u.getFirstName() + " " + u.getLastName() + "</a><br/>");
				
				if (u.getFirstName().equals(firstName) && u.getLastName().equals(lastName))
					user = u;
			}
			//TODO: Display user's set of data and EDIT hyperlink, or CREATE hyperlink
			if (user == null) {
				
				user = new User();
				user.setFirstName(firstName);
				user.setLastName(lastName);
				out.println("<p>You do not have a profile. Please enter one now.</p><br/>");
				out.println("<a href=\"" + req.getContextPath() + "/controller\">CREATE</a><br/>");
				
			} else {
				
				out.println("<p>Your Profile:</p><br/>");
				out.println("First Name: " + user.getFirstName() + "<br/>");
				out.println("Last Name: " + user.getLastName() + "<br/>");
				out.println("Languages: " + Arrays.toString(user.getLanguages()) + "<br/>");
				out.println("Days: " + Arrays.toString(user.getDays()) + "<br/>");
				out.println("State: " + user.getState() + "<br/>");
				out.println("<a href=\"" + req.getContextPath() + "/controller\">EDIT</a><br/>");
			}
			
			//TODO: Display best three matches
			
			out.println("</body></html>");
			
			session.setAttribute("user", user);
			
		} else {
			/* Set Response Headers */
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
		
		InputStream is = this.getClass().getClassLoader().getResourceAsStream(_filename);
		User[] users = UserData.getUserList(is);
		
		/* Set Response Headers */
		res.setStatus(HttpServletResponse.SC_OK);
		res.setContentType("text/html");
		
		out.println("<html><head><title>Lab 2</title></head><body>");
		out.println("<h1>Welcome " + firstName + " " + lastName + "!</h1><br/>");
		out.println("<a href=\"" + req.getContextPath() + "/\">LOGOUT</a><br/>");
		out.println("<p>Current Users:</p><br/>");
		//Display list of names
		for (User u : users) {
			
			out.println("<a href=\"" + req.getContextPath() + "/usercontroller?firstname=\"" +
				u.getFirstName() + "\"&lastname=\"" + u.getLastName() + "\">" +
				u.getFirstName() + " " + u.getLastName() + "</a><br/>");
		}
		//Display user's set of data and EDIT hyperlink
		out.println("<p>Your Profile:</p><br/>");
		out.println("First Name: " + user.getFirstName() + "<br/>");
		out.println("Last Name: " + user.getLastName() + "<br/>");
		out.println("Languages: " + Arrays.toString(user.getLanguages()) + "<br/>");
		out.println("Days: " + Arrays.toString(user.getDays()) + "<br/>");
		out.println("State: " + user.getState() + "<br/>");
		out.println("<a href=\"" + req.getContextPath() + "/controller\">EDIT</a><br/>");
		//TODO: Display best three matches
		out.println("</body></html>");
		
	}
	
}