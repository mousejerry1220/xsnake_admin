package org.xsnake.web.action;

public interface SessionSupport {

	void setSessionAttribute(String key , Object obj);
	
	void sessionInvalidate();
	
	Object getSessionAttribute(String key);
	
}
