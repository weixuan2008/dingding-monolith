package com.hy.micro.service.dingding.exception;

import com.hy.micro.service.dingding.enums.ExceptionCode;

import lombok.Getter;

/**
 * 
 * @Author: Eddie.Wei   
 * @Date:   2018-09-30 
 * @github: https://github.com/weixuan2008
 */
@Getter
public class BloomingException extends RuntimeException {
	private static final long serialVersionUID = 1;
	private Integer code;

	
	public BloomingException(ExceptionCode exceptionCodes) {
		super(exceptionCodes.getMsg());
		this.code = exceptionCodes.getCode();
	}

	public BloomingException(ExceptionCode exceptionCodes, String msg) {
		super(msg);
		this.code = exceptionCodes.getCode();
	}
}
