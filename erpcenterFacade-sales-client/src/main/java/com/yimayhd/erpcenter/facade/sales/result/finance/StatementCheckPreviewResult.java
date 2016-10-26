/**
 * 
 */
package com.yimayhd.erpcenter.facade.sales.result.finance;

import java.io.Serializable;
import java.util.Map;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.facade.sales.result.ResultSupport;

/**
 * @ClassName: StatementCheckPreviewResult
 * @author hongfei.guo
 * @date 2016年10月19日
 */
public class StatementCheckPreviewResult extends ResultSupport implements Serializable {

	private static final long serialVersionUID = -5895100754940754874L;

	private String imgPath;
	private String printName;
	private PageBean pageBean;
	private Map<Integer, String> guideMap;
	
	public String getImgPath() {
		return imgPath;
	}
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
	public String getPrintName() {
		return printName;
	}
	public void setPrintName(String printName) {
		this.printName = printName;
	}
	public PageBean getPageBean() {
		return pageBean;
	}
	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}
	public Map<Integer, String> getGuideMap() {
		return guideMap;
	}
	public void setGuideMap(Map<Integer, String> guideMap) {
		this.guideMap = guideMap;
	}
}
