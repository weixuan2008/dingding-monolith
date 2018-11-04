package com.hy.micro.service.dingding.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * The encapsulated data that will return to front-end.
 * 
 * @Author: Eddie.Wei
 * @Date: 2018-10-09
 * @github: https://github.com/weixuan2008
 */

@Data
@EqualsAndHashCode(callSuper = false)
public class ResultVO<T> extends BaseVO {
	private static final long serialVersionUID = 1L;

	/** code 0 means success **/
	private Integer code;

	/** The description for status code **/
	private String msg;

	/** The returned data **/
	private T data;
}
