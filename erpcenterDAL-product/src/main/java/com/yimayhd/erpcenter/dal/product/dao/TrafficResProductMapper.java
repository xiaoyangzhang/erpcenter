package com.yimayhd.erpcenter.dal.product.dao;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.dal.product.po.TrafficResProduct;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

public interface TrafficResProductMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table traffic_res_product
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table traffic_res_product
     *
     * @mbggenerated
     */
    int insert(TrafficResProduct record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table traffic_res_product
     *
     * @mbggenerated
     */
    TrafficResProduct selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table traffic_res_product
     *
     * @mbggenerated
     */
    List<TrafficResProduct> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table traffic_res_product
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(TrafficResProduct record);
    
    List<TrafficResProduct> findTrafficProductResId(Integer resId);
    
    int insertTrafficResProduct(TrafficResProduct record);
    
    TrafficResProduct selectResProductId(@Param("id") Integer id);
    
    int updateToSaveResProduct(TrafficResProduct productBean);

    /**
   	 * 复制功能查询TrafficResProduct
   	 * @param resId
   	 * @return
   	 */
    List<TrafficResProduct> selectTrafficProductByResId(Integer resId);
    
    int delResProduct(@Param("id") Integer id);
    
    List<TrafficResProduct> findByResProductId(@Param("id") Set<Integer> id);
    
    int updateBysuggestPriceId(@Param("suggestPriceList") List<TrafficResProduct> suggestPriceList);
    
    int updateBySamePayId(@Param("samePayList") List<TrafficResProduct> samePayList);
    
    int updateByProxyPayId(@Param("proxyPayList") List<TrafficResProduct> proxyPayList);
    
    int updateByCostPricePriceId(@Param("costPriceList") List<TrafficResProduct> costPriceList);
    
    TrafficResProduct selectTrafficProductInfo(@Param("id") Integer id);
    
    int updateNumSoldById(TrafficResProduct record);
    
    TrafficResProduct selectTrafficProductInfoByProductCode(@Param("productCode") Integer productCode, @Param("resId") Integer resId);
    
    List<TrafficResProduct>selectResProductListPage(@Param("page") PageBean pageBean);
    
    TrafficResProduct findByResProId(@Param("id") Integer id);
    
    int selectResProductNameCount(@Param("productCode") Integer productCode, @Param("resId") Integer resId);
    
    TrafficResProduct selectNumSoldCount(@Param("resId") Integer resId);
    
    int updateResProductNumStock(@Param("id") Integer id, @Param("numStock") Integer numStock);
}