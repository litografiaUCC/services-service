package com.litografiaartesplanchas.servicesservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * The `ServiceMaterial` class represents a relationship between a service and a material with a
 * quantity in a database table.
 */
@Entity
@Table(name = "servicio_has_material")
public class ServiceMaterial {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_service_has_material")
    private int id;
	
	@ManyToOne
	@JoinColumn(name = "id_servicio", nullable = false)
	@JsonIgnore
	private ServiceModel service;
	
	@ManyToOne
	@JoinColumn(name = "id_material", nullable = false)
	private Material material;
	
	@Column(name = "cantidad", nullable = false)
    private int quantity;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ServiceModel getService() {
		return service;
	}

	public void setService(ServiceModel service) {
		this.service = service;
	}

	public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
