package com.litografiaartesplanchas.servicesservice.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.litografiaartesplanchas.servicesservice.model.ServiceModel;
import com.litografiaartesplanchas.servicesservice.model.TypeService;
import com.litografiaartesplanchas.servicesservice.service.ServiceService;
import com.litografiaartesplanchas.servicesservice.utils.Response;
import com.litografiaartesplanchas.servicesservice.utils.ResponseBody;
import com.litografiaartesplanchas.servicesservice.utils.errors.ErrorHandlerResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

/**
 * The `ServiceController` class in Java Spring defines RESTful endpoints for managing services,
 * handling exceptions and returning appropriate responses.
 */
@RestController
@RequestMapping("api/v1/service")
public class ServiceController {
    @Autowired
    private ServiceService serviceService;
    
    /**
     * This Java function retrieves all services and handles potential exceptions.
     * 
     * @return The `getAll` method in the code snippet is returning a `ResponseEntity` object. The
     * method is trying to get all services by calling the `serviceService.getAll()` method. If the
     * list of services is empty, it returns a response with status code 204 (No Content) using
     * `Response.noContent()`. If the list is not empty, it returns a response with status code 200
     */
    @GetMapping("/")
    @Operation(summary = "Get all services")
    @ApiResponses({
        @ApiResponse(responseCode = "200"),
        @ApiResponse(description = "No Content", responseCode = "201"),
        @ApiResponse(description = "Bad Request", responseCode = "400")
    })
    public ResponseEntity<?> getAll(){
        try{
        	List<ServiceModel> data = serviceService.getAll();
        	if(data.isEmpty()) return Response.noContent();
        	return Response.ok(data);
        }catch(Exception e) {
        	return ErrorHandlerResponse.handleException(e);
        }
    }

    /**
     * This Java function retrieves a service by its ID and returns a response with appropriate status
     * codes.
     * 
     * @param id The `id` parameter in the `@GetMapping` annotation represents the unique identifier of
     * the service that you want to retrieve. This endpoint is designed to fetch a service by its
     * specific ID.
     * @return A ResponseEntity object containing a ResponseBody object is being returned.
     */
    @GetMapping(value = "/{id}")
    @Operation(summary = "Get service by id")
    @ApiResponses({
        @ApiResponse(responseCode = "200"),
        @ApiResponse(description = "Not found", responseCode = "404"),
        @ApiResponse(description = "Bad Request", responseCode = "400")
    })
    public ResponseEntity<ResponseBody> getById(@PathVariable long id){
        try {
            ServiceModel service = serviceService.getServiceById(id);
            ArrayList<ServiceModel> data = new ArrayList<ServiceModel>();
            data.add(service);
            return Response.ok(data);
        }catch(Exception e) {
            return ErrorHandlerResponse.handleException(e);
        }
    }
    
    /**
     * This function retrieves all types of services and returns them in a ResponseEntity.
     * 
     * @return The `getAllTypes` method is returning a `ResponseEntity` object. If the list of
     * `TypeService` objects is empty, it returns a response with status code 204 (No Content). If the
     * list is not empty, it returns a response with status code 200 (OK) and the list of `TypeService`
     * objects. If an exception occurs during the execution of the method,
     */
    @GetMapping("/types")
    @Operation(summary = "Get all types of services")
    @ApiResponses({
        @ApiResponse(responseCode = "200"),
        @ApiResponse(description = "No Content", responseCode = "201"),
        @ApiResponse(description = "Bad Request", responseCode = "400")
    })
    public ResponseEntity<?> getAllTypes(){
    	try{
	        List<TypeService> data = serviceService.getTypesService();
	        if(data.isEmpty()) return Response.noContent();
	        return Response.ok(data);
    	}catch(Exception e) {
        	return ErrorHandlerResponse.handleException(e);
        }
    }
    
    /**
     * This Java function retrieves services by type and returns a response entity with the service
     * data.
     * 
     * @param id The `id` parameter in the `@GetMapping("/types/{id}")` annotation represents the
     * unique identifier of the service type that you want to retrieve information for. This value will
     * be extracted from the path variable in the URL when a request is made to this endpoint.
     * @return A ResponseEntity object containing a ResponseBody object is being returned.
     */
    @GetMapping("/types/{id}")
    @Operation(summary = "Get service by type")
    @ApiResponses({
        @ApiResponse(responseCode = "200"),
        @ApiResponse(description = "Not found", responseCode = "404"),
        @ApiResponse(description = "Bad Request", responseCode = "400"),
    })
    public ResponseEntity<ResponseBody> getByType(@PathVariable int id){
        try {
            List<ServiceModel> data = serviceService.getServicesByType(id);
            return Response.ok(data);
        }catch(Exception e) {
            return ErrorHandlerResponse.handleException(e);
        }
    }
    
    /**
     * The function creates a new service and handles potential exceptions.
     * 
     * @param service The `service` parameter in the `createService` method is of type `ServiceModel`.
     * It is annotated with `@RequestBody`, indicating that the data for this parameter will be
     * obtained from the request body of the HTTP POST request. The `ServiceModel` class likely
     * contains the necessary information to create
     * @return The `createService` method is returning a `ResponseEntity` object with a generic type of
     * `ResponseBody`. The method is returning either a successful response with status code 200 or an
     * error response with status codes 409 or 400 based on the outcome of creating a new service.
     */
    @PostMapping("/create")
    @Operation(summary = "Create a new service")
    @ApiResponses({
        @ApiResponse(responseCode = "200"),
        @ApiResponse(description = "Conflict", responseCode = "409"),
        @ApiResponse(description = "Bad Request", responseCode = "400")
    })
    public ResponseEntity<ResponseBody> createService(@RequestBody ServiceModel service){
        try {
            serviceService.createService(service);
            return Response.ok();
        }catch(Exception e) {
            System.out.println(e.getMessage());
            return ErrorHandlerResponse.handleException(e);
        }
    }
    
    /**
     * This function updates a service by its id and handles various response scenarios.
     * 
     * @param service The `service` parameter in the `updateService` method is of type `ServiceModel`
     * and represents the service entity that needs to be updated. This parameter is annotated with
     * `@RequestBody`, indicating that the data for this parameter will be obtained from the request
     * body.
     * @return The method `updateService` is returning a `ResponseEntity` object with a generic type of
     * `ResponseBody`. The return statement in the `try` block is `Response.ok()`, which indicates a
     * successful response with status code 200. If an exception is caught, the method returns the
     * result of `ErrorHandlerResponse.handleException(e)`, which handles the exception and returns an
     * appropriate response.
     */
    @PutMapping("/update")
    @Operation(summary = "Update a service by id")
    @ApiResponses({
        @ApiResponse(responseCode = "200"),
        @ApiResponse(description = "Bad Request", responseCode = "400"),
        @ApiResponse(description = "Not found", responseCode = "404"),
        @ApiResponse(description = "Conflict", responseCode = "409")
    })
    public ResponseEntity<ResponseBody> updateService(@RequestBody ServiceModel service){
        try{
        	serviceService.updateService(service);
            return Response.ok();
        }catch(Exception e){
            e.printStackTrace();
            return ErrorHandlerResponse.handleException(e);
        }
    }
    
    /**
     * This function is a DELETE endpoint in a Java Spring application that deletes a service by its ID
     * and returns an appropriate response based on the outcome.
     * 
     * @param id The `id` parameter in the `@DeleteMapping` annotation represents the unique identifier
     * of the service that you want to delete. This identifier is used to locate and delete the
     * specific service from the system.
     * @return The deleteService method is returning a ResponseEntity object with a ResponseBody type.
     * The method returns a response with status code 200 if the service is successfully deleted. If
     * there is a bad request (status code 400) or the service is not found (status code 404),
     * appropriate error responses are returned. If an exception occurs during the deletion process,
     * the method will handle the exception and return an
     */
    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete a servicey by id")
    @ApiResponses({
        @ApiResponse(responseCode = "200"),
        @ApiResponse(description = "Bad Request", responseCode = "400"),
        @ApiResponse(description = "Not found", responseCode = "404"),
    })
    public ResponseEntity<ResponseBody> deleteService(@PathVariable int id){
        try{
        	serviceService.deleteService(id);
            return Response.ok();
        }catch(Exception e){
            e.printStackTrace();
            return ErrorHandlerResponse.handleException(e);
        }
    }
}