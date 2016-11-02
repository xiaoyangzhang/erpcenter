package com.yimayhd.erpcenter.facade.dataanalysis.client.service;

import java.util.List;
import java.util.Map;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.dal.basic.po.DicInfo;
import com.yimayhd.erpcenter.facade.dataanalysis.client.query.AirTicketDetailQueriesDTO;
import com.yimayhd.erpcenter.facade.dataanalysis.client.query.DeliveryDetailListDTO;
import com.yimayhd.erpcenter.facade.dataanalysis.client.query.GetAgeListByProductDTO;
import com.yimayhd.erpcenter.facade.dataanalysis.client.query.GetAirTicketDetailDTO;
import com.yimayhd.erpcenter.facade.dataanalysis.client.query.GetEmployeeIdsDTO;
import com.yimayhd.erpcenter.facade.dataanalysis.client.query.GetNumAndOrderDTO;
import com.yimayhd.erpcenter.facade.dataanalysis.client.query.GetPaymentDataDTO;
import com.yimayhd.erpcenter.facade.dataanalysis.client.query.GroupOrderDTO;
import com.yimayhd.erpcenter.facade.dataanalysis.client.query.OpearteGroupListDTO;
import com.yimayhd.erpcenter.facade.dataanalysis.client.query.PaymentStaticPreviewDTO;
import com.yimayhd.erpcenter.facade.dataanalysis.client.query.ProductGuestConditionDTO;
import com.yimayhd.erpcenter.facade.dataanalysis.client.query.SaleOperatorExcelDTO;
import com.yimayhd.erpcenter.facade.dataanalysis.client.query.ShopInfoDetailDTO;
import com.yimayhd.erpcenter.facade.dataanalysis.client.query.ShopSelectListDTO;
import com.yimayhd.erpcenter.facade.dataanalysis.client.query.ToOperatorGroupStaticTableDTO;
import com.yimayhd.erpcenter.facade.dataanalysis.client.query.ToOrdersPreviewDTO;
import com.yimayhd.erpcenter.facade.dataanalysis.client.query.ToSaleOperatorOrderStaticTableDTO;
import com.yimayhd.erpcenter.facade.dataanalysis.client.query.ToSaleOperatorPreviewDTO;
import com.yimayhd.erpcenter.facade.dataanalysis.client.result.AirTicketDetailQueriesResult;
import com.yimayhd.erpcenter.facade.dataanalysis.client.result.AllProvinceResult;
import com.yimayhd.erpcenter.facade.dataanalysis.client.result.BookingSupplierDetailListResult;
import com.yimayhd.erpcenter.facade.dataanalysis.client.result.DataAnalysisWebResult;
import com.yimayhd.erpcenter.facade.dataanalysis.client.result.DeliveryDetailListResult;
import com.yimayhd.erpcenter.facade.dataanalysis.client.result.GetAgeListByProductResult;
import com.yimayhd.erpcenter.facade.dataanalysis.client.result.GetAirTicketDetailResult;
import com.yimayhd.erpcenter.facade.dataanalysis.client.result.GetLevelNameResult;
import com.yimayhd.erpcenter.facade.dataanalysis.client.result.GetNumAndOrderResult;
import com.yimayhd.erpcenter.facade.dataanalysis.client.result.GetOrdersResult;
import com.yimayhd.erpcenter.facade.dataanalysis.client.result.GetOrgAndUserTreeJsonStrResult;
import com.yimayhd.erpcenter.facade.dataanalysis.client.result.GetPaymentDataResult;
import com.yimayhd.erpcenter.facade.dataanalysis.client.result.HotelQueriesResult;
import com.yimayhd.erpcenter.facade.dataanalysis.client.result.OpearteGroupListResult;
import com.yimayhd.erpcenter.facade.dataanalysis.client.result.PaymentStaticPreviewResult;
import com.yimayhd.erpcenter.facade.dataanalysis.client.result.RestaurantQueriesResult;
import com.yimayhd.erpcenter.facade.dataanalysis.client.result.SaleOperatorExcelResult;
import com.yimayhd.erpcenter.facade.dataanalysis.client.result.ShopDetailListResult;
import com.yimayhd.erpcenter.facade.dataanalysis.client.result.ShopInfoDetailResult;
import com.yimayhd.erpcenter.facade.dataanalysis.client.result.ShopSelectListResult;
import com.yimayhd.erpcenter.facade.dataanalysis.client.result.ToOperatorGroupStaticTableResult;
import com.yimayhd.erpcenter.facade.dataanalysis.client.result.ToOrdersPreviewResult;
import com.yimayhd.erpcenter.facade.dataanalysis.client.result.ToPaymentPreviewResult;
import com.yimayhd.erpcenter.facade.dataanalysis.client.result.ToSaleOperatorListResult;
import com.yimayhd.erpcenter.facade.dataanalysis.client.result.ToSaleOperatorOrderStaticTableResult;
import com.yimayhd.erpcenter.facade.dataanalysis.client.result.ToSaleOperatorPreviewResult;
import com.yimayhd.erpcenter.facade.dataanalysis.client.result.ToSaleOperatorTableResult;
import com.yimayhd.erpcenter.facade.dataanalysis.client.result.TranportListResult;

/**
 * 
 * @author gaotingping
 * 2016年10月31日
 */
public interface DataAnalysisFacade {

	GetOrgAndUserTreeJsonStrResult getOrgAndUserTreeJsonStr(Integer bizId);

	ToSaleOperatorPreviewResult toSaleOperatorPreview(ToSaleOperatorPreviewDTO toSaleOperatorPreviewDTO);

	ToSaleOperatorListResult toSaleOperatorList();

	ToSaleOperatorTableResult toSaleOperatorTable(ToSaleOperatorPreviewDTO toSaleOperatorPreviewDTO);

	GetLevelNameResult getLevelName(String code);

	SaleOperatorExcelResult saleOperatorExcel(SaleOperatorExcelDTO saleOperatorExcelDTO);

	ToSaleOperatorOrderStaticTableResult toSaleOperatorOrderStaticTable(
			ToSaleOperatorOrderStaticTableDTO toSaleOperatorOrderStaticTableDTO);

	ToOperatorGroupStaticTableResult toOperatorGroupStaticTable(
			ToOperatorGroupStaticTableDTO toOperatorGroupStaticTableDTO);

	AllProvinceResult getAllProvince();

	ToOrdersPreviewResult toOrdersPreview(ToOrdersPreviewDTO toOrdersPreviewDTO);

	ToPaymentPreviewResult toPaymentPreview(ToOrdersPreviewDTO toOrdersPreviewDTO);

	GetOrdersResult getOrders(ToOrdersPreviewDTO toOrdersPreviewDTO);

	GetPaymentDataResult getPaymentData(GetPaymentDataDTO getPaymentDataDTO);

	ShopInfoDetailResult shopInfoDetail(ShopInfoDetailDTO shopInfoDetailDTO);

	ShopDetailListResult shopDetailList(Integer id);

	ShopSelectListResult shopSelectList(ShopSelectListDTO shopSelectListDTO);

	String getEmployeeIds(GetEmployeeIdsDTO getEmployeeIdsDTO);

	PaymentStaticPreviewResult paymentStaticPreview(PaymentStaticPreviewDTO paymentStaticPreviewDTO);

	PaymentStaticPreviewResult paymentStaticExport(PaymentStaticPreviewDTO paymentStaticPreviewDTO);

	RestaurantQueriesResult restaurantQueries(Integer bizId);

	RestaurantQueriesResult restaurantBooking(Integer bizId);

	RestaurantQueriesResult restaurantJSFS(Integer bizId);

	RestaurantQueriesResult restaurantDetailList(Integer bizId);

	HotelQueriesResult hotelQueries(Integer bizId);

	RestaurantQueriesResult hotelBookingQueries(Integer bizId);

	RestaurantQueriesResult hotelJSFS(Integer bizId);

	RestaurantQueriesResult hoteldetailQueries(Integer bizId);

	RestaurantQueriesResult fleetQueries(Integer bizId);

	RestaurantQueriesResult fleetDetailList(Integer bizId);

	RestaurantQueriesResult fleetJSFSList(Integer bizId);

	RestaurantQueriesResult entertainmentDetailQueries(Integer bizId);

	RestaurantQueriesResult sightList(Integer bizId);

	RestaurantQueriesResult sightBookingList(Integer bizId);

	RestaurantQueriesResult sightJSFS(Integer bizId);

	RestaurantQueriesResult sightDetailList(Integer bizId);

	RestaurantQueriesResult airTicketQueries(Integer bizId);

	RestaurantQueriesResult airTicketBookingQueries(Integer bizId);

	RestaurantQueriesResult airTicketJSFS(Integer bizId);

	AirTicketDetailQueriesResult airTicketDetailQueries(AirTicketDetailQueriesDTO airTicketDetailQueriesDTO);

	RestaurantQueriesResult trainTicketQueries(Integer bizId);

	RestaurantQueriesResult trainTicketBookingQueries(Integer bizId);

	RestaurantQueriesResult trainTicketJSFS(Integer bizId);

	AirTicketDetailQueriesResult trainTicketDetailQueries(AirTicketDetailQueriesDTO airTicketDetailQueriesDTO);

	RestaurantQueriesResult insuranceQueries(Integer bizId);

	RestaurantQueriesResult insuranceDetailQueries(Integer bizId);

	RestaurantQueriesResult insuranceBookingQueries(Integer bizId);

	RestaurantQueriesResult insuranceJSFS(Integer bizId);

	RestaurantQueriesResult incomeQueries(Integer bizId);

	RestaurantQueriesResult incomeDetailQueries(Integer bizId);

	RestaurantQueriesResult outcomeQueries(Integer bizId);

	RestaurantQueriesResult outcomeDetailQueries(Integer bizId);

	DeliveryDetailListResult deliveryDetailList(DeliveryDetailListDTO deliveryDetailListDTO);

	GetAirTicketDetailResult getAirTicketDetail(GetAirTicketDetailDTO getAirTicketDetailDTO);

	GetAirTicketDetailResult getTrainTicketDetail(GetAirTicketDetailDTO getAirTicketDetailDTO);

	GetAgeListByProductResult getAgeListByProduct(GetAgeListByProductDTO getAgeListByProductDTO);

	BookingSupplierDetailListResult getBookingSupplierDetailList(Integer id);

	TranportListResult tranportList(Integer bizId);

	OpearteGroupListResult opearteGroupList(OpearteGroupListDTO opearteGroupListDTO);

	GetNumAndOrderResult getNumAndOrder(GetNumAndOrderDTO getNumAndOrderDTO);

	GetNumAndOrderResult getSupplierOrder(GetNumAndOrderDTO getNumAndOrderDTO);

	GetNumAndOrderResult getSupplierDetails(GetNumAndOrderDTO getNumAndOrderDTO);

	GetNumAndOrderResult getDetail2(GetNumAndOrderDTO getNumAndOrderDTO);
	
	
	
	

	

	public DataAnalysisWebResult<Map<String, Object>> groupBookingList(
			ShopSelectListDTO shopSelectListDTO);


	public DataAnalysisWebResult<PageBean> groupInfoList(ShopSelectListDTO shopSelectListDTO);

	/**
	 * 子公司未收债务查询页面
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	public DataAnalysisWebResult<Map<String, Object>> toSubsidiaryDebt();

	public DataAnalysisWebResult<Map<String, Object>> toSubsidiaryDebt(String sl, String ssl,
			String rp, Integer page, Integer pageSize, String svc,
			ShopSelectListDTO shopSelectListDTO,Map<String, Object> pms);

	

	/* 购物统计-产品 */
	public DataAnalysisWebResult<List<DicInfo>> toProductList(String typecode,Integer bizId);




//	/**
//	 * 根据产品+旅行社统计年龄段
//	 * 
//	 * @return
//	 */
//
//	@RequestMapping("toAgeListByProductAndAgency.htm")
//	public String toAgeListByProductAndAgency(HttpServletRequest request,
//			HttpServletResponse response, ModelMap model) {
//		// model.addAttribute("bizId", WebUtils.getCurBizId(request));
//		// Integer bizId = WebUtils.getCurBizId(request);
//		// getOrgAndUserTreeJsonStr(model, bizId);
//		return "queries/byAge/ageListByProductAndAgency";
//
//	}

//
//	/**
//	 * 线路团量分布
//	 * 
//	 * @param request
//	 * @param response
//	 * @param model
//	 * @param condition
//	 * @return
//	 */
//	@RequestMapping("productGuestStatics.htm")
//	public String productGuestStaticsList(HttpServletRequest request,
//			HttpServletResponse response, ModelMap model) {
//		String startDate = DateUtils.getMonthFirstDay();
//		String endDate = DateUtils.getMonthLastDay();
//		model.addAttribute("startDate", startDate);
//		model.addAttribute("endDate", endDate);
//		// Integer bizId = WebUtils.getCurBizId(request);
//		// getOrgAndUserTreeJsonStr(model, bizId);
//		return "queries/order/product-guest";
//	}

	public DataAnalysisWebResult<String> productGuestStatics(ProductGuestConditionDTO productGuestConditionDTO);

	

	public DataAnalysisWebResult<Map<String, Object>> queryGroupNumber(GroupOrderDTO groupOrderDTO, Integer type, Integer dataType);

	public DataAnalysisWebResult<Map<String, Object>> expGroupNumber(GroupOrderDTO groupOrderDTO);

	/**
	 * 客源分布
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
//	public String guestSourceStaticsList(HttpServletRequest request,
//			HttpServletResponse response, ModelMap model) {
//		String startDate = DateUtils.getMonthFirstDay();
//		String endDate = DateUtils.getMonthLastDay();
//		model.addAttribute("startDate", startDate);
//		model.addAttribute("endDate", endDate);
//		// Integer bizId = WebUtils.getCurBizId(request);
//		// getOrgAndUserTreeJsonStr(model, bizId);
//		return "queries/order/guest-source";
//	}

	public DataAnalysisWebResult<String> guestSourceStatics(ProductGuestConditionDTO productGuestConditionDTO);



	/**
	 * 购物项目统计
	 * 
	 * @param request
	 * @param model
	 * @param guide
	 * @param pageSize
	 * @param page
	 * @return
	 */
	public DataAnalysisWebResult<PageBean> toBookingShopList(PageBean pageBean,Integer bizId);

	/**
	 * 酒店订单明细预览页面
	 * 
	 * @param request
	 * @param reponse
	 * @param model
	 * @param sl
	 * @param ssl
	 * @param rp
	 * @param page
	 * @param pageSize
	 * @param svc
	 * @return
	 */
//	@RequestMapping(value = "hotelDetailPreview.htm")
//	public String hotelDetailPreview(HttpServletRequest request,
//			HttpServletResponse reponse, ModelMap model, String sl, String ssl,
//			String rp, Integer page, Integer pageSize, String svc) {
//		PageBean pb = commonQuery(request, model, sl, page, 10000, svc);
//		String imgPath = bizSettingCommon.getMyBizLogo(request);
//		model.addAttribute("imgPath", imgPath);
//
//		model.addAttribute("printName", WebUtils.getCurUser(request).getName());
//		return "/queries/hotel/hotelDetailPreview";
//	}

//	/**
//	 * 酒店导出excel
//	 * 
//	 * @param request
//	 * @param response
//	 * @param model
//	 * @param sl
//	 * @param ssl
//	 * @param rp
//	 * @param page
//	 * @param pageSize
//	 * @param svc
//	 */
//	public void exportExcel(String sl,
//			String ssl, String rp, Integer page, Integer pageSize, String svc) {
//		PageBean pb = commonQuery(request, model, sl, page, 10000, svc);
//
//		String path = "";
//		BigDecimal total = new BigDecimal(0);
//		BigDecimal totalNum = new BigDecimal(0);
//		BigDecimal totalNumMinus = new BigDecimal(0);
//
//		try {
//			String url = request.getSession().getServletContext()
//					.getRealPath("/template/excel/hotelBusinessDetail.xlsx");
//			FileInputStream input = new FileInputStream(new File(url)); // 读取的文件路径
//			XSSFWorkbook wb = new XSSFWorkbook(new BufferedInputStream(input));
//			CellStyle cellStyle = wb.createCellStyle();
//			cellStyle.setBorderBottom(CellStyle.BORDER_THIN); // 下边框
//			cellStyle.setBorderLeft(CellStyle.BORDER_THIN);// 左边框
//			cellStyle.setBorderTop(CellStyle.BORDER_THIN);// 上边框
//			cellStyle.setBorderRight(CellStyle.BORDER_THIN);// 右边框
//			cellStyle.setAlignment(CellStyle.ALIGN_CENTER); // 居中
//
//			CellStyle styleLeft = wb.createCellStyle();
//			styleLeft.setBorderBottom(CellStyle.BORDER_THIN); // 下边框
//			styleLeft.setBorderLeft(CellStyle.BORDER_THIN);// 左边框
//			styleLeft.setBorderTop(CellStyle.BORDER_THIN);// 上边框
//			styleLeft.setBorderRight(CellStyle.BORDER_THIN);// 右边框
//			styleLeft.setAlignment(CellStyle.ALIGN_LEFT); // 居左
//
//			CellStyle styleRight = wb.createCellStyle();
//			styleRight.setBorderBottom(CellStyle.BORDER_THIN); // 下边框
//			styleRight.setBorderLeft(CellStyle.BORDER_THIN);// 左边框
//			styleRight.setBorderTop(CellStyle.BORDER_THIN);// 上边框
//			styleRight.setBorderRight(CellStyle.BORDER_THIN);// 右边框
//			styleRight.setAlignment(CellStyle.ALIGN_RIGHT); // 居右
//			Sheet sheet = wb.getSheetAt(0); // 获取到第一个sheet
//			Row row = null;
//			Cell cc = null;
//			// 遍历集合数据，产生数据行
//			// Iterator<GroupOrder> it = orders.iterator();
//			List<Map<String, Object>> result = pb.getResult();
//			int index = 0;
//			for (Map<String, Object> map : result) {
//				// GroupOrder order = it.next() ;
//				row = sheet.createRow(index + 2);
//				cc = row.createCell(0);
//				cc.setCellValue(index + 1);
//				cc.setCellStyle(cellStyle);
//				cc = row.createCell(1);
//				cc.setCellValue(map.get("groupCode") + "");
//				cc.setCellStyle(styleLeft);
//				cc = row.createCell(2);
//				cc.setCellValue(map.get("totalAdult") + "大"
//						+ map.get("totalChild") + "小" + map.get("totalGuide")
//						+ "陪");
//				cc.setCellStyle(styleLeft);
//				cc = row.createCell(3);
//				cc.setCellValue(map.get("supplierName") + "");
//				cc.setCellStyle(styleLeft);
//				cc = row.createCell(4);
//				cc.setCellValue(map.get("bookingGuideInfo") + "");
//				cc.setCellStyle(styleLeft);
//				cc = row.createCell(5);
//				cc.setCellValue(map.get("operatorName") + "");
//				cc.setCellStyle(cellStyle);
//				cc = row.createCell(6);
//				cc.setCellValue(map.get("itemDate") + "");
//				cc.setCellStyle(cellStyle);
//				cc = row.createCell(7);
//				cc.setCellValue(map.get("type1Name") + "");
//				cc.setCellStyle(cellStyle);
//				cc = row.createCell(8);
//				cc.setCellValue(map.get("itemBrief") == null ? "" : map
//						.get("itemBrief") + "");
//				cc.setCellStyle(cellStyle);
//				cc = row.createCell(9);
//				BigDecimal itemNum = new BigDecimal(0);
//				itemNum = itemNum.add((BigDecimal) map.get("itemNum"));
//				cc.setCellValue(itemNum.intValue());
//				cc.setCellStyle(cellStyle);
//				cc = row.createCell(10);
//				BigDecimal itemPrice = new BigDecimal(0);
//				itemPrice = itemPrice.add((BigDecimal) map.get("itemPrice"));
//				cc.setCellValue(itemPrice.intValue());
//				cc.setCellStyle(cellStyle);
//				cc = row.createCell(11);
//				BigDecimal itemNumMinus = new BigDecimal(0);
//				itemNumMinus = itemNumMinus.add((BigDecimal) map
//						.get("itemNumMinus"));
//				cc.setCellValue(itemNumMinus.doubleValue()); // 团款
//				cc.setCellStyle(cellStyle);
//				cc = row.createCell(12);
//				cc.setCellValue(map.get("cashType") == null ? "" : map
//						.get("cashType") + "");// 已收
//				cc.setCellStyle(cellStyle);
//				cc = row.createCell(13);
//				BigDecimal itemTotal = new BigDecimal(0);
//				itemTotal = itemTotal.add((BigDecimal) map.get("itemTotal"));
//				cc.setCellValue(itemTotal.doubleValue());// 未收
//				cc.setCellStyle(cellStyle);
//				index++;
//				total = total.add((BigDecimal) map.get("itemTotal"));
//				totalNum = totalNum.add((BigDecimal) map.get("itemNum"));
//				totalNumMinus = totalNumMinus.add((BigDecimal) map
//						.get("itemNumMinus"));
//			}
//
//			row = sheet.createRow(pb.getResult().size() + 2); // 加合计行
//			cc = row.createCell(0);
//			cc.setCellStyle(styleRight);
//			cc = row.createCell(1);
//			cc.setCellStyle(styleRight);
//			cc = row.createCell(2);
//			cc.setCellStyle(styleRight);
//			cc = row.createCell(3);
//			cc.setCellStyle(styleRight);
//			cc = row.createCell(4);
//			cc.setCellStyle(styleRight);
//			cc = row.createCell(5);
//			cc.setCellStyle(styleRight);
//			cc = row.createCell(6);
//			cc.setCellStyle(styleRight);
//			cc = row.createCell(7);
//			cc.setCellValue("合计：");
//			cc.setCellStyle(styleRight);
//			cc = row.createCell(8);
//			cc.setCellStyle(cellStyle);
//			cc = row.createCell(9);
//			cc.setCellValue(totalNum.intValue());
//			cc.setCellStyle(styleRight);
//			cc = row.createCell(10);
//			cc.setCellStyle(styleRight);
//			cc = row.createCell(11);
//			cc.setCellValue(totalNumMinus.doubleValue());
//			cc.setCellStyle(cellStyle);
//			cc = row.createCell(12);
//			cc.setCellStyle(cellStyle);
//			cc = row.createCell(13);
//			cc.setCellValue(total.doubleValue());
//			cc.setCellStyle(cellStyle);
//
//			CellRangeAddress region = new CellRangeAddress(pb.getResult()
//					.size() + 3, pb.getResult().size() + 4, 0, 13);
//			sheet.addMergedRegion(region);
//			// row = sheet.getRow(orders.size()+3); //打印信息
//			row = sheet.createRow(pb.getResult().size() + 3);
//			cc = row.createCell(0);
//			cc.setCellValue("打印人：" + WebUtils.getCurUser(request).getName()
//					+ " 打印时间："
//					+ DateUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
//			path = request.getSession().getServletContext().getRealPath("/")
//					+ "/download/" + System.currentTimeMillis() + ".xlsx";
//			FileOutputStream out = new FileOutputStream(path);
//			wb.write(out);
//			out.close();
//			wb.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		download2(path, request, response);
//		// return "";
//	}

//	private void download2(String path, HttpServletRequest request,
//			HttpServletResponse response) {
//		try {
//			// path是指欲下载的文件的路径。
//			File file = new File(path);
//			// 取得文件名。
//			String fileName = "";
//			try {
//				fileName = new String("业务统计明细.xlsx".getBytes("UTF-8"),
//						"iso-8859-1");
//			} catch (UnsupportedEncodingException e) {
//				e.printStackTrace();
//			}
//			// 以流的形式下载文件。
//			InputStream fis = new BufferedInputStream(new FileInputStream(path));
//			byte[] buffer = new byte[fis.available()];
//			fis.read(buffer);
//			fis.close();
//			// 清空response
//			response.reset();
//
//			/*
//			 * //解决IE浏览器下下载文件名乱码问题 if
//			 * (request.getHeader("USER-AGENT").indexOf("msie") > -1){ fileName
//			 * = new URLEncoder().encode(fileName) ; }
//			 */
//			// 设置response的Header
//			response.addHeader("Content-Disposition", "attachment;filename="
//					+ fileName);
//			response.addHeader("Content-Length", "" + file.length());
//			OutputStream toClient = new BufferedOutputStream(
//					response.getOutputStream());
//			response.setContentType("application/vnd.ms-excel;charset=gb2312");
//			toClient.write(buffer);
//			toClient.flush();
//			toClient.close();
//			file.delete();
//		} catch (IOException ex) {
//			ex.printStackTrace();
//		}
//	}

	/**
	 * 餐厅、门票、保险预览页面
	 * 
	 * @param request
	 * @param reponse
	 * @param model
	 * @param sl
	 * @param ssl
	 * @param rp
	 * @param page
	 * @param pageSize
	 * @param svc
	 * @return
	 */

//	@RequestMapping(value = "detailPreview.htm")
//	public String detailPreview(HttpServletRequest request,
//			HttpServletResponse reponse, ModelMap model, String sl, String ssl,
//			String rp, Integer page, Integer pageSize, String svc) {
//		PageBean pb = commonQuery(request, model, sl, page, 10000, svc);
//		String imgPath = bizSettingCommon.getMyBizLogo(request);
//		model.addAttribute("imgPath", imgPath);
//
//		model.addAttribute("printName", WebUtils.getCurUser(request).getName());
//		return "/queries/detailPreview";
//	}

	/**
	 * 餐厅、门票、保险导出excel
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @param sl
	 * @param ssl
	 * @param rp
	 * @param page
	 * @param pageSize
	 * @param svc
	 */
//	@RequestMapping("exportExcel2.htm")
//	public void exportExcel2(HttpServletRequest request,
//			HttpServletResponse response, ModelMap model, String sl,
//			String ssl, String rp, Integer page, Integer pageSize, String svc) {
//		PageBean pb = commonQuery(request, model, sl, page, 10000, svc);
//
//		String path = "";
//		BigDecimal total = new BigDecimal(0);
//		BigDecimal totalNum = new BigDecimal(0);
//		BigDecimal totalNumMinus = new BigDecimal(0);
//		try {
//			String url = request.getSession().getServletContext()
//					.getRealPath("/template/excel/businessDetail.xlsx");
//			FileInputStream input = new FileInputStream(new File(url)); // 读取的文件路径
//			XSSFWorkbook wb = new XSSFWorkbook(new BufferedInputStream(input));
//			CellStyle cellStyle = wb.createCellStyle();
//			cellStyle.setBorderBottom(CellStyle.BORDER_THIN); // 下边框
//			cellStyle.setBorderLeft(CellStyle.BORDER_THIN);// 左边框
//			cellStyle.setBorderTop(CellStyle.BORDER_THIN);// 上边框
//			cellStyle.setBorderRight(CellStyle.BORDER_THIN);// 右边框
//			cellStyle.setAlignment(CellStyle.ALIGN_CENTER); // 居中
//
//			CellStyle styleLeft = wb.createCellStyle();
//			styleLeft.setBorderBottom(CellStyle.BORDER_THIN); // 下边框
//			styleLeft.setBorderLeft(CellStyle.BORDER_THIN);// 左边框
//			styleLeft.setBorderTop(CellStyle.BORDER_THIN);// 上边框
//			styleLeft.setBorderRight(CellStyle.BORDER_THIN);// 右边框
//			styleLeft.setAlignment(CellStyle.ALIGN_LEFT); // 居左
//
//			CellStyle styleRight = wb.createCellStyle();
//			styleRight.setBorderBottom(CellStyle.BORDER_THIN); // 下边框
//			styleRight.setBorderLeft(CellStyle.BORDER_THIN);// 左边框
//			styleRight.setBorderTop(CellStyle.BORDER_THIN);// 上边框
//			styleRight.setBorderRight(CellStyle.BORDER_THIN);// 右边框
//			styleRight.setAlignment(CellStyle.ALIGN_RIGHT); // 居右
//			Sheet sheet = wb.getSheetAt(0); // 获取到第一个sheet
//			Row row = null;
//			Cell cc = null;
//			// 遍历集合数据，产生数据行
//			// Iterator<GroupOrder> it = orders.iterator();
//			List<Map<String, Object>> result = pb.getResult();
//			int index = 0;
//			for (Map<String, Object> map : result) {
//				// GroupOrder order = it.next() ;
//				row = sheet.createRow(index + 2);
//				cc = row.createCell(0);
//				cc.setCellValue(index + 1);
//				cc.setCellStyle(cellStyle);
//				cc = row.createCell(1);
//				cc.setCellValue(map.get("groupCode") + "");
//				cc.setCellStyle(styleLeft);
//				cc = row.createCell(2);
//				cc.setCellValue(map.get("totalAdult") + "大"
//						+ map.get("totalChild") + "小" + map.get("totalGuide")
//						+ "陪");
//				cc.setCellStyle(styleLeft);
//				cc = row.createCell(3);
//				cc.setCellValue(map.get("supplierName") + "");
//				cc.setCellStyle(styleLeft);
//
//				cc = row.createCell(4);
//				cc.setCellValue(map.get("operatorName") + "");
//				cc.setCellStyle(cellStyle);
//				cc = row.createCell(5);
//				cc.setCellValue(map.get("itemDate") + "");
//				cc.setCellStyle(cellStyle);
//				cc = row.createCell(6);
//				cc.setCellValue(map.get("type1Name") == null ? "" : map
//						.get("type1Name") + "");
//				cc.setCellStyle(cellStyle);
//				cc = row.createCell(7);
//				cc.setCellValue(map.get("itemBrief") == null ? "" : map
//						.get("itemBrief") + "");
//				cc.setCellStyle(cellStyle);
//				cc = row.createCell(8);
//				BigDecimal itemNum = new BigDecimal(0);
//				itemNum = itemNum.add((BigDecimal) map.get("itemNum"));
//				cc.setCellValue(itemNum.intValue());
//				cc.setCellStyle(cellStyle);
//				cc = row.createCell(9);
//				BigDecimal itemPrice = new BigDecimal(0);
//				itemPrice = itemPrice.add((BigDecimal) map.get("itemPrice"));
//				cc.setCellValue(itemPrice.intValue());
//				cc.setCellStyle(cellStyle);
//				cc = row.createCell(10);
//				BigDecimal itemNumMinus = new BigDecimal(0);
//				itemNumMinus = itemNumMinus.add((BigDecimal) map
//						.get("itemNumMinus"));
//				cc.setCellValue(itemNumMinus.doubleValue()); // 团款
//				cc.setCellStyle(cellStyle);
//				cc = row.createCell(11);
//				cc.setCellValue(map.get("cashType") == null ? "" : map
//						.get("cashType") + "");// 已收
//				cc.setCellStyle(cellStyle);
//				cc = row.createCell(12);
//				BigDecimal itemTotal = new BigDecimal(0);
//				itemTotal = itemTotal.add((BigDecimal) map.get("itemTotal"));
//				cc.setCellValue(itemTotal.doubleValue());// 未收
//				cc.setCellStyle(cellStyle);
//				index++;
//				total = total.add((BigDecimal) map.get("itemTotal"));
//				totalNum = totalNum.add((BigDecimal) map.get("itemNum"));
//				totalNumMinus = totalNumMinus.add((BigDecimal) map
//						.get("itemNumMinus"));
//			}
//			row = sheet.createRow(pb.getResult().size() + 2); // 加合计行
//			cc = row.createCell(0);
//			cc.setCellStyle(styleRight);
//			cc = row.createCell(1);
//			cc.setCellStyle(styleRight);
//			cc = row.createCell(2);
//			cc.setCellStyle(styleRight);
//			cc = row.createCell(3);
//			cc.setCellStyle(styleRight);
//			cc = row.createCell(4);
//			cc.setCellStyle(styleRight);
//			cc = row.createCell(5);
//			cc.setCellStyle(styleRight);
//			cc = row.createCell(6);
//			cc.setCellStyle(styleRight);
//			cc = row.createCell(7);
//			cc.setCellValue("合计：");
//			cc.setCellStyle(styleRight);
//			cc = row.createCell(8);
//			cc.setCellValue(totalNum.intValue());
//			cc.setCellStyle(cellStyle);
//			cc = row.createCell(9);
//			cc.setCellStyle(styleRight);
//			cc = row.createCell(10);
//			cc.setCellValue(totalNumMinus.doubleValue());
//			cc.setCellStyle(styleRight);
//			cc = row.createCell(11);
//			cc.setCellStyle(cellStyle);
//			/*
//			 * cc = row.createCell(12); cc.setCellStyle(cellStyle);
//			 */
//			cc = row.createCell(12);
//			cc.setCellValue(total.doubleValue());
//			cc.setCellStyle(cellStyle);
//
//			CellRangeAddress region = new CellRangeAddress(pb.getResult()
//					.size() + 3, pb.getResult().size() + 4, 0, 12);
//			sheet.addMergedRegion(region);
//			// row = sheet.getRow(orders.size()+3); //打印信息
//			row = sheet.createRow(pb.getResult().size() + 3);
//			cc = row.createCell(0);
//			cc.setCellValue("打印人：" + WebUtils.getCurUser(request).getName()
//					+ " 打印时间："
//					+ DateUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
//			path = request.getSession().getServletContext().getRealPath("/")
//					+ "/download/" + System.currentTimeMillis() + ".xlsx";
//			FileOutputStream out = new FileOutputStream(path);
//			wb.write(out);
//			out.close();
//			wb.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		download2(path, request, response);
//		// return "";
//	}

	/**
	 * 机票、火车票预览页面
	 * 
	 * @param request
	 * @param reponse
	 * @param model
	 * @param sl
	 * @param ssl
	 * @param rp
	 * @param page
	 * @param pageSize
	 * @param svc
	 * @return
	 */
//	@RequestMapping(value = "ticketDetailPreview.htm")
//	public String ticketDetailPreview(HttpServletRequest request,
//			HttpServletResponse reponse, ModelMap model, String sl, String ssl,
//			String rp, Integer page, Integer pageSize, String svc) {
//		PageBean pb = commonQuery(request, model, sl, page, 10000, svc);
//		String imgPath = bizSettingCommon.getMyBizLogo(request);
//		model.addAttribute("imgPath", imgPath);
//
//		model.addAttribute("printName", WebUtils.getCurUser(request).getName());
//		return "/queries/ticketDetailPreview";
//	}

	/**
	 * 机票、火车票导出excel
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @param sl
	 * @param ssl
	 * @param rp
	 * @param page
	 * @param pageSize
	 * @param svc
	 */
//	@RequestMapping("exportExcel3.htm")
//	public void exportExcel3(HttpServletRequest request,
//			HttpServletResponse response, ModelMap model, String sl,
//			String ssl, String rp, Integer page, Integer pageSize, String svc) {
//		PageBean pb = commonQuery(request, model, sl, page, 10000, svc);
//
//		String path = "";
//		BigDecimal total = new BigDecimal(0);
//		BigDecimal totalNum = new BigDecimal(0);
//		BigDecimal totalNumMinus = new BigDecimal(0);
//		try {
//			String url = request.getSession().getServletContext()
//					.getRealPath("/template/excel/ticketBusinessDetail.xlsx");
//			FileInputStream input = new FileInputStream(new File(url)); // 读取的文件路径
//			XSSFWorkbook wb = new XSSFWorkbook(new BufferedInputStream(input));
//			CellStyle cellStyle = wb.createCellStyle();
//			cellStyle.setBorderBottom(CellStyle.BORDER_THIN); // 下边框
//			cellStyle.setBorderLeft(CellStyle.BORDER_THIN);// 左边框
//			cellStyle.setBorderTop(CellStyle.BORDER_THIN);// 上边框
//			cellStyle.setBorderRight(CellStyle.BORDER_THIN);// 右边框
//			cellStyle.setAlignment(CellStyle.ALIGN_CENTER); // 居中
//
//			CellStyle styleLeft = wb.createCellStyle();
//			styleLeft.setBorderBottom(CellStyle.BORDER_THIN); // 下边框
//			styleLeft.setBorderLeft(CellStyle.BORDER_THIN);// 左边框
//			styleLeft.setBorderTop(CellStyle.BORDER_THIN);// 上边框
//			styleLeft.setBorderRight(CellStyle.BORDER_THIN);// 右边框
//			styleLeft.setAlignment(CellStyle.ALIGN_LEFT); // 居左
//
//			CellStyle styleRight = wb.createCellStyle();
//			styleRight.setBorderBottom(CellStyle.BORDER_THIN); // 下边框
//			styleRight.setBorderLeft(CellStyle.BORDER_THIN);// 左边框
//			styleRight.setBorderTop(CellStyle.BORDER_THIN);// 上边框
//			styleRight.setBorderRight(CellStyle.BORDER_THIN);// 右边框
//			styleRight.setAlignment(CellStyle.ALIGN_RIGHT); // 居右
//			Sheet sheet = wb.getSheetAt(0); // 获取到第一个sheet
//			Row row = null;
//			Cell cc = null;
//			// 遍历集合数据，产生数据行
//			// Iterator<GroupOrder> it = orders.iterator();
//			List<Map<String, Object>> result = pb.getResult();
//			int index = 0;
//			for (Map<String, Object> map : result) {
//				// GroupOrder order = it.next() ;
//				row = sheet.createRow(index + 2);
//				cc = row.createCell(0);
//				cc.setCellValue(index + 1);
//				cc.setCellStyle(cellStyle);
//				cc = row.createCell(1);
//				cc.setCellValue(map.get("groupCode") + "");
//				cc.setCellStyle(styleLeft);
//				cc = row.createCell(2);
//				cc.setCellValue(map.get("totalAdult") + "大"
//						+ map.get("totalChild") + "小" + map.get("totalGuide")
//						+ "陪");
//				cc.setCellStyle(styleLeft);
//				cc = row.createCell(3);
//				cc.setCellValue(map.get("supplierName") + "");
//				cc.setCellStyle(styleLeft);
//
//				cc = row.createCell(4);
//				cc.setCellValue(map.get("operatorName") + "");
//				cc.setCellStyle(cellStyle);
//				cc = row.createCell(5);
//				cc.setCellValue(map.get("itemDate") + "");
//				cc.setCellStyle(cellStyle);
//				cc = row.createCell(6);
//				cc.setCellValue(map.get("ticketFligh") + "");
//				cc.setCellStyle(cellStyle);
//				cc = row.createCell(7);
//				cc.setCellValue(map.get("type1Name") == null ? "" : map
//						.get("type1Name") + "");
//				cc.setCellStyle(cellStyle);
//				cc = row.createCell(8);
//				cc.setCellValue(map.get("ticketDeparture") + "");
//				cc.setCellStyle(cellStyle);
//				cc = row.createCell(9);
//				cc.setCellValue(map.get("ticketArrival") + "");
//				cc.setCellStyle(cellStyle);
//
//				cc = row.createCell(10);
//				BigDecimal itemNum = new BigDecimal(0);
//				itemNum = itemNum.add((BigDecimal) map.get("itemNum"));
//				cc.setCellValue(itemNum.intValue());
//				cc.setCellStyle(cellStyle);
//				cc = row.createCell(11);
//				BigDecimal itemPrice = new BigDecimal(0);
//				itemPrice = itemPrice.add((BigDecimal) map.get("itemPrice"));
//				cc.setCellValue(itemPrice.intValue());
//				cc.setCellStyle(cellStyle);
//				cc = row.createCell(12);
//				BigDecimal itemNumMinus = new BigDecimal(0);
//				itemNumMinus = itemNumMinus.add((BigDecimal) map
//						.get("itemNumMinus"));
//				cc.setCellValue(itemNumMinus.doubleValue()); // 团款
//				cc.setCellStyle(cellStyle);
//				cc = row.createCell(13);
//				cc.setCellValue(map.get("cashType") == null ? "" : map
//						.get("cashType") + "");// 已收
//				cc.setCellStyle(cellStyle);
//				cc = row.createCell(14);
//				BigDecimal itemTotal = new BigDecimal(0);
//				itemTotal = itemTotal.add((BigDecimal) map.get("itemTotal"));
//				cc.setCellValue(itemTotal.doubleValue());// 未收
//				cc.setCellStyle(cellStyle);
//				index++;
//				total = total.add((BigDecimal) map.get("itemTotal"));
//
//				totalNum = totalNum.add((BigDecimal) map.get("itemNum"));
//				totalNumMinus = totalNumMinus.add((BigDecimal) map
//						.get("itemNumMinus"));
//			}
//			row = sheet.createRow(pb.getResult().size() + 2); // 加合计行
//			cc = row.createCell(0);
//			cc.setCellStyle(styleRight);
//			cc = row.createCell(1);
//			cc.setCellStyle(styleRight);
//			cc = row.createCell(2);
//			cc.setCellStyle(styleRight);
//			cc = row.createCell(3);
//			cc.setCellStyle(styleRight);
//			cc = row.createCell(4);
//			cc.setCellStyle(styleRight);
//			cc = row.createCell(5);
//			cc.setCellStyle(styleRight);
//			cc = row.createCell(6);
//			cc.setCellStyle(styleRight);
//			cc = row.createCell(7);
//			cc.setCellValue("合计：");
//			cc.setCellStyle(styleRight);
//			cc = row.createCell(8);
//			cc.setCellStyle(cellStyle);
//			cc = row.createCell(9);
//			cc.setCellStyle(styleRight);
//			cc = row.createCell(10);
//			cc.setCellValue(totalNum.intValue());
//			cc.setCellStyle(styleRight);
//			cc = row.createCell(11);
//			cc.setCellStyle(cellStyle);
//			cc = row.createCell(12);
//			cc.setCellValue(totalNumMinus.doubleValue());
//			cc.setCellStyle(cellStyle);
//			cc = row.createCell(13);
//			cc.setCellStyle(cellStyle);
//			/*
//			 * cc = row.createCell(12); cc.setCellStyle(cellStyle);
//			 */
//			cc = row.createCell(14);
//			cc.setCellValue(total.doubleValue());
//			// cc.setCellValue(total + "");
//			cc.setCellStyle(cellStyle);
//
//			CellRangeAddress region = new CellRangeAddress(pb.getResult()
//					.size() + 3, pb.getResult().size() + 4, 0, 14);
//			sheet.addMergedRegion(region);
//			// row = sheet.getRow(orders.size()+3); //打印信息
//			row = sheet.createRow(pb.getResult().size() + 3);
//			cc = row.createCell(0);
//			cc.setCellValue("打印人：" + WebUtils.getCurUser(request).getName()
//					+ " 打印时间："
//					+ DateUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
//			path = request.getSession().getServletContext().getRealPath("/")
//					+ "/download/" + System.currentTimeMillis() + ".xlsx";
//			FileOutputStream out = new FileOutputStream(path);
//			wb.write(out);
//			out.close();
//			wb.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		download2(path, request, response);
//		// return "";
//	}

	/**
	 * 点击团号时，根据团id加载订单id
	 * 
	 * @param request
	 * @param response
	 * @param groupId
	 * @return
	 */
	public DataAnalysisWebResult<Map<String, Object>> loadOrderId(Integer groupId);

	/**
	 * 客户产品人数统计
	 * 
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 */
//	@RequestMapping("productWithCustomerList.htm")
//	public String productWithCustomerQueries(HttpServletRequest request,
//			HttpServletResponse response, ModelMap modelMap) {
//		// Integer bizId = WebUtils.getCurBizId(request);
//		// getOrgAndUserTreeJsonStr(modelMap, bizId);
//		modelMap.addAttribute("bizId", WebUtils.getCurBizId(request));
//		return "queries/productWithCustomer/productWithCustomerList";
//	}

//	@RequestMapping("productWithCustomerList.do")
//	public String productWithCustomerList(HttpServletRequest request,
//			HttpServletResponse reponse, ModelMap model, String sl, String ssl,
//			String rp, Integer page, Integer pageSize, String svc) {
//		PageBean pb = commonQuery(request, model, sl, page, pageSize, svc);
//		/*
//		 * List result = pb.getResult(); Map paramters =
//		 * WebUtils.getQueryParamters(request); if (result != null &&
//		 * result.size() > 0) { for (int i = 0; i < result.size(); i++) { Map
//		 * map = (Map) result.get(i); String productBrandName = (String)
//		 * map.get("productBrandName"); String productName = (String)
//		 * map.get("productName"); paramters.put("productBrandName",
//		 * productBrandName); paramters.put("productName", productName); int
//		 * buyTotal = detailDeployService .selectBuyTotalByProduct(paramters);
//		 * map.put("buyTotal", buyTotal); } }
//		 */
//		return rp;
//
//	}


	public DataAnalysisWebResult<Integer> productProfitList(Map paramters);

	/**
	 * 其他收入、其他支出预览页面
	 * 
	 * @param request
	 * @param reponse
	 * @param model
	 * @param sl
	 * @param ssl
	 * @param rp
	 * @param page
	 * @param pageSize
	 * @param svc
	 * @return
	 */
//	@RequestMapping(value = "otherDetailPreview.htm")
//	public String otherDetailPreview(HttpServletRequest request,
//			HttpServletResponse reponse, ModelMap model, String sl, String ssl,
//			String rp, Integer page, Integer pageSize, String svc) {
//		PageBean pb = commonQuery(request, model, sl, page, 10000, svc);
//		String imgPath = bizSettingCommon.getMyBizLogo(request);
//		model.addAttribute("imgPath", imgPath);
//
//		model.addAttribute("printName", WebUtils.getCurUser(request).getName());
//		model.addAttribute("supplierType", WebUtils.getQueryParamters(request)
//				.get("supplierType"));
//		return "/queries/otherDetailPreview";
//	}


}
