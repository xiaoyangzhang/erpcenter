package com.yimayhd.erpcenter.facade.sales.service.impl.bak;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.biz.basic.service.RegionBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.GroupOrderBiz;
import com.yimayhd.erpcenter.biz.sys.service.PlatformEmployeeBiz;
import com.yimayhd.erpcenter.biz.sys.service.PlatformOrgBiz;
import com.yimayhd.erpcenter.dal.sales.client.sales.constants.Constants;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrder;
import com.yimayhd.erpcenter.facade.sales.query.grouporder.ToOrderLockTableDTO;
import com.yimayhd.erpcenter.facade.sales.result.BaseStateResult;
import com.yimayhd.erpcenter.facade.sales.result.grouporder.ToOrderLockListResult;
import com.yimayhd.erpcenter.facade.sales.result.grouporder.ToOrderLockTableResult;
import com.yimayhd.erpcenter.facade.sales.service.bak.GroupOrderLockFacade;


/**
 * GroupOrderFacadeImpl
 *
 * @author lilin
 * @date 16/10/25
 */
public class GroupOrderLockFacadeImpl implements GroupOrderLockFacade {
   
	private static final Logger logger = LoggerFactory.getLogger(GroupOrderLockFacadeImpl.class);
   
    @Autowired
    private GroupOrderBiz groupOrderBiz;
    
	@Autowired
	private RegionBiz regionService;
	
	@Autowired
	private PlatformEmployeeBiz platformEmployeeService;

	@Autowired
	private PlatformOrgBiz orgService;
    
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
    public BaseStateResult updateOrderLockState(Integer orderId, Integer orderLockState) {
    	BaseStateResult result=new BaseStateResult();
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
