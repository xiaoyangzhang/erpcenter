package com.yimayhd.erpcenter.dal.product.vo;

import java.io.Serializable;
import java.util.Date;

public class TaoBaoProducts implements Serializable {

    private static final long serialVersionUID = 2338416585676566721L;

    private String cid;
    private Date modified;
    private String num_iid;
    private String outer_id;
    private String title;

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

    public String getNum_iid() {
        return num_iid;
    }

    public void setNum_iid(String num_iid) {
        this.num_iid = num_iid;
    }

    public String getOuter_id() {
        return outer_id;
    }

    public void setOuter_id(String outer_id) {
        this.outer_id = outer_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
