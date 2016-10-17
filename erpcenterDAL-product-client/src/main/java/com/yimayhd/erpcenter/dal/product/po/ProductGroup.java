package com.yimayhd.erpcenter.dal.product.po;

import java.io.Serializable;
import java.util.List;

public class ProductGroup implements Serializable {
    private static final long serialVersionUID = -1483546697645210088L;
    private Integer id;

    private Integer productId;

    private String name;

    private String cityDeparture;

    private Long createTime;

    private String flag;//是否过期
    private Byte state;
    private Integer groupSupplierId;
    private List<ProductGroupPrice> groupPrices;
    private Integer rowSpan;//价格组跨行数
    private boolean checked;//组团版/批量设置用户
    
    
   

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public Integer getRowSpan() {
		return rowSpan;
	}

	public void setRowSpan(Integer rowSpan) {
		this.rowSpan = rowSpan;
	}

	public List<ProductGroupPrice> getGroupPrices() {
		return groupPrices;
	}

	public void setGroupPrices(List<ProductGroupPrice> groupPrices) {
		this.groupPrices = groupPrices;
	}

	public Integer getGroupSupplierId() {
		return groupSupplierId;
	}

	public void setGroupSupplierId(Integer groupSupplierId) {
		this.groupSupplierId = groupSupplierId;
	}

	/**
     * 0分别定价 1统一定价
     */
    private Integer groupSetting;
    
    
    public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public Byte getState() {
		return state;
	}

	public void setState(Byte state) {
		this.state = state;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getCityDeparture() {
        return cityDeparture;
    }

    public void setCityDeparture(String cityDeparture) {
        this.cityDeparture = cityDeparture == null ? null : cityDeparture.trim();
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

	public Integer getGroupSetting() {
		return groupSetting;
	}

	public void setGroupSetting(Integer groupSetting) {
		this.groupSetting = groupSetting;
	}
    
}