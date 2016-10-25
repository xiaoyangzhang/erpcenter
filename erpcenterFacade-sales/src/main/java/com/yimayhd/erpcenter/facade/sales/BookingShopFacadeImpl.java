package com.yimayhd.erpcenter.facade.sales;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.TourGroupBiz;
import com.yimayhd.erpcenter.biz.sys.service.PlatformEmployeeBiz;
import com.yimayhd.erpcenter.dal.sales.client.sales.constants.Constants;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.TourGroup;
import com.yimayhd.erpcenter.facade.sales.query.BookingShopListDTO;
import com.yimayhd.erpcenter.facade.sales.service.BookingShopFacade;

public class BookingShopFacadeImpl implements BookingShopFacade{
	@Autowired
	private PlatformEmployeeBiz platformEmployeeBiz;
	@Autowired
	private TourGroupBiz tourGroupBiz;
	
	@Override
	public PageBean bookingShopList(BookingShopListDTO bookingShopListDTO) {
		TourGroup group = bookingShopListDTO.getGroup();
		PageBean pageBean = new PageBean();
		if(group.getPage()==null){
			group.setPage(1);
		}
		if(group.getPageSize()==null){
			pageBean.setPageSize(Constants.PAGESIZE);
		}else{
			pageBean.setPageSize(group.getPageSize());
		}
		if(StringUtils.isBlank(bookingShopListDTO.getSaleOperatorIds()) && StringUtils.isNotBlank(bookingShopListDTO.getOrgIds())){
			Set<Integer> set = new HashSet<Integer>();
			String[] orgIdArr = bookingShopListDTO.getOrgIds().split(",");
			for(String orgIdStr : orgIdArr){
				set.add(Integer.valueOf(orgIdStr));
			}
			set = platformEmployeeBiz.getUserIdListByOrgIdList(bookingShopListDTO.getBizId(), set);
			String salesOperatorIds="";
			for(Integer usrId : set){
				salesOperatorIds+=usrId+",";
			}
			if(!salesOperatorIds.equals("")){
				group.setSaleOperatorIds(salesOperatorIds.substring(0, salesOperatorIds.length()-1));
			}
		}
		pageBean.setParameter(group);
		pageBean.setPage(group.getPage());
		
		pageBean = tourGroupBiz.selectBookingShopListPage(pageBean, bookingShopListDTO.getBizId(),bookingShopListDTO.getIds());
		return pageBean;
	}

}
