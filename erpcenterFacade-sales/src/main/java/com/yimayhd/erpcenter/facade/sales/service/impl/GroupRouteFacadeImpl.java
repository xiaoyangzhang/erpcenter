package com.yimayhd.erpcenter.facade.sales.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.yimayhd.erpcenter.biz.product.service.ProductInfoBiz;
import com.yimayhd.erpcenter.biz.product.service.ProductRemarkBiz;
import com.yimayhd.erpcenter.biz.product.service.ProductRouteBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.GroupOrderBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.GroupRouteBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.TourGroupBiz;
import com.yimayhd.erpcenter.dal.product.po.ProductAttachment;
import com.yimayhd.erpcenter.dal.product.po.ProductInfo;
import com.yimayhd.erpcenter.dal.product.po.ProductRemark;
import com.yimayhd.erpcenter.dal.product.po.ProductRoute;
import com.yimayhd.erpcenter.dal.product.po.ProductRouteSupplier;
import com.yimayhd.erpcenter.dal.product.po.ProductRouteTraffic;
import com.yimayhd.erpcenter.dal.product.vo.ProductRouteDayVo;
import com.yimayhd.erpcenter.dal.product.vo.ProductRouteVo;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrder;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupRoute;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupRouteAttachment;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupRouteSupplier;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupRouteTraffic;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.TourGroup;
import com.yimayhd.erpcenter.dal.sales.client.sales.vo.GroupRouteDayVO;
import com.yimayhd.erpcenter.dal.sales.client.sales.vo.GroupRouteVO;
import com.yimayhd.erpcenter.facade.sales.query.grouproute.EditGroupRouteDTO;
import com.yimayhd.erpcenter.facade.sales.result.BaseStateResult;
import com.yimayhd.erpcenter.facade.sales.result.grouproute.GetImpDataResult;
import com.yimayhd.erpcenter.facade.sales.result.grouproute.ToEditRouteListResult;
import com.yimayhd.erpcenter.facade.sales.result.grouproute.ToGroupRouteResult;
import com.yimayhd.erpcenter.facade.sales.result.grouproute.ToImpRouteListResult;
import com.yimayhd.erpcenter.facade.sales.service.GroupRouteFacade;

public class GroupRouteFacadeImpl implements GroupRouteFacade{
	
	@Autowired
	private TourGroupBiz tourGroupService;
	
	@Autowired
	private GroupRouteBiz groupRouteService;
	
	@Autowired
	private GroupOrderBiz groupOrderService;
	
	@Autowired
	private ProductRouteBiz productRouteService;
	
	@Autowired
	private ProductInfoBiz productInfoService;
	
	@Autowired
	private ProductRemarkBiz productRemarkService;
	
	public ToEditRouteListResult toEditRouteList(Integer groupId) {
		
		TourGroup tourGroup = tourGroupService.selectByPrimaryKey(groupId);
		
		ToEditRouteListResult result=new ToEditRouteListResult();
		result.setTourGroup(tourGroup);
		
		List<GroupRoute> list = groupRouteService.selectByGroupId(groupId);
		if (list != null && list.size() > 0) {
			result.setSuccess(false);
		} else {
			result.setSuccess(true);
		}
		
		return result;
	}

	public ToImpRouteListResult toImpRouteList(Integer productId, Integer orderId,Integer groupId){
	
		TourGroup tourGroup = tourGroupService.selectByPrimaryKey(groupId);
		ProductInfo productInfo = productInfoService.findProductInfoById(productId);
		ProductRemark productRemark = productRemarkService.findProductRemarkByProductId(productId);
		GroupOrder groupOrder = groupOrderService.findById(orderId);
		
		ToImpRouteListResult result=new ToImpRouteListResult();
		
		result.setGroupOrder(groupOrder);
		result.setProductInfo(productInfo);
		result.setProductRemark(productRemark);
		result.setTourGroup(tourGroup);
		
		return result;
	}

	public GetImpDataResult getImpData(Integer productId){
		
		ProductRouteVo productRouteVo = productRouteService.findByProductId(productId);
		
		GroupRouteVO groupRouteVO = new GroupRouteVO();
		
		List<GroupRouteDayVO> groupRouteDayVOList = new ArrayList<GroupRouteDayVO>();
		List<ProductRouteDayVo> productRoteDayVoList = productRouteVo.getProductRoteDayVoList();

		if (productRoteDayVoList != null && productRoteDayVoList.size() > 0) {
			for (ProductRouteDayVo productRouteDayVo : productRoteDayVoList) {
				ProductRoute productRoute = productRouteDayVo.getProductRoute();
				GroupRoute groupRoute = new GroupRoute();
				GroupRouteDayVO groupRouteDayVO = new GroupRouteDayVO();
				try {
					BeanUtils.copyProperties(groupRoute, productRoute);
					groupRoute.setId(null);
					List<ProductAttachment> productAttachmentsList = productRouteDayVo.getProductAttachments();
					List<GroupRouteAttachment> groupRouteAttachmentList = new ArrayList<GroupRouteAttachment>();
					if (productAttachmentsList != null && productAttachmentsList.size() > 0) {

						for (ProductAttachment productAttachment : productAttachmentsList) {
							GroupRouteAttachment groupRouteAttachment = new GroupRouteAttachment();
							BeanUtils.copyProperties(groupRouteAttachment, productAttachment);
							//System.out.println(groupRouteAttachment.getPath() + ";" + groupRouteAttachment.getName());
							groupRouteAttachment.setId(null);
							groupRouteAttachmentList.add(groupRouteAttachment);
						}
					}

					List<ProductRouteTraffic> productRouteTrafficList = productRouteDayVo.getProductRouteTrafficList();
					List<GroupRouteTraffic> groupRouteTrafficList = new ArrayList<GroupRouteTraffic>();
					if (productRouteTrafficList != null && productRouteTrafficList.size() > 0) {
						for (ProductRouteTraffic productRouteTraffic : productRouteTrafficList) {
							GroupRouteTraffic groupRouteTraffic = new GroupRouteTraffic();
							BeanUtils.copyProperties(groupRouteTraffic, productRouteTraffic);
							groupRouteTraffic.setId(null);
							groupRouteTrafficList.add(groupRouteTraffic);

						}
					}

					List<GroupRouteSupplier> groupRouteOptionsSupplierList = new ArrayList<GroupRouteSupplier>();
					List<ProductRouteSupplier> productOptionsSupplierList = productRouteDayVo
							.getProductOptionsSupplierList();
					if (productOptionsSupplierList != null && productOptionsSupplierList.size() > 0) {
						for (ProductRouteSupplier productRouteSupplier : productOptionsSupplierList) {
							GroupRouteSupplier groupRouteOptionsSupplier = new GroupRouteSupplier();
							BeanUtils.copyProperties(groupRouteOptionsSupplier, productRouteSupplier);
							groupRouteOptionsSupplier.setId(null);
							groupRouteOptionsSupplierList.add(groupRouteOptionsSupplier);
						}
					}
					groupRouteDayVO.setGroupRoute(groupRoute);
					groupRouteDayVO.setGroupRouteAttachmentList(groupRouteAttachmentList);
					groupRouteDayVO.setGroupRouteOptionsSupplierList(groupRouteOptionsSupplierList);
					groupRouteDayVO.setGroupRouteTrafficList(groupRouteTrafficList);

				} catch (Exception e) {
					GetImpDataResult result=new GetImpDataResult();
					result.setError("行程转换错误!");
					return result;
				}
				groupRouteDayVOList.add(groupRouteDayVO);
			}

		}
		groupRouteVO.setGroupRouteDayVOList(groupRouteDayVOList);

		GetImpDataResult result=new GetImpDataResult();
		result.setGroupRouteVO(groupRouteVO);
		result.setSuccess(true);
		return result;

	}

	public BaseStateResult saveGroupRoute(EditGroupRouteDTO editGroupRouteDTO){
		groupRouteService.saveGroupRoute(editGroupRouteDTO.getGroupRouteVO());
		return new BaseStateResult(true,null);
	}

	public GetImpDataResult getDataByGroupId(Integer groupId){
		GroupRouteVO groupRouteVO = groupRouteService.findGroupRouteByGroupId(groupId);
		GetImpDataResult result=new GetImpDataResult();
		result.setGroupRouteVO(groupRouteVO);
		return result;
	}
	
	public GetImpDataResult getDataByOrderId(Integer orderId){
		GroupRouteVO groupRouteVO = groupRouteService.findGroupRouteByOrderId(orderId);
		GetImpDataResult result=new GetImpDataResult();
		result.setGroupRouteVO(groupRouteVO);
		return result;
	}

	public ToGroupRouteResult toGroupRoute(Integer groupId) {
		TourGroup tourGroup = tourGroupService.selectByPrimaryKey(groupId);
		
		ToGroupRouteResult result=new ToGroupRouteResult();
		result.setTourGroup(tourGroup);
		
		return result;
	}

	public BaseStateResult editGroupRoute(EditGroupRouteDTO editGroupRouteDTO){
		groupRouteService.editGroupRoute(editGroupRouteDTO.getGroupRouteVO());
		return new BaseStateResult(true,null);
	}
}
