package com.erp.user.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name="user_login")
public class Login {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "LoginId", nullable = false, unique = true)
	private Long loginId;
	@Column(name = "UserId", nullable = false)
	private Long userId;
	@Column(name = "UserName")
	private String userName;
	@Column(name = "Address")
	private String address;
	@Column(name = "City")
	private String city;
	@Column(name = "State")
	private String state;
	@Column(name = "SessionId")
	private String sessionId;
	@Column(name = "LoginDate")
	@Temporal(TemporalType.TIMESTAMP)
	private Date loginDate;
	@Column(name = "LogoutDate")
	@Temporal(TemporalType.TIMESTAMP)
	private Date logoutDate;
	@Column(name = "ClientIp")
	private String clientIp;
	@Column(name = "ClientHost")
	private String clientHost;
}
