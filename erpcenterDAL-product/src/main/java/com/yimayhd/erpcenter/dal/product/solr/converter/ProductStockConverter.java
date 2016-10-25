package com.yimayhd.erpcenter.dal.product.solr.converter;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.response.Group;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
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
import com.yimayhd.erpcenter.dal.product.solr.util.SolrDateUtil;
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
		//dto.setPrOrgId(stock.getOrgId()+"");
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
	
	
	public static StockStaticsResultVOPlus docList2PageBean(SolrDocumentList doclist) {
		StockStaticsResultVOPlus voResult=new StockStaticsResultVOPlus();
		int id=0;
		String brand_name="";
		String name_city="";
		String stockInfo="";
		try {
		
		if(doclist!=null&&doclist.size()>0){
			brand_name=doclist.get(0).get("infoBrandName")+"";
			name_city=doclist.get(0).get("infoNameCity")+"";
			id=Integer.parseInt(doclist.get(0).get("psReceiveCount")+"");
			for(SolrDocument doc:doclist){
				String stock_count=doc.get("psStockCount")+"";
				String receive_count=doc.get("psReceiveCount")+"";
				String item_date=SolrDateUtil.getLong2Date(Long.parseLong(doc.get("psItemDate")+"") ,"yyyy-MM-dd" );
				stockInfo=stockInfo+item_date+"/"+stock_count+"/"+receive_count+",";
			}		
			stockInfo=stockInfo.substring(0, stockInfo.length()-1);
			voResult.setBrandName(brand_name);
			voResult.setNameCity(name_city);
			voResult.setProductId(id);
			voResult.setStockInfo(stockInfo);
		}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return voResult;
	}

	/**
	 * 
	 * @param queryDTO
	 * @return
	 */
	public static SolrQuery queryDTO2SolrQuery(ProductStockPageQueryDTO queryDTO) {
		SolrQuery solrQuery = new SolrQuery("*:*");

		if(!StringUtils.isEmpty(queryDTO.getPrOrgId())){
			String orgIdQuery = "prOrgId:*" + queryDTO.getPrOrgId() + ",*";
			solrQuery.addFilterQuery(orgIdQuery);
		}
		
		if(queryDTO.getInfoBrandId() != null){
			String brandQuery = "infoBrandId:" + queryDTO.getInfoBrandId();
			solrQuery.addFilterQuery(brandQuery);
		}
		
		if(queryDTO.getInfoNameCity() != null){
			String cityQuery = "infoNameCity:*" + queryDTO.getInfoNameCity() + "*";
			solrQuery.addFilterQuery(cityQuery);
		}
		
		if(queryDTO.getPsItemDateStart()!=null&&queryDTO.getPsItemDateEnd()!=null){
			String dateQuery="psItemDate" + ":["+queryDTO.getPsItemDateStart().getTime()+" TO " +queryDTO.getPsItemDateEnd().getTime()+"]";
			solrQuery.addFacetQuery(dateQuery);

		}
		
//		solrQuery.addFilterQuery("-psState:-1");
//		solrQuery.addFilterQuery("-infoState:-1");
		StringBuffer q_sb = new StringBuffer();
		String one = "-1";
		q_sb.append("-psState:" + "\""+one+"\"");
		String two = "-1";
		q_sb.append("-infoState:" + "\""+two+"\"");

		solrQuery.addFacetQuery(q_sb.toString());
		solrQuery.setParam(GroupParams.GROUP, true);
		solrQuery.setParam(GroupParams.GROUP_FIELD, "infoId");
		solrQuery.setParam("group.limit", "7");//每组显示的个数，默认为1
		solrQuery.set(GroupParams.GROUP_SORT,"psItemDate asc");
		solrQuery.setParam("group.ngroups", true);

		solrQuery.setStart(queryDTO.getStartRow());
		solrQuery.setRows(queryDTO.getOldPageSize());

	return solrQuery;
	}

}
