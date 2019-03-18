package mzomorod;

public class HTMLContent implements ContentType {
	
	private String url;
	private String color;
	
	public HTMLContent(String url, String color) {
		this.url = url;
		this.color = color;
	}
	
	public String getTaskContent(Task[] tasks) {
		
		String html = "<html><head><title>LAB1</title></head><body style=\"background-color:" + color + "\">";
		
		for (Task task : tasks) {
			html += "Name: " + task.getName() + "<br/>";
			html += "Description: " + task.getDescription() + "<br/>";
			String[] daysArr = task.getDays();
			String result = "Days: ";
			for (String dArr : daysArr) {
				result += dArr + " ";
			}
			html += result + "<br/>";
			html += "Duration: " + task.getDuration() + "<br/>";
			html += "Owner: " + task.getOwner() + "<br/>";
		}
		
		html += "<a href=\"" + url + "/index.html\">RETURN</a>";
		html += "</body></html>";
		
		return html;
	}
	
	public String getMessageContent(String msg, String desc) {
		String html = "<html><head><title>LAB1</title></head><body style=\"background-color:" + color + "\">";
		html += "<h1>" + msg + "</h1><br/><p>" + desc + "</p>";
		html += "<a href=\"" + url + "/index.html\">RETURN</a>";
		html += "</body></html>";
		
		return html;
	}
}