package com.hy.micro.service.dingding.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.hy.micro.service.dingding.enums.GenderType;
import com.hy.micro.service.dingding.enums.UserRole;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * The user information table.
 * 
 * @Author: Eddie.Wei
 * @Date: 2018-09-30
 * @github: https://github.com/weixuan2008
 */

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "user_info")
public class UserInfo extends BaseEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "user_id")
	private String userId;

	@Column(name = "user_name", nullable = false)
	private String username;

	@Column(name = "password", nullable = false)
	private String password;

	@Column(name = "gender", nullable = false)
	private GenderType gender;

	@Column(name = "phone", nullable = false)
	private String phone;

	@Column(name = "country", nullable = false)
	private String country;

	@Column(name = "province", nullable = false)
	private String province;

	@Column(name = "city", nullable = false)
	private String city;

	@Column(name = "address", nullable = false)
	private String address;

	@Column(name = "email", nullable = false)
	private String email;

	/** token id from weichat **/
	@Column(name = "open_id")
	private String openId;

	@Column(name = "user_role", nullable = false)
	private UserRole userRole;
}
