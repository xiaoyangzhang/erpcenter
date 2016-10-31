package com.yimayhd.erpcenter.facade.sales.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingShop;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingShopDetail;
import com.yimayhd.erpcenter.dal.sales.client.operation.vo.BookingShopDetailDeployVO;
import com.yimayhd.erpcenter.dal.sales.client.sales.vo.TourGroupVO;
import com.yimayhd.erpcenter.facade.sales.query.BookingShopDTO;
import com.yimayhd.erpcenter.facade.sales.query.BookingShopDetailDeployDTO;
import com.yimayhd.erpcenter.facade.sales.query.BookingShopListDTO;
import com.yimayhd.erpcenter.facade.sales.query.ContractPriceExtDTO;
import com.yimayhd.erpcenter.facade.sales.result.BookingShopInputResult;
import com.yimayhd.erpcenter.facade.sales.result.BookingShopResult;
import com.yimayhd.erpcenter.facade.sales.result.GuestShopListResult;
import com.yimayhd.erpcenter.facade.sales.result.GuestShopResult;
import com.yimayhd.erpcenter.facade.sales.result.LoadBookingShopInfoResult;
import com.yimayhd.erpcenter.facade.sales.result.LoadShopInfoResult;
import com.yimayhd.erpcenter.facade.sales.result.ResultSupport;
import com.yimayhd.erpcenter.facade.sales.result.ToAddShopResult;
import com.yimayhd.erpcenter.facade.sales.result.ToFactShopResult;
import com.yimayhd.erpcenter.facade.sales.result.WebResult;
import com.yimayhd.erpresource.dal.po.SupplierItem;

/**
 * 
* @ClassName: BookingShopFacade 
* @Description: 
* @author wangjun
* @date 2016年10月25日 下午4:32:17 
*
 */
public interface BookingShopFacade {
	/**
	 * 客人购物录入查询
	 * @param bookingShopListDTO
	 * @return
	 * @author wangjun
	 */
	PageBean bookingShopList(BookingShopListDTO bookingShopListDTO);
	/**
	 * 客人购物录入-编辑
	 * @param bookingShopDTO
	 * @return
	 * @author wangjun
	 */
	List<Map<String, Object>> toEditShop(BookingShopDTO bookingShopDTO);
	/**
	 * 客人购物录入-编辑保存
	 * @param shopDetails
	 * @author wangjun
	 */
	void saveShopDetails(String shopDetails);
	
	/**
	 * 购物单列表
	 * @param groupId
	 * @return
	 * @author wangjun
	 */
	List<BookingShop> shopDetailList(int groupId);
	
	/**
	 * 购物单编辑页面
	 * @param bookingShopDTO
	 * @return
	 * @author wangjun
	 */
	ToFactShopResult toFactShop(BookingShopDTO bookingShopDTO);
	
	/**
	 * 购物单编辑-保存
	 * @return
	 * @author wangjun
	 */
	int saveDeploy(BookingShopDetailDeployDTO bookingShopDetailDeployDTO);
	/**
	 * 删除购物单
	 * @param bookingId
	 * @return
	 * @author wangjun
	 */
	int delBookingShop(int bookingId);
	
	/**
	 * 分配购物店公用接口（bookingshop.toFinanceBookingShopView.htm）
	 * @param groupId
	 * @return
	 * @author wangjun
	 */
	LoadBookingShopInfoResult loadBookingShopInfo(int groupId);
	
	/**
	 * 编辑购物店公用接口（bookingshop.editFinanceShop.htm）
	 * @param bookingShopDTO
	 * @return
	 * @author wangjun
	 */
	LoadShopInfoResult loadShopInfo(BookingShopDTO bookingShopDTO);
	
	/**
	 * 客人购物统计列表
	 * @param bizId
	 * @return
	 * @author wangjun
	 */
	GuestShopListResult toGuestShopList(int bizId);
	
	/**
	 * 客人购物统计列表do
	 * @param bookingShopListDTO
	 * @return
	 * @author wangjun
	 */
	GuestShopResult guestShopList(BookingShopListDTO bookingShopListDTO);
	
	/**
	 * 购物录入列表
	 * @param bookingShopListDTO
	 * @return
	 * @author wangjun
	 */
	BookingShopInputResult bookingShopInputList(BookingShopListDTO bookingShopListDTO);
	
	/**
	 * 新增购物页面
	 * @param groupId
	 * @return
	 * @author wangjun
	 */
	ToAddShopResult toAddShop(int groupId);
	
	/**
	 * 根据导游获取结对司机
	 * @param bookingShopDTO
	 * @return
	 * @author wangjun
	 */
	Map<String, Object> getMatchedDriver(BookingShopDTO bookingShopDTO);
	/**
	 * 根据新增时所选商家ID加载该商家项目
	 * @param supplierId
	 * @return
	 * @author wangjun
	 */
	List<SupplierItem> selectItems(int supplierId);
	
	/**
	 * 查询购物项目协议
	 * @param contractPriceExtDTO
	 * @return
	 * @author wangjun
	 */
	String contractPriceExt(ContractPriceExtDTO contractPriceExtDTO);
	
	PageBean getShopGroupList(PageBean pageBean,TourGroupVO groupVO,Set<Integer> set);
	BookingShopResult groupShopBookingList(Integer groupId);
	boolean checkGroupCanEdit(Integer groupId);
	
	ResultSupport saveShopInfo(BookingShop bookingShop ,String bizCode);
	
	WebResult<Map<String, Boolean>> deldetailGuide(Integer bookingShopId);
	BookingShopResult toFactShop(Integer bookingShopId);
	BookingShopResult editFactShop(Integer orderId,Integer bookingShopDetailId);
	BookingShopDetail getShopDetailById(Integer bookingShopDetailId);
	ResultSupport saveShopDetail(BookingShopDetail shopDetail);
	ResultSupport delShopDetail(Integer bookingShopDetailId,Integer groupId);
	ResultSupport saveShopDetail(BookingShopDetailDeployVO vo);
	PageBean shopTJList(PageBean pageBean,Integer bizId);
}
