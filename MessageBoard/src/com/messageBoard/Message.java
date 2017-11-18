package com.messageBoard;


public class Message {

	private String message;
	private String time;

	public String getTime() {
		return time;
	}

	public void setTime(String time2) {
		this.time = time2;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "Message [message=" + message + ", time=" + time + "]";
	}
	
}
