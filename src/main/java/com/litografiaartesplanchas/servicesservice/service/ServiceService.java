package com.litografiaartesplanchas.servicesservice.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.litografiaartesplanchas.servicesservice.model.Material;
import com.litografiaartesplanchas.servicesservice.model.ServiceMaterial;
import com.litografiaartesplanchas.servicesservice.model.ServiceModel;
import com.litografiaartesplanchas.servicesservice.model.TypeService;
import com.litografiaartesplanchas.servicesservice.repository.IEmployeeRepository;
import com.litografiaartesplanchas.servicesservice.repository.IMaterialRepository;
import com.litografiaartesplanchas.servicesservice.repository.IServiceMaterialRepository;
import com.litografiaartesplanchas.servicesservice.repository.IServiceRepository;
import com.litografiaartesplanchas.servicesservice.repository.ITypeServiceRepository;
import com.litografiaartesplanchas.servicesservice.utils.errors.ConflictException;
import com.litografiaartesplanchas.servicesservice.utils.errors.NotFoundException;

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

	
	public Optional<ServiceModel> getServiceById(long id) {
		return serviceRepository.findById(id);
	}
	
	public List<ServiceModel> getServicesByType(long id) throws NotFoundException{
		Optional<TypeService> optionalType = typeServiceRepository.findById(id);
		if(optionalType.isEmpty()) throw new NotFoundException("Type don't exist");
		TypeService type = optionalType.get();
		return serviceRepository.findByTypeService(type);
	}
	
	public List<TypeService> getTypesService(){
		return typeServiceRepository.findAll();
	}
	
	public void createService(ServiceModel service) throws ConflictException{
		if(serviceRepository.existsByName(service.getName())) throw new ConflictException("Service already exists.");
		
		int idTypeService = service.getTypeService().getId();
		Optional<TypeService> optionalTypeService = typeServiceRepository.findById((long) idTypeService);
		if(optionalTypeService.isEmpty()) throw new ConflictException("Invalid type service with id: " + idTypeService);
		service.setTypeService(optionalTypeService.get());
		
		for(ServiceMaterial serviceMaterial: service.getServiceMaterials()) {
			Material material = serviceMaterial.getMaterial();
			Optional<Material> optionalMaterial = materialRepository.findById((long) material.getId());
			if(optionalMaterial.isEmpty()) throw new ConflictException("Invalid material with id: " + material.getId());
			material = optionalMaterial.get();
			serviceMaterial.setMaterial(material);
			serviceMaterial.setService(service);
		}
		
		if(service.getEmployees() != null) throw new ConflictException("Cannot create a service with employees");
		
		serviceRepository.save(service);
		serviceHasMaterialRepository.saveAll(service.getServiceMaterials());
	}
}
