package com.yimayhd.erpcenter.facade.dataanalysis.client.result;

/**
 * 
 * @author liyong
 * 2016年11月1日 描述：
 * @param <T>
 */
public class DataAnalysisWebResult<T> extends BaseResult {

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
