package com.yimayhd.erpcenter.facade.tj.service.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.biz.basic.service.DicBiz;
import com.yimayhd.erpcenter.biz.basic.service.LogOperatorBiz;
import com.yimayhd.erpcenter.biz.basic.service.RegionBiz;
import com.yimayhd.erpcenter.biz.product.service.ProductInfoBiz;
import com.yimayhd.erpcenter.biz.product.service.TrafficResBiz;
import com.yimayhd.erpcenter.biz.product.service.TrafficResProductBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.finance.FinanceBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.GroupOrderBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.GroupOrderGuestBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.GroupOrderPriceBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.GroupOrderTransportBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.SpecialGroupOrderBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.TourGroupBiz;
import com.yimayhd.erpcenter.biz.sys.service.PlatformEmployeeBiz;
import com.yimayhd.erpcenter.biz.sys.service.PlatformOrgBiz;
import com.yimayhd.erpcenter.common.contants.BasicConstants;
import com.yimayhd.erpcenter.common.contants.BasicConstants.LOG_ACTION;
import com.yimayhd.erpcenter.common.util.LogFieldUtil;
import com.yimayhd.erpcenter.dal.basic.po.DicInfo;
import com.yimayhd.erpcenter.dal.basic.po.LogOperator;
import com.yimayhd.erpcenter.dal.product.constans.Constants;
import com.yimayhd.erpcenter.dal.product.po.TrafficResProduct;
import com.yimayhd.erpcenter.dal.product.po.TrafficResStocklog;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrder;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.TourGroup;
import com.yimayhd.erpcenter.dal.sales.client.sales.vo.MergeGroupOrderVO;
import com.yimayhd.erpcenter.dal.sys.po.PlatformEmployeePo;
import com.yimayhd.erpcenter.facade.tj.client.query.LockListTableDTO;
import com.yimayhd.erpcenter.facade.tj.client.query.TrafficOrderDTO;
import com.yimayhd.erpcenter.facade.tj.client.result.AdminOrderListTableResult;
import com.yimayhd.erpcenter.facade.tj.client.result.LockListTableResult;
import com.yimayhd.erpcenter.facade.tj.client.service.ResTrafficOrderFacade;

public class ResTrafficOrderFacadeImpl implements ResTrafficOrderFacade{
	private static final Logger LOGGER = LoggerFactory.getLogger("ResTrafficOrderFacadeImpl");
	
	@Autowired
	private DicBiz dicBiz;
	@Autowired
	private PlatformOrgBiz platformOrgBiz;
	@Autowired
	private PlatformEmployeeBiz platformEmployeeBiz;
	@Autowired
	private TrafficResBiz trafficResBiz;
	@Autowired
	private RegionBiz regionBiz;
	@Autowired
	private ProductInfoBiz productInfoBiz;
	@Autowired
	private SpecialGroupOrderBiz specialGroupOrderBiz;
	@Autowired
	private GroupOrderBiz groupOrderBiz;
	@Autowired
	private GroupOrderPriceBiz groupOrderPriceBiz;
	@Autowired
	private GroupOrderGuestBiz groupOrderGuestBiz;
	@Autowired
	private GroupOrderTransportBiz groupOrderTransportBiz;
	@Autowired
	private FinanceBiz financeBiz;
	@Autowired
	private TrafficResProductBiz trafficResProductBiz;
	@Autowired
	private LogOperatorBiz logOperatorBiz;
	@Autowired
	private TourGroupBiz tourGroupBiz;
	
	public List<DicInfo> loadGroupOrderInfo(int bizId){
		List<DicInfo> brandProductList = dicBiz.getListByTypeCode(BasicConstants.CPXL_PP, bizId);
		return brandProductList;
	}
	
	/**
	 * 订单数据展示Table
	 * @param request
	 * @param model
	 * @param pageSize
	 * @param page
	 * @return
	 */
	public LockListTableResult lockListTable(LockListTableDTO lockListTableDTO){
		LockListTableResult result = new LockListTableResult();
		int bizId = lockListTableDTO.getBizId();
		Map<String,Object> pmBean  = lockListTableDTO.getPmBean();
		Integer page = lockListTableDTO.getPage();
		Integer pageSize = lockListTableDTO.getPageSize();
		Set<Integer> dataUserIdSet = lockListTableDTO.getDataUserIdSet();
		PageBean<GroupOrder> pageBean = new PageBean<GroupOrder>();
		if(page==null){
			pageBean.setPage(1);
		}else{
			pageBean.setPage(page);
		}
		if(pageSize==null){
			pageBean.setPageSize(Constants.PAGESIZE);
		}else{
			pageBean.setPageSize(pageSize);
		}
		pageBean.setPage(page);
		pageBean.setParameter(pmBean);
		result.setSumGroupOrder(groupOrderBiz.sumGroupOrder(pageBean));
		pageBean=groupOrderBiz.selectResGroupOrderList(pageBean,
				bizId,dataUserIdSet);
		result.setPageBean(pageBean);
		return result;
	}
	
	public String toUpdateProductPrice(TrafficOrderDTO trafficOrderDTO){
		String id = trafficOrderDTO.getId();
		String causeRemark = trafficOrderDTO.getCauseRemark();
		int bizId = trafficOrderDTO.getBizId();
		int userId = trafficOrderDTO.getUserId();
		PlatformEmployeePo curUser = trafficOrderDTO.getCurUser();
		String myBizCode = trafficOrderDTO.getMyBizCode();
		//此方法为：分销商取消订单
		groupOrderBiz.findUpdateAdminOrderState(id, causeRemark);
		String ret = Update_OrderState(bizId,userId,myBizCode,curUser, id, "2");
		return ret;
	}
	
	/**
	 * 管理员端订单列表页面跳转
	 * @param request
	 * @param model
	 * @return
	 */
	public List<DicInfo> loadAdminOrderInfo(int bizId){
		List<DicInfo> brandProductList = dicBiz.getListByTypeCode(BasicConstants.CPXL_PP, bizId);
		return brandProductList;
	}
	
	/**
	 * 订单数据展示Table
	 * @param request
	 * @param model
	 * @param pageSize
	 * @param page
	 * @return
	 */
	public AdminOrderListTableResult adminOrderListTable(TrafficOrderDTO trafficOrderDTO){
		AdminOrderListTableResult result = new AdminOrderListTableResult();
		Integer page = trafficOrderDTO.getPage();
		Integer pageSize = trafficOrderDTO.getPageSize();
		Map<String, Object> pmBean = trafficOrderDTO.getPm();
		int bizId = trafficOrderDTO.getBizId();
		Set<Integer> dataUserIdSet = trafficOrderDTO.getDataUserIdSet();
		PageBean<GroupOrder> pageBean = new PageBean<GroupOrder>();
		if(page==null){
			pageBean.setPage(1);
		}else{
			pageBean.setPage(page);
		}
		if(pageSize==null){
			pageBean.setPageSize(Constants.PAGESIZE);
		}else{
			pageBean.setPageSize(pageSize);
		}
		pageBean.setPage(page);
		pageBean.setParameter(pmBean);
		result.setSumResAdminOrder(groupOrderBiz.sumResAdminOrder(pageBean));
		pageBean=groupOrderBiz.selectResAdminOrderList(pageBean,bizId,dataUserIdSet);
		result.setPageBean(pageBean);
		//权限分配
//		UserSession user = WebUtils.getCurrentUserSession(request);
//		Map<String,Boolean> optMap = user.getOptMap();
//		String menuCode = PermissionConstants.TRAFFICRES;
//		model.addAttribute("optMap_EDIT", optMap.containsKey(menuCode.concat("_").concat(PermissionConstants.TRAFFICRES_EDIT)));
//		model.addAttribute("optMap_PAY", optMap.containsKey(menuCode.concat("_").concat(PermissionConstants.TRAFFICRES_PAY)));
		return result;
	}
	
	/**
	 * 取消订单，更新操作
	 * @param request
	 * @param id
	 * @param causeRemark
	 * @param model
	 * @return
	 * @throws ParseException 
	 */
	public String toUpdateAdminExtResState(TrafficOrderDTO trafficOrderDTO){
		String id = trafficOrderDTO.getId();
		String causeRemark = trafficOrderDTO.getCauseRemark();
		int bizId = trafficOrderDTO.getBizId();
		int userId = trafficOrderDTO.getUserId();
		PlatformEmployeePo curUser = trafficOrderDTO.getCurUser();
		String myBizCode = trafficOrderDTO.getMyBizCode();
		
		groupOrderBiz.findUpdateAdminOrderState(id,causeRemark);
		String ret = Update_OrderState(bizId,userId,myBizCode,curUser, id, "2");
		return ret;
		
	}
	
	
	/**
	 * 跳转至状态修改页面
	 * @param request
	 * @param model
	 * @param id
	 * @param resId
	 * @return
	 */
	public GroupOrder toUpdateOrderState(String id){
		//将选中的id传入页面
		GroupOrder orderBean = groupOrderBiz.selectByPrimaryKey(Integer.valueOf(id));
		return orderBean;
	}
	
	/**
	 * 保存修改的订单状态
	 * @param request
	 * @param model
	 * @param id
	 * @return
	 * @throws ParseException 
	 */
	public String toUpdateExtResState(TrafficOrderDTO trafficOrderDTO){
		String id = trafficOrderDTO.getId();
		String extResState = trafficOrderDTO.getExtResState();
		int bizId = trafficOrderDTO.getBizId();
		int userId = trafficOrderDTO.getUserId();
		PlatformEmployeePo curUser = trafficOrderDTO.getCurUser();
		String myBizCode = trafficOrderDTO.getMyBizCode();
		String ret = Update_OrderState(bizId,userId,myBizCode,curUser, id, extResState);
		return ret;
	}
	
	private String Update_OrderState(int bizId,int userId,String myBizCode,PlatformEmployeePo curUser, String id,String extResState){
		//  0待确认（占位）、1已确认（占位）、2取消（退还）
		// 订单状态 1正常  -1删除  0 待确认
		
		String briefStr = "";
		Integer orderState = 0;
		GroupOrder order = groupOrderBiz.selectByPrimaryKey(Integer.valueOf(id));
		TrafficResStocklog updateStockLog=new TrafficResStocklog();
		if("0".equals(extResState)){
			briefStr = "更改状态为：待确认";
			updateStockLog.setAdjustState(0);
			order.setExtResConfirmId(curUser.getEmployeeId());
			order.setExtResConfirmName(curUser.getName());
		}
		if("1".equals(extResState)){
			orderState = 1;
			updateStockLog.setAdjustState(1);
			briefStr = "更改状态为：已确认";
			order.setExtResConfirmId(curUser.getEmployeeId());
			order.setExtResConfirmName(curUser.getName());
			toGroup(order.getId(), order.getOrderType(), bizId,userId,myBizCode,curUser);
		}
		if("2".equals(extResState)){
			updateStockLog.setAdjustState(2);
			briefStr = "更改状态为：取消";
		}
		//更新group_order表
		order.setExtResState(Integer.valueOf(extResState));
		order.setState(orderState);
		int nums = groupOrderBiz.loadUpdateExtResState(order);
		
		//若状态改为已确认，则需要更改 traffic_res_stocklog 预留订单状态为已确认
		updateStockLog.setResId(order.getExtResId());
		updateStockLog.setOrderId(order.getId());
		trafficResBiz.updateStockLog_AdjustState(updateStockLog);
		
		//更新产品已售数量  
		TrafficResProduct trafficResProduct=trafficResBiz.selectTrafficProductInfoByProductCode(order.getProductId(), order.getExtResId());
		if (null != trafficResProduct){
			Integer sumPerson = groupOrderBiz.selectSumPersonByProductId(trafficResProduct.getResId(), trafficResProduct.getProductCode(), order.getDepartureDate());
			trafficResProduct.setNumSold(sumPerson);
			trafficResBiz.updateNumSoldById(trafficResProduct);
		}
				
		//更新库存
		trafficResBiz.updateStockOrStockDisable(order.getExtResId());
		
		//日志
		Integer groupId = order.getGroupId()==null?0:order.getGroupId();
		insertLog(LogFieldUtil.getLog_Instant(curUser.getBizId(), curUser.getName(), LOG_ACTION.UPDATE, "group_order", order.getId(), groupId ,briefStr, null, null));

		Map<String,Object> map = new HashMap<String,Object>();
		map.put("success", nums);
		return JSON.toJSONString(map);

	}
	
	private void toGroup(Integer orderId,Integer orderType,int bizId,int userId,String myBizCode,PlatformEmployeePo curUser){
		GroupOrder order = groupOrderBiz.selectByPrimaryKey(orderId);
		if(orderType==1){				// 团队
			List<MergeGroupOrderVO> result = new ArrayList<MergeGroupOrderVO>();
				MergeGroupOrderVO mov = new MergeGroupOrderVO();
				mov.getOrderList().add(order);
				result.add(mov);
			try {
				specialGroupOrderBiz.mergetGroupResTeam(result, bizId,userId, curUser.getName(), myBizCode);
			} catch (ParseException e) {
				LOGGER.error("mergetGroupResTeam error! result={},bizId={},userId={},userName={},myBizCode={}",result,bizId,userId,curUser.getName(),myBizCode,e);
			}
		}
		if(orderType==0){          // 散客
			List<TourGroup> groupList  =tourGroupBiz.selecGroupBefAutoMergerGroup(order.getBizId(), order.getDepartureDate(), order.getProductId());
			if (groupList != null && groupList.size() > 0) {
				TourGroup tourGroup=groupList.get(0);
				order.setGroupId(tourGroup.getId());
				groupOrderBiz.updateGroupOrder(order);
				tourGroup.setOrderNum(tourGroup.getOrderNum() == null ? 1
						: tourGroup.getOrderNum() + 1);
				tourGroupBiz.updateByPrimaryKey(tourGroup);
				groupOrderBiz.updateGroupPersonNum(tourGroup.getId());
				groupOrderBiz.updateGroupPrice(tourGroup.getId());
			} else { // 如果不存在新建团并加入
				List<MergeGroupOrderVO> result = new ArrayList<MergeGroupOrderVO>();
				MergeGroupOrderVO mov = new MergeGroupOrderVO();
				mov.getOrderList().add(order);
				result.add(mov);
				try {
					specialGroupOrderBiz.mergetGroupResFit(result, bizId, userId, curUser.getName(), myBizCode);
				} catch (ParseException e) {
					LOGGER.error("mergetGroupResFit error! result={},bizId={},userId={},userName={},myBizCode={}",result,bizId,userId,curUser.getName(),myBizCode);
				}
			}
		}
	}
	
	public String toDeleteOrderInfo(TrafficOrderDTO trafficOrderDTO){
		String id = trafficOrderDTO.getId();
		PlatformEmployeePo curUser = trafficOrderDTO.getCurUser();
		int nums = groupOrderBiz.delGroupOrderId(Integer.valueOf(id));
		//插入日志
		GroupOrder groupOrder = groupOrderBiz.selectByPrimaryKey(Integer.valueOf(id));
		Integer groupId = groupOrder.getGroupId()==null?0:groupOrder.getGroupId();
		insertLog(LogFieldUtil.getLog_Instant(curUser.getBizId(), curUser.getName(),LOG_ACTION.DELETE, "group_order", groupOrder.getId(), groupId ,"删除订单!", groupOrder, null));
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("success", nums);
		return JSON.toJSONString(map);
	}
	
	private void insertLog(com.yimayhd.erpcenter.common.po.LogOperator log){
		List<com.yimayhd.erpcenter.common.po.LogOperator> logList = new ArrayList<com.yimayhd.erpcenter.common.po.LogOperator>();
		logList.add(log);
		logOperatorBiz.insert(logList);
	}
	

}
