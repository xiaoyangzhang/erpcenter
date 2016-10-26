package com.yimayhd.erpcenter.dal.sales.client.finance.po;

import java.io.Serializable;
import java.math.BigDecimal;

public class InfoBean implements Serializable {

	private static final long serialVersionUID = 3213354858833128783L;

	private String id;
	private String name;
	private BigDecimal num = new BigDecimal(0);
	private Boolean isAudited;
	private String css;
	private int auditedNum;
	private int count;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getNum() {
		return num;
	}

	public void setNum(BigDecimal num) {
		this.num = num;
	}

	public Boolean getIsAudited() {
		return auditedNum != 0 && auditedNum == count;
	}

	public String getCss() {
		return css;
	}

	public void setCss(String css) {
		this.css = css;
	}

	public int getAuditedNum() {
		return auditedNum;
	}

	public void setAuditedNum(int auditedNum) {
		this.auditedNum = auditedNum;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
}