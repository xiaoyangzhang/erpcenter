package com.yimayhd.erpcenter.facade.dataanalysis.client.query;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.dal.sales.client.operation.vo.PaymentExportVO;
import com.yimayhd.erpcenter.dal.sales.client.query.vo.DeparentmentOrderCondition;
import com.yimayhd.erpcenter.dal.sales.client.query.vo.ProductGuestCondition;
import com.yimayhd.erpcenter.dal.sales.client.query.vo.ProductGuestShoppingCondition;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrder;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.TourGroup;
import com.yimayhd.erpcenter.dal.sales.client.sales.vo.TourGroupVO;
import org.springframework.ui.ModelMap;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;

/**
 * @ClassName: ${ClassName}
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @Auther xueshengdong
 * @Date 2016/11/1 18:05
 */
public class QueryDTO implements Serializable {
    private GroupOrder groupOrder;
    private String productBrandId;
    private String saleOperatorIds;
    private String orgIds;
    private Integer page;
    private Integer pageSize;
    private Integer bizId;
    private Map<String, Object> parameters;
    private Set<Integer> userIdSet;
    private String staticsOrgIds;
    private Set<Integer> orgIdSet;
    private Integer groupId;
    private TourGroupVO group;
    private TourGroup tourGroup;
    private ProductGuestCondition productGuestCondition;
    private PageBean pageBean;
    private String citysSupplierIds;
    private String supplierLevel;
    private PaymentExportVO vo;

    private String groupMode;

    private ProductGuestShoppingCondition productGuestShoppingCondition;

    public ProductGuestShoppingCondition getProductGuestShoppingCondition() {
        return productGuestShoppingCondition;
    }

    public void setProductGuestShoppingCondition(ProductGuestShoppingCondition productGuestShoppingCondition) {
        this.productGuestShoppingCondition = productGuestShoppingCondition;
    }

    private Integer dataType;

    public Integer getDataType() {
        return dataType;
    }

    public void setDataType(Integer dataType) {
        this.dataType = dataType;
    }

    public String getCitysSupplierIds() {
        return citysSupplierIds;
    }

    public void setCitysSupplierIds(String citysSupplierIds) {
        this.citysSupplierIds = citysSupplierIds;
    }

    public String getSupplierLevel() {
        return supplierLevel;
    }

    public void setSupplierLevel(String supplierLevel) {
        this.supplierLevel = supplierLevel;
    }

    public PageBean getPageBean() {
        return pageBean;
    }

    public void setPageBean(PageBean pageBean) {
        this.pageBean = pageBean;
    }

    public ProductGuestCondition getProductGuestCondition() {
        return productGuestCondition;
    }

    public void setProductGuestCondition(ProductGuestCondition productGuestCondition) {
        this.productGuestCondition = productGuestCondition;
    }

    public TourGroup getTourGroup() {
        return tourGroup;
    }

    public void setTourGroup(TourGroup tourGroup) {
        this.tourGroup = tourGroup;
    }

    private String sl;
    private String ssl;
    private  String rp;
    private String svc;

    public TourGroupVO getGroup() {
        return group;
    }

    public void setGroup(TourGroupVO group) {
        this.group = group;
    }

    public String getSl() {
        return sl;
    }

    public void setSl(String sl) {
        this.sl = sl;
    }

    public String getSsl() {
        return ssl;
    }

    public void setSsl(String ssl) {
        this.ssl = ssl;
    }

    public String getRp() {
        return rp;
    }

    public void setRp(String rp) {
        this.rp = rp;
    }

    public String getSvc() {
        return svc;
    }

    public void setSvc(String svc) {
        this.svc = svc;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Set<Integer> getOrgIdSet() {
        return orgIdSet;
    }

    public void setOrgIdSet(Set<Integer> orgIdSet) {
        this.orgIdSet = orgIdSet;
    }

    public DeparentmentOrderCondition getCondition() {
        return condition;
    }

    public void setCondition(DeparentmentOrderCondition condition) {
        this.condition = condition;
    }

    private DeparentmentOrderCondition condition;


    public String getStaticsOrgIds() {
        return staticsOrgIds;
    }

    public void setStaticsOrgIds(String staticsOrgIds) {
        this.staticsOrgIds = staticsOrgIds;
    }

    public Set<Integer> getUserIdSet() {
        return userIdSet;
    }

    public void setUserIdSet(Set<Integer> userIdSet) {
        this.userIdSet = userIdSet;
    }

    public Map<String, Object> getParameters() {
        return parameters;
    }

    public void setParameters(Map<String, Object> parameters) {
        this.parameters = parameters;
    }

    public Integer getBizId() {
        return bizId;
    }

    public void setBizId(Integer bizId) {
        this.bizId = bizId;
    }

    public GroupOrder getGroupOrder() {
        return groupOrder;
    }

    public void setGroupOrder(GroupOrder groupOrder) {
        this.groupOrder = groupOrder;
    }

    public String getProductBrandId() {
        return productBrandId;
    }

    public void setProductBrandId(String productBrandId) {
        this.productBrandId = productBrandId;
    }

    public String getSaleOperatorIds() {
        return saleOperatorIds;
    }

    public void setSaleOperatorIds(String saleOperatorIds) {
        this.saleOperatorIds = saleOperatorIds;
    }

    public String getOrgIds() {
        return orgIds;
    }

    public void setOrgIds(String orgIds) {
        this.orgIds = orgIds;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getGroupMode() {
        return groupMode;
    }

    public void setGroupMode(String groupMode) {
        this.groupMode = groupMode;
    }

    public PaymentExportVO getVo() {
        return vo;
    }

    public void setVo(PaymentExportVO vo) {
        this.vo = vo;
    }
}
