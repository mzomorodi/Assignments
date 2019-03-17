package mzomorod;

import org.json.*;
import java.util.LinkedList;
import java.io.Serializable;

/**
 *	Task.java
 *	Matthew Zomorodi
 *	ASU-CIDSE
 *	SER 422 Spring 2019
 *	Professor Gary
 */

public class Task implements Serializable{
	
	private String name;
	private String description;
	private String duration;
	private String owner;
	
	private LinkedList<String> days;
	
	public Task(String p_name, String p_description,
		String[] p_days, String p_duration, String p_owner) {
		
		name = p_name;
		description = p_description;
		duration = p_duration;
		owner = p_owner;
		
		days = new LinkedList<String>();
		for (String p_day : p_days)
			days.add(p_day);
	}
	
	public Task(JSONObject jsonObj) {
		name = jsonObj.getString("Name");
		description = jsonObj.getString("Description");
		duration = jsonObj.getString("Duration");
		owner = jsonObj.getString("Owner");
		
		days = new LinkedList<String>();
		JSONArray dayArray = jsonObj.getJSONArray("Days");
		for (int i = 0; i < dayArray.length(); i++)
			days.add((String) dayArray.get(i));
	}
	
	
	public String getName() {
		return name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public String getDuration() {
		return duration;
	}
	
	public String getOwner() {
		return owner;
	}
	
	public String[] getDays() {
		return days.toArray(new String[0]);
	}
	
	public JSONObject toJson() {
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("Name", name);
		jsonObj.put("Description", description);
		jsonObj.put("Days", days);
		jsonObj.put("Duration", duration);
		jsonObj.put("Owner", owner);
		
		return jsonObj;
	}
	
	public String toString() {
		String result = "Name: " + name + "\n";
		result += "Description: " + description + "\n";
		result += "Days: ";
		for (String day : days) {
			result += day + " ";
		}
		result += "\nDuration: " + duration + "\n";
		result += "Owner: " + owner + "\n";
		
		return result;
	}
}