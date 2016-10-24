package com.yimayhd.erpcenter.biz.sales.service.impl.operation;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.yihg.operation.api.BookingShopDetailDeployService;
import com.yihg.operation.dao.BookingShopDetailDeployMapper;
import com.yihg.operation.dao.BookingShopDetailMapper;
import com.yihg.operation.dao.BookingShopMapper;
import com.yihg.operation.po.BookingShop;
import com.yihg.operation.po.BookingShopDetail;
import com.yihg.operation.po.BookingShopDetailDeploy;
import com.yihg.operation.vo.BookingShopDetailDeployVO;

public class BookingShopDetailDeployServiceImpl implements BookingShopDetailDeployService{
	static Logger logger = LoggerFactory.getLogger(BookingShopDetailDeployServiceImpl.class);
	@Autowired
	private BookingShopDetailDeployMapper shopDetailDeployMapper;
	@Autowired
	private BookingShopMapper bookingShopMapper;
	@Autowired
	private BookingShopDetailMapper detailMapper;
	@Override
	public List<BookingShopDetailDeploy> selectByDetailId(Integer dId) {
		
		return shopDetailDeployMapper.selectByDetailId(dId);
	}
	
public List<BookingShopDetailDeploy> selectByBookingId(Integer dId) {
		
		return shopDetailDeployMapper.selectByBookingId(dId);
	}

	@Override
	public int insertSelective(BookingShopDetailDeployVO record) {
		int i = 0;
		
		List<BookingShopDetailDeploy> detailDeploy= record.getDetail();
		
		if(detailDeploy.get(0).getBookingId()!=null){
			BigDecimal totalMoney=new BigDecimal(0);
			shopDetailDeployMapper.deleteByPrimaryKey(detailDeploy.get(0).getBookingId());	
			for (BookingShopDetailDeploy bookingShopDetailDeploy : detailDeploy) {
				i = shopDetailDeployMapper.insertSelective(bookingShopDetailDeploy);
				if(bookingShopDetailDeploy.getBuyTotal()!=null){
					totalMoney=totalMoney.add(bookingShopDetailDeploy.getBuyTotal());
				}
			}
			BookingShop shop = new BookingShop();
			shop.setId(detailDeploy.get(0).getBookingId());
			shop.setTotalMoney(totalMoney);
			bookingShopMapper.updateByPrimaryKeySelective(shop);
			
		}
		
		return i;
	}

	@Override
	public int deleteByPrimaryKey(Integer bdId) {
		return shopDetailDeployMapper.deleteByPrimaryKey(bdId);
	}

	@Override
	public int saveSelective(BookingShopDetailDeploy record) {
		return shopDetailDeployMapper.insertSelective(record);
	}

	@Override
	public int updateByPrimaryKeySelective(BookingShopDetailDeploy record) {
		return shopDetailDeployMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public void updateBookingShopDetailDeploy(BookingShopDetailDeployVO deploy) {
		List<BookingShopDetailDeploy> detailDeploys = deploy.getDetail();
		if (detailDeploys!=null && detailDeploys.size()>0) {
			Map<Integer,BigDecimal> map = new HashMap<Integer,BigDecimal>();
			for (BookingShopDetailDeploy dep : detailDeploys) {
				//logger.debug("---------购物明细："+dep.getBookingId()+"--------------------------"+dep.getBuyTotal());
				//获取总数
				BigDecimal total = dep.getBuyTotal();				
				if(total != null){
					if(map.containsKey(dep.getBookingId())){
						total = total.add(map.get(dep.getBookingId()));					
					}
					map.put(dep.getBookingId(), total);					
				}else{
					//购物金额为null时，认为还没有购物
					continue;
				}
				
				//为了解决数据重复保存的问题，改为先删除后添加
				shopDetailDeployMapper.deleteByBookingIdAndOrderId(dep.getBookingId(), dep.getOrderId());
				shopDetailDeployMapper.insertSelective(dep);
			}
			if(map.size()>0){
				for (Map.Entry<Integer, BigDecimal> entry : map.entrySet()) {
					//logger.debug(entry.getKey()+"--------------------------"+entry.getValue());
					BookingShop shop = new BookingShop();
					shop.setId(entry.getKey());
					shop.setTotalMoney(entry.getValue());
					bookingShopMapper.updateByPrimaryKeySelective(shop);
				} 
			}
						
		}
	}

	@Override
	public int selectBuyTotalByProduct(Map map) {
		return shopDetailDeployMapper.selectBuyTotalByProduct(map)==null?0:shopDetailDeployMapper.selectBuyTotalByProduct(map);
	}

	/**
	 * 删除该购物店下客人购物录入数据信息
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public int deleteByShopId(Integer shopId) {
		if(shopId==null){
			return -1;
		}
		shopDetailDeployMapper.deleteByBookingId(shopId);
		BookingShop bs = new BookingShop();
		bs.setId(shopId);
		bs.setTotalMoney(new BigDecimal(0));
		bookingShopMapper.updateByPrimaryKeySelective(bs);
		return 1;
	}

	@Override
	public int getCountByShopId(Integer shopId) {
		return shopDetailDeployMapper.getCountByBookingId(shopId);
	}

	@Override
	public BigDecimal getSumBuyTotalByBookingId(Integer id) {
		return shopDetailDeployMapper.getSumBuyTotalByBookingId(id);
	}
}
