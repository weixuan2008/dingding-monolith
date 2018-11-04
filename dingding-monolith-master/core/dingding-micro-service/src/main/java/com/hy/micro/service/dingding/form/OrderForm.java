package com.hy.micro.service.dingding.form;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

/**
 * The form data of front-end for order.
 * 
 * @Author: Eddie.Wei
 * @Date: 2018-10-08
 * @github: https://github.com/weixuan2008
 */

@Data
public class OrderForm {
	@NotEmpty(message = "The name cannot be empty")
	private String name;
	
	@NotEmpty(message = "The phone cannot be empty")
	private String phone;
	
	@NotEmpty(message = "The address cannot be empty")
	private String address;
	
	@NotEmpty(message = "The openid cannot be empty")
	private String openid;
	
	@NotEmpty(message = "The products cannot be empty")
	private String items;
}
