package mzomorod;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import java.net.*;

public class Controller extends HttpServlet {
	
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
		
		HttpSession session = req.getSession();
		String view = (String) session.getAttribute("view");
		User user = (User) session.getAttribute("user");
		
		/* Process Request Headers */
		System.out.println("POSTVIEW: " + view);
		if (view.equals("Page6")) {
			String action = req.getParameter("submit");
			if (action.equals("Cancel")) {
				res.setStatus(HttpServletResponse.SC_TEMPORARY_REDIRECT);
				res.sendRedirect(res.encodeRedirectURL(req.getContextPath() + "/welcome"));
				return;
			} else if (action.equals("Edit")) {
				res.setStatus(HttpServletResponse.SC_TEMPORARY_REDIRECT);
				res.sendRedirect(res.encodeRedirectURL(req.getContextPath() + "/controller"));
				return;
			} else if (action.equals("Confirm")) {
				InputStream is = this.getClass().getClassLoader().getResourceAsStream(_filename);
				URL resourceUrl = this.getClass().getResource(_filename);
				UserData.addUser(user, is, resourceUrl);
				res.setStatus(HttpServletResponse.SC_TEMPORARY_REDIRECT);
				res.sendRedirect(res.encodeRedirectURL(req.getContextPath() + "/welcome"));
				Cookie fnameCookie = new Cookie("fname", user.getFirstName());
				fnameCookie.setMaxAge(360*24*7);
				res.addCookie(fnameCookie);
				Cookie lnameCookie = new Cookie("lname", user.getLastName());
				lnameCookie.setMaxAge(360*24*7);
				res.addCookie(lnameCookie);
				session.invalidate();
				res.setStatus(HttpServletResponse.SC_TEMPORARY_REDIRECT);
				res.sendRedirect(res.encodeRedirectURL(req.getContextPath() + "/"));
			}
		}
		
		if (view.equals("Page2")) {
			view = "Page3";
			user.setFirstName(req.getParameter("fname"));
			user.setLastName(req.getParameter("lname"));
			session.setAttribute("user", user);
		} else if (view.equals("Page3")) {
			view = "Page4";
			String[] languages = req.getParameterValues("languages");
			if (languages == null)
				user.clearLanguages();
			else
				user.addLanguages(languages);
			session.setAttribute("user", user);
		} else if (view.equals("Page4")) {
			view = "Page5";
			String[] days = req.getParameterValues("days");
			if (days == null)
				user.clearDays();
			else
				user.addDays(days);
			session.setAttribute("user", user);
		} else if (view.equals("Page5")) {
			view = "Page6";
			user.setState(req.getParameter("state"));
			session.setAttribute("user", user);
			req.setAttribute("user", user);
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
		
		System.out.println("GETVIEW: " + view);
		if (view == null || view.equals("Page3") || view.equals("Page6")) {
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