package com.yimayhd.erpcenter.facade.sales.service;

import java.util.Set;

import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrder;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.TourGroup;
import com.yimayhd.erpcenter.dal.sales.client.sales.vo.OtherInfoVO;
import com.yimayhd.erpcenter.facade.sales.query.ChangeGroupDTO;
import com.yimayhd.erpcenter.facade.sales.query.ProfitQueryByTourDTO;
import com.yimayhd.erpcenter.facade.sales.query.ToSKConfirmPreviewDTO;
import com.yimayhd.erpcenter.facade.sales.query.grouporder.ToOrderLockTableDTO;
import com.yimayhd.erpcenter.facade.sales.result.*;
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


    public ToPreviewResult createSalesConfirm(Integer orderId,Integer agency,Integer curBizId,Integer orgId);
    public ToPreviewResult createSalesChargeNoRoute(Integer orderId,Integer curBizId,Integer orgId);
    public ToPreviewResult createSalesConfirmNoRoute(Integer orderId,Integer curBizId,Integer orgId);
    public ToPreviewResult createSalesCharge(Integer orderId,Integer curBizId,Integer orgId);
    public ToPreviewResult createGuestNames(Integer orderId,Integer curBizId,Integer orgId);
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
    public ToPreviewResult toPreview(Integer orderId, Integer num,Integer agency,Integer curBizId,Integer rogId);
    public ToSaleChargeResult toSaleCharge(Integer orderId, Integer num,Integer curUserId ,Integer curBizId,Integer orgId);
    public ToSKChargePreviewResult toSKChargePreview(Integer groupId,Integer curUserId ,Integer curBizId,Integer supplierId,Integer orgId);
    public BookingProfitTableResult getSupplierInfo(Integer supplierId);
    public BookingProfitTableResult getBankInfo(Integer curBizId);
    public BookingProfitTableResult bookingProfitTable(TourGroup tourGroup ,Integer curBizId, Set<Integer>orgIdSet);
    public BookingProfitTableResult toProfitQueryList(Integer bizId);
    
	public ToOrderLockListResult toOrderLockList(Integer bizId);
	public ToProfitQueryTableResult toProfitQueryTable(ToOrderLockTableDTO orderLockTableDTO);
	public ProfitQueryByTourResult toProfitQueryTableByTour(ProfitQueryByTourDTO profitQueryByTourDTO);
	public ToSKConfirmPreviewResult toSKConfirmPreview(ToSKConfirmPreviewDTO toSKConfirmPreviewDTO);

    public ProfitEverifyTableResult toProfitEverifyTable(GroupOrder groupOrder, Integer page, Integer pageSize, Integer bizId, Set<Integer> dataUserIdSet);
    public BookingProfitTableResult toSaleProfitListByTour(Integer bizId);
    public ProfitQueryByTourResult toSaleProfitTableByTour(ProfitQueryByTourDTO profitQueryByTourDTO);
    public BookingProfitTableResult operatorSummaryTable(TourGroup tour,Integer bizId,Set<Integer> userIdSet);
    public BookingProfitTableResult toProfitSaleExcel(GroupOrder groupOrder,Integer bizId,Set<Integer> userIdSet);


}
