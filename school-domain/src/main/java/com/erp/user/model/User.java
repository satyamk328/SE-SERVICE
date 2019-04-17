package com.erp.user.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_master")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "UserId", nullable = false, unique = true)
	private Long userId;
	@Column(name="FirstName")
	private String firstName;
	@Column(name="LastName")
	private String lastName;
	@Column(name="Password")
	private String password;
	@Column(name="Email")
	private String email;
	@Column(name="PhoneNumber")
	private Long phoneNumber;
	@Column(name="Address")
	private String address;
	@Column(name="City")
	private String city;
	@Column(name="State")
	private String state;
	@Column(name="Country")
	private String country;
	@Column(name="IsActive")
	private Boolean isActive;
	@Column(name="IsLock")
	private Boolean isLock;
	@Column(name="CreatedBy")
	private String createdBy;
	@Column(name="DateCreated")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateCreated;
	@Column(name="ModifiedBy")
	private String modifiedBy;
	@Column(name="DateModified")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateModified;
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RoleId",nullable = false)
	private Role role;

}