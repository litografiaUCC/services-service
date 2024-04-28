package com.litografiaartesplanchas.servicesservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.litografiaartesplanchas.servicesservice.model.ServiceModel;

public interface IServiceRepository extends JpaRepository<ServiceModel, Long>{

}
