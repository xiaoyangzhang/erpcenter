package com.yimayhd.erpcenter.dal.sales.finance.impl;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.common.exception.ClientException;
import com.yimayhd.erpcenter.common.util.DateUtils;
import com.yimayhd.erpcenter.common.util.NumberUtil;
import com.yimayhd.erpcenter.dal.sales.client.finance.po.CheckSheetFinance;
import com.yimayhd.erpcenter.dal.sales.client.finance.po.FinanceCommission;
import com.yimayhd.erpcenter.dal.sales.client.finance.po.FinanceGuide;
import com.yimayhd.erpcenter.dal.sales.client.finance.po.FinancePay;
import com.yimayhd.erpcenter.dal.sales.client.finance.po.FinancePayDetail;
import com.yimayhd.erpcenter.dal.sales.client.finance.service.FinanceDal;
import com.yimayhd.erpcenter.dal.sales.client.finance.service.FinanceGuideDal;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingGuide;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingSupplier;
import com.yimayhd.erpcenter.dal.sales.client.operation.service.BookingGuideDal;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.TourGroup;
import com.yimayhd.erpcenter.dal.sales.finance.dao.FinanceBillMapper;
import com.yimayhd.erpcenter.dal.sales.finance.dao.FinanceCommissionDeductionMapper;
import com.yimayhd.erpcenter.dal.sales.finance.dao.FinanceCommissionMapper;
import com.yimayhd.erpcenter.dal.sales.finance.dao.FinanceGuideMapper;
import com.yimayhd.erpcenter.dal.sales.finance.dao.FinancePayCommissionDeductionMapper;
import com.yimayhd.erpcenter.dal.sales.finance.dao.FinancePayDetailCommissionDeductionMapper;
import com.yimayhd.erpcenter.dal.sales.finance.dao.FinancePayDetailMapper;
import com.yimayhd.erpcenter.dal.sales.finance.dao.FinancePayMapper;
import com.yimayhd.erpcenter.dal.sales.operation.dao.BookingGuideMapper;
import com.yimayhd.erpcenter.dal.sales.operation.dao.BookingSupplierMapper;
import com.yimayhd.erpcenter.dal.sales.sales.dao.TourGroupMapper;

/**
 * 导游报账
 * 
 * @author Jing.Zhuo
 * @create 2015年8月13日 下午12:11:47
 */
public class FinanceGuideDalImpl implements FinanceGuideDal {

	@Autowired
	private SqlSessionTemplate sqlSessionSales;
	
	@Autowired
	private FinanceDal financeDal;
	@Autowired
	private	BookingGuideDal bookingGuideDal;
	
	@Autowired
	private TourGroupMapper groupMapper;
	@Autowired
	private BookingGuideMapper guideMapper;
	@Autowired
	private BookingSupplierMapper supplierMapper;
	@Autowired
	private FinanceGuideMapper financeGuideMapper;
	@Autowired
	private FinancePayMapper payMapper;
	@Autowired
	private FinancePayCommissionDeductionMapper payCommDeductionMapper;
	@Autowired
	private FinancePayDetailMapper payDetailMapper;
	@Autowired
	private FinancePayDetailCommissionDeductionMapper payDetailCommDeductionMapper;
	@Autowired
	private FinanceCommissionMapper commissionMapper;
	@Autowired
	private FinanceCommissionDeductionMapper commissionDeductionMapper;
	@Autowired
	private FinanceBillMapper financeBillMapper;
	

	@Override
	@Transactional
	public void auditGuideBill(Integer billId) {
		
		//checkGroupState(billId);
		
		/** 导游报账单审核 和 订单是否审核无关   2016-01-15  
		int unauditedOrderCount = financeGuideMapper.selectUnauditedOrderCount(billId);
		if(unauditedOrderCount > 0){
			throw new ClientException("请先审核需要报账的订单");
		}
		**/
		
		BookingGuide guide = new BookingGuide();
		guide.setId(billId);

		// 设置财务状态为已审核
		guide.setStateFinance((byte) 1);
		guideMapper.updateByPrimaryKeySelective(guide);
	}
	
	@Override
	@Transactional
	public void delAuditGuideBill(Integer billId) {
		BookingGuide guide = new BookingGuide();
		guide.setId(billId);

		// 设置财务状态为未审核
		guide.setStateFinance((byte) 0);
		guideMapper.updateByPrimaryKeySelective(guide);
	}

	@Override
	@Transactional
	public void rejectGuideBill(Integer billId) {

		BookingGuide guide = new BookingGuide();
		guide.setId(billId);

		// 设置报账状态为未报账
		guide.setStateBooking((byte) 0);
		guideMapper.updateByPrimaryKeySelective(guide);
	}

	@Override
	@Transactional
	public void submit(Integer billId, int state) {
		checkGroupState(billId);
		BookingGuide guide = new BookingGuide();
		guide.setId(billId);

		// 设置报账状态为已报账
		guide.setStateBooking((byte) state);
		guideMapper.updateByPrimaryKeySelective(guide);
	}
	
	/**
	 * 校验团状态是否可以报账
	 */
	@Transactional
	private void checkGroupState(Integer bookid){
		
		BookingGuide guide = guideMapper.selectByPrimaryKey(bookid);
		TourGroup group = groupMapper.selectByPrimaryKey(guide.getGroupId());
		if(group.getGroupState()!=1){
			throw new ClientException("只有状态为已确认的团才能报账");
		}
	}

	@Override
	@Transactional
	public void payGuideBill(FinancePay pay) {

		Integer billId = pay.getBookingGuideId();
		checkGroupState(billId);
		
		BookingGuide guide = guideMapper.selectByPrimaryKey(billId);
		if (guide.getStateFinance().intValue() != 1) {
			throw new ClientException("未审核的报账单不能付款");
		}
		//0为付款
		pay.setPayDirect(0);
		pay.setCreateTime(System.currentTimeMillis());
		pay.setPayDate(Calendar.getInstance().getTime());

		FinanceGuide p1 = new FinanceGuide();
		p1.setBookingId(billId);
		//根据booking_guide_id查询finance_guide表
		List<FinanceGuide> list = financeGuideMapper.selectByProperty(p1);

		for (FinanceGuide fg : list) {
			//根据id查询booking_supplier表
			BookingSupplier bs = supplierMapper.selectByPrimaryKey(fg.getBookingIdLink());

			pay.setId(null);
			pay.setCash(fg.getTotal());
			pay.setSupplierId(bs.getSupplierId());
			pay.setSupplierName(bs.getSupplierName());
			pay.setSupplierType(bs.getSupplierType());
			payMapper.insert(pay);
			FinancePayDetail detail = new FinancePayDetail();
			detail.setLocOrderId(fg.getBookingIdLink());
			detail.setPayId(pay.getId());
			detail.setCash(fg.getTotal());
			payDetailMapper.insert(detail);
			
			BookingSupplier modify = new BookingSupplier();
			modify.setId(fg.getBookingIdLink());
			BigDecimal rowCash = financeDal.calcTotalCashValue(fg.getBookingIdLink(), fg.getSupplierType());
			modify.setTotalCash(rowCash);
			supplierMapper.updateByPrimaryKeySelective(modify);//更新booking_supplier表的total_cash			
		}

		BookingGuide p2 = new BookingGuide();
		p2.setId(billId);

		// 置为已经报账
		p2.setStateBooking((byte) 3);
		guideMapper.updateByPrimaryKeySelective(p2);

		// 统计整团收支
		financeDal.calcTourGroupAmount(guide.getGroupId());
	}
	
	/**
	 * 导游报账单分开报账
	 */
	@Override
	@Transactional
	public void payGuideBill(FinancePay pay,String checkedArr,String name) {

		if(StringUtils.isEmpty(checkedArr)){
			return;
		}
			
		Integer billId = pay.getBookingGuideId();
		//checkGroupState(billId);
		
		BookingGuide guide = guideMapper.selectByPrimaryKey(billId);
		if (guide.getStateFinance().intValue() != 1) {
			throw new ClientException("未审核的报账单不能付款");
		}
		
		//0为付款
		pay.setId(null);
		pay.setPayDirect(0);
		pay.setCreateTime(System.currentTimeMillis());
		pay.setPayDate(Calendar.getInstance().getTime());
		pay.setGroupId(guide.getGroupId());
		BigDecimal payCash = new BigDecimal(0);
		pay.setCash(payCash);
		pay.setType(2); //标识是导游报账
		pay.setGroupId(null);
		pay.setGuideId(null);
		payMapper.insert(pay);
		
		BigDecimal rowCash = new BigDecimal(0);
		//根据booking_guide_id查询finance_guide表
		List<FinanceGuide> list = financeGuideMapper.selectByPropertyAndStr(billId,checkedArr);
		for (FinanceGuide fg : list) {
			
			if(fg.getTotal() != null){
				if(fg.getSupplierType().compareTo(120) == 0){
					payCash = payCash.subtract(fg.getTotal());
				}else{
					payCash = payCash.add(fg.getTotal());
				}
			}
			FinancePayDetail detail = new FinancePayDetail();
			detail.setLocOrderId(fg.getBookingIdLink());
			detail.setPayId(pay.getId());
			detail.setCash(fg.getTotal());
			payDetailMapper.insert(detail);
			
			//更新finance_guide状态置为已付
			fg.setPayStatus(1);
			fg.setPayUserName(name);
			fg.setPayTime(new Date());
			financeGuideMapper.updateByPrimaryKeySelective(fg);
			
			
			//BookingSupplier bs = supplierMapper.selectByPrimaryKey(fg.getBookingIdLink()); //重新汇总订单的totalCash字段
			BookingSupplier modify = new BookingSupplier();
			modify.setId(fg.getBookingIdLink());
			//ou注释以下两行 2016-06-15，直接在原来基础上直接加减是有问题，因为发现好多次本身totalCash字段的值是错的
			//modify.setTotalCash(bs.getTotalCash() == null ? new BigDecimal(0) : bs.getTotalCash());
			//modify.setTotalCash(modify.getTotalCash().add(fg.getTotal()));
			rowCash = financeDal.calcTotalCashValue(fg.getBookingIdLink(), fg.getSupplierType());
			modify.setTotalCash(rowCash);
			supplierMapper.updateByPrimaryKeySelective(modify);//更新booking_supplier表的total_cash
		}
		
		pay.setCash(payCash);
		payMapper.update(pay);

		BookingGuide p2 = new BookingGuide();
		p2.setId(billId);
		
		//统计finance_guide已结算金额.除了收入类型
		BigDecimal sumTotal = financeGuideMapper.selectSumTotalPay(guide.getGroupId(), guide.getId(),0);
		//统计finance_guide已结算收入类型金额.
		BigDecimal sumTotalIncome = financeGuideMapper.selectSumTotalPay(guide.getGroupId(), guide.getId(),1);
	
		if((sumTotal.subtract(sumTotalIncome)).compareTo(guide.getTotal())==0){
			// 置为已经报账
			p2.setStateBooking((byte) 3);
			guideMapper.updateByPrimaryKeySelective(p2);
		}
		
		// 统计整团收支
		financeDal.calcTourGroupAmount(guide.getGroupId());
	}
	
	/**
	 * 删除导游报账单
	 */
	@Override
	@Transactional
	public void deletePayById(Integer payId){
		if(payId == null){
			return;
		}
		
		FinancePay pay = payMapper.selectByPrimaryKey(payId);
		
		List<FinancePayDetail> details = payDetailMapper.selectByPayId(payId);
		if(details == null || details.size() == 0){
			return;
		}
		
		Integer groupId = 0;
		for(int i = 0; i < details.size(); i++){
			FinanceGuide financeGuide = financeGuideMapper.selectByBookingIdLink(details.get(i).getLocOrderId());
			financeGuide.setPayStatus(0);
			financeGuide.setPayUserName("");
			financeGuideMapper.updateByPrimaryKeySelective(financeGuide);
			
			if(i == 0){
				groupId = financeGuide.getGroupId();
			}
			BookingGuide p2 = new BookingGuide();
			p2.setId(pay.getBookingGuideId());
			p2.setStateBooking((byte) 2);  //将报账状态改为：处理中
			guideMapper.updateByPrimaryKeySelective(p2);
		}
		
		payDetailMapper.deleteByPayId(payId);
		payMapper.deleteByPrimaryKey(payId);
		
		//重新汇总 订单 的已付金额
		for(int i = 0; i < details.size(); i++){
			FinanceGuide financeGuide = financeGuideMapper.selectByBookingIdLink(details.get(i).getLocOrderId());
			
			BookingSupplier modify = new BookingSupplier();
			modify.setId(financeGuide.getBookingIdLink());
			BigDecimal rowCash = financeDal.calcTotalCashValue(financeGuide.getBookingIdLink(), financeGuide.getSupplierType());
			modify.setTotalCash(rowCash);
			supplierMapper.updateByPrimaryKeySelective(modify);//更新booking_supplier表的total_cash
		}
		// 统计整团收支
		financeDal.calcTourGroupAmount(groupId);
	}
	
	/**
	 * 删除佣金发放付款记录
	 */
	@Override
	@Transactional
	public void deletePayCommById(Integer payId){
		if(payId == null){
			return;
		}
		FinancePay pay = payMapper.selectByPrimaryKey(payId);
		
		List<FinancePayDetail> details = payDetailMapper.selectByPayId(payId);
		if(details == null || details.size() == 0){
			return;
		}
		
		StringBuilder commIds = new StringBuilder();
		FinancePayDetail detail = null;
		for(int i = 0; i < details.size(); i++){
			detail = details.get(i);
			if(i > 0){
				commIds.append(",");
			}
			commIds.append(detail.getLocOrderId());
		}
		
		List<FinanceCommission> comms = commissionMapper.selectByPrimaryKeys(commIds.toString());
		if(comms == null || comms.size() == 0){
			return;
		}
		
		FinanceCommission comm = null;
		for(int i = 0; i < comms.size(); i++){
			comm = comms.get(i);
			comm.setTotalCash(new BigDecimal(0));
			comm.setPayTime(null);
			comm.setPayUserName("");
			commissionMapper.update(comm);
		}
		
		payDetailMapper.deleteByPayId(payId);
		payMapper.deleteByPrimaryKey(payId);
		
		// 统计整团收支
		if(pay.getGroupId() != null){
			financeDal.calcTourGroupAmount(pay.getGroupId());
		}
	}
	
	/**
	 * 删除佣金扣除付款记录
	 */
	@Override
	@Transactional
	public void deletePayCommDeductionById(Integer payId){
		if(payId == null){
			return;
		}
		
		List<FinancePayDetail> details = payDetailCommDeductionMapper.selectByPayId(payId);
		if(details == null || details.size() == 0){
			return;
		}
		
		StringBuilder commIds = new StringBuilder();
		FinancePayDetail detail = null;
		for(int i = 0; i < details.size(); i++){
			detail = details.get(i);
			if(i > 0){
				commIds.append(",");
			}
			commIds.append(detail.getLocOrderId());
		}
		
		List<FinanceCommission> comms = commissionDeductionMapper.selectByPrimaryKeys(commIds.toString());
		if(comms == null || comms.size() == 0){
			return;
		}
		
		FinanceCommission comm = null;
		for(int i = 0; i < comms.size(); i++){
			comm = comms.get(i);
			comm.setTotalCash(new BigDecimal(0));
			comm.setPayTime(null);
			comm.setPayUserName("");
			commissionDeductionMapper.update(comm);
		}
		
		payDetailCommDeductionMapper.deleteByPayId(payId);
		payCommDeductionMapper.deleteByPrimaryKey(payId);
	}

	@Override
	public List<FinanceGuide> selectListByGroupIdAndBookingId(Integer groupId, Integer bookingId) {

		return financeGuideMapper.selectListByGroupIdAndBookingId(groupId, bookingId);
	}

	@Override
	public List<BookingSupplier> getFinanceSupplierByFinanceGuide(FinanceGuide financeGuide) {
		List<BookingSupplier> list = supplierMapper.getFinanceSupplierByFinanceGuide(financeGuide);
		return list;
	}
	
	@Override
	public List<BookingSupplier> getFinanceSupplier(FinanceGuide financeGuide) {
		List<BookingSupplier> list = supplierMapper.getFinanceSupplier(financeGuide);
		return list;
	}
	
	/**
	 * 批量添加导游报账订单
	 * @param data
	 * @return
	 */
	@Transactional
	public void financeBatchSave(List<FinanceGuide> list) {
		if(list == null || list.size() == 0){
			return;
		}
		
		FinanceGuide item = null;
		for(int i = 0; i < list.size(); i++){
			item = list.get(i);
			this.financeSave(item);
		}
	}
	
	/**
	 * 添加导游报账订单
	 * @param data
	 * @return
	 */
	@Transactional
	public void financeSave(FinanceGuide item) {
		if(item == null){
			return;
		}
		
		//如果订单不存在，则不添加导游报账单
		BookingSupplier bs = supplierMapper.selectByPrimaryKey(item.getBookingIdLink());
		if(bs == null){
			return;
		}
		
		if(item.getBookingId() != null){
			//如果导游不存在，则不添加导游报账单
			BookingGuide bookingGuide = bookingGuideDal.selectByPrimaryKey(item.getBookingId());
			if(bookingGuide == null){
				return;
			}
		}
		
		boolean needUpdate = true;
		FinanceGuide fg = financeGuideMapper.selectByBookingIdLink(item.getBookingIdLink());
		if(fg == null){
			if(item.getBookingId() != null){
				financeGuideMapper.insertSelective(item);
			}
		}else{
			//如果报账金额不相等，或者导游不一致，则修改报账单
			if(item.getTotal().compareTo(fg.getTotal()) != 0 || item.getBookingId() != fg.getBookingId()){
				
				//如果修改了导游，则将原导游的报账记录删掉，给新导游增加报账记录
				if(item.getBookingId() != fg.getBookingId()){
					
					this.financeDelete(fg.getGroupId(), fg.getBookingIdLink(), fg.getBookingId());
					
					//如果新导游为空，则不添加找张记录
					if(item.getBookingId() != null){
						financeGuideMapper.insertSelective(item);
					}
				}else{
					fg.setTotal(item.getTotal());
					fg.setBookingId(item.getBookingId());
					financeGuideMapper.updateByPrimaryKeySelective(fg);
				}
			}else{
				needUpdate = false;
			}
		}
		if(needUpdate){
			//更新导游报账金额
			updateBookingGuideTotal(item.getGroupId(), item.getBookingId());
		}
	}

	@Override
	@Transactional
	public int save(List<FinanceGuide> list) {
		int id = 0;
		if (list.size() != 0) {
			BookingGuide bg = new BookingGuide();
			for (FinanceGuide financeGuide : list) {
				financeGuideMapper.insertSelective(financeGuide);
				bg.setId(financeGuide.getBookingId());
				id = financeGuide.getBookingId();
			}
			BigDecimal total=financeGuideMapper.selectSumTotalGroupIdAndBookingId(list.get(0).getGroupId(), list.get(0).getBookingId(),0);
			BigDecimal totalIncome=financeGuideMapper.selectSumTotalGroupIdAndBookingId(list.get(0).getGroupId(), list.get(0).getBookingId(),1);
			// 查询统计
			bg.setTotal(total.subtract(totalIncome));
			// bg.setTotal();
			guideMapper.updateByPrimaryKeySelective(bg);
		}

		return id;
	}
	
	/**
	 * 删除导游报账订单
	 * @param id
	 * @param bookingId
	 * @return
	 */
	@Transactional
	public void financeDelete(Integer groupId, Integer bookingIdLink,Integer bookingId) {
		if(groupId == null || bookingIdLink == null || bookingId == null){
			return;
		}
		financeGuideMapper.deleteByBookingIdLink(bookingIdLink);
		updateBookingGuideTotal(groupId, bookingId);
	}
	
	/**
	 * 更新团导游保障金额
	 * @param groupId
	 * @param bookingId
	 */
	@Transactional
	public void updateBookingGuideTotal(Integer groupId, Integer bookingId){
		
		if(groupId == null || bookingId == null){
			return;
		}
		
		BigDecimal total=financeGuideMapper.selectSumTotalGroupIdAndBookingId(groupId, bookingId, 0);
		BigDecimal totalIncome=financeGuideMapper.selectSumTotalGroupIdAndBookingId(groupId, bookingId, 1);
		
		BookingGuide bg = new BookingGuide();
		bg.setId(bookingId);
		// 查询统计
		bg.setTotal(total.subtract(totalIncome));
		// bg.setTotal();
		guideMapper.updateByPrimaryKeySelective(bg);
	}
	
	@Override
	@Transactional
	public int deleteByPrimaryKey(Integer id) {

		return financeGuideMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insertSelective(FinanceGuide record) {
		return financeGuideMapper.insertSelective(record);
	}

	@Override
	public List<CheckSheetFinance> selectListByGroupIdAndBookingIdAndType(
			Integer groupId, Integer bookingId, Integer supplierType) {
		return financeGuideMapper.selectListByGroupIdAndBookingIdAndType(groupId, bookingId, supplierType);
	}

	@Override
	public FinanceGuide selectByPrimaryKey(Integer id) {
		return financeGuideMapper.selectByPrimaryKey(id);
	}

	@Override
	public Integer selectCountByBookingId(Integer bookingId) {
		return financeGuideMapper.selectCountByBookingId(bookingId);
	}

	@Override
	public List<Map<String, String>> getGuideNameList(Integer bizId, String name) {
		return financeGuideMapper.getGuideNameList(bizId,name);
	}
	
	/**
	 * 查询团导游发放佣金
	 * @param groupId
	 * @return
	 */
	@Override
	public List<FinanceCommission> selectCommissionByGroupId(Integer groupId) {
		return commissionMapper.selectByGroupId(groupId);
	}
	
	/**
	 * 查询团导游扣除佣金
	 * @param groupId
	 * @return
	 */
	@Override
	public List<FinanceCommission> selectCommissionDeductionByGroupId(Integer groupId) {
		return commissionDeductionMapper.selectByGroupId(groupId);
	}
	
	/**
	 * 添加团导游发放佣金
	 * @param com
	 */
	@Override
	@Transactional
	public void batchInsertCommission(Integer bizId, Integer userId, String userName, List<FinanceCommission> list){
		
		if(list == null || list.size() == 0){
			return ;
		}
		
		Integer groupId = list.get(0).getGroupId();
		commissionMapper.deleteByGroupId(bizId, groupId);
		
		FinanceCommission com = null;
		for(int i = 0; i < list.size(); i++){
			
			com = list.get(i);
			BigDecimal total = com.getTotal(); 
			if(total == null){
				continue;
			}
			//当佣金为扣除的时候，存储负数
			if("扣除".equals(com.getCashType())){
				com.setTotal(total.subtract(total).subtract(total));
			}
			
			com.setBizId(bizId);
			com.setStateFinance(0);
			com.setCreateUser(userName);
			com.setCreateUserId(userId);
			com.setCreateTime(DateUtils.getDate());
			commissionMapper.insert(com);
		}
		
		financeDal.calcTourGroupAmount(groupId);
	}
	
	/**
	 * 添加团导游扣除佣金
	 * @param com
	 */
	@Override
	@Transactional
	public void batchInsertCommissionDeduction(Integer bizId, Integer userId, String userName, List<FinanceCommission> list){
		
		if(list == null || list.size() == 0){
			return ;
		}
		
		Integer groupId = list.get(0).getGroupId();
		commissionDeductionMapper.deleteByGroupId(bizId, groupId);
		
		FinanceCommission com = null;
		for(int i = 0; i < list.size(); i++){
			
			com = list.get(i);
			BigDecimal total = com.getTotal(); 
			if(total == null){
				continue;
			}
			//当佣金为扣除的时候，存储负数
			if("扣除".equals(com.getCashType())){
				com.setTotal(total.subtract(total).subtract(total));
			}
			
			com.setBizId(bizId);
			com.setStateFinance(0);
			com.setCreateUser(userName);
			com.setCreateUserId(userId);
			com.setCreateTime(DateUtils.getDate());
			commissionDeductionMapper.insert(com);
		}
	}

	/**
	 * 删除单条导游佣金
	 */
	@Override
	public void deleteCommission(Integer bizId, Integer id) {
		commissionMapper.deleteByPrimaryKey(bizId, id);
	}
	
	/**
	 * 删除团导游发放佣金
	 */
	public void deleteCommissionByGroupId(Integer bizId, Integer groupId) {
		commissionMapper.deleteByGroupId(bizId, groupId);
		//修改团更新时间，已方便之后根据团时间归档
		groupMapper.updateTimestampByPrimaryKey(groupId);
	}
	
	/**
	 * 删除团导游扣除佣金
	 */
	public void deleteCommissionDeductionByGroupId(Integer bizId, Integer groupId) {
		commissionDeductionMapper.deleteByGroupId(bizId, groupId);
	}

	@Override
	public List<FinanceCommission> getFinanceCommisionByGroupIdAndGuideId(Integer bizId,Integer groupId,
			Integer guideId) {
		return commissionMapper.getFinanceCommisionByGroupIdAndGuideId(bizId,groupId,guideId);
	}
	
	@Override
	public List<FinanceCommission> getFinanceCommisionDeductionByGroupIdAndGuideId(Integer bizId,Integer groupId,
			Integer guideId) {
		return commissionDeductionMapper.getFinanceCommisionByGroupIdAndGuideId(bizId,groupId,guideId);
	}

	/**
	 * 购物佣金发放统计
	 */
	@Override
	public PageBean getCommisionStatsListPage(PageBean pageBean) {
		List<Map<String, Object>> data = commissionMapper.getCommisionStatsListPage(pageBean);
		pageBean.setResult(data);
		return pageBean;
	}
	
	/**
	 * 购物佣金扣除统计
	 */
	@Override
	public PageBean getCommisionDeductionStatsListPage(PageBean pageBean) {
		List<Map<String, Object>> data = commissionDeductionMapper.getCommisionStatsListPage(pageBean);
		pageBean.setResult(data);
		return pageBean;
	}
	
	@Override
	public BookingGuide parseDataToGuide(Map<String, Object> dataMap, PageBean pageBean, boolean isDeduction){
		
		//根据团对数据分组
		Map<Integer, TourGroup> groupMap = new HashMap<Integer, TourGroup>();
		TourGroup group = null;
		FinanceCommission comm = null;
		
		Integer groupId = getIntegerValueOfMap(dataMap, "group_id");
		String groupCode = getStringValueOfMap(dataMap, "group_code");
		Integer groupMode = getIntegerValueOfMap(dataMap, "group_mode");
		Integer guideId =  getIntegerValueOfMap(dataMap, "guide_id");
		String  guideName = getStringValueOfMap(dataMap, "guide_name");
		Integer totalAdult =  getIntegerValueOfMap(dataMap, "total_adult");
		Integer totalChild =  getIntegerValueOfMap(dataMap, "total_child");
		Integer totalGuide =  getIntegerValueOfMap(dataMap, "total_guide");
		Integer operatorId =  getIntegerValueOfMap(dataMap, "operator_id");
		String operatorName = getStringValueOfMap(dataMap, "operator_name");
		Integer groupState = getIntegerValueOfMap(dataMap, "group_state");
		String productBrandName = getStringValueOfMap(dataMap, "product_brand_name");
		String productName = getStringValueOfMap(dataMap, "product_name");
		
		group = groupMap.get(groupId);
		group = new TourGroup();
		group.setId(groupId);
		group.setOperatorId(operatorId);
		group.setOperatorName(operatorName);
		group.setGroupMode(groupMode);
		group.setGroupState(groupState);
		group.setProductBrandName(productBrandName);
		group.setProductName(productName);
		groupMap.put(groupId, group);
		
		BookingGuide guide = new BookingGuide();
		guide.setGuideId(guideId);
		guide.setGuideName(guideName);
		guide.setGroupId(groupId);
		guide.setGroupCode(groupCode);
		guide.setPersonNum(totalAdult + totalChild + totalGuide);
		guide.setGroup(group);
		
		Map request = (Map)pageBean.getParameter();
		request.put("groupId", groupId);
		request.put("guideId", guideId);
		List<FinanceCommission> comms = null;
		if(!isDeduction){
			comms = commissionMapper.getCommisions(pageBean);	
		}else{
			comms = commissionDeductionMapper.getCommisions(pageBean);
		}
		guide.setComms(comms);
		
		return guide;
	}
	
	private String getStringValueOfMap(Map<String, Object> map, String key){
		if("".equals(key)){
			return "";
		}
		
		Object value = map.get(key);
		if(value != null){
			return value.toString();
		}
		return "";
	}
	
	private Integer getIntegerValueOfMap(Map<String, Object> map, String key){
		if("".equals(key)){
			return 0;
		}
		try{
			Object value = map.get(key);
			if(value != null){
				
				return Integer.parseInt(value.toString());
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return 0;
	}

	@Override
	public PageBean querySettleListPage(PageBean pageBean, Integer bizId) {
		StringBuffer str=new StringBuffer();
		List<BookingGuide> result = financeGuideMapper.querySettleListPage(pageBean, bizId);
		
		for (int i=0;i<result.size();i++) {
			Map bookingGuide = new HashMap<String, Object>();
			bookingGuide = (Map) result.get(i);
			//统计finance_guide已结算金额.除了收入类型
			BigDecimal sumTotal = financeGuideMapper.selectSumTotalPay(Integer.parseInt(bookingGuide.get("group_id").toString()), Integer.parseInt(bookingGuide.get("id").toString()),0);
			//统计finance_guide已结算收入类型金额.
			BigDecimal sumTotalIncome = financeGuideMapper.selectSumTotalPay(Integer.parseInt(bookingGuide.get("group_id").toString()), Integer.parseInt(bookingGuide.get("id").toString()),1);
			bookingGuide.put("total_cash", sumTotal.subtract(sumTotalIncome));
			
		}
		pageBean.setResult(result);
		return pageBean;
	}
	
	@Override
	public BigDecimal getSumTotal(PageBean pageBean, Integer bizId) {
		return financeGuideMapper.getSumTotal(pageBean, bizId);
	}
	
	@Override
	public BigDecimal getSumTotalCash(PageBean pageBean, Integer bizId) {
		
		/**
		List<BookingGuide> result = financeGuideMapper.querySettleList(pageBean, bizId);
		BigDecimal sumTotalCash=new BigDecimal(0);
		for (int i=0;i<result.size();i++) {
			Map bookingGuide = new HashMap<String, Object>();
			bookingGuide = (Map) result.get(i);
			//统计finance_guide已结算金额.除了收入类型
			BigDecimal sumTotal = financeGuideMapper.selectSumTotalPay(Integer.parseInt(bookingGuide.get("group_id").toString()), Integer.parseInt(bookingGuide.get("id").toString()),0);
			//统计finance_guide已结算收入类型金额.
			BigDecimal sumTotalIncome = financeGuideMapper.selectSumTotalPay(Integer.parseInt(bookingGuide.get("group_id").toString()), Integer.parseInt(bookingGuide.get("id").toString()),1);
			sumTotalCash=sumTotalCash.add(sumTotal.subtract(sumTotalIncome));
		}
		**/
		
		BigDecimal sumTotal = financeGuideMapper.selectSumTotalPay2(pageBean, 0);
		BigDecimal sumTotalIncome = financeGuideMapper.selectSumTotalPay2(pageBean, 1);
		BigDecimal sumTotalCash = new BigDecimal(0);
		sumTotalCash = sumTotalCash.add(sumTotal.subtract(sumTotalIncome));
		return sumTotalCash;
	}
	
	@Override
	@Transactional
	public void payCommBill(FinancePay pay) {
		
		if(StringUtils.isEmpty(pay.getCommissionIds())){
			return;
		}
		
		//根据佣金ID查询要结算的佣金
		List<FinanceCommission> list = commissionMapper.selectUnPayCommByPrimaryKeys(pay.getCommissionIds());
		
		//0为付款
		pay.setPayDirect(0);
		pay.setCreateTime(System.currentTimeMillis());
		pay.setPayDate(Calendar.getInstance().getTime());
		pay.setId(null);
		pay.setCash(new BigDecimal(0));
		pay.setType(1); //购物佣金付款
		payMapper.insert(pay);
		
		BigDecimal totalCash = new BigDecimal(0);
		
		Integer groupId = 0;
		FinanceCommission modify = null;
		for (int i = 0; i < list.size(); i++) {
			
			modify = list.get(i);
			if(i == 0){
				groupId = modify.getGroupId();
			}
			
			BigDecimal totalB = modify.getTotal();
			modify.setTotalCash(totalB);
			modify.setStateCommission(2);		
			String opeateLog = modify.getOperateLog() != null ? modify.getOperateLog() : ""; 
			
			String type = "发放";
			int ret =totalB.compareTo(BigDecimal.ZERO); 
			if(ret == -1){
				//如果<0 为扣款
				type = "扣除";
			}
			Date currDate = new Date();
			modify.setOperateLog(opeateLog + DateUtils.format(currDate, DateUtils.FORMAT_LONG) +" "
					+ pay.getUserName() +"给导游"+ modify.getGuideName() + type + modify.getTotalCash().abs() +"元\n" );
			modify.setPayUserName(pay.getUserName());
			modify.setPayTime(currDate);
			
			commissionMapper.update(modify);	//设置为已报账，并记录日志
			FinancePayDetail detail = new FinancePayDetail();
			detail.setLocOrderId(modify.getId());
			detail.setPayId(pay.getId());
			detail.setCash(modify.getTotal());
			payDetailMapper.insert(detail);
			totalCash = totalCash.add(modify.getTotal());
		}
		pay.setCash(totalCash);
		payMapper.update(pay);
		
		// 统计整团收支
		financeDal.calcTourGroupAmount(groupId);
	}
	
	@Override
	@Transactional
	public void payCommDeductionBill(FinancePay pay) {
		
		if(StringUtils.isEmpty(pay.getCommissionIds())){
			return;
		}
		
		//根据佣金ID查询要结算的佣金
		List<FinanceCommission> list = commissionDeductionMapper.selectUnPayCommByPrimaryKeys(pay.getCommissionIds());
		
		//0为付款
		pay.setPayDirect(0);
		pay.setCreateTime(System.currentTimeMillis());
		pay.setPayDate(Calendar.getInstance().getTime());
		pay.setId(null);
		pay.setCash(new BigDecimal(0));
		pay.setType(1); //购物佣金扣除付款
		payCommDeductionMapper.insert(pay);
		
		BigDecimal totalCash = new BigDecimal(0);
		
		FinanceCommission modify = null;
		for (int i = 0; i < list.size(); i++) {
			
			modify = list.get(i);
			
			BigDecimal totalB = modify.getTotal();
			modify.setTotalCash(totalB);
			modify.setStateCommission(2);		
			String opeateLog = modify.getOperateLog() != null ? modify.getOperateLog() : ""; 
			
			String type = "发放";
			int ret =totalB.compareTo(BigDecimal.ZERO); 
			if(ret == -1){
				//如果<0 为扣款
				type = "扣除";
			}
			Date currDate = new Date();
			modify.setOperateLog(opeateLog + DateUtils.format(currDate, DateUtils.FORMAT_LONG) +" "
					+ pay.getUserName() +"给导游"+ modify.getGuideName() + type + modify.getTotalCash().abs() +"元\n" );
			modify.setPayUserName(pay.getUserName());
			modify.setPayTime(currDate);
			
			commissionDeductionMapper.update(modify);	//设置为已报账，并记录日志
			FinancePayDetail detail = new FinancePayDetail();
			detail.setLocOrderId(modify.getId());
			detail.setPayId(pay.getId());
			detail.setCash(modify.getTotal());
			payDetailCommDeductionMapper.insert(detail);
			totalCash = totalCash.add(modify.getTotal());
		}
		pay.setCash(totalCash);
		payCommDeductionMapper.update(pay);
	}

	@Override
	public PageBean selectGuideCashRecordListPage(PageBean pageBean) {
		List<Map<String, Object>> results = payMapper.selectGuideCashRecordListPage(pageBean);
		pageBean.setResult(results);
		return pageBean;
	}

	@Override
	public List<Map<String, Object>> selectGuidePayDetailsByPayId(Integer payId) {
		return payDetailMapper.selectGuidePayDetailsByPayId(payId);
	}
	
	@Override
	public List<Map<String, Object>> selectCommPayDetailsByPayId(Integer payId) {
		return payDetailMapper.selectCommPayDetailsByPayId(payId);
	}
	
	@Override
	public List<Map<String, Object>> selectCommPayDetailsDeductionByPayId(Integer payId) {
		return payDetailCommDeductionMapper.selectCommPayDetailsByPayId(payId);
	}

	@Override
	public Map<String, Object> getCommisionsSum(PageBean pageBean) {
		return commissionMapper.getCommisionsTotal(pageBean);
	}
	
	@Override
	public Map<String, Object> getCommisionsDeductionSum(PageBean pageBean) {
		return commissionDeductionMapper.getCommisionsTotal(pageBean);
	}
	
	@Override
	public BigDecimal selectCommTotalByGroupId(Integer groupId) {
		Map<String, Object> map = commissionMapper.selectTotalByGroupId(groupId); 
		if(map != null){
			return NumberUtil.parseObj2Num(map.get("sum_total"));
		}
		return new BigDecimal(0);
	}

//	@Override
//	public FinanceGuide selectByBookingIdLink(Integer bookingIdLink) {
//		return financeGuideMapper.selectByBookingIdLink(bookingIdLink);
//	}
//
//	@Override
//	public int deleteByBookingIdLink(Integer bookingIdLink) {
//		return financeGuideMapper.deleteByBookingIdLink(bookingIdLink);
//	}
//
//	@Override
//	public BigDecimal selectTotalByBookingIdLinkAndType(Integer bookingIdLink,
//			Integer type) {
//		return financeGuideMapper.selectTotalByBookingIdLinkAndType(bookingIdLink, type);
//	}
	
	@Override
	public Integer getCommisionTotalSum(Integer groupId, Integer guideId) {
		
		if(groupId == null || guideId == null){
			return 0;
		}
		
		return commissionMapper.getCommisionTotalSum(groupId, guideId);
	}
	
	@Override
	public Map<String, Object> getCommisionTotalSumAndTotalCashSum(Integer groupId, Integer guideId) {
		
		if(groupId == null || guideId == null){
			return null;
		}
		return commissionMapper.getCommisionTotalSumAndTotalCashSum(groupId, guideId);
	}

	@Override
	public List<FinanceCommission> getCommisions(PageBean pageBean) {
		return commissionMapper.getCommisions(pageBean);
	}
	
	@Override
	public List<FinanceCommission> getCommisionDeductions(PageBean pageBean) {
		return commissionDeductionMapper.getCommisions(pageBean);
	}

	@Override
	public Map<String, Object> getAllTotalSumAndTotalCashSum(PageBean pageBean) {
		return commissionMapper.getAllTotalSumAndTotalCashSum(pageBean);
	}

	@Override
	public Map<String, Object> selectGuidePayInfo(Integer payId) {
		if(payId == null){
			return null;
		}
		
		return payMapper.selectGuidePayInfo(payId);
	}
	
	@Override
	public Map<String, Object> selectCommPayInfo(Integer payId) {
		if(payId == null){
			return null;
		}
		
		return payMapper.selectCommPayInfo(payId);
	}
	
	@Override
	public Map<String, Object> selectCommPayDeductionInfo(Integer payId) {
		if(payId == null){
			return null;
		}
		
		return payCommDeductionMapper.selectCommPayInfo(payId);
	}

	@Override
	public void changeGroup(Integer groupId, Integer guideId, Integer mguideId) {
		financeBillMapper.changeGroup(groupId,guideId,mguideId);
	}
}
