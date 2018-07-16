package com.yhz.framework.web;

public class WebModel {
public final static int PAGE_SHOW = 2;
	
	private String submitFlag = "";
	private int nowPage = 1;
	private int count = 0;
	private int maxPage = 1;
	
	public String getSubmitFlag() {
		return submitFlag;
	}

	public void setSubmitFlag(String submitFlag) {
		this.submitFlag = submitFlag;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getMaxPage() {
		if(this.count > 0 ){
			this.maxPage =  (int)Math.ceil(this.count*1.0/this.PAGE_SHOW);
		}
		
		return maxPage;
	}
	public void setMaxPage(int maxPage) {
		this.maxPage = maxPage;
	}
	public int getNowPage() {
		return nowPage;
	}
	public void setNowPage(int nowPage) {
		this.nowPage = nowPage;
	}
	
	
	////////////
	public int getFromNum(){
		return (nowPage-1)*this.PAGE_SHOW;
	}
}
