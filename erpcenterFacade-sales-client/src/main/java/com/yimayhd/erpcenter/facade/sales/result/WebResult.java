/**
 * 
 */
package com.yimayhd.erpcenter.facade.sales.result;

/**
 * @ClassName: WebResult
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author zhangxiaoyang
 * @date 2016年10月18日
 */
public class WebResult<T> extends ResultSupport {

	/**
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	*/
	private static final long serialVersionUID = 7546504127191622861L;

	private T value;

	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}
	
}
