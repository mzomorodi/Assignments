package mzomorod;

public class ContentProvider {
	
	ContentType content;
	
	public void setContentType(ContentType type) {
		content = type;
	}
	
	public String getTaskContent(Task[] tasks) {
		return content.getTaskContent(tasks);
	}
	
	public String getMessageContent(String msg, String desc) {
		return content.getMessageContent(msg, desc);
	}
}