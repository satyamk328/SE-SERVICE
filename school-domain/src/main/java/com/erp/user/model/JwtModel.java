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
	@Column(name = "TokenId", nullable = false, unique = true)
	private Long tokenId;
	@Column(name = "UserId", nullable = false)
	private Long userId;
	@Column(name = "Token", nullable = false)
	private String token;
	@Column(name="IssueTime")
	@Temporal(TemporalType.TIMESTAMP)
	private Date issueTime;
	@Column(name="ExpirationTime")
	@Temporal(TemporalType.TIMESTAMP)
	private Date expirationTime;
	@Column(name="Valid")
	private boolean valid;
	@Column(name="ChannelType")
	private String channelType;
	@Column(name = "CreatedBy")
	private String createdBy;
	@Column(name = "DateCreated")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateCreated;
	@Column(name = "ModifiedBy")
	private String modifiedBy;
	@Column(name = "DateModified")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateModified;
}
