package com.yimayhd.erpcenter.biz.product.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.yihg.product.api.TrafficResProductService;
import com.yihg.product.dao.TrafficResProductMapper;
import com.yihg.product.po.TrafficResProduct;
import com.yimayhd.erpcenter.biz.product.service.TrafficResProductBiz;
import com.yimayhd.erpcenter.dal.product.service.TrafficResProductDal;

public class TrafficResProductBizImpl implements TrafficResProductBiz{
	
	@Autowired
	private TrafficResProductDal trafficResProductDal;
	
	/**
	 * 根据资源Id查询绑定的产品信息
	 */
	@Override
	public List<TrafficResProduct> loadTrafficResProductInfo(Integer resId) {
		List<TrafficResProduct> proList = trafficResProductMapper.findTrafficProductResId(resId);
		return proList;
	}

	/**
	 * 增加一个产品信息
	 */
	@Override
	public int insertTrafficResProduct(TrafficResProduct product) {
		 trafficResProductMapper.insertTrafficResProduct(product);
		 int num = product.getId();
		return num;
	}

	/**
	 * 根据id查询资源产品信息
	 */
	@Override
	public TrafficResProduct loadTrafficResProduct(Integer id) {
		TrafficResProduct productBean = trafficResProductMapper.selectResProductId(id);
		return productBean;
	}

	/**
	 * 保存修改产品信息
	 */
	@Override
	public int saveTrafficResProduct(TrafficResProduct productBean) {
		int num = trafficResProductMapper.updateToSaveResProduct(productBean);
		return num;
	}

	/**
	 * 删除绑定中的产品信息
	 */
	@Override
	public int delTrafficResProduct(Integer id) {
		int num = trafficResProductMapper.delResProduct(id);
		return num;
	}

	/**
	 * 跟id集合进行查询所有的数据
	 */
	@Override
	public int loadByResProductId(String id,String suggest_price_id,String adjust_uprodown_num,String price) {
        String[] sourceStrArray = id.split(",");
        Set<Integer> set = new HashSet<Integer>();
        for (int i = 0; i < sourceStrArray.length; i++) {
        	set.add(Integer.valueOf(sourceStrArray[i]));
        }
		
		List<TrafficResProduct> proBindingList = trafficResProductMapper.findByResProductId(set);
		//System.out.println("------"+id+"---"+suggest_price_id+"----"+adjust_uprodown_num+"----"+price);
		int nums = 0;
		if("0".equals(suggest_price_id) && "0".equals(adjust_uprodown_num)){
			List<TrafficResProduct> rpCostPriceList = new ArrayList<TrafficResProduct>();
			for(TrafficResProduct rp : proBindingList){
				//更新上调成本价
				TrafficResProduct rpCostBean = new TrafficResProduct();
				BigDecimal sug_price = new BigDecimal(price); 
				rpCostBean.setAdultCostPrice(sug_price.add(rp.getAdultCostPrice()));
				rpCostBean.setChildCostPrice(sug_price.add(rp.getChildCostPrice()));
				rpCostBean.setBabyCostPrice(sug_price.add(rp.getBabyCostPrice()));
				rpCostBean.setId(rp.getId());
				rpCostBean.setResId(rp.getResId());
				rpCostPriceList.add(rpCostBean);
			}
			
			nums = trafficResProductMapper.updateByCostPricePriceId(rpCostPriceList);
		}
		if("1".equals(suggest_price_id) && "0".equals(adjust_uprodown_num)){
			List<TrafficResProduct> rpSuggestList = new ArrayList<TrafficResProduct>();
			for(TrafficResProduct rp : proBindingList){
				//更新上调零售价
				TrafficResProduct rpBean = new TrafficResProduct();
				BigDecimal sug_price = new BigDecimal(price); 
				rpBean.setAdultSuggestPrice(sug_price.add(rp.getAdultSuggestPrice()));
				rpBean.setChildSuggestPrice(sug_price.add(rp.getChildSuggestPrice()));
				rpBean.setBabySuggestPrice(sug_price.add(rp.getBabySuggestPrice()));
				rpBean.setId(rp.getId());
				rpBean.setResId(rp.getResId());
				rpSuggestList.add(rpBean);
			}
			
			nums = trafficResProductMapper.updateBysuggestPriceId(rpSuggestList);
		}
		if("2".equals(suggest_price_id) && "0".equals(adjust_uprodown_num)){
			List<TrafficResProduct> rpSamePayList = new ArrayList<TrafficResProduct>();
			for(TrafficResProduct rp : proBindingList){
				//更新上调同行返款
				TrafficResProduct spBean = new TrafficResProduct();
				BigDecimal spay_price = new BigDecimal(price); 
				spBean.setAdultSamePay(spay_price.add(rp.getAdultSamePay()));
				spBean.setChildSamePay(spay_price.add(rp.getChildSamePay()));
				spBean.setBabySamePay(spay_price.add(rp.getBabySamePay()));
				spBean.setId(rp.getId());
				spBean.setResId(rp.getResId());
				rpSamePayList.add(spBean);
			}
			//修改同行返款
			nums = trafficResProductMapper.updateBySamePayId(rpSamePayList);
			//更新上调同行返款
			return nums;
		}
		if("3".equals(suggest_price_id) && "0".equals(adjust_uprodown_num)){
			List<TrafficResProduct> rpProxyPayList = new ArrayList<TrafficResProduct>();
			for(TrafficResProduct rp : proBindingList){
				//更新代理返款
				TrafficResProduct proxyBean = new TrafficResProduct();
				BigDecimal proxy_price = new BigDecimal(price); 
				proxyBean.setAdultProxyPay(proxy_price.add(rp.getAdultProxyPay()));
				proxyBean.setChildProxyPay(proxy_price.add(rp.getChildProxyPay()));
				proxyBean.setBadyProxyPay(proxy_price.add(rp.getBadyProxyPay()));
				proxyBean.setId(rp.getId());
				proxyBean.setResId(rp.getResId());
				rpProxyPayList.add(proxyBean);
			}
			//修改代理返款
			nums = trafficResProductMapper.updateByProxyPayId(rpProxyPayList);
			//更新上调代理返款
			return nums;
		}
		if("0".equals(suggest_price_id) && "1".equals(adjust_uprodown_num)){
			List<TrafficResProduct> down_CostPriceList = new ArrayList<TrafficResProduct>();
			for(TrafficResProduct rp : proBindingList){
				//更新下调成本价
				TrafficResProduct dsBean = new TrafficResProduct();
				BigDecimal suggest_price = new BigDecimal(price); 
				dsBean.setAdultCostPrice(rp.getAdultCostPrice().subtract(suggest_price));
				dsBean.setChildCostPrice(rp.getChildCostPrice().subtract(suggest_price));
				dsBean.setBabyCostPrice(rp.getBabyCostPrice().subtract(suggest_price));
				dsBean.setId(rp.getId());
				dsBean.setResId(rp.getResId());
				down_CostPriceList.add(dsBean);
			}
			//修改下调成本价
			nums = trafficResProductMapper.updateByCostPricePriceId(down_CostPriceList);
			//更新下调成本价
			return nums;
		}
		if("1".equals(suggest_price_id) && "1".equals(adjust_uprodown_num)){
			List<TrafficResProduct> down_suggestList = new ArrayList<TrafficResProduct>();
			for(TrafficResProduct rp : proBindingList){
				//更新下调零售价
				TrafficResProduct dsBean = new TrafficResProduct();
				BigDecimal suggest_price = new BigDecimal(price); 
				dsBean.setAdultSuggestPrice(rp.getAdultSuggestPrice().subtract(suggest_price));
				dsBean.setChildSuggestPrice(rp.getChildSuggestPrice().subtract(suggest_price));
				dsBean.setBabySuggestPrice(rp.getBabySuggestPrice().subtract(suggest_price));
				dsBean.setId(rp.getId());
				dsBean.setResId(rp.getResId());
				down_suggestList.add(dsBean);
			}
			//修改下调零售价
			nums = trafficResProductMapper.updateBysuggestPriceId(down_suggestList);
			//更新下调零售价
			return nums;
		}
		if("2".equals(suggest_price_id) && "1".equals(adjust_uprodown_num)){
			List<TrafficResProduct> down_SamePayList = new ArrayList<TrafficResProduct>();
			for(TrafficResProduct rp : proBindingList){
				//更新下调同行返款
				TrafficResProduct dsBean = new TrafficResProduct();
				BigDecimal dspay_price = new BigDecimal(price); 
				dsBean.setAdultSamePay(rp.getAdultSamePay().subtract(dspay_price));
				dsBean.setChildSamePay(rp.getChildSamePay().subtract(dspay_price));
				dsBean.setBabySamePay(rp.getBabySamePay().subtract(dspay_price));
				dsBean.setId(rp.getId());
				dsBean.setResId(rp.getResId());
				down_SamePayList.add(dsBean);
			}
			//修改同行返款
			nums = trafficResProductMapper.updateBySamePayId(down_SamePayList);
			//更新下调同行返款
			return nums;
		}
		if("3".equals(suggest_price_id) && "1".equals(adjust_uprodown_num)){
			List<TrafficResProduct> down_ProxyPayList = new ArrayList<TrafficResProduct>();
			for(TrafficResProduct rp : proBindingList){
				//更新下调代理返款
				TrafficResProduct dppBean = new TrafficResProduct();
				BigDecimal dpppay_price = new BigDecimal(price); 
				dppBean.setAdultProxyPay(rp.getAdultProxyPay().subtract(dpppay_price));
				dppBean.setChildProxyPay(rp.getChildProxyPay().subtract(dpppay_price));
				dppBean.setBadyProxyPay(rp.getBadyProxyPay().subtract(dpppay_price));
				dppBean.setId(rp.getId());
				dppBean.setResId(rp.getResId());
				down_ProxyPayList.add(dppBean);
			}
			//修改代理返款
			nums = trafficResProductMapper.updateByProxyPayId(down_ProxyPayList);
			//更新下调代理返款
			return nums;
		}
		return nums;
	}

	@Override
	public TrafficResProduct selectByResProductId(Integer id) {
		return trafficResProductMapper.findByResProId(id);
	}

	@Override
	public Integer selectResProductNameCount(Integer productCode,Integer  resId) {
		return trafficResProductMapper.selectResProductNameCount(productCode,resId);
	}

}
