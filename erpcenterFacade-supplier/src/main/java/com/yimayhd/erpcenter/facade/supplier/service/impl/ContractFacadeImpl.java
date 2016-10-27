package com.yimayhd.erpcenter.facade.supplier.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.WebUtils;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.biz.basic.service.DicBiz;
import com.yimayhd.erpcenter.biz.basic.service.RegionBiz;
import com.yimayhd.erpcenter.dal.basic.constant.BasicConstants;
import com.yimayhd.erpcenter.dal.basic.po.DicInfo;
import com.yimayhd.erpcenter.dal.basic.po.RegionInfo;
import com.yimayhd.erpcenter.facade.supplier.errorcode.SupplierErrorCode;
import com.yimayhd.erpcenter.facade.supplier.query.SupplierContractVoDTO;
import com.yimayhd.erpcenter.facade.supplier.result.ContractSupplierListResult;
import com.yimayhd.erpcenter.facade.supplier.result.NewContractPagaResult;
import com.yimayhd.erpcenter.facade.supplier.result.WebResult;
import com.yimayhd.erpcenter.facade.supplier.service.ContractFacade;
import com.yimayhd.erpresource.biz.service.BizSupplierRelationBiz;
import com.yimayhd.erpresource.biz.service.ContractBiz;
import com.yimayhd.erpresource.biz.service.SupplierBiz;
import com.yimayhd.erpresource.biz.service.SupplierItemBiz;
import com.yimayhd.erpresource.dal.constants.Constants;
import com.yimayhd.erpresource.dal.po.BizSupplierRelation;
import com.yimayhd.erpresource.dal.po.SupplierContract;
import com.yimayhd.erpresource.dal.po.SupplierContractPrice;
import com.yimayhd.erpresource.dal.po.SupplierInfo;
import com.yimayhd.erpresource.dal.po.SupplierItem;
import com.yimayhd.erpresource.dal.vo.SupplierContractVo;
/**
 * 
 * @author liyong
 * 2016年10月26日 
 */
public class ContractFacadeImpl implements ContractFacade{
	
	private static final Logger log = LoggerFactory
			.getLogger(ContractFacadeImpl.class);

    @Autowired
    private DicBiz dicBiz;

    @Autowired
    private ContractBiz contractBiz;

    @Autowired
    private RegionBiz regionBiz;
    @Autowired
    private BizSupplierRelationBiz bizSupplierRelationBiz;

    @Autowired
    private SupplierBiz supplierBiz;
    @Autowired
    private SupplierItemBiz supplierItemBiz;
    
    @Override
    public NewContractPagaResult newContractPage(Integer bizId,String supplierId){
    	
    	NewContractPagaResult webResult = new NewContractPagaResult();
		try{
			 SupplierContractVo supplierContractVo = contractBiz.findContract(bizId, Integer.valueOf(supplierId), null);
				       
		     if(supplierContractVo.getSupplierInfo().getSupplierType().equals(Constants.SCENICSPOT)||supplierContractVo.getSupplierInfo().getSupplierType().equals(Constants.SHOPPING)){
		     	List<SupplierItem> dictTypeList = supplierItemBiz.findSupplierItemBySupplierId(Integer.valueOf(supplierId));
		     	webResult.setDictTypeList(dictTypeList);
		     	
		     }else if(supplierContractVo.getSupplierInfo().getSupplierType().equals(Constants.LOCALTRAVEL)){
		      	List<DicInfo> dictTypeList = dicBiz.getListByTypeCode(BasicConstants.XMFY_DJXM,bizId);
		      	webResult.setDicInfoList(dictTypeList);
		     }else{
		     	List<DicInfo> dictTypeList = dicBiz.getListByTypeCode(Constants.dictTypeMap.get(supplierContractVo.getSupplierInfo().getSupplierType()));
		     	webResult.setDicInfoList(dictTypeList);
			 }
		
		     //车队二级类型
		     if(Constants.FLEET.equals(supplierContractVo.getSupplierInfo().getSupplierType())){
		         List<DicInfo> dictSecLevelTypeList = dicBiz.getListByTypeCode(Constants.FLEET_LINE_BRAND_TYPE_CODE,bizId);
		         webResult.setDictSecLevelTypeList(dictSecLevelTypeList);
		     }
		     webResult.setSupplierContractVo(supplierContractVo);
		     List<DicInfo> cashTypes = dicBiz.getListByTypeCode(BasicConstants.GYXX_JSFS, bizId);
		     webResult.setCashTypes(cashTypes);
		}catch (Exception e) {
			log.error(e.getMessage());
			webResult.setErrorCode(SupplierErrorCode.SYSTEM_ERROR);
		}
		return webResult;
       
    }
    @Override
    public NewContractPagaResult editContractPage(Integer bizId ,String supplierId,String contractId){
    	
    	NewContractPagaResult webResult = new NewContractPagaResult();
		try{
			 SupplierContractVo supplierContractVo = contractBiz.findContract(bizId, Integer.valueOf(supplierId), Integer.valueOf(contractId));
				       
		     if(supplierContractVo.getSupplierInfo().getSupplierType().equals(Constants.SCENICSPOT)||supplierContractVo.getSupplierInfo().getSupplierType().equals(Constants.SHOPPING)){
		     	List<SupplierItem> dictTypeList = supplierItemBiz.findSupplierItemBySupplierId(Integer.valueOf(supplierId));
		     	webResult.setDictTypeList(dictTypeList);
		     	
		     }else if(supplierContractVo.getSupplierInfo().getSupplierType().equals(Constants.LOCALTRAVEL)){
		      	List<DicInfo> dictTypeList = dicBiz.getListByTypeCode(BasicConstants.XMFY_DJXM,bizId);
		      	webResult.setDicInfoList(dictTypeList);
		     }else{
		     	List<DicInfo> dictTypeList = dicBiz.getListByTypeCode(Constants.dictTypeMap.get(supplierContractVo.getSupplierInfo().getSupplierType()));
		     	webResult.setDicInfoList(dictTypeList);
			 }
		
		     //酒店两种字典类型
	        if(Constants.HOTEL.equals(supplierContractVo.getSupplierInfo().getSupplierType())){
	            List<DicInfo> dictType2List = dicBiz.getListByTypeCode(Constants.dictType2Map.get(supplierContractVo.getSupplierInfo().getSupplierType()));
	            webResult.setDictType2List(dictType2List);
	        }
		     //车队二级类型
		     if(Constants.FLEET.equals(supplierContractVo.getSupplierInfo().getSupplierType())){
		         List<DicInfo> dictSecLevelTypeList = dicBiz.getListByTypeCode(Constants.FLEET_LINE_BRAND_TYPE_CODE,bizId);
		         webResult.setDictSecLevelTypeList(dictSecLevelTypeList);
		     }
		     webResult.setSupplierContractVo(supplierContractVo);
		     List<DicInfo> cashTypes = dicBiz.getListByTypeCode(BasicConstants.GYXX_JSFS, bizId);
		     webResult.setCashTypes(cashTypes);
		}catch (Exception e) {
			log.error(e.getMessage());
			webResult.setErrorCode(SupplierErrorCode.SYSTEM_ERROR);
		}
		return webResult;
    }
    @Override
    public NewContractPagaResult viewContractPage(Integer bizId ,String supplierId,String contractId){
    	
    	NewContractPagaResult webResult = new NewContractPagaResult();
		try{
			 SupplierContractVo supplierContractVo = contractBiz.findContract(bizId, Integer.valueOf(supplierId), Integer.valueOf(contractId));
				       
		     if(supplierContractVo.getSupplierInfo().getSupplierType().equals(Constants.SCENICSPOT)||supplierContractVo.getSupplierInfo().getSupplierType().equals(Constants.SHOPPING)){
		     	List<SupplierItem> dictTypeList = supplierItemBiz.findSupplierItemBySupplierId(Integer.valueOf(supplierId));
		     	webResult.setDictTypeList(dictTypeList);
		     	
		     }else{
		     	List<DicInfo> dictTypeList = dicBiz.getListByTypeCode(Constants.dictTypeMap.get(supplierContractVo.getSupplierInfo().getSupplierType()));
		     	webResult.setDicInfoList(dictTypeList);
			 }
		
		     //车队二级类型
		     if(Constants.FLEET.equals(supplierContractVo.getSupplierInfo().getSupplierType())){
		         List<DicInfo> dictSecLevelTypeList = dicBiz.getListByTypeCode(Constants.FLEET_LINE_BRAND_TYPE_CODE,bizId);
		         webResult.setDictSecLevelTypeList(dictSecLevelTypeList);
		     }
		     webResult.setSupplierContractVo(supplierContractVo);
		     List<DicInfo> cashTypes = dicBiz.getListByTypeCode(BasicConstants.GYXX_JSFS, bizId);
		     webResult.setCashTypes(cashTypes);
		}catch (Exception e) {
			log.error(e.getMessage());
			webResult.setErrorCode(SupplierErrorCode.SYSTEM_ERROR);
		}
		return webResult;
    }
    @Override
    public NewContractPagaResult newFleetContractPage(Integer bizId,Integer supplierId){
    	
    	NewContractPagaResult webResult = new NewContractPagaResult();
		try{
			SupplierContractVo supplierContractVo = contractBiz.findFleetContract(bizId, null);

	        List<DicInfo> dictTypeList = dicBiz.getListByTypeCode(Constants.dictTypeMap.get(supplierContractVo.getSupplierInfo().getSupplierType()));
	        webResult.setDicInfoList(dictTypeList);

	        //车队二级类型
	        if(Constants.FLEET.equals(supplierContractVo.getSupplierInfo().getSupplierType())){
	            List<DicInfo> dictSecLevelTypeList = dicBiz.getListByTypeCode(Constants.FLEET_LINE_BRAND_TYPE_CODE,bizId);
	            webResult.setDictSecLevelTypeList(dictSecLevelTypeList);
	        }
	        webResult.setSupplierContractVo(supplierContractVo);
	        List<DicInfo> cashTypes = dicBiz.getListByTypeCode(BasicConstants.GYXX_JSFS, bizId);
	        webResult.setCashTypes(cashTypes);
		}catch (Exception e) {
			log.error(e.getMessage());
			webResult.setErrorCode(SupplierErrorCode.SYSTEM_ERROR);
		}
		return webResult;
    }
    @Override
    public NewContractPagaResult editFleetContractPage(Integer bizId,String contractId){
    	

    	
    	NewContractPagaResult webResult = new NewContractPagaResult();
		try{
			SupplierContractVo supplierContractVo = contractBiz.findFleetContract(bizId, Integer.valueOf(contractId));
				       
		     if(supplierContractVo.getSupplierInfo().getSupplierType().equals(Constants.SCENICSPOT)||supplierContractVo.getSupplierInfo().getSupplierType().equals(Constants.SHOPPING)){
		    	 List<SupplierItem> dictTypeList = supplierItemBiz.findSupplierItemBySupplierId(supplierContractVo.getSupplierInfo().getId());
		     	webResult.setDictTypeList(dictTypeList);
		     	
		     }else{
		     	List<DicInfo> dictTypeList = dicBiz.getListByTypeCode(Constants.dictTypeMap.get(supplierContractVo.getSupplierInfo().getSupplierType()));
		     	webResult.setDicInfoList(dictTypeList);
			 }
		     //酒店两种字典类型
	        if(Constants.HOTEL.equals(supplierContractVo.getSupplierInfo().getSupplierType())){
	            List<DicInfo> dictType2List = dicBiz.getListByTypeCode(Constants.dictType2Map.get(supplierContractVo.getSupplierInfo().getSupplierType()));
	            webResult.setDictType2List(dictType2List);
	        }
		     //车队二级类型
		     if(Constants.FLEET.equals(supplierContractVo.getSupplierInfo().getSupplierType())){
		         List<DicInfo> dictSecLevelTypeList = dicBiz.getListByTypeCode(Constants.FLEET_LINE_BRAND_TYPE_CODE,bizId);
		         webResult.setDictSecLevelTypeList(dictSecLevelTypeList);
		     }
		     webResult.setSupplierContractVo(supplierContractVo);
		     List<DicInfo> cashTypes = dicBiz.getListByTypeCode(BasicConstants.GYXX_JSFS, bizId);
		     webResult.setCashTypes(cashTypes);
		}catch (Exception e) {
			log.error(e.getMessage());
			webResult.setErrorCode(SupplierErrorCode.SYSTEM_ERROR);
		}
		return webResult;
    }
    @Override
    public NewContractPagaResult viewFleetContractPage(Integer bizId,String contractId){
    	
    	NewContractPagaResult webResult = new NewContractPagaResult();
		try{
			SupplierContractVo supplierContractVo = contractBiz.findFleetContract(bizId, Integer.valueOf(contractId));
			  List<DicInfo> dictTypeList = dicBiz.getListByTypeCode(Constants.dictTypeMap.get(supplierContractVo.getSupplierInfo().getSupplierType()));
			  webResult.setDicInfoList(dictTypeList);      
		     //酒店两种字典类型
	        if(Constants.HOTEL.equals(supplierContractVo.getSupplierInfo().getSupplierType())){
	            List<DicInfo> dictType2List = dicBiz.getListByTypeCode(Constants.dictType2Map.get(supplierContractVo.getSupplierInfo().getSupplierType()));
	            webResult.setDictType2List(dictType2List);
	        }
		     //车队二级类型
		     if(Constants.FLEET.equals(supplierContractVo.getSupplierInfo().getSupplierType())){
		         List<DicInfo> dictSecLevelTypeList = dicBiz.getListByTypeCode(Constants.FLEET_LINE_BRAND_TYPE_CODE,bizId);
		         webResult.setDictSecLevelTypeList(dictSecLevelTypeList);
		     }
		     webResult.setSupplierContractVo(supplierContractVo);
		     List<DicInfo> cashTypes = dicBiz.getListByTypeCode(BasicConstants.GYXX_JSFS, bizId);
		     webResult.setCashTypes(cashTypes);
		}catch (Exception e) {
			log.error(e.getMessage());
			webResult.setErrorCode(SupplierErrorCode.SYSTEM_ERROR);
		}
		return webResult;
    }



    @Override
    public ContractSupplierListResult contractSupplierList(Integer bizId,PageBean<SupplierInfo> pageBean,String provinceId,String cityId){

    	ContractSupplierListResult webResult = new ContractSupplierListResult();
		try{
			pageBean = contractBiz.findSupplierInfos(pageBean, bizId);
	        webResult.setPageBean(pageBean);
	        List<RegionInfo> allProvince = regionBiz.getAllProvince();
	        webResult.setAllProvince(allProvince);
	        List<RegionInfo> cityList = regionBiz.getRegionById(provinceId);
	        webResult.setCityList(cityList);
	        List<RegionInfo> areaList = regionBiz.getRegionById(cityId);
	        webResult.setAreaList(areaList);
		}catch (Exception e) {
			log.error(e.getMessage());
			webResult.setErrorCode(SupplierErrorCode.SYSTEM_ERROR);
		}
		return webResult;

        
    }

    @Override
    public WebResult<PageBean> contractList( String supplierId,PageBean<SupplierContract> pageBean,Integer bizId){

    	WebResult<PageBean> webResult = new WebResult<PageBean>();
		try{
	        BizSupplierRelation bizSupplierRelation = bizSupplierRelationBiz.getByBizIdAndSupplierId(bizId, Integer.valueOf(supplierId));
	        if(bizSupplierRelation != null){
	            pageBean = contractBiz.findContracts(pageBean, bizSupplierRelation.getId());
	        }
	        webResult.setValue(pageBean);
		}catch (Exception e) {
			log.error(e.getMessage());
			webResult.setErrorCode(SupplierErrorCode.SYSTEM_ERROR);
		}
		return webResult;
    	
        
    }

    @Override
    public WebResult<PageBean> fleetList(Integer bizId,PageBean<SupplierContract> pageBean,Integer supplierId){
    	WebResult<PageBean> webResult = new WebResult<PageBean>();
		try{
			pageBean = contractBiz.findContracts(pageBean, 0 - bizId);//车队shop_supplier_id=0
	        webResult.setValue(pageBean);
		}catch (Exception e) {
			log.error(e.getMessage());
			webResult.setErrorCode(SupplierErrorCode.SYSTEM_ERROR);
		}
		return webResult;
    }

    
    private void handleSupplierContractVo(SupplierContractVo supplierContractVo){
    	if (supplierContractVo.getPriceVoList()!=null && supplierContractVo.getPriceVoList().size()>0) {
			for (int i = 0; i < supplierContractVo.getPriceVoList().size(); i++) {
				SupplierContractPrice price = supplierContractVo
						.getPriceVoList().get(i).getSupplierContractPrice();
				if (price.getRebateMethod() != null
						&& price.getRebateMethod().equals(1)) {
					price.setRebateAmount(price.getRebateAmountPercent());
				}
			}
		}
		return ;
    }
    
    
    @Override
    public WebResult<SupplierContractVo> newContract(SupplierContractVoDTO supplierContractVoDTO){
    	
    	WebResult<SupplierContractVo> webResult = new WebResult<SupplierContractVo>();
		try{
			SupplierContractVo supplierContractVo = supplierContractVoDTO.getSupplierContractVo();
			this.handleSupplierContractVo(supplierContractVo);
	        supplierContractVo = contractBiz.saveContract(supplierContractVo);
	        webResult.setValue(supplierContractVo);
		}catch (Exception e) {
			log.error(e.getMessage());
			webResult.setErrorCode(SupplierErrorCode.SYSTEM_ERROR);
		}
		return webResult;
    	
    }

    @Override
    public WebResult<Boolean> editContract(SupplierContractVoDTO supplierContractVoDTO){
    	WebResult<Boolean> webResult = new WebResult<Boolean>();
		try{
			SupplierContractVo supplierContractVo = supplierContractVoDTO.getSupplierContractVo();
			this.handleSupplierContractVo(supplierContractVo);
	        contractBiz.updateContract(supplierContractVo);
	        webResult.setValue(true);
		}catch (Exception e) {
			log.error(e.getMessage());
			webResult.setErrorCode(SupplierErrorCode.SYSTEM_ERROR);
		}
		return webResult;
    }

    @Override
    public WebResult<Boolean> deleteContract(Integer bizId,Integer supplierId, Integer contractId){
    	
    	WebResult<Boolean> webResult = new WebResult<Boolean>();
		try{
	        boolean success = contractBiz.deleteContract(bizId, supplierId, contractId);
	        webResult.setValue(success);
		}catch (Exception e) {
			log.error(e.getMessage());
			webResult.setErrorCode(SupplierErrorCode.SYSTEM_ERROR);
		}
		return webResult;

    }
    @Override
    public WebResult<Boolean> copyContract(Integer supplierId, Integer contractId){
    	WebResult<Boolean> webResult = new WebResult<Boolean>();
		try{
			contractBiz.copySupplierContract(contractId);
	        webResult.setValue(true);
		}catch (Exception e) {
			log.error(e.getMessage());
			webResult.setErrorCode(SupplierErrorCode.SYSTEM_ERROR);
		}
		return webResult;
    	
    	
    }

    @Override
    public WebResult<Boolean> deleteFleetContract(Integer bizId,Integer contractId){
    	
    	WebResult<Boolean> webResult = new WebResult<Boolean>();
		try{
		    boolean success = contractBiz.deleteFleetContract(bizId, contractId);
	        webResult.setValue(success);
		}catch (Exception e) {
			log.error(e.getMessage());
			webResult.setErrorCode(SupplierErrorCode.SYSTEM_ERROR);
		}
		return webResult;

    }
}
