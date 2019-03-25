package mzomorod;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class Controller extends HttpServlet {
	
	public void doPost(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		String view = (String) session.getAttribute("view");
		User user = (User) session.getAttribute("user");
		
		/* Process Request Headers */
		
		
		if (view == null) {
			view = "Page2";
			req.setAttribute("firstname", user.getFirstName());
			req.setAttribute("lastname", user.getLastName());
		} else if (view.equals("Page2")) {
			view = "Page3";
			String firstName = req.getParameter("fname");
			String lastName = req.getParameter("lname");
			user.setFirstName(firstName);
			user.setLastName(lastName);
			session.setAttribute("user", user);
		} else if (view.equals("Page3")) {
			view = "Page4";
			String[] languages = req.getParameterValues("languages");
			user.addLanguages(languages);
			session.setAttribute("user", user);
		} else if (view.equals("Page4")) {
			view = "Page5";
			String[] days = req.getParameterValues("days");
			user.addDays(days);
			session.setAttribute("user", user);
		} else if (view.equals("Page5")) {
			view = "Page6";
			String state = req.getParameter("state");
			user.setState(state);
			session.setAttribute("user", user);
			req.setAttribute("user", user);
		} else {
			String action = req.getParameter("submit");
			
			if (action.equals("Cancel")) {
				session.removeAttribute("view");
				res.setStatus(HttpServletResponse.SC_SEE_OTHER);
				res.sendRedirect(res.encodeRedirectURL(req.getContextPath() + "/welcome"));
				return;
			} else if (action.equals("Edit")) {
				view = "Page2";
			} else if (action.equals("Confirm")) {
				
			}
		}
		
		session.setAttribute("view", view);
		
		try {
			req.getRequestDispatcher(view).forward(req, res);
		} catch(IOException ioe) {
			
		}
		
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse res)
		throws ServletException {
		
		HttpSession session = req.getSession();
		String view = (String) session.getAttribute("view");
		User user = (User) session.getAttribute("user");
		
		if (view.equals("Page3")) {
			view = "Page2";
			req.setAttribute("firstname", user.getFirstName());
			req.setAttribute("lastname", user.getLastName());
		} else if (view.equals("Page4")) {
			view = "Page3";
		} else if (view.equals("Page5")) {
			view = "Page4";
		}
		
		session.setAttribute("view", view);
		
		try {
			req.getRequestDispatcher(view).forward(req, res);
		} catch(IOException ioe) {
			
		}
	}
}