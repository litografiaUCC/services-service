package com.litografiaartesplanchas.servicesservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.litografiaartesplanchas.servicesservice.model.Employee;

public interface IEmployeeRepository extends JpaRepository<Employee, Long> {

}
