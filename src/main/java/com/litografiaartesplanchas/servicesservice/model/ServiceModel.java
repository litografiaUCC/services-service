package com.litografiaartesplanchas.servicesservice.model;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "servicio")
public class ServiceModel {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_servicio")
	private int id;
	
	@NotBlank
	@NotNull
	@Column(name = "nombre", nullable = false , length = 100, unique = true)
	private String name;

	@Column(name = "descripcion", nullable = true, columnDefinition = "MEDIUMTEXT")
	private String description;
	
	@Column(name = "precio")
	private double price;
	
	@Column(name = "foto", nullable = true, length = 255)
	private String picture;
	
	@NotBlank
	@ManyToOne
	@JoinColumn(name = "id_tipo_servicio", nullable = false)
	private TypeService typeService;

	@ManyToMany(mappedBy = "service", cascade = CascadeType.ALL)
    private Set<ServiceMaterial> serviceMaterials;
	
	@JoinTable(
	        name = "servicio_has_empleado",
	        joinColumns = @JoinColumn(name = "id_servicio", nullable = false),
	        inverseJoinColumns = @JoinColumn(name="id_empleado", nullable = false)
	 )
	 @ManyToMany()
	 private Set<Employee> employees;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public TypeService getTypeService() {
		return typeService;
	}

	public void setTypeService(TypeService typeService) {
		this.typeService = typeService;
	}

	public Set<ServiceMaterial> getServiceMaterials() {
		return serviceMaterials;
	}

	public void setServiceMaterials(Set<ServiceMaterial> serviceMaterials) {
		this.serviceMaterials = serviceMaterials;
	}

	public Set<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(Set<Employee> employees) {
		this.employees = employees;
	}
	
	
}
