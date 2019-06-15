package com.erp.user.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
@Table(name = "role_master")
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "RoleId", nullable = false, unique = true)
	private Long roleId;
	@Column(name="RoleName")
	private String roleName;
	@Column(name="Description")
	private String description;
	@Column(name="IsActive")
	private Boolean isActive;
	@Column(name="IsAdmin")
	private Boolean isAdmin;
	@OneToMany(cascade = CascadeType.ALL, mappedBy="roles")
    private List<User> users;
	
}