/**
 * 
 */
package org.erpcenterFacade.common.client.result;

import java.io.Serializable;
import java.util.List;

import com.yimayhd.erpcenter.dal.basic.po.RegionInfo;

/**
 * @ClassName: RegionResult
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author zhangxiaoyang
 * @date 2016年10月20日
 */
public class RegionResult extends ResultSupport implements Serializable {

	/**
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	*/
	private static final long serialVersionUID = 7968694650370268L;
	private List<RegionInfo> regionList;
	public List<RegionInfo> getRegionList() {
		return regionList;
	}
	public void setRegionList(List<RegionInfo> regionList) {
		this.regionList = regionList;
	}
	
}
