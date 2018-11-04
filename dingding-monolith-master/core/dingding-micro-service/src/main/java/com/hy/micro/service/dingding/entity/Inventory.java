package com.hy.micro.service.dingding.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "inventory_master")
public class Inventory extends BaseEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="product_id")
	private String productId;
	
	@Column(name="product_category")
	private String productCategory;
	
	@Column(name="vendor_id", nullable=false)
	private String vendorId;
	
	@Column(name="base_price", nullable=false)
	private BigDecimal basePrice;
	
	@Column(name="product_sku", nullable=false)
	private Integer sku;
}
