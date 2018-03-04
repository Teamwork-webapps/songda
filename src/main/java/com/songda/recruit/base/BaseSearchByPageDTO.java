package com.songda.recruit.base;

import org.apache.commons.lang3.StringUtils;


public class BaseSearchByPageDTO {

	private String page = "1";
	private String rows = "20";
	private String orderType;
	private String sortCol;
	
	/**
	 * 获取页码，从1开始
	 * @return
	 */
	public String getPage() {
		return page;
	}
	/**
	 * 设置页码
	 * @param page
	 */
	public void setPage(String page) {
		if(StringUtils.isNotBlank(page)){
			this.page = page;
		}else{
			this.page = "1";
		}
	}
	/**
	 * 获取每页的行数
	 * @return
	 */
	public String getRows() {
		return rows;
	}
	/**
	 * 设置每页的行数
	 * @param rows
	 */
	public void setRows(String rows) {
		if(StringUtils.isNotBlank(rows)){
			this.rows = rows;
		}else{
			this.rows = "20";
		}
	}
	
	/**
	 * 获取排序设置 asc升序，desc降序，分号间隔每个排序字段的排序方式
	 * @return
	 */
	public String getOrderType() {
		return orderType;
	}
	
	/**
	 * 设置排序设置 asc升序，desc降序
	 * @param order
	 */
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	
	/**
	 * 获取排序的字段名称 如：id，分号间隔每个排序字段
	 * @return
	 */
	public String getSortCol() {
		return sortCol;
	}
	
	/**
	 * 设置排序的字段名称 如：id
	 * @param sort
	 */
	public void setSortCol(String sortCol) {
		this.sortCol = sortCol;
	}
	
}
