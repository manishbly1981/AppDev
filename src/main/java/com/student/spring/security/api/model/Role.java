package com.student.spring.security.api.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Role {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private int roleId;
	
	private String role;
}
