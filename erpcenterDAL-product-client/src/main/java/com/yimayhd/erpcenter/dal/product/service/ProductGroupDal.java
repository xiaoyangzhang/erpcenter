package com.yimayhd.erpcenter.dal.product.service;

import java.util.Date;
import java.util.List;

import com.yimayhd.erpcenter.dal.product.po.ProductGroup;
import com.yimayhd.erpcenter.dal.product.vo.ProductGroupVo;

/**
 * @author : xuzejun
 * @date : 2015年7月2日 下午3:58:56
 * @Description: 价格组接口
 */
public interface ProductGroupDal {
	/**
	 * 插入
	 * @param record
	 * @return
	 */
	int save(ProductGroup productGroup);
	//返回主键
	int save3(ProductGroup productGroup);
	void save2(List<ProductGroup> productGroup,Integer groupSupplierId);

	
	List<ProductGroup> selectProductGroups(Integer productId);
	
	List<ProductGroup> selectProductGroupsBySellerId(Integer productId,Integer sellerId);


	/**
	 * 校验价格组名称唯一性
	 * @param name
	 * @param productId
	 */
	int validateName(String name, Integer productId,Integer id);

	/**
	 * 根据组id获取组信息
	 * @param groupId
	 * @return
	 */
	ProductGroup getGroupInfoById(Integer groupId);
	
	/**
	 * 根据产品组团社表id查询产品组
	 * @param groupSupplierId
	 * @return
	 */
	List<ProductGroup> selectProductGroupList(Integer groupSupplierId);
	
	/**
	 * 根据productId和supplierId获取价格组
	 * @param productId
	 * @param supplierId
	 * @return
	 */
	List<ProductGroupVo> selectGroupsByProdctIdAndSupplierId(Integer productId,Integer supplierId,Date groupDate);
	/**
	 * 向组团社拷贝价格组
	 * @param groupIdList
	 * @param supplierIdList
	 */
	void copyGroups(List<Integer> groupIdList, List<Integer> groupSupplierIdList);
	
	/**
	 * 组团版删除价格组后执行逻辑
	 * 1、删除用户组 2、删除价格
	 * @param groupId
	 */
	void afterDelAgencyGroup(Integer groupId);
	
	
}
