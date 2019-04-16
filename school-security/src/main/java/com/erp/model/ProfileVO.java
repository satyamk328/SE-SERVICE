package com.erp.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfileVO {
	private String user;
	private String status;
	private String type;
	private Object userContext;
	private String accessToken;
	private Long expiresIn;
	private String tokenType;
}