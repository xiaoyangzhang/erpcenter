package com.yimayhd.erpcenter.facade.supplier.result;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.dal.basic.po.DicInfo;
import com.yimayhd.erpresource.dal.vo.SupplierCarVO;
/**
 * 供应商汽车列表返回结果
 * @author liyong
 * 2016年10月25日 
 */
public class SupplierCarListResult extends ResultSupport implements Serializable {
	
	private PageBean pageBean = new PageBean();
	
	private List<SupplierCarVO> voList = new ArrayList<SupplierCarVO>();
	
	private List<DicInfo> dicInfos = new ArrayList<DicInfo>();

	/**
	 * @return getPageBean获取 pageBean 的值
	 */
	public PageBean getPageBean() {
		return pageBean;
	}

	/**
	 * @param setPageBean 设置  pageBean 的值
	 */
	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}

	/**
	 * @return getVoList获取 voList 的值
	 */
	public List<SupplierCarVO> getVoList() {
		return voList;
	}

	/**
	 * @param setVoList 设置  voList 的值
	 */
	public void setVoList(List<SupplierCarVO> voList) {
		this.voList = voList;
	}

	/**
	 * @return getDicInfos获取 dicInfos 的值
	 */
	public List<DicInfo> getDicInfos() {
		return dicInfos;
	}

	/**
	 * @param setDicInfos 设置  dicInfos 的值
	 */
	public void setDicInfos(List<DicInfo> dicInfos) {
		this.dicInfos = dicInfos;
	}
	
	

}
