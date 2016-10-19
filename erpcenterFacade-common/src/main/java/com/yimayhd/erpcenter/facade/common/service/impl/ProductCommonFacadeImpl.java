package com.yimayhd.erpcenter.facade.common.service.impl;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.erpcenterFacade.common.client.query.LoadProductDepartmentDTO;
import org.erpcenterFacade.common.client.result.LoadProductDepartmentResult;
import org.erpcenterFacade.common.client.service.ProductCommonFacade;
import org.springframework.beans.factory.annotation.Autowired;

import com.yimayhd.erpcenter.biz.basic.service.DicBiz;
import com.yimayhd.erpcenter.biz.sys.service.PlatformEmployeeBiz;
import com.yimayhd.erpcenter.biz.sys.service.PlatformOrgBiz;
import com.yimayhd.erpcenter.common.contants.BasicConstants;
import org.erpcenterFacade.common.client.query.LoadProductDepartmentDTO;
import org.erpcenterFacade.common.client.result.LoadProductDepartmentResult;
import org.erpcenterFacade.common.client.service.ProductFacadeCommon;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @ClassName: ${ClassName}
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @Auther xueshengdong
 * @Date 2016/10/18 19:55
 */
public class ProductCommonFacadeImpl implements ProductCommonFacade {
    @Autowired
    private DicBiz dicBiz;
    @Autowired
    private PlatformOrgBiz platformOrgBiz;
    @Autowired
    private PlatformEmployeeBiz platformEmployeeBiz;

    /**
     * 部门 计调 查询
     *
     * @param departmentTuneQueryDTO
     * @return
     */
    @Override
    public DepartmentTuneQueryResult departmentTuneQuery(DepartmentTuneQueryDTO departmentTuneQueryDTO) {
        DepartmentTuneQueryResult departmentTuneQueryResult = new DepartmentTuneQueryResult();
        departmentTuneQueryResult.setOrgJsonStr(platformOrgBiz.getComponentOrgTreeJsonStr(departmentTuneQueryDTO.getBizId()));
        departmentTuneQueryResult.setOrgUserJsonStr(platformEmployeeBiz.getComponentOrgUserTreeJsonStr(departmentTuneQueryDTO.getBizId()));
        return departmentTuneQueryResult;
    }

    /**
     * 产品品牌 查询
     *
     * @param brandQueryDTO
     * @return
     */
    @Override
    public BrandQueryResult brandQuery(BrandQueryDTO brandQueryDTO) {
        BrandQueryResult brandQueryResult = new BrandQueryResult();
        brandQueryResult.setBrandList(dicBiz.getListByTypeCode(BasicConstants.CPXL_PP, brandQueryDTO.getBizId()));
        return brandQueryResult;
    }

	/* (non-Javadoc)
	 * <p>Title: setSaleOperatorIds</p> 
	 * <p>Description: </p> 
	 * @param operatorIds
	 * @param orgIds
	 * @return 
	 * @see org.erpcenterFacade.common.client.service.ProductFacadeCommon#setSaleOperatorIds(java.lang.String, java.lang.String)
	 */
	@Override
	public String setSaleOperatorIds(String operatorIds, String orgIds,int bizId) {
		String saleOperatorIds = "";
		if (StringUtils.isBlank(operatorIds) && StringUtils.isNotBlank(orgIds)) {
			Set<Integer> set = new HashSet<Integer>();
			String[] orgIdArr = orgIds.split(",");
			for (String orgIdStr : orgIdArr) {
				set.add(Integer.valueOf(orgIdStr));
			}
			set = platformEmployeeBiz.getUserIdListByOrgIdList(
					bizId, set);
			String salesOperatorIds = "";
			for (Integer usrId : set) {
				salesOperatorIds += usrId + ",";
			}
			if (!salesOperatorIds.equals("")) {
				saleOperatorIds = salesOperatorIds.substring(0,salesOperatorIds.length() - 1);
			}
		}
		return saleOperatorIds;
	}
}
