package org.erpcenterFacade.common.client.result;

import java.io.Serializable;

public class CheckProductStockResult implements Serializable {

	/**
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	*/
	private static final long serialVersionUID = 1L;
	
	private String sb;
	private String sqlSb;
	
	public String getSb() {
		return sb;
	}
	public void setSb(String sb) {
		this.sb = sb;
	}
	public String getSqlSb() {
		return sqlSb;
	}
	public void setSqlSb(String sqlSb) {
		this.sqlSb = sqlSb;
	}
}
