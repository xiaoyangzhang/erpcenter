package com.yimayhd.erpcenter.dal.product.service.impl;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.dal.product.dao.*;
import com.yimayhd.erpcenter.dal.product.po.*;
import com.yimayhd.erpcenter.dal.product.service.TaoBaoStockDal;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class TaoBaoStockDalImpl implements TaoBaoStockDal{
	
	@Autowired
	private TaobaoStockMapper taobaoStockMapper;

	@Autowired
	private TaobaoStockProductMapper taobaoStockProductMapper;
	
	@Autowired
	private TaobaoProductMapper taobaoProductMapper;
	
	@Autowired
	private TaobaoStockLogMapper taobaoStockLogMapper;
	
	@Autowired
	private TaobaoStockDateMapper taobaoStockDateMapper;
	
	@Autowired
	private TaobaoProductSkusMapper taobaoProductSkusMapper;
	
	  @Transactional
	    @Override
	    public List<Map<String, String>> updateProductStockByTaobao(List<Map<String, String>> mapList) {
		  List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		  for (Map<String, String> map : mapList) {
	            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	            Date date;
			  int count=0;
	            try {
	                if (StringUtils.isNotBlank(map.get("depDate"))) {
	                    date = sdf.parse(map.get("depDate"));
//	                    TaobaoStockDate taobaoStockDate =  taobaoStockDateMapper.selectTaobaoStocksByNumIidAndStockDate(map.get("numIid"), date);
	                    TaobaoStockDate taobaoStockDate =  taobaoStockDateMapper.selectTaobaoStocksByNumIidAndStockDate(map.get("sku"), date,map.get("numIid"));  
	                    if(taobaoStockDate !=null){
	                        TaobaoStockLog tsl = taobaoStockLogMapper.selectByStockDateIdAndTaobaoOrderId(taobaoStockDate.getId(), Integer.parseInt(map.get("taobaoOrderId")));
	                        if(tsl==null){
	                    	TaobaoStockLog taobaoStockLog=new TaobaoStockLog();
	                    	taobaoStockLog.setStockId(taobaoStockDate.getStockId());
	                    	taobaoStockLog.setStockDateId(taobaoStockDate.getId());
	                    	taobaoStockLog.setNum(Integer.parseInt(map.get("receiveCount")));
	                    	taobaoStockLog.setCreateUser("sys");
	                    	taobaoStockLog.setTaobaoOrderId(Integer.parseInt(map.get("taobaoOrderId")));
	                    	taobaoStockLog.setCreateUser(map.get("createUser"));
	                    	taobaoStockLog.setOrderId(0);
	                    	taobaoStockLogMapper.insertSelective(taobaoStockLog);
	                    	taobaoStockDateMapper.updateByLog(taobaoStockDate.getId());  // 计算已售
								// 如果库存小于0
								count =taobaoStockDate.getStockCount() - taobaoStockDate.getSaleCount()
										- Integer.parseInt(map.get("receiveCount"));
	                        }else{
	                        	if(tsl.getNum()!=Integer.parseInt(map.get("receiveCount"))){
	                        		tsl.setNum(Integer.parseInt(map.get("receiveCount")));
	                        		tsl.setCreateUser(map.get("createUser"));
	                        		taobaoStockLogMapper.updateByPrimaryKeySelective(tsl);
	                        		taobaoStockDateMapper.updateByLog(taobaoStockDate.getId());  // 计算已售
									if(tsl.getNum()<Integer.parseInt(map.get("receiveCount"))){
										count =taobaoStockDate.getStockCount() - taobaoStockDate.getSaleCount()
												- Integer.parseInt(map.get("receiveCount")+tsl.getNum());
									}
	                        }
	                    }
	                }
						if(count < 0){
							list.add(map);
						}
					}
	            } catch (ParseException e) {
	                e.printStackTrace();
	            }
	        }
		  return list;
	    }
	  
	  
	/**
	 * 查询淘宝库存信息
	 * @param pageBean
	 * @return
	 */
	@Override
	public PageBean<TaobaoStock> selectByStockListPage(PageBean<TaobaoStock> pageBean) {
		List<TaobaoStock> stockList = taobaoStockMapper.selectByStockListPage(pageBean);
		pageBean.setResult(stockList);
		return pageBean;
	}

	/**
	 * 修改
	 */
	@Override
	public TaobaoStock selectByPrimaryKey(Integer id) {
		TaobaoStock taobaoStock = taobaoStockMapper.selectByPrimaryKey(id);
		return taobaoStock;
	}

	/**
	 * 修改后保存方法
	 */
	@Override
	public int updateByPrimaryKeySelective(TaobaoStock record) {
		int num = taobaoStockMapper.updateByPrimaryKeySelective(record);
		return num;
	}

	/**
	 * 查询已关联的库存产品
	 */
	@Override
	public List<TaobaoStockProduct> findStockProductStockId(Integer stockId) {
		List<TaobaoStockProduct> tbspList = taobaoStockProductMapper.findStockProductStockId(stockId);
		return tbspList;
	}

	@Override
	public List<TaobaoProduct> findTaobaoProductById(Set<Integer> set) {
		List<TaobaoProduct> TaoBaoProList = taobaoProductMapper.findTaobaoProductById(set);
		return TaoBaoProList;
	}

	@Override
	public int insertTaobaoProduct(TaobaoProduct taobaoProduct) {
		taobaoProductMapper.insertTaobaoProduct(taobaoProduct);
		return taobaoProduct.getId();
	}

	@Override
	public int insertTbStockProduct(TaobaoStockProduct record) {
		taobaoStockProductMapper.insertSelective(record);
		return record.getId();
	}

	/**
	 * 查询taobao_product中的产品名称信息
	 */
	@Override
	public PageBean<TaobaoProduct> findTaoBaoProductListPage(PageBean<TaobaoProduct> pageBean,Integer bizId) {
		List<TaobaoProduct> tbProductsList = taobaoProductMapper.selectTaobaoProductListPage(pageBean, bizId);
		pageBean.setResult(tbProductsList);
		return pageBean;
	}
	
	/**
	 * 查询taobao_product中的产品名称信息
	 */
	@Override
	public PageBean<TaobaoProduct> selectTPBytpdIdListPage(PageBean<TaobaoProduct> pageBean,Integer bizId) {
		List<TaobaoProduct> tbProductsList = taobaoProductMapper.selectTPBytpdIdListPage(pageBean, bizId);
		pageBean.setResult(tbProductsList);
		return pageBean;
	}
	
	/**
	 * 查询taobao_product库存信息（操作单引用产品时，弹出框） ou.zongying 2016-10-15
	 */
	@Override
	public PageBean findTaoBaoProductStockListPage(PageBean pageBean) {
		List<Map<String, Object>> result = taobaoProductMapper.findTaoBaoProductStockListPage(pageBean);
		pageBean.setResult(result);
		return pageBean;
	}

	
	@Override
	public TaobaoProduct findByPrimaryKey(Integer id) {
		TaobaoProduct taobaoProduct = taobaoProductMapper.selectByPrimaryKey(id);
		return taobaoProduct;
	}

	@Override
	public int deleteTaoBaoStockProduct(TaobaoStockProduct taobaoStockProduct) {
		int num = taobaoStockProductMapper.deleteStockProductBySkus(taobaoStockProduct);
		return num;
	}

	@Override
	public int deleteByPrimaryKey(Integer id) {
		int nums = taobaoStockMapper.deleteByPrimaryKey(id);
		return nums;
	}

	@Override
	public int insertSelective(TaobaoStock record) {
		return taobaoStockMapper.insertSelective(record);
	}

	@Override
	public TaobaoStockProduct findStockProductInfo(TaobaoStockProduct record) {
		TaobaoStockProduct taobaoStockProduct = taobaoStockProductMapper.findStockProductBySkus(record);
		return taobaoStockProduct;
	}

	@Override
	public TaobaoStockLog selectLogNumByOrderId(Integer stockId) {
		TaobaoStockLog count = taobaoStockLogMapper.selectLogNumByOrderId(stockId);
		return count;
	}

	@Override
	public TaobaoStockLog selectLogByStockDateIdAndOrderId(Integer stockDateId, Integer orderId) {
		TaobaoStockLog log = taobaoStockLogMapper.selectByStockDateIdAndOrderId(stockDateId, orderId);
		return log;
	}

	@Override
	public int delTaoBaoProductStock(TaobaoStockLog stockLog){
		taobaoStockLogMapper.delStockById(stockLog.getId());
		if(stockLog.getStockDateId()!=null)
			taobaoStockDateMapper.updateByLog(stockLog.getStockDateId());  // 计算已售
		return stockLog.getId();
	}

	@Override
	public TaobaoStockLog selectStockLogAllByOrderId(Integer orderId){
		TaobaoStockLog log = taobaoStockLogMapper.selectStockLogAllByOrderId(orderId);
		return log;
	}

	@Override
	public TaobaoStockLog selectStockLogAllByTaobaoOrderId(Integer taobaoOrderId){
		TaobaoStockLog log = taobaoStockLogMapper.selectStockLogAllByTaobaoOrderId(taobaoOrderId);
		return log;
	}
	
	@Override
	public TaobaoProductSkus selectByVid(String vid,String numIid){
		TaobaoProductSkus sku=	taobaoProductSkusMapper.selectByVid(vid,numIid);
		return sku;
	}
	
	@Override
	public int updateTaobaoProductSkus(TaobaoProductSkus record){
		taobaoProductSkusMapper.updateByPrimaryKeySelective(record);
		return record.getId();
	}
	
	@Override
	public int insertTaobaoProductSkus(TaobaoProductSkus record){
		taobaoProductSkusMapper.insertSelective(record);
		return record.getId();
	}
	
	@Override
	public int updateTaobaoProduct(TaobaoProduct record){
		taobaoProductMapper.updateByPrimaryKeySelective(record);
		return record.getId();
	}
	
	@Override
	public TaobaoProductSkus selectSkusById(Integer id){
		TaobaoProductSkus sku=	taobaoProductSkusMapper.selectByPrimaryKey(id);
		return sku;
	}
	@Override
	public void updateState(Integer id,Integer state){
		taobaoStockMapper.updateState(id, state);
	}

	@Override
	public List<TaobaoStockProduct> findStockProductStockIdHavePSI(Integer stockId) {
		List<TaobaoStockProduct> tbspList = taobaoStockProductMapper.findStockProductStockIdHavePSI(stockId);
		return tbspList;
	}
}
