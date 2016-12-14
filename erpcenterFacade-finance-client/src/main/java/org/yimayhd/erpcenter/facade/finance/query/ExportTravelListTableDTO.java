package org.yimayhd.erpcenter.facade.finance.query;

import com.yimayhd.erpcenter.dal.sales.client.sales.vo.TourGroupVO;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @ClassName: ${ClassName}
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @Auther xueshengdong
 * @Date 2016/12/13 18:50
 */
public class ExportTravelListTableDTO {
    private String sl;
    private String ssl;
    private String rp;
    private Integer page;
    private Integer pageSize;
    private String svc;
    private TourGroupVO group;
    private Integer bizId;
    private Map<String, Object> parameters;
    private Set<Integer> userIdSet;
    private String staticsOrgIds;
    private Set<Integer> orgIdSet;
    private List<Integer> groupIds;
    private String[] paramsGroupids;

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

    public String getSvc() {
        return svc;
    }

    public void setSvc(String svc) {
        this.svc = svc;
    }

    public Integer getBizId() {
        return bizId;
    }

    public void setBizId(Integer bizId) {
        this.bizId = bizId;
    }

    public Map<String, Object> getParameters() {
        return parameters;
    }

    public void setParameters(Map<String, Object> parameters) {
        this.parameters = parameters;
    }

    public Set<Integer> getUserIdSet() {
        return userIdSet;
    }

    public void setUserIdSet(Set<Integer> userIdSet) {
        this.userIdSet = userIdSet;
    }

    public String getStaticsOrgIds() {
        return staticsOrgIds;
    }

    public void setStaticsOrgIds(String staticsOrgIds) {
        this.staticsOrgIds = staticsOrgIds;
    }

    public Set<Integer> getOrgIdSet() {
        return orgIdSet;
    }

    public void setOrgIdSet(Set<Integer> orgIdSet) {
        this.orgIdSet = orgIdSet;
    }

    public List<Integer> getGroupIds() {
        return groupIds;
    }

    public void setGroupIds(List<Integer> groupIds) {
        this.groupIds = groupIds;
    }

    public String[] getParamsGroupids() {
        return paramsGroupids;
    }

    public void setParamsGroupids(String[] paramsGroupids) {
        this.paramsGroupids = paramsGroupids;
    }
}
