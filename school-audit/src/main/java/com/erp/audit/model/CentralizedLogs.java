package com.erp.audit.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.erp.utils.DateTimeDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "central_logs")
public class CentralizedLogs {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "LogId", nullable = false, unique = true)
	private Long logId;
	@Column(name = "UserId")
	private Long userId;
	@Column(name = "AppName")
	private String appName;
	@Column(name = "LogLevel")
	private String logLevel;
	@Column(name = "LogTimeStamp")
	private Date logTimeStamp;
	@Column(name = "LogMessage")
	private String logMessage;
	

	@JsonDeserialize(using = DateTimeDeserializer.class)
	public void setLogTimeStamp(final Date logTimeStamp) {
		this.logTimeStamp = logTimeStamp;
	}

}
