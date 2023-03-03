package com.student.spring.security.api.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.student.spring.security.api.config.AttributeEncrypter;

import lombok.Data;


@Entity
@Data
public class User {
	@Id
	@GeneratedValue(strategy= GenerationType.SEQUENCE)
	@Column(name="userId")
	private int userId;

	@Column(name="email", unique = true, nullable= false, columnDefinition = "varchar (255)")
	private String email;
	
	@Column(name="firstName", nullable= false, columnDefinition = "varchar (255)")
	private String firstName;
	
	@Column(name="lastName", nullable= false, columnDefinition = "varchar (255)")
	private String lastName;
	
	@Convert(converter= AttributeEncrypter.class)
	@Column(name="password", nullable= false, columnDefinition = "varchar (255)")
	private String password;
	
	@Convert(converter= AttributeEncrypter.class)
	@Column(name="authenticationCode", columnDefinition = "varchar (255) Default '1234'")
	private String authenticationCode;
	
	@Column(name= "status", columnDefinition = "varchar (10) Default 'InActive'")
	private String status;//Active/InActive
	
	@Column(name= "invalidTry", columnDefinition= "integer Default 0")
	private int invalidTry;// After 3 retry locked
	
	@Column(name= "isLocked", columnDefinition = "boolean Default false")
	private boolean isLocked;
	
	@ManyToMany(cascade=CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name="userRole", joinColumns = @JoinColumn(name="userId"), inverseJoinColumns = @JoinColumn(name="roleId"))
	private Set<Role> roles;	
}