package com.yimayhd.erpcenter.facade.supplier.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.util.WebUtils;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.biz.basic.service.DicBiz;
import com.yimayhd.erpcenter.dal.basic.po.DicInfo;
import com.yimayhd.erpcenter.facade.supplier.errorcode.SupplierErrorCode;
import com.yimayhd.erpcenter.facade.supplier.query.SupplierCarVODTO;
import com.yimayhd.erpcenter.facade.supplier.result.EditSupplierCarResult;
import com.yimayhd.erpcenter.facade.supplier.result.SupplierCarListResult;
import com.yimayhd.erpcenter.facade.supplier.result.WebResult;
import com.yimayhd.erpcenter.facade.supplier.service.SupplierCarFacade;
import com.yimayhd.erpresource.biz.service.SupplierCarBiz;
import com.yimayhd.erpresource.biz.service.SupplierImgBiz;
import com.yimayhd.erpresource.dal.constants.Constants;
import com.yimayhd.erpresource.dal.po.SupplierCar;
import com.yimayhd.erpresource.dal.vo.SupplierCarVO;


public class SupplierCarFacadeImpl implements SupplierCarFacade {
	private static final Logger log = LoggerFactory
			.getLogger(SupplierCarFacadeImpl.class);
	@Autowired
	private SupplierCarBiz supplierCarBiz;
	@Autowired
	private SupplierImgBiz supplierImgBiz;
	@Autowired
	private DicBiz dicBiz;

	@Override
	public SupplierCarListResult toSupplierCarList(Integer bizId,PageBean pageBean) {

    	
		SupplierCarListResult webResult = new SupplierCarListResult();
		try{
			PageBean page = supplierCarBiz.selectPrivateCarListPage(pageBean,bizId);
			List<SupplierCarVO> voList = new ArrayList<SupplierCarVO>();
			List<SupplierCar> result = page.getResult();
			if (result != null && result.size() > 0) {
				for (SupplierCar sc : result) {
					SupplierCarVO scv = new SupplierCarVO();
					scv.setSupplierCar(sc);
					scv.setImgList(supplierImgBiz.selectBySupplierCommentImgId(
							sc.getId(), 5));
					voList.add(scv);
				}
			}
			webResult.setPageBean(page);
			webResult.setVoList(voList);
			List<DicInfo> list = dicBiz
					.getListByTypeCode(Constants.FLEET_TYPE_CODE);
			webResult.setDicInfos(list);
		}catch (Exception e) {
			log.error(e.getMessage());
			webResult.setErrorCode(SupplierErrorCode.SYSTEM_ERROR);
		}
		return webResult;
	}

	@Override
	public SupplierCarListResult toAllSupplierCarList(PageBean pageBean) {
		
		SupplierCarListResult webResult = new SupplierCarListResult();
		try{
			PageBean page = supplierCarBiz.selectAllCarListPage(pageBean);
			List<SupplierCarVO> voList = new ArrayList<SupplierCarVO>();
			List<SupplierCar> result = page.getResult();
			if (result != null && result.size() > 0) {
				for (SupplierCar sc : result) {
					SupplierCarVO scv = new SupplierCarVO();
					scv.setSupplierCar(sc);
					scv.setImgList(supplierImgBiz.selectBySupplierCommentImgId(
							sc.getId(), 5));
					voList.add(scv);
				}
			}
			webResult.setPageBean(page);
			webResult.setVoList(voList);
			List<DicInfo> list = dicBiz
					.getListByTypeCode(Constants.FLEET_TYPE_CODE);
			webResult.setDicInfos(list);
		}catch (Exception e) {
			log.error(e.getMessage());
			webResult.setErrorCode(SupplierErrorCode.SYSTEM_ERROR);
		}
		return webResult;
	}

	@Override
	public WebResult<Boolean> delCarRelation(Integer bizId,Integer id) {
		
		WebResult<Boolean> webResult = new WebResult<Boolean>();
		try{
			supplierCarBiz.delSupplierCar(id, bizId);
			webResult.setValue(true);
		}catch (Exception e) {
			log.error(e.getMessage());
			webResult.setErrorCode(SupplierErrorCode.SYSTEM_ERROR);
		}
		return webResult;
		
	}

	@Override
	public WebResult<List<DicInfo>> toAddSupplierCar(String fileTypeCode) {
		WebResult<List<DicInfo>> webResult = new WebResult<List<DicInfo>>();
		try{
			List<DicInfo> list = dicBiz
					.getListByTypeCode(Constants.FLEET_TYPE_CODE);
			webResult.setValue(list);
		}catch (Exception e) {
			log.error(e.getMessage());
			webResult.setErrorCode(SupplierErrorCode.SYSTEM_ERROR);
		}
		return webResult;
	}

	@Override
	public WebResult<Integer> addSupplierCar(Integer bizId,SupplierCarVODTO supplierCarVODTO) {
		
		WebResult<Integer> webResult = new WebResult<Integer>();
		try{
			SupplierCarVO supplierCarVO = supplierCarVODTO.getSupplierCarVO();
			supplierCarVO.getSupplierCar().setTypeName(dicBiz.getById(supplierCarVO.getSupplierCar().getTypeId()+"").getValue());
			int id = supplierCarBiz.insertSupplierCar(supplierCarVO,
					bizId);
			webResult.setValue(id);
		}catch (Exception e) {
			log.error(e.getMessage());
			webResult.setErrorCode(SupplierErrorCode.SYSTEM_ERROR);
		}
		return webResult;
	}

	@Override
	public EditSupplierCarResult toEditSupplierCar(Integer id,String fileTypeCode) {
		
		EditSupplierCarResult webResult = new EditSupplierCarResult();
		try{
			SupplierCarVO supplierCarVO = supplierCarBiz.selectById(id);
			webResult.setSupplierCarVO(supplierCarVO);
			List<DicInfo> list = dicBiz
					.getListByTypeCode(Constants.FLEET_TYPE_CODE);
			webResult.setList(list);
		}catch (Exception e) {
			log.error(e.getMessage());
			webResult.setErrorCode(SupplierErrorCode.SYSTEM_ERROR);
		}
		return webResult;
		
	}

	@Override
	public WebResult<Boolean> editSupplierCar(SupplierCarVODTO supplierCarVODTO) {
		
		WebResult<Boolean> webResult = new WebResult<Boolean>();
		try{
			SupplierCarVO supplierCarVO = supplierCarVODTO.getSupplierCarVO();
			supplierCarVO.getSupplierCar().setTypeName(dicBiz.getById(supplierCarVO.getSupplierCar().getTypeId()+"").getValue());
			supplierCarBiz.updateSupplierCar(supplierCarVO);
			webResult.setValue(true);
		}catch (Exception e) {
			log.error(e.getMessage());
			webResult.setErrorCode(SupplierErrorCode.SYSTEM_ERROR);
		}
		return webResult;
		
	}

	@Override
	public WebResult<Boolean> addRelation(String ids,Integer bizId) {
		WebResult<Boolean> webResult = new WebResult<Boolean>();
		try{
			supplierCarBiz.addRelation(ids, bizId);
			webResult.setValue(true);
		}catch (Exception e) {
			log.error(e.getMessage());
			webResult.setErrorCode(SupplierErrorCode.SYSTEM_ERROR);
		}
		return webResult;
	}

}
