package com.yimayhd.erpcenter.biz.sales.service.impl.operation;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

import com.yimayhd.erpcenter.biz.sales.client.service.operation.BookingSupplierDetailBiz;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingSupplierDetail;
import com.yimayhd.erpcenter.dal.sales.client.operation.service.BookingSupplierDetailDal;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.yihg.mybatis.utility.PageBean;


public class BookingSupplierDetailBizImpl implements
        BookingSupplierDetailBiz {

    @Autowired
    private BookingSupplierDetailDal bookingSupplierDetailDal;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return bookingSupplierDetailDal.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(BookingSupplierDetail record) {
        return bookingSupplierDetailDal.insert(record);
    }

    @Override
    public int insertSelective(BookingSupplierDetail record) {
        return bookingSupplierDetailDal.insertSelective(record);
    }

    @Override
    public BookingSupplierDetail selectByPrimaryKey(Integer id) {
        return bookingSupplierDetailDal.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(BookingSupplierDetail record) {
        return bookingSupplierDetailDal.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(BookingSupplierDetail record) {
        return bookingSupplierDetailDal.updateByPrimaryKey(record);
    }

    @Override
    public List<BookingSupplierDetail> selectByPrimaryBookId(Integer bookId) {
        return bookingSupplierDetailDal.selectByPrimaryBookId(bookId);
    }

    @Override
    public int deleteByBookingId(Integer bookingId) {
        return bookingSupplierDetailDal.deleteByBookingId(bookingId);
    }

    @Override
    public List<BookingSupplierDetail> getDriversByGroupIdAndType(
            Integer groupId, Integer supplierType) {
        return bookingSupplierDetailDal.getDriversByGroupIdAndType(groupId, supplierType);
    }

    @Override
    public BookingSupplierDetail selectDriverInfoByGroupIdAndDriverId(Integer groupId, Integer driverId) {
        return bookingSupplierDetailDal.selectDriverInfoByGroupIdAndDriverId(groupId, driverId);
    }


    /**
     * 拼接明细字符串
     *
     * @param bookingSupplier
     * @param list
     * @return
     */
    @Override
    public String concatDetail(Integer supplierType, String remark, List<BookingSupplierDetail> list) {
        return bookingSupplierDetailDal.concatDetail(supplierType, remark, list);
    }

    /**
     * 拼接明细表格
     *
     * @param bookingSupplier
     * @param list
     * @return
     */
    @Override
    public String concatDetailTable(Integer supplierType, String remark, List<BookingSupplierDetail> list) {
        return bookingSupplierDetailDal.concatDetailTable(supplierType, remark, list);
    }

    @Override
    public List<Map<String, Integer>> getBookingIdsByType1Id(Map parameters) {
        return bookingSupplierDetailDal.getBookingIdsByType1Id(parameters);
    }

    @Override
    public List<BookingSupplierDetail> selectBookingSupplierDetailByGroupId(
            Integer groupId) {
        return bookingSupplierDetailDal.selectBookingSupplierDetailByGroupId(groupId);
    }

}
