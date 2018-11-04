package com.hy.micro.service.dingding.form;

import javax.validation.constraints.NotEmpty;
import lombok.Data;

/**
 * The form data of front-end for product category.
 * 
 * @Author: Eddie.Wei
 * @Date: 2018-10-08
 * @github: https://github.com/weixuan2008
 */

@Data
public class CategoryForm {
	/** category id with auto increasing **/
	private Integer categoryId;

	/** category name **/
	@NotEmpty(message = "The catetory cannot be empty")
	private String categoryName;

	/*** category type */
	private Integer categoryType;
}
