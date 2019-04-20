package com.erp.audit.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "api_response_log")
public class ServiceApiAuditLog {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "LogId", nullable = false, unique = true)
	private Long logId;
	@Column(name = "ApiName")
	private String apiName;
	@Column(name = "UserId")
	private Long userId;
	@Column(name = "StartTime")
	private Date startTime;
	@Column(name = "EndTime")
	private Date endTime;
	@Column(name = "DurationMilliSeconds")
	private Long durationMilliSeconds;
	@Column(name = "RequestHttpMethod")
	private String requestHttpMethod;
	@Column(name = "RequestUrl")
	private String requestUrl;
	@Column(name = "RequestBody")
	private String requestBody;
	@Column(name = "ResponseHttpCode")
	private int responseHttpCode;
	@Column(name = "ResponseBody")
	private String responseBody;
	@Column(name = "RequestHeader")
	private String requestHeader;
	@Column(name = "ResponseHeader")
	private String responseHeader;
	@Column(name = "ChannelType")
	private String channelType;
	
}
