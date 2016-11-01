package com.yimayhd.erpcenter.facade.common.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.yimayhd.erpcenter.biz.basic.service.DicBiz;
import com.yimayhd.erpcenter.biz.basic.service.ImgSpaceBiz;
import com.yimayhd.erpcenter.biz.basic.service.RegionBiz;
import com.yimayhd.erpcenter.biz.sys.service.PlatformEmployeeBiz;
import com.yimayhd.erpcenter.biz.sys.service.PlatformOrgBiz;
import com.yimayhd.erpcenter.common.contants.BasicConstants;
import com.yimayhd.erpcenter.common.util.FileConstant;
import com.yimayhd.erpcenter.common.util.StaConstant;
import com.yimayhd.erpcenter.dal.basic.dto.TreeDto;
import com.yimayhd.erpcenter.dal.basic.po.ImgSpace;
import com.yimayhd.erpcenter.dal.basic.po.RegionInfo;

import org.erpcenterFacade.common.client.errorcode.ProductErrorCode;
import org.erpcenterFacade.common.client.query.BrandQueryDTO;
import org.erpcenterFacade.common.client.query.DepartmentTuneQueryDTO;
import org.erpcenterFacade.common.client.result.BrandQueryResult;
import org.erpcenterFacade.common.client.result.DepartmentTuneQueryResult;
import org.erpcenterFacade.common.client.result.RegionResult;
import org.erpcenterFacade.common.client.service.ProductCommonFacade;

/**
 * @ClassName: ${ClassName}
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @Auther xueshengdong
 * @Date 2016/10/18 19:55
 */

public class ProductCommonFacadeImpl implements ProductCommonFacade {
	private static final Logger LOGGER = LoggerFactory.getLogger("ProductFacadeCommonImpl");

    @Autowired
    private DicBiz dicBiz;
    @Autowired
    private PlatformOrgBiz platformOrgBiz;
    @Autowired
    private PlatformEmployeeBiz platformEmployeeBiz;
    @Autowired
    private ImgSpaceBiz imgSpaceBiz;
    @Autowired
    private RegionBiz regionBiz;
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
				String saleOperatorIds = salesOperatorIds.substring(0,salesOperatorIds.length() - 1);
				return saleOperatorIds;
			}
		}
		return operatorIds;
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

	@Override
	public List<Map<String, String>> orgUserTree(int bizId, String type) {
		if(StringUtils.isBlank(type)){
			type = "single";
		}
		return platformEmployeeBiz.getOrgUserTree(bizId,null,type);
	}

	@Override
	public List<Map<String, String>> queryOrgUserTree(int bizId, String name,
			String type) {
		if(StringUtils.isBlank(type)){
			type = "single";
		}
		return platformEmployeeBiz.getOrgUserTreeFuzzy(bizId, name, type);
	}

	/* (non-Javadoc)
	 * <p>Title: queryProvinces</p> 
	 * <p>Description: </p> 
	 * @return 
	 * @see org.erpcenterFacade.common.client.service.ProductCommonFacade#queryProvinces()
	 */
	@Override
	public RegionResult queryProvinces() {
		RegionResult result = new RegionResult();
		
		try {
			List<RegionInfo> allProvince = regionBiz.getAllProvince();
			result.setRegionList(allProvince);
		} catch (Exception e) {
			LOGGER.error("regionBiz.getAllProvince error:{}",e);
			result.setErrorCode(ProductErrorCode.SYSTEM_ERROR);
		}
		return result;
	}

	@Override
	public ImgSpace toUploadPage(int parentId) {
		ImgSpace imgSpace = null;
		try {
			imgSpace = imgSpaceBiz.findImgSpaceById(parentId);
		} catch (Exception e) {
			LOGGER.error("ProductCommonFacadeImpl.toUploadPage error:{}",e);
		}
		return imgSpace;
	}
}
