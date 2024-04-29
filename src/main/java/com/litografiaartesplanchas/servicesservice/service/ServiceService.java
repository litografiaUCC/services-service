package com.litografiaartesplanchas.servicesservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.litografiaartesplanchas.servicesservice.model.ServiceModel;
import com.litografiaartesplanchas.servicesservice.model.TypeService;
import com.litografiaartesplanchas.servicesservice.repository.IEmployeeRepository;
import com.litografiaartesplanchas.servicesservice.repository.IMaterialRepository;
import com.litografiaartesplanchas.servicesservice.repository.IServiceMaterialRepository;
import com.litografiaartesplanchas.servicesservice.repository.IServiceRepository;
import com.litografiaartesplanchas.servicesservice.repository.ITypeServiceRepository;

@Service
public class ServiceService {
	@Autowired
	private IServiceRepository serviceRepository;
	@Autowired
	private ITypeServiceRepository typeServiceRepository;
	@Autowired
	private IServiceMaterialRepository serviceHasMaterialRepository;
	@Autowired
	private IMaterialRepository materialRepository;
	@Autowired
	private IEmployeeRepository employeeRepository;
	
	public List<ServiceModel> getAll(){
		return serviceRepository.findAll();
	}
	
	/*
	public Optional<ServiceModel> getServiceById(long id) {
		return serviceRepository.findById(id);
	}*/
	
	public List<TypeService> getTypesService(){
		return typeServiceRepository.findAll();
	}
}
