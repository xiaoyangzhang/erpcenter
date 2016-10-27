package com.yimayhd.erpcenter.facade.supplier.result;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.dal.basic.po.DicInfo;
import com.yimayhd.erpcenter.dal.basic.po.RegionInfo;

/**
 * 导游列表结果
 * @author liyong
 * 2016年10月25日 描述：
 */
public class GuideListResult extends ResultSupport implements Serializable {
	
	/**
	 * @author liyong
	 * 2016年10月25日 描述：
	 */
	private static final long serialVersionUID = 1L;
	private PageBean pageBean = new PageBean();
	private List<RegionInfo> allProvince =new ArrayList<RegionInfo>();
	private List<DicInfo> djList = new  ArrayList<DicInfo>();
	
	private String images_source;
	public PageBean getPageBean() {
		return pageBean;
	}
	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}
	public List<RegionInfo> getAllProvince() {
		return allProvince;
	}
	public void setAllProvince(List<RegionInfo> allProvince) {
		this.allProvince = allProvince;
	}
	public String getImages_source() {
		return images_source;
	}
	public void setImages_source(String images_source) {
		this.images_source = images_source;
	}
	public List<DicInfo> getDjList() {
		return djList;
	}
	public void setDjList(List<DicInfo> djList) {
		this.djList = djList;
	}
	
	

}
