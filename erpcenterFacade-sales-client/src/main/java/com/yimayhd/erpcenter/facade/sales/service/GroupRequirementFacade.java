package com.yimayhd.erpcenter.facade.sales.service;

import com.yimayhd.erpcenter.facade.sales.query.grouprequirement.SaveGroupRequirementDTO;
import com.yimayhd.erpcenter.facade.sales.query.grouprequirement.ToRequirementListDTO;
import com.yimayhd.erpcenter.facade.sales.result.BaseStateResult;
import com.yimayhd.erpcenter.facade.sales.result.grouprequirement.EditGroupRequirementResult;
import com.yimayhd.erpcenter.facade.sales.result.grouprequirement.ToRequirementListResult;

/**
 * 
 * @author gaotingping
 * 2016年10月27日
 */
public interface GroupRequirementFacade {
	
	//跳转到计调汇总
	public ToRequirementListResult toRequirementList(ToRequirementListDTO toRequirementListDTO);
	
	//新增计调需求信息
	public BaseStateResult saveGroupRequirement(SaveGroupRequirementDTO saveGroupRequirementDTO);
	
	//修改计调需求信息回显数据
	public EditGroupRequirementResult editGroupRequirement(Integer id);
	
	//保存计调需求修改数据
	public BaseStateResult updateGroupRequirement(SaveGroupRequirementDTO saveGroupRequirementDTO);
	
	//删除计调需求信息
	public BaseStateResult deleteGroupRequirementById(Integer id);

	

	
}
