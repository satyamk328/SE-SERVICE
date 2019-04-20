package com.erp.auth.model;

import java.util.List;

import com.erp.user.model.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfileVO {
	private User userContext;
	private List<String> manuList;
	private String status;
	private String type;
	private String accessToken;
	private Long expiresIn;
	private String tokenType;
}