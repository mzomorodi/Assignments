package mzomorod;

import javax.servlet.http.*;
import java.io.*;

public class Page0 extends HttpServlet {
	
	private static String _filename = null;
	
	public void init(ServletConfig config) throws ServletException {
		// if you forget this your getServletContext() will get a NPE! 
		super.init(config);
		_filename = config.getInitParameter("userlist");
		if (_filename == null || _filename.length() == 0) {
			throw new ServletException();
		}
		System.out.println("Loaded init param userlist with value " + _filename);
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse res)
		throws ServletException {
		
		
	}
}