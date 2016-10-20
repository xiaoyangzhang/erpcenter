package com.yimayhd.erpcenter.biz.basic.service;

import java.util.List;

import com.yimayhd.erpcenter.dal.basic.po.DicInfo;


public interface DicBiz {
	List<DicInfo> getListByTypeId(String type);

	DicInfo getById(int id);

	void update(DicInfo dicInfo);

	void add(DicInfo dicInfo);

	void delDic(String dicId);
	
	List<DicInfo> getListByTypeCode(String typeCode);
	
	List<DicInfo> getListByTypeCode(String typeCode,Integer bizId);
	
	/**
	 * 得到佣金项目类型树状字符串
	 * @param typeCode
	 * @param bizId
	 * @return
	 */
	String getCommProjectTypeTreeJsonStr(String typeCode,Integer bizId);

	List<DicInfo> getListByTypeIdAndName(String type ,String name);
	
	List<DicInfo> getListByTypeIdAndName(String type,Integer bizId,String name);
	
	/**
	 * 根据字典类型编码和字典编码唯一定位到一个字典信息
	 * @param typeCode
	 * @param dicCode
	 * @return
	 */
	DicInfo getDicInfoByTypeCodeAndDicCode(String typeCode,String dicCode);
	
	/**
	 * 根据商家id，字典类型编码和字典编码唯一定位到一个字典信息
	 * @param typeCode
	 * @param dicCode
	 * @return
	 */
	DicInfo getDicInfoByTypeCodeAndDicCode(Integer bizId,String typeCode,String dicCode);
	
}
