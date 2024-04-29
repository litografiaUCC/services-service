package com.litografiaartesplanchas.servicesservice.utils;


import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

/**
 * The `Response` class provides static methods to generate different types of ResponseEntity objects
 * with corresponding status codes and response bodies.
 */
public class Response {
	/**
	 * The function `generateResponseEntity` creates a ResponseEntity object with a given ResponseBody and
	 * HttpStatusCode.
	 * 
	 * @param body The `body` parameter is an object of type `ResponseBody`, which contains the data that
	 * will be included in the response entity.
	 * @param status The `status` parameter in the `generateResponseEntity` method is of type
	 * `HttpStatusCode`. It is used to specify the HTTP status code that will be included in the response
	 * entity.
	 * @return A `ResponseEntity` object containing a `ResponseBody` object and a `HttpStatusCode` object
	 * is being returned.
	 */
	private static ResponseEntity<ResponseBody> generateResponseEntity(ResponseBody body, HttpStatusCode status) {
		return new ResponseEntity<ResponseBody>(body, status);
	}
	
	/**
	 * The function returns a ResponseEntity with a successful status code and a response body indicating
	 * success.
	 * 
	 * @return A `ResponseEntity` object with a `ResponseBody` object set to `ok` and an HTTP status of
	 * `OK` is being returned.
	 */
	public static ResponseEntity<ResponseBody> ok(){
		return generateResponseEntity(ResponseBody.ok(null), HttpStatus.OK);
	}
	
	/**
	 * The function returns a ResponseEntity with a successful status code and a response body containing
	 * a list of data.
	 * 
	 * @param data The `data` parameter is a List of objects that will be included in the response body.
	 * @return A `ResponseEntity` object containing a `ResponseBody` object with the data provided in the
	 * input list, along with an HTTP status code of 200 (OK).
	 */
	public static ResponseEntity<ResponseBody> ok(List<?> data){
		return generateResponseEntity(ResponseBody.ok(data), HttpStatus.OK);
	}
	
	/**
	 * The function returns a ResponseEntity with no content.
	 * 
	 * @return A ResponseEntity object with no content is being returned.
	 */
	public static ResponseEntity<Object> noContent() {
		return ResponseEntity.noContent().build();
	}
	
	/**
	 * The function returns a ResponseEntity with a not found response body and HTTP status code 404.
	 * 
	 * @return A ResponseEntity object with a ResponseBody object representing a "not found" response and
	 * an HTTP status code of 404 (NOT_FOUND) is being returned.
	 */
	public static ResponseEntity<ResponseBody> notFound() {
		return generateResponseEntity(ResponseBody.notFound(), HttpStatus.NOT_FOUND);
	}
	
	/**
	 * The function `badRequest()` returns a ResponseEntity with a bad request status and body.
	 * 
	 * @return A `ResponseEntity` object with a `ResponseBody` object representing a bad request response
	 * and an HTTP status of 400 (Bad Request) is being returned.
	 */
	public static ResponseEntity<ResponseBody> badRequest() {
		return generateResponseEntity(ResponseBody.badRequest(), HttpStatus.BAD_REQUEST);
	}

	/**
	 * The function "conflict" returns a ResponseEntity with a conflict status and a response body
	 * containing the provided information.
	 * 
	 * @param info The `info` parameter is a string that contains information about the conflict that
	 * occurred. This information will be included in the response body to provide details about the
	 * conflict.
	 * @return A `ResponseEntity` object with a `ResponseBody` object containing conflict information and
	 * an HTTP status code of 409 (CONFLICT).
	 */
	public static ResponseEntity<ResponseBody> conflict(String info) {
		return generateResponseEntity(ResponseBody.conflict(info), HttpStatus.CONFLICT);
	}
}