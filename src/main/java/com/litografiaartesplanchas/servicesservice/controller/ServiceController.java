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


@RestController
@RequestMapping("api/v1/service")
public class ServiceController {
    @Autowired
    private ServiceService serviceService;
    
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