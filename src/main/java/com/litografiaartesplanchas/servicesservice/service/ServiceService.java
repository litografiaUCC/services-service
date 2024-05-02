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

/**
 * The `ServiceService` class in Java provides methods to manage services, including retrieving,
 * creating, updating, and deleting service entities while handling exceptions and conflicts.
 */
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
     * This Java function returns a list of ServiceModel objects by calling the findAll method on a
     * serviceRepository.
     * 
     * @return A List of ServiceModel objects is being returned.
     */
    public List<ServiceModel> getAll(){
        return serviceRepository.findAll();
    }

    
    /**
     * This Java function retrieves a ServiceModel object by its ID from a repository and throws a
     * NotFoundException if the object is not found.
     * 
     * @param id The `id` parameter is a `long` value representing the unique identifier of the service
     * that needs to be retrieved from the service repository.
     * @return The method `getServiceById` is returning a `ServiceModel` object with the specified
     * `id`. If the service with the given `id` is not found in the `serviceRepository`, a
     * `NotFoundException` is thrown with the message "Client not Found".
     */
    public ServiceModel getServiceById(long id) throws NotFoundException{
        Optional<ServiceModel> optionalService = serviceRepository.findById(id);
        if(optionalService.isEmpty()) {
            throw new NotFoundException("Client not Found");
        }
        return optionalService.get();
    }
    
    /**
     * This Java function retrieves a list of ServiceModel objects based on a given type ID, handling a
     * NotFoundException if the type does not exist.
     * 
     * @param id The `id` parameter is the unique identifier of the type of service for which you want
     * to retrieve a list of services.
     * @return The `getServicesByType` method returns a list of `ServiceModel` objects based on the
     * provided `id` of a `TypeService`.
     */
    public List<ServiceModel> getServicesByType(long id) throws NotFoundException{
        Optional<TypeService> optionalType = typeServiceRepository.findById(id);
        if(optionalType.isEmpty()) throw new NotFoundException("Type don't exist");
        TypeService type = optionalType.get();
        return serviceRepository.findByTypeService(type);
    }
    
    /**
     * This Java function returns a list of TypeService objects by querying all records from the
     * typeServiceRepository.
     * 
     * @return A List of TypeService objects is being returned.
     */
    public List<TypeService> getTypesService(){
        return typeServiceRepository.findAll();
    }
    
    /**
     * The `createService` function in Java creates a new service with specified materials and type,
     * checking for conflicts and ensuring valid data before saving it to the repository.
     * 
     * @param service The `service` parameter in the `createService` method is an instance of the
     * `ServiceModel` class. It contains information about a service that needs to be created, such as
     * the name of the service, type of service, service materials, and employees associated with the
     * service.
     */
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
    
    /**
     * The `updateService` function in Java updates a service entity with new data, handling conflicts
     * and exceptions for various fields like name, description, price, picture, type of service,
     * materials, and employees.
     * 
     * @param newServiceData The `updateService` method you provided is used to update an existing
     * service with new data provided in the `newServiceData` parameter. Let's break down the logic of
     * the method:
     */
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
    
    /**
     * The function deletes a service by its ID after checking if it exists in the repository.
     * 
     * @param id The `id` parameter in the `deleteService` method is used to specify the unique
     * identifier of the service that needs to be deleted from the repository.
     */
    public void deleteService(int id) throws NotFoundException {
    	if(serviceRepository.findById((long) id).isEmpty()) throw new NotFoundException("Service Not Found");
        ServiceModel service = this.getServiceById(id);
        serviceRepository.delete(service);
    }
}
