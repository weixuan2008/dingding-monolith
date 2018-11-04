package com.hy.micro.service.dingding.utils;

import lombok.Data;

/**
 * 
 * @Description: TODO
 * @Author: Eddie.Wei   
 * @Date: Oct 29, 2018 
 * @version:
 * @github: https://github.com/weixuan2008
 */

@Data
public class PageUtil {

	private int dataCount;
	private int currentPage;
	private int pageCount;
	private int pageSize;
	private String pageUrl;

	public PageUtil() {

	}

	public PageUtil(int dataCount, int currentPage, int pageCount, int pageSize, String pageUrl) {
		super();
		this.dataCount = dataCount;
		this.currentPage = currentPage;
		this.pageCount = pageCount;
		this.pageSize = pageSize;
		this.pageUrl = pageUrl;
	}
}
