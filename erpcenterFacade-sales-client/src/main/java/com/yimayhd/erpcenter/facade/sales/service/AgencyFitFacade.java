package com.yimayhd.erpcenter.facade.sales.service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.dal.product.vo.ProductGroupVo;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrder;
import com.yimayhd.erpcenter.dal.sales.client.sales.vo.FitOrderVO;
import com.yimayhd.erpcenter.dal.sales.client.sales.vo.MergeGroupOrderVO;
import com.yimayhd.erpcenter.facade.sales.query.AgencyOrderQueryDTO;
import com.yimayhd.erpcenter.facade.sales.result.AgencyOrderResult;
import com.yimayhd.erpcenter.facade.sales.result.ResultSupport;
import com.yimayhd.erpcenter.facade.sales.result.WebResult;

public interface AgencyFitFacade {

	List<ProductGroupVo> selectGroupsByProdctIdAndSupplierId(Integer productId,Integer supplierId,Date date);
	AgencyOrderResult toAddGroupOrder(AgencyOrderQueryDTO queryDTO);
	AgencyOrderResult toEditFirOrder(Integer orderId)throws Exception;
	WebResult<Map<String, Object>> saveFitOrderInfo(FitOrderVO fitOrderVO,AgencyOrderQueryDTO queryDTO) throws Exception;
	AgencyOrderResult getFitOrderListForMsglData(AgencyOrderQueryDTO queryDTO);
	PageBean  getInsertFitGroupList(AgencyOrderQueryDTO queryDTO)throws ParseException ;
	ResultSupport updateGroupOrder(GroupOrder groupOrder);
	ResultSupport delGroupOrder(Integer orderId,Integer bizId);
	AgencyOrderResult modifyGroup(String[] idArr);
	List<GroupOrder> toMergeGroup(String[] idArr);
	ResultSupport insertGroupMany(AgencyOrderQueryDTO queryDTO);
	PageBean toImpNotGroupList(PageBean pageBean ,Integer bizId,Set<Integer> set);
	ResultSupport mergeGroup(AgencyOrderQueryDTO queryDTO, MergeGroupOrderVO mergeGroupOrderVO) throws ParseException;
	ResultSupport delYmgGroupOrder(Integer bizId, Integer id) throws ParseException;
}
