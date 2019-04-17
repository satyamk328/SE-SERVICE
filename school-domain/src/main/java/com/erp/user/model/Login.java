package com.erp.user.model;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Login {

	private Long loginId;
	private Long userId;
	private String userName;
	private String address;
	private String city;
	private String state;
	private String sessionId;
	private Date loginDate;
	private Date logoutDate;
	private String clientIp;
	private String clientHost;
}
