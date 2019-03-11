package com.erp.model;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;
/**
 * @author Satyam Kumar
 *
 */
@Getter
@Setter
public abstract class BaseModel implements Serializable {

	protected static final long serialVersionUID = 1L;
	private String createdBy;
	private Date createdOn;
	private String modifyBy;
	private Date modifyOn;

}
