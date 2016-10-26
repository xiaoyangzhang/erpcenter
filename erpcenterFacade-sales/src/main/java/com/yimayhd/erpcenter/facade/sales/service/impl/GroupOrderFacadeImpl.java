package com.yimayhd.erpcenter.facade.sales.service.impl;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONArray;
import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.biz.basic.service.RegionBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.GroupOrderBiz;
import com.yimayhd.erpcenter.biz.sys.service.PlatformEmployeeBiz;
import com.yimayhd.erpcenter.biz.sys.service.PlatformOrgBiz;
import com.yimayhd.erpcenter.dal.sales.client.sales.constants.Constants;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.AiYouBean;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrder;
import com.yimayhd.erpcenter.facade.sales.errorcode.SalesErrorCode;
import com.yimayhd.erpcenter.facade.sales.query.ToOrderLockTableDTO;
import com.yimayhd.erpcenter.facade.sales.result.FitUpdateStateResult;
import com.yimayhd.erpcenter.facade.sales.result.ListResultSupport;
import com.yimayhd.erpcenter.facade.sales.result.ToOrderLockListResult;
import com.yimayhd.erpcenter.facade.sales.result.ToOrderLockTableResult;
import com.yimayhd.erpcenter.facade.sales.service.GroupOrderFacade;


/**
 * GroupOrderFacadeImpl
 *
 * @author lilin
 * @date 16/10/25
 */
public class GroupOrderFacadeImpl implements GroupOrderFacade {
   
	private static final Logger LOGGER = LoggerFactory.getLogger(GroupOrderFacadeImpl.class);
   
    @Autowired
    private GroupOrderBiz groupOrderBiz;
    
//	@Autowired
//	private SupplierGuideService guideService;
//	@Autowired
//	private DicService dicService;
    
	@Autowired
	private RegionBiz regionService;
	
//	@Autowired
//	private GroupOrderService groupOrderService;
//	@Autowired
//	private GroupOrderGuestService groupOrderGuestService;
//	@Autowired
//	private GroupOrderPriceService groupOrderPriceService;
//	@Autowired
//	private GroupOrderTransportService groupOrderTransportService;
//	@Autowired
//	private GroupRequirementService groupRequirementService;
	
	@Autowired
	private PlatformEmployeeBiz platformEmployeeService;
	
//	@Autowired
//	private SupplierService supplierService;
//	@Autowired
//	private ProductInfoService productInfoService;
//	@Autowired
//	private ProductGroupService productGroupService;
//	@Autowired
//	private ProductGroupExtraItemService productGroupExtraItemService;
//
//	@Autowired
//	private ProductGroupPriceService productGroupPriceService;
//	@Autowired
//	private ProductRouteService productRouteService;
//	@Autowired
//	private GroupRouteService groupRouteService;
//	@Autowired
//	private TourGroupService tourGroupService;

	@Autowired
	private PlatformOrgBiz orgService;
	
//	@Autowired
//	private ProductRemarkService productRemarkService;
//	@Autowired
//	private ProductGroupSupplierService productGroupSupplierService;
//	@Autowired
//	private BookingGuideService bookingGuideService;
//	@Autowired
//	private BookingSupplierDetailService bookingSupplierDetailService;
//
//	@Autowired
//	private BizSettingCommon settingCommon;
//
//	@Autowired
//	private BizSettingCommon bizSettingCommon;
//	@Autowired
//	private BookingDeliveryService deliveryService;
//
//	@Autowired
//	private GroupRouteService routeService;
//
//	@Autowired
//	private BookingShopService shopService;

//	@Autowired
//	private BookingSupplierService bookingSupplierService;
//	@Autowired
//	private BookingSupplierDetailService detailService;
//	@Autowired
//	private AirTicketRequestService airTicketRequestService ;
//	
//	@Autowired
//	private SupplierService supplierInfoService ;

    @Override
    public ListResultSupport<AiYouBean> getAiYourOrders(String code, String port, String startDate, String endDate, String groupNum, Integer bizId) {

        ListResultSupport<AiYouBean> result = new ListResultSupport<AiYouBean>();

        // 访问爱游系统 (yihg-aiyou-api[数据查询系统])
        CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
        // 访问地址
        HttpPost httpPost = new HttpPost(Constants.AIYOU_API_URL + "/opBill/orderList.do");
        // 访问参数
        List<NameValuePair> nameValuePairList = new
                ArrayList<NameValuePair>();
        nameValuePairList.add(new BasicNameValuePair("dbType", port));
        nameValuePairList.add(new BasicNameValuePair("code", code));
        nameValuePairList.add(new BasicNameValuePair("startDate",
                startDate));
        nameValuePairList.add(new BasicNameValuePair("endDate", endDate));
        nameValuePairList.add(new BasicNameValuePair("groupNum", groupNum));
        List<AiYouBean> aiyouOrderList = new ArrayList<AiYouBean>();
        try {

            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairList));

            CloseableHttpResponse closeableHttpResponse;
            closeableHttpResponse = closeableHttpClient.execute(httpPost);

            String orderString = "";
            try {
                HttpEntity httpEntity = closeableHttpResponse.getEntity();
                orderString = EntityUtils.toString(httpEntity);
            } catch (IOException e) {
                LOGGER.error("", e);
                result.setErrorCode(SalesErrorCode.QUERY_ERROR);
                return result;
            } finally {
                closeableHttpResponse.close();
            }

            List<AiYouBean> aiYouBeans = JSONArray.parseArray(orderString, AiYouBean.class);

            if (aiYouBeans != null && aiYouBeans.size() > 0) {
                List<GroupOrder> orders = groupOrderBiz.selectAiYouOrders(bizId, startDate,
                        endDate);
                for (AiYouBean aiYouBean : aiYouBeans) {
                    aiYouBean.setSupplierCode(port);
                    String yyyy = aiYouBean.getGroup_num().substring(0, 4);
                    String MM = aiYouBean.getGroup_num().substring(4, 6);
                    String dd = aiYouBean.getGroup_num().substring(6, 8);
                    aiYouBean.setDate(yyyy + "-" + MM + "-" + dd);
                    if (orders != null && orders.size() > 0) {
                        for (GroupOrder groupOrder : orders) {
                            if (aiYouBean.getGroup_id().equals(groupOrder.getAiyouGroupId())) {
                                aiYouBean.setIsImport(1);
                                break;
                            }
                        }
                    }
                }
                aiyouOrderList.addAll(aiYouBeans);
            }

            httpPost.abort();
        } catch (Exception ex) {
            LOGGER.error("",ex);
            result.setErrorCode(SalesErrorCode.QUERY_ERROR);
            return result;
        }
        result.setValues(aiyouOrderList);
        return result;
    }
    
    @Override
    public ToOrderLockListResult toOrderLockList(Integer bizId) {
    	
    	ToOrderLockListResult result=new ToOrderLockListResult();

    	result.setAllProvince(regionService.getAllProvince());
		result.setOrgJsonStr(orgService.getComponentOrgTreeJsonStr(bizId));
		result.setOrgUserJsonStr(platformEmployeeService.getComponentOrgUserTreeJsonStr(bizId));
		
		return result;
    }
    
    @Override
    public ToOrderLockTableResult toOrderLockTable(ToOrderLockTableDTO orderLockTableDTO) throws ParseException{
		
    	Integer bizId = orderLockTableDTO.getBizId();
    	GroupOrder order = orderLockTableDTO.getOrder();
    	Set<Integer> userIdSet = orderLockTableDTO.getUserIdSet();
    	
    	if (order != null && order.getDateType() == 2) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			if (!"".equals(order.getStartTime())) {
				order.setStartTime(sdf.parse(order.getStartTime()).getTime() + "");
			}
			if (!"".equals(order.getEndTime())) {
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(sdf.parse(order.getEndTime()));
				calendar.add(Calendar.DAY_OF_MONTH, +1);// 让日期加1
				order.setEndTime(calendar.getTime().getTime() + "");
			}
		}

		PageBean<GroupOrder> pageBean = new PageBean<GroupOrder>();
		pageBean.setPage(order.getPage());
		pageBean.setPageSize(order.getPageSize() == null ? Constants.PAGESIZE : order.getPageSize());
		pageBean.setParameter(order);
		// 如果人员为空并且部门不为空，则取部门下的人id
		if (StringUtils.isBlank(order.getSaleOperatorIds()) && StringUtils.isNotBlank(order.getOrgIds())) {
			Set<Integer> set = new HashSet<Integer>();
			String[] orgIdArr = order.getOrgIds().split(",");
			for (String orgIdStr : orgIdArr) {
				set.add(Integer.valueOf(orgIdStr));
			}
			set = platformEmployeeService.getUserIdListByOrgIdList(bizId, set);
			String salesOperatorIds = "";
			for (Integer usrId : set) {
				salesOperatorIds += usrId + ",";
			}
			if (!salesOperatorIds.equals("")) {
				order.setSaleOperatorIds(salesOperatorIds.substring(0, salesOperatorIds.length() - 1));
			}
		}
		pageBean = groupOrderBiz.selectOrderLockByConListPage(pageBean, bizId,userIdSet);
		String totalPb = groupOrderBiz.selectOrderLockByCon(pageBean, bizId,userIdSet);
		
		ToOrderLockTableResult result=new ToOrderLockTableResult();
		result.setPageBean(pageBean);
		result.setTotalPb(totalPb);
		
    	return result;
    }
    
    @Override
    public FitUpdateStateResult updateOrderLockState(Integer orderId, Integer orderLockState) {
    	FitUpdateStateResult result=new FitUpdateStateResult();
		int i = groupOrderBiz.updateOrderLockState(orderId, orderLockState);
		if(i==1){
			result.setSuccess(true);
		}else{
			result.setSuccess(false);
			result.setError("服务器忙！");
		}
    	return result;
    }
}
