package com.yimayhd.erpcenter.facade.supplier.service;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.facade.supplier.query.SupplierContractVoDTO;
import com.yimayhd.erpcenter.facade.supplier.result.ContractPagaResult;
import com.yimayhd.erpcenter.facade.supplier.result.ContractSupplierListResult;
import com.yimayhd.erpcenter.facade.supplier.result.NewContractPagaResult;
import com.yimayhd.erpcenter.facade.supplier.result.WebResult;
import com.yimayhd.erpresource.dal.po.SupplierContract;
import com.yimayhd.erpresource.dal.po.SupplierInfo;
import com.yimayhd.erpresource.dal.vo.SupplierContractVo;

/**
 * 
 * @author liyong
 * 2016年10月25日 
 */
public interface ContractFacade {
    

    public NewContractPagaResult newContractPage(Integer bizId,String supplierId);

    public NewContractPagaResult editContractPage(Integer bizId ,String supplierId,String contractId);

    public NewContractPagaResult viewContractPage(Integer bizId ,String supplierId,String contractId);

    public NewContractPagaResult newFleetContractPage(Integer bizId,Integer supplierId);
       
    public NewContractPagaResult editFleetContractPage(Integer bizId,String contractId);

    public NewContractPagaResult viewFleetContractPage(Integer bizId,String contractId);

    public ContractSupplierListResult contractSupplierList(Integer bizId,PageBean<SupplierInfo> pageBean,String provinceId,String cityId);

    public ContractPagaResult contractList(String supplierId, PageBean<SupplierContract> pageBean, Integer bizId);

    public WebResult<PageBean> fleetList(Integer bizId,PageBean<SupplierContract> pageBean,Integer supplierId);

    public WebResult<SupplierContractVo> newContract(SupplierContractVoDTO supplierContractVoDTO);

    public WebResult<Boolean> editContract(SupplierContractVoDTO supplierContractVoDTO);


    public WebResult<Boolean> deleteContract(Integer bizId,Integer supplierId, Integer contractId);
    /**
     * 复制供应商协议
     */
    public WebResult<Boolean> copyContract(Integer supplierId, Integer contractId);

    public WebResult<Boolean> deleteFleetContract(Integer bizId,Integer contractId);

    public NewContractPagaResult newDeliveryContractPage(Integer bizId,String supplierId);

    public NewContractPagaResult editDeliveryContractPage(Integer bizId,String contractId);

    public void delPriceExtRow(Integer priceExtId);
}
