package com.yimayhd.erpcenter.dal.product.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.yihg.mybatis.utility.PageBean;

public interface TrafficResMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table traffic_res
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table traffic_res
     *
     * @mbggenerated
     */
    int insert(TrafficRes record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table traffic_res
     *
     * @mbggenerated
     */
    TrafficRes selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table traffic_res
     *
     * @mbggenerated
     */
    List<TrafficRes> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table traffic_res
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(TrafficRes record);
    
    
    int insertSelective(TrafficRes record);
    
    List<TrafficRes>selectTrafficResListPage(@Param("page") PageBean pageBean);
    
    
    int updateTrafficRes(TrafficRes record);
    
    List<TrafficRes>selectResDetails(@Param("id") Integer id);
    
    /**
	 * 复制功能查询TrafficRes
	 * @param id
	 * @param date
	 * @return
	 */
    TrafficRes selectTrafficResById(@Param("id")Integer id,@Param("date")String date);
    
    int updateStockOrStockDisable(@Param("id")Integer id);
    
   int updateTrafficResState(@Param("id")Integer id,@Param("state")Integer state);
   
   List<TrafficRes>selectDetailsStocklog(@Param("resId") Integer resId,@Param("adjustTime")String adjustTime);
}

