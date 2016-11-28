package com.yimayhd.erpcenter.facade.basic.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.yimayhd.erpcenter.biz.basic.service.DicBiz;
import com.yimayhd.erpcenter.biz.basic.service.DicTypeBiz;
import com.yimayhd.erpcenter.dal.basic.po.DicInfo;
import com.yimayhd.erpcenter.dal.basic.po.DicTypeInfo;
import com.yimayhd.erpcenter.facade.basic.errorcode.BasicErrorCode;
import com.yimayhd.erpcenter.facade.basic.result.ResultSupport;
import com.yimayhd.erpcenter.facade.basic.service.DicFacade;

/**
 * @author hongfei.guo
 * @create 2016年10月25日 上午10:55:42
 */
public class DicFacadeImpl implements DicFacade {
	
	@Autowired
	private DicBiz dicBiz;
	
	@Autowired
	private DicTypeBiz dicTypeBiz;
	
	@Override
	public List<DicTypeInfo> dicTypeList(String pid, String name) {
		if(StringUtils.isNotBlank(name)){
			try {
				name = new String(name.getBytes("iso8859-1"),"utf-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		List<DicTypeInfo> dicTypeList = dicTypeBiz.getListByPidAndName(pid,name);
		return dicTypeList;
	}

	@Override
	public DicTypeInfo getDicTypeById(String pid) {
		DicTypeInfo info = dicTypeBiz.getById(pid);
		return info;
	}

	@Override
	public DicTypeInfo getDicTypeById(Integer pid) {
		DicTypeInfo info = dicTypeBiz.getById(pid);
		return info;
	}

	@Override
	public DicInfo getDicById(Integer id) {
		DicInfo info = dicBiz.getById(id);
		return info;
	}

	@Override
	public ResultSupport dicTypeUpdate(DicTypeInfo info) {
		
		ResultSupport result = new ResultSupport();
		try{
			dicTypeBiz.update(info);
		}catch(Exception ex){
			result.setErrorCode(BasicErrorCode.MODIFY_ERROR);
			ex.printStackTrace();
		}
		
		return result;
	}

	@Override
	public ResultSupport dicTypeAdd(DicTypeInfo info) {
		ResultSupport result = new ResultSupport();
		try{
			dicTypeBiz.add(info);
		}catch(Exception ex){
			result.setErrorCode(BasicErrorCode.MODIFY_ERROR);
			ex.printStackTrace();
		}
		return result;
	}

	@Override
	public ResultSupport dicTypeDelete(Integer id) {
		ResultSupport result = new ResultSupport();
		try{
			dicTypeBiz.delete(id);
		}catch(Exception ex){
			result.setErrorCode(BasicErrorCode.MODIFY_ERROR);
			ex.printStackTrace();
		}
		return result;
	}

	@Override
	public ResultSupport dicUpdate(DicInfo dicInfo) {
		ResultSupport result = new ResultSupport();
		try{
			dicBiz.update(dicInfo);
		}catch(Exception ex){
			result.setErrorCode(BasicErrorCode.MODIFY_ERROR);
			ex.printStackTrace();
		}
		return result;
	}

	@Override
	public ResultSupport dicAdd(DicInfo dicInfo) {
		ResultSupport result = new ResultSupport();
		try{
			dicBiz.add(dicInfo);
		}catch(Exception ex){
			result.setErrorCode(BasicErrorCode.MODIFY_ERROR);
			ex.printStackTrace();
		}
		return result;
	}

	@Override
	public ResultSupport dicDel(Integer id) {
		ResultSupport result = new ResultSupport();
		try{
			dicBiz.delDic(id);
		}catch(Exception ex){
			result.setErrorCode(BasicErrorCode.MODIFY_ERROR);
			ex.printStackTrace();
		}
		return result;
	}

	@Override
	public String getDicTypeJson() {
		return dicTypeBiz.getDicTypeJson();
	}

	@Override
	public List<DicInfo> getListByTypeIdAndName(String type, String name) {
		List<DicInfo> list = dicBiz.getListByTypeIdAndName(type, name);
		return list;
	}

	@Override
	public List<DicInfo> getListByTypeIdAndName(String type, int bizId, String name) {
		List<DicInfo> list = dicBiz.getListByTypeIdAndName(type, bizId, name);
		return list;
	}

	@Override
	public List<DicInfo> getListByTypeCode(String typeCode, Integer bizId) {
		return dicBiz.getListByTypeCode(typeCode, bizId);
	}
	
}
