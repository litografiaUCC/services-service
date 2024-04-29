package com.litografiaartesplanchas.servicesservice.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.litografiaartesplanchas.servicesservice.model.ServiceModel;
import com.litografiaartesplanchas.servicesservice.model.TypeService;
import com.litografiaartesplanchas.servicesservice.service.ServiceService;
import com.litografiaartesplanchas.servicesservice.utils.Response;
import com.litografiaartesplanchas.servicesservice.utils.ResponseBody;
import com.litografiaartesplanchas.servicesservice.utils.errors.ErrorHandlerResponse;
import com.litografiaartesplanchas.servicesservice.utils.errors.NotFoundException;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("api/v1/service")
public class ServiceController {
	@Autowired
	private ServiceService serviceService;
	
	@GetMapping("/")
	public ResponseEntity<ResponseBody> getAll(){
		List<ServiceModel> data = serviceService.getAll();
		return Response.ok(data);
	}

	/*
	@GetMapping(value = "/{id}")
	@ApiResponses({
        @ApiResponse(responseCode = "200"),
        @ApiResponse(description = "Not found", responseCode = "404")
    })
	public ResponseEntity<ResponseBody> getById(@PathVariable long id){
		try {
			Optional<ServiceModel> optionalService = serviceService.getServiceById(id);
			if(optionalService.isEmpty()) {
				throw new NotFoundException("Client not Found");
			}
			ServiceModel service = optionalService.get();
			ArrayList<ServiceModel> data = new ArrayList<ServiceModel>();
			data.add(service);
			return Response.ok(data);
		}catch(Exception e) {
			return ErrorHandlerResponse.handleException(e);
		}
	}*/
	
	@GetMapping("/types")
	public ResponseEntity<ResponseBody> getAllTypes(){
		List<TypeService> data = serviceService.getTypesService();
		return Response.ok(data);
	}
}
