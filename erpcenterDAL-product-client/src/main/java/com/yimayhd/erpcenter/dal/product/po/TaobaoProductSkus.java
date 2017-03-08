package com.yimayhd.erpcenter.dal.product.po;

import java.io.Serializable;

public class TaobaoProductSkus implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 2394887831924324488L;

	private Integer id;

    private String numIid;

    private String taobaoProductId;

    private String pid;

    private String vid;

    private String pidName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumIid() {
        return numIid;
    }

    public void setNumIid(String numIid) {
        this.numIid = numIid;
    }

    public String getTaobaoProductId() {
        return taobaoProductId;
    }

    public void setTaobaoProductId(String taobaoProductId) {
        this.taobaoProductId = taobaoProductId == null ? null : taobaoProductId.trim();
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid == null ? null : pid.trim();
    }

    public String getVid() {
        return vid;
    }

    public void setVid(String vid) {
        this.vid = vid == null ? null : vid.trim();
    }

    public String getPidName() {
        return pidName;
    }

    public void setPidName(String pidName) {
        this.pidName = pidName == null ? null : pidName.trim();
    }
}