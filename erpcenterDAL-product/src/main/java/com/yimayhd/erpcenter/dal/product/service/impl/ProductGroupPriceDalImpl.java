package com.yimayhd.erpcenter.dal.product.service.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yimayhd.erpcenter.common.exception.ClientException;
import com.yimayhd.erpcenter.dal.product.mapper.ProductGroupPriceMapper;
import com.yimayhd.erpcenter.dal.product.mapper.ProductGroupPriceStockallocateMapper;
import com.yimayhd.erpcenter.dal.product.po.ProductGroupPrice;
import com.yimayhd.erpcenter.dal.product.po.ProductGroupPriceStockallocate;
import com.yimayhd.erpcenter.dal.product.service.ProductGroupPriceDal;
import com.yimayhd.erpcenter.dal.product.vo.PriceCopyVo;
import com.yimayhd.erpcenter.dal.product.vo.ProductPriceVo;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.util.StringUtils;

public class ProductGroupPriceDalImpl implements ProductGroupPriceDal {
	static final Logger logger = LoggerFactory.getLogger(ProductGroupPriceDalImpl.class);
	
	@Autowired
	private ProductGroupPriceMapper groupPriceMapper;
	@Autowired
	private ProductGroupPriceStockallocateMapper stockallocateMapper;
	
	@Override
	@Transactional
	public int save(ProductPriceVo priceVo) {
		int i = 0;
		Long time =System.currentTimeMillis();
		if(priceVo.getGroupPrice().getId()==null){
			String[] groupDate = priceVo.getGroupPrice().getGroupDates().split(",");
            List<Date> dateList = new ArrayList<Date>();
            Map<Date, Integer> idMap = new HashMap<Date, Integer>();
            if(groupDate.length > 0){
                List<ProductGroupPrice> productGroupPrices = groupPriceMapper.selectByGroupId(priceVo.getGroupPrice().getGroupId(), groupDate[0].split("-")[0], groupDate[0].split("-")[1]);
                for(ProductGroupPrice productGroupPrice : productGroupPrices){
                    dateList.add(productGroupPrice.getGroupDate());
                    idMap.put(productGroupPrice.getGroupDate(), productGroupPrice.getId());
                }
            }

			for (String str : groupDate) {
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");//小写的mm表示的是分钟  
				ProductGroupPrice groupPrice = priceVo.getGroupPrice();
				try {
                    Date date = sdf.parse(str.trim());
                    if(dateList.contains(date)){//包含已存团期
                        groupPrice.setId(idMap.get(date));
                        groupPrice.setState((byte) 1);
                        groupPrice.setCreateTime(time);
                        i = groupPriceMapper.updateByPrimaryKeySelective(groupPrice);

                    }else{//新增
                        groupPrice.setGroupDate(date);
                        groupPrice.setState((byte)1);
                        groupPrice.setCreateTime(time);

                        i = groupPriceMapper.insertSelective(groupPrice);
                    }

				} catch (ParseException e) {
					e.printStackTrace();
				}



                //先删除分配名额
                stockallocateMapper.deleteByPrimaryKey(groupPrice.getId());
				List<ProductGroupPriceStockallocate> priceStockallocate = priceVo.getPriceStockallocate();
				if(priceStockallocate!=null&&!priceStockallocate.isEmpty()){
					for (ProductGroupPriceStockallocate stockallocate : priceStockallocate) {
						stockallocate.setCreateTime(time);
						stockallocate.setPriceId(groupPrice.getId());
						stockallocateMapper.insertSelective(stockallocate);
					}
				}
			}
		}else{
			//修改price
			ProductGroupPrice groupPrice = priceVo.getGroupPrice();
			i = groupPriceMapper.updateByPrimaryKeySelective(groupPrice);
			//先删除分配名额
			stockallocateMapper.deleteByPrimaryKey(groupPrice.getId());
			List<ProductGroupPriceStockallocate> priceStockallocate = priceVo.getPriceStockallocate();
			if(priceStockallocate!=null&&!priceStockallocate.isEmpty()){
				for (ProductGroupPriceStockallocate stockallocate : priceStockallocate) {
					stockallocate.setCreateTime(time);
					stockallocate.setPriceId(groupPrice.getId());
					stockallocateMapper.insertSelective(stockallocate);
				}
			}
		}
		
		
		
		return i;
	}

    @Override
    public boolean delete(Integer priceId) {
        return groupPriceMapper.deleteByPrimaryKey(priceId) == 1;
    }

    @Override
	public List<ProductGroupPrice> selectProductGroupPrices(Integer groupId, String year, String month) {
    	if(year!=null){
    		if(month.length() == 1){
    			month = "0" + month;
    		}
    		return groupPriceMapper.selectByGroupId(groupId, year, month);
    	}
		
		return groupPriceMapper.selectByGroupId(groupId, null, null);
	}

	

	@Override
	public ProductPriceVo selectByPrimaryKey(Integer id) {
		ProductPriceVo vo = new ProductPriceVo();
		vo.setGroupPrice(groupPriceMapper.selectByPrimaryKey(id));
		vo.setPriceStockallocate(stockallocateMapper.selectByPrimaryKey(id));
		return vo;
	}

	/**
	 * edited by ge 20151027
	 * 如果是统一定价则supplierId传入null即可
	 * 如果是普通模式分别定价，则传入地接社SupplierId
	 */
    @Transactional
    @Override
    public boolean updateStock(Integer priceId, Integer supplierId, Integer increaseCount) {
    	if(supplierId==null){
    		return groupPriceMapper.increaseStockCount(priceId, increaseCount) >0;
    	}
        return groupPriceMapper.increaseStockCount(priceId, increaseCount) >0 && stockallocateMapper.updateStockByPriceIdAndSupplierId(priceId, supplierId, increaseCount) >=0;
    }
    
    @Transactional
	public int copyGroupPrice(PriceCopyVo copyVo){
		List<ProductGroupPrice> priceList = groupPriceMapper.selectByGroupIdAndDateSpan(copyVo.getGroupId(), copyVo.getStartTime(), copyVo.getEndTime());
		if(priceList==null || priceList.size()==0){
			return 0;
		}
		int year = copyVo.getDestYear().intValue();
		int month = copyVo.getDestMonth().intValue();
		for(ProductGroupPrice price : priceList){
			Date groupDate = price.getGroupDate();
			groupDate = convertDate(groupDate,year,month);
			if(groupDate!=null){							
				List<ProductGroupPrice> list = groupPriceMapper.selectByGroupIdAndGroupDate(price.getGroupId(), groupDate);				
				ProductGroupPrice price2 = new ProductGroupPrice();
				BeanUtils.copyProperties(price, price2);
				price2.setGroupDate(groupDate);	
				//修改已收客数为0
				price2.setReceiveCount(0);
				if(list!=null && list.size()>0){				
					price2.setId(list.get(0).getId());
					int result = groupPriceMapper.updateByPrimaryKeySelective(price2);
					if(result!=1){
						//throw new RuntimeException("价格更新失败");
						logger.error("更新失败！"+groupDate);
					}
				}else{
					price2.setId(null);
					int result = groupPriceMapper.insertSelective(price2);
					if(result!=1){
						//throw new RuntimeException("价格新增失败");
						logger.error("新增失败！"+groupDate);
					}
				}
			}else{
				logger.info("日期不存在");
			}
		}
		return 1;
	}
    
    private Date convertDate(Date date,Integer year,Integer month){
    	Calendar c = Calendar.getInstance();
    	c.setTime(date);
    	int day = c.get(Calendar.DATE);
    	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    	 try {
			 format.setLenient(false);
			 logger.debug(year+"-"+(month<10? "0"+month:month)+"-"+day);
			 return format.parse(year+"-"+(month<10? ("0"+month):month)+"-"+(day<10?("0"+day):day));
		} catch (ParseException e) {
			//e.printStackTrace();
			return null;
		}
    	
    }

	@Override
	public Integer selectProductByProduct(Map parameters) {
		return groupPriceMapper.selectProductGroupByProduct(parameters);
	}

	@Override
	public int save(ProductGroupPrice price) {
		groupPriceMapper.insertSelective(price);
		return price.getId();
	}

	@Override
	public List<Map> getMinPriceByProductIdSetAndDate(
			List<Integer> productIds, Date date) {
		return groupPriceMapper.selectMinPriceByProductIdAndDate(productIds, date);
	}

	@Override
	public List<ProductGroupPrice> selectPriceByGroupId(Integer groupId) {
		return groupPriceMapper.selectPricesByGroupId2(groupId);
	}

	@Override
	@Transactional
	public void batchInsertPriceGroup(Integer bizId, String json) {
		if(StringUtils.isEmpty(json)){
			throw new ClientException("请输入正确的数据");
		}
		
		JSONObject obj = JSON.parseObject(json);
		Integer groupId = Integer.parseInt(obj.getString("groupId"));
		String notIds = obj.getString("notIds");
		JSONArray list = obj.getJSONArray("list");
		
		groupPriceMapper.deleteByGroupIdNotInIds(bizId, groupId ,notIds);
		
		JSONObject item = null;
		ProductGroupPrice productGroupPrice = null;
		for(int i = 0; i < list.size(); i++){
			item = list.getJSONObject(i);
			if(item == null){
				continue;
			}
			productGroupPrice = new ProductGroupPrice();
			productGroupPrice.setGroupId(groupId);
			productGroupPrice.setGroupDate(item.getDate("groupDate"));
			productGroupPrice.setGroupDateTo(item.getDate("groupDateTo"));
			productGroupPrice.setPriceSuggestAdult(item.getFloat("price_suggest_adult"));
			productGroupPrice.setPriceSuggestChild(item.getFloat("price_suggest_child"));
			productGroupPrice.setPriceSettlementAdult(item.getFloat("price_settlement_adult"));
			productGroupPrice.setPriceSettlementChild(item.getFloat("price_settlement_child"));
			productGroupPrice.setPriceCostAdult(item.getFloat("price_cost_adult"));
			productGroupPrice.setPriceCostChild(item.getFloat("price_cost_child"));
			productGroupPrice.setState(Byte.valueOf("1"));
			productGroupPrice.setCreateTime(System.currentTimeMillis());
			if(StringUtils.isEmpty(item.getString("id"))){
				groupPriceMapper.insertSelective(productGroupPrice);
			}else if(!StringUtils.isEmpty(item.getString("id"))){
				productGroupPrice.setId(Integer.parseInt(item.getString("id")));
				groupPriceMapper.updateByPrimaryKeySelective(productGroupPrice);
			}
		}
	}
	
	@Override
	public List<ProductGroupPrice> getPriceByPidAndUserIdAndDate(Integer bizId,
			Integer productId, Integer operatorId, Date date) {
		return groupPriceMapper.getPriceByPidAndUserIdAndDate(bizId,productId,operatorId,date);
	}

//	@Override
//	public int save2(ProductGroupPrice price) {
//		return groupPriceMapper.insertSelective(price);
//	}
    

}
