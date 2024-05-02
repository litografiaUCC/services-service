package com.litografiaartesplanchas.servicesservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * The Employee class represents an entity with attributes such as id, name, lastName, email, password,
 * phone, photo, numberDocument, isActive, typeDocument, and role.
 */
@Entity
@Table(name = "empleado")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_empleado")
    private int id;
    
    @Column(name = "nombre", nullable = false , length = 100)
    private String name;

    @Column(name = "apellido", nullable = false , length = 100)
    private String lastName;
	
	@Column(name = "correo", nullable = false , length = 255, unique = true)
	private String email;
	
	@Column(name = "contrasena", nullable = false , length = 255)
	private String password;
	
	@Column(name = "telefono", nullable = true , length = 30)
	private String phone;
	
	@Column(name = "foto", nullable = true , length = 255)
	private String photo;
	
	@Column(name = "numero_documento", nullable = false, unique = true)
	private String numberDocument;

	@Column(name = "activo", columnDefinition = "BOOLEAN DEFAULT true")
	private boolean isActive;

	@ManyToOne
	@JoinColumn(name = "id_tipo_documento")
	private TypeDocument typeDocument;

    @ManyToOne
    @JoinColumn(name = "id_rol")
    private Role role;

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

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

}
