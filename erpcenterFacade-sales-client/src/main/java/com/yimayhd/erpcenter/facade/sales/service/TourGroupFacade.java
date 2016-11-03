package com.yimayhd.erpcenter.facade.sales.service;

import java.util.Set;

import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrder;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.TourGroup;
import com.yimayhd.erpcenter.dal.sales.client.sales.vo.OtherInfoVO;
import com.yimayhd.erpcenter.facade.sales.query.ChangeGroupDTO;
import com.yimayhd.erpcenter.facade.sales.query.ProfitQueryByTourDTO;
import com.yimayhd.erpcenter.facade.sales.query.ToSKConfirmPreviewDTO;
import com.yimayhd.erpcenter.facade.sales.query.grouporder.ToOrderLockTableDTO;
import com.yimayhd.erpcenter.facade.sales.result.BookingProfitTableResult;
import com.yimayhd.erpcenter.facade.sales.result.GetPushInfoResult;
import com.yimayhd.erpcenter.facade.sales.result.ProfitQueryByTourResult;
import com.yimayhd.erpcenter.facade.sales.result.PushWapResult;
import com.yimayhd.erpcenter.facade.sales.result.ResultSupport;
import com.yimayhd.erpcenter.facade.sales.result.ToAddTourGroupOrderResult;
import com.yimayhd.erpcenter.facade.sales.result.ToChangeGroupResult;
import com.yimayhd.erpcenter.facade.sales.result.ToGroupListResult;
import com.yimayhd.erpcenter.facade.sales.result.ToOtherInfoResult;
import com.yimayhd.erpcenter.facade.sales.result.ToPreviewResult;
import com.yimayhd.erpcenter.facade.sales.result.ToProfitQueryTableResult;
import com.yimayhd.erpcenter.facade.sales.result.ToSKChargePreviewResult;
import com.yimayhd.erpcenter.facade.sales.result.ToSKConfirmPreviewResult;
import com.yimayhd.erpcenter.facade.sales.result.ToSaleChargeResult;
import com.yimayhd.erpcenter.facade.sales.result.TogroupRequirementResult;
import com.yimayhd.erpcenter.facade.sales.result.grouporder.ToOrderLockListResult;

/**
 * @ClassName: ${ClassName}
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @Auther xueshengdong
 * @Date 2016/10/27 10:58
 */
public interface TourGroupFacade {
    public ToChangeGroupResult toChangeGroup(int groupId);
    public ToChangeGroupResult changeGroup(ChangeGroupDTO changeGroupDTO);
    public GetPushInfoResult getPushInfo(int groupId);
    public ResultSupport updateGuide(Integer groupId, Integer driverId, Integer guideId);
    public ResultSupport updateShop(String guideName, Integer guideId, Integer shopId);
    public ResultSupport pushInfo(Integer groupId);
    public PushWapResult pushWap(Integer groupId);
    public ToChangeGroupResult toEditGroup(Integer groupId);
    public ToAddTourGroupOrderResult toAddTourGroupOrder(Integer groupId, Integer orderId,Integer state,String curUserName,Integer CurUserId,Integer bizId);
    public ToChangeGroupResult saveTourGroupOrder(TourGroup tourGroup,GroupOrder groupOrder,Integer curBizId,Integer curUserOrgId,Integer curUserId, String curUserName);
    public ResultSupport unifiedSaveOtherInfo(OtherInfoVO otherInfoVO,String curUserName,Integer CurUserId);
    public ToOtherInfoResult toOtherInfo(Integer orderId,Integer stateFinance, Integer state,Integer curBizId );
    public TogroupRequirementResult togroupRequirement(Integer orderId,Integer stateFinance, Integer state);
    public ToGroupListResult findTourGroupByCondition(	String yesOrNo, GroupOrder groupOrder,Integer curBizId );


    public ToPreviewResult createSalesConfirm(Integer orderId,Integer agency,Integer curBizId);
    public ToPreviewResult createSalesChargeNoRoute(Integer orderId,Integer curBizId);
    public ToPreviewResult createSalesConfirmNoRoute(Integer orderId,Integer curBizId);
    public ToPreviewResult createSalesCharge(Integer orderId,Integer curBizId);
    public ToPreviewResult createGuestNames(Integer orderId,Integer curBizId);
    public ToPreviewResult saleTravelContract(Integer orderId,Integer curBizId);
    public ToPreviewResult saleInsurance(Integer orderId,Integer curBizId);
    public ToPreviewResult toProfitQueryTableZT( GroupOrder order,Set<Integer> set, String supplierType,Integer curBizId);
    public ToPreviewResult getSupplier( String prefixText, String supplierType,Integer curBizId);
    public ToPreviewResult getPriceSupplierName(String keyword, Integer productId,Integer curBizId);
    public ToPreviewResult getSupplierNameForAgency(String keyword, Integer groupId,Integer priceId, String supplierType,Integer curBizId);
    public ToPreviewResult getSupplierName(String keyword, String supplierType,Integer curBizId);
    public ToPreviewResult getSupplierAndContact(String prefixText, Integer supplierId,Integer curBizId);
    public ToPreviewResult getContactName(String keyword, Integer supplierId,Integer curBizId);
    public ToPreviewResult validatorSupplier(String supplierName, Integer supplierId);
    public ToPreviewResult getSelSupplier( Integer groupId,Integer priceId, String prefixText, String supplierType);
    public ToPreviewResult toPreview(Integer orderId, Integer num,Integer agency,Integer curBizId);
    public ToSaleChargeResult toSaleCharge(Integer orderId, Integer num,Integer curUserId ,Integer curBizId);
    public ToSKChargePreviewResult toSKChargePreview(Integer groupId,Integer curUserId ,Integer curBizId,Integer supplierId);
    public BookingProfitTableResult getSupplierInfo(Integer supplierId);
    public BookingProfitTableResult getBankInfo(Integer curBizId);
    public BookingProfitTableResult bookingProfitTable(TourGroup tourGroup ,Integer curBizId, Set<Integer>orgIdSet);
    public BookingProfitTableResult toProfitQueryList(Integer bizId);
    
	public ToOrderLockListResult toOrderLockList(Integer bizId);
	public ToProfitQueryTableResult toProfitQueryTable(ToOrderLockTableDTO orderLockTableDTO);
	public ProfitQueryByTourResult toProfitQueryTableByTour(ProfitQueryByTourDTO profitQueryByTourDTO);
	public ToSKConfirmPreviewResult toSKConfirmPreview(ToSKConfirmPreviewDTO toSKConfirmPreviewDTO);




}
