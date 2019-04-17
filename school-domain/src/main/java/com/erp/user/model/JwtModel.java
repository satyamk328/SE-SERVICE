package com.erp.user.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class JwtModel {

    private Long consumerTokenId;
    private Long consumerId;
    private String token;
    private Date issueTime;
    private Date expirationTime;
    private boolean valid;
    private String channelName;
    private String channelType;

}
