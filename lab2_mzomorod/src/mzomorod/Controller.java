package mzomorod;

import javax.servlet.http.*;
import java.io.*;

public class Controller extends HttpServlet {
	
	static int page;
	static String nextPage;
	
	public void doPost(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException {
	

		PrintWriter out = res.getWriter();
		
		/* Process Request Headers */
		
		Cookie[] cookies = req.getCookies();
		Cookie pageCookie = null;
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals("Lab2Page"))
				pageCookie = cookie;
		}
		page = Integer.parseInt(pageCookie.getValue());
		page++;
		nextPage = String.valueOf(page);
		
		res.addCookie(new Cookie("Lab2Page", nextPage));
		
		req.getRequestDispatcher("Page" + pageCookie.getValue()).forward(req, res);
	}
}