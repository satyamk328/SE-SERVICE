package com.erp.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Satyam Kumar
 *
 */
@Setter
@Getter
@Entity
@Table(name = "user_master", uniqueConstraints = { @UniqueConstraint(columnNames = { "userName" }),
		@UniqueConstraint(columnNames = { "email" }) })
public class User extends BaseModel implements Serializable {

	private static final long serialVersionUID = 8773592091012906066L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "userId", nullable = false, unique = true)
	private long userId;
	@Column(name = "userName", length = 500, updatable = false)
	private String userName;
	@Column(name = "firstName", length = 500)
	private String firstName;
	@Column(name = "lastName", length = 500)
	private String lastName;
	@Column(name = "Email", length = 500)
	private String email;
	@Column(name = "Address", length = 500)
	private String address;
	@Column(name = "PhoneNumber", length = 500)
	private String phoneNumber;
	@Column(name = "PanNumber", length = 500)
	private String panNumber;
	@Column(name = "password", length = 500)
	private String password;
	@Column(name = "city", length = 500)
	private String city;
	@Column(name = "state", length = 500)
	private String state;
	@Column(name = "Country", length = 500)
	private String country;
	@Column(name = "IsLock")
	private boolean isLock = false;
	@Column(name = "Attempt")
	private Integer attempt;
	@Column(name = "IsActive")
	private boolean isActive = false;
	@Column(name = "otp", length = 500)
	private String otp;

}