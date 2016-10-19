package com.yimayhd.erpcenter.facade.common.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.erpcenterFacade.common.client.service.ProductFacadeCommon;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.yimayhd.erpcenter.biz.basic.service.DicBiz;
import com.yimayhd.erpcenter.biz.basic.service.ImgSpaceBiz;
import com.yimayhd.erpcenter.biz.sys.service.PlatformEmployeeBiz;
import com.yimayhd.erpcenter.biz.sys.service.PlatformOrgBiz;
import com.yimayhd.erpcenter.common.contants.BasicConstants;
import com.yimayhd.erpcenter.dal.basic.dto.TreeDto;
import com.yimayhd.erpcenter.dal.basic.po.ImgSpace;
import com.yimayhd.erpcenter.dal.basic.utils.FileConstant;
import com.yimayhd.erpcenter.dal.basic.utils.StaConstant;
import org.apache.commons.lang3.StringUtils;
import org.erpcenterFacade.common.client.query.BrandQueryDTO;
import org.erpcenterFacade.common.client.query.DepartmentTuneQueryDTO;
import org.erpcenterFacade.common.client.result.BrandQueryResult;
import org.erpcenterFacade.common.client.result.DepartmentTuneQueryResult;
import org.erpcenterFacade.common.client.service.ProductCommonFacade;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

/**
 * @ClassName: ${ClassName}
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @Auther xueshengdong
 * @Date 2016/10/18 19:55
 */
public class ProductFacadeCommonImpl implements ProductFacadeCommon {
	private static final Logger LOGGER = LoggerFactory.getLogger("ProductFacadeCommonImpl");
public class ProductCommonFacadeImpl implements ProductCommonFacade {
    @Autowired
    private DicBiz dicBiz;
    @Autowired
    private PlatformOrgBiz platformOrgBiz;
    @Autowired
    private PlatformEmployeeBiz platformEmployeeBiz;
    @Autowired
    private ImgSpaceBiz imgSpaceBiz;

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

	@Override
	public List<TreeDto> findImgDirTree(int bizId) {
		List<TreeDto> dirDate = new ArrayList<TreeDto>();
		ImgSpace imgSpace = new ImgSpace();
		try {
			imgSpace.setType(FileConstant.DIRECTORY_TYPE);
			imgSpace.setStatus(StaConstant.AVAILABLE_STATUS);
			imgSpace.setParentId(null);
			imgSpace.setSysId("0");
			imgSpace.setDealerId(bizId);
			// 加载用户下的所有目录信息
			dirDate = imgSpaceBiz.findImgDirTree(imgSpace);
		} catch (Exception e) {
			LOGGER.error("ProductFacadeCommon.findImgDirTree error! param:imgSpace={}, error:{}",JSON.toJSONString(imgSpace),e);
		}
		return dirDate;
	}

	@Override
	public List<ImgSpace> imgList(int bizId, int imgId, String name,
			String sortFileds, String order) {
		ImgSpace imgSpace = new ImgSpace();		
		if(!StringUtils.isEmpty(name)){
			imgSpace.setImgName(name);
		}else{
			imgSpace.setParentId(imgId);			
		}
		imgSpace.setSysId("0");
		imgSpace.setDealerId(bizId);
		imgSpace.setType(FileConstant.IMAGE_TYPE);
		imgSpace.setStatus(StaConstant.AVAILABLE_STATUS);
		//显示图片
		List<ImgSpace> list = new ArrayList<ImgSpace>();
		
		try {
			list = imgSpaceBiz.getImgList(imgSpace,sortFileds,order);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
