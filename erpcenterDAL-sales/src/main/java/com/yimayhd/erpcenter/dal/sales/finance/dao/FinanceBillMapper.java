package com.yimayhd.erpcenter.dal.sales.finance.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.ibatis.annotations.Param;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.FinanceBill;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.FinanceBillDetail;

public interface FinanceBillMapper {
	List<Map> statisBillListPage(PageBean<Map> pageBean);
	Map statisBillTotal(@Param("parameter") Map parameter);
	
	/**
	 * 查询派单列表
	 */
	List<FinanceBill> selectreceiveOrderListPage(@Param("page") PageBean pageBean,@Param("bizId") Integer bizId,
			@Param("type") String type, @Param("set")Set<Integer> set);
	
	/**
	 * 根据派单列表ID查询单据列表
	 */
	List<FinanceBillDetail> selectbillListByIds(@Param("bizId") Integer bizId,@Param("ids") String ids);
	
	/**
	 * 根据团id和导游id获取billList
	 * @param bizId
	 * @param id
	 * @param guideId
	 * @return
	 */
	List<FinanceBillDetail> getbillListByIdAndGuideId(@Param("bizId") Integer bizId,@Param("id") String id,@Param("guideId") String guideId);
	
	/**
	 * 查询领单
	 * @param bizId
	 * @param id
	 * @param guideId
	 * @return
	 */
	FinanceBill selectBillOrder(@Param("bizId") Integer bizId, @Param("groupId") Integer groupId, @Param("guideId") Integer guideId);
	
	/**
	 * 添加领单
	 * @param billOrder
	 * @return
	 */
	int insertBillOrder(FinanceBill billOrder);
	
	/**
	 * 删除领单
	 * @param bizId
	 * @param id
	 * @param guideId
	 */
	void deleteBillOrder(@Param("bizId") Integer bizId, @Param("groupId") Integer groupId, @Param("guideId") Integer guideId);
	
	/**
	 * 添加领单详情
	 * @param billDetail
	 * @return
	 */
	int insertBillDetail(FinanceBillDetail billDetail);
	
	/**
	 * 删除领单详情
	 * @param bizId
	 * @param id
	 * @param guideId
	 */
	void deleteBillDetail(@Param("bizId") Integer bizId, @Param("groupId") Integer groupId, @Param("guideId") Integer guideId);

	/**
	 * 派单保存
	 * @param id
	 * @param receiveNum
	 * @param billNumReceive
	 * @param remarkReceive
	 */
	void saveDistributeBill(@Param("id") Integer id,@Param("receivedNum") Integer receivedNum
			, @Param("billNumReceive") String billNumReceive, @Param("remarkReceive") String remarkReceive);
	/**
	 * 销单保存
	 * @param id
	 * @param returndNum
	 * @param billNumReturn
	 * @param remarkReturn
	 */
	void saveVerifyBill(@Param("id") Integer id,@Param("returnedNum") Integer returnedNum
			, @Param("billNumReturn") String billNumReturn, @Param("remarkReturn") String remarkReturn);
	
	/**
	 * 修改派单表
	 * @param userName
	 * @param groupId
	 * @param guideId
	 */
	void updateFinanceOrder(@Param("userName") String userName,@Param("groupId") Integer groupId
			,@Param("guideId") Integer guideId,@Param("date") Date date,@Param("type") String type);
	/**
	 * 获取申请人列表
	 * @return
	 */
	List<Map<String,String>> getFuzzyApplicantList(@Param("bizId") Integer bizId,@Param("name") String name);
	/**
	 * 获取操作员列表
	 * @return
	 */
	List<Map<String, String>> getUserNameList(@Param("bizId") Integer bizId,@Param("name") String name);
	/**
	 * 取消销单
	 * @param order_id
	 * @param type
	 */
	void delVerify(@Param("order_id") String order_id, @Param("type") String type);
	/**
	 * 取消领单
	 * @param group_id
	 * @param guide_id
	 */
	void delReceived(@Param("group_id")String group_id,@Param("guide_id") String guide_id);
	
	/**
	 * 查询领单详情
	 * @param bizId
	 * @param groupId
	 * @param guideId
	 * @return
	 */
	List<FinanceBillDetail> selectBillOrderDetail(@Param("bizId") Integer bizId, @Param("groupId") Integer groupId, @Param("guideId") Integer guideId);
	
	
	void deleteBillDetailById(@Param("id")Integer id);
	/**
	 * 领单
	 * @param id
	 * @param string
	 * @param integer
	 * @param string2
	 */
	void saveApplyBill(@Param("id") Integer id,@Param("billType") String billType
			, @Param("num") Integer num, @Param("remark") String remark);
	/**
	 * 
	 * @param bizId
	 * @param groupId
	 * @param guideId
	 * @param notIds
	 */
	void deleteBillDetailByIds(@Param("bizId") Integer bizId, @Param("groupId") Integer groupId, @Param("guideId") Integer guideId,
			@Param("notIds") String notIds);
	
	/**
	 * 根据导游安排中的groupID查询领单申请信息
	 * @param groupId
	 * @return
	 */
	List<FinanceBill> selectByGroupId(FinanceBill fBill);
	
	void changeGroup(@Param("groupId") Integer groupId, @Param("guideId") Integer guideId, @Param("mguideId") Integer mguideId);


}