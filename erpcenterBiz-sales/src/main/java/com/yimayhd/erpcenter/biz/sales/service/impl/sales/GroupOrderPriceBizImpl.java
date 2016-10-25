package com.yimayhd.erpcenter.biz.sales.service.impl.sales;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.yimayhd.erpcenter.biz.sales.client.service.sales.GroupOrderPriceBiz;
import com.yimayhd.erpcenter.common.util.NumberUtil;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrderPrice;
import com.yimayhd.erpcenter.dal.sales.client.sales.service.GroupOrderPriceDal;
import com.yimayhd.erpcenter.dal.sales.client.sales.vo.CostIncome;

public class GroupOrderPriceBizImpl implements GroupOrderPriceBiz {

	@Autowired
	private GroupOrderPriceDal groupOrderPriceDal;

	
	//@Transactional
	@Override
	public int deleteByPrimaryKey(Integer id) {
		return groupOrderPriceDal.deleteByPrimaryKey(id);
//		GroupOrderPrice record = groupOrderPriceDal.selectByPrimaryKey(id);
//		groupOrderPriceDal.deleteByPrimaryKey(id);
//		if (record.getMode() == 0) {
//			GroupOrder groupOrder = groupOrderMapper.selectByPrimaryKey(record
//					.getOrderId());
//			groupOrderMapper.updateTotalByPrice(groupOrder.getId());
//			if (groupOrder.getGroupId() != null) {
//				tourGroupMapper.updateTotalIncome(groupOrder.getGroupId());
//			}
//		}
//
//		return 1;
	}

	@Override
	public int insert(GroupOrderPrice record) {
		return groupOrderPriceDal.insert(record);
	}

	//@Transactional
	@Override
	public int insertSelective(GroupOrderPrice record) {
		return groupOrderPriceDal.insertSelective(record);
//		groupOrderPriceDal.insertSelective(record);
//		if (record.getMode() == 0) {
//			GroupOrder groupOrder = groupOrderMapper.selectByPrimaryKey(record
//					.getOrderId());
//			groupOrderMapper.updateTotalByPrice(groupOrder.getId());
//			if (groupOrder.getGroupId() != null) {
//				tourGroupMapper.updateTotalIncome(groupOrder.getGroupId());
//			}
//		}
//		return 1;
	}

	@Override
	public GroupOrderPrice selectByPrimaryKey(Integer id) {
		return groupOrderPriceDal.selectByPrimaryKey(id);
	}

	//@Transactional
	@Override
	public int updateByPrimaryKeySelective(GroupOrderPrice record) {
		return groupOrderPriceDal.updateByPrimaryKeySelective(record);
//		groupOrderPriceDal.updateByPrimaryKeySelective(record);
//		//更新收入
//		if (record.getMode() == 0) {
//			GroupOrder groupOrder = groupOrderMapper.selectByPrimaryKey(record
//					.getOrderId());
//			groupOrderMapper.updateTotalByPrice(groupOrder.getId());
//			if (groupOrder.getGroupId() != null) {
//				tourGroupMapper.updateTotalIncome(groupOrder.getGroupId());
//			}
//		}
//		return 1;
	}

	@Override
	public int updateByPrimaryKey(GroupOrderPrice record) {
		return groupOrderPriceDal.updateByPrimaryKey(record);
	}

	@Override
	public List<GroupOrderPrice> selectByOrderAndType(Integer orderId,
			Integer mode) {
		return groupOrderPriceDal.selectByOrderAndType(orderId, mode);
	}

	@Override
	public Boolean selectByOrderAndType(Integer orderId) {
		Boolean flag = false ;
		List<GroupOrderPrice> gopList = groupOrderPriceDal.selectByOrderAndType(orderId, 1) ;
		Double num = (double) 0 ;
		for (GroupOrderPrice groupOrderPrice : gopList) {
		num+=groupOrderPrice.getTotalPrice() ;
		}
		if(num!=null||num!=0){
			flag = true ;
		}
		return flag ;
	}
	@Override
	public List<GroupOrderPrice> selectByOrder(Integer orderId) {
		List<GroupOrderPrice> prices = groupOrderPriceDal.selectByOrder(orderId);
		return prices ;
	}

	@Override
	public int saveCostIncomeBatch(CostIncome costIncome,Integer creatorId,String creatorName) {
		List<GroupOrderPrice> gops = costIncome.getPriceList() ;
		for (GroupOrderPrice gop : gops) {
			//如果id不为空做更新操作，否则做插入操作
			if(gop.getId()!=null){
				gop.setOrderId(costIncome.getOrderId());
				updateByPrimaryKeySelective(gop) ;
			}else{
				gop.setOrderId(costIncome.getOrderId());
				gop.setCreatorId(creatorId);
				gop.setCreatorName(creatorName);
				gop.setCreateTime(new Date().getTime());
				gop.setRowState(1);
				insertSelective(gop) ;
			}
		}
		return 1;
	}

	@Override
	public String concatDetail(List<GroupOrderPrice> priceList) {
		if(priceList == null || priceList.size() == 0){
			return "";
		}
		
		StringBuilder sb = new StringBuilder();
		GroupOrderPrice price = null;
		for(int i =0; i < priceList.size(); i++){
			
			if(i > 0){
				sb.append("<br/>");
			}
			
			price = priceList.get(i);
			sb.append(price.getItemName() != null ? price.getItemName() : "");
			sb.append("：");
			sb.append(NumberUtil.formatDouble(price.getUnitPrice()));
			sb.append("*");
			sb.append(NumberUtil.formatDouble(price.getNumPerson()));
			sb.append("*");
			sb.append(NumberUtil.formatDouble(price.getNumTimes()));
			sb.append("=");
			sb.append(NumberUtil.formatDouble(price.getTotalPrice()));
		}
		return sb.toString();
	}
	
	@Override
	public String concatDetailTable(List<GroupOrderPrice> priceList, boolean isShow,String edit) {
		if(priceList == null || priceList.size() == 0){
			return "";
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append("<table class='in_table'>");
			if(!isShow){
				sb.append("<col width='14.2%' />");
				sb.append("<col width='14.2%' />");
				sb.append("<col width='14.2%' />");
				sb.append("<col width='14.2%' />");
				sb.append("<col width='14.2%' />");
				sb.append("<col width='14.2%' />");
				sb.append("<col width='14.2%' />");
			}else{
				sb.append("<col width='16.6%' />");
				sb.append("<col width='16.6%' />");
				sb.append("<col width='16.6%' />");
				sb.append("<col width='16.6%' />");
				sb.append("<col width='16.6%' />");
				sb.append("<col width='16.6%' />");
			}
			sb.append("<thead></thead>");
			sb.append("<tbody>");
			
			GroupOrderPrice price = null;
			for(int i =0; i < priceList.size(); i++){
				price = priceList.get(i);
				
				sb.append("<tr>");
					sb.append("<td>"+ (price.getItemName() != null ? price.getItemName() : "") +"</td>");
					sb.append("<td>"+ price.getRemark() +"</td>");
					sb.append("<td>"+ NumberUtil.formatDouble(price.getUnitPrice()) +"</td>");
					sb.append("<td>"+ NumberUtil.formatDouble(price.getNumPerson()) +"</td>");
					sb.append("<td>"+ NumberUtil.formatDouble(price.getNumTimes()) +"</td>");
					sb.append("<td>"+ NumberUtil.formatDouble(price.getTotalPrice()) +"</td>");
					if(!isShow){
						if(!StringUtils.isEmpty(edit) && (edit.indexOf("true")!=-1)){
							sb.append("<td>");
							sb.append("<input name='audit_price_id' type='checkbox' value="+ price.getId() +" ");
								if(price.getStateFinance() == 1){
									sb.append(" checked ");
								}
								sb.append(" />");
							sb.append("</td>	");
						}else {
							sb.append("<td>");
							sb.append("</td>	");
						}
					}
				sb.append("</tr>");
			}
			sb.append("</tbody>");
		sb.append("</table>");
		return sb.toString();
	}
	
	@Override
	public void auditPriceByIds(String priceCheckedIds, String priceUnCheckedIds){
		groupOrderPriceDal.auditPriceByIds(priceCheckedIds, priceUnCheckedIds);
//		//审核
//		if(!StringUtils.isEmpty(priceCheckedIds)){
//			groupOrderPriceDal.auditPriceByIds(priceCheckedIds, 1);
//		}
//		
//		//取消审核
//		if(!StringUtils.isEmpty(priceUnCheckedIds)){
//			groupOrderPriceDal.auditPriceByIds(priceUnCheckedIds, 0);
//		}
	}

}
