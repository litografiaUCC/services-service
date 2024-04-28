package com.litografiaartesplanchas.servicesservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.litografiaartesplanchas.servicesservice.model.Material;

public interface IMaterialRepository extends JpaRepository<Material, Long> {

}
