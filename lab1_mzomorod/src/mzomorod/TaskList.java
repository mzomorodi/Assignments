package mzomorod;

import org.json.*;
import java.net.*;
import java.io.*;
import java.nio.file.*;
import java.util.*;

/**
 *	TaskList.java
 *	Matthew Zomorodi
 *	ASU-CIDSE
 *	SER 422 Spring 2019
 *	Professor Gary
 */

public class TaskList implements Serializable {
	
	private HashMap<String, Task> taskList = new HashMap<String, Task>();
	
	public TaskList() {
		
	}
	
	public TaskList(String fname) throws IOException {
		InputStream is = this.getClass().getClassLoader().getResourceAsStream(fname);
		
		try {
			JSONObject obj = new JSONObject(new JSONTokener(is));
			String[] names = JSONObject.getNames(obj);
			
			for (String name : names) {
				Task task = new Task((JSONObject)obj.getJSONObject(name));
				taskList.put(task.getName(), task);
			}			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error processing tasklist");
			throw new IOException("Could not process tasklist file");
		}
		
		is.close();
	}
	
	public boolean add(Task newTask) {
		taskList.put(newTask.getName(), newTask);
		Task task = taskList.get(newTask.getName());
		return task != null;
	}
	
	public int size() {
		return taskList.size();
	}
	
	public Task[] getTasks() {
		return taskList.values().toArray(new Task[0]);
	}
	
	public String toJsonString() {
		JSONObject obj = new JSONObject();
		Task[] tasks = getTasks();
		
		for (Task task : tasks)
			obj.put(task.getName(), task.toJson());
		
		return obj.toString();
	}
	
	public boolean saveTaskList(String fname) {
		try {
			System.out.println("Opening " + fname);
			URL resourceUrl = getClass().getResource(fname);
			File file = Paths.get(resourceUrl.toURI()).toFile();
			PrintWriter writer = new PrintWriter(new FileOutputStream(file));
			System.out.println("...done");
			String out = toJsonString();

			writer.write(out, 0, out.length());
			writer.flush();
			writer.close();
			return true;
		} catch (IOException ioe) {
			System.err.println("Error writing file: " + ioe);
			return false;
		} catch (URISyntaxException urise) {
			System.err.println("Error writing file: " + urise);
			return false;
		}
	}
}