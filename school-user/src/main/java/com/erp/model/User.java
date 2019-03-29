package com.erp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private Long userId;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String emailId;
	private Boolean isActive;
	private String createdBy;
	private String modifiedBy;
	private Long roleId;
	private String description;
	
}