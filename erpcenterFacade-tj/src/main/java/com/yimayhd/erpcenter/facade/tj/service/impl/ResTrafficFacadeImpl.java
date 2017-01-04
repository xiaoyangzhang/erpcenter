package com.yimayhd.erpcenter.facade.tj.service.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.yimayhd.erpcenter.biz.sales.client.service.sales.*;
import com.yimayhd.erpcenter.dal.basic.constant.BasicConstants;
import com.yimayhd.erpcenter.dal.basic.constant.BasicConstants.LOG_ACTION;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.biz.basic.service.DicBiz;
import com.yimayhd.erpcenter.biz.basic.service.LogOperatorBiz;
import com.yimayhd.erpcenter.biz.basic.service.RegionBiz;
import com.yimayhd.erpcenter.biz.product.service.ProductGroupSupplierBiz;
import com.yimayhd.erpcenter.biz.product.service.ProductInfoBiz;
import com.yimayhd.erpcenter.biz.product.service.TrafficResBiz;
import com.yimayhd.erpcenter.biz.product.service.TrafficResProductBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.finance.FinanceBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.operation.BookingGuideBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.operation.BookingSupplierBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.operation.BookingSupplierDetailBiz;
import com.yimayhd.erpcenter.biz.sys.service.PlatformEmployeeBiz;
import com.yimayhd.erpcenter.biz.sys.service.PlatformOrgBiz;
import com.yimayhd.erpcenter.dal.basic.po.DicInfo;
import com.yimayhd.erpcenter.dal.basic.po.LogOperator;
import com.yimayhd.erpcenter.dal.basic.po.RegionInfo;
import com.yimayhd.erpcenter.dal.basic.utils.LogFieldUtil;
import com.yimayhd.erpcenter.dal.product.po.ProductGroupSupplier;
import com.yimayhd.erpcenter.dal.product.po.ProductInfo;
import com.yimayhd.erpcenter.dal.product.po.TrafficRes;
import com.yimayhd.erpcenter.dal.product.po.TrafficResLine;
import com.yimayhd.erpcenter.dal.product.po.TrafficResProduct;
import com.yimayhd.erpcenter.dal.product.po.TrafficResStocklog;
import com.yimayhd.erpcenter.dal.product.vo.ProductSupplierCondition;
import com.yimayhd.erpcenter.dal.product.vo.TrafficResVo;
import com.yimayhd.erpcenter.dal.sales.client.constants.Constants;
import com.yimayhd.erpcenter.dal.sales.client.finance.po.FinancePay;
import com.yimayhd.erpcenter.dal.sales.client.finance.po.FinancePayDetail;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingGuide;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingSupplier;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingSupplierDetail;
import com.yimayhd.erpcenter.dal.sales.client.sales.vo.SpecialGroupOrderVO;
import com.yimayhd.erpcenter.dal.sys.po.PlatformEmployeePo;
import com.yimayhd.erpcenter.facade.tj.client.errorcode.TjErrorCode;
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
import com.yimayhd.erpcenter.facade.tj.client.service.ResTrafficFacade;
import com.yimayhd.erpcenter.facade.tj.client.utils.LogUtils;
import com.yimayhd.erpresource.biz.service.SupplierItemBiz;
import com.yimayhd.erpresource.dal.po.SupplierItem;


public class ResTrafficFacadeImpl implements ResTrafficFacade{
	private static final Logger log = LoggerFactory
			.getLogger(ResTrafficFacadeImpl.class);
	
	@Autowired
	private DicBiz dicBiz;
	@Autowired
	private PlatformOrgBiz platformOrgBiz;
	@Autowired
	private PlatformEmployeeBiz platformEmployeeBiz;
	@Autowired
	private TrafficResBiz trafficResBiz;
	@Autowired
	private RegionBiz regionBiz;
	@Autowired
	private ProductInfoBiz productInfoBiz;
	@Autowired
	private SpecialGroupOrderBiz specialGroupOrderBiz;
	@Autowired
	private GroupOrderBiz groupOrderBiz;
	@Autowired
	private GroupOrderPriceBiz groupOrderPriceBiz;
	@Autowired
	private GroupOrderGuestBiz groupOrderGuestBiz;
	@Autowired
	private GroupOrderTransportBiz groupOrderTransportBiz;
	@Autowired
	private FinanceBiz financeBiz;
	@Autowired
	private TrafficResProductBiz trafficResProductBiz;
	@Autowired
	private LogOperatorBiz logOperatorBiz;
	
	@Autowired
	private ProductGroupSupplierBiz productGroupSupplierBiz;
	
	@Autowired
	private BookingSupplierBiz bookingSupplierBiz;
	
	@Autowired
	private BookingGuideBiz bookingGuideBiz;
	
	@Autowired
	private BookingSupplierDetailBiz bookingSupplierDetailBiz;
	
	@Autowired
	private SupplierItemBiz supplierItemBiz;

	@Autowired
	private TourGroupBiz tourGroupBiz;


	
	/**
	 * 交通资源table
	 * @param request
	 * @param model
	 * @param pageSize
	 * @param page
	 * @return
	 */
	public PageBean resourceList_table(TrafficDTO trafficDTO) {
		Integer page = trafficDTO.getPage();
		Integer pageSize = trafficDTO.getPageSize();
		Map<String, Object> pm = trafficDTO.getPm();
		PageBean<TrafficRes> pageBean = new PageBean<TrafficRes>();
		if(page==null){
			pageBean.setPage(1);
		}else{
			pageBean.setPage(page);
		}
		if(pageSize==null){
			pageBean.setPageSize(Constants.PAGESIZE);
		}else{
			pageBean.setPageSize(pageSize);
		}
		pageBean.setPage(page);
		pageBean.setParameter(pm);
		pageBean=trafficResBiz.selectTrafficResListPage(pageBean);
		return pageBean;
	}
	
	/**
	 * @author : zhoumi
	 * @date : 2016年9月12日 
	 * @Description: 保存
	 */
	public int save(TrafficResDTO trafficResDTO) {
		TrafficResVo vo = trafficResDTO.getTrafficResVo();
		int bizId = trafficResDTO.getBizId();
		PlatformEmployeePo curUser = trafficResDTO.getCurUser();
		int userId = trafficResDTO.getUserId();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if(vo.getTrafficRes().getId()==null){
			vo.getTrafficRes().setUserName(curUser.getName());
			vo.getTrafficRes().setUserId(userId);
			vo.getTrafficRes().setBizId(bizId);
			vo.getTrafficRes().setTimeCreate(sdf.format(new Date()));
			vo.getTrafficRes().setNumReserved(0);
			vo.getTrafficRes().setNumSold(0);
			vo.getTrafficRes().setNumBalance((vo.getTrafficRes().getNumStock()-vo.getTrafficRes().getNumDisable()));
			vo.getTrafficRes().setState((byte) 1);
			vo.getTrafficRes().setTimeUpdate(sdf.format(new Date()));
		}else{
			vo.getTrafficRes().setUserName(curUser.getName());
			vo.getTrafficRes().setUserId(userId);
			vo.getTrafficRes().setTimeUpdate(sdf.format(new Date()));
		}
		//产生日志（主表）
		List<LogOperator> logList = new ArrayList<LogOperator>();
		int id = 0; boolean isNew = false;
		if (vo.getTrafficRes().getId()==null) {
			isNew = true;
			
		}else{
			id = vo.getTrafficRes().getId();
			
			TrafficRes orginReS = trafficResBiz.findTrafficResById(id);
			logList.add(LogFieldUtil.getLog_Instant(curUser.getBizId(), curUser.getName(), BasicConstants.LOG_ACTION.UPDATE, "traffic_res", id, 0, "修改资源记录", vo.getTrafficRes(), orginReS));
			
			//产生日志（子表）
			List<TrafficResLine> lineListDB = trafficResBiz.selectTrafficResLine(id);
			List<TrafficResLine> lineList = vo.getTrafficResLine();
			List<LogOperator> tmpList = LogFieldUtil.getLog_InstantList(curUser.getBizId(), curUser.getName(), "traffic_res_line", id, lineList, lineListDB);
			logList.addAll(tmpList);
		}
		
		id = trafficResBiz.saveTrafficRes(vo); //业务保存代码
		
		if (isNew){
			logList.add(LogFieldUtil.getLog_Instant(curUser.getBizId(), curUser.getName(),LOG_ACTION.INSERT, "traffic_res", id, 0, String.format("创建资源记录,名称:%s", vo.getTrafficRes().getResName()), vo.getTrafficRes(), null)); 
		}
		
		if(vo.getTrafficRes().getId()==null){
			if(vo.getTrafficRes().getNumStock()!=0&&vo.getTrafficRes().getNumStock()!=null){
				TrafficResStocklog trafficResStocklog=new TrafficResStocklog();
				trafficResStocklog.setAdjustAction(com.yimayhd.erpcenter.dal.product.constans.Constants.TRAFFICRES_STOCK_ACTION.STOCK.toString());
				trafficResStocklog.setAdjustNum(vo.getTrafficRes().getNumStock());
				trafficResStocklog.setResId(id);
				trafficResStocklog.setAdjustTime(new Date());
				trafficResStocklog.setUserId(userId);
				trafficResStocklog.setUserName(curUser.getName());
				trafficResBiz.insertTrafficResStocklog(trafficResStocklog);
				trafficResBiz.updateStockOrStockDisable(id);
				
				logList.add(LogFieldUtil.getLog_Instant(curUser.getBizId(), curUser.getName(),LOG_ACTION.INSERT, "traffic_res_stocklog", 0, id ,"库位变动,【入库】", trafficResStocklog, null));
			}
			if(vo.getTrafficRes().getNumDisable()!=0&&vo.getTrafficRes().getNumDisable()!=null){
				TrafficResStocklog trafficResStocklog=new TrafficResStocklog();
				trafficResStocklog.setAdjustAction(com.yimayhd.erpcenter.dal.product.constans.Constants.TRAFFICRES_STOCK_ACTION.STOCK_DISABLE.toString());
				trafficResStocklog.setAdjustNum(vo.getTrafficRes().getNumDisable());
				trafficResStocklog.setResId(id);
				trafficResStocklog.setAdjustTime(new Date());
				trafficResStocklog.setUserId(userId);
				trafficResStocklog.setUserName(curUser.getName());
				trafficResBiz.insertTrafficResStocklog(trafficResStocklog);
				trafficResBiz.updateStockOrStockDisable(id);
				logList.add(LogFieldUtil.getLog_Instant(curUser.getBizId(), curUser.getName(),LOG_ACTION.INSERT, "traffic_res_stocklog", 0, id ,"库位变动,【机动位】", trafficResStocklog, null));
			}
		}
		logOperatorBiz.insert(logList);
		
		return id;
	}
	
	/**
	 * @author : zhoumi
	 * @date : 2016年9月12日 
	 * @Description: 编辑
	 */
	public ToTraficEditResult toEdit(int id) {
		ToTraficEditResult result = new ToTraficEditResult();
		TrafficRes trafficRes=trafficResBiz.findTrafficResById(id);
		result.setTrafficRes(trafficRes);
		List<TrafficResLine> trafficResLine=trafficResBiz.selectTrafficResLine(id);
		result.setTrafficResLines(trafficResLine);
		return result;
	}
	
	/**
	 * @author : zhoumi
	 * @date : 2016年9月22日 
	 * @Description: 上架/下架
	 */
	public int changeResState(TraficchangeResStateDTO dto){
		//日志 
		PlatformEmployeePo curUser = dto.getCurUser();
		int id = dto.getId();
		int state = dto.getState();
		//日志
		TrafficRes trafficRes=trafficResBiz.findTrafficResById(id);
		int rr = trafficResBiz.updateTrafficResState(id, state);
		TrafficRes trafficRes1=trafficResBiz.findTrafficResById(id);
		List<LogOperator> logList = new ArrayList<LogOperator>();
		String stateStr = ("0".equals(String.valueOf(state))?"下架":"上架");
		logList.add(LogFieldUtil.getLog_Instant(curUser.getBizId(), curUser.getName(),LOG_ACTION.UPDATE, "traffic_res", id, 0, "更改状态为："+stateStr, trafficRes1, trafficRes));
		logOperatorBiz.insert(logList);
		return rr;
	}
	
	public List<TrafficRes> resDetails(int resId) {
		return trafficResBiz.selectResDetails(resId);
	}
	
	public List<TrafficRes> detailsStocklog(DetailsStocklogDTO detailsStocklogDTO) {
		int resId = detailsStocklogDTO.getResId();
		String adjustTime = detailsStocklogDTO.getAdjustTime();
		return trafficResBiz.selectDetailsStocklog(resId, adjustTime);
	}
		
    public void sureCopy(DetailsStocklogDTO detailsStocklogDTO){
		int resId = detailsStocklogDTO.getResId();
		String[] arr = detailsStocklogDTO.getAdjustTime().split(",");
		PlatformEmployeePo curUser = detailsStocklogDTO.getCurUser();
		List<String> list = java.util.Arrays.asList(arr);
		//日志 
		List<LogOperator> logList = new ArrayList<LogOperator>();
		
				
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				Integer newResId = trafficResBiz.copyRes(resId, list.get(i));
				logList.add(LogFieldUtil.getLog_Instant(curUser.getBizId(), curUser.getName(),LOG_ACTION.INSERT, "traffic_res", newResId, 0 ,"由复制产生！", null, null));
			}
		}
		logOperatorBiz.insert(logList);
    }
	
    public void change(TrafficChangeDTO trafficChangeDTO){
		int resId = trafficChangeDTO.getResId();
		String type = trafficChangeDTO.getType();
		String numType = trafficChangeDTO.getNumType();
		int num = trafficChangeDTO.getNum();
		PlatformEmployeePo curUser = trafficChangeDTO.getCurUser();
		int userId = trafficChangeDTO.getUserId();
		TrafficResStocklog trafficResStocklog=new TrafficResStocklog();
		if(type.equals("numStock")){
			trafficResStocklog.setAdjustAction(com.yimayhd.erpcenter.dal.product.constans.Constants.TRAFFICRES_STOCK_ACTION.STOCK.toString());
		}
		if(type.equals("numDisable")){
			trafficResStocklog.setAdjustAction(com.yimayhd.erpcenter.dal.product.constans.Constants.TRAFFICRES_STOCK_ACTION.STOCK_DISABLE.toString());
		}
		if(numType.equals("add")){
			trafficResStocklog.setAdjustNum(num);
		}
		if(numType.equals("minus")){
			trafficResStocklog.setAdjustNum(-num);
		}
		trafficResStocklog.setResId(resId);
		trafficResStocklog.setAdjustTime(new Date());
		trafficResStocklog.setUserId(userId);
		trafficResStocklog.setUserName(curUser.getName());
		trafficResBiz.insertTrafficResStocklog(trafficResStocklog);
		trafficResBiz.updateStockOrStockDisable(resId);
		
		//日志 
		List<LogOperator> logList = new ArrayList<LogOperator>();
		logList.add(LogFieldUtil.getLog_Instant(curUser.getBizId(), curUser.getName(),LOG_ACTION.INSERT, "traffic_res_stocklog", 0, resId ,"库位变动", trafficResStocklog, null));
		logOperatorBiz.insert(logList);
    }
	
	/**
	 * 产品列表table
	 * @param request
	 * @param model
	 * @param pageSize
	 * @param page
	 * @return
	 */
	public PageBean resProductList_table(TrafficDTO trafficDTO) {
		Integer page = trafficDTO.getPage();
		Integer pageSize = trafficDTO.getPageSize();
		Map<String,Object> pm = trafficDTO.getPm();
		SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd");
		PageBean<TrafficResProduct> pageBean = new PageBean<TrafficResProduct>();
		if(page==null){
			pageBean.setPage(1);
		}else{
			pageBean.setPage(page);
		}
		if(pageSize==null){
			pageBean.setPageSize(Constants.PAGESIZE);
		}else{
			pageBean.setPageSize(pageSize);
		}
		pageBean.setPage(page);
		pageBean.setParameter(pm);
		pageBean=trafficResBiz.selectResProductListPage(pageBean);
		List<TrafficResProduct> list=pageBean.getResult();
		if(pageBean.getResult()!=null && pageBean.getResult().size()>0){
			 for(TrafficResProduct trp : list){
				 if(trp.getNumStock()==0){     // 库存
					 trp.setNumStock(trp.getNumBalance());
					}else{
						trp.setNumStock(trp.getNumStock()-trp.getNumSold());
						}
				 trp.setAdultSame(trp.getAdultSuggestPrice().subtract(trp.getAdultSamePay()));
				 trp.setAdultProxy((trp.getAdultSuggestPrice().subtract(trp.getAdultSamePay())).subtract(trp.getAdultProxyPay()));
				 trp.setChildSame(trp.getChildSuggestPrice().subtract(trp.getChildSamePay()));
				 trp.setChildProxy((trp.getChildSuggestPrice().subtract(trp.getChildSamePay())).subtract(trp.getChildProxyPay()));
				 trp.setBabySame(trp.getBabySuggestPrice().subtract(trp.getBabySamePay()));
				 trp.setBabyProxy((trp.getBabySuggestPrice().subtract(trp.getBabySamePay())).subtract(trp.getBadyProxyPay()));
			 }
		 }
		return pageBean;
	}
	
	/**
	 * 跳转到预定信息页面
	 * @param request
	 * @param reponse
	 * @param model
	 * @return
	 */
	public TrafficAddResOrderResult addResOrder(TrafficResDTO trafficResDTO) {
		TrafficAddResOrderResult result = new TrafficAddResOrderResult();
		int id = trafficResDTO.getTrafficProductInfoId();
		PlatformEmployeePo curUser = trafficResDTO.getCurUser();
		int bizId = trafficResDTO.getBizId();
//		model.addAttribute("operType", 1);
		TrafficResProduct trafficResProduct=trafficResBiz.selectTrafficProductInfo(id);
		GroupOrder groupOrder  = new GroupOrder();
		groupOrder.setSaleOperatorId(trafficResProduct.getOperatorId());
		groupOrder.setSaleOperatorName(trafficResProduct.getOperatorName());
		groupOrder.setOperatorId(trafficResProduct.getOperatorId()); 
		groupOrder.setOperatorName(trafficResProduct.getOperatorName());
		groupOrder.setContactName(curUser.getName());
		groupOrder.setContactMobile(curUser.getMobile());
		groupOrder.setContactTel(curUser.getPhone());
		groupOrder.setContactFax(curUser.getFax());
		SpecialGroupOrderVO  vo = new SpecialGroupOrderVO();
		groupOrder.setSupplierId(trafficResDTO.getSupplierId());
		groupOrder.setSupplierName(trafficResDTO.getSupplierName());
		vo.setGroupOrder(groupOrder);
		result.setVo(vo);
		List<DicInfo> jdxjList = dicBiz.getListByTypeCode(BasicConstants.GYXX_JDXJ);
		result.setJdxjList(jdxjList);
		List<DicInfo> jtfsList = dicBiz
				.getListByTypeCode(BasicConstants.GYXX_JTFS, bizId);
		result.setJtfsList(jtfsList);
		List<DicInfo> zjlxList = dicBiz
				.getListByTypeCode(BasicConstants.GYXX_ZJLX);
		result.setZjlxList(zjlxList);
		List<DicInfo> sourceTypeList = dicBiz.getListByTypeCode(Constants.GUEST_SOURCE_TYPE, bizId);
		result.setSourceTypeList(sourceTypeList);
		List<RegionInfo> allProvince = regionBiz.getAllProvince();
		result.setAllProvince(allProvince);
		List<DicInfo> lysfxmList = dicBiz.getListByTypeCode(
				BasicConstants.GYXX_LYSFXM, bizId);
		result.setLysfxmList(lysfxmList);
		ProductInfo productInfo=productInfoBiz.findProductInfoById(trafficResProduct.getProductCode());
		if(trafficResProduct.getNumStock()==0){
			trafficResProduct.setNumStock(trafficResProduct.getNumBalance());
		}else{
			trafficResProduct.setNumStock(trafficResProduct.getNumStock()-trafficResProduct.getNumSold());
		}
		trafficResProduct.setAdultSuggestPrice((trafficResProduct.getAdultSuggestPrice().subtract(trafficResProduct.getAdultSamePay())).subtract(trafficResProduct.getAdultProxyPay()));
		trafficResProduct.setChildSuggestPrice((trafficResProduct.getChildSuggestPrice().subtract(trafficResProduct.getChildSamePay())).subtract(trafficResProduct.getChildProxyPay()));
		trafficResProduct.setBabySuggestPrice((trafficResProduct.getBabySuggestPrice().subtract(trafficResProduct.getBabySamePay())).subtract(trafficResProduct.getBadyProxyPay()));
		List<TrafficResLine> trafficResLine=trafficResBiz.selectTrafficResLine(trafficResProduct.getResId());
		result.setTrafficResProduct(trafficResProduct);
		result.setProductInfo(productInfo);
		result.setTrafficResLine(trafficResLine);
		
		return result;
	}
	
	public TrafficAddResOrderResult EditResOrder(TrafficResDTO trafficResDTO){
		TrafficAddResOrderResult result = new TrafficAddResOrderResult();
		int id = trafficResDTO.getOrderId();
		Integer operType = trafficResDTO.getOperType();
		int bizId = trafficResDTO.getBizId();
		//id=80801;
		if(operType==null){
			operType=1;
		}
		result.setOperType(operType);
		SpecialGroupOrderVO vo= specialGroupOrderBiz.selectSpeciaOrderlInfoByOrderId(id);
		result.setVo(vo);
		List<DicInfo> jdxjList = dicBiz.getListByTypeCode(BasicConstants.GYXX_JDXJ);
		result.setJdxjList(jdxjList);
		List<DicInfo> zjlxList = dicBiz
				.getListByTypeCode(BasicConstants.GYXX_ZJLX);
		result.setZjlxList(zjlxList);
		List<DicInfo> lysfxmList = dicBiz.getListByTypeCode(
				BasicConstants.GYXX_LYSFXM, bizId);
		result.setLysfxmList(lysfxmList);
		List<DicInfo> jtfsList = dicBiz
				.getListByTypeCode(BasicConstants.GYXX_JTFS, bizId);
		result.setJtfsList(jtfsList);
		List<DicInfo> sourceTypeList = dicBiz.getListByTypeCode(Constants.GUEST_SOURCE_TYPE, bizId);
		result.setSourceTypeList(sourceTypeList);
		List<RegionInfo> allProvince = regionBiz.getAllProvince();
		result.setAllProvince(allProvince);
		List<RegionInfo> cityList = null;
		if(vo.getGroupOrder().getProvinceId()!=null && vo.getGroupOrder().getProvinceId()!=-1){
			cityList=regionBiz.getRegionById(vo.getGroupOrder().getProvinceId()+"");
		}
		result.setCityList(cityList);
		TrafficResProduct trafficResProduct=trafficResBiz.selectTrafficProductInfoByProductCode(vo.getGroupOrder().getProductId(),vo.getGroupOrder().getExtResId());
		if(trafficResProduct.getNumStock()==0){			//查找库存
			trafficResProduct.setNumStock(trafficResProduct.getNumBalance());
		}else{
			trafficResProduct.setNumStock(trafficResProduct.getNumStock()-trafficResProduct.getNumSold());
		}
		trafficResProduct.setAdultSuggestPrice((trafficResProduct.getAdultSuggestPrice().subtract(trafficResProduct.getAdultSamePay())).subtract(trafficResProduct.getAdultProxyPay()));
		trafficResProduct.setChildSuggestPrice((trafficResProduct.getChildSuggestPrice().subtract(trafficResProduct.getChildSamePay())).subtract(trafficResProduct.getChildProxyPay()));
		trafficResProduct.setBabySuggestPrice((trafficResProduct.getBabySuggestPrice().subtract(trafficResProduct.getBabySamePay())).subtract(trafficResProduct.getBadyProxyPay()));
		result.setTrafficResProduct(trafficResProduct);
		return result;
	}
	
	/**
	 * 保存订单
	 * @param request
	 * @param reponse
	 * @param model
	 * @return
	 */
	public WebResult saveResOrder(TrafficSaveResOrderDTO trafficSaveResOrderDTO){
		WebResult result = new WebResult();
		SpecialGroupOrderVO vo = trafficSaveResOrderDTO.getVo();
		String id = trafficSaveResOrderDTO.getId();
		String ids = trafficSaveResOrderDTO.getIds();
		Integer groupMode = trafficSaveResOrderDTO.getGroupMode();
		Integer trpId = trafficSaveResOrderDTO.getTrpId();
		Integer see = trafficSaveResOrderDTO.getSee();
		int bizId = trafficSaveResOrderDTO.getBizId();
		PlatformEmployeePo curUser = trafficSaveResOrderDTO.getCurUser();
		int userId = trafficSaveResOrderDTO.getUserId();
		String orderNo = trafficSaveResOrderDTO.getOrderNo();
		Integer num, orderId = 0, isAdd = 0;
		GroupOrder go = null;
		if(see != null&&see==1){
			try {
				orderId = specialGroupOrderBiz.saveGuestListInfo(vo,userId,curUser.getName(),bizId);
			} catch (ParseException e) {
				log.info("saveGuestListInfo error! SpecialGroupOrderVO={},userId={},userName={},bizId={}",vo,userId,curUser.getName(),bizId);
				result.setErrorCode(TjErrorCode.SYSTEM_ERROR);
				return result;
			}
			result.setErrorCode(TjErrorCode.PARAM_ERROR);
			result.setValue("1");
			return result;
		}
		TrafficResProduct trafficResProduct=trafficResBiz.selectTrafficProductInfo(trpId);
		if(trafficResProduct.getReserveTime()>0){
			vo.getGroupOrder().setExtResCleanTime(trafficResProduct.getReserveTime());
		}
		
		num=vo.getGroupOrder().getNumAdult()+vo.getGroupOrder().getNumChild();
		
		if(vo.getGroupOrder().getId()==null){// 计算编辑页面库存
			isAdd = 1;
			vo.getGroupOrder().setOrderNo(orderNo);
		}
		vo.getGroupOrder().setExtResPrepay(((new BigDecimal(vo.getGroupOrder().getNumAdult()).multiply(trafficResProduct.getAdultMinDeposit())).
				add(new BigDecimal(vo.getGroupOrder().getNumChild()).multiply(trafficResProduct.getChildMinDeposit()))).
				add(new BigDecimal(vo.getGroupOrder().getNumChildBaby()).multiply(trafficResProduct.getBadyMinDeposit())));
		//TODO 处理日志
		List<GroupOrderPrice> incomeList = null;
		List<GroupOrderGuest> guestList = null;
		List<GroupOrderTransport> transList = null;
		//List<GroupRoute> routeList = null;
		if (orderId > 0){
			incomeList = groupOrderPriceBiz.selectByOrderAndType(orderId, 0);
			guestList = groupOrderGuestBiz.selectByOrderId(orderId);
			transList = groupOrderTransportBiz.selectByOrderId(orderId);
			//routeList = groupRouteService.selectByOrderId(orderId);
		}
		List<LogOperator> logList = new ArrayList<LogOperator>();
		logList.addAll(LogUtils.LogRow_GroupOrder(curUser, vo.getGroupOrder(), go)); //GroupOrder
		logList.addAll(LogUtils.LogRow_GroupOrderPrice(curUser, orderId, vo.getGroupOrderPriceList(), incomeList)); //groupOrderPrice
		logList.addAll(LogUtils.LogRow_GroupOrderGuest(curUser, orderId, vo.getGroupOrderGuestList(), guestList)); //groupOrderGuest
		logList.addAll(LogUtils.LogRow_GroupOrderTransport(curUser, orderId, vo.getGroupOrderTransportList(), transList)); //groupOrderTransport
		
		//List<GroupRoute> routeEditList = LogUtils.groupRouteDatVo_Transfer_Route(vo.getGroupRouteDayVOList(), vo.getGroupOrder().getDepartureDate());
		//logList.addAll(LogUtils.LogRow_GroupRoute_OrderId(request, orderId, routeEditList, routeList)); //groupRoute

		//保存订单
		try {
			orderId = specialGroupOrderBiz.saveOrUpdateSpecialOrderInfo(vo,userId,curUser.getName(),bizId);
		} catch (ParseException e) {
			log.info("saveOrUpdateSpecialOrderInfo error! SpecialGroupOrderVO={},userId={},userName={},bizId={}",vo,userId,curUser.getName(),bizId);
			result.setErrorCode(TjErrorCode.SYSTEM_ERROR);
			return result;
		}
		
		//日志OrderId赋值
		LogUtils.LogRow_SetValue(logList, "group_order", orderId, null);
		LogUtils.LogRow_SetValue(logList, "group_order_price", null, orderId);
		LogUtils.LogRow_SetValue(logList, "group_order_guest", null, orderId);
		LogUtils.LogRow_SetValue(logList, "group_order_transport", null, orderId);
		//LogUtils.LogRow_SetValue(logList, "group_route", null, orderId);
		
		 //减资源库存
		TrafficResStocklog trafficResStocklog=new TrafficResStocklog();
		trafficResStocklog.setAdjustAction(com.yimayhd.erpcenter.dal.product.constans.Constants.TRAFFICRES_STOCK_ACTION.ORDER_SOLD.toString()); // 全款
		trafficResStocklog.setOrderId(orderId);
		trafficResStocklog.setAdjustNum(num);
		trafficResStocklog.setResId(vo.getGroupOrder().getExtResId());
		trafficResStocklog.setAdjustTime(new Date());
		trafficResStocklog.setUserId(userId);
		trafficResStocklog.setUserName(curUser.getName());
		if (isAdd == 1){
			trafficResBiz.insertTrafficResStocklog(trafficResStocklog); //Stock表 
			logList.add(LogFieldUtil.getLog_Instant(curUser.getBizId(), curUser.getName(),LOG_ACTION.INSERT, "traffic_res_stocklog", 0, trafficResProduct.getResId() ,"库位变动", trafficResStocklog, null));
		}else{
			trafficResBiz.updateTrafficResStockLogByOrderId(trafficResStocklog); 
		}
		
		//更新产品已售数量  
		if (null != trafficResProduct){
			Integer sumPerson = groupOrderBiz.selectSumPersonByProductId(trafficResProduct.getResId(),trafficResProduct.getProductCode(), vo.getGroupOrder().getDepartureDate());
			trafficResProduct.setNumSold(sumPerson);
			trafficResBiz.updateNumSoldById(trafficResProduct);
		}
		
		//更新资源的库存数量
		trafficResBiz.updateStockOrStockDisable(vo.getGroupOrder().getExtResId());
		
		//插入到日志
		logOperatorBiz.insert(logList);
		result.setValue(orderId);
		return result;
	}
	
	public GroupOrder receivables(Integer id) {
		GroupOrder go=groupOrderBiz.selectByPrimaryKey(id);
		return go;
	}

	public void makeCollections(MakeCollectionsDTO makeCollectionsDTO) {
		Integer orderId = makeCollectionsDTO.getOrderId();
		FinancePay pay = makeCollectionsDTO.getPay();
		PlatformEmployeePo emp = makeCollectionsDTO.getEmp();
		int bizId = makeCollectionsDTO.getBizId();
		pay.setUserId(emp.getEmployeeId());
		pay.setUserName(emp.getName());
		pay.setBizId(bizId);
		pay.setSupplierType(1);
		pay.setPayDate(new Date());
		pay.setPayDirect(1);
		pay.setCreateTime((new Date()).getTime());
		pay.setGroupId(0);
		financeBiz.makeCollections(pay, orderId);
	}
	
	public List<FinancePayDetail> receivablesRecord(Integer id) {
		List<FinancePayDetail> financePayDetail=financeBiz.selectFinancePayList(id);
		return financePayDetail;
	}
	
	/**
	 * 查询已绑定的产品信息
	 * @param request
	 * @param resId
	 * @param model
	 * @return
	 */
	public List<TrafficResProduct> toProductBindingList(Integer resId){
		List<TrafficResProduct> resProList = trafficResProductBiz.loadTrafficResProductInfo(resId);
		
		//查找 product_group_supplier表，若包含了当前产品的组团社，表示销售权限是指定的而不是开放的
		//product_group_supplier.group_id 存储的是res_id，只是借用字段
		Set<Integer> productIds = new HashSet<Integer>();
		for(TrafficResProduct item : resProList){
			productIds.add(item.getProductCode());
			item.setUserId(0);
		}
		if (productIds.size()>0){
			ProductSupplierCondition condition = new ProductSupplierCondition();
			condition.setProductIds(productIds);
			condition.setPageSize(10000);
			condition.setGroupId(resId);
			List<ProductGroupSupplier> supplierList = productGroupSupplierBiz.selectSupplierList(condition);
			
			for(TrafficResProduct item : resProList){
				for(ProductGroupSupplier su : supplierList){
					if (su.getProductId().equals(item.getProductCode())){
						item.setUserId(1);
					}
				}
			}
			
		}
		return resProList;
	}
	
	/**
	 * 查询产品信息
	 * @param request
	 * @param resId
	 * @param model
	 * @return
	 */
	public List<TrafficResProduct> toProductBindingTable(Integer resId){
		List<TrafficResProduct> resProList = trafficResProductBiz.loadTrafficResProductInfo(resId);
		return resProList;
	}
	
	/**
	 * 插入绑定产品信息
	 * @param request
	 * @param resId
	 * @param model
	 * @return
	 */
	public String insertTrafficProBinding(TrafficProBindingDTO trafficProBindingDTO){
		Integer resId = trafficProBindingDTO.getResId();
		Integer productId = trafficProBindingDTO.getProductId();
		PlatformEmployeePo curUser = trafficProBindingDTO.getCurUser();
		int userId = trafficProBindingDTO.getUserId();
		TrafficResProduct tProductBean = trafficResProductBiz.selectByResProductId(productId);
		TrafficRes trafficRes =trafficResBiz.findTrafficResById(resId);
		TrafficResProduct trproBean = new TrafficResProduct();
		trproBean.setResId(resId);
		trproBean.setProductCode(0);
		trproBean.setProductName("");
		trproBean.setNumStock(0);
		trproBean.setNumSold(0);
		trproBean.setAdultCostHotel(new BigDecimal(0.0000));
		trproBean.setAdultCostTicket(new BigDecimal(0.0000));
		trproBean.setAdultCostJs(new BigDecimal(0.0000));
		trproBean.setAdultCostOther(new BigDecimal(0.0000));
		trproBean.setChildCostHotel(new BigDecimal(0.0000));
		trproBean.setChildCostTicket(new BigDecimal(0.0000));
		trproBean.setChildCostJs(new BigDecimal(0.0000));
		trproBean.setAdultCostOther(new BigDecimal(0.0000));
		trproBean.setBabyCostHotel(new BigDecimal(0.0000));
		trproBean.setBabyCostTicket(new BigDecimal(0.0000));
		trproBean.setAdultCostJs(new BigDecimal(0.0000));
		trproBean.setAdultCostOther(new BigDecimal(0.0000));
		trproBean.setAdultCostPrice(new BigDecimal(0.0000));
		trproBean.setAdultSuggestPrice(new BigDecimal(0.0000));
		trproBean.setAdultSamePay(new BigDecimal(0.0000));
		trproBean.setAdultProxyPay(new BigDecimal(0.0000));
		trproBean.setAdultMinDeposit(new BigDecimal(0.0000));
		trproBean.setChildCostPrice(new BigDecimal(0.0000));
		trproBean.setChildSuggestPrice(new BigDecimal(0.0000));
		trproBean.setChildSamePay(new BigDecimal(0.0000));
		trproBean.setChildProxyPay(new BigDecimal(0.0000));
		trproBean.setChildMinDeposit(new BigDecimal(0.0000));
		trproBean.setBabyCostPrice(new BigDecimal(0.0000));
		trproBean.setBabySuggestPrice(new BigDecimal(0.0000));
		trproBean.setBabySamePay(new BigDecimal(0.0000));
		trproBean.setBadyProxyPay(new BigDecimal(0.0000));
		trproBean.setBadyMinDeposit(new BigDecimal(0.0000));
		trproBean.setUserId(userId);
		trproBean.setUserName(curUser.getName());
		if(null != tProductBean){
			trproBean.setReserveTime(tProductBean.getReserveTime());
		}else {
			trproBean.setReserveTime(0);
			
		}
		trproBean.setReserveStockLimit(0);
		trproBean.setTimeCreate(new Date());
		trproBean.setTimeUpdate(new Date());
		Integer id = trafficResProductBiz.insertTrafficResProduct(trproBean);
				
		//日志 
		List<LogOperator> logList = new ArrayList<LogOperator>();
		logList.add(LogFieldUtil.getLog_Instant(curUser.getBizId(), curUser.getName(),LOG_ACTION.INSERT, "traffic_res_product", id, resId ,"新增产品", null, null));
		logOperatorBiz.insert(logList);
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("success", id);
		return JSON.toJSONString(map);
	}
	
	
	/**
	 * 修改资源产品信息
	 * @param request
	 * @param resId
	 * @param model
	 * @return
	 */
	public TrafficResProduct toUpdateResProduct(Integer resId){
		TrafficResProduct resProductBean = trafficResProductBiz.loadTrafficResProduct(resId);
		return resProductBean;
	}
	
	/**
	 * 保存修改产品信息
	 * @param request
	 * @param resProBean
	 * @param model
	 * @return
	 */
	public String toSaveResProduct(TrafficResProductDTO trafficResProductDTO){
		TrafficResProduct productBean = trafficResProductDTO.getProductBean();
		PlatformEmployeePo curUser = trafficResProductDTO.getCurUser();
		//日志 
		List<LogOperator> logList = new ArrayList<LogOperator>();
		TrafficResProduct proBeanDB = trafficResProductBiz.loadTrafficResProduct(productBean.getId());
		logList.add(LogFieldUtil.getLog_Instant(curUser.getBizId(), curUser.getName(),LOG_ACTION.UPDATE, "traffic_res_product", productBean.getId(), productBean.getResId() ,"修改产品价格", productBean, proBeanDB));
		logOperatorBiz.insert(logList);
		
		productBean.setUserId(curUser.getEmployeeId());
		productBean.setUserName(curUser.getName());
		Map<String,Object> map = new HashMap<String,Object>();
		
		int saveNum = trafficResProductBiz.saveTrafficResProduct(productBean);
		map.put("success", saveNum);
		map.put("resId", productBean.getResId());
		return JSON.toJSONString(map);
	}
	
	/**
	 * 根据ID删除产品信息
	 * @param request
	 * @param id
	 * @return
	 */
	public String deleteResProduct(TrafficResProductDTO trafficResProductDTO){
		int id = trafficResProductDTO.getTrafficResProductId();
		PlatformEmployeePo curUser = trafficResProductDTO.getCurUser();
		//日志 
		List<LogOperator> logList = new ArrayList<LogOperator>();
		TrafficResProduct proBeanDB = trafficResProductBiz.loadTrafficResProduct(id);
		logList.add(LogFieldUtil.getLog_Instant(curUser.getBizId(), curUser.getName(),LOG_ACTION.DELETE, "traffic_res_product", id, proBeanDB.getResId() ,"删除产品", proBeanDB, null));
		logOperatorBiz.insert(logList);
				
		int delNum = trafficResProductBiz.delTrafficResProduct(id);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("success", delNum);
		return JSON.toJSONString(map);
	}
	
	/**
	 * 调整价格方法
	 * @param request
	 * @param id
	 * @param suggest_price_id
	 * @param adjust_uprodown_num
	 * @param price
	 * @return
	 */
	public String toUpdateProductPrice(ToUpdateProductPriceDTO toUpdateProductPriceDTO){
		String id = toUpdateProductPriceDTO.getId();
		String suggest_price_id = toUpdateProductPriceDTO.getSuggestPriceId();
		String adjust_uprodown_num = toUpdateProductPriceDTO.getAdjustUprodownNum();
		String price = toUpdateProductPriceDTO.getPrice();
		Integer resId = toUpdateProductPriceDTO.getResId();
		PlatformEmployeePo curUser = toUpdateProductPriceDTO.getCurUser();
		//统一调整价格日志
		String[] sourceStrArray = id.split(",");
		List<LogOperator> logList = new ArrayList<LogOperator>();
        for (int i = 0; i < sourceStrArray.length; i++) {
        	Integer pid = Integer.valueOf(sourceStrArray[i]);
    		
    		String brief = String.format("统一[%s]%s：%s", "0".equals(adjust_uprodown_num)?"上调":"下调", toUpdateProductPrice_GetFieldName(Integer.valueOf(suggest_price_id)), price);
    		logList.add(LogFieldUtil.getLog_Instant(curUser.getBizId(), curUser.getName(),LOG_ACTION.UPDATE, "traffic_res_product", pid, resId ,brief, null, null));
        }
        logOperatorBiz.insert(logList);
        
        
		int nums= trafficResProductBiz.loadByResProductId(id,suggest_price_id,adjust_uprodown_num,price);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("success", nums);
		return JSON.toJSONString(map);
	}
	private String toUpdateProductPrice_GetFieldName(Integer index){
		String ret = "";
		//"0" //更新上调成本价 1//更新上调零售价  2//更新上调同行返款  3//更新代理返款
		switch (index){
			case 0:
				ret = "成本价";
				break;
			case 1:
				ret = "零售价 ";
				break;
			case 2:
				ret = "同行返款";
				break;
			case 3:
				ret = "代理返款";
				break;
			default :
				ret = "<未定义>";
				break;
		}
		return ret;
	}
	
	
	public List<DicInfo> getListByTypeCode(int bizId) {
		List<DicInfo> brandList = dicBiz
				.getListByTypeCode(BasicConstants.CPXL_PP,bizId);
		return brandList;
	}
	
	public GetProductInfoResult getProductInfo(TrafficResProductDTO trafficResProductDTO) {
		GetProductInfoResult result = new GetProductInfoResult();
		Integer resId = trafficResProductDTO.getResId();
		Integer productId = trafficResProductDTO.getProductId();
		ProductInfo info = productInfoBiz.findProductInfoById(productId);
		//先判断选择的产品名称是否有重复，若已经存在产品，则不能添加该产品
		int productIdCount = 
				trafficResProductBiz.selectResProductNameCount(productId, resId);
		if(productIdCount == 0){
			result.setProductIdCount(productIdCount);
		}else {
			result.setProductIdCount(productIdCount);
		}
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("productIdCount", productIdCount);
		map.put("info", info);
		result.setMap(map);
		return result;
	}
	
	public ToSearchListResult toSearchList(ToSearchListDTO toSearchListDTO) {
		ToSearchListResult result = new ToSearchListResult();
		ProductInfo productInfo = toSearchListDTO.getProductInfo();
		String productName = toSearchListDTO.getProductName();
		String name = toSearchListDTO.getName();
		Integer page = toSearchListDTO.getPage();
		Integer pageSize = toSearchListDTO.getPageSize();
		int bizId = toSearchListDTO.getBizId();
		List<DicInfo> brandList = dicBiz
				.getListByTypeCode(BasicConstants.CPXL_PP,bizId);
		if(page==null){
			page=1;
		}
		PageBean<ProductInfo> pageBean = new PageBean<ProductInfo>();
		if(pageSize==null){
			pageBean.setPageSize(Constants.PAGESIZE);
		}else{
			pageBean.setPageSize(pageSize);
		}
		productInfo.setTravelDays(1);
		pageBean.setParameter(productInfo);
		pageBean.setPage(page);
		Map parameters=new HashMap();
		parameters.put("bizId", bizId);
		parameters.put("name", name);
		parameters.put("productName", productName);
	
		pageBean = productInfoBiz.findProductRoutes(pageBean, parameters);

		result.setBrandList(brandList);
		result.setPage(page);
		result.setPageBean(pageBean);
		return result;
	}
	
	public List<GroupOrderGuest> ticketInfo(Integer resId) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");     
		List<GroupOrderGuest> list=groupOrderGuestBiz.selectGuestTicketInfo(resId);
		List<BookingSupplier>	bs=bookingSupplierBiz.selectTicketInfo(resId);
		if(bs!=null&&bs.size()>0){
			for(GroupOrderGuest gog:list){
				for(BookingSupplier sb:bs){
					if (sb.getGuestIds().contains(Integer.toString(gog.getId()))) {
						gog.setUserName(sb.getUserName());
						gog.setTicketTime((sdf.format(sb.getCreateTime())));
					}
				}
			}
		}
		return list;
	}
	
	public TrafficRes addTicket(Integer resId) {
		return trafficResBiz.selectTrafficResAndLineInfoById1(resId);
	}
	
	/**
	 * 跳转到供应商编辑或新增页面
	 *
	 * @param model
	 * @param groupId
	 * @param bookingId
	 */
	public ToAddSupplierResult toAddSupplier(BookingSupplierDTO bookingSupplierDTO) {
		ToAddSupplierResult result = new ToAddSupplierResult();
		if (bookingSupplierDTO.getBookingId() != null) {
			BookingSupplier supplier = bookingSupplierBiz.selectByPrimaryKey(bookingSupplierDTO.getBookingId());
			
			List<BookingSupplierDetail> detailList = bookingSupplierDetailBiz.selectByPrimaryBookId(bookingSupplierDTO.getBookingId());
			result.setSupplier(supplier);
			result.setDetailList(detailList);
			
			if (supplier.getSupplierType().equals(Constants.SCENICSPOT)) {
				List<SupplierItem> supplierItems = supplierItemBiz.findSupplierItemBySupplierId(supplier.getSupplierId());
				result.setSupplierItems(supplierItems);
			}
		}
		
		if (bookingSupplierDTO.getGroupId() != null) {
			List<BookingGuide> bookingGuides = bookingGuideBiz.selectGuidesByGroupId(bookingSupplierDTO.getGroupId());
			result.setBookingGuides(bookingGuides);
		}
		
		//从字典中查询结算方式
		List<DicInfo> cashTypes = dicBiz.getListByTypeCode(BasicConstants.GYXX_JSFS, bookingSupplierDTO.getBizId());
		result.setCashTypes(cashTypes);
		return result;
	}
	
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
	public void saveBooking(SaveBookingDTO saveBookingDTO) {
		List<BookingSupplierDetail> details=saveBookingDTO.getDetails();
		BookingSupplier bookingSupplier = saveBookingDTO.getBookingSupplier();
		String bookingNo = null;
		if (bookingSupplier.getId() == null) {
			int count = bookingSupplierBiz.getBookingCountByTypeAndTime(bookingSupplier.getSupplierType());
			String code = saveBookingDTO.getCode();
			bookingNo = code + Constants.SUPPLIERSHORTCODEMAP.get(bookingSupplier.getSupplierType()) + new SimpleDateFormat("yyMMdd").format(new Date()) + (count + 100);
			bookingSupplier.setBookingNo(bookingNo);
		}
		Integer bookingId=bookingSupplierBiz.insertSelective(bookingSupplier);
		for(BookingSupplierDetail item:details){
			item.setBookingId(bookingId);
			if(item.getItemNum()>0){
				bookingSupplierDetailBiz.insertSelective(item);
			}
		}
	}
	
	/**
	 * 跳转至机位库存状态页面
	 * @param request
	 * @param resId
	 * @param model
	 * @return
	 */
	public ToUpdateResNumStockStateResult toUpdateResNumStockState(Integer id){
		ToUpdateResNumStockStateResult result = new ToUpdateResNumStockStateResult();
		TrafficRes trafficResBean = trafficResBiz.findTrafficResById(id);
		List<TrafficResProduct> resBindingProList = trafficResProductBiz.loadTrafficResProductInfo(id);
		TrafficResProduct sumResProBean = trafficResProductBiz.selectNumSoldCount(id);
		
		result.setResBindingProList(resBindingProList);
		result.setTrafficResBean(trafficResBean);
		result.setSumResProBean(sumResProBean);
		return result;
	}
	
	/**
	 * 保存机位库存信息
	 * @param request
	 * @param productBean
	 * @return
	 */
	public String toSaveResNumsSold(ToSaveResNumsSoldDTO toSaveResNumsSoldDTO){
		//日志 
		List<LogOperator> logList = new ArrayList<LogOperator>();
		PlatformEmployeePo curUser = toSaveResNumsSoldDTO.getCurUser();
		
				
		TrafficRes trafficResBean = trafficResBiz.findTrafficResById(Integer.valueOf(toSaveResNumsSoldDTO.getId()));
		Integer newId = 0;
		if(null != toSaveResNumsSoldDTO.getNumStock()){

			if(Integer.valueOf(toSaveResNumsSoldDTO.getNumStock()) != trafficResBean.getNumStock()){
				TrafficResStocklog trafficResStocklog=new TrafficResStocklog();
				trafficResStocklog.setAdjustAction(com.yimayhd.erpcenter.dal.product.constans.Constants.TRAFFICRES_STOCK_ACTION.STOCK.toString());
				trafficResStocklog.setAdjustNum(toSaveResNumsSoldDTO.getPoorNumStock());
				trafficResStocklog.setResId(toSaveResNumsSoldDTO.getId());
				trafficResStocklog.setAdjustTime(new Date());
				trafficResStocklog.setUserId(toSaveResNumsSoldDTO.getUserId());
				trafficResStocklog.setUserName(toSaveResNumsSoldDTO.getUserName());
				newId = trafficResBiz.insertTrafficResStocklog(trafficResStocklog);
				trafficResBiz.updateStockOrStockDisable(Integer.valueOf(toSaveResNumsSoldDTO.getId()));

				LogOperator lo = LogFieldUtil.getLog_Instant(curUser.getBizId(), curUser.getName(),LOG_ACTION.UPDATE, "traffic_res_stocklog", newId, Integer.valueOf(toSaveResNumsSoldDTO.getId()), String.format(" 调整【总量】：%s", toSaveResNumsSoldDTO.getPoorNumStock().toString()), null, null);

				if (lo != null) {
					logList.add(lo);
				}
			}
		}
		if(null != toSaveResNumsSoldDTO.getNumDisable()){
			if(Integer.valueOf(toSaveResNumsSoldDTO.getNumDisable()) != trafficResBean.getNumDisable()){
				TrafficResStocklog trafficResStocklog=new TrafficResStocklog();
				trafficResStocklog.setAdjustAction(com.yimayhd.erpcenter.dal.product.constans.Constants.TRAFFICRES_STOCK_ACTION.STOCK_DISABLE.toString());
				trafficResStocklog.setAdjustNum(toSaveResNumsSoldDTO.getPoorNumDisable());
				trafficResStocklog.setResId(Integer.valueOf(toSaveResNumsSoldDTO.getId()));
				trafficResStocklog.setAdjustTime(new Date());
				trafficResStocklog.setUserId(toSaveResNumsSoldDTO.getUserId());
				trafficResStocklog.setUserName(toSaveResNumsSoldDTO.getUserName());
				newId = trafficResBiz.insertTrafficResStocklog(trafficResStocklog);
				trafficResBiz.updateStockOrStockDisable(Integer.valueOf(toSaveResNumsSoldDTO.getId()));

				LogOperator lo = LogFieldUtil.getLog_Instant(curUser.getBizId(), curUser.getName(),LOG_ACTION.UPDATE, "traffic_res_stocklog", newId, Integer.valueOf(toSaveResNumsSoldDTO.getId()), String.format(" 调整【机动位】：%s", toSaveResNumsSoldDTO.getPoorNumStock().toString()), null, null);
				if (lo != null) {
					logList.add(lo);
				}
			}
		}
		
		logOperatorBiz.insert(logList);
		
		List<TrafficResProduct> resProBeanList = JSON.parseArray(toSaveResNumsSoldDTO.getProductList(), TrafficResProduct.class);
		for(TrafficResProduct resProBean: resProBeanList){
			trafficResProductBiz.updateResProductNumStock(
					Integer.valueOf(resProBean.getId()),Integer.valueOf(resProBean.getNumStock()));
		}
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("success", 1);
		return JSON.toJSONString(map);
	}

	@Override
	public PageBean selectAirTicketProfitList(PageBean pageBean) {
		pageBean=trafficResBiz.selectAirTicketProfitListPage(pageBean);
		List <TrafficRes> trafficRes=pageBean.getResult();
		for(TrafficRes item:trafficRes){
			GroupOrder go=groupOrderBiz.selectSumTotalByResId(item.getId());
			item.setSumTotal(go.getTotal());
			item.setSumCost(go.getCost());
		}
		return pageBean;
	}

	@Override
	public List<TrafficResProduct> loadTrafficResProductInfo(Integer resId) {
		return trafficResProductBiz.loadTrafficResProductInfo(resId);
	}

	@Override
	public TrafficAddResOrderResult airTicketProfit_table(TrafficResDTO trafficResDTO) {
		TrafficAddResOrderResult trafficAddResOrderResult = new TrafficAddResOrderResult();
		SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd");
		PageBean<TrafficRes> pageBean = new PageBean<TrafficRes>();
		if(trafficResDTO.getPage()==null){
			pageBean.setPage(1);
		}else{
			pageBean.setPage(trafficResDTO.getPage());
		}
		if(trafficResDTO.getPageSize()==null){
			pageBean.setPageSize(Constants.PAGESIZE);
		}else{
			pageBean.setPageSize(trafficResDTO.getPageSize());
		}
		pageBean.setPage(trafficResDTO.getPage());
		pageBean.setParameter(trafficResDTO.getPm());
		pageBean=trafficResBiz.selectAirTicketProfitListPage(pageBean);
		List <TrafficRes> trafficRes=pageBean.getResult();
		for(TrafficRes item:trafficRes){
			GroupOrder go=groupOrderBiz.selectSumTotalByResId(item.getId());
			item.setSumTotal(go.getTotal());
			item.setSumCost(go.getCost());
		}
		pageBean.setResult(trafficRes);
		trafficAddResOrderResult.setPageBean(pageBean);
		return trafficAddResOrderResult;
	}

	@Override
	public TrafficAddResOrderResult budgetDetail(TrafficResDTO trafficResDTO) {
		TrafficAddResOrderResult trafficAddResOrderResult = new TrafficAddResOrderResult();
		List<TrafficResProduct> lists=trafficResProductBiz.loadTrafficResProductInfo(trafficResDTO.getResId());
		trafficAddResOrderResult.setTrafficResProductLists(lists);
		return trafficAddResOrderResult;
	}

	@Override
	public TrafficAddResOrderResult constantlyDetail(TrafficResDTO trafficResDTO) {
		TrafficAddResOrderResult trafficAddResOrderResult = new TrafficAddResOrderResult();
		List<TourGroup> lists= tourGroupBiz.selectTotalByResId(trafficResDTO.getResId());
		trafficAddResOrderResult.setTourGroupLists(lists);
		return trafficAddResOrderResult;
	}

	@Override
	public TrafficAddResOrderResult resRoomExtrabed_table(TrafficResDTO trafficResDTO) {
		TrafficAddResOrderResult trafficAddResOrderResult = new TrafficAddResOrderResult();
		PageBean<GroupOrder> pageBean = new PageBean<GroupOrder>();
		if(trafficResDTO.getPage()==null){
			pageBean.setPage(1);
		}else{
			pageBean.setPage(trafficResDTO.getPage());
		}
		if(trafficResDTO.getPageSize()==null){
			pageBean.setPageSize(Constants.PAGESIZE);
		}else{
			pageBean.setPageSize(trafficResDTO.getPageSize());
		}
		pageBean.setPage(trafficResDTO.getPage());
		pageBean.setParameter(trafficResDTO.getGroupOrder());
		pageBean=groupOrderBiz.selectRoomExtrabedListPage(pageBean, trafficResDTO.getBizId());

		Map<String, Object> sumRoomExtrabed = groupOrderBiz.selectSumRoomExtrabed(pageBean, trafficResDTO.getBizId());
		trafficAddResOrderResult.setSumRoomExtrabed(sumRoomExtrabed);
		trafficAddResOrderResult.setPageBean(pageBean);
		return trafficAddResOrderResult;
	}


}
