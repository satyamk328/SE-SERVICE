package com.erp.user.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Satyam Kumar
 *
 */
@Setter
@Getter
@Entity
@Table(name = "user_login")
public class Login implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "loginId", unique = true, nullable = false)
	private String id;
	@Column(name = "userId")
	private String uid;
	@Column(name = "userName")
	private String name;
	@Column(name = "state")
	private String state;
	@Column(name = "sessionId", length = 500)
	private String sessionId;
	@Column(name = "loginDate")
	@Temporal(TemporalType.TIMESTAMP)
	private Date date = new Date();
	@Column(name = "address", length = 500)
	private String address;
	@Column(name = "logoutDate")
	@Temporal(TemporalType.TIMESTAMP)
	private Date logoutd;
	@Column(name = "clientIp", length = 500)
	private String clientIP;
	@Column(name = "clientHost", length = 500)
	private String chost;

}
