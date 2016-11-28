package com.yimayhd.erpcenter.facade.tj.service.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;

import com.alibaba.fastjson.JSON;
import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.biz.product.service.ProductStockBiz;
import com.yimayhd.erpcenter.biz.product.service.TaoBaoStockBiz;
import com.yimayhd.erpcenter.common.util.DateUtils;
import com.yimayhd.erpcenter.dal.product.constans.Constants;
import com.yimayhd.erpcenter.dal.product.po.TaobaoProduct;
import com.yimayhd.erpcenter.dal.product.po.TaobaoStock;
import com.yimayhd.erpcenter.dal.product.po.TaobaoStockDate;
import com.yimayhd.erpcenter.dal.product.po.TaobaoStockLog;
import com.yimayhd.erpcenter.dal.product.po.TaobaoStockProduct;
import com.yimayhd.erpcenter.facade.tj.client.service.TaobaoProductFacade;

public class TaobaoProductFacadeImpl implements TaobaoProductFacade {

	@Autowired
	private TaoBaoStockBiz taoBaoStockBiz;
	
	@Autowired
	private ProductStockBiz productStockBiz;
	
	@Override
	public List<TaobaoStockLog> stockLogDetails(Integer stockDateId, String stockDate, ModelMap model) {
		String startMin=(stockDate+ " 00:00:00") ;
        String startMax=(stockDate+ " 23:59:59") ;
	    List<TaobaoStockLog> list = productStockBiz.selectByStockDateIdAndCreateTime(stockDateId, startMax, startMin);
		return list == null ? new ArrayList<TaobaoStockLog>() : list;
	}

	@Override
	public List<TaobaoStockDate> stockMonth(Integer stockId, Integer year, Integer month) {
		String beginDateStr = year+"-"+(month<10 ? ("0"+month):(""+month))+"-01";
    	String endDateStr = month==12 ? ((year+1)+"-01-01"):(year+"-"+(month<9 ? ("0"+(month+1)):(""+(month+1)))+"-01");    	
		Date startDate = DateUtils.parse(beginDateStr, "yyyy-MM-dd");
    	Date endDate = DateUtils.parse(endDateStr,"yyyy-MM-dd");
    	List<TaobaoStockDate> list = productStockBiz.selectTaobaoStocksByProductIdAndDate(stockId, startDate, endDate);
		return list == null ? new ArrayList<TaobaoStockDate>() : list;
	}

	@Override
	public String saveStock(String createUser, Integer stockId, String stockStr, Integer year, Integer month) {
		// String beginDateStr = year+"-"+(month<10 ?
		// ("0"+month):(""+month))+"-01";
		// String endDateStr = month==12 ?
		// ((year+1)+"-01-01"):(year+"-"+(month<9 ?
		// ("0"+(month+1)):(""+(month+1)))+"-01");
		// Date startDate = DateUtils.parse(beginDateStr, "yyyy-MM-dd");
		// Date endDate = DateUtils.parse(endDateStr,"yyyy-MM-dd");

		List<TaobaoStockDate> stockDateList = JSON.parseArray(stockStr, TaobaoStockDate.class);
		if (stockDateList != null && stockDateList.size() > 0) {
			for (TaobaoStockDate item : stockDateList) {
				TaobaoStockLog taobaoStockLog = new TaobaoStockLog();
				taobaoStockLog.setCreateUser( createUser );
				taobaoStockLog.setNum(item.getStockCount());
				taobaoStockLog.setStockId(item.getStockId());
				taobaoStockLog.setTaobaoOrderId(0);
				if (item.getId() == null) {
					Integer dateId = productStockBiz.insertTaobaoStockDateSelective(item);
					taobaoStockLog.setStockDateId(dateId);
				} else {
					taobaoStockLog.setStockDateId(item.getId());
				}
				productStockBiz.insertTaobaoStockLogSelective(taobaoStockLog);
				productStockBiz.updateByLog(item.getId()); // 计算库存
			}
		}
		return null;
	}

	@Override
	public PageBean<TaobaoStock> findTaoBaoStockList(Map<String, Object> psBean, Integer pageSize, Integer page)
			throws ParseException {
		PageBean<TaobaoStock> pageBean = new PageBean<TaobaoStock>();
		if (page == null) {
			pageBean.setPage(1);
		} else {
			pageBean.setPage(page);
		}
		if (pageSize == null) {
			pageBean.setPageSize(Constants.PAGESIZE);
		} else {
			pageBean.setPageSize(pageSize);
		}
		pageBean.setPage(page);
		pageBean.setParameter(psBean);

		pageBean = taoBaoStockBiz.selectByStockListPage(pageBean);

		return pageBean;
	}

	@Override
	public TaobaoStock toUpdatetbStockChange(Integer id) throws ParseException {
		TaobaoStock taobaoStockBean = taoBaoStockBiz.selectByPrimaryKey(id);
		return taobaoStockBean;
	}

	@Override
	public int toSaveTbStock(Integer id, String stockName, Integer clearDayReset, String brief, ModelMap model)
			throws ParseException {
		TaobaoStock tbs = new TaobaoStock();
		tbs.setId(id);
		tbs.setStockName(stockName);
		tbs.setClearDayReset(clearDayReset);
		tbs.setBrief(brief);
		int num = taoBaoStockBiz.updateByPrimaryKeySelective(tbs);
		return num;
	}

	@Override
	public List<TaobaoProduct> toStockProductBindingList(Integer stockId, ModelMap model) {
		List<TaobaoStockProduct> stockProList = 
				taoBaoStockBiz.findStockProductStockId(stockId);
		Set<Integer> proIdSets = new HashSet<Integer>();
		for(TaobaoStockProduct spBean: stockProList){
			proIdSets.add(spBean.getProductId());
		}
		//根据taobao_stock_product中的product_id获取产品名称
		List<TaobaoProduct> proInfoList = null;
		if(proIdSets.size()>0){
			proInfoList = taoBaoStockBiz.findTaobaoProductById(proIdSets);
		}
		return proInfoList == null ? new ArrayList<TaobaoProduct>() : proInfoList;
	}

	@Override
	public PageBean<TaobaoProduct> findTaoBaoProduct(Map<String, Object> psBean, Integer pageSize, Integer page)
			throws ParseException {
		PageBean<TaobaoProduct> pageBean = new PageBean<TaobaoProduct>();

		if (page == null) {
			pageBean.setPage(1);
		} else {
			pageBean.setPage(page);
		}
		if (pageSize == null) {
			pageBean.setPageSize(Constants.PAGESIZE);
		} else {
			pageBean.setPageSize(pageSize);
		}
		pageBean.setPage(page);
		pageBean.setParameter(psBean);

		pageBean = taoBaoStockBiz.findTaoBaoProductListPage(pageBean);
		return pageBean;
	}

	@SuppressWarnings("unchecked")
	@Override
	public PageBean<TaobaoProduct> TaoBaoProductStock_Table(Map<String, Object> psBean, Integer pageSize, Integer page)
			throws ParseException {
		PageBean<TaobaoProduct> pageBean = new PageBean<TaobaoProduct>();
		if (page == null) {
			pageBean.setPage(1);
		} else {
			pageBean.setPage(page);
		}
		if (pageSize == null) {
			pageBean.setPageSize(Constants.PAGESIZE);
		} else {
			pageBean.setPageSize(pageSize);
		}
		pageBean.setPage(page);
		pageBean.setParameter(psBean);

		pageBean = taoBaoStockBiz.findTaoBaoProductStockListPage(pageBean);
		return pageBean;
	}

	@Override
	public Map<String,Object> saveStockProBinding(String productId, Integer stockId, ModelMap model) {
		Map<String,Object> map = new HashMap<String,Object>();
		String[] arrProId= productId.split(",");
		for(int i=0;i<arrProId.length;i++){
			TaobaoStockProduct tbspBean = new TaobaoStockProduct();
			tbspBean.setProductId(Integer.valueOf(arrProId[i]));
			tbspBean.setStockId(stockId);
			//插入淘宝库存产品关联信息
			TaobaoStockProduct taobaoStockProduct = taoBaoStockBiz.findStockProductInfo(tbspBean);
			if(taobaoStockProduct != null){
				//model.addAttribute("spError", "选择的产品名称已经存在！");
				map.put("error", "spError");
			}else{
				taoBaoStockBiz.insertTbStockProduct(tbspBean);
				map.put("success", 1);
			}
		}
		return map;
	}

	@Override
	public boolean deleteTaoBaoStockProduct(String productId, Integer stockId, ModelMap model) {
		String[] arrProId= productId.split(",");
		for(int i=0;i<arrProId.length;i++){
			TaobaoStockProduct tbspBean = new TaobaoStockProduct();
			tbspBean.setProductId(Integer.valueOf(arrProId[i]));
			tbspBean.setStockId(stockId);
			//删除淘宝库存产品关联信息
			taoBaoStockBiz.deleteTaoBaoStockProduct(tbspBean);
		}
		return true;
	}

	@Override
	public int saveTaobbaoStock(String stockName, Integer clearDayReset, String brief, Model model)
			throws ParseException {
		TaobaoStock tbs = new TaobaoStock();
		tbs.setStockName(stockName);
		tbs.setClearDayReset(clearDayReset);
		tbs.setBrief(brief);
		int num = taoBaoStockBiz.insertSelective(tbs);
		return num;
	}

	@Override
	public Map<String,Object> deleteTaoBaoStockProduct(Integer id, ModelMap model) {
		Map<String,Object> map = new HashMap<String,Object>();
		//判断是否存在订单信息
		TaobaoStockLog count = taoBaoStockBiz.selectLogNumByOrderId(id);
		if(count.getId()!=0){
			map.put("error", "logError");
		}else{
			//删除库存信息
			int num = taoBaoStockBiz.deleteByPrimaryKey(id);
			
			List<TaobaoStockProduct> tbSPList = taoBaoStockBiz.findStockProductStockId(id);
			for(TaobaoStockProduct spBean : tbSPList){
				TaobaoStockProduct tbspBean = new TaobaoStockProduct();
				tbspBean.setProductId(spBean.getProductId());
				tbspBean.setStockId(id);
				//删除库存关联信息
				taoBaoStockBiz.deleteTaoBaoStockProduct(tbspBean);
			}
			map.put("success", num);
		}
		return map;
	}
}
