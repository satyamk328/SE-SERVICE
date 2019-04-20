package com.erp.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.erp.utils.GlobalConstants;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ChannelService {

	public String getChannelType() {
		HttpServletRequest request = BaseService.getHttpServletRequest();
		if (request != null) {
			return request.getHeader(GlobalConstants.CHANNEL_TYPE);
		}
		log.debug("Successfully Fetched the Channel Header Details");
		return null;
	}
	
}
