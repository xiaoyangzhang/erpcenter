package com.yimayhd.erpcenter.facade.finance.service.impl;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.yimayhd.erpcenter.facade.finance.errorcode.FinanceErrorCode;
import org.yimayhd.erpcenter.facade.finance.query.DeleteVerifyDetialDTO;
import org.yimayhd.erpcenter.facade.finance.query.QueryVerifyDetailDTO;
import org.yimayhd.erpcenter.facade.finance.query.QueryVerifyDetailTempDTO;
import org.yimayhd.erpcenter.facade.finance.query.SaveVerifyDetailDTO;
import org.yimayhd.erpcenter.facade.finance.query.SearchListDTO;
import org.yimayhd.erpcenter.facade.finance.query.UpdateVerifyDetailDTO;
import org.yimayhd.erpcenter.facade.finance.result.QueryVerifyDetailResult;
import org.yimayhd.erpcenter.facade.finance.result.ResultSupport;
import org.yimayhd.erpcenter.facade.finance.result.VerifyDetailResult;
import org.yimayhd.erpcenter.facade.finance.service.VerifyFacade;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.biz.sales.client.service.finance.FinanceVerifyBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.operation.BookingDeliveryPriceBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.operation.BookingSupplierDetailBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.CommonSaleBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.GroupOrderPriceBiz;
import com.yimayhd.erpcenter.biz.sys.service.PlatformEmployeeBiz;
import com.yimayhd.erpcenter.dal.sales.client.finance.po.FinanceVerify;
import com.yimayhd.erpcenter.dal.sales.client.finance.po.FinanceVerifyDetail;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingDeliveryPrice;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingSupplierDetail;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrderPrice;
import com.yimayhd.erpresource.dal.constants.Constants;

public class VerifyFacadeImpl implements VerifyFacade{

	@Autowired
	private ApplicationContext appContext;
	
	@Autowired
	private PlatformEmployeeBiz platformEmployeeBiz;
	
	@Autowired
	private FinanceVerifyBiz financeVerifyBiz;
	
	@Autowired
	private GroupOrderPriceBiz groupOrderPriceBiz;
	
	@Autowired
	private BookingDeliveryPriceBiz bookingDeliveryPriceBiz;
	
	@Autowired
	private BookingSupplierDetailBiz bookingSupplierDetailBiz;
	
	/**
	 * 获取查询服务
	 * 
	 * @author Jing.Zhuo
	 * @create 2015年8月18日 上午9:34:25
	 * @param svc
	 * @return
	 */
	private CommonSaleBiz getCommonService(String svc) {
		if (StringUtils.isBlank(svc)) {
			svc = "commonSaleBiz";
		}
		return appContext.getBean(svc, CommonSaleBiz.class);
	}

	@Override
	public PageBean searchList(SearchListDTO dto) {
		PageBean pb = new PageBean();
		pb.setPage(dto.getPage());
		pb.setPageSize(dto.getPageSize());
		//如果人员为空并且部门不为空，则取部门下的人id
		if(StringUtils.isBlank(dto.getSaleOperatorIds()) && StringUtils.isNotBlank(dto.getOrgIds())){
			Set<Integer> set = new HashSet<Integer>();
			String[] orgIdArr = dto.getOrgIds().split(",");
			for(String orgIdStr : orgIdArr){
				set.add(Integer.valueOf(orgIdStr));
			}
			set = platformEmployeeBiz.getUserIdListByOrgIdList(dto.getBizId(), set);
			String salesOperatorIds="";
			for(Integer usrId : set){
				salesOperatorIds+=usrId+",";
			}
			if(!salesOperatorIds.equals("")){
				dto.setSaleOperatorIds(salesOperatorIds.substring(0, salesOperatorIds.length()-1));
			}
		}
		Map<String,Object> pms  = dto.getParamters();
		if(null != dto.getSaleOperatorIds() && !"".equals(dto.getSaleOperatorIds())){
			pms.put("operator_id", dto.getSaleOperatorIds());
		}
		pms.put("set", dto.getSet());
		pb.setParameter(pms);
		pb = getCommonService(dto.getSvc()).queryListPage(dto.getSl(), pb);
		return pb;
	}

	@Override
	public ResultSupport changeStatus(String id) {
		
		ResultSupport result = new ResultSupport();
		try{
			financeVerifyBiz.changeStatus(id);
		}catch(Exception ex){
			result.setErrorCode(FinanceErrorCode.MODIFY_ERROR);
			ex.printStackTrace();
		}
		return result;
	}

	@Override
	public VerifyDetailResult verifyDetail(Integer bizId, String verifyId) {
		
		VerifyDetailResult result = new VerifyDetailResult();
		
		FinanceVerify verify = financeVerifyBiz.selectVerify(bizId, verifyId);
		result.setVerify(verify);
		
		List<FinanceVerifyDetail> financeVerifyDetailSum = financeVerifyBiz.selectVerifyDetailSum(bizId, verifyId,verify.getSupplierType());
		result.setFinanceVerifyDetailSum(financeVerifyDetailSum);
		if(financeVerifyDetailSum != null && financeVerifyDetailSum.size()>0){
			Map map= (Map) financeVerifyDetailSum.get(0);
			if(map != null){
				result.setTotalPrice((BigDecimal)map.get("total_price"));
				result.setTotalCash((BigDecimal)map.get("total_cash"));
				
				BigDecimal total_price = (BigDecimal)map.get("total_price");
				BigDecimal total_cash = (BigDecimal)map.get("total_cash");
				if(null==total_price){
					total_price=new BigDecimal(0);
				}
				if(null==total_cash){
					total_cash=new BigDecimal(0);
				}
				result.setTotalNot(total_price.subtract(total_cash));
				result.setTotalAdjust((BigDecimal)map.get("total_adjust"));
			}
		}
		return result;
	}

	@Override
	public QueryVerifyDetailResult queryVerifyDetail(QueryVerifyDetailDTO dto){
		
		QueryVerifyDetailResult result = new QueryVerifyDetailResult();
		
		Integer bizId = dto.getBizId();
		String verifyId = dto.getVerifyId();
		
		FinanceVerify verify = financeVerifyBiz.selectVerify(bizId, verifyId);
		result.setVerify(verify);
		
		Integer supplierType = verify.getSupplierType();
		List<Map<String, Object>> financeVerifyDetailList = financeVerifyBiz.selectVerifyDetailPage(bizId, verifyId, supplierType, dto.getSet());
		
		if(financeVerifyDetailList != null && financeVerifyDetailList.size() > 0){
			
			Map<String, Object> map = null;
			for(int i = 0; i < financeVerifyDetailList.size(); i++){
				map = financeVerifyDetailList.get(i);
				Integer bookingId = map.get("booking_id") != null ? Integer.parseInt(map.get("booking_id").toString()) : 0;
				String details = "";
				if(Constants.TRAVELAGENCY.equals(supplierType)){
					List<GroupOrderPrice> priceList = groupOrderPriceBiz.selectByOrderAndType(bookingId, 0);
					details = groupOrderPriceBiz.concatDetail(priceList);
				}else if(Constants.LOCALTRAVEL.equals(supplierType)){
					List<BookingDeliveryPrice> priceList = bookingDeliveryPriceBiz.getPriceListByBookingId(bookingId);
					details = bookingDeliveryPriceBiz.concatDetail(priceList);
				}else{
					String remark = map.get("remark") != null ? map.get("remark").toString() : "";
					List<BookingSupplierDetail> detailList = bookingSupplierDetailBiz.selectByPrimaryBookId(bookingId);
					details = bookingSupplierDetailBiz.concatDetail(supplierType, remark, detailList);
				}
				
				map.put("details", details);
			}
		}
		result.setFinanceVerifyDetailList(financeVerifyDetailList);
		return result;
	}

	@Override
	public PageBean queyrVerifyDetailTemp(QueryVerifyDetailTempDTO dto) {
		
		PageBean pageBean = new PageBean();
		pageBean.setPage(dto.getPage());
		pageBean.setPageSize(dto.getPageSize());
		pageBean.setParameter(dto.getParamters());
		pageBean = financeVerifyBiz.selectVerifyDetailOrderListPage(pageBean);
		return pageBean;
	}

	@Override
	public PageBean queryVerifyDetailOrders(QueryVerifyDetailTempDTO dto) {
		
		PageBean pageBean = new PageBean();
		pageBean.setPage(dto.getPage());
		pageBean.setPageSize(dto.getPageSize());
		Map<String, Object> pm = dto.getParamters();
		pm.put("set", dto.getSet());
		
		Object orgIds = pm.get("orgIds");
		if(orgIds != null && StringUtils.isNotBlank(orgIds.toString())){
			Set<Integer> set = new HashSet<Integer>();
			String[] orgIdArr = orgIds.toString().split(",");
			for(String orgIdStr : orgIdArr){
				set.add(Integer.valueOf(orgIdStr));
			}
			set = platformEmployeeBiz.getUserIdListByOrgIdList(dto.getBizId(), set);
			String salesOperatorIds="";
			for(Integer usrId : set){
				salesOperatorIds+=usrId+",";
			}
			if(!salesOperatorIds.equals("")){
				pm.put("saleOperatorIds", salesOperatorIds.substring(0, salesOperatorIds.length()-1));
			}
		}
		
		pageBean.setParameter(pm);
		pageBean = financeVerifyBiz.selectVerifyDetailOrderListPage(pageBean);
		return pageBean;
	}

	@Override
	public ResultSupport saveVerifyRecord(FinanceVerify verify, String bizCode) {
		
		Integer verifyId = financeVerifyBiz.insert(verify, bizCode);
		ResultSupport result = new ResultSupport();
		result.setResultMsg(verifyId+"");
		return result;
	}

	@Override
	public List<Map<String, String>> getSupplierNameList(Integer bizId,
			Integer supplierType, String supplierName) {
		List<Map<String,String>> list = financeVerifyBiz.getSupplierNameList(bizId, supplierType, supplierName);
		return list;
	}

	@Override
	public ResultSupport deleteVerifyDetail(DeleteVerifyDetialDTO dto) {
		
		ResultSupport result = new ResultSupport();
		try{
			financeVerifyBiz.deleteVerifyDetail(dto.getBizId(), dto.getVerifyId(), 
					dto.getDetailId(), dto.getTotal(), dto.getTotalCash(), dto.getTotalAdjust());
		}catch(Exception ex){
			result.setErrorCode(FinanceErrorCode.MODIFY_ERROR);
			ex.printStackTrace();
		}
		return result;
	}

	@Override
	public ResultSupport deleteVerify(Integer bizId, Integer id) {
		
		ResultSupport result = new ResultSupport();
		try{
			financeVerifyBiz.deleteVerifyAndDetail(bizId, id);
		}catch(Exception ex){
			result.setErrorCode(FinanceErrorCode.MODIFY_ERROR);
			ex.printStackTrace();
		}
		return result;
	}

	@Override
	public ResultSupport saveVerifyDetail(SaveVerifyDetailDTO dto) {
		
		ResultSupport result = new ResultSupport();
		try{
			financeVerifyBiz.insertDetail(dto.getBizId(), dto.getSupplierType(), dto.getVerifyId(), dto.getIds());
		}catch(Exception ex){
			result.setErrorCode(FinanceErrorCode.MODIFY_ERROR);
			ex.printStackTrace();
		}
		return result;
	}

	@Override
	public ResultSupport updateVerifyDetail(UpdateVerifyDetailDTO dto) {
		
		ResultSupport result = new ResultSupport();
		try{
			financeVerifyBiz.updateDetail(dto.getBizId(), dto.getSupplierType(), dto.getVerifyId(), dto.getRecords());
		}catch(Exception ex){
			result.setErrorCode(FinanceErrorCode.MODIFY_ERROR);
			ex.printStackTrace();
		}
		return result;
	}
}
