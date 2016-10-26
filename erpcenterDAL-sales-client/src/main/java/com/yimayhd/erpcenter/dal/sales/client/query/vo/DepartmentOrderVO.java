package com.yimayhd.erpcenter.dal.sales.client.query.vo;

import java.io.Serializable;

public class DepartmentOrderVO implements Serializable {

	private Integer day1NumAdult;
	private Integer day1NumChild;
	private Integer day2NumAdult;
	private Integer day2NumChild;
	private Integer day3NumAdult;
	private Integer day3NumChild;
	private Integer day4NumAdult;
	private Integer day4NumChild;
	private Integer day5NumAdult;
	private Integer day5NumChild;
	private Integer day6NumAdult;
	private Integer day6NumChild;
	private Integer day7NumAdult;
	private Integer day7NumChild;
	private Integer numAdultTotal;
	private Integer numChildTotal;
	
	private Integer affirmOrderCount;
	private Integer reserveOrderCount;
	private Double rate;
	
	
	
	public Integer getDay1NumAdult() {
		return day1NumAdult;
	}
	public void setDay1NumAdult(Integer day1NumAdult) {
		this.day1NumAdult = day1NumAdult;
	}
	public Integer getDay1NumChild() {
		return day1NumChild;
	}
	public void setDay1NumChild(Integer day1NumChild) {
		this.day1NumChild = day1NumChild;
	}
	public Integer getDay2NumAdult() {
		return day2NumAdult;
	}
	public void setDay2NumAdult(Integer day2NumAdult) {
		this.day2NumAdult = day2NumAdult;
	}
	public Integer getDay2NumChild() {
		return day2NumChild;
	}
	public void setDay2NumChild(Integer day2NumChild) {
		this.day2NumChild = day2NumChild;
	}
	public Integer getDay3NumAdult() {
		return day3NumAdult;
	}
	public void setDay3NumAdult(Integer day3NumAdult) {
		this.day3NumAdult = day3NumAdult;
	}
	public Integer getDay3NumChild() {
		return day3NumChild;
	}
	public void setDay3NumChild(Integer day3NumChild) {
		this.day3NumChild = day3NumChild;
	}
	public Integer getDay4NumAdult() {
		return day4NumAdult;
	}
	public void setDay4NumAdult(Integer day4NumAdult) {
		this.day4NumAdult = day4NumAdult;
	}
	public Integer getDay4NumChild() {
		return day4NumChild;
	}
	public void setDay4NumChild(Integer day4NumChild) {
		this.day4NumChild = day4NumChild;
	}
	public Integer getDay5NumAdult() {
		return day5NumAdult;
	}
	public void setDay5NumAdult(Integer day5NumAdult) {
		this.day5NumAdult = day5NumAdult;
	}
	public Integer getDay5NumChild() {
		return day5NumChild;
	}
	public void setDay5NumChild(Integer day5NumChild) {
		this.day5NumChild = day5NumChild;
	}
	public Integer getDay6NumAdult() {
		return day6NumAdult;
	}
	public void setDay6NumAdult(Integer day6NumAdult) {
		this.day6NumAdult = day6NumAdult;
	}
	public Integer getDay6NumChild() {
		return day6NumChild;
	}
	public void setDay6NumChild(Integer day6NumChild) {
		this.day6NumChild = day6NumChild;
	}
	public Integer getDay7NumAdult() {
		return day7NumAdult;
	}
	public void setDay7NumAdult(Integer day7NumAdult) {
		this.day7NumAdult = day7NumAdult;
	}
	public Integer getDay7NumChild() {
		return day7NumChild;
	}
	public void setDay7NumChild(Integer day7NumChild) {
		this.day7NumChild = day7NumChild;
	}
	public Integer getAffirmOrderCount() {
		return affirmOrderCount;
	}
	public void setAffirmOrderCount(Integer affirmOrderCount) {
		this.affirmOrderCount = affirmOrderCount;
	}
	public Integer getReserveOrderCount() {
		return reserveOrderCount;
	}
	public void setReserveOrderCount(Integer reserveOrderCount) {
		this.reserveOrderCount = reserveOrderCount;
	}
	
	public Integer getNumAdultTotal() {
		return (this.day1NumAdult == null ? 0:this.day1NumAdult)+(this.day2NumAdult==null ? 0:this.day2NumAdult)+(this.day3NumAdult==null?0:this.day3NumAdult)+(this.day4NumAdult==null?0:this.day4NumAdult)+(this.day5NumAdult==null?0:this.day5NumAdult)+(this.day6NumAdult==null ? 0: this.day6NumAdult)+(this.day7NumAdult==null ? 0:this.day7NumAdult);
	}
	public Integer getNumChildTotal() {
		return (this.day1NumChild == null ? 0:this.day1NumChild)+(this.day2NumChild==null?0:this.day2NumChild)+(this.day3NumChild==null?0:this.day3NumChild)+(this.day4NumChild==null?0:this.day4NumChild)+(this.day5NumChild==null?0:this.day5NumChild)+(this.day6NumChild==null?0:this.day6NumChild)+(this.day7NumChild==null?0:this.day7NumChild);
	}
	public Double getRate() {
		if(affirmOrderCount+reserveOrderCount>0){
			return affirmOrderCount*1.00 / (affirmOrderCount+reserveOrderCount);
		}
		return null;
	}
	
	
	
}
