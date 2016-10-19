package com.yimayhd.erpcenter.dal.product.solr.converter;

import org.apache.solr.common.SolrInputDocument;

import com.yimayhd.erpcenter.dal.product.solr.util.ParamCheckUtil;
import com.yimayhd.erpcenter.dal.product.solrdump.domin.ProductStateDO;



public class ProductStateConverter {

	  public static SolrInputDocument productStateListConverter(ProductStateDO productStateDO) {
		  if (null == productStateDO) {
	            return null;
	        }
		  
		  SolrInputDocument doc = new SolrInputDocument();
		  
		  doc.addField("infoId", productStateDO.getInfoId());
		  doc.addField("infoBizId", productStateDO.getInfoBizId());
		  doc.addField("infoCode", ParamCheckUtil.checkString(productStateDO.getInfoCode()));
		  doc.addField("infoType", productStateDO.getInfoType());
		  doc.addField("infoBrandId", productStateDO.getInfoBrandId());
		  doc.addField("infoBrandName", ParamCheckUtil.checkString(productStateDO.getInfoBrandName()));
		  doc.addField("infoNameCity", ParamCheckUtil.checkString(productStateDO.getInfoNameCity()));
		  doc.addField("infoNameBrief", ParamCheckUtil.checkString(productStateDO.getInfoNameBrief()));
		  doc.addField("infoTravelDays", productStateDO.getInfoTravelDays());
		  doc.addField("infoOrderNum", productStateDO.getInfoOrderNum());
		  doc.addField("infoDestProvinceId", productStateDO.getInfoDestProvinceId());
		  doc.addField("infoDestProvinceName", ParamCheckUtil.checkString(productStateDO.getInfoDestProvinceName()));
		  doc.addField("infoDestCityId", productStateDO.getInfoDestCityId());
		  doc.addField("infoDestCityName", ParamCheckUtil.checkString(productStateDO.getInfoDestCityName()));
		  doc.addField("infoState", productStateDO.getInfoState());
		  doc.addField("infoCreatorId", productStateDO.getInfoCreatorId());
		  doc.addField("infoCreatorName", ParamCheckUtil.checkString(productStateDO.getInfoCreatorName()));
		  doc.addField("infoCreateTime", productStateDO.getInfoCreateTime());
		  doc.addField("infoOperatorId", productStateDO.getInfoOperatorId());
		  doc.addField("infoOperatorName", ParamCheckUtil.checkString(productStateDO.getInfoOperatorName()));
		  doc.addField("prOrgId", productStateDO.getPrOrgId());
		  doc.addField("prProductId", productStateDO.getPrProductId());
		  
		  return doc;
	  }
}
