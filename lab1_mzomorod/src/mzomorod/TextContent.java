package mzomorod;

public class TextContent implements ContentType {
	
	public String getTaskContent(Task[] tasks) {
		
		String text = "";
		
		for (Task task : tasks) {
			text += "Name: " + task.getName() + "\n";
			text += "Description: " + task.getDescription() + "\n";
			text += "Days: ";
			String[] days = task.getDays();
			for (String day : days)
				text += day + " ";
			text += "\nDuration: " + task.getDuration() + "\n";
			text += "Owner: " + task.getOwner() + "\n";
		}
		
		return text;
	}
	
	public String getMessageContent(String msg, String desc) {
		
		String text = msg + "\n" + desc;
		
		return text;
	}
}