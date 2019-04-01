package com.erp.spring.model;

import lombok.Getter;
import lombok.Setter;
/**
 * @author Satyam Kumar
 *
 */

@Getter
@Setter
public class RestStatus <T>{

    private String code;
    private String message;
    private String uniqueErrorId;
    private T messageCode;

    public RestStatus(final String statusCode, final String statusMessage) {
        this.code = statusCode;
        this.message = statusMessage;
    }

    public RestStatus() {

    }

    public RestStatus(final String statusCode, final String statusMessage, final String uniqueErrorId, final T messageCode) {
        this.code = statusCode;
        this.message = statusMessage;
        this.uniqueErrorId = uniqueErrorId;
        this.messageCode = messageCode;
    }

}
