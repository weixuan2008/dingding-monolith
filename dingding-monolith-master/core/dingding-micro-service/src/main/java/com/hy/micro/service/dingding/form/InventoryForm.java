package com.hy.micro.service.dingding.form;

import java.math.BigDecimal;

import javax.validation.constraints.NotEmpty;
import lombok.Data;

/**
 * The form data of front-end for inventory.
 * 
 * @Author: Eddie.Wei
 * @Date: 2018-10-08
 * @github: https://github.com/weixuan2008
 */

@Data
public class InventoryForm {
	/** product id **/
	private String productId;

	/** product category **/
	@NotEmpty(message = "The product category cannot be empty")
	private String productCategory;
	
	/** vendor id **/
	@NotEmpty(message = "The vendor id cannot be empty")
	private String vendorId;

	/** product price **/
	@NotEmpty(message = "The product base price cannot be empty")
	private BigDecimal basePrice;

	/** product sku **/
	@NotEmpty(message = "The product sku cannot be empty")
	private Integer sku;	
}
