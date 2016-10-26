package com.yimayhd.erpcenter.dal.sales.finance.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.common.contants.BasicConstants;
import com.yimayhd.erpcenter.common.exception.ClientException;
import com.yimayhd.erpcenter.common.util.NumberUtil;
import com.yimayhd.erpcenter.dal.sales.client.constants.SupplierConstant;
import com.yimayhd.erpcenter.dal.sales.client.finance.po.FinanceCommission;
import com.yimayhd.erpcenter.dal.sales.client.finance.po.FinancePay;
import com.yimayhd.erpcenter.dal.sales.client.finance.po.FinancePayDetail;
import com.yimayhd.erpcenter.dal.sales.client.finance.po.InfoBean;
import com.yimayhd.erpcenter.dal.sales.client.finance.service.FinanceDal;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingDelivery;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingGuide;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingShop;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingShopDetail;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingSupplier;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingSupplierDetail;
import com.yimayhd.erpcenter.dal.sales.client.operation.service.BookingShopDetailDal;
import com.yimayhd.erpcenter.dal.sales.client.operation.service.BookingSupplierDetailDal;
import com.yimayhd.erpcenter.dal.sales.client.sales.constants.Constants;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrder;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.TourGroup;
import com.yimayhd.erpcenter.dal.sales.client.sales.service.GroupOrderPriceDal;
import com.yimayhd.erpcenter.dal.sales.finance.dao.FinanceCommissionMapper;
import com.yimayhd.erpcenter.dal.sales.finance.dao.FinancePayDetailMapper;
import com.yimayhd.erpcenter.dal.sales.finance.dao.FinancePayMapper;
import com.yimayhd.erpcenter.dal.sales.operation.dao.BookingDeliveryMapper;
import com.yimayhd.erpcenter.dal.sales.operation.dao.BookingGuideMapper;
import com.yimayhd.erpcenter.dal.sales.operation.dao.BookingShopMapper;
import com.yimayhd.erpcenter.dal.sales.operation.dao.BookingSupplierMapper;
import com.yimayhd.erpcenter.dal.sales.sales.dao.GroupOrderMapper;
import com.yimayhd.erpcenter.dal.sales.sales.dao.TourGroupMapper;

/**
 * 财务管理
 * 
 * @author Jing.Zhuo
 * @create 2015年7月28日 上午11:39:28
 */
public class FinanceDalImpl implements FinanceDal {

	@Autowired
	private SqlSessionTemplate ss;

	@Autowired
	private TourGroupMapper groupMapper;
	@Autowired
	private BookingDeliveryMapper deliveryMapper;
	@Autowired
	private BookingSupplierMapper supplierMapper;
	@Autowired
	private BookingShopMapper shopMapper;
	@Autowired
	private GroupOrderMapper orderMapper;
	@Autowired
	private FinancePayMapper payMapper;
	@Autowired
	private FinancePayDetailMapper payDetailMapper;
	@Autowired
	private FinanceCommissionMapper financeCommissionMapper;
	@Autowired
	private BookingShopDetailDal bookingShopDetailDal;
	@Autowired
	private BookingSupplierDetailDal bookingSupplierDetailDal;
	@Autowired
	private BookingGuideMapper bookingGuideMapper;
	@Autowired
	private GroupOrderPriceDal groupOrderPriceDal;

	@SuppressWarnings("unchecked")
	@Override
	public Map queryAuditViewInfo(Integer groupId, Integer bizId) {
		Map view = new HashMap();

		// 整团收支统计
		Map pm = new HashMap();
		pm.put("groupId", groupId);
		pm.put("bizId", bizId);
		Map group = ss.selectOne("fin.selectGroupList", pm);
		view.put("group", group);
		List<BookingGuide> bookingGuides = bookingGuideMapper.selectByGroupId(groupId);
		view.put("bookingGuides", bookingGuides);
		
		//团收入 = 团收入 - 购物汇总
		BigDecimal totalIncome = NumberUtil.parseObj2Num(group.get("total_income"));
		BigDecimal totalIncomeShop = NumberUtil.parseObj2Num(group.get("total_income_shop"));
		totalIncome = totalIncome.subtract(totalIncomeShop);
		group.put("total_income", totalIncome);
		
		BigDecimal totalCost = NumberUtil.parseObj2Num(group.get("total_cost"));
		BigDecimal totalProfit = totalIncome.subtract(totalCost);
		group.put("total_profit", totalProfit);
		
		BigDecimal totalAdult = NumberUtil.parseObj2Num(group.get("total_adult"));
		BigDecimal totalChild = NumberUtil.parseObj2Num(group.get("total_child"));
		BigDecimal totalGuide = NumberUtil.parseObj2Num(group.get("total_guide"));
		BigDecimal personNum  = totalAdult.add(totalChild).add(totalGuide);
		group.put("person_num", personNum.intValue());
		if(personNum.compareTo(new BigDecimal(0)) == 0){
			group.put("person_profit", totalProfit);
		}else{
			group.put("person_profit", totalProfit.divide(personNum, 2));
		}

		// 组团社收入统计
		List<GroupOrder> ordls = orderMapper.selectOrderByGroupId(groupId);
		InfoBean order = new InfoBean();
		view.put("order", order);
		order.setCount(ordls.size());
		for (GroupOrder o : ordls) {
			if (o.getTotal() == null)
				o.setTotal(new BigDecimal(0));
			if (o.getAuditUserId() != null) {
				order.setAuditedNum(order.getAuditedNum()+1);
			}
			order.setNum(order.getNum().add(o.getTotal()));
		}
		
		// 购物统计
		List<BookingShop> shopls = shopMapper.getShopListByGroupId(groupId);
		InfoBean shop = new InfoBean();
		shop.setCount(shopls.size());
		view.put("shop", shop);
		
		// 佣金统计
		List<FinanceCommission> commls = financeCommissionMapper.selectByGroupId(groupId);
		InfoBean comm = new InfoBean();
		comm.setCount(commls.size());
		view.put("comm", comm);


		// 地接支出统计
		List<BookingDelivery> dells = deliveryMapper.getDeliveryListByGroupId(groupId);
		InfoBean del = new InfoBean();
		view.put("del", del);
		del.setCount(dells.size());
		for (BookingDelivery d : dells) {
			if (d.getTotal() == null)
				d.setTotal(new BigDecimal(0));
			if (d.getAuditUserId() != null) {
				del.setAuditedNum(del.getAuditedNum()+1);
			}
			del.setNum(del.getNum().add(d.getTotal()));
		}

		// 其他收入
		InfoBean otherin = new InfoBean();
		view.put("otherin", otherin);
		otherin.setId(Constants.OTHERINCOME.toString());

		// 商家支出统计
		Map<Integer, InfoBean> sup = new HashMap<Integer, InfoBean>();
		List<BookingSupplier> supls = supplierMapper.getSupplierListByGroupId(groupId);

		for (BookingSupplier s : supls) {
			if (s.getTotal() == null) {
				s.setTotal(new BigDecimal(0));
			}

			// 其他收入
			if (Constants.OTHERINCOME.equals(s.getSupplierType())) {

				otherin.setCount(otherin.getCount()+1);
				if (s.getAuditUserId() != null) {
					otherin.setAuditedNum(otherin.getAuditedNum()+1);

				}
				otherin.setNum(otherin.getNum().add(s.getTotal()));
			}

			// 酒店、景区、餐厅、车队、等等商家支出
			else {

				InfoBean sinfo = getInfoBean(s, sup);
				sinfo.setNum(sinfo.getNum().add(s.getTotal()));
			}
		}
		view.put("sup", sup.values());
		
		return view;
	}
	
	/**
	 * 团购物、佣金汇总
	 * @param groupId
	 * @return
	 */
	@Override
	public InfoBean statsShopWithCommInfoBean(Integer groupId){
		
		InfoBean shop = new InfoBean();
		
		// 购物收入统计
		List<BookingShop> shopls = shopMapper.getShopListByGroupId(groupId);
		shop.setCount(shopls.size());
		for (BookingShop s : shopls) {
			if (s.getTotalFace() == null) {
				s.setTotalFace(new BigDecimal(0));
			}
			if (s.getAuditUserId() != null) {
				shop.setAuditedNum(shop.getAuditedNum()+1);
			}

			// 人员返款+购物返款
			if(s.getPersonRepayTotal() != null){
				shop.setNum(shop.getNum().add(s.getPersonRepayTotal()));
			}
			if(s.getTotalRepay() != null){
				shop.setNum(shop.getNum().add(s.getTotalRepay()));
			}
		}
		
		//佣金统计
		List<FinanceCommission> coms = financeCommissionMapper.selectByGroupId(groupId);
		for (FinanceCommission c : coms) {
			if (c.getTotal() == null) {
				c.setTotal(new BigDecimal(0));
			}
			if (c.getTotalCash() != null) {
				c.setTotalCash(new BigDecimal(0));
			}
			
			if (c.getAuditUserId() != null) {
				shop.setAuditedNum(shop.getAuditedNum()+1);
			}
			
			shop.setNum(shop.getNum().subtract(c.getTotal()));
		}
		shop.setCount(shop.getCount() + coms.size());
		
		return shop;
	}
	
	/**
	 * 获取除了其他商家之外所有商家的统计信息
	 * 
	 * @author Jing.Zhuo
	 * @create 2015年7月30日 下午6:08:46
	 * @param bs
	 * @param map
	 * @param dicls
	 * @return
	 */
	private InfoBean getInfoBean(BookingSupplier bs, Map<Integer, InfoBean> map) {

		InfoBean info = map.get(bs.getSupplierType());
		if (info == null) {
			info = new InfoBean();
			info.setId(String.valueOf(bs.getSupplierType()));
			if(Constants.OTHEROUTCOME.equals(bs.getSupplierType())){
				info.setName(SupplierConstant.supplierTypeMapPay.get(Constants.OTHER));
			}else{
				info.setName(SupplierConstant.supplierTypeMapPay.get(bs.getSupplierType()));
			}
			info.setCss(SupplierConstant.supplierTypeCSS.get(bs.getSupplierType()));
			map.put(bs.getSupplierType(), info);
		}
		info.setCount(info.getCount()+1);
		if (bs.getAuditUserId() != null) {
			info.setAuditedNum(info.getAuditedNum()+1);
		}
		return info;
	}

	@Transactional
	@Override
	public void calcTourGroupAmount(Integer groupId) {

		List<GroupOrder> ordls = orderMapper.selectOrderByGroupId(groupId);
		List<BookingShop> shopls = shopMapper.getShopListByGroupId(groupId);
		List<BookingSupplier> supls = supplierMapper.getSupplierListByGroupId(groupId);
		List<BookingDelivery> dells = deliveryMapper.getDeliveryListByGroupId(groupId);
		List<FinanceCommission> coms = financeCommissionMapper.selectByGroupId(groupId);

		// 应收（不含购物）
		BigDecimal receive = new BigDecimal(0);
		// 已收（不含购物）
		BigDecimal received = new BigDecimal(0);
		// 购物应收
		BigDecimal receive_shop = new BigDecimal(0);
		// 购物已收
		BigDecimal received_shop = new BigDecimal(0);
		// 应付
		BigDecimal pay = new BigDecimal(0);
		// 已付
		BigDecimal payed = new BigDecimal(0);

		// 销售
		for (GroupOrder o : ordls) {

			if (o.getTotal() == null) {
				o.setTotal(new BigDecimal(0));
			}
			if (o.getTotalCash() == null) {
				o.setTotalCash(new BigDecimal(0));
			}

			// 应收
			receive = receive.add(o.getTotal());
			// 已收
			received = received.add(o.getTotalCash());
		}
		// 购物
		for (BookingShop s : shopls) {

			if (s.getPersonRepayTotal() == null) {
				s.setPersonRepayTotal(new BigDecimal(0));
			}
			if (s.getTotalRepay() == null) {
				s.setTotalRepay(new BigDecimal(0));
			}
			if(s.getTotalCash() == null){
				s.setTotalCash(new BigDecimal(0));
			}

			// 应收 = 购物返款+人员返款
			receive_shop = receive_shop.add(s.getTotalRepay()).add(s.getPersonRepayTotal());

			// 购物返款+人员返款
			received_shop = received_shop.add(s.getTotalCash());
		}
		// 商家
		for (BookingSupplier d : supls) {

			if (d.getTotal() == null) {
				d.setTotal(new BigDecimal(0));
			}
			if (d.getTotalCash() == null) {
				d.setTotalCash(new BigDecimal(0));
			}

			// 判断应该统计为支出还是收入
			if (Constants.OTHERINCOME == d.getSupplierType()) {

				// 应收
				receive = receive.add(d.getTotal());
				// 已收
				received = received.add(d.getTotalCash());
				continue;
			}

			// 应付
			pay = pay.add(d.getTotal());
			// 已付
			payed = payed.add(d.getTotalCash());
		}

		// 地接
		for (BookingDelivery s : dells) {

			if (s.getTotal() == null) {
				s.setTotal(new BigDecimal(0));
			}
			if (s.getTotalCash() == null) {
				s.setTotalCash(new BigDecimal(0));
			}

			// 应付
			pay = pay.add(s.getTotal());
			// 已付
			payed = payed.add(s.getTotalCash());
		}
		
		//佣金
		for(FinanceCommission c : coms){
			if (c.getTotal() == null) {
				c.setTotal(new BigDecimal(0));
			}
			if (c.getTotalCash() == null) {
				c.setTotalCash(new BigDecimal(0));
			}
			//购物应收 = 购物应收 - 导游佣金
			receive_shop = receive_shop.subtract(c.getTotal());
			//购物已收 = 购物已收 - 导游佣金已收
			received_shop = received_shop.subtract(c.getTotalCash());
		}

		TourGroup tg = new TourGroup();
		tg.setId(groupId);
		tg.setTotalIncome(receive.add(receive_shop));
		tg.setTotalIncomeCash(received.add(received_shop));
		tg.setTotalIncomeShop(receive_shop);
		tg.setTotalIncomeShopCash(received_shop);
		tg.setTotalCost(pay);
		tg.setTotalCostCash(payed);
		groupMapper.updateByPrimaryKeySelective(tg);
	}
	

	


	/* (non-Javadoc)
	 * @see com.yihg.finance.api.FinanceService#calcTotalCash_collection(java.lang.Integer)
	 */
	@Override
	public void calcTotalCash_collection(Integer groupId) {
		if(groupId == null){
			return;
		}
		payMapper.calcTotalCash_Supplier(groupId);
		payMapper.calcTotalCash_OtherIn(groupId);
		payMapper.calcTotalCash_Delivery(groupId);
		payMapper.calcTotalCash_Sales(groupId);
	}

	@Override
	@Transactional
	public void audit(Integer groupId, String feeType, String checkedIds, String unCheckedIds, 
			String comCheckedIds, String comUnCheckedIds, Integer userId, String userName) {
		
		if(!"com".equals(feeType) && !"shop".equals(feeType)){
			TourGroup gp = groupMapper.selectByPrimaryKey(groupId);
			if (gp.getGroupState() == BasicConstants.GROUP_STATE_SEAL) {
				throw new ClientException("该团已封存，不能进行操作");
			}
			
			if (gp.getGroupState() == BasicConstants.GROUP_STATE_AUDIT) {
				throw new ClientException("该团已审核，不能进行操作");
			}
		}
		
		String type = "";
		if("order".equals(feeType) || "shop".equals(feeType) || "otherin".equals(feeType) || "com".equals(feeType)){
			type = "收入";
		}else if("del".equals(feeType) || "sup".equals(feeType)){
			type = "支出";
		}
		
		Map pm = new HashMap();
		pm.put("groupId", groupId);
		pm.put("feeType", feeType);

		// 取消审核
		if (StringUtils.isNotBlank(unCheckedIds)) {
			pm.put("ids", unCheckedIds);
			pm.put("stateFinance", 0);
			pm.put("operateLog", userName +"取消审核通过"+ type +" ");
			ss.update("fin.updateAuditByIds", pm);
		}

		// 审核
		if (StringUtils.isNotBlank(checkedIds)) {
			pm.put("userId", userId);
			pm.put("userName", userName);
			pm.put("auditTime", Calendar.getInstance().getTimeInMillis());
			pm.put("ids", checkedIds);
			pm.put("stateFinance", 1);
			pm.put("operateLog", userName +"审核通过"+ type +" ");
			ss.update("fin.updateAuditByIds", pm);
		}
		
		pm.put("feeType", "com");
		
		//取消审核 佣金
		if(StringUtils.isNotBlank(comUnCheckedIds)){
			pm.put("userId", null);
			pm.put("userName", null);
			pm.put("auditTime", null);
			pm.put("ids", comUnCheckedIds);
			pm.put("stateFinance", 0);
			pm.put("operateLog", userName +"取消审核通过"+ type +" ");
			ss.update("fin.updateAuditByIds", pm);
		}
		
		//审核 佣金
		if(StringUtils.isNotBlank(comCheckedIds)){
			pm.put("userId", userId);
			pm.put("userName", userName);
			pm.put("auditTime", Calendar.getInstance().getTimeInMillis());
			pm.put("ids", comCheckedIds);
			pm.put("stateFinance", 1);
			pm.put("operateLog", userName +"审核通过"+ type +" ");
			ss.update("fin.updateAuditByIds", pm);
		}
		
		//修改团更新时间，已方便之后根据团时间归档
		groupMapper.updateTimestampByPrimaryKey(groupId);
	}
	
	@Override
	@Transactional
	public void auditCommDeduction(Integer groupId, String feeType, String comCheckedIds, 
			String comUnCheckedIds, Integer userId, String userName) {
		
		Map pm = new HashMap();
		pm.put("groupId", groupId);
		pm.put("feeType", feeType);
		
		String type = "佣金扣款";
		
		//取消审核 佣金
		if(StringUtils.isNotBlank(comUnCheckedIds)){
			pm.put("userId", null);
			pm.put("userName", null);
			pm.put("auditTime", null);
			pm.put("ids", comUnCheckedIds);
			pm.put("stateFinance", 0);
			pm.put("operateLog", userName +"取消审核通过"+ type +" ");
			ss.update("fin.updateAuditByIds", pm);
		}
		
		//审核 佣金
		if(StringUtils.isNotBlank(comCheckedIds)){
			pm.put("userId", userId);
			pm.put("userName", userName);
			pm.put("auditTime", Calendar.getInstance().getTimeInMillis());
			pm.put("ids", comCheckedIds);
			pm.put("stateFinance", 1);
			pm.put("operateLog", userName +"审核通过"+ type +" ");
			ss.update("fin.updateAuditByIds", pm);
		}
	}
	
	@Override
	@Transactional
	public void audit(String data, Integer userId, String userName) {
		
		if(StringUtils.isEmpty(data) || userId == null || StringUtils.isEmpty(userName)){
			return;
		}
		
		JSONObject obj = JSONObject.parseObject(data);
		Integer groupId = obj.getInteger("groupId");
		JSONArray list = JSONArray.parseArray(obj.getString("list"));
		
		if(list == null || list.size() == 0){
			return;
		}
		
		JSONObject item = null;
		for(int i = 0; i < list.size(); i++){
			
			item = JSONObject.parseObject(list.getString(i));
			String feeType = item.getString("feeType");
			String checkedIds = item.getString("checkedIds");
			String unCheckedIds = item.getString("unCheckedIds");
			
			if(!"comm".equals(feeType)){
				audit(groupId, feeType, checkedIds, unCheckedIds, null, null, userId, userName);
			}else{
				audit(groupId, feeType, null, null, checkedIds, unCheckedIds, userId, userName);
			}
			
			if("order".equals(feeType)){
				String priceCheckedIds = item.getString("priceCheckedIds");
				String priceUnCheckedIds = item.getString("priceUnCheckedIds");
				groupOrderPriceDal.auditPriceByIds(priceCheckedIds, priceUnCheckedIds);
			}
		}
	}

	@Override
	@Transactional
	public void audit(Integer groupId, Integer userId, String userName) {

		TourGroup gp = groupMapper.selectByPrimaryKey(groupId);
		if (gp.getGroupState() != BasicConstants.GROUP_STATE_CONFIRMED) {
			throw new ClientException("只有已确认的团才可以审核");
		}

		List<GroupOrder> ordls = orderMapper.selectOrderByGroupId(groupId);
		//List<BookingShop> shopls = shopMapper.getShopListByGroupId(groupId);
		List<BookingSupplier> supls = supplierMapper.getSupplierListByGroupId(groupId);
		List<BookingDelivery> dells = deliveryMapper.getDeliveryListByGroupId(groupId);
		//List<FinanceCommission> comms = financeCommissionMapper.selectByGroupId(groupId);
		ClientException e = new ClientException("请处理未审核的订单");
		
		// 旅行社
		for (GroupOrder o : ordls) {
			if (o.getAuditUserId() == null) {
				throw e;
			}
		}
		
		// 商家
		for (BookingSupplier o : supls) {
			if (o.getAuditUserId() == null) {
				throw e;
			}
		}

		// 地接
		for (BookingDelivery o : dells) {
			if (o.getAuditUserId() == null) {
				throw e;
			}
		}
		
		TourGroup group = new TourGroup();
		group.setId(groupId);

		// 审核状态
		group.setGroupState(BasicConstants.GROUP_STATE_AUDIT);
		group.setOperateLog(userName +"审核通过团"+ gp.getGroupCode() +" ");
		group.setAuditUser(userName);
		group.setAuditUserId(userId);
		group.setAuditTime(new Date());
		groupMapper.updateByPrimaryKeySelective(group);
	}
	
	@Override
	@Transactional
	public void unAudit(Integer groupId, String userName) {

		TourGroup gp = groupMapper.selectByPrimaryKey(groupId);
		if (gp.getGroupState() != BasicConstants.GROUP_STATE_AUDIT) {
			throw new ClientException("只有已审核的团才可以取消审核");
		}

		TourGroup group = new TourGroup();
		group.setId(groupId);

		// 确认状态
		group.setGroupState(BasicConstants.GROUP_STATE_CONFIRMED);
		group.setOperateLog(userName +"取消审核通过团"+ gp.getGroupCode() +" ");
		group.setAuditUser(null);
		group.setAuditUserId(null);
		group.setAuditTime(null);
		groupMapper.updateByPrimaryKeySelective(group);
	}
	
	@Override
	@Transactional
	public void batchSeal(String groupIds, Integer userId, String userName) {

		if (groupIds == null || "".equals(groupIds)) {
			throw new ClientException("请选择要封存的团");
		}
		String operateLog = userName +"封存团 ";
		groupMapper.updateStateToSeal(groupIds, operateLog);
		
		updateStateSeal(groupIds, userId, userName, 1);
	}
	
	@Override
	@Transactional
	public void unseal(String groupId, Integer userId, String userName) {
		
		Integer intGroupId = Integer.parseInt(groupId);
		TourGroup gp = groupMapper.selectByPrimaryKey(intGroupId);
		if (gp.getGroupState() != 4) {
			throw new ClientException("只有已封存的团才可以解封");
		}
		
		TourGroup group = new TourGroup();
		group.setId(intGroupId);

		// 已审核状态
		group.setGroupState(3);
		group.setOperateLog(userName +"解封团"+ gp.getGroupCode() +" ");
		groupMapper.updateByPrimaryKeySelective(group);
		
		//解封团详情
		updateStateSeal(groupId, userId, userName, 0);
	}
	
	@Override
	@Transactional
	public int makeCollections(FinancePay pay,Integer orderId) {
		payMapper.insertSelective(pay);
		FinancePayDetail details=new FinancePayDetail();
		details.setPayId(pay.getId());
		details.setLocOrderId(orderId);
		details.setCash(pay.getCash());
		payDetailMapper.insertSelective(details);
		if (Constants.TRAVELAGENCY.equals(pay.getSupplierType())) {
			// 收款标识组团社
			GroupOrder order = orderMapper.selectByPrimaryKey(details.getLocOrderId());
//			if( null==order.getStateFinance() || 1 != order.getStateFinance()){
//				throw new ClientException("单号为["+order.getOrderNo()+"]的订单没有审核");
//			}
			BigDecimal originalCash = null;
			GroupOrder modify = new GroupOrder();
			modify.setId(order.getId());
			originalCash = calcTotalCashValue(order.getId(), Constants.TRAVELAGENCY);
			modify.setTotalCash(originalCash);
			orderMapper.updateByPrimaryKeySelective(modify);
			// 维护整团收支信息
			calcTourGroupAmount(order.getGroupId());
		}
		return pay.getId();
	}
	
	@Override
	@Transactional
	public FinancePay pay(FinancePay pay, List<FinancePayDetail> details, Integer supplierType) {
		
		// 计算单次收支记录总额
		pay.setCash(new BigDecimal(0));
		if(details != null && details.size() > 0){
			for (FinancePayDetail d : details) {
	
				if (d.getCash() == null)
					continue;
				 pay.setCash(pay.getCash().add(d.getCash()));
			}
		}
		
		pay.setCreateTime(new Date().getTime());
		boolean isInsert = pay.getId() == null ? true : false; 
		if(isInsert){
			pay.setType(0);  //供应商订单付款
			payMapper.insert(pay);
		}else{
			payMapper.update(pay);
		}
		
		//删除前台删除的订单
		List<FinancePayDetail> existDetails = payDetailMapper.selectByPayId(pay.getId());
		if(existDetails != null && existDetails.size() > 0){
			for(int i = 0; i < existDetails.size(); i++){
				boolean needDelete = true;
				FinancePayDetail existDetail = existDetails.get(i);
				for(int j = 0; j < details.size(); j++){
					FinancePayDetail detail = details.get(j);
					if(existDetail.getLocOrderId() == detail.getLocOrderId()){
						needDelete = false;
						continue;
					}
				}
				if(needDelete){
					this.deletePayDetail(supplierType, existDetail.getLocOrderId(), pay.getId());
				}
			}
		}
		
		if(details != null && details.size() > 0){
			for (FinancePayDetail d : details) {
	
				if (d.getCash() == null)
					continue;
	
				d.setPayId(pay.getId());
				
				BigDecimal originalCash = null;
				if(isInsert){
					payDetailMapper.insert(d);
				}else{
					FinancePayDetail selectedDetail = payDetailMapper.selectByPayIdAndLocOrderId(pay.getId(), d.getLocOrderId());
					if(selectedDetail != null){
						originalCash = selectedDetail.getCash();
						selectedDetail.setCash(d.getCash());
						payDetailMapper.update(selectedDetail);
					}else{
						payDetailMapper.insert(d);
					}
				}
	
				// 维护组团社已付金额
				if (Constants.TRAVELAGENCY.equals(supplierType)) {
					// 收款标识组团社
					GroupOrder order = orderMapper.selectByPrimaryKey(d.getLocOrderId());
//					if( null==order.getStateFinance() || 1 != order.getStateFinance()){
//						throw new ClientException("单号为["+order.getOrderNo()+"]的订单没有审核");
//					}
	
					GroupOrder modify = new GroupOrder();
					modify.setId(order.getId());
					originalCash = calcTotalCashValue(order.getId(), Constants.TRAVELAGENCY);
					modify.setTotalCash(originalCash);
					orderMapper.updateByPrimaryKeySelective(modify);
					// 维护整团收支信息
					calcTourGroupAmount(order.getGroupId());
				}
				// 维护地接社已付金额
				else if(Constants.LOCALTRAVEL.equals(supplierType)){
					BookingDelivery del = deliveryMapper.selectByPrimaryKey(d.getLocOrderId());
					if(del.getStateFinance() != 1){
						throw new ClientException("单号为["+del.getSupplierOrderNo()+"]的订单没有审核");
					}
					
					BookingDelivery modify = new BookingDelivery();
					modify.setId(del.getId());
					originalCash = calcTotalCashValue(del.getId(), Constants.LOCALTRAVEL);
					modify.setTotalCash(originalCash);
					deliveryMapper.updateByPrimaryKeySelective(modify);
					// 维护整团收支信息
					calcTourGroupAmount(del.getGroupId());
				}
				
				// 购物应收统计
				else if(Constants.SHOPPING.equals(supplierType)) {
					BookingShop shop = shopMapper.selectByPrimaryKey(d.getLocOrderId());
					if(shop.getStateFinance() != 1){
						throw new ClientException("单号为["+shop.getBookingNo()+"]的订单没有审核");
					}
					
					BookingShop modify = new BookingShop();
					modify.setId(shop.getId());
					if(isInsert || originalCash == null){
						modify.setTotalCash(shop.getTotalCash() == null ? d.getCash() : shop.getTotalCash().add(d.getCash()));
					}else{
						BigDecimal totalCash = shop.getTotalCash().subtract(originalCash);
						totalCash = totalCash.add(d.getCash());
						modify.setTotalCash(totalCash);
					}
					shopMapper.updateByPrimaryKeySelective(modify);
					
					// 维护整团收支信息
					calcTourGroupAmount(shop.getGroupId());
				}
				// 维护供应商已付金额
				else {
					BookingSupplier sup = supplierMapper.selectByPrimaryKey(d.getLocOrderId());
					if(sup.getStateFinance() != 1){
						throw new ClientException("单号为["+sup.getBookingNo()+"]的订单没有审核");
					}
					BookingSupplier modify = new BookingSupplier();
					modify.setId(sup.getId());
					originalCash = calcTotalCashValue(sup.getId(), sup.getSupplierType());
					modify.setTotalCash(originalCash);
					supplierMapper.updateByPrimaryKeySelective(modify);
					// 维护整团收支信息
					calcTourGroupAmount(sup.getGroupId());
				}
			}
		}
		return pay;
	}

	@Override
	public FinancePay queryPayById(Integer payId) {
 		return payMapper.selectByPrimaryKey(payId);
	}

	@Override
	public List<TourGroup> getOperateLogs(Integer groupId) {
		
		List<Map> list = groupMapper.selectOperateLogPage(groupId);
		if(list == null || list.size() == 0){
			return null;
		}
		
		List<TourGroup> logs = new ArrayList<TourGroup>();
		TourGroup tourGroup = null;
		for(int i = 0; i < list.size(); i++){
			Map map = list.get(i);
			if(map == null){
				continue;
			}
			String log = (String)map.get("operate_log");
			if(log == null || "".equals(log)){
				continue;
			}
			
			String[] arr = log.split("\n");
			for(int j = 0; j < arr.length; j++){
				String item = arr[j];
				String dateStr = item.substring(0, 18);
				SimpleDateFormat dafaFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				
				tourGroup = new TourGroup();
				tourGroup.setOperateLog(item + "<br/>");
				try {
					Date date = dafaFormat.parse(dateStr);
					tourGroup.setOperateLogTime(date.getTime());
				} catch (ParseException e) {
					e.printStackTrace();
					tourGroup.setOperateLogTime(0L);
				}
				logs.add(tourGroup);
			}
		}
		
		Collections.sort(logs,new Comparator<TourGroup>(){  
            public int compare(TourGroup arg0, TourGroup arg1) {  
                return arg0.getOperateLogTime().compareTo(arg1.getOperateLogTime());  
            }  
        });  
		return logs;
	}
	
	private void updateStateSeal(String groupIds, Integer userId, String userName, Integer stateSeal){
		
		Map pm = new HashMap();
		pm.put("groupIds", groupIds);
		pm.put("userId", userId);
		pm.put("userName", userName);
		pm.put("sealTime", Calendar.getInstance().getTimeInMillis());
		pm.put("stateSeal", stateSeal);
		
		String typeText = "封存";
		if(stateSeal == 0){
			typeText = "解封";
		}
		
		// 旅行社 
		String type = "order";
		pm.put("feeType", type);
		pm.put("operateLog", userName + typeText +"收入");
		ss.update("fin.updateSealByGroupIds", pm);
		
		// 商家  
		type = "del";
		pm.put("feeType", type);
		pm.put("operateLog", userName + typeText +"支出");
		ss.update("fin.updateSealByGroupIds", pm);
		
		// 地接
		type = "sup";
		pm.put("feeType", type);
		pm.put("operateLog", userName + typeText +"支出");
		ss.update("fin.updateSealByGroupIds", pm);
	}

	@Override
	@Transactional
	public void deletePayById(Integer payId) {
		payMapper.deleteByPrimaryKey(payId);
		payDetailMapper.deleteByPayId(payId);
	}
	
	@Override
	@Transactional
	public void deletePayDetail(Integer supplierType, Integer locOrderId, Integer payId) {

		FinancePayDetail detail = payDetailMapper.selectByPayIdAndLocOrderId(payId, locOrderId);
		if(detail == null){
			return;
		}
		payDetailMapper.deleteByLocOrderIdAndPayId(locOrderId, payId);
		
		// 维护组团社已付金额
		if (Constants.TRAVELAGENCY.equals(supplierType)) {
			// 收款标识组团社
			GroupOrder order = orderMapper.selectByPrimaryKey(locOrderId);
			GroupOrder modify = new GroupOrder();
			modify.setId(order.getId());
			modify.setTotalCash(order.getTotalCash().subtract(detail.getCash()));
			orderMapper.updateByPrimaryKeySelective(modify);
			
			// 维护整团收支信息
			calcTourGroupAmount(order.getGroupId());
		}
		// 维护地接社已付金额
		else if(Constants.LOCALTRAVEL.equals(supplierType)){
			BookingDelivery del = deliveryMapper.selectByPrimaryKey(locOrderId);
			BookingDelivery modify = new BookingDelivery();
			modify.setId(del.getId());
			modify.setTotalCash(del.getTotalCash().subtract(detail.getCash()));
			deliveryMapper.updateByPrimaryKeySelective(modify);
			
			// 维护整团收支信息
			calcTourGroupAmount(del.getGroupId());
		}
		// 购物应收统计
		else if(Constants.SHOPPING.equals(supplierType)) {
			
			BookingShop shop = shopMapper.selectByPrimaryKey(locOrderId);
			BookingShop modify = new BookingShop();
			modify.setId(shop.getId());
			modify.setTotalCash(shop.getTotalCash().subtract(detail.getCash()));
			shopMapper.updateByPrimaryKeySelective(modify);
			
			// 维护整团收支信息
			calcTourGroupAmount(shop.getGroupId());
		}
		// 维护供应商已付金额
		else {
			BookingSupplier sup = supplierMapper.selectByPrimaryKey(locOrderId);
			BookingSupplier modify = new BookingSupplier();
			modify.setId(sup.getId());
			modify.setTotalCash(sup.getTotalCash().subtract(detail.getCash()));
			supplierMapper.updateByPrimaryKeySelective(modify);

			// 维护整团收支信息
			calcTourGroupAmount(sup.getGroupId());
		}
		
	}

	@Override
	public void auditShop(String checkedIds, String unCheckedIds, Integer employeeId, String name) {
		
		
		String feeType = "shop";
		if(StringUtils.isNotEmpty(checkedIds)){
			List<BookingShop> checkedShops = JSONArray.parseArray(checkedIds, BookingShop.class);
			BookingShop shop = null;
			for(int i = 0; i < checkedShops.size(); i++){
				shop = checkedShops.get(i);
				this.audit(shop.getGroupId(), feeType, shop.getId()+"", "", "", "", employeeId, name);
			}
		}
		
		if(StringUtils.isNotEmpty(unCheckedIds)){
			List<BookingShop> unCheckedShops = JSONArray.parseArray(unCheckedIds, BookingShop.class);
			BookingShop shop = null;
			for(int i = 0; i < unCheckedShops.size(); i++){
				shop = unCheckedShops.get(i);
				this.audit(shop.getGroupId(), feeType, "", shop.getId()+"", "", "", employeeId, name);
			}
		}
	}
	
	@Override
	@Transactional
	public void auditComm(String checkedIds, String unCheckedIds,Integer userId, String userName) {
		
		String feeType = "com";
		if(StringUtils.isNotEmpty(checkedIds)){
			List<FinanceCommission> checkedComms = JSONArray.parseArray(checkedIds, FinanceCommission.class);
			FinanceCommission comm = null;
			for(int i = 0; i < checkedComms.size(); i++){
				comm = checkedComms.get(i);
				this.audit(comm.getGroupId(), feeType, "", "", comm.getId()+"", "", userId, userName);
			}
		}
		
		if(StringUtils.isNotEmpty(unCheckedIds)){
			List<FinanceCommission> unCheckedComms = JSONArray.parseArray(unCheckedIds, FinanceCommission.class);
			FinanceCommission comm = null;
			for(int i = 0; i < unCheckedComms.size(); i++){
				comm = unCheckedComms.get(i);
				this.audit(comm.getGroupId(), feeType, "", "", "", comm.getId()+"", userId, userName);
			}
		}
	}
	
	@Override
	@Transactional
	public void auditCommDeduction(String checkedIds, String unCheckedIds,Integer userId, String userName) {
		
		String feeType = "com_deduction";
		if(StringUtils.isNotEmpty(checkedIds)){
			List<FinanceCommission> checkedComms = JSONArray.parseArray(checkedIds, FinanceCommission.class);
			FinanceCommission comm = null;
			for(int i = 0; i < checkedComms.size(); i++){
				comm = checkedComms.get(i);
				this.auditCommDeduction(comm.getGroupId(), feeType, comm.getId()+"", "", userId, userName);
			}
		}
		
		if(StringUtils.isNotEmpty(unCheckedIds)){
			List<FinanceCommission> unCheckedComms = JSONArray.parseArray(unCheckedIds, FinanceCommission.class);
			FinanceCommission comm = null;
			for(int i = 0; i < unCheckedComms.size(); i++){
				comm = unCheckedComms.get(i);
				this.auditCommDeduction(comm.getGroupId(), feeType, "", comm.getId()+"", userId, userName);
			}
		}
	}
	
	@Override
	public PageBean selectIncomeJoinTableListPage(PageBean pageBean, Integer bizId) {
		
		StringBuffer str=new StringBuffer();
		List<Map<String,Object>> result = orderMapper.selectIncomeJoinTableListPage(pageBean, bizId);
		Map param = (Map)pageBean.getParameter();
		Integer supType = 0;
		if(param != null){
			supType = param.get("supType") != null ? Integer.parseInt(param.get("supType").toString()) : 0;
		}
		
		//购物或其他有明细
		if(supType.equals(6) || supType.equals(12)){
			String details = "";
			
			Map<String,Object> map = null;
			for (int i=0;i<result.size();i++) {
				map = result.get(i);
				
				Integer bookingId = map.get("id") != null ? Integer.parseInt(map.get("id").toString()) : 0; 
				
				if(supType.equals(6)){
					List<BookingShopDetail> list = bookingShopDetailDal.getShopDetailListByBookingId(bookingId);
					details = bookingShopDetailDal.concatDetail(list);
				}else if(supType.equals(12)){
					Integer supplierType = map.get("supplier_type") != null ? Integer.parseInt(map.get("supplier_type").toString()) : 0; 
					String remark = map.get("remark") != null ? map.get("supplier_type").toString() : ""; 
					List<BookingSupplierDetail> list = bookingSupplierDetailDal.selectByPrimaryBookId(bookingId);
					details = bookingSupplierDetailDal.concatDetail(supplierType, remark, list);
				}
				map.put("details", details);
			}
		}
		
		pageBean.setResult(result);
		return pageBean;
	}

	@Override
	public PageBean getsubjectSummaryListPage(PageBean pageBean) {
		List<Map<String,Object>> result = supplierMapper.getsubjectSummaryListPage(pageBean);
		pageBean.setResult(result);
		return pageBean;
	}
	
	@Override
	public PageBean getsubjectSummary2ListPage(PageBean pageBean,String sum) {
		List<Map<String,Object>> result = supplierMapper.getsubjectSummary2ListPage(pageBean,sum);
		pageBean.setResult(result);
		return pageBean;
	}

	@Override
	public Map<String, Object> getsubjectSummaryQDYJ(PageBean pageBean,Integer supplierId,String sType) {
		return supplierMapper.getsubjectSummaryQDYJ(pageBean,supplierId,sType);
	}
	
	@Override
	public Map<String, Object> getsubjectSummaryQT(PageBean pageBean,Integer supplierId,String sType,String sType2,String sType3) {
		return supplierMapper.getsubjectSummaryQT(pageBean,supplierId,sType,sType2,sType3);
	}
	
	@Override
	public Map<String, Object> getsubjectSummaryQDYJTotal(PageBean pageBean,String sType) {
		return supplierMapper.getsubjectSummaryQDYJTotal(pageBean,sType);
	}
	
	@Override
	public Map<String, Object> getsubjectSummaryQTTotal(PageBean pageBean,String sType,String sType2,String sType3) {
		return supplierMapper.getsubjectSummaryQTTotal(pageBean,sType,sType2,sType3);
	}

	@Override
	public List<FinancePay> getFinancePayBySupplierId(Integer supplierId, Integer bizId) {
		return payMapper.getFinancePayBySupplierId(supplierId, bizId) ;
	}
	//判断是否有酒店,车队订单
	@Override
	public boolean hasHotelOrder(Integer groupId) {
		if(groupId == null){
			return false;
		}
		List<BookingSupplier> hot = supplierMapper.getHotelAndFleetListByGroupId(groupId);
				if(hot != null && hot.size() > 0){
							return true;				
				}
				return false;
				}
				
	@Override
	public boolean hasAuditOrder(Integer groupId) {
		if(groupId == null){
			return false;
		}
		
		List<GroupOrder> ordls = orderMapper.selectOrderByGroupId(groupId);
		List<BookingShop> shopls = shopMapper.getShopListByGroupId(groupId);
		List<BookingSupplier> supls = supplierMapper.getSupplierListByGroupId(groupId);
		List<BookingDelivery> dells = deliveryMapper.getDeliveryListByGroupId(groupId);
		List<FinanceCommission> comms = financeCommissionMapper.selectByGroupId(groupId);
		
		// 旅行社
		if(ordls != null && ordls.size() > 0){
			for (GroupOrder o : ordls) {
				if (o.getAuditUserId() != null) {
					return true;
				}
			}
		}
		
		// 购物
		if(shopls != null && shopls.size() > 0){
			for (BookingShop o : shopls) {
				if (o.getAuditUserId() != null) {
					return true;
				}
			}
		}
		
		// 商家
		if(supls != null && supls.size() > 0){
			for (BookingSupplier o : supls) {
				if (o.getAuditUserId() != null) {
					return true;
				}
			}
		}

		// 地接
		if(dells != null && dells.size() > 0){
			for (BookingDelivery o : dells) {
				if (o.getAuditUserId() != null) {
					return true;
				}
			}
		}
		
		//佣金
		if(comms != null && comms.size() > 0){
			for (FinanceCommission o : comms) {
				if (o.getAuditUserId() != null) {
					return true;
				}
			}
		}
		
		return false;
	}

	@Override
	public boolean hasPayOrIncomeRecord(Integer groupId){
		if(groupId == null){
			return false;
		}
		
		Integer num = payMapper.selectPayOrIncomeRecordCount(groupId);
		if(num > 0){
			return true;
		}
		return false;
	}

	@Override
	public List<Map<String, Object>> selectDetailByLocOrderId(Integer locOrderId) {
		
		if(locOrderId == null){
			return null;
		}
		return payDetailMapper.selectDetailByLocOrderId(locOrderId);
	}

	@Transactional
	@Override
	public void calcBookingSupplierTotalCash(Integer bookingSupplierId) {
		
		if(bookingSupplierId == null){
			return;
		}
		
		BigDecimal totalCash = new BigDecimal(0);
		
		//查询供应商订单的收付款（已收，已付）金额
		BookingSupplier bs1 = supplierMapper.selectPayOrIncomeOrderTotalCash(bookingSupplierId);
		if(bs1 != null && bs1.getTotalCash() != null){
			totalCash = totalCash.add(bs1.getTotalCash());
		}
		
		//查询供应商订单的导游报账已付款金额
		BookingSupplier bs2 = supplierMapper.selectPayOrIncomeGuideTotalCash(bookingSupplierId);
		if(bs2 != null && bs2.getTotalCash() != null){
			totalCash = totalCash.add(bs2.getTotalCash());
		}
		
		//更新供应商订单的已付金额
		BookingSupplier bs = new BookingSupplier();
		bs.setId(bookingSupplierId);
		bs.setTotalCash(totalCash);
		supplierMapper.updateByPrimaryKeySelective(bs);
	}
	
	@Transactional
	@Override
	public void calcGroupOrderTotalCash(Integer groupOrderId) {
		
		if(groupOrderId == null){
			return;
		}
		
		//查询团订单的已收款金额
		GroupOrder order = orderMapper.selectIncomeOrderTotalCash(groupOrderId);
		if(order != null && order.getTotalCash() != null){
			
			//更新团订单的已付金额
			GroupOrder go = new GroupOrder();
			go.setId(order.getId());
			go.setTotalCash(order.getTotalCash());
			orderMapper.updateByPrimaryKeySelective(go);
		}
	}
	
	
	/* (non-Javadoc)
	 * @see com.yihg.finance.api.FinanceService#calcTotalCashValue(java.lang.Integer, java.lang.Integer)
	 * @param supplierType 1, 120(12), 16, 其它     ：1为销售订单 ，120(12)为其它收入，16为地接订单 ，其它为房餐车等 
	 */
	@Override
	public BigDecimal calcTotalCashValue(Integer orderId, Integer supplierType) {
		// TODO Auto-generated method stub
		return payMapper.calcTotalCashValue(orderId, supplierType);
	}
	
	@Override
	public List<FinancePayDetail> selectFinancePayList(Integer orderId){
		List<FinancePayDetail> financePayDetail=payDetailMapper.selectFinancePayList(orderId);
		return financePayDetail;
	}
}
