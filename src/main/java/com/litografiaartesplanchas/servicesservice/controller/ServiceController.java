package com.litografiaartesplanchas.servicesservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.litografiaartesplanchas.servicesservice.model.ServiceModel;
import com.litografiaartesplanchas.servicesservice.model.TypeService;
import com.litografiaartesplanchas.servicesservice.service.ServiceService;

@RestController
@RequestMapping("api/v1/service")
public class ServiceController {
	@Autowired
	private ServiceService serviceService;
	
	/**
	 * This Java function uses a GET mapping to retrieve a list of ServiceModel objects.
	 * 
	 * @return A list of ServiceModel objects is being returned.
	 */
	@GetMapping("/")
	public List<ServiceModel> getAll(){
		return serviceService.getAll();
	}

	/**
	 * This Java function returns a list of TypeService objects by calling a method from a service class.
	 * 
	 * @return A list of TypeService objects is being returned.
	 */
	@GetMapping("/types")
	public List<TypeService> getAllTypes(){
		return serviceService.getTypesService();
	}
}
