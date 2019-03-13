package com.erp.user.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Type;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Satyam Kumar
 *
 */
@Setter
@Getter
@Entity
@Table(name = "user_master", uniqueConstraints = { @UniqueConstraint(columnNames = { "UserName" }),
		@UniqueConstraint(columnNames = { "Email" }) })
public class User extends BaseModel implements Serializable {

	private static final long serialVersionUID = 8773592091012906066L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "UserId", nullable = false, unique = true)
	private long userId;
	@Column(name = "UserName", length = 500, updatable = false)
	private String userName;
	@Column(name = "FirstName", length = 500)
	private String firstName;
	@Column(name = "LastName", length = 500)
	private String lastName;
	@Column(name = "Email", length = 500)
	private String email;
	@Column(name = "Address", length = 500)
	private String address;
	@Column(name = "PhoneNumber", length = 500)
	private String phoneNumber;
	@Column(name = "PanNumber", length = 500)
	private String panNumber;
	@Column(name = "Password", length = 500)
	private String password;
	@Column(name = "City", length = 500)
	private String city;
	@Column(name = "State", length = 500)
	private String state;
	@Column(name = "Country", length = 500)
	private String country;
	@Column(name = "IsLock", nullable = false)
	private boolean isLock = false;
	@Column(name = "Attempt")
	private Integer attempt;
	@Column(name = "IsActive", nullable = false)
	private boolean isActive = false;
	@Column(name = "OTP", length = 500)
	private String otp;

}