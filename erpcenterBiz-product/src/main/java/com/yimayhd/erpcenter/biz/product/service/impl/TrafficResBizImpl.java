package com.yimayhd.erpcenter.biz.product.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.biz.product.service.TrafficResBiz;
import com.yimayhd.erpcenter.dal.product.po.TrafficRes;
import com.yimayhd.erpcenter.dal.product.po.TrafficResLine;
import com.yimayhd.erpcenter.dal.product.po.TrafficResProduct;
import com.yimayhd.erpcenter.dal.product.po.TrafficResStocklog;
import com.yimayhd.erpcenter.dal.product.service.TrafficResDal;
import com.yimayhd.erpcenter.dal.product.vo.TrafficResVo;



public class TrafficResBizImpl implements TrafficResBiz{
	
	@Autowired
	private TrafficResDal trafficResDal;
	
	
	@Override
	public int saveTrafficRes(TrafficResVo trafficResVo) {
		return trafficResDal.saveTrafficRes(trafficResVo);
	}
	
	@Override
	public TrafficRes findTrafficResById(Integer id) {
		return trafficResDal.findTrafficResById(id);
	}
	
	@Override
	public PageBean<TrafficRes> selectTrafficResListPage(
			PageBean<TrafficRes> pageBean) {
		return trafficResDal.selectTrafficResListPage(pageBean);
	}
	
	@Override
	public List<TrafficResLine> selectTrafficResLine(Integer resId) {
		return trafficResDal.selectTrafficResLine(resId);
	}
	
	@Override
	public List<TrafficRes> selectResDetails(Integer id){
		return trafficResDal.selectResDetails(id);
	}
	@Override
	public List<TrafficRes> selectDetailsStocklog(Integer id,String adjustTime){
		return trafficResDal.selectDetailsStocklog(id, adjustTime);
	}

	@Override
	public int updateTrafficResStockLogByOrderId(TrafficResStocklog record) {
		return trafficResDal.updateTrafficResStockLogByOrderId(record);
	}

	@Override
	public int updateStockLog_AdjustState(TrafficResStocklog record) {
		return trafficResDal.updateStockLog_AdjustState(record);
	}

	@Override
	public TrafficRes selectTrafficResAndLineInfoById(Integer id) {
		return trafficResDal.selectTrafficResAndLineInfoById(id);
	}

	@Override
	public TrafficRes selectTrafficResAndLineInfoById1(Integer id) {
		return trafficResDal.selectTrafficResAndLineInfoById1(id);
	}

	@Override
	public int copyRes(Integer resId,String date) {
		return trafficResDal.copyRes(resId, date);
	}
	
	@Override
	public int insertTrafficResStocklog(TrafficResStocklog record) {
		return trafficResDal.insertTrafficResStocklog(record);
	}
	
	
	@Override
	public int updateStockOrStockDisable(Integer id) {
		return trafficResDal.updateStockOrStockDisable(id);
	}
	
	@Override
	public TrafficResProduct selectTrafficProductInfo(Integer id) {
		return trafficResDal.selectTrafficProductInfo(id);
	}
	
	@Override
	public int updateNumSoldById (TrafficResProduct record) {
		return trafficResDal.updateNumSoldById(record);
	}
	
	@Override
	public TrafficResProduct selectTrafficProductInfoByProductCode(Integer productCode,Integer  resId) {
		return trafficResDal.selectTrafficProductInfoByProductCode(productCode, resId);
}
	
	@Override
	public PageBean<TrafficResProduct> selectResProductListPage(PageBean<TrafficResProduct> pageBean) {
		return trafficResDal.selectResProductListPage(pageBean);
	}
	
	@Override
	public int updateTrafficResState(Integer id,Integer state) {
		return trafficResDal.updateTrafficResState(id, state);
	}
}
