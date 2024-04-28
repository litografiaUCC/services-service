package com.litografiaartesplanchas.servicesservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.litografiaartesplanchas.servicesservice.model.ServiceMaterial;

public interface IServiceMaterialRepository extends JpaRepository<ServiceMaterial, Long> {

}
