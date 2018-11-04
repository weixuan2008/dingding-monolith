package com.hy.micro.service.dingding.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "inventory_vendor")
public class Vendor extends BaseEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "vendor_id")
	private String vendorId;

	@Column(name = "vendor_name", nullable = false)
	private String vendorName;

	@Column(name = "vendor_address", nullable = false)
	private String vendorAddress;

	@Column(name = "phone_number", nullable = false)
	private String phoneNumber;

	@Column(name = "vendor_city", nullable = false)
	private String vendorCity;

	@Column(name = "vendor_state", nullable = false)
	private String vendorState;

	@Column(name = "vendor_country", nullable = false)
	private String vendorCountry;

	@Column(name = "vendor_zipcode", nullable = false)
	private String vendorZipcode;

	@Column(name = "vendor_level", nullable = false)
	private Integer vendorLevel;
}
