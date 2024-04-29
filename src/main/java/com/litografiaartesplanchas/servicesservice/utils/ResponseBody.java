package com.litografiaartesplanchas.servicesservice.utils;

import java.util.List;

import jakarta.validation.constraints.NotBlank;

/**
 * The class `ResponseBody` is a generic class in Java that represents a response with status, message,
 * and optional data for different HTTP status codes.
 */
public class ResponseBody<T> {
	@NotBlank
	private int status;
	@NotBlank
	private String message;
	private List<T> data;
	
	public ResponseBody(int status, String message) {
		super();
		this.status = status;
		this.message = message;
	}

	public ResponseBody(int status, String message, List<T> data) {
		super();
		this.status = status;
		this.message = message;
		this.data = data;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}
	
	/**
	 * The function `ok` returns a ResponseBody object with a status code of 200, message "ok", and a list
	 * of data.
	 * 
	 * @param data The `data` parameter in the `ok` method is a List of objects.
	 * @return A ResponseBody object with a status code of 200, message "ok", and the provided data in a
	 * List.
	 */
	public static ResponseBody<?> ok(List<?> data) {
		return new ResponseBody(200, "ok", data);
	}
	
	/**
	 * The function `badRequest` returns a ResponseBody object with a status code of 400 and an error
	 * message.
	 * 
	 * @return A ResponseBody object with a status code of 400 and a message "Something way wrong" is
	 * being returned.
	 */
	public static ResponseBody<?> badRequest() {
		return new ResponseBody(400, "Something way wrong");
	}
	
	/**
	 * The function `notFound()` returns a ResponseBody object with a 404 status code and "Not Found"
	 * message.
	 * 
	 * @return An instance of the ResponseBody class with a status code of 404 and a message of "Not
	 * Found" is being returned.
	 */
	public static ResponseBody<?> notFound() {
		return new ResponseBody(404, "Not Found");
	}

	/**
	 * The function `conflict` returns a `ResponseBody` object with a status code of 409 and the provided
	 * information.
	 * 
	 * @param info The `info` parameter in the `conflict` method is a string that contains information
	 * about the conflict that occurred.
	 * @return A ResponseBody object with a status code of 409 and the provided information.
	 */
	public static ResponseBody conflict(String info) {
		return new ResponseBody(409, info);
	}
}
