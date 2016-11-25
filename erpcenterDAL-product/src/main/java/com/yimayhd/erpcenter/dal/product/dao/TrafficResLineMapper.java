package com.yimayhd.erpcenter.dal.product.dao;

import com.yihg.product.po.TrafficResLine;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TrafficResLineMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table traffic_res_line
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table traffic_res_line
     *
     * @mbggenerated
     */
    int insert(TrafficResLine record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table traffic_res_line
     *
     * @mbggenerated
     */
    TrafficResLine selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table traffic_res_line
     *
     * @mbggenerated
     */
    List<TrafficResLine> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table traffic_res_line
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(TrafficResLine record);
    
    int insertSelective(TrafficResLine record);

    List<TrafficResLine> selectTrafficResLine(Integer resId); 
    
    /**
   	 * 复制功能查询TrafficResLine
   	 * @param resId
   	 * @return
   	 */
    List<TrafficResLine> selectTrafficResLineByResId(@Param("dateDeparture") String dateDeparture, @Param("dateNew") String dateNew, @Param("resId") Integer resId);
    
    int updateTrafficResLine(TrafficResLine record);
    
    
}