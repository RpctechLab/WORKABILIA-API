package it.teorema.workabilia.utils;

public class ResponseHttp {
	private String code;
	private String message;
	private Object dataSource;
	private String codeSession;
	private String sessionMessage;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getDataSource() {
		return dataSource;
	}
	public void setDataSource(Object dataSource) {
		this.dataSource = dataSource;
	}
	public String getCodeSession() {
		return codeSession;
	}
	public void setCodeSession(String codeSession) {
		this.codeSession = codeSession;
	}
	public String getSessionMessage() {
		return sessionMessage;
	}
	public void setSessionMessage(String sessionMessage) {
		this.sessionMessage = sessionMessage;
	}
}