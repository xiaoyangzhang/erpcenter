package com.yimayhd.erpcenter.biz.sales.client.service.sales;

import java.util.List;
import java.util.Map;

import com.yihg.mybatis.utility.PageBean;

/**
 * 通用查询
 * 
 * @author Jing.Zhuo
 * @create 2015年7月27日 上午9:27:43
 */
public interface CommonSaleBiz {
	
	/**
	 * 分页列表查询
	 * 
	 * @author Jing.Zhuo
	 * @create 2015年7月27日 上午9:28:36
	 * @param sqlId 查询语句ID
	 * @param pageBean 分页参数
	 * @return
	 */
	PageBean queryListPage(String sqlId, PageBean pageBean);

	/**
	 * 列表查询
	 * 
	 * @author Jing.Zhuo
	 * @create 2015年7月27日 上午9:28:43
	 * @param sqlId 查询语句ID
	 * @param param 查询条件
	 * @return
	 */
	List queryList(String sqlId, Map param);
	
	/**
	 * 对象查询
	 * 
	 * @author Jing.Zhuo
	 * @create 2015年7月27日 上午9:28:43
	 * @param sqlId 查询语句ID
	 * @param param 查询条件
	 * @return
	 */
	Map queryOne(String sqlId, Map param);

}
