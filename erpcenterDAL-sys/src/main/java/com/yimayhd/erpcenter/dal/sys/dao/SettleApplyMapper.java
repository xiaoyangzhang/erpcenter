package com.yimayhd.erpcenter.dal.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.dal.sys.po.SettleApply;

public interface SettleApplyMapper {
	int deleteById(Integer id);

	int insert(SettleApply record);

	int insertSelective(SettleApply record);

	SettleApply selectById(Integer id);

	int updateByIdSelective(SettleApply record);

	int updateById(SettleApply record);

	int getCountByName(String name);

	int updateServiceNoById(@Param("serviceNo") String serviceNo,
			@Param("id") Integer id);

	List<SettleApply> getApplyListPage(PageBean pageBean);

}