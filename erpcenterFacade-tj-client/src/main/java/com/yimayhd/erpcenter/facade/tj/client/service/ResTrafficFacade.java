package com.yimayhd.erpcenter.facade.tj.client.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.dubbo.container.page.Page;
import com.alibaba.fastjson.JSON;
import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.common.contants.BasicConstants;
import com.yimayhd.erpcenter.common.contants.BasicConstants.LOG_ACTION;
import com.yimayhd.erpcenter.dal.basic.po.DicInfo;
import com.yimayhd.erpcenter.dal.basic.po.LogOperator;
import com.yimayhd.erpcenter.dal.basic.utils.LogFieldUtil;
import com.yimayhd.erpcenter.dal.product.po.TrafficRes;
import com.yimayhd.erpcenter.dal.product.po.TrafficResProduct;
import com.yimayhd.erpcenter.dal.product.po.TrafficResStocklog;
import com.yimayhd.erpcenter.dal.sales.client.constants.Constants;
import com.yimayhd.erpcenter.dal.sales.client.finance.po.FinancePayDetail;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingGuide;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingSupplier;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingSupplierDetail;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrder;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrderGuest;
import com.yimayhd.erpcenter.dal.sys.po.PlatformEmployeePo;
import com.yimayhd.erpcenter.facade.tj.client.query.BookingSupplierDTO;
import com.yimayhd.erpcenter.facade.tj.client.query.DetailsStocklogDTO;
import com.yimayhd.erpcenter.facade.tj.client.query.MakeCollectionsDTO;
import com.yimayhd.erpcenter.facade.tj.client.query.SaveBookingDTO;
import com.yimayhd.erpcenter.facade.tj.client.query.ToSaveResNumsSoldDTO;
import com.yimayhd.erpcenter.facade.tj.client.query.ToSearchListDTO;
import com.yimayhd.erpcenter.facade.tj.client.query.ToUpdateProductPriceDTO;
import com.yimayhd.erpcenter.facade.tj.client.query.TrafficChangeDTO;
import com.yimayhd.erpcenter.facade.tj.client.query.TrafficDTO;
import com.yimayhd.erpcenter.facade.tj.client.query.TrafficProBindingDTO;
import com.yimayhd.erpcenter.facade.tj.client.query.TrafficResDTO;
import com.yimayhd.erpcenter.facade.tj.client.query.TrafficResProductDTO;
import com.yimayhd.erpcenter.facade.tj.client.query.TrafficSaveResOrderDTO;
import com.yimayhd.erpcenter.facade.tj.client.query.TraficchangeResStateDTO;
import com.yimayhd.erpcenter.facade.tj.client.result.GetProductInfoResult;
import com.yimayhd.erpcenter.facade.tj.client.result.ToAddSupplierResult;
import com.yimayhd.erpcenter.facade.tj.client.result.ToSearchListResult;
import com.yimayhd.erpcenter.facade.tj.client.result.ToTraficEditResult;
import com.yimayhd.erpcenter.facade.tj.client.result.ToUpdateResNumStockStateResult;
import com.yimayhd.erpcenter.facade.tj.client.result.TrafficAddResOrderResult;
import com.yimayhd.erpcenter.facade.tj.client.result.WebResult;
import com.yimayhd.erpresource.dal.po.SupplierItem;


public interface ResTrafficFacade {
	/**
	 * 交通资源table
	 * @param request
	 * @param model
	 * @param pageSize
	 * @param page
	 * @return
	 */
	public PageBean resourceList_table(TrafficDTO trafficDTO);
	
	/**
	 * @author : zhoumi
	 * @date : 2016年9月12日 
	 * @Description: 保存
	 */
	public int save(TrafficResDTO trafficResDTO) ;
	
	/**
	 * @author : zhoumi
	 * @date : 2016年9月12日 
	 * @Description: 编辑
	 */
	public ToTraficEditResult toEdit(int id) ;
	
	/**
	 * @author : zhoumi
	 * @date : 2016年9月22日 
	 * @Description: 上架/下架
	 */
	public int changeResState(TraficchangeResStateDTO dto);
	
	public List<TrafficRes> resDetails(int resId) ;
	
	public List<TrafficRes> detailsStocklog(DetailsStocklogDTO detailsStocklogDTO) ;
		
    public void sureCopy(DetailsStocklogDTO detailsStocklogDTO);
	
    public void change(TrafficChangeDTO trafficChangeDTO);
	
	/**
	 * 产品列表table
	 * @param request
	 * @param model
	 * @param pageSize
	 * @param page
	 * @return
	 */
	public PageBean resProductList_table(TrafficDTO trafficDTO) ;
	
	/**
	 * 跳转到预定信息页面
	 * @param request
	 * @param reponse
	 * @param model
	 * @return
	 */
	public TrafficAddResOrderResult addResOrder(TrafficResDTO trafficResDTO) ;
	
	public TrafficAddResOrderResult EditResOrder(TrafficResDTO trafficResDTO);
	
	/**
	 * 保存订单
	 * @param request
	 * @param reponse
	 * @param model
	 * @return
	 */
	public WebResult saveResOrder(TrafficSaveResOrderDTO trafficSaveResOrderDTO);
	
	public GroupOrder receivables(Integer id);

	public void makeCollections(MakeCollectionsDTO makeCollectionsDTO);
	
	public List<FinancePayDetail> receivablesRecord(Integer id) ;
	
	/**
	 * 查询已绑定的产品信息
	 * @param request
	 * @param resId
	 * @param model
	 * @return
	 */
	public List<TrafficResProduct> toProductBindingList(Integer resId);
	
	/**
	 * 查询产品信息
	 * @param request
	 * @param resId
	 * @param model
	 * @return
	 */
	public List<TrafficResProduct> toProductBindingTable(Integer resId);
	
	/**
	 * 插入绑定产品信息
	 * @param request
	 * @param resId
	 * @param model
	 * @return
	 */
	public String insertTrafficProBinding(TrafficProBindingDTO trafficProBindingDTO);
	
	
	/**
	 * 修改资源产品信息
	 * @param request
	 * @param resId
	 * @param model
	 * @return
	 */
	public TrafficResProduct toUpdateResProduct(Integer resId);
	
	/**
	 * 保存修改产品信息
	 * @param request
	 * @param resProBean
	 * @param model
	 * @return
	 */
	public String toSaveResProduct(TrafficResProductDTO trafficResProductDTO);
	
	/**
	 * 根据ID删除产品信息
	 * @param request
	 * @param id
	 * @return
	 */
	public String deleteResProduct(TrafficResProductDTO trafficResProductDTO);
	
	/**
	 * 调整价格方法
	 * @param request
	 * @param id
	 * @param suggest_price_id
	 * @param adjust_uprodown_num
	 * @param price
	 * @return
	 */
	public String toUpdateProductPrice(ToUpdateProductPriceDTO toUpdateProductPriceDTO);
	
	public List<DicInfo> getListByTypeCode(int bizId) ;
	
	public GetProductInfoResult getProductInfo(TrafficResProductDTO trafficResProductDTO) ;
	
	public ToSearchListResult toSearchList(ToSearchListDTO toSearchListDTO) ;
	
	public List<GroupOrderGuest> ticketInfo(Integer resId);
	
	public TrafficRes addTicket(Integer resId);
	
	/**
	 * 跳转到供应商编辑或新增页面
	 *
	 * @param model
	 * @param groupId
	 * @param bookingId
	 */
	public ToAddSupplierResult toAddSupplier(BookingSupplierDTO bookingSupplierDTO) ;
	
	/**
	 * 保存新增出票
	 *
	 * @param request
	 * @param reponse
	 * @param booking
	 * @param groupId
	 * @param supplierType
	 * @param supplierId
	 * @return
	 * @throws ParseException s
	 */
	public void saveBooking(SaveBookingDTO saveBookingDTO);
	
	/**
	 * 跳转至机位库存状态页面
	 * @param request
	 * @param resId
	 * @param model
	 * @return
	 */
	public ToUpdateResNumStockStateResult toUpdateResNumStockState(Integer id);
	
	/**
	 * 保存机位库存信息
	 * @param request
	 * @param productBean
	 * @return
	 */
	public String toSaveResNumsSold(ToSaveResNumsSoldDTO toSaveResNumsSoldDTO);

	PageBean selectAirTicketProfitList(PageBean pageBean);

	List<TrafficResProduct> loadTrafficResProductInfo(Integer resId);

	TrafficAddResOrderResult airTicketProfit_table(TrafficResDTO trafficResDTO);

	TrafficAddResOrderResult budgetDetail(TrafficResDTO trafficResDTO);

	TrafficAddResOrderResult constantlyDetail(TrafficResDTO trafficResDTO);

	TrafficAddResOrderResult resRoomExtrabed_table(TrafficResDTO trafficResDTO);
}
