package com.erp.user.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "privilege_master")
public class RolePrivilege {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "LoginId", nullable = false, unique = true)
	private Long privilegeId;
	@Column(name = "PrivilegeName")
	private String privilegeName;
	@Column(name = "Description")
	private String description;
	
}
