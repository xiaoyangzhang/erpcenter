package com.yimayhd.erpcenter.biz.sales.service.impl.operation;

import com.yimayhd.erpcenter.biz.sales.client.service.operation.BookingShopDetailDeployBiz;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingShopDetailDeploy;
import com.yimayhd.erpcenter.dal.sales.client.operation.service.BookingShopDetailDal;
import com.yimayhd.erpcenter.dal.sales.client.operation.service.BookingShopDetailDeployDal;
import com.yimayhd.erpcenter.dal.sales.client.operation.vo.BookingShopDetailDeployVO;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: ${ClassName}
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @Auther xueshengdong
 * @Date 2016/10/25 17:01
 */
public class BookingShopDetailDeployBizImpl implements BookingShopDetailDeployBiz {
    @Autowired
    private BookingShopDetailDeployDal bookingShopDetailDeployDal;

    @Override
    public int deleteByPrimaryKey(Integer bdId) {
        return bookingShopDetailDeployDal.deleteByPrimaryKey(bdId);
    }

    @Override
    public List<BookingShopDetailDeploy> selectByDetailId(Integer dId) {
        return bookingShopDetailDeployDal.selectByDetailId(dId);
    }

    @Override
    public int insertSelective(BookingShopDetailDeployVO record) {
        return bookingShopDetailDeployDal.insertSelective(record);
    }

    @Override
    public int saveSelective(BookingShopDetailDeploy record) {
        return bookingShopDetailDeployDal.saveSelective(record);
    }

    @Override
    public int updateByPrimaryKeySelective(BookingShopDetailDeploy record) {
        return bookingShopDetailDeployDal.updateByPrimaryKeySelective(record);
    }

    @Override
    public List<BookingShopDetailDeploy> selectByBookingId(Integer dId) {
        return bookingShopDetailDeployDal.selectByBookingId(dId);
    }

    @Override
    public void updateBookingShopDetailDeploy(BookingShopDetailDeployVO deploy) {
        bookingShopDetailDeployDal.updateBookingShopDetailDeploy(deploy);
    }

    @Override
    public int selectBuyTotalByProduct(Map map) {
        return bookingShopDetailDeployDal.selectBuyTotalByProduct(map);
    }

    /**
     * 删除掉该购物店客人购物数据信息
     *
     * @param shopId
     * @return
     */
    @Override
    public int deleteByShopId(Integer shopId) {
        return bookingShopDetailDeployDal.deleteByShopId(shopId);
    }

    /**
     * 判断购物店的客人购物录入数据记录数
     *
     * @param shopId
     * @return
     */
    @Override
    public int getCountByShopId(Integer shopId) {
        return bookingShopDetailDeployDal.getCountByShopId(shopId);
    }

    @Override
    public BigDecimal getSumBuyTotalByBookingId(Integer id) {
        return bookingShopDetailDeployDal.getSumBuyTotalByBookingId(id);
    }
}
