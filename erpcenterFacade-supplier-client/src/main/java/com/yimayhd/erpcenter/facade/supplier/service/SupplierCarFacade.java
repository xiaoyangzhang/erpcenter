package com.yimayhd.erpcenter.facade.supplier.service;

import java.util.List;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.dal.basic.po.DicInfo;
import com.yimayhd.erpcenter.facade.supplier.query.SupplierCarVODTO;
import com.yimayhd.erpcenter.facade.supplier.result.EditSupplierCarResult;
import com.yimayhd.erpcenter.facade.supplier.result.SupplierCarListResult;
import com.yimayhd.erpcenter.facade.supplier.result.WebResult;


public interface SupplierCarFacade {

	public SupplierCarListResult toSupplierCarList(Integer bizId,PageBean pageBean);

	public SupplierCarListResult toAllSupplierCarList(PageBean pageBean) ;

	public WebResult<Boolean> delCarRelation(Integer bizId,Integer id);


	public WebResult<List<DicInfo>> toAddSupplierCar(String fileTypeCode);

	public WebResult<Integer> addSupplierCar(Integer bizId,SupplierCarVODTO supplierCarVODTO);

	public EditSupplierCarResult toEditSupplierCar(Integer id,String fileTypeCode);

	public WebResult<Boolean> editSupplierCar(SupplierCarVODTO supplierCarVODTO) ;

	public WebResult<Boolean> addRelation(String ids,Integer bizId) ;

}
