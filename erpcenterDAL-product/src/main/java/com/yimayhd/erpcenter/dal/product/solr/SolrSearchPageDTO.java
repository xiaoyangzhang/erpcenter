package com.yimayhd.erpcenter.dal.product.solr;

import java.io.Serializable;
import java.util.List;

import org.springframework.util.CollectionUtils;


public class SolrSearchPageDTO<T> implements Serializable{

    private static final long serialVersionUID = 7378807577314788084L;
    protected int pageNo = 1;
    protected int pageSize;
    protected long totalCount;
    protected boolean hasNext;
    protected List<T> list;

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {

    	if(!CollectionUtils.isEmpty(list) && list.size() >= pageSize){
    		hasNext = true;
    	}
    	
        this.list = list;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        if (pageNo <= 0) {
            pageNo = 1;
        }
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        if (pageSize < 0) {
            pageSize = 0;
        }
        this.pageSize = pageSize;
    }

    public int getStartRow() {
        return (pageNo - 1) * pageSize;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

    public boolean isHasNext() {
    	
        return hasNext;
    }

    public void setHasNext(boolean hasNext) {
        this.hasNext = hasNext;
    }
}
