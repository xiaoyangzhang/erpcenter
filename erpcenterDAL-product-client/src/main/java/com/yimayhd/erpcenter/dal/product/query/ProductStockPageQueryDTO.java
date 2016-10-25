package com.yimayhd.erpcenter.dal.product.query;

import java.io.Serializable;
import java.util.Date;

import com.yimayhd.erpcenter.common.query.PageQuery;

public class ProductStockPageQueryDTO extends PageQuery implements Serializable{
	
	private static final long serialVersionUID = 1514932164244446916L;
	

	 private Integer infoBizId;//商家id--------可选
	 private Integer infoBrandId;//名称-品牌--------
	 private String infoNameCity;//名称-城市----------like
	 private Byte infoState;//产品状态,1待上架2上架3下架-1删除--------必须!=-1
	 private Integer prProductId;//pr表产品id
	 private Date psItemDateStart;//ps表时间-------必须
	 private Date psItemDateEnd;//ps表时间-------必须
	 private Integer psState;//ps表状态1正常 -1删除--------必须!=-1
	 private String prOrgId;//pr表机构id--------必须
	 private String stockInfo;//pr表机构id--------必须
	/**
	 * @return the infoBizId
	 */
	public Integer getInfoBizId() {
		return infoBizId;
	}
	/**
	 * @param infoBizId the infoBizId to set
	 */
	public void setInfoBizId(Integer infoBizId) {
		this.infoBizId = infoBizId;
	}
	/**
	 * @return the infoBrandId
	 */
	public Integer getInfoBrandId() {
		return infoBrandId;
	}
	/**
	 * @param infoBrandId the infoBrandId to set
	 */
	public void setInfoBrandId(Integer infoBrandId) {
		this.infoBrandId = infoBrandId;
	}
	/**
	 * @return the infoNameCity
	 */
	public String getInfoNameCity() {
		return infoNameCity;
	}
	/**
	 * @param infoNameCity the infoNameCity to set
	 */
	public void setInfoNameCity(String infoNameCity) {
		this.infoNameCity = infoNameCity;
	}
	/**
	 * @return the infoState
	 */
	public Byte getInfoState() {
		return infoState;
	}
	/**
	 * @param infoState the infoState to set
	 */
	public void setInfoState(Byte infoState) {
		this.infoState = infoState;
	}
	/**
	 * @return the prProductId
	 */
	public Integer getPrProductId() {
		return prProductId;
	}
	/**
	 * @param prProductId the prProductId to set
	 */
	public void setPrProductId(Integer prProductId) {
		this.prProductId = prProductId;
	}
	/**
	 * @return the psItemDateStart
	 */
	public Date getPsItemDateStart() {
		return psItemDateStart;
	}
	/**
	 * @param psItemDateStart the psItemDateStart to set
	 */
	public void setPsItemDateStart(Date psItemDateStart) {
		this.psItemDateStart = psItemDateStart;
	}
	/**
	 * @return the psItemDateEnd
	 */
	public Date getPsItemDateEnd() {
		return psItemDateEnd;
	}
	/**
	 * @param psItemDateEnd the psItemDateEnd to set
	 */
	public void setPsItemDateEnd(Date psItemDateEnd) {
		this.psItemDateEnd = psItemDateEnd;
	}
	/**
	 * @return the psState
	 */
	public Integer getPsState() {
		return psState;
	}
	/**
	 * @param psState the psState to set
	 */
	public void setPsState(Integer psState) {
		this.psState = psState;
	}
	/**
	 * @return the prOrgId
	 */
	public String getPrOrgId() {
		return prOrgId;
	}
	/**
	 * @param prOrgId the prOrgId to set
	 */
	public void setPrOrgId(String prOrgId) {
		this.prOrgId = prOrgId;
	}
	 
	 
	 

}
