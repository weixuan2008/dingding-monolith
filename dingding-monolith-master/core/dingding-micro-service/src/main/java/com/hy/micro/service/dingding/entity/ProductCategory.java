package com.hy.micro.service.dingding.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * The category table for product.
 * 
 * @Author: Eddie.Wei
 * @Date: 2018-09-30
 * @github: https://github.com/weixuan2008
 */

@Data
@EqualsAndHashCode(callSuper=true)
@Entity
@Table(name = "product_category")
public class ProductCategory extends BaseEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "category_id")
	private Integer categoryId;

	@Column(name = "category_name", nullable = false)
	private String categoryName;

	@Column(name = "category_type", nullable = false)
	private Integer categoryType;
}
