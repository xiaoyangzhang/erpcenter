package org.yimayhd.erpcenter.facade.finance.result;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.common.BaseResult;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: ${ClassName}
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @Auther xueshengdong
 * @Date 2016/12/13 18:50
 */
public class ExportTravelListTableResult extends BaseResult {

    private static final long serialVersionUID = -2157680669110047307L;
    private PageBean pageBean;
    private Map<Integer, String> guideMap;
    private Map sum;

    private Map<String,Object> model;

    private List<Map<String, Object>> orderList;
    private List<Map<String, Object>> deliveryList;
    private List<Map<String, Object>> supplierList;
    private List<Map<String, Object>> otherIncomeList;

    public PageBean getPageBean() {
        return pageBean;
    }

    public void setPageBean(PageBean pageBean) {
        this.pageBean = pageBean;
    }

    public Map<Integer, String> getGuideMap() {
        return guideMap;
    }

    public void setGuideMap(Map<Integer, String> guideMap) {
        this.guideMap = guideMap;
    }

    public Map getSum() {
        return sum;
    }

    public void setSum(Map sum) {
        this.sum = sum;
    }

    public List<Map<String, Object>> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Map<String, Object>> orderList) {
        this.orderList = orderList;
    }

    public List<Map<String, Object>> getDeliveryList() {
        return deliveryList;
    }

    public void setDeliveryList(List<Map<String, Object>> deliveryList) {
        this.deliveryList = deliveryList;
    }

    public List<Map<String, Object>> getSupplierList() {
        return supplierList;
    }

    public void setSupplierList(List<Map<String, Object>> supplierList) {
        this.supplierList = supplierList;
    }

    public List<Map<String, Object>> getOtherIncomeList() {
        return otherIncomeList;
    }

    public void setOtherIncomeList(List<Map<String, Object>> otherIncomeList) {
        this.otherIncomeList = otherIncomeList;
    }

    public Map<String, Object> getModel() {
        return model;
    }

    public void setModel(Map<String, Object> model) {
        this.model = model;
    }
}
