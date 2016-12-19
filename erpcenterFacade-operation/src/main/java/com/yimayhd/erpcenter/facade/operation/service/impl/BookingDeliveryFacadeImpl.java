
package com.yimayhd.erpcenter.facade.operation.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.biz.basic.service.DicBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.operation.*;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.*;
import com.yimayhd.erpcenter.biz.sys.service.PlatAuthBiz;
import com.yimayhd.erpcenter.biz.sys.service.PlatformOrgBiz;
import com.yimayhd.erpcenter.common.util.DateUtil;
import com.yimayhd.erpcenter.common.util.DateUtils;
import com.yimayhd.erpcenter.dal.sales.client.constants.Constants;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.*;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.*;
import com.yimayhd.erpcenter.dal.sales.client.sales.vo.TourGroupVO;
import com.yimayhd.erpcenter.dal.sales.client.sales.vo.TransferOrderVO;
import com.yimayhd.erpcenter.dal.sys.po.PlatformEmployeePo;
import com.yimayhd.erpcenter.dal.sys.po.PlatformOrgPo;
import com.yimayhd.erpcenter.facade.operation.errorcode.OperationErrorCode;
import com.yimayhd.erpcenter.facade.operation.result.BookingDeliveryResult;
import com.yimayhd.erpcenter.facade.operation.result.ResultSupport;
import com.yimayhd.erpcenter.facade.operation.result.WebResult;
import com.yimayhd.erpcenter.facade.operation.service.BookingDeliveryFacade;
import com.yimayhd.erpresource.biz.service.SupplierBiz;
import com.yimayhd.erpresource.dal.po.SupplierInfo;
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
import org.springframework.util.CollectionUtils;
import org.springframework.web.util.WebUtils;

import java.util.*;

/**
 * @ClassName: BookingDeliveryFacadeImpl
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author zhangxiaoyang
 * @date 2016年10月25日
 */
public class BookingDeliveryFacadeImpl implements BookingDeliveryFacade {

	private static final Logger LOGGER = LoggerFactory.getLogger(BookingDeliveryFacadeImpl.class);

	@Autowired
	private BookingDeliveryBiz bookingDeliveryBiz;
	@Autowired
	private BookingDeliveryPriceBiz bookingDeliveryPriceBiz;
	@Autowired
	private TourGroupBiz tourGroupBiz;
	@Autowired
	private GroupRouteBiz groupRouteBiz;
	@Autowired
	private GroupOrderBiz groupOrderBiz;
	@Autowired
	private SupplierBiz supplierBiz;
	@Autowired
	private BookingSupplierBiz bookingSupplierBiz;
	@Autowired
	private BookingSupplierDetailBiz bookingSupplierDetailBiz;
	@Autowired
	private GroupOrderGuestBiz groupOrderGuestBiz;
	@Autowired
	private BookingGuideBiz bookingGuideBiz;
	@Autowired
	private GroupOrderTransportBiz groupOrderTransportBiz;
	@Autowired
	private GroupRequirementBiz groupRequirementBiz;
	@Autowired
	private DicBiz dicBiz;
	@Autowired
	private PlatformOrgBiz platformOrgBiz;
	@Autowired
	private PlatAuthBiz platAuthBiz;
	@Override
	public BookingDeliveryResult getDeliveryBookingDetailList(Integer groupId) {
		BookingDeliveryResult result = new BookingDeliveryResult();
		List<BookingDelivery> list = bookingDeliveryBiz.getDeliveryListByGroupId(groupId);
        if (list != null && list.size() > 0) {
            for (BookingDelivery delivery : list) {
                delivery.setPriceList(bookingDeliveryPriceBiz.getPriceListByBookingId(delivery.getId()));
            }
        }
        result.setBookingDeliveries(list);
		return result;
	}
	
	@Override
	public BookingDeliveryResult getDeliveryBookingArrangeList(Integer groupId) {
		BookingDeliveryResult bookingDeliveryResult = getDeliveryBookingDetailList(groupId);
		bookingDeliveryResult.setGroupCanEdit(tourGroupBiz.checkGroupCanEdit(groupId));
		return bookingDeliveryResult;
	}

	
	@Override
	public BookingDeliveryResult getDeliveryEditInfo(Integer gid,Integer bid) {
		BookingDeliveryResult result = new BookingDeliveryResult();
		TourGroup groupInfo = tourGroupBiz.selectByPrimaryKey(gid);
		result.setTourGroup(groupInfo);
        List<GroupRoute> routeList = groupRouteBiz.selectByGroupId(gid);
        result.setGroupRoutes(routeList);
        if (groupInfo!=null&&groupInfo.getGroupMode()!=null&&groupInfo.getGroupMode() < 1) {
            List<GroupOrder> orderList = groupOrderBiz.selectOrderByGroupId(gid);
            if (orderList != null && orderList.size() > 0) {
                for (GroupOrder order : orderList) {
                    SupplierInfo supplierInfo = supplierBiz.selectBySupplierId(order.getSupplierId());
                    if (supplierInfo != null) {
                        order.setSupplierName(supplierInfo.getNameFull());
                    }
                }
            }
            result.setGroupOrders(orderList);
        }
        
        if (bid != null) {
            BookingDelivery delivery = bookingDeliveryBiz.getBookingInfoById(bid);
            result.setBookingDelivery(delivery);
        }
		return result;
	}

	
	@Override
	public WebResult<Map<String, Object>> saveDeliveryBooking(String bookingStr,PlatformEmployeePo userInfo) {
		WebResult<Map<String, Object>> result = new WebResult<Map<String, Object>>(); 
		BookingDelivery bookingDelivery = JSON.parseObject(bookingStr, BookingDelivery.class);
        if (!tourGroupBiz.checkGroupCanEdit(bookingDelivery.getGroupId())) {
           // return errorJson("该团已审核或封存，不允许修改该信息");
        	result.setErrorCode(OperationErrorCode.UNABLE_EDIT_ERROR);
        	return result;
        }
        if (bookingDelivery.getId() == null) {
            bookingDelivery.setUserId(userInfo.getEmployeeId());
            bookingDelivery.setUserName(userInfo.getName());
            bookingDelivery.setStateBooking(0);
            bookingDelivery.setStateFinance(0);
            bookingDelivery.setBookingDate(new Date());
            bookingDelivery.setCreateTime(System.currentTimeMillis());
        }
        int id = bookingDeliveryBiz.saveBooking(bookingDelivery);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("groupId", bookingDelivery.getGroupId());
        map.put("bookingId", id);
        result.setValue(map);
		return result;
	}

	
	@Override
	public ResultSupport angencyConfirm(Integer id) {
		ResultSupport result = new ResultSupport();
		bookingDeliveryBiz.angencyConfirm(id);
		return result;
	}

	
	@Override
	public ResultSupport angencyDelete(Integer id) {
		ResultSupport result = new ResultSupport();
		bookingDeliveryBiz.angencyDelete(id);
		return result;
	}

	
	@Override
	public BookingDeliveryResult getBookingDeliveryInfo(Integer bookingId,Integer bizId) {
		BookingDeliveryResult result = new BookingDeliveryResult();
		BookingDelivery bookingDelivery = bookingDeliveryBiz.getBookingInfoById(bookingId);
		result.setBookingDelivery(bookingDelivery);
		
		TourGroup tourGroup = tourGroupBiz.selectByPrimaryKey(bookingDelivery.getGroupId());
		result.setTourGroup(tourGroup);
		
		List<BookingSupplierDetail> driversByGroupIdAndType = bookingSupplierDetailBiz.getDriversByGroupIdAndType(tourGroup.getId(), Constants.FLEET);
		result.setSupplierDetails(driversByGroupIdAndType);
		
		List<GroupRoute> groupRoutes = groupRouteBiz.selectByGroupIdAndBookingId(tourGroup.getId(),bookingDelivery.getId());
		result.setGroupRoutes(groupRoutes);
		
		List<BookingGuide> bookingGuides = bookingGuideBiz.selectByGroupId2(tourGroup.getId());
		result.setBookingGuides(bookingGuides);
		
		List<GroupOrder> orderList = null;
		if (tourGroup.getGroupMode() > 0) {//团队
            orderList = groupOrderBiz.selectOrderByGroupId(tourGroup.getId());
        } else {//散客
            List<BookingDeliveryOrder> deliveryOrderList = bookingDelivery.getOrderList();
            orderList = new ArrayList<GroupOrder>();
            if (deliveryOrderList != null && deliveryOrderList.size() > 0) {
                for (BookingDeliveryOrder deliveryOrder : deliveryOrderList) {
                    orderList.add(groupOrderBiz.selectByPrimaryKey(deliveryOrder.getOrderId()));
                }
            }
        }
		result.setGroupOrders(orderList);
		
		if (!CollectionUtils.isEmpty(orderList)) {
			
			List<GroupOrderGuest> groupOrderGuests = groupOrderGuestBiz.selectByOrderId(orderList.get(0).getId());
			result.setNotGuests(groupOrderGuests);
		}
		List<GroupOrderPrintPo> gopps = new ArrayList<GroupOrderPrintPo>();
	    GroupOrderPrintPo gopp = null;
        // 房量总计
        String total = "";
        if (tourGroup != null && tourGroup.getGroupMode() < 1) {
            for (GroupOrder order : orderList) {
                // 拿到单条订单信息
                gopp = new GroupOrderPrintPo();
                gopp.setPlace((order.getProvinceName() == null ? "" : order.getProvinceName())
                        + (order.getCityName() == null ? "" : order.getCityName()));
                gopp.setRemark(order.getRemarkInternal());
                // 根据散客订单统计客人信息
                List<GroupOrderGuest> guests = groupOrderGuestBiz.selectByOrderId(order.getId());
                //客人-接送方式
                gopp.setGuesStatic(order.getReceiveMode());
                gopp.setGuests(guests);
                
                // 根据散客订单统计人数
                Integer numAdult = groupOrderGuestBiz.selectNumAdultByOrderID(order.getId());
                Integer numChild = groupOrderGuestBiz.selectNumChildByOrderID(order.getId());
                gopp.setPersonNum(numAdult + "+" + numChild);
                // 根据散客订单统计酒店信息
                List<GroupRequirement> grogShopList = groupRequirementBiz.selectByOrderAndType(order.getId(), 3);
                if (grogShopList.size() > 0) {
                    if (StringUtils.isNotBlank(grogShopList.get(0).getHotelLevel()) && !"".equals(grogShopList.get(0).getHotelLevel())) {
                        gopp.setHotelLevel(dicBiz.getById(Integer.parseInt(grogShopList.get(0).getHotelLevel())).getValue()
                                + "\n");
                    }
                }
                gopp.setGroupRequirements(grogShopList);
                // 省外交通
                // 根据散客订单统计接机信息
                List<GroupOrderTransport> groupOrderTransports = groupOrderTransportBiz.selectByOrderId(order.getId());
                gopp.setOrderTransports(groupOrderTransports);
                gopps.add(gopp);
            }
        }
        result.setOrderPrints(gopps);
        if (tourGroup != null && tourGroup.getGroupMode() > 0) {
        	 Map parameters = new HashMap();
             parameters.put("groupId", tourGroup.getId());
             parameters.put("supplierId", null);
             parameters.put("bizId", bizId);
        	List<GroupOrderGuest> orderGuests = groupOrderGuestBiz.getGroupOrderGuests(parameters);
        	result.setGuests(orderGuests);
		}
		return result;
	}

	

	@Override
	public BookingDeliveryResult getDeliveryPriceInfo(Integer bizId,
			PageBean pageBean, Set<Integer> set, String carInfo) {
		BookingDeliveryResult result = new BookingDeliveryResult();
		if (StringUtils.isNotBlank(carInfo)) {
			
            List<Integer> groupIds = bookingSupplierBiz.getGroupIdByCarInfo(carInfo);
            if (groupIds != null && groupIds.size() > 0) {
                String groupIdStr = groupIds.toString().substring(1, groupIds.toString().length() - 1);
                BookingDeliveryPrice bookingDeliveryPrice = (BookingDeliveryPrice)pageBean.getParameter();
                bookingDeliveryPrice.setGroupIds(groupIdStr);
            }
        }
        Map<String, Object> sum = bookingDeliveryPriceBiz.getSupplierPriceTotal(pageBean, bizId, set);
        result.setMap(sum);
        PageBean page = bookingDeliveryPriceBiz.getSupplierPriceListPage(pageBean, bizId, set);
        result.setPageBean(page);
		return result;
	}

	@Override
	public PageBean getLocalTravelAngencyGroupList(PageBean pageBean,
			TourGroupVO tourGroup, Set<Integer> set) {
		pageBean = tourGroupBiz.getLocalTravelAngencyGroupList(pageBean, tourGroup, set);
		return pageBean;
	}

	@Override
	public Integer getOrgIdBy(Integer orderId ,Integer bizId) {
		try{
			GroupOrder groupOrder = groupOrderBiz.selectByPrimaryKey(orderId);
			Integer saleOperatorId = groupOrder.getSaleOperatorId();
			// 根据销售人员ID获得销售人员所在
			PlatformOrgPo pop = platformOrgBiz.getCompanyByEmployeeId2(bizId,saleOperatorId);

			if (pop != null) {
				return pop.getOrgId();
			}else{
				return -1;
			}
		}catch (Exception e){
			return -1;
		}

	}

	@Override
	public WebResult<PageBean> pushListTable(PageBean pageBean, Integer bizId) {
		WebResult<PageBean> result = new WebResult<PageBean>();
		try {
			PageBean pageBean1 = tourGroupBiz.getPushDeliveryList(pageBean, bizId);
			result.setValue(pageBean1);
		}catch (Exception e){
			result.setSuccess(false);
			LOGGER.error(e.getMessage());
		}
		return  result;
	}

	@Override
	public WebResult<String> pushRemoteSave(Integer groupId, Integer bookingId,Integer bizId, String fromAppKey, String fromSecretKey, String toAppKey) {


		WebResult<String> result = new WebResult<String>();
		try{
			// 推送信息给接口中心
			// erp中地接社发送给地接社
			List<GroupOrder> groupOrder = groupOrderBiz.selectOrderByGroupId(groupId);
			TourGroup tourGroup = tourGroupBiz.selectByPrimaryKey(groupId);
			BookingDelivery bd = bookingDeliveryBiz.getBookingInfoById(bookingId);
			TransferOrderVO orderVo = new TransferOrderVO();
			TransferOrder transferOrder = new TransferOrder();
			List<TransferOrderFamily> familys = new ArrayList<TransferOrderFamily>();
			List<TransferOrderGuest> guests = new ArrayList<TransferOrderGuest>();
			List<TransferOrderPrice> prices = new ArrayList<TransferOrderPrice>();
			List<TransferOrderRoute> routes = new ArrayList<TransferOrderRoute>();

			orderVo.setTransferOrder(transferOrder);
			orderVo.setFamilys(familys);
			orderVo.setGuests(guests);
			orderVo.setPrices(prices);
			orderVo.setRoutes(routes);

			// 给TransferOrder赋值
			transferOrder.setId(0);
			transferOrder.setAppId("");
			transferOrder.setApiMethod("");
			transferOrder.setSupplierId(bd.getSupplierId());
			transferOrder.setSupplierName(bd.getSupplierName());
			transferOrder.setFromOrderId(bd.getId());
			transferOrder.setSendUserName(bd.getUserName());
			transferOrder.setSendUserMobile("");
			transferOrder.setSendUserTel("");
			transferOrder.setSendUserFax("");
			transferOrder.setBizId(bizId);
			transferOrder.setOrderId(0);
			transferOrder.setOrderCodeSend(tourGroup.getGroupCode());
			transferOrder.setOrderCodeReceive("");// 接收方订单号
			transferOrder.setOrderProductName(tourGroup.getProductName());
			transferOrder.setSupplierUserName(bd.getContact());
			transferOrder.setSupplierUserMobile(bd.getContactMobile());
			transferOrder.setSupplierUserTel(bd.getContactTel());
			transferOrder.setSupplierUserFax(bd.getContactFax());
			transferOrder.setDaynum(tourGroup.getDaynum());
			transferOrder.setDateStart(tourGroup.getDateStart());
			transferOrder.setDateEnd(tourGroup.getDateEnd());
			transferOrder.setPersonAdult(bd.getPersonAdult());
			transferOrder.setPersonChild(bd.getPersonChild());
			transferOrder.setPersonGuide(bd.getPersonGuide());
			transferOrder.setRemark(bd.getRemark());
			transferOrder.setRemarkService("");
			transferOrder.setTotal(tourGroup.getTotal());
			transferOrder.setStateReceive((byte) 0);
			transferOrder.setStateUpdate((byte) 1);
			transferOrder.setTimeReceive(null);
			transferOrder.setTimeCreate(new Date());
			transferOrder.setTimeUpdate(new Date());
			transferOrder.setFromAppKey(fromAppKey);
			transferOrder.setToAppKey(toAppKey);

			//TransferOrderFamily
			if (tourGroup.getGroupMode() <= 0) { // 散客团
				if (groupOrder != null && groupOrder.size() > 0) {
					for (GroupOrder go : groupOrder) {
						TransferOrderFamily family = new TransferOrderFamily();
						List<GroupOrderTransport> orderTransports = groupOrderTransportBiz
								.selectByGroupId(go.getId());
						family.setFromOrderId(bd.getId());
						family.setFromOrderFamilyId(go.getId());
						family.setLeaderName(go.getReceiveMode());
						family.setPersonAdult(go.getNumAdult());
						family.setPersonChild(go.getNumChild());
						family.setHotelLevel(go.getHotelLevels());
						family.setHotelNums(go.getHotelNums());
						// 省外（长线）－接机信息
						family.setTransportArrival(getAirInfo(orderTransports, 0));
						// 省内（短线）－接送信息
						family.setTransportMiddle(getSourceType(orderTransports));
						// '接送信息：省外（长线）－送机信息',
						family.setTransportLeave(getAirInfo(orderTransports, 1));
						family.setRemark(go.getRemark());
						familys.add(family);

						// 给guestList赋值
						List<GroupOrderGuest> gu = groupOrderGuestBiz.selectByOrderId(go.getId());
						if (gu != null && gu.size() > 0) {
							for (GroupOrderGuest item : gu) {
								TransferOrderGuest guest = new TransferOrderGuest();
								guest.setFromOrderId(bd.getId());
								guest.setFromOrderFamilyId(item.getOrderId());
								guest.setName(item.getName());
								guest.setType(item.getType());
								guest.setCertificateNum(item.getCertificateNum());
								guest.setGender(item.getGender());
								guest.setMobile(item.getMobile());
								guest.setNativePlace(item.getNativePlace());
								guest.setAge(item.getAge());
								guest.setCareer(item.getCareer());
								guest.setIsSingleRoom(item.getIsSingleRoom());
								guest.setRemark(item.getRemark());
								guests.add(guest);
							}
						}

						// routeList
						List<GroupRoute> rlist = groupRouteBiz.selectByOrderId(go.getId());
						if (rlist != null && rlist.size() > 0) {
							for (GroupRoute item : rlist) {
								TransferOrderRoute gRoute = new TransferOrderRoute();
								gRoute.setFromOrderId(bd.getId());
								gRoute.setDayVal(item.getDayNum());
								gRoute.setDateVal(item.getGroupDate());
								gRoute.setBreakfast(item.getBreakfast());
								gRoute.setLunch(item.getLunch());
								gRoute.setSupper(item.getSupper());
								gRoute.setHotels(item.getHotelName());
								gRoute.setRouteDesp(item.getRouteDesp());
								routes.add(gRoute);
							}
						}
					}
				}
			} else {    // 团队订单
				if (groupOrder != null && groupOrder.size() > 0) {
					for (GroupOrder go : groupOrder) {
						TransferOrderFamily family = new TransferOrderFamily();
						family.setId(0);
						family.setOrderId(0);
						family.setFromOrderId(0);
						family.setFromOrderFamilyId(0);
						family.setLeaderName("");
						family.setPersonAdult(0);
						family.setPersonChild(0);
						family.setHotelLevel("");
						family.setHotelNums("");
						family.setTransportArrival("");// 省外（长线）－接机信息
						family.setTransportMiddle("");// 省内（短线）－接送信息
						family.setTransportLeave("");// '接送信息：省外（长线）－送机信息',
						family.setRemark("");
						familys.add(family);

						// 给guestList赋值
						List<GroupOrderGuest> gu = groupOrderGuestBiz.selectByOrderId(go.getId());
						if (gu != null && gu.size() > 0) {
							for (GroupOrderGuest item : gu) {
								TransferOrderGuest guest = new TransferOrderGuest();
								guest.setFromOrderId(bd.getId());
								guest.setFromOrderFamilyId(item.getOrderId());
								guest.setName(item.getName());
								guest.setType(item.getType());
								guest.setCertificateNum(item.getCertificateNum());
								guest.setGender(item.getGender());
								guest.setMobile(item.getMobile());
								guest.setNativePlace(item.getNativePlace());
								guest.setAge(item.getAge());
								guest.setCareer(item.getCareer());
								guest.setIsSingleRoom(item.getIsSingleRoom());
								guest.setRemark(item.getRemark());

								guests.add(guest);
							}
						}
						// routeList
						List<GroupRoute> rlist = groupRouteBiz.selectByGroupId(tourGroup.getId());
						if (rlist != null && rlist.size() > 0) {
							for (GroupRoute item : rlist) {
								TransferOrderRoute gRoute = new TransferOrderRoute();
								gRoute.setFromOrderId(bd.getId());
								gRoute.setDayVal(item.getDayNum());
								gRoute.setDateVal(item.getGroupDate());
								gRoute.setBreakfast(item.getBreakfast());
								gRoute.setLunch(item.getLunch());
								gRoute.setSupper(item.getSupper());
								gRoute.setHotels(item.getHotelName());
								gRoute.setRouteDesp(item.getRouteDesp());

								routes.add(gRoute);
							}
						}
					}
				}
			}
			// priceList
			List<BookingDeliveryPrice> bdp = bookingDeliveryPriceBiz
					.getPriceListByBookingId(bd.getId());
			if (bdp != null && bdp.size() > 0) {
				for (BookingDeliveryPrice item : bdp) {
					TransferOrderPrice price = new TransferOrderPrice();
					price.setFromOrderId(bd.getId());
					price.setItem(item.getItemName());
					price.setRemark(item.getRemark());
					price.setPrice(item.getUnitPrice());
					price.setNumTimes(item.getNumTimes());
					price.setNumPerson(item.getNumPerson());
					price.setTotal(item.getTotalPrice());

					prices.add(price);
				}
			}

			String jsonStr = JSON.toJSONString(orderVo);
			result.setValue(jsonStr);

		}catch(Exception e){
			result.setSuccess(false);
			LOGGER.error(e.getMessage());
		}
		return  result;
	}


	@Override
	public WebResult<Boolean> updatePushStatus(Integer bookingId){
		WebResult<Boolean> result = new WebResult<Boolean>();
		try{
			bookingDeliveryBiz.updatePushStatus(bookingId);
			result.setSuccess(true);
		}catch (Exception e){
			result.setSuccess(false);
		}
		return  result;
	}





	/**
	 * 接送信息
	 *
	 * @param groupOrderTransports
	 * @param flag                 0表示接信息 1表示送信息
	 * @return
	 */
	public String getAirInfo(List<GroupOrderTransport> groupOrderTransports,
							 Integer flag) {
		StringBuilder sb = new StringBuilder();
		if (flag == 0) {
			for (GroupOrderTransport transport : groupOrderTransports) {
				if (transport.getType() == 0 && transport.getSourceType() == 1) {
					sb.append(
							(transport.getDepartureCity() == null ? "" : transport.getDepartureCity()) + "/"
									+ (transport.getArrivalCity() == null ? "" : transport.getArrivalCity()) + " "
									+ (transport.getClassNo() == null ? "" : transport.getClassNo()) + " "
									+ " 发出时间：" + (DateUtils.format(transport.getDepartureDate(), "MM-dd") == null ? "" : DateUtils.format(transport.getDepartureDate(), "MM-dd"))
									+ " "
									+ (transport.getDepartureTime() == null ? "" : transport.getDepartureTime()) + "\n");
				}
			}
		}
		if (flag == 1) {
			for (GroupOrderTransport transport : groupOrderTransports) {
				if (transport.getType() == 1 && transport.getSourceType() == 1) {
					sb.append(
							(transport.getDepartureCity() == null ? "" : transport.getDepartureCity()) + "/"
									+ (transport.getArrivalCity() == null ? "" : transport.getArrivalCity()) + " "
									+ (transport.getClassNo() == null ? "" : transport.getClassNo()) + " "
									+ " 发出时间：" + (DateUtils.format(transport.getDepartureDate(), "MM-dd") == null ? "" : DateUtils.format(transport.getDepartureDate(), "MM-dd"))
									+ " "
									+ (transport.getDepartureTime() == null ? "" : transport.getDepartureTime()) + "\n");
				}
			}
		}
		return sb.toString();
	}


	/**
	 * 省内交通
	 *
	 * @param groupOrderTransports
	 * @return
	 */
	public String getSourceType(List<GroupOrderTransport> groupOrderTransports) {
		StringBuilder sb = new StringBuilder();
		for (GroupOrderTransport transport : groupOrderTransports) {
			if (transport.getSourceType() == 0) {
				Date departureDate = transport.getDepartureDate();
				String departureTime = transport.getDepartureTime();
				String md = "";
				String hm = "";
				if (departureTime != null) {
					md = DateUtil.date2Str(departureDate, "MM-dd");
					hm = departureTime;
				}
				sb.append(md + " " + transport.getDepartureCity() + "/"
						+ transport.getArrivalCity() + " "
						+ transport.getClassNo() + " " + hm + "\n");
			}
		}
		return sb.toString();
	}


}
