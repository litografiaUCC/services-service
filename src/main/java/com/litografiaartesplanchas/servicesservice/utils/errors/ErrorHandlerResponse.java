package com.litografiaartesplanchas.servicesservice.utils.errors;

import org.springframework.http.ResponseEntity;

import com.litografiaartesplanchas.servicesservice.utils.Response;
import com.litografiaartesplanchas.servicesservice.utils.ResponseBody;

/**
 * The `ErrorHandlerResponse` class provides a method `handleException` that returns appropriate
 * responses based on the type of exception encountered.
 */
public class ErrorHandlerResponse{
	
	/**
     * The function `handleException` returns an appropriate response based on the type of exception
     * thrown.
     * 
     * @param exception The `exception` parameter is an instance of the `Exception` class that is passed
     * to the `handleException` method for handling different types of exceptions. The method checks the
     * type of the exception and returns an appropriate response based on the type of exception
     * encountered.
     * @return The method `handleException` returns a `ResponseEntity` object with a `ResponseBody` type.
     * The specific response returned depends on the type of exception passed to the method. If the
     * exception is an instance of `NotFoundException`, a not found response is returned. If it is an
     * instance of `ConflictException`, a conflict response with the exception message is returned.
     * Otherwise, a bad request response is returned
     */
    public static ResponseEntity<ResponseBody> handleException(Exception exception) {
		if (exception instanceof NotFoundException) {
            return Response.notFound();
        } else if (exception instanceof ConflictException) {
            return Response.conflict(exception.getMessage());
        } else {
            return Response.badRequest();
        }
	}
}
