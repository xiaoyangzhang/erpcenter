/**
 * 
 */
package com.yimayhd.erpcenter.facade.operation.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.biz.sales.client.service.finance.FinanceBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.operation.BookingGuideBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.operation.BookingShopBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.operation.BookingShopDetailBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.operation.BookingShopDetailDeployBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.operation.BookingSupplierDetailBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.GroupOrderBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.TourGroupBiz;
import com.yimayhd.erpcenter.common.util.DateUtils;
import com.yimayhd.erpcenter.common.util.NumberUtil;
import com.yimayhd.erpcenter.dal.sales.client.constants.Constants;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingGuide;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingShop;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingShopDetail;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingShopDetailDeploy;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingSupplierDetail;
import com.yimayhd.erpcenter.dal.sales.client.operation.vo.BookingGroup;
import com.yimayhd.erpcenter.dal.sales.client.operation.vo.BookingGuidesVO;
import com.yimayhd.erpcenter.dal.sales.client.operation.vo.BookingShopDetailDeployVO;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrder;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.TourGroup;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.TourGroupPriceAndPersons;
import com.yimayhd.erpcenter.facade.operation.errorcode.OperationErrorCode;
import com.yimayhd.erpcenter.facade.operation.query.BookingFinanceShopQueryDTO;
import com.yimayhd.erpcenter.facade.operation.result.BookingShopResult;
import com.yimayhd.erpcenter.facade.operation.result.FinanceShopResult;
import com.yimayhd.erpcenter.facade.operation.result.ResultSupport;
import com.yimayhd.erpcenter.facade.operation.service.BookingFinanceShopFacade;
import com.yimayhd.erpresource.biz.service.ContractBiz;
import com.yimayhd.erpresource.biz.service.SupplierItemBiz;
import com.yimayhd.erpresource.dal.po.SupplierContractPriceDateInfo;
import com.yimayhd.erpresource.dal.po.SupplierItem;

/**
 * @ClassName: BookingFinanceShopFacadeImpl
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author zhangxiaoyang
 * @date 2016年10月26日
 */
public class BookingFinanceShopFacadeImpl implements BookingFinanceShopFacade {

	private static final Logger LOGGER = LoggerFactory.getLogger(BookingFinanceShopFacadeImpl.class);
	@Autowired
	private TourGroupBiz tourGroupBiz;
	@Autowired
	private BookingShopBiz bookingShopBiz;
	@Autowired
	private BookingGuideBiz bookingGuideBiz;
	@Autowired
	private FinanceBiz financeBiz;
	@Autowired
	private BookingShopDetailBiz bookingShopDetailBiz;
	@Autowired
	private SupplierItemBiz supplierItemBiz;
	@Autowired
	private BookingSupplierDetailBiz bookingSupplierDetailBiz;
	@Autowired
	private GroupOrderBiz groupOrderBiz;
	@Autowired
	private BookingShopDetailDeployBiz bookingShopDetailDeployBiz;
	@Autowired
	private ContractBiz contractBiz;
	
	@Override
	public FinanceShopResult financeShopList(PageBean pageBean, Integer bizId,
			Set<Integer> set) {
		FinanceShopResult result = new FinanceShopResult();
		pageBean = tourGroupBiz.selectBookingFinanceShopGroupListPage(pageBean, bizId,set);
		result.setPageBean(pageBean);
		BookingGroup bookingGroup = tourGroupBiz.selectBookingFinanceShopGroupListPageSum(pageBean, bizId,set);
		result.setBookingGroup(bookingGroup);
		return result;
	}
	
	@Override
	public BookingShopResult shopDetailList(Integer groupId) {
		BookingShopResult result = new BookingShopResult();
		List<BookingShop> bookingShops=bookingShopBiz.getShopListByGroupId(groupId);
		result.setBookingShops(bookingShops);
		BigDecimal count=new BigDecimal(0);
		for (BookingShop bookingShop : bookingShops) {
			if(bookingShop.getTotalFace()!=null){
				count=count.add(bookingShop.getTotalFace());
			}
			
		}
		result.setCount(count);
		return result;
	}

	@Override
	public BookingShopResult toEditBookingShop(Integer groupId,Integer type) {
		BookingShopResult result = new BookingShopResult();
		List<BookingShop> shoplist = bookingShopBiz.getShopListByGroupId(groupId);
		result.setBookingShops(shoplist);
		if(type != 1){
			TourGroupPriceAndPersons tourGroupInfo = tourGroupBiz.selectTourGroupInfo(groupId);
			tourGroupInfo.setProfit(tourGroupInfo.getIncomeIncome()-tourGroupInfo.getCostTotalPrice());
			tourGroupInfo.setTotalProfit(tourGroupInfo.getProfit()/tourGroupInfo.getTotalAdult());
			result.setTourGroupInfo(tourGroupInfo);
		}
		return result;
	}

	@Override
	public BookingShopResult editShop(Integer groupId, Integer bookingShopId) {
		BookingShopResult result = new BookingShopResult();
		if(bookingShopId != null){
			BookingShop shop = bookingShopBiz.selectByPrimaryKey(bookingShopId);
			result.setBookingShop(shop);
		}
		//查询导游列表
		List<BookingGuide> guides = bookingGuideBiz.selectGuidesByGroupId(groupId);
		result.setBookingGuides(guides);
		return result;
	}

	@Override
	public int saveShopAndUpdateFinance(BookingShop bookingShop,String bizCode) {
		int result = saveShop(bookingShop, bizCode);
		if(bookingShop.getId()!=null){
			financeBiz.calcTourGroupAmount(bookingShop.getGroupId());
		}
		return result;
	}

	@Override
	public int saveShop(BookingShop bookingShop, String bizCode) {
		if(null == bookingShop.getId()){
			int No = bookingShopBiz.getBookingCountByTime();
			bookingShop.setBookingNo(bizCode+Constants.SHOPPING+new SimpleDateFormat("yyMMdd").format(new Date())+(No+100));
		}
		int result = bookingShopBiz.save(bookingShop);
		return result;
	}

	@Override
	public void deldetailGuide(Integer bookingId) {

		BookingShop shop = bookingShopBiz.selectByPrimaryKey(bookingId);
		bookingShopDetailBiz.deleteByBookingId(bookingId);
		bookingShopBiz.deleteByPrimaryKey(bookingId);
		financeBiz.calcTourGroupAmount(shop.getGroupId());
	}

	
	@Override
	public BookingShopResult toFactShop(Integer groupId, Integer bookingShopId) {
		BookingShopResult result = new BookingShopResult();
		BookingShop shop =bookingShopBiz.selectByPrimaryKey(bookingShopId);
		result.setBookingShop(shop);
		//查询实际消费返款列表
		List<BookingShopDetail> shopDetails =bookingShopDetailBiz.getShopDetailListByBookingId(bookingShopId);
		result.setShopDetails(shopDetails);
		//商品
		List<SupplierItem> supplierItems = supplierItemBiz.findSupplierItemBySupplierId(shop.getSupplierId());
		result.setSupplierItems(supplierItems);
		//查询导游列表
		List<BookingGuide> guides = bookingGuideBiz.selectGuidesByGroupId(groupId);
		result.setBookingGuides(guides);
		BookingGuide driverGuide = bookingGuideBiz.selectByGuideIdAndGroupId(shop.getGuideId(), groupId);
		result.setBookingGuide(driverGuide);
		BookingSupplierDetail driver = null;
		if(driverGuide != null){
			driver = bookingSupplierDetailBiz.selectByPrimaryKey(driverGuide.getBookingDetailId());
			result.setBookingSupplierDetail(driver);
		}
		List<BookingSupplierDetail> driverList = bookingSupplierDetailBiz.getDriversByGroupIdAndType(groupId, null);
		result.setSupplierDetails(driverList);
		return result;
	}

	@Override
	public BookingShopResult editFactShop(
			BookingShopDetailDeploy shopDetailDeploy) {
		BookingShopResult result = new BookingShopResult();
		List<BookingShopDetailDeploy> deploys = bookingShopDetailDeployBiz.selectByDetailId(shopDetailDeploy.getBookingDetailId());
		result.setShopDetailDeploys(deploys);
		List<GroupOrder> groupOrders = tourGroupBiz.selectOrderAndGuestInfoByGroupId(shopDetailDeploy.getOrderId());
		result.setGroupOrders(groupOrders);
		return result;
	}

	@Override
	public BookingShopDetail getShopDetailById(Integer shopDetailId) {
		if(shopDetailId == null){
			LOGGER.error("params:shopDetailId={}",shopDetailId);
			return new BookingShopDetail();
		}
		BookingShopDetail shopDetail = bookingShopDetailBiz.getShopDetailById(shopDetailId);
		return shopDetail;
	}

	@Override
	public Map<String, Object> saveShopDetail(
			List<BookingShopDetail> shopDetails, BookingShop bookingShop,String bizCode) {
		//保存购物
		if(null==bookingShop.getId()){
			int No = bookingShopBiz.getBookingCountByTime();
			bookingShop.setBookingNo(bizCode+Constants.SHOPPING+new SimpleDateFormat("yyMMdd").format(new Date())+(No+100));
			bookingShop.setPersonNumFact(bookingShop.getPersonNum());
		}
		Integer id = bookingShopBiz.save(bookingShop);
		if(bookingShop.getId() != null){
			bookingShopDetailBiz.deleteByBookingId(bookingShop.getId());
		}
		//保存实际消费
		//List<BookingShopDetail> bShopDetails = JSON.parseArray(shopDetail, BookingShopDetail.class);
		if(!CollectionUtils.isEmpty(shopDetails)){
			for (BookingShopDetail bShopDetail : shopDetails) {
				bShopDetail.setType((byte)1);
				bShopDetail.setBookingId(id);
				bookingShopDetailBiz.save(bShopDetail);
			}
		}else{//如果detail为空，则shop金额全部为空
			bookingShop.setTotalFace(new BigDecimal(0));
			bookingShop.setTotalRepay(new BigDecimal(0));
			bookingShopBiz.updateByPrimaryKeySelective(bookingShop);
		}
		if(bookingShop != null && bookingShop.getGroupId() != null){
			financeBiz.calcTourGroupAmount(bookingShop.getGroupId());
		}
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("id", id);
		return map;
	}

	@Override
	public ResultSupport delShopDetail(Integer id, Integer groupId) {
		ResultSupport result = new ResultSupport();
		BookingShopDetail shopDetail = bookingShopDetailBiz.getShopDetailById(id);
		int delResult = bookingShopDetailBiz.deleteByPrimaryKey(id);
		if(shopDetail.getType() != null && shopDetail.getType().equals((byte)1)){
		  
				bookingShopBiz.updatetotalFace(shopDetail.getBookingId());
		}
		financeBiz.calcTourGroupAmount(groupId);
		if (delResult < 1) {
			result.setErrorCode(OperationErrorCode.MODIFY_ERROR);
			
		}
		return result;
	}

	@Override
	public ResultSupport saveShopDetail(BookingShopDetailDeployVO deployVO) {
		ResultSupport resultSupport = new ResultSupport();
		int insertResult = bookingShopDetailDeployBiz.insertSelective(deployVO);
		if (insertResult < 1) {
			resultSupport.setErrorCode(OperationErrorCode.MODIFY_ERROR);
		}
		return resultSupport;
	}

	@Override
	public BookingShopResult toAddShop(Integer groupId) {
		BookingShopResult result = new BookingShopResult();
		List<BookingGuide> guides = bookingGuideBiz.selectGuidesByGroupId(groupId);
		result.setBookingGuides(guides);
		List<BookingSupplierDetail> driverList = bookingSupplierDetailBiz.getDriversByGroupIdAndType(groupId, null);
		result.setSupplierDetails(driverList);
		TourGroup tourGroup = tourGroupBiz.selectByPrimaryKey(groupId);
		result.setTourGroup(tourGroup);
		return result;
	}

	@Override
	public Map<String, Object> getMatchedDriver(Integer guideId, Integer groupId) {
		BookingGuide driverGuide = bookingGuideBiz.selectByGuideIdAndGroupId(guideId, groupId);
		BookingSupplierDetail driver = bookingSupplierDetailBiz.selectByPrimaryKey(driverGuide.getBookingDetailId());
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("driverId", null==driver?"":driver.getDriverId());
		map.put("driverName", null==driver?"":driver.getDriverName());
		return map;
	}

	@Override
	public ResultSupport delBookingShop(Integer bookingId) {
		ResultSupport result = new ResultSupport();
		BookingShop shop = bookingShopBiz.selectByPrimaryKey(bookingId);
		bookingShopDetailBiz.deleteByBookingId(bookingId);
		bookingShopBiz.deleteByPrimaryKey(bookingId);
		financeBiz.calcTourGroupAmount(shop.getGroupId());
		return result;
	}

	@Override
	public ResultSupport delShopAndDetail(Integer bookingId) {
		ResultSupport resultSupport = new ResultSupport();
		int s = bookingShopDetailDeployBiz.getCountByShopId(bookingId);
		if(s>0){
			resultSupport.setErrorCode(OperationErrorCode.MODIFY_ERROR);
			return resultSupport;
		}else{
			BookingShop shop = bookingShopBiz.selectByPrimaryKey(bookingId);
			bookingShopDetailBiz.deleteByBookingId(bookingId);
			bookingShopBiz.deleteByPrimaryKey(bookingId);
			financeBiz.calcTourGroupAmount(shop.getGroupId());
			return resultSupport;
		}
	}

	@Override
	public String toSaveExcelData(BookingFinanceShopQueryDTO queryDTO) {
		StringBuffer str =new StringBuffer();
		Integer supplierId = queryDTO.getSupplierId();
		String bizCode = queryDTO.getBizCode();
		Integer bizId = queryDTO.getBizId();
		String userName = queryDTO.getUserName();
		Sheet sheet = queryDTO.getSheet();
		Integer userId = queryDTO.getUserId();
		String supplierName = queryDTO.getSupplierName();
		Map<String, Object> map = queryDTO.getMap();
		List<SupplierItem> supplierItems = supplierItemBiz.findSupplierItemBySupplierId(supplierId);
		//比较表中购物项目，去掉不存在的购物项目
		String imp = null;
		
		//筛选后，保留下来的购物项目集合
		List<SupplierItem> supplierItemList = new ArrayList<SupplierItem>();
		Iterator<Map.Entry<String,Object>> it = map.entrySet().iterator();  
        while(it.hasNext()){  
            Map.Entry<String,Object> entry=it.next();  
            for (SupplierItem supplierItem : supplierItems) {
				if(supplierItem.getItemName().trim().equals(entry.getKey())){
					imp = "HAVE";
					supplierItem.setExclId((Integer)entry.getValue());
					supplierItemList.add(supplierItem);
				}
			}
            if(null==imp){
            	str.append(supplierName+"没有"+entry.getKey()+"\r\n");
            	it.remove();  
			}
			imp =null;
        }  
		Row row = null ;
		Cell cell = null ;
		TourGroup tour = null;
		 for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
			row = sheet.getRow(rowIndex) ;
			//团号
			String groupCode="";
			cell = row.getCell(0) ;
			if(null!=cell){
				cell.setCellType(1) ;
				str.append(cell.getStringCellValue()+",");
				groupCode = cell.getStringCellValue().trim();
			}
			//导游姓名
			String guideName="";
			Integer guideId = null;
			cell = row.getCell(1) ;
			if(null!=cell){
				cell.setCellType(1) ;
				str.append(cell.getStringCellValue()+":");
				tour = tourGroupBiz.selectByGroupCode(groupCode);
				if(null==tour){
					str.append("找不到该团！\r\n");
					continue;
				}
				List<BookingGuidesVO> guide = bookingGuideBiz.selectBookingGuideVoByGroupId(tour.getId());
				for (BookingGuidesVO bookingGuidesVO : guide) {
					if(bookingGuidesVO.getGuide().getGuideName().equals(cell.getStringCellValue().trim())){
						guideName = cell.getStringCellValue().trim();
						guideId = bookingGuidesVO.getGuide().getGuideId();
					}
				}
				if(StringUtils.isBlank(guideName)){
					str.append("该团找不到此导游！\r\n");
					continue;
				}
			}
			
			//进店人数
			String personNum = null;
			cell = row.getCell(2) ;
			if(null!=cell){
				cell.setCellType(1) ;
				personNum = cell.getStringCellValue().trim();
				if(!NumberUtil.isNumeric(personNum)){
					str.append("该团进店人数不是数字！\r\n");
					continue;
				}
			}
			//进店日期
			Date date = null;
			cell = row.getCell(3) ;
			if(null!=cell){
				cell.setCellType(Cell.CELL_TYPE_NUMERIC) ;
				date = cell.getDateCellValue();
			}
			
			//购物店信息
			BookingShop shop =new BookingShop();
			shop.setGroupId(tour.getId());
			shop.setGuideId(guideId);
			shop.setGuideName(guideName);
			shop.setPersonNum(personNum==null?0:Integer.parseInt(personNum));
			shop.setShopDate(date == null ? "" : DateUtils.format(date));
			shop.setSupplierId(supplierId);
			shop.setSupplierName(supplierName);
			
			
			List<BookingShopDetail> bShopDetails= new ArrayList<BookingShopDetail>();
			BookingShopDetail bookingShopDetail = null;
			
			for (SupplierItem supplierItem : supplierItemList) {
				bookingShopDetail = new BookingShopDetail();
				bookingShopDetail.setGoodsId(supplierItem.getId());
				bookingShopDetail.setGoodsName(supplierItem.getItemName());
				cell = row.getCell(supplierItem.getExclId()) ;
				if(null!=cell){
					bookingShopDetail.setBuyTotal(new BigDecimal(cell.getNumericCellValue()));
				}
				//返款协议
				List<Date> dateList = new ArrayList<Date>();
				dateList.add(tour.getDateStart());
				List<SupplierContractPriceDateInfo> priceList = contractBiz.getContractPriceByPramas(bizId, supplierId,supplierItem.getId(), dateList);
				if(null!=priceList && priceList.size()>0){
					bookingShopDetail.setRepayVal(new BigDecimal(priceList.get(priceList.size()-1).getRebateAmount()));
				}else{
					str.append(supplierItem.getItemName()+"没有返款比例！");
					continue;
				}
				bookingShopDetail.setRepayTotal(bookingShopDetail.getBuyTotal().multiply(bookingShopDetail.getRepayVal().divide(new BigDecimal("100"))));
				bShopDetails.add(bookingShopDetail);
			}
			//保存
			bookingShopBiz.saveShopAndDetail(bizCode,userId,userName,bShopDetails,shop);
			str.append("导入成功！\r\n");
			
		 }
		return str.toString();
	}

	
	

}
