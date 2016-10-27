package com.yimayhd.erpcenter.facade.supplier.service;


import java.util.List;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.TourGroup;
import com.yimayhd.erpcenter.facade.supplier.query.SupplierGuestDTO;
import com.yimayhd.erpcenter.facade.supplier.result.GuestLabelEditResult;
import com.yimayhd.erpcenter.facade.supplier.result.GuestLabelListResult;
import com.yimayhd.erpcenter.facade.supplier.result.WebResult;

/**
 * 客人相关facade
 * @author liyong
 * 2016年10月26日 描述：
 */
public interface SupplierGuestFacade {
	
	/**
	 * 查询客人标签列表
	 * @author liyong
	 * 2016年10月26日 描述：
	 * @param bizId
	 * @return
	 */
	public GuestLabelListResult guestLabelList(Integer bizId);
	
	/**
	 * 删除客人标签
	 * @param id
	 * @return
	 */
	public WebResult<Boolean> deleteLabel(Integer id);
	
	/**
	 * 添加客人标签
	 * @author liyong
	 * @param bizId 
	 * @param name
	 * @return
	 */
	public WebResult<Boolean> addLabel(Integer bizId, String name);
	/**
	 * 客人列表
	 * @param request
	 * @param reponse
	 * @param model
	 * @return
	 */
	public GuestLabelListResult guestList(Integer bizId);
	/**
	 *  客人列表展示
	 * @author liyong
	 * 2016年10月26日 
	 * @param page
	 * @param pageSize
	 * @param chooseIds
	 * @param pageBean
	 * @return
	 */
	public WebResult<PageBean> guestList(Integer page, Integer pageSize,String chooseIds,PageBean pageBean);
	
	/**
	 * 删除客人
	 * @author liyong
	 * @param id
	 * @return
	 */
	public WebResult<Boolean> deleteGuest(Integer id);
	/**
	 * 
	 * 查询客人旅游记录
	 * @author liyong
	 * @param bizId
	 * @param idCard
	 * @return
	 */
	public WebResult<List<TourGroup>> selectTravelRecords(Integer bizId, String idCard);
	
	/**
	 * 添加客人
	 * @author liyong
	 * 2016年10月26日 描述：
	 * @param bizId
	 * @param id
	 * @return
	 */
	public GuestLabelListResult addGuest(Integer bizId, Integer id);
	/**
	 * 修改客人
	 * @author liyong
	 * 2016年10月26日 描述：
	 * @param bizId
	 * @param id
	 * @return
	 */
	public GuestLabelEditResult editGuest(Integer bizId, Integer id);
	/**
	 * 保存客人
	 * @author liyong
	 * 2016年10月26日 描述：
	 * @param guest
	 * @param choseIds
	 * @return
	 */
	public WebResult<Boolean> saveGuest(SupplierGuestDTO guestDTO,String choseIds,Integer bizId);

}
