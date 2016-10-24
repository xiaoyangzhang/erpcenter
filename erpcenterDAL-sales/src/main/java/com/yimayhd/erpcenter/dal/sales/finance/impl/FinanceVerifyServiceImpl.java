package com.yimayhd.erpcenter.dal.sales.finance.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.common.exception.ClientException;
import com.yimayhd.erpcenter.dal.basic.utils.DateUtils;
import com.yimayhd.erpcenter.dal.sales.client.finance.po.FinanceVerify;
import com.yimayhd.erpcenter.dal.sales.client.finance.po.FinanceVerifyDetail;
import com.yimayhd.erpcenter.dal.sales.client.finance.service.FinanceVerifyDal;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingDeliveryPrice;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingSupplierDetail;
import com.yimayhd.erpcenter.dal.sales.client.operation.service.BookingDeliveryPriceService;
import com.yimayhd.erpcenter.dal.sales.client.operation.service.BookingSupplierDetailService;
import com.yimayhd.erpcenter.dal.sales.client.sales.constants.Constants;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrderPrice;
import com.yimayhd.erpcenter.dal.sales.client.sales.service.GroupOrderPriceService;
import com.yimayhd.erpcenter.dal.sales.finance.dao.FinanceVerifyMapper;

/**
 * 对账管理
 */
public class FinanceVerifyServiceImpl implements FinanceVerifyDal {

	@Autowired
	private SqlSessionTemplate ss;

	@Autowired
	private FinanceVerifyMapper financeVerifyMapper;
	
	@Autowired
	private BookingSupplierDetailService bookingSupplierDetailService;
	
	@Autowired
	private GroupOrderPriceService groupOrderPriceService;
	
	@Autowired
	private BookingDeliveryPriceService bookingDeliveryPriceService;
	
	/**
	 * 修改对账单状态
	 */
	@Override
	public void changeStatus(String id) {
		financeVerifyMapper.changeStatus(id);
		
	}
	@Override
	public List<Map<String, Object>> selectVerifyDetailPage(Integer bizId,
			String verifyId,Integer supplierType, Set<Integer> set) {
		return financeVerifyMapper.selectVerifyDetailPage(bizId, verifyId, supplierType, set);
	}
	@Override
	public List<FinanceVerifyDetail> selectVerifyDetailSum(Integer bizId,
			String verifyId,Integer supplierType) {
		return financeVerifyMapper.selectVerifyDetailSum(bizId,verifyId,supplierType);
	}
	
	/**
	 * 新增对账单
	 */
	@Transactional
	@Override
	public Integer insert(FinanceVerify verify, String bizCode) throws ClientException {
		
		int supplierNameCount = financeVerifyMapper.getSupplierNameCount(verify.getBizId(), verify.getSupplierType(), verify.getSupplierName());
		if(supplierNameCount == 0){
			
			if(verify.getSupplierType() == Constants.TRAVELAGENCY){
				throw new ClientException("该组团社不存在");
			}else if(verify.getSupplierType() == Constants.LOCALTRAVEL){
				throw new ClientException("该地接社不存在");
			}else{
				throw new ClientException("该商家不存在");
			}
				
		}
		
		Integer sort = financeVerifyMapper.getMaxSort(verify.getBizId());
		if(sort == null){
			sort = 1;
		}else{
			sort ++;
		}
		String verifyCode = this.generateVerifyCode(bizCode, verify.getSupplierType(), sort);
		verify.setSort(sort);
		verify.setVerifyCode(verifyCode);
		
		Integer verifyId = financeVerifyMapper.insert(verify);
		verifyId = financeVerifyMapper.selectInsertKey();
		
		return verifyId;
	}
	
	@Override
	public Integer getMaxSort(Integer bizId) {
		return financeVerifyMapper.getMaxSort(bizId);
	}
	

	@Override
	public List<Map<String, String>> getSupplierNameList(Integer bizId, Integer supplierType, String supplierName) {
		return financeVerifyMapper.getSupplierNameList(bizId, supplierType, supplierName);
	}
	
	/**
	 * 生成对账单号
	 * 对账单编号规则：
	 * YM-CT20151008-1
	 * 企业编码-类型 日期-递增编号
	 */
	@Override
	public String generateVerifyCode(String bizCode, Integer supplierType, Integer sort) {
		
		String acronym = Constants.SUPPLIERSHORTCODEMAP.get(supplierType);
		String currDate = DateUtils.format(new Date(), DateUtils.FORMAT_SHORT);
		
		StringBuilder verifyCode = new StringBuilder();
		verifyCode.append(bizCode);
		verifyCode.append("-");
		verifyCode.append(acronym);
		verifyCode.append(currDate.replaceAll("-", ""));
		verifyCode.append("-");
		verifyCode.append(sort);
		
		return verifyCode.toString();
	}
	@Override
	public void deleteVerify(Integer bizId, Integer id) {
		financeVerifyMapper.deleteVerify(bizId, id);
	}
	
	@Override
	public FinanceVerify selectVerify(Integer bizId, String verifyId) {
		return financeVerifyMapper.selectVerify(bizId, verifyId);
	}
	
	/**
	 * 添加对账单详情
	 */
	@Transactional
	@Override
	public void insertDetail(Integer bizId, Integer supplierType, Integer verifyId, String ids){
		
		if(ids == null || "".equals(ids)){
			return;
		}
		
		FinanceVerifyDetail detail = null;
		String[] idArr = ids.split(",");
		for(int i = 0; i < idArr.length; i++){
			
			Integer id = Integer.parseInt(idArr[i]);
			detail = new FinanceVerifyDetail();
			detail.setBizId(bizId);
			detail.setVerifyId(verifyId);
			
			if(supplierType == Constants.TRAVELAGENCY){
				detail.setOrderId(id);
			}else{
				detail.setBookingId(id);
			}
			BigDecimal decimal = new BigDecimal(0);
			detail.setTotalAdjust(decimal);
			financeVerifyMapper.insertDetail(detail);
		}
		
		financeVerifyMapper.updateVerifyState(bizId, verifyId, 0); //将对账单状态改为“未确认”
		
	}
	
	/**
	 * 更新对账单详情
	 * records
	 * [
	 * 	{orderId:1212,remark:asdf,total_adjust},
	 * 	{orderId:1212,remark:asdf,total_adjust}
	 * ]
	 */
	@Transactional
	@Override
	public void updateDetail(Integer bizId, Integer supplierType, Integer verifyId, String records){
		
		if(records == null || "".equals(records)){
			return;
		}
		
		financeVerifyMapper.deleteVerifyDetail(bizId, verifyId);
		
		BigDecimal totalPrice = new BigDecimal(0);
		BigDecimal totalCash = new BigDecimal(0);
		BigDecimal totalAdjust = new BigDecimal(0);
		
		FinanceVerifyDetail detail = null;
		JSONArray ja = JSONArray.parseArray(records);
		JSONObject jo = null;
		for(int i = 0; i < ja.size(); i++){
			jo = ja.getJSONObject(i);
			detail = new FinanceVerifyDetail();
			detail.setBizId(bizId);
			detail.setVerifyId(verifyId);
			
			Integer id = jo.getInteger("orderId");
			if(supplierType == Constants.TRAVELAGENCY){
				detail.setOrderId(id);
			}else{
				detail.setBookingId(id);
			}
			BigDecimal adjust = jo.getBigDecimal("totalAdjust");
			detail.setTotalAdjust(adjust);
			detail.setRemark(jo.getString("remark"));
			financeVerifyMapper.insertDetail(detail);
			
			if(jo.getBigDecimal("total") != null){
				totalPrice = totalPrice.add(jo.getBigDecimal("total"));
			}
			if(jo.getBigDecimal("totalCash") != null){
				totalCash = totalCash.add(jo.getBigDecimal("totalCash"));
			}
			if(adjust != null){
				totalAdjust = totalAdjust.add(adjust);
			}
		}
		
		FinanceVerify verify = new FinanceVerify();
		verify.setBizId(bizId);
		verify.setId(verifyId);
		verify.setTotalPrice(totalPrice);
		verify.setTotalCash(totalCash);
		verify.setTotalRecord(new BigDecimal(ja.size()));
		verify.setTotalAdjust(totalAdjust);
		verify.setVerifyState(0); //将对账单状态改为未确认
		financeVerifyMapper.updateVerify(verify);
		
	}
	
	@Transactional
	@Override
	public void deleteVerifyAndDetail(Integer bizId, Integer id) {
		financeVerifyMapper.deleteVerify(bizId, id);
		financeVerifyMapper.deleteVerifyDetail(bizId, id);
	}
	
	@Transactional
	@Override
	public void deleteVerifyDetail(Integer bizId, Integer verifyId, Integer detailId, 
			BigDecimal total, BigDecimal totalCash, BigDecimal totalAdjust) {
		
		financeVerifyMapper.deleteVerifyDetailById(bizId, detailId);
		
		FinanceVerify verify = new FinanceVerify();
		verify.setId(verifyId);
		verify.setBizId(bizId);
		verify.setTotalPrice(total);
		verify.setTotalCash(totalCash);
		verify.setTotalAdjust(totalAdjust);
		verify.setTotalRecord(new BigDecimal(1));
		verify.setVerifyState(0); //将对账单状态改为“未确认”
		financeVerifyMapper.updateVerifyTotal(verify);
	}
	
	@Override
	public PageBean selectVerifyDetailOrderListPage(PageBean pageBean) {
		
		Map requestMap = (Map)pageBean.getParameter();
		Integer supplierType = requestMap.get("supplierType") != null ? Integer.parseInt(requestMap.get("supplierType").toString()) : 0;
		
		List<Map<String, Object>> orders = financeVerifyMapper.selectVerifyDetailOrderListPage(pageBean); 
		if(orders != null && orders.size() > 0){
			Map<String, Object> map = null;
			for(int i = 0; i < orders.size(); i++){
				map = orders.get(i);
				
				Integer bookingId = map.get("id") != null ? Integer.parseInt(map.get("id").toString()) : 0;
				String details = "";
				if(Constants.TRAVELAGENCY.equals(supplierType)){
					List<GroupOrderPrice> priceList = groupOrderPriceService.selectByOrderAndType(bookingId, 0);
					details = groupOrderPriceService.concatDetail(priceList);
				}else if(Constants.LOCALTRAVEL.equals(supplierType)){
					List<BookingDeliveryPrice> priceList = bookingDeliveryPriceService.getPriceListByBookingId(bookingId);
					details = bookingDeliveryPriceService.concatDetail(priceList);
				}else{
					String remark = map.get("remark") != null ? map.get("remark").toString() : "";
					List<BookingSupplierDetail> detailList = bookingSupplierDetailService.selectByPrimaryBookId(bookingId);
					details = bookingSupplierDetailService.concatDetail(supplierType, remark, detailList);
				}
				map.put("details", details);
			}
		}
		pageBean.setResult(orders);
		return pageBean;
	}

}
