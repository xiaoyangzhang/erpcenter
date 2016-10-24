package com.yihg.operation.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.ibatis.annotations.Param;

import com.yihg.mybatis.utility.PageBean;
import com.yihg.operation.po.BookingShop;
import com.yihg.operation.po.BookingShopDet;
import com.yihg.operation.po.BookingShopDetail;
import com.yihg.operation.po.BookingShopSelect;
import com.yihg.operation.vo.QueryGuideShop;
import com.yihg.operation.vo.QueryShopInfo;

public interface BookingShopMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BookingShop record);

    int insertSelective(BookingShop record);

    BookingShop selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BookingShop record);

    int updateByPrimaryKey(BookingShop record);

    List<BookingShop> getShopListByGroupId(Integer groupId);

	Integer getSelectCountByGruopId(Integer groupId);
	int updatetotalFace(int bId);

	int getBookingCountByTime(@Param("curDay")Long curDay,@Param("nextDay")Long nextDay);
	
	List<BookingShopDet> getShopListByGroupIdAndShopDate(@Param("groupId")Integer groupId,@Param("shopDate")String shopDate);

	

	List<QueryGuideShop> getGuideShopListPage(@Param("page") PageBean pageBean,@Param("set")Set<Integer> set);

	BookingShopSelect getShopSelect(@Param("groupId")Integer groupId);

	List<QueryShopInfo> getshopInfoDetailListPage(@Param("page")PageBean pageBean);

	List<BookingShop> selectShopListPage(@Param("page") PageBean pageBean, @Param("bizId")Integer bizId);

	List<BookingShopDetail> getShopDetailListByShopId(@Param("page") PageBean pageBean, @Param("id")Integer id);

	List<BookingShop> selectShopVerifyListPage(@Param("page") PageBean pageBean, @Param("bizId")Integer bizId);
	Integer selectShopVerifySum(@Param("page") PageBean pageBean, @Param("bizId")Integer bizId);

	List<BookingShopDetail> getShopVerifyDetailListByShopId(@Param("page") PageBean pageBean, @Param("id")Integer id, @Param("sum")String sum);
	List<Map<String, Object>> getBookingShopList(@Param("groupId")Integer groupId,@Param("orderId")Integer orderId);

	List<BookingShop> getShopListByGroupIdAndGuideId(@Param("page") PageBean pageBean,@Param("groupId")Integer groupId, @Param("guideId")Integer guideId);

	List<BookingShop> getShopListByGroupIdAndSupplierId(@Param("groupId")Integer groupId,
			@Param("supplierId")Integer supplierId,@Param("guideId")Integer guideId);

	Integer existBookingShop(Integer supplierId);
	
	/**
	 * 根据导游安排中的groupID查询领单申请信息
	 * @param groupId
	 * @return
	 */
	List<BookingShop> selectByGroupId(BookingShop bs);
}