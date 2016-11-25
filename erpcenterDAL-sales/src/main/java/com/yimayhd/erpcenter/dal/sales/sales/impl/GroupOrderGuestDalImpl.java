package com.yimayhd.erpcenter.dal.sales.sales.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrderGuest;
import com.yimayhd.erpcenter.dal.sales.client.sales.service.GroupOrderDal;
import com.yimayhd.erpcenter.dal.sales.client.sales.service.GroupOrderGuestDal;
import com.yimayhd.erpcenter.dal.sales.sales.dao.GroupOrderGuestMapper;
import com.yimayhd.erpcenter.dal.sales.sales.dao.GroupOrderMapper;
import com.yimayhd.erpcenter.dal.sales.sales.dao.TourGroupMapper;

public class GroupOrderGuestDalImpl implements GroupOrderGuestDal {

	@Autowired
	private GroupOrderGuestMapper groupOrderGuestMapper;

	@Autowired
	private TourGroupMapper tourGroupMapper;
	@Autowired
	private GroupOrderMapper groupOrderMapper;

	@Autowired
	private GroupOrderDal groupOrderDal;

	@Transactional
	@Override
	public int deleteByPrimaryKey(Integer id) {
		GroupOrderGuest key = groupOrderGuestMapper.selectByPrimaryKey(id);
		int primaryKey = groupOrderGuestMapper.deleteByPrimaryKey(id);
		return primaryKey;
	}
	@Transactional
	@Override
	public int insert(GroupOrderGuest record) {
		int insert = groupOrderGuestMapper.insert(record);
		return insert;
	}
	@Transactional
	@Override
	public int insertSelective(GroupOrderGuest record) {
		int selective = groupOrderGuestMapper.insertSelective(record);
		return selective;
	}

	@Override
	public GroupOrderGuest selectByPrimaryKey(Integer id) {
		return groupOrderGuestMapper.selectByPrimaryKey(id);
	}
	@Transactional
	@Override
	public int updateByPrimaryKeySelective(GroupOrderGuest record) {
		int selective = groupOrderGuestMapper.updateByPrimaryKeySelective(record);
		return selective;
	}

	@Override
	public int updateByPrimaryKey(GroupOrderGuest record) {
	
		int primaryKey = groupOrderGuestMapper.updateByPrimaryKey(record);
		return primaryKey;
	}

	@Override
	public List<GroupOrderGuest> selectByOrderId(Integer orderId) {
		return groupOrderGuestMapper.selectByOrderId(orderId);
	}
	
	@Override
	public GroupOrderGuest selectGenderSum(Integer orderId) {
		return groupOrderGuestMapper.selectGenderSum(orderId);
	}

	@Override
	public Integer selectGuestCountByOrderId(Integer orderId) {
		return groupOrderGuestMapper.selectGuestCountByOrderId(orderId);
	}

	@Override
	public Integer selectNumAdultByOrderID(Integer id) {
		return groupOrderGuestMapper.selectNumAdultByOrderID(id);
	}

	@Override
	public Integer selectNumChildByOrderID(Integer id) {
		return groupOrderGuestMapper.selectNumChildByOrderID(id);
	}

	@Override
	public Integer selectNumGuideByOrderID(Integer id) {
		return groupOrderGuestMapper.selectNumGuideByOrderID(id);
	}


	@Override
	public List<GroupOrderGuest> getGroupOrderGuests(Map parameters) {
		return groupOrderGuestMapper
				.getGroupOrderGuestList(parameters);
	}

	@Override
	public List<GroupOrderGuest> getGuestByGuestCertificateNum(
			String guestCertificateNum, Integer orderId) {
		return groupOrderGuestMapper.getGuestByGuestCertificateNum(
				guestCertificateNum, orderId);
	}
	@Override
	public List<GroupOrderGuest> getGuestByGroupIdAndType(Integer groupId,
			Integer guestType) {
		return groupOrderGuestMapper.getGuestByGroupIdAndType(groupId, guestType);
	}
	@Override
	public List<GroupOrderGuest> getGuestByGroupIdAndIsLeader(Integer groupId,
			Integer isLeader) {
		return groupOrderGuestMapper.getGuestByGroupIdAndIsLeader(groupId, isLeader);
	}
	
	@Override
	public List<GroupOrderGuest> selectGuestTicketInfo(Integer resId) {
		List<GroupOrderGuest> list= groupOrderGuestMapper.selectGuestTicketInfo(resId);
		 return list;
	}
	@Override
	public List<GroupOrderGuest> getEmployeeByMobile(String mobile) {
		return groupOrderGuestMapper.getEmployeeByMobile(mobile);
	}
}
