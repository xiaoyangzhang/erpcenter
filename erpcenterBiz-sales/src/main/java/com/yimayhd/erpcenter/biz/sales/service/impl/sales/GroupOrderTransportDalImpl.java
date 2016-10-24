package com.yimayhd.erpcenter.biz.sales.service.impl.sales;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrderTransport;
import com.yimayhd.erpcenter.dal.sales.client.sales.service.GroupOrderTransportDal;
import com.yimayhd.erpcenter.dal.sales.client.sales.vo.Transport;
import com.yimayhd.erpcenter.dal.sales.sales.dao.GroupOrderTransportMapper;


public class GroupOrderTransportDalImpl implements GroupOrderTransportDal{

	@Autowired
	private GroupOrderTransportMapper groupOrderTransportMapper ;
	
	@Override
	public int deleteByPrimaryKey(Integer id) {
		return groupOrderTransportMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(GroupOrderTransport record) {
		return groupOrderTransportMapper.insert(record);
	}

	@Override
	public int insertSelective(GroupOrderTransport record) {
		return groupOrderTransportMapper.insertSelective(record);
	}

	@Override
	public GroupOrderTransport selectByPrimaryKey(Integer id) {
		return groupOrderTransportMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(GroupOrderTransport record) {
		return groupOrderTransportMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(GroupOrderTransport record) {
		return groupOrderTransportMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<GroupOrderTransport> selectByOrderId(Integer orderId) {
		
		return groupOrderTransportMapper.selectByOrderId(orderId);
	}

	@Override
	public List<GroupOrderTransport> selectByCustomerCertificateNum(
			String certificateNum,Integer groupId) {
		
		return groupOrderTransportMapper.selectByCustomerCertificateNum(certificateNum,groupId);
	}

	@Override
	public int saveAndEditSeatInCoach(Transport transport) {
		int i = 0 ;
		List<GroupOrderTransport> gots = transport.getTransportList() ;
		for (GroupOrderTransport got : gots) {
			if(got.getId()!=null){
				i = updateByPrimaryKeySelective(got) ;
			}else{
				got.setOrderId(transport.getOrderId());
				got.setCreateTime(new Date().getTime());
				i = insertSelective(got) ;
			}
		}
		return i;
	}

	@Override
	public List<GroupOrderTransport> selectByGroupId(Integer groupId) {
		return groupOrderTransportMapper.selectByGroupId(groupId);
	}

}
