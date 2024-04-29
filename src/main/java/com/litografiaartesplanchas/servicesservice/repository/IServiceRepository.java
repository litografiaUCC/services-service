package com.litografiaartesplanchas.servicesservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.litografiaartesplanchas.servicesservice.model.ServiceModel;
import com.litografiaartesplanchas.servicesservice.model.TypeService;

public interface IServiceRepository extends JpaRepository<ServiceModel, Long>{
	List<ServiceModel> findByTypeService(TypeService type);
	boolean existsByName(String name);
}
