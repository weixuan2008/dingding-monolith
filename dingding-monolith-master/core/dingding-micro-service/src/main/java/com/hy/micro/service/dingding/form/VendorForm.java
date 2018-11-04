package com.hy.micro.service.dingding.form;
import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class VendorForm {
	private String vendorId;

	@NotEmpty(message = "The vendor name cannot be empty")
	private String vendorName;
	
	@NotEmpty(message = "The vendor address cannot be empty")
	private String vendorAddress;

	@NotEmpty(message = "The phone number cannot be empty")
	private String phoneNumber;

	@NotEmpty(message = "The vendor city cannot be empty")
	private String vendorCity;	
	
	@NotEmpty(message = "The vendor state cannot be empty")
	private String vendorState;

	@NotEmpty(message = "The vendor country cannot be empty")
	private String vendorCountry;

	private String vendorZipcode;

	private Integer vendorLevel;
}
