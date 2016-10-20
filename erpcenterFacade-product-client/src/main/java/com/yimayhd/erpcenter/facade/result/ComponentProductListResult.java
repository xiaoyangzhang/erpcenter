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
import com.yimayhd.erpcenter.dal.product.po.ProductRemark;
import com.yimayhd.erpcenter.dal.product.vo.ProductInfoVo;
import com.yimayhd.erpcenter.dal.product.vo.ProductRouteVo;

/**
 * @ClassName: ProductInfoResult
 * @Description: 产品列表/预览
 * @author zhangxiaoyang
 * @date 2016年10月19日
 */
public class ComponentProductListResult extends ResultSupport implements Serializable {

	/**
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	*/
	private static final long serialVersionUID = 6636674666861206380L;
	
	private PageBean page;
	private Integer pageNum;
	
	public PageBean getPage() {
		return page;
	}
	public void setPage(PageBean page) {
		this.page = page;
	}
	public Integer getPageNum() {
		return pageNum;
	}
	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}
}
