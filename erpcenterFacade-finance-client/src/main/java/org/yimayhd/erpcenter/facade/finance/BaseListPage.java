/**
 * 
 */
package org.yimayhd.erpcenter.facade.finance;

import java.io.Serializable;

/**
 * @ClassName: BaseListPage
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author zhangxiaoyang
 * @date 2016年10月18日
 */
public class BaseListPage implements Serializable {

	/**
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	*/
	private static final long serialVersionUID = -2556824914327988809L;

	private int page = 1;
	private int pageSize = 20;
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	
}
