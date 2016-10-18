package com.yimayhd.erpcenter.dal.basic.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.yimayhd.erpcenter.dal.basic.constant.BasicConstants;
import com.yimayhd.erpcenter.dal.basic.dao.DicTypeMapper;
import com.yimayhd.erpcenter.dal.basic.po.DicTypeInfo;
import com.yimayhd.erpcenter.dal.basic.service.DicTypeDal;


public class DicTypeDalImpl implements DicTypeDal {
	
	@Autowired
	private DicTypeMapper dicTypeMapper;

	@Override
	public void add(DicTypeInfo dicTypeInfo){
		if(dicTypeInfo!=null){
			Integer pid = dicTypeInfo.getPid();
			if(pid == BasicConstants.ROOT_ID){
				dicTypeInfo.setLevel(1);
				dicTypeMapper.insert(dicTypeInfo);
				dicTypeInfo.setTreePath(dicTypeInfo.getId()+"");
				dicTypeMapper.update(dicTypeInfo);				
			}else{
				DicTypeInfo parent = dicTypeMapper.getById(pid);
				if(parent!=null){
					dicTypeInfo.setLevel(parent.getLevel()+1);
					dicTypeMapper.insert(dicTypeInfo);
					dicTypeInfo.setTreePath(parent.getTreePath()+","+dicTypeInfo.getId());
					dicTypeMapper.update(dicTypeInfo);	
				}
			}
		}
	}

	@Override
	public void update(DicTypeInfo dicTypeInfo) {
		dicTypeMapper.update(dicTypeInfo);		
	}

	@Override
	public void delete(Integer id){
		DicTypeInfo info = new DicTypeInfo();
		info.setPid(id);
		int cnt = dicTypeMapper.getCount(info);
		if(cnt==0){
			dicTypeMapper.delete(id);
		}
	}
	
	@Override
	public void delete(String id){
		Integer typeId = 0;
		if(StringUtils.isBlank(id)){
			return;
		}
		typeId = Integer.valueOf(id);
		DicTypeInfo info = new DicTypeInfo();
		info.setPid(typeId);
		int cnt = dicTypeMapper.getCount(info);
		if(cnt==0){
			dicTypeMapper.delete(typeId);
		}
	}

	@Override
	public DicTypeInfo getById(String id) {
		if(StringUtils.isBlank(id) || id.equals(BasicConstants.ROOT_ID.toString())){
			DicTypeInfo info = new DicTypeInfo();
			info.setId(BasicConstants.ROOT_ID);
			info.setValue(BasicConstants.ROOT_NAME);
			return info;
		}
		Integer typeId = Integer.valueOf(id);
		return dicTypeMapper.getById(typeId);
	}

	@Override
	public DicTypeInfo getById(Integer id) {
		if(id == null || id.equals(BasicConstants.ROOT_ID)){
			DicTypeInfo info = new DicTypeInfo();
			info.setId(BasicConstants.ROOT_ID);
			info.setValue(BasicConstants.ROOT_NAME);
			return info;
		}
		return dicTypeMapper.getById(id);
	}
	
	@Override
	public List<DicTypeInfo> getListByPid(Integer pid)  {
		if(pid == null){
			pid = BasicConstants.ROOT_ID;
		}
		return dicTypeMapper.getListByPid(pid);
	}

	@Override
	public String getDicTypeJson() {
		List<DicTypeInfo> list = dicTypeMapper.getAll();
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		if(list!=null && list.size()>0){
			for(DicTypeInfo info : list){
				sb.append("{");
				sb.append("id:\"");
				sb.append(info.getId());
				sb.append("\",");
				sb.append("pId:\"");
				sb.append(info.getPid());
				sb.append("\",");
				sb.append("name:\"");
				sb.append(info.getValue());
				sb.append("\"");
				if(info.getPid()==0){
					sb.append(",");
					sb.append("open:true");
				}
				sb.append("},");			
			}
			sb.deleteCharAt(sb.length()-1);
		}
		sb.append("]");
		return sb.toString();
	}

	@Override
	public List<DicTypeInfo> getListByPidAndName(
			String pid, String name) {
		Integer pId = 0;
		if(StringUtils.isNotBlank(pid)){
			pId = Integer.valueOf(pid);
		}else{
			pId = BasicConstants.ROOT_ID;
		}
		return dicTypeMapper.getListByPidAndName(pId,name);
	}

	@Override
	public List<DicTypeInfo> getAll() {
		return dicTypeMapper.getAll();
	}
	
}
