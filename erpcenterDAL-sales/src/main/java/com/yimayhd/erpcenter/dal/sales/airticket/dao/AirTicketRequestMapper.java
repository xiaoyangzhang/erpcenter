package com.yimayhd.erpcenter.dal.sales.airticket.dao;

import com.yihg.airticket.po.AirTicketRequest;
import com.yihg.mybatis.utility.PageBean;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

public interface AirTicketRequestMapper {
	/**
	 * 单条插入
	 * @param record
	 * @return
	 */
    int insert(AirTicketRequest record);
    /**
     * 单条删除
     * @param record
     * @return
     */
    int delete(@Param("id") Integer id, @Param("bizId") Integer bizId,
               @Param("opId") Integer opId, @Param("opName") String opName, @Param("comment") String comment);
    /**
     * 单条更新
     * @param record
     * @return
     */
    int update(AirTicketRequest record);
    /**
     * 单条查询
     * @param record
     * @return
     */
    AirTicketRequest findRequest(@Param("id") Integer id, @Param("bizId") Integer bizId);
    /**
     * 分页查询
     * @param request
     * @return
     */
    List<AirTicketRequest> selectRequestListPage(PageBean<AirTicketRequest> pageBean);
    
    HashMap<String, Integer> countAppliedTickets(PageBean<AirTicketRequest> pageBean);
    HashMap<String, Integer> countAvailableTickets(PageBean<AirTicketRequest> pageBean);
    
    List<AirTicketRequest> selectRequestListPageWithGroupOrder(PageBean<AirTicketRequest> pageBean);
    
    int selectRequestIdByBKId(Integer bookingSupplierId);
    /**
     * 修改机票申请的状态
     */
    int setStatus(@Param("param") HashMap<String, Object> param);
    
    int countRequestByResource(@Param("resourceId") Integer resourceId, @Param("bizId") Integer bizId);
    
    List<Integer> findRequestsByResource(@Param("id") Integer id, @Param("bizId") Integer bizId);
    
    int updateBookingSupplierId(@Param("id") Integer id, @Param("bizId") Integer bizId, @Param("bookingSupplierId") Integer bookingSupplierId);
    
    List<Integer> selectRequestIdByOrderId(@Param("bizId") Integer bizId, @Param("orderId") Integer id);
    List<AirTicketRequest> findRequestsByGroupOrderId(@Param("bizId") Integer bizId, @Param("strOrderIds") String strOrderIds);
}