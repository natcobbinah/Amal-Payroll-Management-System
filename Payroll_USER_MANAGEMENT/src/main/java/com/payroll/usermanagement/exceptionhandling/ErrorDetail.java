package com.payroll.usermanagement.exceptionhandling;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ErrorDetail {

	private String title;
	private int status;
	private String detail;
	private long timeStamp;
	private String developerMessage;
	private Map<String,List<ValidationError>> errors =
			new HashMap<String,List<ValidationError>>();
	
	public ErrorDetail() {
		super();
	}
	
	public ErrorDetail(String title, int status, String detail, long timeStamp, String developerMessage,
			Map<String, List<ValidationError>> errors) {
		super();
		this.title = title;
		this.status = status;
		this.detail = detail;
		this.timeStamp = timeStamp;
		this.developerMessage = developerMessage;
		this.errors = errors;
	}



	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(int status) {
		this.status = status;
	}
	/**
	 * @return the detail
	 */
	public String getDetail() {
		return detail;
	}
	/**
	 * @param detail the detail to set
	 */
	public void setDetail(String detail) {
		this.detail = detail;
	}
	/**
	 * @return the timeStamp
	 */
	public long getTimeStamp() {
		return timeStamp;
	}
	/**
	 * @param timeStamp the timeStamp to set
	 */
	public void setTimeStamp(long timeStamp) {
		this.timeStamp = timeStamp;
	}
	/**
	 * @return the developerMessage
	 */
	public String getDeveloperMessage() {
		return developerMessage;
	}
	/**
	 * @param developerMessage the developerMessage to set
	 */
	public void setDeveloperMessage(String developerMessage) {
		this.developerMessage = developerMessage;
	}
	/**
	 * @return the errors
	 */
	public Map<String, List<ValidationError>> getErrors() {
		return errors;
	}
	/**
	 * @param errors the errors to set
	 */
	public void setErrors(Map<String, List<ValidationError>> errors) {
		this.errors = errors;
	}
	
}
