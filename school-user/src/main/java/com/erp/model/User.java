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
    private String loginId;
    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private Long phoneNumber;
    private String address;
    private String city;
    private String state;
	private Boolean isActive;
	private Boolean isLock;
	private String createdBy;
	private String modifiedBy;
	private Long roleId;
	private String description;
	
}