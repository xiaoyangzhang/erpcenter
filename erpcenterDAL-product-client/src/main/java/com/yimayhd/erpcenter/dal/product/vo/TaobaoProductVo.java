package com.yimayhd.erpcenter.dal.product.vo;

import java.io.Serializable;

public class TaobaoProductVo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3073238144193639412L;

     private Integer taobaoProductId;
	 private Integer SkusId;
	 private String outerId;
	 private String title;
	 private String pidName;
	 private String myStoreId;
	 
	public String getOuterId() {
		return outerId;
	}
	public void setOuterId(String outerId) {
		this.outerId = outerId;
	}
	public Integer getTaobaoProductId() {
		return taobaoProductId;
	}
	public void setTaobaoProductId(Integer taobaoProductId) {
		this.taobaoProductId = taobaoProductId;
	}
	public Integer getSkusId() {
		return SkusId;
	}
	public void setSkusId(Integer skusId) {
		SkusId = skusId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPidName() {
		return pidName;
	}
	public void setPidName(String pidName) {
		this.pidName = pidName;
	}
	public String getMyStoreId() {
		return myStoreId;
	}
	public void setMyStoreId(String myStoreId) {
		this.myStoreId = myStoreId;
	}
	 
	 
}
