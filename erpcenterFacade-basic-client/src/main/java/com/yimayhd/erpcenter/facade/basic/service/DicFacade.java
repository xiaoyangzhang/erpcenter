package com.yimayhd.erpcenter.facade.basic.service;

import java.util.List;

import com.yimayhd.erpcenter.dal.basic.po.DicInfo;
import com.yimayhd.erpcenter.dal.basic.po.DicTypeInfo;
import com.yimayhd.erpcenter.facade.basic.result.ResultSupport;

/**
 * @author hongfei.guo
 * @create 2016年10月25日 上午10:55:42
 */
public interface DicFacade {
	
	List<DicTypeInfo> dicTypeList(String pid,String name);
	
	DicTypeInfo getDicTypeById(String pid);
	
	DicTypeInfo getDicTypeById(Integer pid);
	
	DicInfo getDicById(Integer id);
	
	ResultSupport dicTypeUpdate(DicTypeInfo info);
	
	ResultSupport dicTypeAdd(DicTypeInfo info);
	
	ResultSupport dicTypeDelete(Integer id);
	
	ResultSupport dicUpdate(DicInfo dicInfo);
	
	ResultSupport dicAdd(DicInfo dicInfo);
	
	ResultSupport dicDel(Integer id);
	
	String getDicTypeJson();
	
	List<DicInfo> getListByTypeIdAndName(String type, String name);
	
	List<DicInfo> getListByTypeIdAndName(String type, int bizId, String name);
	
	List<DicInfo> getListByTypeCode(String typeCode, Integer bizId);
	
	public List<DicInfo> getListByTypeCode(String typeCode);
}
