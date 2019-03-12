package com.erp.audit.model;

import java.util.Date;

import com.erp.utils.DateTimeDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CentralizedLogs {
	private String appName;
	private String logLevel;
	private Date logTimeStamp;
	private String logMessage;

	@JsonDeserialize(using = DateTimeDeserializer.class)
	public void setLogTimeStamp(final Date logTimeStamp) {
		this.logTimeStamp = logTimeStamp;
	}

}
