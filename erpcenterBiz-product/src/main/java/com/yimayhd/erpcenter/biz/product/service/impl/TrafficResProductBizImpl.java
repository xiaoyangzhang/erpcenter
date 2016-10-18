package com.yimayhd.erpcenter.biz.product.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.yimayhd.erpcenter.biz.product.service.TrafficResProductBiz;
import com.yimayhd.erpcenter.dal.product.po.TrafficResProduct;
import com.yimayhd.erpcenter.dal.product.service.TrafficResProductDal;

public class TrafficResProductBizImpl implements TrafficResProductBiz{
	
	@Autowired
	private TrafficResProductDal trafficResProductDal;
	
	/**
	 * 根据资源Id查询绑定的产品信息
	 */
	@Override
	public List<TrafficResProduct> loadTrafficResProductInfo(Integer resId) {
		return trafficResProductDal.loadTrafficResProductInfo(resId);
	}

	/**
	 * 增加一个产品信息
	 */
	@Override
	public int insertTrafficResProduct(TrafficResProduct product) {
		return trafficResProductDal.insertTrafficResProduct(product);
	}

	/**
	 * 根据id查询资源产品信息
	 */
	@Override
	public TrafficResProduct loadTrafficResProduct(Integer id) {
		return trafficResProductDal.loadTrafficResProduct(id);
	}

	/**
	 * 保存修改产品信息
	 */
	@Override
	public int saveTrafficResProduct(TrafficResProduct productBean) {
		return trafficResProductDal.saveTrafficResProduct(productBean);
	}

	/**
	 * 删除绑定中的产品信息
	 */
	@Override
	public int delTrafficResProduct(Integer id) {
		return trafficResProductDal.delTrafficResProduct(id);
	}

	/**
	 * 跟id集合进行查询所有的数据
	 */
	@Override
	public int loadByResProductId(String id,String suggest_price_id,String adjust_uprodown_num,String price) {
		return trafficResProductDal.loadByResProductId(id, suggest_price_id, adjust_uprodown_num, price);
	}

	@Override
	public TrafficResProduct selectByResProductId(Integer id) {
		return trafficResProductDal.selectByResProductId(id);
	}

	@Override
	public Integer selectResProductNameCount(Integer productCode,Integer  resId) {
		return trafficResProductDal.selectResProductNameCount(productCode,resId);
	}

}
