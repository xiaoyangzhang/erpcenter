package com.yimayhd.erpcenter.facade.supplier.result;

import java.io.Serializable;
import java.util.List;

import com.yimayhd.erpcenter.dal.basic.po.DicInfo;
/**
 * 客户咨询登记 返回结果封装
 * @author liyong
 * 2016年10月25日 描述：
 */
public class ConsultGuestListResult implements Serializable{
	/**
	 * @author liyong
	 * 2016年10月25日 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 意向游玩
	 */
	private List<DicInfo> intentionDestList;
	/**
	 * 信息渠道
	 */
	private List<DicInfo> infoSourceList;
	/**
	 * @return getIntentionDestList获取 intentionDestList 的值
	 */
	public List<DicInfo> getIntentionDestList() {
		return intentionDestList;
	}
	/**
	 * @param setIntentionDestList 设置  intentionDestList 的值
	 */
	public void setIntentionDestList(List<DicInfo> intentionDestList) {
		this.intentionDestList = intentionDestList;
	}
	/**
	 * @return getInfoSourceList获取 infoSourceList 的值
	 */
	public List<DicInfo> getInfoSourceList() {
		return infoSourceList;
	}
	/**
	 * @param setInfoSourceList 设置  infoSourceList 的值
	 */
	public void setInfoSourceList(List<DicInfo> infoSourceList) {
		this.infoSourceList = infoSourceList;
	}
	
	

}
