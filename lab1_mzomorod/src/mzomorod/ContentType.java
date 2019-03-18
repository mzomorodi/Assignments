package mzomorod;

public interface ContentType {
	
	public String getTaskContent(Task[] tasks);
	
	public String getMessageContent(String msg, String desc);
}