package com.yimayhd.erpcenter.dal.product.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.alibaba.fastjson.JSON;
import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.dal.product.dao.TrafficResLineMapper;
import com.yimayhd.erpcenter.dal.product.dao.TrafficResMapper;
import com.yimayhd.erpcenter.dal.product.dao.TrafficResProductMapper;
import com.yimayhd.erpcenter.dal.product.dao.TrafficResStocklogMapper;
import com.yimayhd.erpcenter.dal.product.po.TrafficRes;
import com.yimayhd.erpcenter.dal.product.po.TrafficResLine;
import com.yimayhd.erpcenter.dal.product.po.TrafficResProduct;
import com.yimayhd.erpcenter.dal.product.po.TrafficResStocklog;
import com.yimayhd.erpcenter.dal.product.service.TrafficResDal;
import com.yimayhd.erpcenter.dal.product.vo.TrafficResVo;



public class TrafficResDalImpl implements TrafficResDal{
	private static final Logger LOGGER = LoggerFactory.getLogger(TrafficResDalImpl.class);
	
	@Autowired
	private TrafficResMapper trafficResMapper;
	
	@Autowired
	private TrafficResLineMapper trafficResLineMapper;
	
	@Autowired
	private TrafficResProductMapper trafficResProductMapper;
	
	@Autowired
	private TrafficResStocklogMapper trafficResStocklogMapper;
	
	@Autowired
    private TransactionTemplate transactionTemplateProduct;
	
	@Override
	public int saveTrafficRes(final TrafficResVo trafficResVo) {
		final TrafficRes trafficRes=trafficResVo.getTrafficRes();
		final List<TrafficResLine> trafficResLine= trafficResVo.getTrafficResLine();
		Boolean dbResult = transactionTemplateProduct.execute(new TransactionCallback<Boolean>() {
			@Override
			public Boolean doInTransaction(TransactionStatus status) {
				try{
					if(trafficRes.getId()==null){
						int result = trafficResMapper.insertSelective(trafficRes);
						if (result <= 0) {
							return false;
						}
					}else{
						int result2 = trafficResMapper.updateTrafficRes(trafficRes);
						if (result2 <= 0) {
							return false;
						}
						List<Integer> a=new ArrayList<Integer>();                              // 删除交通信息
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
								 int result3 = trafficResLineMapper.deleteByPrimaryKey(tr1.getId());
								 if (result3 < 0) {
									 return false;
								 }
							 }
						 }
						}else{  // 删除页面全部交通信息
							 for (TrafficResLine tr1 : trafficResLine1) {
								 int result4 = trafficResLineMapper.deleteByPrimaryKey(tr1.getId());
								 if (result4 < 0) {
									 return false;
								 }
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
								   int result5 = trafficResLineMapper.insertSelective(tr);
								   if (result5 <= 0) {
									   return false;
								   }
							   }else{
								   tr.setTimeUpdate(new Date());
								   int result6 = trafficResLineMapper.updateTrafficResLine(tr);
								   if (result6 <= 0) {
									   return false;
								   }
							   }
						   }
					   }
					}
					
					return true;
				}catch(Exception e){
					status.setRollbackOnly(); 
					LOGGER.error("saveTrafficRes failed!  TrafficResVo={}", JSON.toJSONString(trafficResVo), e);
					return false;
				}
			}
		});
		if( dbResult == null || !dbResult ){
			LOGGER.error("saveTrafficRes failed!  TrafficResVo={}", JSON.toJSONString(trafficResVo));
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
		String resDate = trafficRes.getDateStart();
		trafficRes.setDateStart(date);
		trafficRes.setDateLatest(trafficRes.getNewDateLatest());
		trafficResMapper.insertSelective(trafficRes);
		int newResId=trafficRes.getId();
		//TrafficResLine
		List<TrafficResLine> list1= trafficResLineMapper.selectTrafficResLineByResId(resDate, date, resId);
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
		updateStockOrStockDisable(newResId);
		return newResId;
	}
	
	@Override
	public int insertTrafficResStocklog(TrafficResStocklog record) {
		trafficResStocklogMapper.insertSelective(record);
		return record.getId();
	}

	@Override
	public int updateTrafficResStockLogByOrderId(TrafficResStocklog record) {
		trafficResStocklogMapper.updateByOrderId(record);
		return 1;
	}

	@Override
	public int updateStockOrStockDisable(Integer id) {
		trafficResMapper.updateStockOrStockDisable(id);
		return id;
	}

	@Override
	public int updateStockLog_AdjustState(TrafficResStocklog record) {
		trafficResStocklogMapper.updateByAdjustState(record);
		return 1;
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

	@Override
	public TrafficRes selectTrafficResAndLineInfoById(Integer id) {
		TrafficRes trafficRes=trafficResMapper.selectTrafficResAndLineInfoById(id);
		return trafficRes;
	}

	@Override
	public TrafficRes selectTrafficResAndLineInfoById1(Integer id) {
		TrafficRes trafficRes=trafficResMapper.selectTrafficResAndLineInfoById1(id);
		return trafficRes;
	}

	@Override
	public PageBean<TrafficRes> selectAirTicketProfitListPage(
			PageBean<TrafficRes> pageBean) {
		List<TrafficRes> list = trafficResMapper.selectAirTicketProfitListPage(pageBean);
		pageBean.setResult(list);
		return pageBean;
	}

	@Override
	public PageBean<TrafficResProduct> findResProductListToWX(PageBean<TrafficResProduct> pageBean) {

		List<TrafficResProduct> list = trafficResProductMapper.selectResProductToWXListPage(pageBean);

		Map<String, String> map = (Map<String, String>) pageBean.getParameter();

		for (TrafficResProduct trp : list) {
			List<TrafficResProduct> trpList = trafficResProductMapper.selectResProductInfoListToWX(
					Integer.parseInt(map.get("bizId")), map.get("startTime"), map.get("endTime"), trp.getProductCode());

			for (TrafficResProduct resProduct : trpList) {
				if (resProduct.getNumStock() == 0) {
					resProduct.setNumStock(resProduct.getNumBalance());
				} else {
					resProduct.setNumStock(resProduct.getNumStock() - resProduct.getNumSold());
				}
				if (resProduct.getUnconfirm() == null) {
					resProduct.setUnconfirm(0);
				}
				if (resProduct.getConfirm() == null) {
					resProduct.setConfirm(0);
				}

			}

			trp.setTrafficResProducts(trpList);
		}

		pageBean.setResult(list);
		return pageBean;
	}

	@Override
	public TrafficResProduct findResProductToWX(Integer trpId, Integer resId) {

		TrafficResProduct trafficResProduct = trafficResProductMapper.selectResProductInfoToWX(trpId, resId);
		if (trafficResProduct.getNumStock() == 0) {
			trafficResProduct.setNumStock(trafficResProduct.getNumBalance());
		} else {
			trafficResProduct.setNumStock(trafficResProduct.getNumStock() - trafficResProduct.getNumSold());
		}
		return trafficResProduct;
	}



	@Override
	public List<TrafficRes> findProductInfoByYearToWX(Integer bizId, String startTime, String endTime) {
		return trafficResMapper.selectProductInfoByYearToWX(bizId, startTime, endTime);
	}

}
