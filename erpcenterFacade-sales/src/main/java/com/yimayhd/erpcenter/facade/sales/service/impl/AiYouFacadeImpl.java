/**
 * 
 */
package com.yimayhd.erpcenter.facade.sales.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.common.json.JSON;
import com.yimayhd.erpcenter.biz.basic.service.DicBiz;
import com.yimayhd.erpcenter.biz.basic.service.RegionBiz;
import com.yimayhd.erpcenter.biz.product.service.ProductGroupSupplierBiz;
import com.yimayhd.erpcenter.biz.product.service.ProductInfoBiz;
import com.yimayhd.erpcenter.biz.product.service.ProductRemarkBiz;
import com.yimayhd.erpcenter.biz.product.service.ProductRouteBiz;
import com.yimayhd.erpcenter.biz.product.service.ProductStockBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.FitOrderBiz;
import com.yimayhd.erpcenter.common.contants.BasicConstants;
import com.yimayhd.erpcenter.dal.basic.po.DicInfo;
import com.yimayhd.erpcenter.dal.basic.po.RegionInfo;
import com.yimayhd.erpcenter.dal.product.po.ProductGroupSupplier;
import com.yimayhd.erpcenter.dal.product.po.ProductInfo;
import com.yimayhd.erpcenter.dal.product.po.ProductRemark;
import com.yimayhd.erpcenter.dal.product.po.ProductRoute;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.AiYouBean;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrder;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrderGuest;
import com.yimayhd.erpcenter.dal.sales.client.sales.vo.FitOrderVO;
import com.yimayhd.erpcenter.dal.sys.po.PlatformEmployeePo;
import com.yimayhd.erpcenter.facade.sales.query.SaveFitOrderInfoDTO;
import com.yimayhd.erpcenter.facade.sales.query.ToImpAiYouOrderDTO;
import com.yimayhd.erpcenter.facade.sales.result.ToImpAiYouOrderResult;
import com.yimayhd.erpcenter.facade.sales.result.Tourist;
import com.yimayhd.erpcenter.facade.sales.service.AiYouFacade;
import com.yimayhd.erpcenter.facade.sales.utils.MD5Util;
import com.yimayhd.erpresource.biz.service.SupplierBiz;
import com.yimayhd.erpresource.dal.constants.Constants;
import com.yimayhd.erpresource.dal.po.SupplierInfo;

/**
 * @ClassName: BookingGuideFacadeImpl
 * @author hongfei.guo
 * @date 2016年10月27日
 */
public class AiYouFacadeImpl implements AiYouFacade {
	
	@Autowired
	private ProductInfoBiz productInfoBiz;
	
	@Autowired
	private ProductStockBiz productStockBiz;
	
	@Autowired
	private FitOrderBiz fitOrderBiz;
	
	@Autowired
	private ProductRemarkBiz productRemarkBiz;
	
	@Autowired
	private ProductGroupSupplierBiz productGroupSupplierBiz;
	
	@Autowired
	private DicBiz dicBiz;
	
	@Autowired
	private SupplierBiz supplierBiz;
	
	@Autowired
	private RegionBiz regionBiz;
	
	@Autowired
	private ProductRouteBiz productRouteBiz;
	
	@Override
	public List<ProductInfo> searchProductByNameAndDate(Integer bizId,
			String name, String date) {
		List<ProductInfo> prodList = productInfoBiz.searchProductByNameAndDate(bizId, name,
                date);
		return prodList;
	}

	@Override
	public Integer getRestCountByProductIdAndDate(Integer productId, Date date) {
		Integer count = productStockBiz.getRestCountByProductIdAndDate(productId, date);
		return count;
	}

	@Override
	public Integer saveFitOrderInfo(SaveFitOrderInfoDTO dto) {
		
		FitOrderVO fitOrderVO = dto.getFitOrderVO();
		
		Integer orderId = new Integer(0);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Integer newNum = fitOrderVO.getGroupOrder().getNumAdult() + fitOrderVO.getGroupOrder().getNumChild();
        Integer oldNum = 0;
        try {
            if (fitOrderVO.getGroupOrder().getId() != null) {
                FitOrderVO vo = fitOrderBiz.selectFitOrderVOById(fitOrderVO.getGroupOrder().getId());

                oldNum = vo.getGroupOrder().getNumAdult() + vo.getGroupOrder().getNumChild();
            }
            fitOrderVO.getGroupOrder().setOrderNo(dto.getBizCode());
            ProductInfo productInfo = productInfoBiz.findProductInfoById(fitOrderVO.getGroupOrder().getProductId());
            orderId = fitOrderBiz.saveOrUpdateFitOrderInfo(fitOrderVO, dto.getEmployeeId(),
                    dto.getEmployeeName(), productInfo.getOperatorId(), productInfo.getOperatorName(),
                    dto.getBizId(), dto.getBizCode(), false);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        try {
            productStockBiz.updateStockCount(fitOrderVO.getGroupOrder().getProductId(),
                    sdf.parse(fitOrderVO.getGroupOrder().getDepartureDate()), newNum - oldNum);
        } catch (Exception e) {
        	e.printStackTrace();
        }

        return orderId;
	}

	@Override
	public ToImpAiYouOrderResult toImpAiYouOrder(ToImpAiYouOrderDTO dto) {
		
		ToImpAiYouOrderResult result = new ToImpAiYouOrderResult();
		
        List<ProductGroupSupplier> supplierList = productGroupSupplierBiz.selectProductGroupSuppliers2(dto.getProductId()); // 获取价格下的组团社列表
        result.setSupplierList(supplierList);
        
        PlatformEmployeePo curUser = dto.getCurUser();
        ProductRemark productRemark = productRemarkBiz.findProductRemarkByProductId(dto.getProductId());
        ProductInfo productInfo = productInfoBiz.findProductInfoById(dto.getProductId());

		/* 读取团的详细信息 */
        HttpClient httpClient = new DefaultHttpClient();
        String getOrdersUrl = "http://{{ip}}/aiyou/api/erp.do?m=getGroupInfo&code={{supplier_code}}&group_id={{group_id}}"
                .replace("{{ip}}", Constants.aiYouUrlMap.get(dto.getSupplierCode())).replace("{{supplier_code}}", dto.getCode())
                .replace("{{group_id}}", dto.getAiyouId());

        Map<String, String> params = new HashMap<String, String>();
        params.put("m", "getGroupInfo");
        params.put("code", dto.getCode());
        params.put("group_id", dto.getAiyouId());
        String sign = MD5Util.getSign_Aiyou(dto.getCode(), MD5Util.MD5_Taobao(dto.getCode() + "erp"), params);
        getOrdersUrl += "&sign=" + sign;
        HttpGet getOrders = new HttpGet(getOrdersUrl);
        
        HttpResponse orders = null;
        String r = "";
        AiYouBean aiYouBean = null;
        try{
        	orders = httpClient.execute(getOrders);
            r = EntityUtils.toString(orders.getEntity());
            aiYouBean = JSON.parse(r, AiYouBean.class);
        }catch(Exception ex){
        	ex.printStackTrace();
        }
        getOrders.abort();

		/* 把数据放到groupOrder里 */
        FitOrderVO vo = new FitOrderVO();
        GroupOrder groupOrder = new GroupOrder();
        groupOrder.setDepartureDate(aiYouBean.getGroup_date());
        groupOrder.setAiyouGroupId(dto.getAiyouId());
        groupOrder.setSupplierCode(dto.getAiyouId());
        groupOrder.setNumAdult(aiYouBean.getAdult_num());
        groupOrder.setNumChild(aiYouBean.getChild_num());
        groupOrder.setSupplierCode(aiYouBean.getGroup_num());
        groupOrder.setReceiveMode(aiYouBean.getGroup_num().substring(8));
        groupOrder.setDepartureDate(dto.getDate());
        groupOrder.setProductName(aiYouBean.getProduct_name());
        groupOrder.setContactName(aiYouBean.getReseller_contact_name());
        groupOrder.setContactMobile(aiYouBean.getReseller_contact_mobile());
        groupOrder.setContactFax(aiYouBean.getReseller_fax());
        groupOrder.setContactTel(aiYouBean.getReseller_tel());
        String memo = "";
        if (aiYouBean.getMemo() != null) {
            memo += aiYouBean.getMemo() + "\n";
        }
        memo += "爱游产品名：" + aiYouBean.getProduct_name() + "\n";
        if (aiYouBean.getFlight_info() != null) {
            memo += "机票：" + aiYouBean.getFlight_info() + "\n";
        }
        if (aiYouBean.getRoom_info() != null) {
            memo += "用房：" + aiYouBean.getRoom_info() + "\n";
        }
        groupOrder.setRemarkInternal(memo);

        groupOrder.setOrderType(0);
        groupOrder.setSaleOperatorId(curUser.getEmployeeId());
        groupOrder.setSaleOperatorName(curUser.getName());
        groupOrder.setOperatorId(curUser.getEmployeeId());
        groupOrder.setOperatorName(curUser.getName());
        // supplierService.selectBySupplierId(Constants.supplierMap.get(supplierCode));
        SupplierInfo supplierInfo = supplierBiz.selectBySupplierId(Constants.supplierMap.get(dto.getSupplierCode())); // TODO:
        // 先都显示爱游店
        if (supplierInfo != null) {
            groupOrder.setSupplierId(supplierInfo.getId());
            groupOrder.setSupplierName(supplierInfo.getNameFull());
        }

        groupOrder.setProductId(productInfo.getId());
        groupOrder.setProductBrandId(productInfo.getBrandId());
        groupOrder.setProductBrandName(productInfo.getBrandName());
        groupOrder.setProductShortName(productInfo.getNameCity());
        groupOrder.setProductName(productInfo.getNameCity());
        groupOrder.setRemark(productRemark.getRemarkInfo());
        groupOrder.setServiceStandard(productRemark.getServeLevel());

        vo.setGroupOrder(groupOrder);

        Date d = new Date();
		try {
			d = new SimpleDateFormat("yyyy-MM-dd").parse(dto.getDate());
		} catch (ParseException e) {
			e.printStackTrace();
		}
        int count = productStockBiz.getRestCountByProductIdAndDate(groupOrder.getProductId(), d);
        result.setCount(count); // 库存
        
        List<DicInfo> zjlxList = dicBiz.getListByTypeCode(BasicConstants.GYXX_ZJLX);
        result.setZjlxList(zjlxList);
        List<GroupOrderGuest> groupOrderGuestList = new ArrayList<GroupOrderGuest>();

        List<HashMap<String, String>> tourists = aiYouBean.getTourists();
        if (tourists != null && tourists.size() > 0) {
            GroupOrderGuest gog;
            for (HashMap<String, String> t : tourists) {
                Tourist tourist = new Tourist();
                tourist.setTourist_id(t.get("tourist_id"));
                tourist.setTourist_id_type(t.get("tourist_id_type"));
                tourist.setTourist_mobile(t.get("tourist_mobile"));
                tourist.setTourist_name(t.get("tourist_name"));
                gog = new GroupOrderGuest();
                gog.setName(tourist.getTourist_name());
                gog.setCertificateNum(tourist.getTourist_id());
                gog.setMobile(tourist.getTourist_mobile());
                if ("idcard".equals(tourist.getTourist_id_type())) {
                    gog.setCertificateTypeId(dicBiz
                            .getDicInfoByTypeCodeAndDicCode(BasicConstants.GYXX_ZJLX, BasicConstants.GYXX_ZJLX_SFZ)
                            .getId());
                } else if ("passport".equals(tourist.getTourist_id_type())) {
                    gog.setCertificateTypeId(dicBiz
                            .getDicInfoByTypeCodeAndDicCode(BasicConstants.GYXX_ZJLX, BasicConstants.GYXX_ZJLX_HZ)
                            .getId());
                }
                groupOrderGuestList.add(gog);
            }
        }

        vo.setGroupOrderGuestList(groupOrderGuestList);

        int bizId = dto.getBizId();

        result.setFitOrderVO(vo);
        List<DicInfo> jdxjList = dicBiz.getListByTypeCode(BasicConstants.GYXX_JDXJ);
        result.setJdxjList(jdxjList);
        List<DicInfo> jtfsList = dicBiz.getListByTypeCode(BasicConstants.GYXX_JTFS, bizId);
        result.setJtfsList(jtfsList);

        List<DicInfo> sourceTypeList = dicBiz.getListByTypeCode(Constants.GUEST_SOURCE_TYPE, bizId);
        result.setSourceTypeList(sourceTypeList);
        
        List<RegionInfo> allProvince = regionBiz.getAllProvince();
        result.setAllProvince(allProvince);
        
        List<DicInfo> lysfxmList = dicBiz.getListByTypeCode(BasicConstants.GYXX_LYSFXM, dto.getBizId());
        result.setLysfxmList(lysfxmList);
        return result;
	}
	
	@Override
	public DicInfo getDicInfoByTypeCodeAndDicCode(Integer bizId, String typeCode, String dicCode){
		return dicBiz.getDicInfoByTypeCodeAndDicCode(bizId, typeCode, dicCode);
	}

	@Override
	public DicInfo getDicInfoByTypeCodeAndDicCode(String typeCode, String dicCode) {
		return dicBiz.getDicInfoByTypeCodeAndDicCode(typeCode, dicCode);
	}
	
	@Override
	public ProductRemark findProductRemarkByProductId(Integer productId) {
		ProductRemark productRemark = productRemarkBiz.findProductRemarkByProductId(productId);
		return productRemark;
	}

	@Override
	public ProductInfo findProductInfoById(Integer productId) {
		ProductInfo productInfo = productInfoBiz.findProductInfoById(productId);
		return productInfo;
	}

	@Override
	public List<ProductRoute> findProductRouteByProductId(Integer productId) {
		List<ProductRoute> productRouteList = productRouteBiz.findProductRouteByProductId(productId);
		return productRouteList;
	}
	
	
}
