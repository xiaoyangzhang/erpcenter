package com.yimayhd.erpcenter.dal.sales.client.finance.service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.ibatis.annotations.Param;

import com.yihg.mybatis.utility.PageBean;
import com.yihg.sales.po.FinanceBill;
import com.yihg.sales.po.FinanceBillDetail;

/**
 * 业务查询 - 领单查询
 */
public interface FinanceBillService{
	PageBean<Map> statisBillList(PageBean<Map> pageBean);
	Map statisBillTotal(Map parameter);
	
	
	/**
	 * 查询拍单列表
	 * @param pageBean
	 * @param bizId
	 * @param type
	 * @return
	 */
	public PageBean selectreceiveOrderListSelectPage(PageBean pageBean, Integer bizId, String type, Set<Integer> set);
	
	/**
	 * 根据团id和导游id获取billList
	 * @param bizId
	 * @param id
	 * @param guideId
	 * @return
	 */
	public List<FinanceBillDetail> getbillListByIdAndGuideId(Integer bizId, String id, String guideId);

	
	/**
	 * 批量添加领单详情
	 * @param bizId
	 * @param userName
	 * @param json
	 */
	void batchInsertBillDetail(Integer bizId, String userName, String json);
	
	/**
	 * 删除领单
	 * @param bizId
	 * @param groupId
	 * @param guideId
	 */
	public void deleteBillOrder(Integer bizId, Integer groupId, Integer guideId);
	/**
	 * 派单保存
	 * @param billList
	 */
	void saveDistributeBill(String billList);
	/**
	 * 销单保存
	 * @param billList
	 */
	void saveVerifyBill(String billList);
	/**
	 * 修改派单表
	 * @param userName
	 * @param groupId
	 * @param guideId
	 */
	void updateFinanceOrder(String userName,Integer groupId,Integer guideId,Date date,String type);
	
	/**
	 * 获取申请人列表
	 * @param name
	 * @return
	 */
	List<Map<String,String>> getFuzzyApplicantList(Integer bizId,String name);
	/**
	 * 获取操作员列表
	 * @param name
	 * @return
	 */
	List<Map<String, String>> getUserNameList(Integer bizId, String name);
	/**
	 * 取消销单
	 * @param order_id
	 * @param type
	 */
	void delVerify(String order_id,String type);
	/**
	 * 取消领单
	 * @param order_id
	 * @param type
	 */
	void delReceived(String group_id, String guide_id);
	
	/**
	 * 根据导游安排张的groupID查询领单申请信息
	 * @param groupId
	 * @return
	 */
	List<FinanceBill> findByGroupId(FinanceBill fBill);
	
	
	
}
