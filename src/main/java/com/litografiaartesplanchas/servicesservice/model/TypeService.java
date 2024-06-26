package com.litografiaartesplanchas.servicesservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * This Java class represents a TypeService entity with properties for id and name mapped to a database
 * table named "tipo_servicio".
 */
@Entity
@Table(name = "tipo_servicio")
public class TypeService {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_servicio")
	private int id;

	@Column(name = "nombre", nullable = false, length = 45)
	private String name;
	
	@Column(name = "descripcion", nullable = false, length = 255)
	private String description;
	
	@Column(name = "imagen", nullable = true, length = 255)
	private String image;

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

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
}
