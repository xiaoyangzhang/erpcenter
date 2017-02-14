package com.yimayhd.erpcenter.facade.supplier.service;

import java.util.List;
import java.util.Map;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.dal.basic.po.DicInfo;
import com.yimayhd.erpcenter.facade.supplier.query.SupplierBankaccountDTO;
import com.yimayhd.erpcenter.facade.supplier.query.SupplierBillDTO;
import com.yimayhd.erpcenter.facade.supplier.query.SupplierContactManDTO;
import com.yimayhd.erpcenter.facade.supplier.query.SupplierGuideDTO;
import com.yimayhd.erpcenter.facade.supplier.query.SupplierInfoDTO;
import com.yimayhd.erpcenter.facade.supplier.result.AddSupplierResult;
import com.yimayhd.erpcenter.facade.supplier.result.BusinessInfoResult;
import com.yimayhd.erpcenter.facade.supplier.result.ContactManListResult;
import com.yimayhd.erpcenter.facade.supplier.result.EditSupplierResult;
import com.yimayhd.erpcenter.facade.supplier.result.FolderListResult;
import com.yimayhd.erpcenter.facade.supplier.result.GuideAddResult;
import com.yimayhd.erpcenter.facade.supplier.result.GuideListResult;
import com.yimayhd.erpcenter.facade.supplier.result.PictureListResult;
import com.yimayhd.erpcenter.facade.supplier.result.SuplierListResult;
import com.yimayhd.erpcenter.facade.supplier.result.WebResult;
import com.yimayhd.erpresource.dal.po.SupplierBankaccount;
import com.yimayhd.erpresource.dal.po.SupplierBill;
import com.yimayhd.erpresource.dal.po.SupplierContactMan;


/**
 * 供应商facade接口
 * @author liyong
 * 2016年10月25日 
 */
public interface SupplierFacade {
//	private static final Logger log = LoggerFactory
//			.getLogger(SupplierFacade.class);
	
	
	public WebResult<Boolean> deleteSupplierItem(Integer id);


	/**
	 * 跳转到添加供应商页面
	 *
	 * @return
	 */
	
	public AddSupplierResult toAddSupplier() ;

	/**
	 * 跳转到供应商编辑页面
	 * @author liyong
	 * 2016年10月25日
	 * @param id
	 * @param operType
	 * @return
	 */

	public EditSupplierResult toEditSupplier(Integer id,Integer operType); 


	
	/**
	 *  验证当前供应商类别下全称是否存在
	 * @author liyong
	 * 2016年10月25日 描述：
	 * @param supplierId
	 * @param supplierType
	 * @param nameFull
	 * @return
	 */
	public WebResult<String> verifyNameFull(Integer supplierId,
			Integer supplierType, String nameFull);

	/**
	 * 添加供应商
	 * @author liyong
	 * 2016年10月25日 描述：
	 * @param supplierInfoDTO
	 * @param items
	 * @return
	 */
	public WebResult<String> saveRest(SupplierInfoDTO supplierInfoDTO, String items,Integer bizId);

	/**
	 * 编辑供应商
	 *
	 * @param request
	 * @param reponse
	 * @param model
	 * @param supplierInfo
	 * @return
	 */
	public WebResult<Boolean> editRest(SupplierInfoDTO supplierInfoDTO, String items);

	/**
	 * 改变状态
	 * @author liyong
	 * 2016年10月25日 描述：
	 * @param supplierId
	 * @param state
	 * @return
	 */
	public WebResult<Boolean> changeState(Integer supplierId, Integer state) ;

	/**
	 *  删除供应商
	 * @author liyong
	 * 2016年10月25日 描述：
	 * @param bizId
	 * @param supplierId
	 * @return
	 */
	public WebResult<Boolean> delSupplier(Integer bizId, Integer supplierId); 

	
	

	public WebResult<Boolean> fixSupplierName(Integer supplierId, Integer supplierType, String supplierName);
	
	
	/**
	 * 跳转到银行、发票等其他信息页
	 *
	 * @param request
	 * @param reponse
	 * @param model
	 * @param supplierId
	 * @return
	 */
	public BusinessInfoResult toBusinessInfo(Integer supplierId);

	/**
	 * 添加银行帐号信息
	 * @author liyong
	 * 2016年10月25日 描述：
	 * @param supplierBankaccountDTO
	 * @return
	 */
	public WebResult<Boolean> addBankInfo(SupplierBankaccountDTO supplierBankaccountDTO);

	/**
	 * 查找所编辑的银行信息
	 *
	 * @param id
	 * @return
	 */
	public WebResult<SupplierBankaccount> getBankInfo(Integer id);

	/**
	 * 编辑银行账号信息
	 *
	 * @param supplierBankaccountDTO
	 * @return
	 */
	public WebResult<Boolean> editBankInfo(SupplierBankaccountDTO supplierBankaccountDTO);

	/**
	 * 删除银行帐号信息
	 *
	 * @param request
	 * @param reponse
	 * @param id
	 * @return
	 */
	public WebResult<Boolean> delBankInfo(Integer id);

	/**
	 * 添加发票信息
	 * @param supplierBill
	 */
	public WebResult<Boolean> addBillInfo(SupplierBillDTO supplierBillDTO);

	/**
	 * 设置为默认发票
	 *
	 * @param billId
	 * @param supplierId
	 * @return
	 */
	public WebResult<Boolean> setDefault(Integer billId, Integer supplierId);

	/**
	 * 查找所编辑的发票信息
	 *
	 * @param id
	 * @return
	 */
	public WebResult<SupplierBill> getBillInfo(Integer id);

	/**
	 * 编辑发票信息
	 *
	 * @param request
	 * @param reponse
	 * @param supplierBill
	 * @return
	 */
	public WebResult<Boolean> editBillInfo(SupplierBillDTO supplierBillDTO);

	/**
	 * 删除发票信息
	 *
	 * @param id
	 * @return
	 */
	public WebResult<Boolean> delBillInfo(Integer id);

	/**
	 * 跳转到联系人列表
	 *
	 * @param request
	 * @param reponse
	 * @param model
	 * @param id
	 * @return
	 */
	public ContactManListResult toContactList(Integer bizId,Integer id);

	/**
	 * 获取供应商下联系人信息，供列表查看用
	 *
	 * @param request
	 * @param reponse
	 * @param model
	 * @param id
	 * @return
	 */
	public WebResult<List<SupplierContactMan>> getContactManList(Integer bizId,Integer id);

	/**
	 * 导入私有联系人
	 *
	 * @param request
	 * @param reponse
	 * @param ids
	 * @param supplierId
	 * @return
	 */
	public WebResult<Boolean> addPrivateMan(Integer bizId, String ids, Integer supplierId);

	/**
	 * 增加联系人
	 *
	 * @param request
	 * @param reponse
	 * @param model
	 * @return
	 */
	public WebResult<Boolean> addContactMan(Integer bizId,SupplierContactManDTO supplierContactManDTO);

	/**
	 * 验证当前供应商联系人手机号码是否存在
	 *
	 * @param supplierId
	 * @param mobile
	 * @return
	 */
	public WebResult<String> verifyMobile(Integer manId, String mobile);

	/**
	 * 删除私有联系人信息
	 *
	 * @param request
	 * @param reponse
	 * @param supplierId
	 * @param manId
	 * @return
	 */
	public WebResult<Boolean> delContactMan(Integer bizId, Integer supplierId, Integer manId);

	/**
	 * 查找所编辑的联系人资料
	 *
	 * @param request
	 * @param reponse
	 * @param manId
	 * @return
	 */
	public WebResult<SupplierContactMan> getManInfo( Integer manId);

	/**
	 * 编辑联系人
	 *
	 * @param request
	 * @param reponse
	 * @param supplierContactMan
	 * @return
	 */
	public WebResult<Boolean> editContactMan(SupplierContactManDTO supplierContactManDTO);

	/**
	 * 供应商
	 * 
	 * @param request
	 * @param reponse
	 * @param model
	 * @return
	 */
//	public SuplierListResult toTravelagencyList(SupplierInfoDTO supplierInfoDTO);
//
//	/**
//	 * 餐厅
//	 * 
//	 * @param request
//	 * @param reponse
//	 * @param model
//	 * @return
//	 */
//	public SuplierListResult toRestaurant(SupplierInfoDTO supplierInfoDTO);
//
//	/**
//	 * 酒店
//	 * 
//	 * @param model
//	 * @return
//	 */
//	public SuplierListResult toHotelList(SupplierInfoDTO supplierInfoDTO);
//
//	/**
//	 * 车队
//	 * @param model
//	 * @return
//	 */
//	public SuplierListResult toFleetList(SupplierInfoDTO supplierInfoDTO);
//
//	/**
//	 * 景区
//	 * 
//	 * @param model
//	 * @return
//	 */
//	public SuplierListResult toScenicspotList(SupplierInfoDTO supplierInfoDTO);
//
//	/**
//	 * 购物
//	 * 
//	 * @param request
//	 * @param reponse
//	 * @param model
//	 * @return
//	 */
//	public SuplierListResult toShoppingList(SupplierInfoDTO supplierInfoDTO);
//
//	/**
//	 * 娱乐
//	 * 
//	 * @param request
//	 * @param reponse
//	 * @param model
//	 * @return
//	 */
//	public SuplierListResult toEntertainmentList(SupplierInfoDTO supplierInfoDTO);
//
//	/**
//	 * 机票代理
//	 * 
//	 * @param request
//	 * @param reponse
//	 * @param model
//	 * @return
//	 */
//	public SuplierListResult toAirticketagentList(SupplierInfoDTO supplierInfoDTO);
//
//	/**
//	 * 火车票代理
//	 * 
//	 * @param request
//	 * @param reponse
//	 * @param model
//	 * @return
//	 */
//	public SuplierListResult toTrainticketagentList(SupplierInfoDTO supplierInfoDTO);
//
//	/**
//	 * 高尔夫
//	 * 
//	 * @param request
//	 * @param reponse
//	 * @param model
//	 * @return
//	 */
//	public SuplierListResult toGolfList(SupplierInfoDTO supplierInfoDTO);
//
//	/**
//	 * 其他
//	 * 
//	 * @param request
//	 * @param reponse
//	 * @param model
//	 * @return
//	 */
//	public SuplierListResult toOtherList(SupplierInfoDTO supplierInfoDTO);
//
//	/**
//	 * 保险
//	 * @param model
//	 * @return
//	 */
//	public SuplierListResult toInsuranceList(SupplierInfoDTO supplierInfoDTO);
//
//	/**
//	 * 地接社
//	 * 
//	 * @param model
//	 * @return
//	 */
//	public SuplierListResult toLocalTravelList(SupplierInfoDTO supplierInfoDTO);

	public SuplierListResult toSuplierList(SupplierInfoDTO supplierInfoDTO,Integer bizId);

	/**
	 * 查找所有供应商
	 *
	 * @param supplierInfo
	 * @return
	 */
	public SuplierListResult toImpSupplierList(SupplierInfoDTO supplierInfoDTO,Integer bizId);

	/**
	 * 导入供应商
	 * @param ids
	 * @return
	 */
	public WebResult<Boolean> impSupplier(Integer bizId,String ids);
	
//	public GuideListResult guideList(SupplierGuideDTO guideDTO, Integer page, Integer pageSize);
//
//	public GuideListResult guideListQuery(SupplierGuideDTO guideDTO, Integer page, Integer pageSize);

	public GuideListResult loadGuideList(SupplierGuideDTO guideDTO, Integer page, Integer pageSize,Integer bizId);
	
	public GuideListResult loadMyGuideList(SupplierGuideDTO guideDTO, Integer page, Integer pageSize,Integer bizId);

	public GuideAddResult guideAdd();

	public GuideAddResult editGuide(Integer id);
	
	public GuideAddResult guideDetail(Integer id);

	public WebResult<Boolean> delGuide(Integer bizId, Integer id);

	public WebResult<Boolean> delPublicGuide(Integer id);

	public WebResult<Map<String,Object>> saveGuide(SupplierGuideDTO guideDTO,Integer bizId);

	public GuideListResult guideArchivesList(SupplierGuideDTO guideDTO, Integer page, Integer pageSize,Integer bizId);
	/**
	 * 根据typecode获取对相应的list
	 * @author liyong
	 * 2016年10月27日 描述：
	 * @param typecode
	 * @return
	 */
	public WebResult<List<DicInfo>> getListByTypeCode(String typecode);
	
//	public GuideListResult queryGuideArchivesList(SupplierGuideDTO guideDTO, Integer page, Integer pageSize);

	public GuideListResult impGuideArchivesList(SupplierGuideDTO guideDTO, Integer page, Integer pageSize,Integer bizId);

//	public GuideListResult queryImpGuideArchivesList(Integer bizId,SupplierGuideDTO guideDTO, Integer page, Integer pageSize);

	public WebResult<PageBean> expGuide(SupplierGuideDTO guideDTO,Integer bizId, Integer page, Integer pageSize);

//	public GuideListResult bizGuideList(Integer bizId,SupplierGuideDTO guideDTO, Integer page, Integer pageSize);

//	public GuideListResult queryBizGuideList(Integer bizId,SupplierGuideDTO guideDTO, Integer page, Integer pageSize);
//
//	public GuideListResult impGuideList(Integer bizId,SupplierGuideDTO guideDTO, Integer page, Integer pageSize);
//
//	public GuideListResult queryImpGuideList(Integer bizId,SupplierGuideDTO guideDTO, Integer page, Integer pageSize);

	public WebResult<Boolean> impGuideList(Integer bizId,String guideIds);

	/**
	 * 跳转到图片页面
	 * 
	 * @param id
	 * @param supplierType
	 * @return
	 */
	public FolderListResult toFolderList(Integer id,
			Integer supplierType, Integer operType);

	/**
	 * 跳转到图片展示页面
	 * 
	 * @param id
	 * @param supplierId
	 * @param supplierType
	 * @return
	 */

	public PictureListResult toPictureList(Integer id,Integer supplierId, Integer supplierType, Integer operType);

	/**
	 * 保存图片
	 * 
	 * @param request
	 * @param reponse
	 * @param imgs
	 * @return
	 */
	public WebResult<Boolean> saveSupplierImg(String imgs);

	public WebResult<Boolean> delPicture( Integer id);
	
	public WebResult<String> searchSupplier(Integer bizId, Integer supplierType, String keyword);
	/**
	 * 获取待审核的供应商列表
	 * @param request
	 * @param response
	 * @param model
	 * @param supplierInfo
	 * @return
	 */
	public WebResult<PageBean> supplierCheckList(PageBean pageBean,Integer bizId);
	/**
	 * 审核供应商
	 * @param checkedSupplierIds
	 * @return
	 */
	public WebResult<Boolean> checkSupplier(String checkedSupplierIds);
	public List<Integer> getSupplierIds(Map<String, Object> params);
}
