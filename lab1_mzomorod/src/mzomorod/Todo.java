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
		TaskList taskList;
		
		/* Process Request Headers */
		
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
			res.setContentType("text/html");
			
			out.println("<html><head><title>LAB1</title></head><body>");
			out.println("<h1>500 Error: " + ioe);
			out.println("</body></html>");
			return;
		}
		
		Task task = new Task(name, desc, days, duration, owner);
		taskList.add(task);
		int size = taskList.size();
		
		taskList.saveTaskList(_filename);
		
		/* Aggregate Response Payload */
		
		/* Set Response Headers */
		res.setStatus(200);
		res.setContentType("text/html");
		
		/* Write Results */
		out.println("<html><head><title>LAB1</title></head><body>");
		out.println("<h1> Operation Successful!</h1><br/>");
		out.println("<p> There are " + size + " tasks.</p><br/>");
		out.println("<a href=\"" + req.getRequestURL() + "\">RETURN</a>");
		out.println("</body></html>");
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) 
		throws ServletException, IOException {
		
		PrintWriter out= res.getWriter();
		TaskList taskList;
		
		/* Process Request Headers */
		
		/* Process Request */		
		
		/* Perform Processing */
		try {
			taskList = new TaskList(_filename);
		} catch (IOException ioe) {
			res.setStatus(500);
			res.setContentType("text/html");
			
			out.println("<html><head><title>LAB1</title></head><body>");
			out.println("<h1>500 Error: " + ioe);
			out.println("</body></html>");
			return;
		}
		
		
		/* Aggregate Response Payload */
		
		/* Set Response Headers */
		res.setStatus(200);
		res.setContentType("text/html");
		
		/* Write Results */
		out.println("<html><head><title>LAB1</title></head><body>");
		Task[] tasks = taskList.getTasks();
		for (Task task : tasks) {
			out.println("Name: " + task.getName() + "<br/>");
			out.println("Description: " + task.getDescription() + "<br/>");
			String[] daysArr = task.getDays();
			String result = "Days: ";
			for (String dArr : daysArr) {
				result += dArr + " ";
			}
			out.println(result + "<br/>");
			out.println("Duration: " + task.getDuration() + "<br/>");
			out.println("Owner: " + task.getOwner() + "<br/>");
			out.println("<a href=\"" + req.getRequestURL() + "\">RETURN</a>");
			out.println("</body></html>");
		}
	}
}