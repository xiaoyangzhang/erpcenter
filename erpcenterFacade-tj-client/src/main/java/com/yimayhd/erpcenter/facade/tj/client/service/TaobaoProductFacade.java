package com.yimayhd.erpcenter.facade.tj.client.service;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.dal.product.po.TaobaoProduct;
import com.yimayhd.erpcenter.dal.product.po.TaobaoStock;
import com.yimayhd.erpcenter.dal.product.po.TaobaoStockDate;
import com.yimayhd.erpcenter.dal.product.po.TaobaoStockLog;

public interface TaobaoProductFacade {

	public List<TaobaoStockLog> stockLogDetails(Integer stockDateId, String stockDate, ModelMap model);

	public List<TaobaoStockDate> stockMonth(Integer stockId, Integer year, Integer month);

	public String saveStock(String createUser, Integer stockId, String stockStr, Integer year, Integer month);

	/**
	 * 显示淘宝库存信息Table
	 * 
	 * @param request
	 * @param TaobaoStock
	 * @param model
	 * @return
	 * @throws ParseException
	 */
	public PageBean<TaobaoStock> findTaoBaoStockList(Map<String,Object> psBean, Integer pageSize, Integer page) throws ParseException;

	/**
	 * 修改淘宝库存
	 * 
	 * @param request
	 * @param TaobaoStock
	 * @param model
	 * @return
	 * @throws ParseException
	 */
	public TaobaoStock toUpdatetbStockChange(Integer id) throws ParseException;

	/**
	 * 保存修改库存信息
	 * 
	 * @param request
	 * @param id
	 * @param stockName
	 * @param clearDayReset
	 * @param brief
	 * @param model
	 * @return
	 * @throws ParseException
	 */
	public int toSaveTbStock(Integer id, String stockName, Integer clearDayReset, String brief, ModelMap model)
			throws ParseException;

	/**
	 * 关联产品信息
	 * 
	 * @param request
	 * @param stockId
	 * @param model
	 * @return
	 */
	public List<TaobaoProduct> toStockProductBindingList(Integer stockId, ModelMap model);

	public PageBean<TaobaoProduct> findTaoBaoProduct(Map<String,Object> psBean, Integer pageSize, Integer page) throws ParseException;

	public PageBean<TaobaoProduct> TaoBaoProductStock_Table(Map<String,Object> psBean, Integer pageSize, Integer page) throws ParseException;

	/**
	 * 保存添加的产品信息
	 * 
	 * @param request
	 * @param stockId
	 * @param model
	 * @return
	 */
	public Map<String,Object> saveStockProBinding(String productId, Integer stockId, ModelMap model);

	/**
	 * 删除淘宝库存产品关联信息
	 * 
	 * @param request
	 * @param productId
	 * @param stockId
	 * @param model
	 * @return
	 */
	public boolean deleteTaoBaoStockProduct(String productId, Integer stockId, ModelMap model);

	/**
	 * 保存
	 * 
	 * @param request
	 * @param stockName
	 * @param clearDayReset
	 * @param brief
	 * @param model
	 * @return
	 * @throws ParseException
	 */
	public int saveTaobbaoStock(String stockName, Integer clearDayReset, String brief, Model model)
			throws ParseException;

	/**
	 * 删除淘宝库存信息
	 * 
	 * @param request
	 * @param stockId
	 * @param model
	 * @return
	 */
	public Map<String,Object> deleteTaoBaoStockProduct(Integer id, ModelMap model);
}
