/**
 * 
 */
package com.yimayhd.erpcenter.facade.result;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.dal.basic.po.DicInfo;
import com.yimayhd.erpcenter.dal.basic.po.RegionInfo;

/**
 * @ClassName: ProductInfoResult
 * @Description: 产品价格列表
 * @author hongfei.guo
 * @date 2016年10月19日
 */
public class ProductPriceListResult extends ResultSupport implements Serializable {

	/**
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	*/
	private static final long serialVersionUID = 6636674666861206380L;
	
	private List<RegionInfo> allProvince;
	private List<DicInfo> brandList;
	private PageBean page;
	private Integer pageNum;
	private Map<Integer, String> priceStateMap;
	
	public List<RegionInfo> getAllProvince() {
		return allProvince;
	}
	public void setAllProvince(List<RegionInfo> allProvince) {
		this.allProvince = allProvince;
	}
	public List<DicInfo> getBrandList() {
		return brandList;
	}
	public void setBrandList(List<DicInfo> brandList) {
		this.brandList = brandList;
	}
	public Integer getPageNum() {
		return pageNum;
	}
	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}
	public Map<Integer, String> getPriceStateMap() {
		return priceStateMap;
	}
	public void setPriceStateMap(Map<Integer, String> priceStateMap) {
		this.priceStateMap = priceStateMap;
	}
	public PageBean getPage() {
		return page;
	}
	public void setPage(PageBean page) {
		this.page = page;
	}
}
