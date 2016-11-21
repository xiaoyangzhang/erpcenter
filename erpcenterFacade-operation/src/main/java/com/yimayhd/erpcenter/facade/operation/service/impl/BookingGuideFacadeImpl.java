/**
 * 
 */
package com.yimayhd.erpcenter.facade.operation.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.biz.basic.service.DicBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.finance.FinanceGuideBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.operation.BookingDeliveryBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.operation.BookingGuideBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.operation.BookingSupplierBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.operation.BookingSupplierDetailBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.GroupOrderBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.GroupOrderGuestBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.GroupOrderTransportBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.GroupRequirementBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.GroupRouteBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.TourGroupBiz;
import com.yimayhd.erpcenter.biz.sys.service.PlatformEmployeeBiz;
import com.yimayhd.erpcenter.dal.sales.client.finance.po.FinanceCommission;
import com.yimayhd.erpcenter.dal.sales.client.finance.po.FinanceGuide;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingDelivery;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingGuide;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingSupplier;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingSupplierDetail;
import com.yimayhd.erpcenter.dal.sales.client.operation.vo.BookingGuidesVO;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupGuidePrintPo;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrder;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrderGuest;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrderPrintPo;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrderTransport;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupRequirement;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupRoute;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.TourGroup;
import com.yimayhd.erpcenter.dal.sales.client.sales.vo.TourGroupVO;
import com.yimayhd.erpcenter.facade.operation.errorcode.OperationErrorCode;
import com.yimayhd.erpcenter.facade.operation.query.BookingGuideQueryDTO;
import com.yimayhd.erpcenter.facade.operation.result.BookingGuideResult;
import com.yimayhd.erpcenter.facade.operation.result.ResultSupport;
import com.yimayhd.erpcenter.facade.operation.result.WebResult;
import com.yimayhd.erpcenter.facade.operation.service.BookingGuideFacade;
import com.yimayhd.erpresource.biz.service.SupplierGuideBiz;
import com.yimayhd.erpresource.dal.constants.Constants;
import com.yimayhd.erpresource.dal.po.SupplierGuide;

/**
 * @ClassName: BookingGuideFacadeImpl
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author zhangxiaoyang
 * @date 2016年10月27日
 */
public class BookingGuideFacadeImpl implements BookingGuideFacade {

	@Autowired
	private TourGroupBiz tourGroupBiz;
	@Autowired
	private GroupRequirementBiz groupRequirementBiz;
	@Autowired
	private BookingGuideBiz bookingGuideBiz;
	@Autowired
	private FinanceGuideBiz financeGuideBiz;
	@Autowired
	private BookingSupplierDetailBiz bookingSupplierDetailBiz;
	@Autowired
	private SupplierGuideBiz supplierGuideBiz;
	@Autowired
	private GroupOrderBiz groupOrderBiz;
	@Autowired
	private BookingSupplierBiz bookingSupplierBiz;
	@Autowired
	private GroupOrderGuestBiz groupOrderGuestBiz;
	@Autowired
	private GroupOrderTransportBiz groupOrderTransportBiz;
	@Autowired
	private BookingDeliveryBiz bookingDeliveryBiz;
	@Autowired
	private PlatformEmployeeBiz platformEmployeeBiz;
	@Autowired
	private GroupRouteBiz groupRouteBiz;
	@Autowired
	private DicBiz dicBiz;
	@Override
	public PageBean getGuideGroupList(PageBean pageBean, TourGroupVO group,
			Set<Integer> set) {
		pageBean = tourGroupBiz.getGuideGroupList(pageBean, group, set);
		return pageBean;
	}
	
	@Override
	public BookingGuideResult guideDetailList(Integer groupId) {
		BookingGuideResult result = new BookingGuideResult();
		TourGroup tg = tourGroupBiz.selectByPrimaryKey(groupId) ;
		result.setTourGroup(tg);
		//查询需求订单
		List<GroupRequirement> groupRequirements = groupRequirementBiz.selectByGroupIdAndType(groupId, Constants.GUIDE);
		result.setGroupRequirements(groupRequirements);
		List<BookingGuidesVO> vo = bookingGuideBiz.selectBookingGuideVoByGroupId(groupId);
		result.setBookingGuidesVOs(vo);
		return result;
	}

	@Override
	public BookingGuideResult guideDetailListView(Integer groupId) {
		BookingGuideResult result = new BookingGuideResult();
		Boolean checkGroupCanEdit = tourGroupBiz.checkGroupCanEdit(groupId);
		result.setGroupAbleEdit(checkGroupCanEdit);
		List<BookingGuidesVO> vo = bookingGuideBiz.selectBookingGuideVoByGroupId(groupId);
		result.setBookingGuidesVOs(vo);
		return result;
	}

	@Override
	public Map<String, Object> moveCoupon(BookingGuideQueryDTO queryDTO) {
		Map<String, Object> map = new HashMap<String, Object>();
		//TODO 此方法不全
		bookingGuideBiz.changeShop(queryDTO.getGroupId(),queryDTO.getGuideId(),queryDTO.getmGuideId(),queryDTO.getBookingGuideId());
		financeGuideBiz.changeGroup(queryDTO.getGroupId(),queryDTO.getGuideId(),queryDTO.getmGuideId());
		map.put("flag", true);
		map.put("msg", "转移成功");
		return map;
	}

	@Override
	public BookingGuideResult toGuideDetailListView(Integer groupId) {
		BookingGuideResult result = new BookingGuideResult();
		List<BookingGuidesVO> vo = bookingGuideBiz.selectBookingGuideVoByGroupId(groupId);
		result.setBookingGuidesVOs(vo);
		return result;
	}

	@Override
	public WebResult<Map<String, Object>> saveGuide(BookingGuidesVO guidesVO, String bizCode) {
		WebResult<Map<String, Object>> result = new WebResult<Map<String,Object>>();
		BookingGuide guide = guidesVO.getGuide();
		List<BookingGuide> list = bookingGuideBiz.selectGuidesByGroupId(guide.getGroupId());

		if(!tourGroupBiz.checkGroupCanEdit(guide.getGroupId())){
			result.setErrorCode(OperationErrorCode.UNABLE_EDIT_ERROR);
			return result;
		}
		if(guide.getId()==null){//新增
			for (BookingGuide bookingGuide : list) {
				if(guide.getGuideId().intValue()==bookingGuide.getGuideId().intValue()){
					result.setErrorCode(OperationErrorCode.GUIDE_EXISTED);
					return result;
				}
			}
			int No = bookingGuideBiz.getBookingCountByTime();
			guide.setBookingNo(bizCode+Constants.GUIDE+new SimpleDateFormat("yyMMdd").format(new Date())+(No+100));
			guide.setCreateTime(System.currentTimeMillis());
			guide.setBookingDate(new Date());
		}
		int insertResutl = bookingGuideBiz.insertSelective(guidesVO);
		if (insertResutl < 1) {
			result.setErrorCode(OperationErrorCode.MODIFY_ERROR);
		}else {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", guide.getGroupId());
		}
		return result;
	}

	@Override
	public BookingGuideResult toEditGuideView(Integer groupId,
			Integer bookingGuideId) {
		BookingGuideResult result = new BookingGuideResult();
		if(bookingGuideId != null){
			BookingGuidesVO vo = bookingGuideBiz.selectBookingGuideVoByGroupIdAndId(bookingGuideId);
			result.setGuidesVO(vo);
		}
		List<BookingSupplierDetail> driverList = bookingSupplierDetailBiz.getDriversByGroupIdAndType(groupId,Constants.FLEET);
		result.setSupplierDetails(driverList);
		//查询出团时间和团结束时间
		TourGroup tourGroup = tourGroupBiz.selectByPrimaryKey(groupId);
		result.setTourGroup(tourGroup);
		return result;
	}

	@Override
	public PageBean getGuideListByBizId(BookingGuideQueryDTO queryDTO) {
		PageBean pageBean = supplierGuideBiz.getGuideListByBizId(queryDTO.getSupplierGuide(),queryDTO.getBizId(),queryDTO.getPage(),queryDTO.getPageSize());
		
		return pageBean;
	}

	@Override
	public ResultSupport deldetailGuide(Integer bookingGuideId) {
		ResultSupport resultSupport = new ResultSupport();
		bookingGuideBiz.deleteByPrimaryKey(bookingGuideId);
		return resultSupport;
	}

	@Override
	public ResultSupport defTetailGuide(BookingGuide bookingGuide,
			Integer groupId) {
		ResultSupport resultSupport = new ResultSupport();
		bookingGuideBiz.updateBygroupId(groupId);
		int updateResult = bookingGuideBiz.updateByPrimaryKeySelective(bookingGuide);
		if (updateResult < 1) {
			resultSupport.setErrorCode(OperationErrorCode.MODIFY_ERROR);
		}
		return resultSupport;
	}

	@Override
	public BookingGuideResult financeBill(Integer groupId,
			Integer bookingGuideId) {
		BookingGuideResult result = new BookingGuideResult();
		List<FinanceGuide> list = financeGuideBiz.selectListByGroupIdAndBookingId(groupId, bookingGuideId);
		result.setFinanceGuides(list);
		/*Integer[] supplierType ={Constants.SCENICSPOT,Constants.HOTEL,Constants.RESTAURANT,Constants.FLEET,Constants.OTHERINCOME,Constants.OTHEROUTCOME};*/
		TourGroup group = tourGroupBiz.selectByPrimaryKey(groupId);//团信息
		result.setTourGroup(group);
		BookingGuidesVO guidesVo = bookingGuideBiz.selectBookingGuideVoByGroupIdAndId(bookingGuideId);
		result.setGuidesVO(guidesVo);
		String supplierName = "";
		//团队才有组团社
		if(group!=null && group.getGroupMode()>0){
			List<GroupOrder> listg = groupOrderBiz.selectOrderByGroupId(groupId);
//			if(listg!=null && listg.size()>0){
//				supplierName=listg.get(0).getSupplierName();
//			}
			result.setOrderList(listg);
		}
		
		return result;
	}

	@Override
	public BookingGuideResult financeSupplierView(FinanceGuide financeGuide) {
		BookingGuideResult result = new BookingGuideResult();
		List<BookingSupplier> list = financeGuideBiz.getFinanceSupplierByFinanceGuide(financeGuide);
		//result.setBookingSuppliers(list);
		getSupplierDetails(list);
		//result.setBookingSuppliers(list);
		BookingGuide guide= bookingGuideBiz.selectByPrimaryKey(financeGuide.getBookingId());
		result.setBookingGuide(guide);
		result.setBookingSuppliers(list);
		return result;
	}

	private void getSupplierDetails(List<BookingSupplier> list) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd") ;
		for (com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingSupplier bookingSupplier : list) {
			List<BookingSupplierDetail> supplierDetails = bookingSupplierDetailBiz.selectByPrimaryBookId(bookingSupplier.getId());
			//for (BookingSupplierDetail bookingSupplierDetail : supplierDetails) {
			List<String> str = new ArrayList<String>();
				for (int i = 0; i < supplierDetails.size(); i++) {
				
				if(bookingSupplier.getId().equals(supplierDetails.get(i).getBookingId())){
					if(bookingSupplier.getSupplierType().equals(Constants.SCENICSPOT)){//景点
						//门票：日期 + 项目 + 单价*（数量-免去）
						str.add(sdf.format(supplierDetails.get(i).getItemDate())+" "+supplierDetails.get(i).getType1Name()+" "+supplierDetails.get(i).getItemPrice()+"*"+"("+supplierDetails.get(i).getItemNum()+"-"+supplierDetails.get(i).getItemNumMinus()+")");/*+(bookingSupplierDetail.getItemNum()-bookingSupplierDetail.getItemNumMinus());*/
					}else if (bookingSupplier.getSupplierType().equals(Constants.HOTEL)) {//用房
						//入住日期 + 类别（房型）  单价*(数量-名去)
						str.add(sdf.format(supplierDetails.get(i).getItemDate())+" "+supplierDetails.get(i).getType1Name()+"("+(null==supplierDetails.get(i).getType2Name()?"":supplierDetails.get(i).getType2Name())+")"+" "+supplierDetails.get(i).getItemPrice()+"*"+"("+supplierDetails.get(i).getItemNum()+"-"+supplierDetails.get(i).getItemNumMinus()+")"+" "+supplierDetails.get(i).getItemBrief());
					}else if (bookingSupplier.getSupplierType().equals(Constants.RESTAURANT)) {//用餐
						//用餐日期 + 餐厅（类别）  单价*(数量-名去)
						str.add(sdf.format(supplierDetails.get(i).getItemDate())+" "+supplierDetails.get(i).getType1Name()+"("+(null==supplierDetails.get(i).getType2Name()?"":supplierDetails.get(i).getType2Name())+")"+" "+supplierDetails.get(i).getItemPrice()+"*"+"("+supplierDetails.get(i).getItemNum()+"-"+supplierDetails.get(i).getItemNumMinus()+")");
					}else if (bookingSupplier.getSupplierType().equals(Constants.FLEET)) {//用车
						//车型（座位数）+车牌号 司机 + 联系方式
						str.add(supplierDetails.get(i).getType1Name()+"("+supplierDetails.get(i).getType2Name()+")"+" "+supplierDetails.get(i).getCarLisence()+" "+supplierDetails.get(i).getDriverName()+" "+supplierDetails.get(i).getDriverTel());
					}else if (bookingSupplier.getSupplierType().equals(Constants.OTHERINCOME)) {//其他收入
						//项目  价格*数量  备注
						str.add(supplierDetails.get(i).getType1Name()+" "+supplierDetails.get(i).getItemPrice()+"*"+supplierDetails.get(i).getItemNum()+" "+bookingSupplier.getRemark());
					}else if (bookingSupplier.getSupplierType().equals(Constants.OTHEROUTCOME)) {//其他支出
						//项目  价格*数量  备注
						str.add(supplierDetails.get(i).getType1Name()+" "+supplierDetails.get(i).getItemPrice()+"*"+supplierDetails.get(i).getItemNum()+" "+bookingSupplier.getRemark());
					}
					
				}
				bookingSupplier.setSupplierDetail(str);
			}
			
			
		}
	}

	@Override
	public List<BookingSupplier> getFinanceSupplierByFinanceGuide(
			FinanceGuide financeGuide) {
		List<BookingSupplier> list = financeGuideBiz.getFinanceSupplierByFinanceGuide(financeGuide);
		getSupplierDetails(list);
		return list;
	}

	@Override
	public ResultSupport financeSave(List<FinanceGuide> financeGuides) {
		ResultSupport resultSupport = new ResultSupport();
		BookingGuide guide = bookingGuideBiz.selectByPrimaryKey(financeGuides.get(0).getBookingId());
		if(guide.getStateFinance()!=null && guide.getStateFinance().equals(1)){
			resultSupport.setErrorCode(OperationErrorCode.CHECKED);
			return resultSupport;
			//return errorJson("已审核！");
		}
		int save = financeGuideBiz.save(financeGuides) ;
		if (save < 1) {
			resultSupport.setErrorCode(OperationErrorCode.MODIFY_ERROR);
		}
		return resultSupport;
	}

	@Override
	public ResultSupport delFinance(BookingGuideQueryDTO queryDTO) {
		ResultSupport resultSupport = new ResultSupport();
		financeGuideBiz.financeDelete(queryDTO.getGroupId(),queryDTO.getBookingIdLink(),queryDTO.getBookingGuideId());
		return resultSupport;
	}

	@Override
	public BookingGuideResult bookingGuideListCount(PageBean pageBean,
			Integer bizId, Set<Integer> set) {
		BookingGuideResult result = new BookingGuideResult();
		pageBean = bookingGuideBiz.selectBookingGuideListCountListPage(pageBean, bizId,set);
		
		result.setPageBean(pageBean);
		Map totalMap= bookingGuideBiz.getBookingGuideTotalCount(pageBean, bizId,set);
		result.setMap(totalMap);
		return result;
	}

	@Override
	public PageBean bookingGuideListSelect(PageBean pageBean, Integer bizId,
			Set<Integer> set) {
		PageBean page = bookingGuideBiz.selectBookingGuideListSelectListPage(pageBean, bizId,set);
		return page;
	}

	@Override
	public BookingGuideResult getGuideRouteInfo(Integer groupId) {
		BookingGuideResult result = new BookingGuideResult();
		TourGroup tourGroup = tourGroupBiz.selectByPrimaryKey(groupId);
		String mobile = platformEmployeeBiz.findByEmployeeId(tourGroup.getOperatorId()).getMobile();
		result.setMobile(mobile);
		result.setTourGroup(tourGroup);
		List<BookingGuide> guides = bookingGuideBiz.selectGuidesByGroupId(groupId);
		result.setBookingGuides(guides);
		List<GroupOrderGuest> guestGuides = groupOrderGuestBiz.getGuestByGroupIdAndType(groupId,3);
		result.setGuestGuides(guestGuides);
		List<GroupOrderGuest> guestLeaders = groupOrderGuestBiz.getGuestByGroupIdAndIsLeader(groupId, 1);
		result.setGuestLeaders(guestLeaders);
		if(guides.size()>0){
			StringBuilder sb = new StringBuilder();
			SupplierGuide sg = null;
			for (BookingGuide guide : guides) {
				sg = supplierGuideBiz.getGuideInfoById(guide.getGuideId());
				sb.append(guide.getGuideName()+" "+guide.getGuideMobile()+" "+sg.getLicenseNo()+"\n") ;
			}
			result.setGuideString(sb.toString());
		}
		//预定车信息
		List<BookingSupplier> bs4 = bookingSupplierBiz.getBookingSupplierByGroupIdAndSupplierType(groupId, 4) ;
		StringBuilder sb = new StringBuilder() ;
			for (BookingSupplier bs : bs4) {
				List<BookingSupplierDetail> details = bookingSupplierDetailBiz.selectByPrimaryBookId(bs.getId()) ;
				for (BookingSupplierDetail bsd : details) {
					sb.append(bsd.getDriverName()+" "+bsd.getDriverTel()+" "+bsd.getCarLisence()+"\n") ;
				}
			}
		result.setDriverString(sb.toString());
		List<GroupOrderTransport> gots = groupOrderTransportBiz.selectByGroupId(groupId) ;
		result.setOrderTransports(gots);
		List<GroupRoute> routeList = groupRouteBiz.selectByGroupId(tourGroup.getId());
		result.setGroupRoutes(routeList);
		return result;
	}

	@Override
	public BookingGuideResult getGroupRouteInfo(Integer groupId) {
		BookingGuideResult result = new BookingGuideResult();
		//查询导游信息
		BookingGuide bookingGuide = bookingGuideBiz.selectByPrimaryKey(groupId);			
		result.setBookingGuide(bookingGuide);
		//团信息
		TourGroup tourGroup = tourGroupBiz.selectByPrimaryKey(bookingGuide.getGroupId());
		result.setTourGroup(tourGroup);
		String mobile = platformEmployeeBiz.findByEmployeeId(tourGroup.getOperatorId()).getMobile();
		result.setMobile(mobile);
		List<GroupOrderGuest> guestGuides = groupOrderGuestBiz.getGuestByGroupIdAndType(groupId,3);
		result.setGuestGuides(guestGuides);
		List<GroupOrderGuest> guestLeaders = groupOrderGuestBiz.getGuestByGroupIdAndIsLeader(groupId, 1);
		result.setGuestLeaders(guestLeaders);
		/**
		 * 如果该导游下面有结对司机，就显示结对司机，如果没有就显示团下面的所有司机
		 */
		BookingSupplierDetail bsd = bookingSupplierDetailBiz.selectByPrimaryKey(bookingGuide.getBookingDetailId()) ;
		StringBuilder sb = new StringBuilder() ;
		if(null!=bsd && null!=bsd.getDriverId()){
			sb.append(bsd.getDriverName()+" "+bsd.getDriverTel()+" "+bsd.getCarLisence()+"\n") ;
		}else{
			//查询团下所有预定车信息
			List<BookingSupplier> bs4 = bookingSupplierBiz.getBookingSupplierByGroupIdAndSupplierType(bookingGuide.getGroupId(), 4) ;
			for (BookingSupplier bs : bs4) {
				List<BookingSupplierDetail> details = bookingSupplierDetailBiz.selectByPrimaryBookId(bs.getId()) ;
				for (BookingSupplierDetail bsd1 : details) {
					sb.append(bsd1.getDriverName()+" "+bsd1.getDriverTel()+" "+bsd1.getCarLisence()+"\n") ;
				}
			}
		}
		//接送信息
		result.setDriverString(sb.toString());
		List<GroupOrderTransport> gots = groupOrderTransportBiz.selectByGroupId(groupId) ;
		result.setOrderTransports(gots);
		SupplierGuide sg = supplierGuideBiz.getGuideInfoById(bookingGuide.getGuideId());
		result.setSupplierGuide(sg);
		List<GroupRoute> routeList = groupRouteBiz.selectByGroupId(tourGroup.getId());
		result.setGroupRoutes(routeList);
//		String operatorMobile = platformEmployeeBiz.findByEmployeeId(tourGroup.getOperatorId()).getMobile();
		return result;
	}

	@Override
	public List<GroupGuidePrintPo> getOperatorInfo(Integer groupId, Integer id,Integer num) {
		
		List<GroupGuidePrintPo> pos = new ArrayList<GroupGuidePrintPo>() ;
		GroupGuidePrintPo po = null ;
		//预定下接社信息
		List<BookingDelivery> deliveries = bookingDeliveryBiz.getDeliveryListByGroupId(groupId) ;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd") ;
		for (BookingDelivery bd : deliveries) {
			po = new  GroupGuidePrintPo();
			po.setSupplierType("下接社");
			po.setSupplierName(bd.getSupplierName());
			po.setContacktWay(bd.getContact()+"-"+bd.getContactMobile());
			po.setPaymentWay("");
			String dd = "" ;
			if(bd.getDateArrival()!=null){
				dd=sdf.format(bd.getDateArrival()) ;
			}
			po.setDetail(dd+" "+"人数："+bd.getPersonAdult()+"大"+bd.getPersonChild()+"小"+bd.getPersonGuide()+"陪"+"\n");
			pos.add(po) ;
		}
		
		//预订房信息
		List<BookingSupplier> bs3 = bookingSupplierBiz.getBookingSupplierByGroupIdAndSupplierType(groupId, 3) ;
		for (BookingSupplier bs : bs3) {
			po = new  GroupGuidePrintPo();
			po.setSupplierType("房");
			po.setSupplierName(bs.getSupplierName());
			po.setContacktWay(bs.getContact()+"-"+bs.getContactMobile());
			po.setPaymentWay(bs.getCashType());
			List<BookingSupplierDetail> details = bookingSupplierDetailBiz.selectByPrimaryBookId(bs.getId()) ;
			StringBuilder sb = new StringBuilder() ;
			for (BookingSupplierDetail bsd : details) {
				String dd = "" ;
				if(bsd.getItemDate()!=null){
					dd = sdf.format(bsd.getItemDate()) ;
				}
				sb.append(dd+
						" 【"+bsd.getType1Name()+"】 "+
						"("+bsd.getItemNum().toString().replace(".0","")+
						"-"+bsd.getItemNumMinus().toString().replace(".0","")+")" +"\n");
			}
			po.setDetail(sb.toString());
			pos.add(po) ;
		}
		//预定车信息
		List<BookingSupplier> bs4 = bookingSupplierBiz.getBookingSupplierByGroupIdAndSupplierType(groupId, 4) ;
		if(num==3){
			for (BookingSupplier bs : bs4) {
				List<BookingSupplierDetail> details = bookingSupplierDetailBiz.selectByPrimaryBookId(bs.getId()) ;
				StringBuilder sb = new StringBuilder() ;
				for (BookingSupplierDetail bsd : details) {
					po = new  GroupGuidePrintPo();
					po.setSupplierType("司机");
					po.setSupplierName(bsd.getDriverName());
					po.setContacktWay(bsd.getDriverTel());
					po.setPaymentWay(bs.getCashType());
					if(null!=bs.getRemark() && (!"".equals(bs.getRemark()))){
						sb.append(bsd.getType1Name()+","+bsd.getType2Name()+"座"+","+bsd.getCarLisence()+",备注："+bs.getRemark()+"\n") ;
					}else{
						sb.append(bsd.getType1Name()+","+bsd.getType2Name()+"座"+","+bsd.getCarLisence()+"\n") ;
					}
					po.setDetail(sb.toString());
					pos.add(po) ;
				}
			}
		}else{
			BookingSupplierDetail bsd = bookingSupplierDetailBiz.selectByPrimaryKey(id) ;
			if(bsd!=null){
				BookingSupplier bs = bookingSupplierBiz.selectByPrimaryKey(bsd.getBookingId()) ;
				StringBuilder sb = new StringBuilder() ;
				po = new  GroupGuidePrintPo();
				po.setSupplierType("司机");
				po.setSupplierName(bsd.getDriverName());
				po.setContacktWay(bsd.getDriverTel());
				po.setPaymentWay(bs.getCashType());
				if(bs.getRemark()!=""&&bs.getRemark()!=null){
					sb.append(bsd.getType1Name()+","+bsd.getType2Name()+"座"+","+bsd.getCarLisence()+",备注："+bs.getRemark()+"\n") ;
				}else{
					sb.append(bsd.getType1Name()+","+bsd.getType2Name()+"座"+","+bsd.getCarLisence()+"\n") ;
				}
				po.setDetail(sb.toString());
				pos.add(po) ;
			}else{
				List<BookingSupplier> bsList = bookingSupplierBiz.getBookingSupplierByGroupIdAndSupplierType(groupId, 4) ;
				for (BookingSupplier bs : bsList) {
					List<BookingSupplierDetail> details = bookingSupplierDetailBiz.selectByPrimaryBookId(bs.getId()) ;
					StringBuilder sb = new StringBuilder() ;
					for (BookingSupplierDetail detail : details) {
						po = new  GroupGuidePrintPo();
						po.setSupplierType("司机");
						po.setSupplierName(detail.getDriverName());
						po.setContacktWay(detail.getDriverTel());
						po.setPaymentWay(bs.getCashType());
						if(null!=bs.getRemark() && (!"".equals(bs.getRemark()))){
							sb.append(detail.getType1Name()+","+detail.getType2Name()+"座"+","+detail.getCarLisence()+",备注："+bs.getRemark()+"\n") ;
						}else{
							sb.append(detail.getType1Name()+","+detail.getType2Name()+"座"+","+detail.getCarLisence()+"\n") ;
						}
						po.setDetail(sb.toString());
						pos.add(po) ;
					}
				}
			}
		}
		
		
		return pos;
	}

	@Override
	public List<GroupOrderPrintPo> getPrintPoList(Integer groupId) {
		List<GroupOrder> orders = groupOrderBiz.selectOrderByGroupId(groupId) ;
		List<GroupOrderPrintPo> gopps = new ArrayList<GroupOrderPrintPo>() ;
		GroupOrderPrintPo gopp = null ;
		for (GroupOrder order : orders) {
			//拿到单条订单信息
			gopp = new GroupOrderPrintPo() ;
			gopp.setGroupOrder(order);
			gopp.setRemark(order.getRemark());
			//根据散客订单统计人数
			Integer numAdult = groupOrderGuestBiz.selectNumAdultByOrderID(order.getId()) ;
			Integer numChild = groupOrderGuestBiz.selectNumChildByOrderID(order.getId()) ;
			gopp.setPersonNum(numAdult+"大"+numChild+"小");
			//根据散客订单统计客人信息
			List<GroupOrderGuest> guests1 = groupOrderGuestBiz.selectByOrderId(order.getId()) ;
//			StringBuilder sb = new StringBuilder();
//			for (GroupOrderGuest guest : guests1) {
//				sb.append(guest.getName() + " " + guest.getMobile() + " "
//						+ guest.getCertificateNum() + "/n");
//			}
//			gopp.setGuestInfo(sb.toString());
			//根据散客订单统计酒店信息
			List<GroupRequirement> grogShopList = groupRequirementBiz
					.selectByOrderAndType(order.getId(), 3);
			StringBuilder sb = new StringBuilder();
			for (GroupRequirement groupRequirement : grogShopList) {
				sb.append(groupRequirement.getRequireDate() + " "
				  		+ dicBiz.getById(Integer.parseInt(groupRequirement.getHotelLevel())).getValue() + " "
						+ groupRequirement.getCountSingleRoom() + "单间" + " "
						+ groupRequirement.getCountDoubleRoom() + "标间" + " "
						+ groupRequirement.getCountTripleRoom() + "三人间");
			}
			gopp.setHotelInfo(sb.toString());
			//根据散客订单统计接机信息
			List<GroupOrderTransport> groupOrderTransports = groupOrderTransportBiz
					.selectByOrderId(order.getId());
			gopps.add(gopp) ;
		}
		return gopps;
	}

	@Override
	public BookingGuideResult financeSupplierGuideView(FinanceGuide financeGuide,Integer bizId) {
		BookingGuideResult result = new BookingGuideResult();
		BookingGuide guide= bookingGuideBiz.selectByPrimaryKey(financeGuide.getBookingId());
		List<BookingSupplier> bookingSuppliers = bookingSupplierBiz.selectByGroupId(financeGuide.getGroupId());
		HashMap<Object, Object> map =new HashMap<Object, Object>();
		for (BookingSupplier bookingSupplier : bookingSuppliers) {
			financeGuide.setSupplierType(bookingSupplier.getSupplierType());
			List<BookingSupplier> bookingSupplierList = financeGuideBiz.getFinanceSupplierByFinanceGuide(financeGuide);
			getSupplierDetails(bookingSupplierList);
			map.put(bookingSupplier.getSupplierType(), bookingSupplierList);
		}
		
		
		List<FinanceCommission> financeCommissionList = financeGuideBiz.getFinanceCommisionByGroupIdAndGuideId(bizId,financeGuide.getGroupId(),guide.getGuideId());
		result.setFinanceCommissions(financeCommissionList);
		return result;
	}

	@Override
	public BookingGuideResult paymentDetail(Integer groupId, Integer bookingId) {
		BookingGuideResult result = new BookingGuideResult();
		TourGroup group = tourGroupBiz.selectByPrimaryKey(groupId);//团信息
		result.setTourGroup(group);
		BookingGuidesVO guidesVo = bookingGuideBiz.selectBookingGuideVoByGroupIdAndId(bookingId);
		result.setGuidesVO(guidesVo);
		
		HashMap<Object, Object> map =new HashMap<Object, Object>();
		FinanceGuide financeGuide =new FinanceGuide();
		financeGuide.setBookingId(bookingId);
		financeGuide.setGroupId(groupId);
		List<BookingSupplier> bookingSuppliers = bookingSupplierBiz.selectByGroupId(groupId);
		for (BookingSupplier bookingSupplier : bookingSuppliers) {
			financeGuide.setSupplierType(bookingSupplier.getSupplierType());
			List<BookingSupplier> bookingSupplierList = financeGuideBiz.getFinanceSupplier(financeGuide);
			getSupplierDetails(bookingSupplierList);
			map.put(bookingSupplier.getSupplierType(), bookingSupplierList);
		}
		
		PageBean commPageBean = new PageBean();
		Map<String,Object> commMap  = new HashMap<String, Object>();
		commMap.put("groupId", groupId);
		commMap.put("guideId", guidesVo.getGuide().getGuideId());
		commPageBean.setParameter(commMap);
		
		
		List<FinanceCommission> guideComms = financeGuideBiz.getCommisions(commPageBean);
		result.setFinanceCommissions(guideComms);
		List<FinanceCommission> guideCommDeductions = financeGuideBiz.getCommisionDeductions(commPageBean);
		result.setGuideCommDeductions(guideCommDeductions);
		return result;
	}

	@Override
	public BookingGuideResult printDetail(Integer groupId, Integer bookingId,Integer bizId) {
		
		BookingGuideResult result = new BookingGuideResult();
		TourGroup group = tourGroupBiz.selectByPrimaryKey(groupId);//团信息
		result.setTourGroup(group);
		BookingGuidesVO guidesVo = bookingGuideBiz.selectBookingGuideVoByGroupIdAndId(bookingId);
		result.setGuidesVO(guidesVo);
		HashMap<Object, Object> map =new HashMap<Object, Object>();
		FinanceGuide financeGuide =new FinanceGuide();
		financeGuide.setBookingId(bookingId);
		financeGuide.setGroupId(groupId);
		List<BookingSupplier> bookingSuppliers = bookingSupplierBiz.selectByGroupId(groupId);
		for (BookingSupplier bookingSupplier : bookingSuppliers) {
			financeGuide.setSupplierType(bookingSupplier.getSupplierType());
			List<BookingSupplier> bookingSupplierList = financeGuideBiz.getFinanceSupplierByFinanceGuide(financeGuide);
			getSupplierDetails(bookingSupplierList);
			map.put(bookingSupplier.getSupplierType(), bookingSupplierList);
		}
		result.setMap(map);
		if(null!=guidesVo.getGuide()){
			SupplierGuide supplierGuide = supplierGuideBiz.getGuideInfoById(guidesVo.getGuide().getGuideId());
			result.setSupplierGuide(supplierGuide);
		}
		
		List<FinanceCommission> financeCommissionList = financeGuideBiz.getFinanceCommisionByGroupIdAndGuideId(bizId,groupId,guidesVo.getGuide().getGuideId());
		result.setFinanceCommissions(financeCommissionList);
		return result;
	}

	@Override
	public BookingGuideResult guideFinance(Integer groupId,
			Integer bookingGuideId) {
		BookingGuide guide = bookingGuideBiz.selectByPrimaryKey(bookingGuideId);
		BookingGuideResult result = new BookingGuideResult();
		if (guide != null) {
			result = financeBill(groupId, bookingGuideId);
		}
		result.setBookingGuide(guide);
		return result;
	}
	
}
