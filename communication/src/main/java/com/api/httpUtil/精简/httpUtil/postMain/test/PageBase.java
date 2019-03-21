package com.api.httpUtil.精简.httpUtil.postMain.test;

public class PageBase {
	private int pageSize;// 页面显示的条数,例如 每页显示15条数据
	private int pageStart;// 第几页 , 例如 现在第一页
	private int pageSum; // 数据总条数

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageStart() {
		return pageStart;
	}

	public void setPageStart(int pageStart) {
		this.pageStart = pageStart;
	}

	public int getPageSum() {
		return pageSum;
	}

	public void setPageSum(int pageSum) {
		this.pageSum = pageSum;
	}
}
