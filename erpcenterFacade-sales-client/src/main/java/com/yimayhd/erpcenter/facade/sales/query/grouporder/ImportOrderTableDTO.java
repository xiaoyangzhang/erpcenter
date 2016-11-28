package com.yimayhd.erpcenter.facade.sales.query.grouporder;

import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrder;
import com.yimayhd.erpcenter.dal.sys.po.PlatformOrgPo;

import java.io.Serializable;
import java.util.Map;

/**
 * @ClassName: ${ClassName}
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @Auther xueshengdong
 * @Date 2016/11/28 15:26
 */
public class ImportOrderTableDTO implements Serializable {
    private static final long serialVersionUID = -1414059875463204495L;

    private GroupOrder groupOrder;
    private String startTime;
    private String endTime;
    private Map<String, Object> pm;
    private PlatformOrgPo pop;
    private Integer bizId;
    private Integer employeeId;
    private String name;

    private String[] orderIds;
    private Integer saleOperatorId;
    private String saleOperatorName;
    private Integer operatorId;
    private String operatorName;
    private Integer supplierId;
    private String supplierName;
    private Integer orderType;


    public GroupOrder getGroupOrder() {
        return groupOrder;
    }

    public void setGroupOrder(GroupOrder groupOrder) {
        this.groupOrder = groupOrder;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Map<String, Object> getPm() {
        return pm;
    }

    public void setPm(Map<String, Object> pm) {
        this.pm = pm;
    }

    public PlatformOrgPo getPop() {
        return pop;
    }

    public void setPop(PlatformOrgPo pop) {
        this.pop = pop;
    }

    public Integer getBizId() {
        return bizId;
    }

    public void setBizId(Integer bizId) {
        this.bizId = bizId;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getOrderIds() {
        return orderIds;
    }

    public void setOrderIds(String[] orderIds) {
        this.orderIds = orderIds;
    }

    public Integer getSaleOperatorId() {
        return saleOperatorId;
    }

    public void setSaleOperatorId(Integer saleOperatorId) {
        this.saleOperatorId = saleOperatorId;
    }

    public String getSaleOperatorName() {
        return saleOperatorName;
    }

    public void setSaleOperatorName(String saleOperatorName) {
        this.saleOperatorName = saleOperatorName;
    }

    public Integer getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Integer operatorId) {
        this.operatorId = operatorId;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public Integer getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }
}

