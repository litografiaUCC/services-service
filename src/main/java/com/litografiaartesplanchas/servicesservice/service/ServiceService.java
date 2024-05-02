package com.litografiaartesplanchas.servicesservice.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.litografiaartesplanchas.servicesservice.model.Employee;
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

    
    public ServiceModel getServiceById(long id) throws NotFoundException{
        Optional<ServiceModel> optionalService = serviceRepository.findById(id);
        if(optionalService.isEmpty()) {
            throw new NotFoundException("Client not Found");
        }
        return optionalService.get();
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
        if(service.getTypeService() == null) throw new ConflictException("Specific the type of service");
        int idTypeService = service.getTypeService().getId();
        Optional<TypeService> optionalTypeService = typeServiceRepository.findById((long) idTypeService);
        if(optionalTypeService.isEmpty()) throw new ConflictException("Invalid type service with id: " + idTypeService);
        service.setTypeService(optionalTypeService.get());
        
        for(ServiceMaterial serviceMaterial: service.getServiceMaterials()) {
            Material material = serviceMaterial.getMaterial();
            if(serviceMaterial.getQuantity() == 0) throw new ConflictException("Specific quantity of material by id: " + material.getId());
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
    
    public void updateService(ServiceModel newServiceData) throws ConflictException, NotFoundException{
    	Optional<ServiceModel> optional = serviceRepository.findById((long) newServiceData.getId());
    	if(optional.isEmpty()) throw new NotFoundException("Service not found");
    	
    	ServiceModel service = optional.get();
    	
    	if(newServiceData.getName() != null) {
    		if(serviceRepository.existsByName(newServiceData.getName())) throw new ConflictException("Service already exists");
    		service.setName(newServiceData.getName());
    	}
    	if(newServiceData.getDescription() != null) service.setDescription(newServiceData.getDescription());
    	if(newServiceData.getPrice() != 0) service.setDescription(newServiceData.getDescription());
    	if(newServiceData.getPicture() != null) service.setPicture(newServiceData.getPicture());
    	if(newServiceData.getDescription() != null) service.setDescription(newServiceData.getDescription());
    	if(newServiceData.getTypeService() != null) {
    		int idTypeService = newServiceData.getTypeService().getId();
	    	Optional<TypeService> optionalTypeService = typeServiceRepository.findById((long) idTypeService);
	        if(optionalTypeService.isEmpty()) throw new ConflictException("Invalid type service with id: " + idTypeService);
	        service.setTypeService(optionalTypeService.get());
    	}
	    
    	if(newServiceData.getServiceMaterials() != null) {
	    	for(ServiceMaterial serviceMaterial: newServiceData.getServiceMaterials()) {
	            Material material = serviceMaterial.getMaterial();

	            if(serviceMaterial.getQuantity() == 0) throw new ConflictException("Specific quantity of material by id: " + material.getId());
	            Optional<Material> optionalMaterial = materialRepository.findById((long) material.getId());
	            if(optionalMaterial.isEmpty()) throw new ConflictException("Invalid material with id: " + material.getId());
	            material = optionalMaterial.get();
	            serviceMaterial.setMaterial(material);
	            serviceMaterial.setService(service);
	        }
    	}
    	
    	service.setServiceMaterials(newServiceData.getServiceMaterials());
    	
    	if(newServiceData.getEmployees() != null) {
			Set<Employee> employees = service.getEmployees();
	    	
	    	for(Employee employee: newServiceData.getEmployees()) {
	             Optional<Employee> optionalEmployee = employeeRepository.findById((long) employee.getId());
	             if(optionalEmployee.isEmpty()) throw new ConflictException("Invalid employee with id: " + employee.getId());
	             employee = optionalEmployee.get();
	             if(!employees.contains(employee)) employees.add(employee);
	    	}
	    	
	    	service.setEmployees(employees);
    	}
    	
    	serviceRepository.save(service);
    }
    
    public void deleteService(int id) throws NotFoundException {
    	if(serviceRepository.findById((long) id).isEmpty()) throw new NotFoundException("Service Not Found");
        ServiceModel service = this.getServiceById(id);
        serviceRepository.delete(service);
    }
}
