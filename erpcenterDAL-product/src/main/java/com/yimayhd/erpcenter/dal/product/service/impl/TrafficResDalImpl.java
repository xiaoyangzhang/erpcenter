package com.yimayhd.erpcenter.dal.product.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.dal.product.mapper.TrafficResLineMapper;
import com.yimayhd.erpcenter.dal.product.mapper.TrafficResMapper;
import com.yimayhd.erpcenter.dal.product.mapper.TrafficResProductMapper;
import com.yimayhd.erpcenter.dal.product.mapper.TrafficResStocklogMapper;
import com.yimayhd.erpcenter.dal.product.po.TrafficRes;
import com.yimayhd.erpcenter.dal.product.po.TrafficResLine;
import com.yimayhd.erpcenter.dal.product.po.TrafficResProduct;
import com.yimayhd.erpcenter.dal.product.po.TrafficResStocklog;
import com.yimayhd.erpcenter.dal.product.service.TrafficResDal;
import com.yimayhd.erpcenter.dal.product.vo.TrafficResVo;



public class TrafficResDalImpl implements TrafficResDal{
	
	@Autowired
	private TrafficResMapper trafficResMapper;
	
	@Autowired
	private TrafficResLineMapper trafficResLineMapper;
	
	@Autowired
	private TrafficResProductMapper trafficResProductMapper;
	
	@Autowired
	private TrafficResStocklogMapper trafficResStocklogMapper;
	
	@Transactional
	@Override
	public int saveTrafficRes(TrafficResVo trafficResVo) {
		TrafficRes trafficRes=trafficResVo.getTrafficRes();
		List<TrafficResLine> trafficResLine= trafficResVo.getTrafficResLine();
		if(trafficRes.getId()==null){
			trafficResMapper.insertSelective(trafficRes);
		}else{
			trafficResMapper.updateTrafficRes(trafficRes);
			List<Integer> a=new ArrayList();                              // 删除交通信息
			List<TrafficResLine> trafficResLine1=trafficResLineMapper.selectTrafficResLine(trafficRes.getId());
			if(trafficResLine1 !=null&&trafficResLine1.size()>0){
				if(trafficResLine !=null&&trafficResLine.size()>0){ 
			 for (TrafficResLine tr : trafficResLine) {
				 if(tr.getId()!=null){
					 a.add(tr.getId());
				 }
			 }
			 for (TrafficResLine tr1 : trafficResLine1) {
				 if(!(a.contains(tr1.getId()))){
					 trafficResLineMapper.deleteByPrimaryKey(tr1.getId());
				 }
			 }
			}else{  // 删除页面全部交通信息
				 for (TrafficResLine tr1 : trafficResLine1) {
						 trafficResLineMapper.deleteByPrimaryKey(tr1.getId());
				 }
				}
			}
		}
		if(trafficResLine !=null&&trafficResLine.size()>0){
			   for (TrafficResLine tr : trafficResLine) {
				   if(tr.getLineDate()!=""&&tr.getLineDate()!=null&&tr!=null){
				   if(tr.getId()==null){
				  tr.setResId(trafficRes.getId());
				  tr.setTimeCreate(new Date());
				  tr.setUserId(trafficRes.getUserId());
				  tr.setUserName(trafficRes.getUserName());
				  trafficResLineMapper.insertSelective(tr);
			   }else{
				   tr.setTimeUpdate(new Date());
				   trafficResLineMapper.updateTrafficResLine(tr);
			   }
			   }
			   }
		}
		return trafficRes.getId();
	}

	
	@Override
	public TrafficRes findTrafficResById(Integer id) {
		TrafficRes trafficRes=trafficResMapper.selectByPrimaryKey(id);
		return trafficRes;
}
	
	@Override
	public PageBean<TrafficRes> selectTrafficResListPage(
			PageBean<TrafficRes> pageBean) {
		List<TrafficRes> list = trafficResMapper.selectTrafficResListPage(pageBean);
		pageBean.setResult(list);
		return pageBean;
	}
	
	@Override
	public List<TrafficResLine> selectTrafficResLine(Integer resId) {
		List<TrafficResLine> list = trafficResLineMapper.selectTrafficResLine(resId);
		return list;
	}
	
	@Override
	public List<TrafficRes> selectResDetails(Integer id){
		List<TrafficRes> list = trafficResMapper.selectResDetails(id);
		return list;
	}
	@Override
	public List<TrafficRes> selectDetailsStocklog(Integer id,String adjustTime){
		List<TrafficRes> list = trafficResMapper.selectDetailsStocklog(id,adjustTime);
		return list;
	}
	@Override
	public int copyRes(Integer resId,String date) {
		// TrafficRes
		TrafficRes trafficRes=trafficResMapper.selectTrafficResById(resId,date);
		trafficRes.setDateStart(date);
		trafficRes.setDateLatest(trafficRes.getNewDateLatest());
		trafficResMapper.insertSelective(trafficRes);
		int newResId=trafficRes.getId();
		//TrafficResLine
		List<TrafficResLine> list1= trafficResLineMapper.selectTrafficResLineByResId(resId);
		if (list1 != null && list1.size() > 0) {
			for (TrafficResLine item : list1) {
				item.setResId(newResId);
				trafficResLineMapper.insertSelective(item);
			}
		}
		//TrafficResProduct
		List<TrafficResProduct> list2=trafficResProductMapper.selectTrafficProductByResId(resId);
		if (list2 != null && list2.size() > 0) {
			for (TrafficResProduct item : list2) {
				item.setResId(newResId);
				trafficResProductMapper.insertTrafficResProduct(item);
			}
		}
		//TrafficResStocklog
		List<TrafficResStocklog> list3=trafficResStocklogMapper.selectStocklogbyResId(resId);
		if (list3 != null && list3.size() > 0) {
			for (TrafficResStocklog item : list3) {
				item.setResId(newResId);
				trafficResStocklogMapper.insertSelective(item);
			}
		}
		return newResId;
	}
	
	@Override
	public int insertTrafficResStocklog(TrafficResStocklog record) {
		trafficResStocklogMapper.insertSelective(record);
		return record.getId();
	}
	
	
	@Override
	public int updateStockOrStockDisable(Integer id) {
		trafficResMapper.updateStockOrStockDisable(id);
		return id;
	}
	
	@Override
	public TrafficResProduct selectTrafficProductInfo(Integer id) {
		TrafficResProduct trafficResProduct=trafficResProductMapper.selectTrafficProductInfo(id);
		return trafficResProduct;
}
	
	@Override
	public int updateNumSoldById (TrafficResProduct record) {
		trafficResProductMapper.updateNumSoldById(record);
		return record.getId();
}
	
	@Override
	public TrafficResProduct selectTrafficProductInfoByProductCode(Integer productCode,Integer  resId) {
		TrafficResProduct trafficResProduct=trafficResProductMapper.selectTrafficProductInfoByProductCode(productCode,resId);
		return trafficResProduct;
}
	
	@Override
	public PageBean<TrafficResProduct> selectResProductListPage(PageBean<TrafficResProduct> pageBean) {
		List<TrafficResProduct> list = trafficResProductMapper.selectResProductListPage(pageBean);
		pageBean.setResult(list);
		return pageBean;
	}
	
	@Override
	public int updateTrafficResState(Integer id,Integer state) {
		trafficResMapper.updateTrafficResState(id,state);
		return id;
	}
}
