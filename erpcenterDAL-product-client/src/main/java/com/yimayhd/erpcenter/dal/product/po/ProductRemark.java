package com.yimayhd.erpcenter.dal.product.po;

import java.io.Serializable;

public class ProductRemark implements Serializable {
    private static final long serialVersionUID = -8830665879783141623L;
    private Integer id;

    private Integer productId;

    private String guestNote;

    private String productFeature;

    private String itemInclude;

    private String childPlan;

    private String shoppingPlan;

    private String itemCharge;

    private String itemFree;

    private String attention;

    private String itemOther;

    private String warmTip;

    private String passort;

    private String itemExclude;

    private String serveLevel;

    private Long createTime;
    private String eatNote;
    private String carNote;
    private String guideNote;
    private String refundRule;
    private String appointRule;
    private String remarkInfo;
    private String insuranceNote;
    private String sightNote;
    
    public String getEatNote() {
		return eatNote;
	}

	public void setEatNote(String eatNote) {
		this.eatNote = eatNote;
	}

	public String getCarNote() {
		return carNote;
	}

	public void setCarNote(String carNote) {
		this.carNote = carNote;
	}

	public String getGuideNote() {
		return guideNote;
	}

	public void setGuideNote(String guideNote) {
		this.guideNote = guideNote;
	}

	public String getRefundRule() {
		return refundRule;
	}

	public void setRefundRule(String refundRule) {
		this.refundRule = refundRule;
	}

	public String getAppointRule() {
		return appointRule;
	}

	public void setAppointRule(String appointRule) {
		this.appointRule = appointRule;
	}

	public String getRemarkInfo() {
		return remarkInfo;
	}

	public void setRemarkInfo(String remarkInfo) {
		this.remarkInfo = remarkInfo;
	}

	public String getInsuranceNote() {
		return insuranceNote;
	}

	public void setInsuranceNote(String insuranceNote) {
		this.insuranceNote = insuranceNote;
	}

	public String getSightNote() {
		return sightNote;
	}

	public void setSightNote(String sightNote) {
		this.sightNote = sightNote;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getGuestNote() {
        return guestNote;
    }

    public void setGuestNote(String guestNote) {
        this.guestNote = guestNote == null ? null : guestNote.trim();
    }

    public String getProductFeature() {
        return productFeature;
    }

    public void setProductFeature(String productFeature) {
        this.productFeature = productFeature == null ? null : productFeature.trim();
    }

    public String getItemInclude() {
        return itemInclude;
    }

    public void setItemInclude(String itemInclude) {
        this.itemInclude = itemInclude == null ? null : itemInclude.trim();
    }

    public String getChildPlan() {
        return childPlan;
    }

    public void setChildPlan(String childPlan) {
        this.childPlan = childPlan == null ? null : childPlan.trim();
    }

    public String getShoppingPlan() {
        return shoppingPlan;
    }

    public void setShoppingPlan(String shoppingPlan) {
        this.shoppingPlan = shoppingPlan == null ? null : shoppingPlan.trim();
    }

    public String getItemCharge() {
        return itemCharge;
    }

    public void setItemCharge(String itemCharge) {
        this.itemCharge = itemCharge == null ? null : itemCharge.trim();
    }

    public String getItemFree() {
        return itemFree;
    }

    public void setItemFree(String itemFree) {
        this.itemFree = itemFree == null ? null : itemFree.trim();
    }

    public String getAttention() {
        return attention;
    }

    public void setAttention(String attention) {
        this.attention = attention == null ? null : attention.trim();
    }

    public String getItemOther() {
        return itemOther;
    }

    public void setItemOther(String itemOther) {
        this.itemOther = itemOther == null ? null : itemOther.trim();
    }

    public String getWarmTip() {
        return warmTip;
    }

    public void setWarmTip(String warmTip) {
        this.warmTip = warmTip == null ? null : warmTip.trim();
    }

    public String getPassort() {
        return passort;
    }

    public void setPassort(String passort) {
        this.passort = passort == null ? null : passort.trim();
    }

    public String getItemExclude() {
        return itemExclude;
    }

    public void setItemExclude(String itemExclude) {
        this.itemExclude = itemExclude == null ? null : itemExclude.trim();
    }

    public String getServeLevel() {
        return serveLevel;
    }

    public void setServeLevel(String serveLevel) {
        this.serveLevel = serveLevel;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }
}