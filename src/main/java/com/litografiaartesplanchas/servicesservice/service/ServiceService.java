package com.litografiaartesplanchas.servicesservice.service;

import java.util.List;

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
	
	/**
	 * The above function returns a list of ServiceModel objects by calling the findAll method on the
	 * serviceRepository.
	 * 
	 * @return A List of ServiceModel objects is being returned.
	 */
	public List<ServiceModel> getAll(){
		return serviceRepository.findAll();
	}
	
	/**
	 * The function `getTypesService` returns a list of all `TypeService` objects from the
	 * `typeServiceRepository`.
	 * 
	 * @return A List of TypeService objects is being returned.
	 */
	public List<TypeService> getTypesService(){
		return typeServiceRepository.findAll();
	}
}
