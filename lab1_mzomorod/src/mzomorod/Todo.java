package mzomorod;

import javax.servlet.*;
import javax.servlet.http.*;

import java.io.*;
import java.util.*;

/**
 *	Todo.java
 *	Matthew Zomorodi
 *	ASU-CIDSE
 *	SER 422 Spring 2019
 *	Professor Gary
 */

@SuppressWarnings("serial")
public class Todo extends HttpServlet {
	
	private static String _filename = null;
	
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		_filename = config.getInitParameter("tasklist");
		if (_filename == null || _filename.length() == 0) {
			throw new ServletException();
		}
		System.out.println("Loaded init param tasklist with value " + _filename);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) 
		throws ServletException, IOException {
		
		PrintWriter out= res.getWriter();
		ContentProvider provider = new ContentProvider();
		String color = "White";
		TaskList taskList;
		
		/* Process Request Headers */
		if (req.getHeader("User-Agent").matches("(.*)Firefox(.*)"))
			color = "LightBlue";
		if (req.getHeader("Accept").contains("text/plain")) {
			provider.setContentType(new HTMLContent(req.getContextPath(), color));
			res.setContentType("text/plain");
		}
		else {
			provider.setContentType(new TextContent());
			res.setContentType("text/html");
		}
		
		/* Process Request */		
		String name = req.getParameter("name");
		String desc = req.getParameter("desc");
		String[] days = req.getParameterValues("days");
		String duration = req.getParameter("len");
		String owner = req.getParameter("owner");
		
		/* Perform Processing */
		try {
			taskList = new TaskList(_filename);
		} catch (IOException ioe) {
			res.setStatus(500);
			out.println(provider.getMessageContent("500 Error", ioe.toString()));
			return;
		}
		
		Task task = new Task(name, desc, days, duration, owner);
		taskList.add(task);
		taskList.saveTaskList(_filename);
		
		/* Aggregate Response Payload */
		
		/* Set Response Headers */
		res.setStatus(200);
		
		/* Write Results */
		out.println(provider.getMessageContent("Operation Successful!", 
			"There are " + taskList.size() + " tasks."));
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) 
		throws ServletException, IOException {
		
		PrintWriter out= res.getWriter();
		ContentProvider provider = new ContentProvider();
		String color = "White";
		TaskList taskList;
		
		/* Process Request Headers */
		if (req.getHeader("User-Agent").matches("(.*)Firefox(.*)"))
			color = "LightBlue";
		if (req.getHeader("Accept").contains("text/plain")) {
			provider.setContentType(new HTMLContent(req.getContextPath(), color));
			res.setContentType("text/plain");
		}
		else {
			provider.setContentType(new TextContent());
			res.setContentType("text/html");
		}
		
		/* Process Request */		
		
		/* Perform Processing */
		try {
			taskList = new TaskList(_filename);
		} catch (IOException ioe) {
			res.setStatus(500);
			out.println(provider.getMessageContent("500 Error", ioe.toString()));
			return;
		}
		//TODO: PERFORM SEARCH BASED ON QUERY STRING
		//
		
		/* Aggregate Response Payload */
		
		/* Set Response Headers */
		res.setStatus(200);
		res.setHeader("Cache-Control", "no-cache");
		
		/* Write Results */
		out.println(provider.getTaskContent(taskList.getTasks()));
	}
}