package com.yimayhd.erpcenter.facade.sales.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.yimayhd.erpcenter.biz.basic.service.DicBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.GroupRequirementBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.TourGroupBiz;
import com.yimayhd.erpcenter.dal.basic.po.DicInfo;
import com.yimayhd.erpcenter.dal.sales.client.sales.constants.Constants;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupRequirement;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.TourGroup;
import com.yimayhd.erpcenter.facade.sales.query.grouprequirement.SaveGroupRequirementDTO;
import com.yimayhd.erpcenter.facade.sales.query.grouprequirement.ToRequirementListDTO;
import com.yimayhd.erpcenter.facade.sales.result.BaseStateResult;
import com.yimayhd.erpcenter.facade.sales.result.grouprequirement.EditGroupRequirementResult;
import com.yimayhd.erpcenter.facade.sales.result.grouprequirement.ToRequirementListResult;
import com.yimayhd.erpcenter.facade.sales.service.GroupRequirementFacade;

public class GroupRequirementFacadeImpl implements GroupRequirementFacade{
	
	@Autowired
	private GroupRequirementBiz groupRequirementService;
	
	@Autowired
	private TourGroupBiz tourGroupService;
	
	@Autowired
	private DicBiz dicService;
	

	public ToRequirementListResult toRequirementList(ToRequirementListDTO toRequirementListDTO){
		
		Integer groupId=toRequirementListDTO.getGroupId();
		
		List<GroupRequirement> hotelList = groupRequirementService.selectByGroupIdAndType(groupId, Constants.HOTEL);
		List<GroupRequirement> airList = groupRequirementService.selectByGroupIdAndType(groupId, Constants.AIRTICKETAGENT);
		List<GroupRequirement> trainList = groupRequirementService.selectByGroupIdAndType(groupId, Constants.TRAINTICKETAGENT);
		List<GroupRequirement> fleetList = groupRequirementService.selectByGroupIdAndType(groupId, Constants.FLEET);
		List<GroupRequirement> guideList = groupRequirementService.selectByGroupIdAndType(groupId, Constants.GUIDE);
		List<GroupRequirement> restaurantList = groupRequirementService.selectByGroupIdAndType(groupId, Constants.RESTAURANT);
		if(hotelList!=null && hotelList.size()>0){
			for (GroupRequirement hotel : hotelList) {
				//FIXME 待修复
				//hotel.setHotelLevelName(dicService.getById(hotel.getHotelLevel()+"").getValue());
			}
		}
		
		TourGroup tourGroup = tourGroupService.selectByPrimaryKey(groupId);
		List<DicInfo> cdcxList = dicService.getListByTypeCode(Constants.FLEET_TYPE_CODE);
		
		ToRequirementListResult result=new ToRequirementListResult();
		result.setAirList(airList);
		result.setCdcxList(cdcxList);
		result.setFleetList(fleetList);
		result.setGuideList(guideList);
		result.setHotelList(hotelList);
		result.setRestaurantList(restaurantList);
		result.setTourGroup(tourGroup);
		result.setTrainList(trainList);
		
		return result;
	}
	
	public BaseStateResult saveGroupRequirement(SaveGroupRequirementDTO saveGroupRequirementDTO){
		groupRequirementService.insertSelective(saveGroupRequirementDTO.getGroupRequirement());
		return new BaseStateResult(true,null) ;
	}
	
	public EditGroupRequirementResult editGroupRequirement(Integer id){
		GroupRequirement groupRequirement = groupRequirementService.selectByPrimaryKey(id) ;
		EditGroupRequirementResult result=new EditGroupRequirementResult();
		result.setGroupRequirement(groupRequirement);
		return result ;
	}
	
	public BaseStateResult updateGroupRequirement(SaveGroupRequirementDTO saveGroupRequirementDTO){
		groupRequirementService.updateByPrimaryKeySelective(saveGroupRequirementDTO.getGroupRequirement()) ;
		return new BaseStateResult(true,null) ;
	}
	
	public BaseStateResult deleteGroupRequirementById(Integer id){
		groupRequirementService.deleteByPrimaryKey(id) ;
		return new BaseStateResult(true,null);
	}
}
