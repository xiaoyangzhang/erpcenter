package com.yimayhd.erpcenter.biz.sales.service.impl.sales;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.yimayhd.erpcenter.biz.sales.client.service.sales.GroupOrderGuestBiz;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrderGuest;
import com.yimayhd.erpcenter.dal.sales.client.sales.service.GroupOrderGuestDal;

public class GroupOrderGuestBizImpl implements GroupOrderGuestBiz {

	@Autowired
	private GroupOrderGuestDal groupOrderGuestDal;
	//@Transactional
	@Override
	public int deleteByPrimaryKey(Integer id) {
		return groupOrderGuestDal.deleteByPrimaryKey(id);
//		GroupOrderGuest key = groupOrderGuestMapper.selectByPrimaryKey(id);
//		int primaryKey = groupOrderGuestMapper.deleteByPrimaryKey(id);
//		return primaryKey;
	}
	//@Transactional
	@Override
	public int insert(GroupOrderGuest record) {
		return groupOrderGuestDal.insert(record);
//		int insert = groupOrderGuestMapper.insert(record);
//		return insert;
	}
	//@Transactional
	@Override
	public int insertSelective(GroupOrderGuest record) {
		return groupOrderGuestDal.insertSelective(record);
//		int selective = groupOrderGuestMapper.insertSelective(record);
//		return selective;
	}

	@Override
	public GroupOrderGuest selectByPrimaryKey(Integer id) {
		return groupOrderGuestDal.selectByPrimaryKey(id);
	}
	//@Transactional
	@Override
	public int updateByPrimaryKeySelective(GroupOrderGuest record) {
		int selective = groupOrderGuestDal.updateByPrimaryKeySelective(record);
		return selective;
	}

	@Override
	public int updateByPrimaryKey(GroupOrderGuest record) {
	
		int primaryKey = groupOrderGuestDal.updateByPrimaryKey(record);
		return primaryKey;
	}

	@Override
	public List<GroupOrderGuest> selectByOrderId(Integer orderId) {
		return groupOrderGuestDal.selectByOrderId(orderId);
	}
	
	@Override
	public GroupOrderGuest selectGenderSum(Integer orderId) {
		return groupOrderGuestDal.selectGenderSum(orderId);
	}

	@Override
	public Integer selectGuestCountByOrderId(Integer orderId) {
		return groupOrderGuestDal.selectGuestCountByOrderId(orderId);
	}

	@Override
	public Integer selectNumAdultByOrderID(Integer id) {
		return groupOrderGuestDal.selectNumAdultByOrderID(id);
	}

	@Override
	public Integer selectNumChildByOrderID(Integer id) {
		return groupOrderGuestDal.selectNumChildByOrderID(id);
	}

	@Override
	public Integer selectNumGuideByOrderID(Integer id) {
		return groupOrderGuestDal.selectNumGuideByOrderID(id);
	}


	@Override
	public List<GroupOrderGuest> getGroupOrderGuests(Map parameters) {
		return groupOrderGuestDal.getGroupOrderGuests(parameters);
	}

	@Override
	public List<GroupOrderGuest> getGuestByGuestCertificateNum(
			String guestCertificateNum, Integer orderId) {
		return groupOrderGuestDal.getGuestByGuestCertificateNum(
				guestCertificateNum, orderId);
	}
	@Override
	public List<GroupOrderGuest> getGuestByGroupIdAndType(Integer groupId,
			Integer guestType) {
		return groupOrderGuestDal.getGuestByGroupIdAndType(groupId, guestType);
	}
	@Override
	public List<GroupOrderGuest> getGuestByGroupIdAndIsLeader(Integer groupId,
			Integer isLeader) {
		return groupOrderGuestDal.getGuestByGroupIdAndIsLeader(groupId, isLeader);
	}
	

}
