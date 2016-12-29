package com.yimayhd.erpcenter.biz.product.service.impl;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.biz.product.service.TaoBaoStockBiz;
import com.yimayhd.erpcenter.dal.product.po.TaobaoProduct;
import com.yimayhd.erpcenter.dal.product.po.TaobaoStock;
import com.yimayhd.erpcenter.dal.product.po.TaobaoStockLog;
import com.yimayhd.erpcenter.dal.product.po.TaobaoStockProduct;
import com.yimayhd.erpcenter.dal.product.service.TaoBaoStockDal;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;
import java.util.Set;


public class TaoBaoStockBIzImpl implements TaoBaoStockBiz{
	
	@Autowired
	private TaoBaoStockDal taoBaoStockDal;


	@Override
	public void updateProductStockByTaobao(List<Map<String, String>> mapList) {
		taoBaoStockDal.updateProductStockByTaobao(mapList);
	}

	@Override
	public PageBean<TaobaoStock> selectByStockListPage(PageBean<TaobaoStock> pageBean) {
		return taoBaoStockDal.selectByStockListPage(pageBean);
	}

	@Override
	public TaobaoStock selectByPrimaryKey(Integer id) {
		return taoBaoStockDal.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(TaobaoStock record) {
		return taoBaoStockDal.updateByPrimaryKeySelective(record);
	}

	@Override
	public List<TaobaoStockProduct> findStockProductStockId(Integer stockId) {
		return taoBaoStockDal.findStockProductStockId(stockId);
	}

	@Override
	public List<TaobaoProduct> findTaobaoProductById(Set<Integer> set) {
		return taoBaoStockDal.findTaobaoProductById(set);
	}

	@Override
	public int insertTaobaoProduct(TaobaoProduct taobaoProduct) {
		return taoBaoStockDal.insertTaobaoProduct(taobaoProduct);
	}

	@Override
	public int insertTbStockProduct(TaobaoStockProduct record) {
		return taoBaoStockDal.insertTbStockProduct(record);
	}

	@Override
	public PageBean<TaobaoProduct> findTaoBaoProductListPage(PageBean<TaobaoProduct> pageBean) {
		return taoBaoStockDal.findTaoBaoProductListPage(pageBean);
	}

	@Override
	public TaobaoProduct findByPrimaryKey(Integer id) {
		return taoBaoStockDal.findByPrimaryKey(id);
	}

	@Override
	public int deleteTaoBaoStockProduct(TaobaoStockProduct taobaoStockProduct) {
		return taoBaoStockDal.deleteTaoBaoStockProduct(taobaoStockProduct);
	}

	@Override
	public int deleteByPrimaryKey(Integer id) {
		return taoBaoStockDal.deleteByPrimaryKey(id);
	}

	@Override
	public int insertSelective(TaobaoStock record) {
		return taoBaoStockDal.insertSelective(record);
	}

	@Override
	public TaobaoStockProduct findStockProductInfo(TaobaoStockProduct record) {
		return taoBaoStockDal.findStockProductInfo(record);
	}

	@Override
	public TaobaoStockLog selectLogNumByOrderId(Integer stockId) {
		return taoBaoStockDal.selectLogNumByOrderId(stockId);
	}

	@Override
	public TaobaoStockLog selectLogByStockDateIdAndOrderId(Integer stockDateId, Integer orderId) {
		return taoBaoStockDal.selectLogByStockDateIdAndOrderId(stockDateId,orderId);
	}

	@Override
	public PageBean findTaoBaoProductStockListPage(PageBean pageBean) {
		return taoBaoStockDal.findTaoBaoProductStockListPage(pageBean);
	}

	@Override
	public int delTaoBaoProductStock(TaobaoStockLog stockLog) {
		return taoBaoStockDal.delTaoBaoProductStock(stockLog);
	}

	@Override
	public TaobaoStockLog selectStockLogAllByOrderId(Integer orderId) {
		return taoBaoStockDal.selectStockLogAllByTaobaoOrderId(orderId);
	}

	@Override
	public TaobaoStockLog selectStockLogAllByTaobaoOrderId(Integer taobaoOrderId) {
		return taoBaoStockDal.selectStockLogAllByTaobaoOrderId(taobaoOrderId);
	}
}
