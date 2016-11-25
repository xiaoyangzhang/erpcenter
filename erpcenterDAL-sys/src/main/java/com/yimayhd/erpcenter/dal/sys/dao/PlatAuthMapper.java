package com.yimayhd.erpcenter.dal.sys.dao;

import com.yihg.sys.po.PlatAuth;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PlatAuthMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(PlatAuth record);

	int insertSelective(PlatAuth record);

	PlatAuth selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(PlatAuth record);

	int updateByPrimaryKey(PlatAuth record);

	PlatAuth selectByBizIdAndOrgIdOrSupplierId(@Param("bizId") Integer bizId,
                                               @Param("orgId") Integer orgId, @Param("supplierId") Integer supplierId);

	List<PlatAuth> selectByBizIdAndOrgNotZero(@Param("bizId") Integer bizId);
}