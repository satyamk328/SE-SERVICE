package com.erp.audit.model;

import java.util.Date;

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
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "user_jwt_token")
public class JwtModel {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "TokenId")
	private Long tokenId;
	@Column(name = "Token")
	private String token;
	@Column(name = "IssueTime")
	private Date issueTime;
	@Column(name = "ExpirationTime")
	private Date expirationTime;
	@Column(name = "userId")
	private Long userId;
	@Column(name = "Valid")
	private boolean valid;
	@Column(name = "ChannelType")
	private String channelType;

}
