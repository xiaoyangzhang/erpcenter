package com.yimayhd.erpcenter.dal.product.po;

import java.io.Serializable;
import java.util.Date;

public class TaobaoProduct implements Serializable {
   
    private static final long serialVersionUID = 7308314060476522944L;

    private Integer id;
    private String numIid;
    private String outerId;
    private String cid;
    private String title;
    private String props;
    private Date created;
    private Date modified;
    private String myStoreId;


    private Integer proId;
    private Integer tpsId;
    private String pidName;
    private String pid;
    private String vid;
    private String stockName;






    public Integer getId() {
        return id;
    }




	/**
	 * @return the stockName
	 */
	public String getStockName() {
		return stockName;
	}




	/**
	 * @param stockName the stockName to set
	 */
	public void setStockName(String stockName) {
		this.stockName = stockName;
	}




	public Integer getProId() {
		return proId;
	}

	public void setProId(Integer proId) {
		this.proId = proId;
	}

	public Integer getTpsId() {
		return tpsId;
	}

	public void setTpsId(Integer tpsId) {
		this.tpsId = tpsId;
	}

	public String getPidName() {
		return pidName;
	}

	public void setPidName(String pidName) {
		this.pidName = pidName;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getVid() {
		return vid;
	}

	public void setVid(String vid) {
		this.vid = vid;
	}

	public String getMyStoreId() {
		return myStoreId;
	}

	public void setMyStoreId(String myStoreId) {
		this.myStoreId = myStoreId;
	}


    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumIid() {
        return numIid;
    }

    public void setNumIid(String numIid) {
        this.numIid = numIid == null ? null : numIid.trim();
    }

    public String getOuterId() {
        return outerId;
    }

    public void setOuterId(String outerId) {
        this.outerId = outerId == null ? null : outerId.trim();
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid == null ? null : cid.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getProps() {
        return props;
    }

    public void setProps(String props) {
        this.props = props == null ? null : props.trim();
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }
}
