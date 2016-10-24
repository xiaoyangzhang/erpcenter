package com.yimayhd.erpcenter.dal.product.solr.converter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.time.DateUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.common.SolrInputDocument;
import org.apache.solr.common.params.GroupParams;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.dal.product.dto.ProductStateDTO;
import com.yimayhd.erpcenter.dal.product.dto.ProductStockDTO;
import com.yimayhd.erpcenter.dal.product.po.ProductInfo;
import com.yimayhd.erpcenter.dal.product.po.ProductStock;
import com.yimayhd.erpcenter.dal.product.query.ProductStatePageQueryDTO;
import com.yimayhd.erpcenter.dal.product.query.ProductStockPageQueryDTO;
import com.yimayhd.erpcenter.dal.product.solr.SolrSearchPageDTO;
import com.yimayhd.erpcenter.dal.product.solr.util.ParamCheckUtil;
import com.yimayhd.erpcenter.dal.product.vo.StockStaticCondition;
import com.yimayhd.erpcenter.dal.product.vo.StockStaticsResultVOPlus;

public class ProductStockConverter {


	public static SolrInputDocument productStateListConverter(ProductStockDTO productStockDTO) {
		if (null == productStockDTO) {
			return null;
		}

		SolrInputDocument doc = new SolrInputDocument();
		
		doc.addField("infoId", productStockDTO.getInfoId());
		doc.addField("infoBizId", productStockDTO.getInfoBizId());
		doc.addField("infoBrandId", productStockDTO.getInfoBrandId());
		doc.addField("infoBrandName", ParamCheckUtil.checkString(productStockDTO.getInfoBrandName()));
		doc.addField("infoNameCity", ParamCheckUtil.checkString(productStockDTO.getInfoNameCity()));
		doc.addField("infoState", productStockDTO.getInfoState());
		doc.addField("psItemDate", productStockDTO.getPsItemDate());
		doc.addField("psId", productStockDTO.getPsId());
		doc.addField("psStockCount", productStockDTO.getPsStockCount());
		doc.addField("psReceiveCount", productStockDTO.getPsReceiveCount());
		doc.addField("psState", productStockDTO.getPsState());
		doc.addField("prOrgId", ParamCheckUtil.checkString(productStockDTO.getPrOrgId()));

		return doc;
	}

	/**
	 * 
	 * @param pageBean
	 * @param parameters
	 * @return
	 */
	public static ProductStockPageQueryDTO toQueryDTO(PageBean<StockStaticCondition> pageBean) {
		ProductStockPageQueryDTO dto=new ProductStockPageQueryDTO();
		StockStaticCondition stock=(StockStaticCondition) pageBean.getParameter();
		dto.setInfoBizId(stock.getBizId());
		dto.setInfoBrandId(stock.getBrandId());
		dto.setInfoNameCity(stock.getProductName());
		dto.setInfoState((byte) -1);//状态是固定值
		dto.setPrOrgId(stock.getOrgId()+"");
		dto.setPsState(-1);
		dto.setPsItemDateStart(stock.getGroupDate());
		dto.setPsItemDateEnd(stock.getToGroupDate());
		dto.setPageNo(pageBean.getPage());
		dto.setPageSize(pageBean.getPageSize());
		return dto;
	}

	/**
	 * 
	 * @param solrPageResult
	 * @return
	 */
	public static List<StockStaticsResultVOPlus> dto2PageBean(SolrSearchPageDTO<ProductStockDTO> solrPageResult) {
		List<StockStaticsResultVOPlus> listResult=new ArrayList<StockStaticsResultVOPlus>();
		List<ProductStockDTO> solrList=solrPageResult.getList();
		if(solrList!=null&&!solrList.isEmpty()){
			for(ProductStockDTO dto:solrList){
				StockStaticsResultVOPlus vo=new StockStaticsResultVOPlus();
				vo.setReceiveCount(dto.getPsReceiveCount());
				vo.setStockCount(dto.getPsStockCount());
				vo.setBrandName(dto.getInfoBrandName());
				vo.setProductId(dto.getInfoId());
				//vo.setStockInfo(stockInfo);//实际库存数量为聚合函数查询值----------重要参数
				vo.setProductId(dto.getInfoId());
				listResult.add(vo);
			}
		}
		return listResult;
	}

	/**
	 * 
	 * @param queryDTO
	 * @return
	 */
	public static SolrQuery queryDTO2SolrQuery(ProductStockPageQueryDTO queryDTO) {

		SolrQuery solrQuery = new SolrQuery();
		
		solrQuery.setParam("q","*:*");
		solrQuery.setParam(GroupParams.GROUP,"true");
		solrQuery.add(GroupParams.GROUP_FIELD,"infoId");
		//solrQuery.add(GroupParams.GROUP_FIELD,"infoNameCity");
		//solrQuery.add(GroupParams.GROUP_FORMAT,"grouped");
		//solrQuery.add(GroupParams.GROUP_MAIN,"false");
		solrQuery.setParam("group.ngroups", true);		
		//solrQuery.set(GroupParams.GROUP_SORT,"infoBrandName asc,infoNameCity asc");
		solrQuery.setRows(queryDTO.getPageSize());
		solrQuery.setParam("group.limit",queryDTO.getStartRow() + "");

		return solrQuery;
	}

}
