package com.litografiaartesplanchas.servicesservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.litografiaartesplanchas.servicesservice.model.ServiceModel;
import com.litografiaartesplanchas.servicesservice.model.TypeService;

public interface IServiceRepository extends JpaRepository<ServiceModel, Long>{
	/**
	 * This function retrieves a list of ServiceModel objects based on the specified TypeService.
	 * 
	 * @param type The `findByTypeService` method is likely used to search for `ServiceModel` objects
	 * based on a specific `TypeService` parameter. The `type` parameter would be the specific type of
	 * service that you want to search for in the list of `ServiceModel` objects.
	 * @return A list of ServiceModel objects that match the specified TypeService.
	 */
	List<ServiceModel> findByTypeService(TypeService type);
	/**
	 * The function existsByName checks if an item with a specific name exists.
	 * 
	 * @param name The `existsByName` method takes a `String` parameter named `name`, which represents the
	 * name you want to check for existence. The method will return a boolean value indicating whether an
	 * entity with the given name exists or not.
	 * @return A boolean value is being returned.
	 */
	boolean existsByName(String name);
}
