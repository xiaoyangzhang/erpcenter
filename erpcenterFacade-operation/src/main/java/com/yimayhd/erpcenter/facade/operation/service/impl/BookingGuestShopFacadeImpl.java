/**
 * 
 */
package com.yimayhd.erpcenter.facade.operation.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.yimayhd.erpcenter.biz.sales.client.service.operation.BookingShopBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.operation.BookingShopDetailBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.operation.BookingShopDetailDeployBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.TourGroupBiz;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingShop;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingShopDetail;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingShopDetailDeploy;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrder;
import com.yimayhd.erpcenter.facade.sales.errorcode.SaleErrorCode;
import com.yimayhd.erpcenter.facade.sales.result.BookingShopResult;
import com.yimayhd.erpcenter.facade.sales.result.ResultSupport;
import com.yimayhd.erpcenter.facade.sales.service.BookingGuestShopFacade;

/**
 * @ClassName: BookingGuestShopFacadeImpl
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author zhangxiaoyang
 * @date 2016年10月27日
 */
public class BookingGuestShopFacadeImpl implements BookingGuestShopFacade {

	@Autowired
	private BookingShopDetailBiz bookingShopDetailBiz;
	@Autowired
	private BookingShopBiz bookingShopBiz;
	@Autowired
	private BookingShopDetailDeployBiz bookingShopDetailDeployBiz;
	@Autowired
	private TourGroupBiz tourGroupBiz;
	@Override
	public ResultSupport delShopDetail(Integer id) {
		ResultSupport resultSupport = new ResultSupport();
		int delResult = bookingShopDetailBiz.deleteByPrimaryKey(id);
		if (delResult < 1) {
			resultSupport.setErrorCode(SaleErrorCode.MODIFY_ERROR);
		}
		return resultSupport;
	}
	
	@Override
	public ResultSupport saveShopDetail(BookingShopDetail shopDetail) {
		ResultSupport resultSupport = new ResultSupport();
		shopDetail.setType((byte)0);//客人购物录入
		int saveResult = bookingShopDetailBiz.save(shopDetail);
		if (saveResult < 1) {
			resultSupport.setErrorCode(SaleErrorCode.MODIFY_ERROR);
		}
		return resultSupport;
	}

	
	@Override
	public BookingShopResult factShop(Integer id, Integer groupId) {
		BookingShopResult result = new BookingShopResult();
		BookingShop shop =bookingShopBiz.selectByPrimaryKey(id);
		result.setBookingShop(shop);
		List<BookingShopDetailDeploy> deploys = bookingShopDetailDeployBiz.selectByBookingId(id);
		List<GroupOrder> groupOrders = tourGroupBiz.selectOrderAndGuestInfoByGroupId(groupId);
		List<BookingShopDetailDeploy> detailDeploys = new ArrayList<BookingShopDetailDeploy>();
		
		for (GroupOrder g : groupOrders) {
			boolean exist = false;
			BookingShopDetailDeploy deploy = new BookingShopDetailDeploy();
			deploy.setOrderId(g.getId());//订单id
			deploy.setOrderNo(g.getOrderNo());//订单号
			deploy.setSupplierName(g.getSupplierName());//组团社
			deploy.setAdultNum(g.getNumAdult());
			deploy.setChildNum(g.getNumChild());
			
			deploy.setGuestNames(g.getReceiveMode());
			for (int i = 0; i < deploys.size()&& !exist; i++) {
				if(deploys.get(i).getOrderId().equals(g.getId())){
					deploy.setBookingId(id);
					deploy.setBuyTotal(deploys.get(i).getBuyTotal());
					deploy.setRemark(deploys.get(i).getRemark());
					detailDeploys.add(deploy);
					exist = true;
					break;
				}
			}
			if(!exist){
				
				deploy.setBookingId(id);
				deploy.setRemark(null);
				deploy.setBuyTotal(null);
				
				detailDeploys.add(deploy);
			}
			
		}
		result.setShopDetailDeploys(deploys);
		return result;
	}

	@Override
	public ResultSupport deldetailGuide(Integer id) {
		ResultSupport resultSupport = new ResultSupport();
		int delResult = bookingShopBiz.deleteByPrimaryKey(id);
		if (delResult < 1) {
			resultSupport.setErrorCode(SaleErrorCode.MODIFY_ERROR);
		}
		return resultSupport;
	}

}
