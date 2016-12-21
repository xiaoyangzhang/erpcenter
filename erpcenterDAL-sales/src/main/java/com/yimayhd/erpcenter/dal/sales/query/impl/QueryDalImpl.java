package com.yimayhd.erpcenter.dal.sales.query.impl;

import java.text.DecimalFormat;
import java.util.*;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.util.TypeUtils;
import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.dal.sales.client.operation.vo.GroupBookingInfo;
import com.yimayhd.erpcenter.dal.sales.client.query.service.QueryDAL;
import com.yimayhd.erpcenter.dal.sales.client.query.vo.ProductGuestCondition;
import com.yimayhd.erpcenter.dal.sales.client.query.vo.ProductGuestShoppingItem;
import com.yimayhd.erpcenter.dal.sales.client.query.vo.ProductGuestStaticsVo;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrder;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrderGuest;
import com.yimayhd.erpcenter.dal.sales.client.sales.vo.SaleOperatorVo;
import com.yimayhd.erpcenter.dal.sales.operation.dao.OperationMapper;
import com.yimayhd.erpcenter.dal.sales.query.dao.QueryMapper;
import com.yimayhd.erpcenter.dal.sales.sales.dao.GroupOrderGuestMapper;
import com.yimayhd.erpcenter.dal.sales.sales.dao.GroupOrderMapper;
import com.yimayhd.erpcenter.dal.sales.sales.dao.GroupOrderTransportMapper;
import com.yimayhd.erpcenter.dal.sales.sales.dao.GroupRequirementMapper;
import org.springframework.util.CollectionUtils;

public class QueryDalImpl implements QueryDAL {

	@Resource
	private GroupOrderMapper orderMapper;
	@Resource
	private OperationMapper operationMapper;
	@Autowired
	private GroupOrderMapper groupOrderMapper;
	@Autowired
	private QueryMapper queryMapper;
	@Autowired
	private GroupOrderGuestMapper guestMapper;
	@Autowired
	private GroupOrderTransportMapper transportMapper;
	@Autowired
	private GroupRequirementMapper requirementMapper;
	
	@Override
	public String productGuestStatics(
			ProductGuestCondition condition,Set<Integer> userIds) {
		List<ProductGuestStaticsVo> list = orderMapper.productGuestStatics(condition,userIds);
		if(list!=null && list.size()>0){
			int len = list.size();
			
			StringBuilder sb = new StringBuilder();
			int otherTotal = 0;
			for(int idx=0;idx<list.size();idx++){
				ProductGuestStaticsVo vo =list.get(idx);
				
					sb.append("['");
					sb.append(vo.getProductBrandName());
					sb.append("【");
					sb.append(vo.getProductName());
					sb.append("】");
					sb.append("',");
					sb.append(vo.getGuestCnt().intValue());
					sb.append("],");				
				
			}
			
			return sb.substring(0, sb.length()-1); 
		}
		return "";
	}

	@Override
	public String guestSourceStatics(
			ProductGuestCondition condition,Set<Integer> userIds) {
		List<Map<String,Object>> list = orderMapper.guestSourceStatics(condition,userIds);
		if(list!=null && list.size()>0){
			int len = list.size();
			
			StringBuilder sb = new StringBuilder();
			int otherTotal = 0;
			for(int idx=0;idx<list.size();idx++){
				Map<String,Object> map =list.get(idx);
			
					sb.append("['");
					sb.append(map.get("provinceName"));
					sb.append("（");
					sb.append(map.get("adultCnt"));
					sb.append("大");
					sb.append(map.get("childCnt"));
					sb.append("小）',");
					sb.append(map.get("guestCnt"));
					sb.append("],");				
				
			}
			
			return sb.substring(0, sb.length()-1); 
		}
		return "";
	}

//	@Override
//	public String getGroupNumStatics(List<GroupOrder> list,Integer dataType) {
//		// TODO Auto-generated method stub
//
//		if(list!=null && list.size()>0){
//			int len = list.size();
//			int otherTotal = 0;
//			StringBuilder sb = new StringBuilder();
//			for(int idx=0;idx<list.size();idx++){
//				GroupOrder groupOrder =list.get(idx);
//					sb.append("['");
//					sb.append(groupOrder.getSupplierName());
//					if(dataType==0){
//						sb.append("（");
//						sb.append(groupOrder.getNumAdult());
//						sb.append("大");
//						sb.append(groupOrder.getNumChild());
//						sb.append("小）',");
//						sb.append(groupOrder.getGuestCount());
//					}else{
//						sb.append("',");
//						sb.append(groupOrder.getSupplierCount());
//					}
//
//					sb.append("],");
//			}
//
//			return sb.substring(0, sb.length()-1);
//		}
//		return null;
//	}

	@Override
	public PageBean selectGroupBookingListPage(PageBean pageBean,
			Integer bizId, Set<Integer> set) {
		
		List<GroupBookingInfo> bookingGroups =operationMapper.selectGroupBookingListPage(pageBean, bizId, set);
		if(bookingGroups!=null && bookingGroups.size()>0){
			for(GroupBookingInfo bg : bookingGroups){
				if (bg.getGroupMode() != null){
					if(bg.getGroupMode().intValue()>0){
						List<Map<String,Object>> orderMap= groupOrderMapper.getOrderBreifInfoByGroupId(bg.getGroupId());
						if(orderMap!=null && orderMap.size()>0){
							Map<String,Object> map = orderMap.get(0);
							bg.setOrderId(TypeUtils.castToInt(map.get("orderId")));
							bg.setReceiveMode(TypeUtils.castToString(map.get("receiveMode")));
						}
					}else{
						bg.setReceiveMode("");
					}
				}
			}
		}
		pageBean.setResult(bookingGroups);
		return pageBean;
	}

	@Override
	public List<ProductGuestStaticsVo> productGuestStatics2(
			ProductGuestCondition condition, Set<Integer> userIds) {
		return  orderMapper.productGuestStatics(condition,userIds);
	}

	@Override
	public List<Map<String, Object>> guestSourceStatics2(
			ProductGuestCondition condition, Set<Integer> userIds) {
		return orderMapper.guestSourceStatics(condition,userIds);
	}

	@Override
	public PageBean<SaleOperatorVo> selectSaleOperatorByConListPage(
			PageBean<SaleOperatorVo> pageBean, Integer bizId, Set<Integer> set) {
		
		SaleOperatorVo paramObj = (SaleOperatorVo)pageBean.getParameter();
		Set<Integer> orderIdSet = null;
		if (StringUtils.isNotBlank(paramObj.getGuestName()) || (StringUtils.isNotBlank(paramObj.getMobile()))) {
			orderIdSet = new HashSet<Integer>();
			List<GroupOrderGuest> guestList = guestMapper.selectOrderIdsByNameOrMobile(pageBean);
			if(!CollectionUtils.isEmpty(guestList)){
				for(GroupOrderGuest guest : guestList){
					orderIdSet.add(guest.getOrderId());
				}

			}
			if(orderIdSet.size()==0){
				orderIdSet.add(-1);
			}
		}
		paramObj.setOrderIdSet(orderIdSet);
//		if(orderIdSet.size()==0){
//			orderIdSet.add(-1);
//		}
//		paramObj.setOrderIdSet(orderIdSet);
//		if (CollectionUtils.isEmpty(orderIdSet)) {
//			paramObj.setOrderIdSet(null);
//		}
//		pageBean.getParameter().
		List<SaleOperatorVo> orders = groupOrderMapper.selectSaleOperatorByConListPage(pageBean, bizId, set);
		if(!CollectionUtils.isEmpty(orders)){
			SaleOperatorVo order = null;
			StringBuilder sb = new StringBuilder();
			for(int i = 0; i < orders.size(); i++){
				if(i > 0){
					sb.append(",");
				}
				order = orders.get(i);
				sb.append(order.getId());
			}
			String orderIds = sb.toString();

			List<Map<String, Object>> guests = guestMapper.selectGuestForOrders(pageBean, orderIds);
			this.mergeGuestsInOrders(orders, guests);

			List<Map<String, Object>> requirements = requirementMapper.selectRequirementForOrders(pageBean, orderIds);
			this.mergeRequirementsInOrders(orders, requirements);

			List<Map<String, Object>> transports = transportMapper.selectTransportForOrders(pageBean, orderIds);
			this.mergeTransportsInOrders(orders, transports);
		}

		pageBean.setResult(orders);
		return pageBean;
	}
	
	public void mergeRequirementsInOrders(List<SaleOperatorVo> orders, List<Map<String, Object>> requirements){
		if(orders == null || orders.size() == 0 || requirements == null || requirements.size() == 0){
			return;
		}
		
		SaleOperatorVo order = null;
		
		for(int i = 0; i < orders.size(); i++){
			order = orders.get(i);
			
			Map<String, Object> requirement = null;
			for(int j = 0; j < requirements.size(); j++){
				requirement = requirements.get(j);
				Integer requirementOrderId = (Integer)requirement.get("order_id");
				if(order.getId().intValue() == requirementOrderId.intValue()){
					order.setHotelLevels(requirement.get("hotelLevels").toString());
					order.setHotelNums(requirement.get("hotelNums").toString());
					break;
				}
			}
		}
	}
	
	public void mergeGuestsInOrders(List<SaleOperatorVo> orders, List<Map<String, Object>> guests){
		if(orders == null || orders.size() == 0 || guests == null || guests.size() == 0){
			return;
		}
		
		SaleOperatorVo order = null;
		
		for(int i = 0; i < orders.size(); i++){
			order = orders.get(i);
			
			Map<String, Object> guest = null;
			for(int j = 0; j < guests.size(); j++){
				guest = guests.get(j);
				Integer guestOrderId = (Integer)guest.get("order_id");
				if(order.getId().intValue() == guestOrderId.intValue()){
					order.setGuestNames(guest.get("guestNames").toString());
					break;
				}
			}
		}
	}
	
	public void mergeTransportsInOrders(List<SaleOperatorVo> orders, List<Map<String, Object>> transports){
		if(orders == null || orders.size() == 0 || transports == null || transports.size() == 0){
			return;
		}
		
		SaleOperatorVo order = null;
		
		for(int i = 0; i < orders.size(); i++){
			order = orders.get(i);
			
			Map<String, Object> transport = null;
			for(int j = 0; j < transports.size(); j++){
				transport = transports.get(j);
				Integer guestOrderId = (Integer)transport.get("order_id");
				if(order.getId().intValue() == guestOrderId.intValue()){
					order.setUpAir(transport.get("upAir").toString());
					order.setOffAir(transport.get("offAir").toString());
					order.setTrans(transport.get("trans").toString());
					break;
				}
			}
		}
	}
	
	@Override
	public PageBean selectProductGuestShopStatics(PageBean pageBean) {
		List<Map<String,Object>> orderList = queryMapper.getProductGuestShoppingGroupOrderListPage(pageBean);
		if(orderList!=null && orderList.size()>0){
			for(Map<String,Object> map : orderList){
				String orderIds = TypeUtils.castToString(map.get("order_ids"));
				List<ProductGuestShoppingItem> provinceShoppingList = new ArrayList<ProductGuestShoppingItem>();
				if(StringUtils.isNotEmpty(orderIds)){
					if(orderIds.endsWith(",")){
						orderIds = orderIds.substring(0, orderIds.length()-1);
					}
					List<Map<String,Object>> cityShoppingList = queryMapper.getGuestSourceShoppingByOrderId(orderIds);
					if(cityShoppingList!=null && cityShoppingList.size()>0){
						for(Map<String,Object> cityMap :cityShoppingList){							
							provinceShoppingStatics(provinceShoppingList,cityMap);
						}
					}
				}
				map.put("provinces", provinceShoppingList);
			}
		}
		pageBean.setResult(orderList);
		return pageBean;
	}
	
	private void provinceShoppingStatics(List<ProductGuestShoppingItem> provinceShoppingList,Map<String,Object> cityMap){
		String provinceName = TypeUtils.castToString(cityMap.get("province_name"));
		Integer adult = TypeUtils.castToInt(cityMap.get("adult"));
		Double total = TypeUtils.castToDouble(cityMap.get("total"));
		
		for(ProductGuestShoppingItem province : provinceShoppingList){
			if(provinceName.equals(province.getProvinceName())){
				province.setAdultSum(adult+province.getAdultSum());
				province.setTotalSum(total+province.getTotalSum());
				province.getCitys().add(cityMap);				
				return;
			}
		}
		ProductGuestShoppingItem item = new ProductGuestShoppingItem();
		item.setProvinceName(provinceName);
		item.setAdultSum(adult);
		item.setTotalSum(total);
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		list.add(cityMap);
		item.setCitys(list);
		
		provinceShoppingList.add(item);
	}

	/**
	 * 获取预定安排的总人数
	 */
	@Override
	public Map<String, Object> getPersonCount(PageBean pageBean, Integer bizId, Set<Integer> set) {
		return operationMapper.getPersonCount(pageBean, bizId, set);
	}

	@Override
	public PageBean selectSupplierGuestShopStatics(PageBean pageBean,Set<Integer> set) {
		List<Map<String,Object>> orderList = queryMapper.getSupplierGuestShoppingGroupOrderListPage(pageBean,set);
		List<Map<String,Object>> detailList = queryMapper.getGuestSourceShoppingByOrderIds(pageBean,set);
		
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		if(orderList!=null && orderList.size()>0){
		
			for(Map<String,Object> map : orderList){
				if(detailList!=null && detailList.size()>0){
					List<ProductGuestShoppingItem> provinceShoppingList = new ArrayList<ProductGuestShoppingItem>();
					for(Map<String,Object> detail : detailList){
						if (map.get("supplierId").equals(detail.get("supplier_id"))) {
							provinceShoppingStatics(provinceShoppingList,detail);
						}
					}
					map.put("provinces", provinceShoppingList);
				}
				list.add(map);
			}
		}
		pageBean.setResult(list);
		return pageBean;
	}

	public PageBean selectSupplierGuestShopStatic(PageBean pageBean,Set<Integer> set) {
		List<Map<String,Object>> orderList = queryMapper.getSupplierGuestShoppingGroupOrderListPage(pageBean,set);
		List<Map<String,Object>> detailList = queryMapper.getGuestSourceShoppingByOrderIds(pageBean,set);

		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		if(orderList!=null && orderList.size()>0){

			for(Map<String,Object> map : orderList){
				if(detailList!=null && detailList.size()>0){
					List<ProductGuestShoppingItem> provinceShoppingList = new ArrayList<ProductGuestShoppingItem>();
					for(Map<String,Object> detail : detailList){
						if (map.get("supplierId").equals(detail.get("supplier_id"))) {
							provinceShoppingStatics(provinceShoppingList,detail);
						}
					}
					map.put("provinces", provinceShoppingList);
				}
				list.add(map);
			}
		}
		pageBean.setResult(list);
		return pageBean;
		}
	
	@Override
	public String getGuestSexInfo(Map parameters) {
		List<GroupOrderGuest> guests = guestMapper.selectGroupOrderGuestList(parameters);
		StringBuffer sb=new StringBuffer();
		DecimalFormat df=new DecimalFormat("#.##");
		if(guests!=null && guests.size()>0) {
			int guestNum = guests.size();
			int maleGuestNum = 0;
			int femaleGuestNum = 0;
			int maleNum=0;
			int femaleNum=0;
			int maleAdultNum=0;
			int maleChildNum=0;
			int femaleAdultNum=0;
			int femaleChildNum=0;
			if (guests!=null && guests.size()>0) {
				for (GroupOrderGuest guest : guests) {
					if (guest.getGender() == 0) {
						femaleNum++;
						//女性成人数
						if (guest.getType() == 1) {
							femaleAdultNum++;
						}
						//女性儿童数
						if (guest.getType() == 2) {
							femaleChildNum++;
						}
					}
					if (guest.getGender() == 1 || guest.getGender() == null) {
						maleNum++;
						//男性成人数
						if (guest.getType() == 1) {
							maleAdultNum++;
						}
						//男性儿童数
						if (guest.getType() == 2) {
							maleChildNum++;
						}
					}

				}
			}
			//			System.out.println(maleNum+","+guestNum);
//			System.out.println(femaleNum+","+guestNum);
			sb.append("['").append("男："/*+df.format(Double.parseDouble(maleNum+"")/Double.parseDouble(guestNum+"")*100)+"%（"*/).append("（"+maleAdultNum+"大"+maleChildNum+"小）',").append(maleNum).append("],")
			.append("['").append("女："/*+df.format(Double.parseDouble(femaleNum+"")/Double.parseDouble(guestNum+"")*100)+"%（"*/).append("（"+femaleAdultNum+"大"+femaleChildNum+"小）',").append(femaleNum).append("]");
		}
		return sb.toString();
	}

	@Override
	public String getGuestAgeInfo(Map parameters) {
		List<Map<String, Object>> ageList = guestMapper.selectGuestAgeList(parameters);
		StringBuffer sb=new StringBuffer();
		DecimalFormat df=new DecimalFormat("#.##");
		if (ageList!=null && ageList.size() > 0) {
			//12岁以下人数
			long age1=0;
			//13~23岁人数
			long age2=0;
			//24~28岁人数
			long age3=0;
			//29~49岁人数
			long age4=0;
			//50~55岁人数
			long age5=0;
			//56~65岁人数
			long age6=0;
			//66岁以上人数
			long age7=0;
			//总人数
			long personCount=0;
			if (ageList!=null && ageList.size()>0) {
				for (Map<String, Object> map : ageList) {
					if (map!=null) {
						personCount += ((map.get("adultCount") == null ? 0
								: (Long) map.get("adultCount")) + (map
								.get("childCount") == null ? 0 : (Long) map
								.get("childCount")));
						age1 += map.get("12以下") == null ? 0 : (Long) map
								.get("12以下");
						age2 += map.get("13~23") == null ? 0 : (Long) map
								.get("13~23");
						age3 += map.get("24~28") == null ? 0 : (Long) map
								.get("24~28");
						age4 += map.get("29~49") == null ? 0 : (Long) map
								.get("29~49");
						age5 += map.get("50~55") == null ? 0 : (Long) map
								.get("50~55");
						age6 += map.get("56~65") == null ? 0 : (Long) map
								.get("56~65");
						age7 += map.get("66以上") == null ? 0 : (Long) map
								.get("66以上");
					}
				}
			}
			sb. append("['").append("0~12岁：',"/*+df.format(Double.parseDouble(age1+"")/Double.parseDouble(personCount+"")*100)+"%（"*/).append(age1).append("],").
				append("['").append("13~23岁：',"/*+df.format(Double.parseDouble(age2+"")/Double.parseDouble(personCount+"")*100)+"%（"*/).append(age2).append("],").
				append("['").append("24~28岁：',"/*+df.format(Double.parseDouble(age3+"")/Double.parseDouble(personCount+"")*100)+"%（"*/).append(age3).append("],").
				append("['").append("29~49岁：',"/*+df.format(Double.parseDouble(age4+"")/Double.parseDouble(personCount+"")*100)+"%（"*/).append(age4).append("],").
				append("['").append("50~55岁：',"/*+df.format(Double.parseDouble(age5+"")/Double.parseDouble(personCount+"")*100)+"%（"*/).append(age5).append("],").
				append("['").append("56~65岁：',"/*+df.format(Double.parseDouble(age6+"")/Double.parseDouble(personCount+"")*100)+"%（"*/).append(age6).append("],").
				append("['").append("66岁以上：',"/*+df.format(Double.parseDouble(age7+"")/Double.parseDouble(personCount+"")*100)+"%（"*/).append(age7).append("]");
		}
		return sb.toString();
	}

	@Override
	public String getGuestAirTimeInfo(Map parameters) {
		parameters.put("time1", "06:00");
		//parameters.put("time11", "09:00");
		parameters.put("time2", "09:00");
		//parameters.put("time22", "12:00");
		parameters.put("time3", "12:00");
		//parameters.put("time33", "15:00");
		parameters.put("time4", "15:00");
		//parameters.put("time44", "18:00");
		parameters.put("time5", "18:00");
		//parameters.put("time55", "21:00");
		parameters.put("time6", "21:00");
		//parameters.put("time66", "24:00");
		parameters.put("time7", "00:00");
		parameters.put("time8", "23::59");
		//parameters.put("time77", "05:00");
		List<Map<String, Object>> airTimeList = transportMapper.selectGuestAirTimeList(parameters);
		StringBuffer sb=new StringBuffer();
		DecimalFormat df=new DecimalFormat("#.##");
		if (airTimeList!=null && airTimeList.size()>0) {
			//航班落地时间在6~8时人数
			long time1=0;
			//航班落地时间在9~11时人数
			long time2=0;
			//航班落地时间在12~14时人数
			long time3=0;
			//航班落地时间在15~17时人数
			long time4=0;
			//航班落地时间在18~20时人数
			long time5=0;
			//航班落地时间在21~23时人数
			long time6=0;
			//航班落地时间在24~5时人数
			long time7=0;
			//总人数
			long personCount=0;
			//System.out.println(airTimeList);
			
			for (Map<String, Object> map : airTimeList) {
				if (map!=null) {
					//System.out.println(map.get("adultCount"));
					//System.out.println(map.get("childCount"));
					personCount += ((map.get("adultCount") == null ? 0
							: (Long) map.get("adultCount")) + (map
							.get("childCount") == null ? 0 : (Long) map
							.get("childCount")));
					time1 += map.get("6~8") == null ? 0 : (Long) map.get("6~8");
					time2 += map.get("9~11") == null ? 0 : (Long) map
							.get("9~11");
					time3 += map.get("12~14") == null ? 0 : (Long) map
							.get("12~14");
					time4 += map.get("15~17") == null ? 0 : (Long) map
							.get("15~17");
					time5 += map.get("18~20") == null ? 0 : (Long) map
							.get("18~20");
					time6 += map.get("21~23") == null ? 0 : (Long) map
							.get("21~23");
					time7 += map.get("24~5") == null ? 0 : (Long) map
							.get("24~5");
				}
			}
			
			sb. append("['").append("6~8时：',"/*+df.format(Double.parseDouble(time1+"")/Double.parseDouble(personCount+"")*100)+"%（"*/).append(time1).append("],").
			append("['").append("9~11时：',"/*+df.format(Double.parseDouble(time2+"")/Double.parseDouble(personCount+"")*100)+"%（"*/).append(time2).append("],").
			append("['").append("12~14时：',"/*+df.format(Double.parseDouble(time3+"")/Double.parseDouble(personCount+"")*100)+"%（"*/).append(time3).append("],").
			append("['").append("15~17时：',"/*+df.format(Double.parseDouble(time4+"")/Double.parseDouble(personCount+"")*100)+"%（"*/).append(time4).append("],").
			append("['").append("18~20时：',"/*+df.format(Double.parseDouble(time5+"")/Double.parseDouble(personCount+"")*100)+"%（"*/).append(time5).append("],").
			append("['").append("21~23时：',"/*+df.format(Double.parseDouble(time6+"")/Double.parseDouble(personCount+"")*100)+"%（"*/).append(time6).append("],").
			append("['").append("24~5时：',"/*+df.format(Double.parseDouble(time7+"")/Double.parseDouble(personCount+"")*100)+"%（"*/).append(time7).append("]");
	
		}
		return sb.toString();
	}

	@Override
	public String getGuestSouceInfo(Map parameters) {
		List<Map<String,Object>> list = orderMapper.guestSourceStatics2(parameters);
		if(list!=null && list.size()>0){
			int len = list.size();
			//System.out.println(len);
			//int otherArea=0;
			StringBuilder sb = new StringBuilder();
			long otherTotal = 0;
			long otherAdultTotal = 0;
			long otherChildTotal = 0;
				if (list!=null && list.size()>0) {
					if (len <= 10) {
						for (int idx = 0; idx < list.size(); idx++) {

							Map<String, Object> map = list.get(idx);

							sb.append("['");
							sb.append(map.get("provinceName"));
							sb.append("（");
							sb.append(map.get("adultCnt") == null ? 0 : map
									.get("adultCnt"));
							sb.append("大");
							sb.append(map.get("childCnt") == null ? 0 : map
									.get("childCnt"));
							sb.append("小）',");
							sb.append(map.get("guestCnt") == null ? 0 : map
									.get("guestCnt"));
							sb.append("],");
						}

					}
					if (len > 10) {
						for (int idx = 0; idx < 10; idx++) {

							Map<String, Object> map = list.get(idx);

							sb.append("['");
							sb.append(map.get("provinceName"));
							sb.append("（");
							sb.append(map.get("adultCnt") == null ? 0 : map
									.get("adultCnt"));
							sb.append("大");
							sb.append(map.get("childCnt") == null ? 0 : map
									.get("childCnt"));
							sb.append("小）',");
							sb.append(map.get("guestCnt") == null ? 0 : map
									.get("guestCnt"));
							sb.append("],");
						}
						for (int i = 0; i >= 10 && i < len; i++) {
							Map<String, Object> map = list.get(i);
							otherAdultTotal +=( map.get("adultCnt") == null ? 0
									: (Long) map.get("adultCnt"));
							otherChildTotal += (map.get("childCnt") == null ? 0
									: (Long) map.get("childCnt"));
							otherTotal += otherAdultTotal + otherChildTotal;
						}
						//	System.out.println("hahahfdahfhahahhdf");
						sb.append("['")
								.append("其他地区")
								.append("（" + otherAdultTotal + "大"
										+ otherChildTotal + "小）',")
								.append(otherTotal).append("],");
						//return sb.toString(); 
					}
					return sb.substring(0, sb.length()-1); 
				}
		}
		return "";
	}

	@Override
	public Map<String, Object> getReceivePersonStatistics(Map parameters) {
		return queryMapper.getReceivePersonStatistics(parameters);
	}

	@Override
	public Map<String, Object> getPersonCountMap(Map parameters) {
		return queryMapper.getPersonCountMap(parameters);
	}

	@Override
	public Map<String, Object> selectSaleOperatorTotalPerson(
			PageBean<SaleOperatorVo> pageBean, Integer bizId, Set<Integer> set) {
		return groupOrderMapper.selectSaleOperatorTotalPerson(pageBean, bizId, set);
	}

	@Override
	public Map<String, Object> selectDeliveryOriginalTotal(Map parameters) {
		return queryMapper.selectDeliveryOriginalTotal(parameters);
	}

	@Override
	public Map<String, Object> selectOriginalTotal1(Map parameters) {
		return queryMapper.selectOriginalTotal1(parameters);
	}

	@Override
	public Map<String, Object> selectOriginalTotal2(Map parameters) {
		return queryMapper.selectOriginalTotal2(parameters);
	}

	@Override
	public List<Map<String, Object>> selectBookingSupplierCountForGroups(String groupIds, Integer supplierType, String supplierName) {
		return operationMapper.selectBookingSupplierCountForGroups(groupIds, supplierType, supplierName);
	}
	
	@Override
	public List<Map<String, Object>> selectBookingShopCountForGroups(String groupIds, String supplierName) {
		return operationMapper.selectBookingShopCountForGroups(groupIds, supplierName);
	}
	
	@Override
	public List<Map<String, Object>> selectBookingGuideForGroups(String groupIds, String supplierName) {
		return operationMapper.selectBookingGuideForGroups(groupIds, supplierName);
	}
	
	@Override
	public List<Map<String, Object>> selectBookingDeliveryForGroups(String groupIds, String supplierName) {
		return operationMapper.selectBookingDeliveryForGroups(groupIds, supplierName);
	}

	public static double mode(double[] array) {
		  Arrays.sort(array);
		  int count = 1;
		  int longest = 0;
		  double mode = 0;
		  for (int i = 0; i < array.length - 1; i++) {
		   if (array[i] == array[i + 1]) {
		    count++;
		   } else {
		    count = 1;//如果不等于，就换到了下一个数，那么计算下一个数的次数时，count的值应该重新符值为一
		    continue;
		   }
		   if (count > longest) {
		    mode = array[i];
		    longest = count;
		   }
		  }
		  System.out.println(longest);//打印出这个数出现的次数已判断是否正确
		  return mode;
		 }
	public static void main(String[] args) {
		double aa[] = {1.0,1.0,2.3,1.0};
		QueryDalImpl.mode(aa);
	}

}
