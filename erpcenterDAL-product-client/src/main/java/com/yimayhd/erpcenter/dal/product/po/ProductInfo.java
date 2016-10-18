package com.yimayhd.erpcenter.dal.product.po;

import java.io.Serializable;
import java.util.List;

import com.yimayhd.erpcenter.dal.basic.utils.LogFieldAnno;

public class ProductInfo implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;

	private Integer bizId;

	private String code;

	private Byte type;

	private Integer brandId;
	
	@LogFieldAnno(description="品牌名称")
	private String brandName;

	/**
	 * 产品名称（nameDuration、nameTravelMode、nameStarLevel、nameMode弃用）
	 */
	@LogFieldAnno(description="产品名称")
	private String nameCity;

  /*  private String nameDuration;*/

   /* private String nameTravelMode;*/

   /* private String nameStarLevel;*/
	
	private String nameBrief;

	/*  private String nameMode;*/
	@LogFieldAnno(description="天数")
	private Integer travelDays;
	@LogFieldAnno(description="序号")
	private Integer orderNum;

	private Integer destProvinceId;

	private String destProvinceName;

	private Integer destCityId;

	private String destCityName;

	private Byte state;

	private Integer creatorId;

	private String creatorName;

	private Long createTime;
	private Integer operatorId;
	private String operatorName;
	private String operatorIds;
	private String orgIds;
	private Integer obligate;
	private Integer obligateHour;
	private Integer obligateCount;
	private String obligateRemark;
	private Integer rowSpan;
	private List<ProductGroup> productGroupList;
	private List<ProductGroupSeller> groupSellers;
	private List<ProductRoute> productRouteList;
	private ProductRemark productRemark;
	private Integer  stockCount;
	private String  itemDate;
	private Integer  stock;
	private Integer receiveCount;
	private Integer  productSysId;
	private Integer  sourceType;

	public Integer getProductSysId() {
		return productSysId;
	}
	public void setProductSysId(Integer productSysId) {
		this.productSysId = productSysId;
	}
	public Integer getReceiveCount() {
		return receiveCount;
	}
	public void setReceiveCount(Integer receiveCount) {
		this.receiveCount = receiveCount;
	}
	public Integer getStock() {
		return stock;
	}
	public void setStock(Integer stock) {
		this.stock = stock;
	}
	public String getItemDate() {
		return itemDate;
	}
	public void setItemDate(String itemDate) {
		this.itemDate = itemDate;
	}
	public Integer getStockCount() {
		return stockCount;
	}
	public void setStockCount(Integer stockCount) {
		this.stockCount = stockCount;
	}
	
	public Integer getBizId() {
		return bizId;
	}

	public Integer getBrandId() {
		return brandId;
	}

	public String getBrandName() {
		return brandName;
	}

	public String getCode() {
		return code;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public Integer getCreatorId() {
		return creatorId;
	}

	public String getCreatorName() {
		return creatorName;
	}

	public Integer getDestCityId() {
		return destCityId;
	}

	public String getDestCityName() {
		return destCityName;
	}

	public Integer getDestProvinceId() {
		return destProvinceId;
	}

	public String getDestProvinceName() {
		return destProvinceName;
	}

	public List<ProductGroupSeller> getGroupSellers() {
		return groupSellers;
	}

	public Integer getId() {
		return id;
	}

	public String getNameBrief() {
		return nameBrief;
	}

	public String getNameCity() {
		return nameCity;
	}

	public Integer getObligate() {
		return obligate;
	}

	public Integer getObligateCount() {
		return obligateCount;
	}

	public Integer getObligateHour() {
		return obligateHour;
	}

	public String getObligateRemark() {
		return obligateRemark;
	}

	public Integer getOperatorId() {
		return operatorId;
	}

	public String getOperatorIds() {
		return operatorIds;
	}

	public String getOperatorName() {
		return operatorName;
	}

	public Integer getOrderNum() {
		return orderNum;
	}

	public String getOrgIds() {
		return orgIds;
	}

	public List<ProductGroup> getProductGroupList() {
		return productGroupList;
	}

	public ProductRemark getProductRemark() {
		return productRemark;
	}

	public List<ProductRoute> getProductRouteList() {
		return productRouteList;
	}

	public Integer getRowSpan() {
		return rowSpan;
	}

	public Byte getState() {
		return state;
	}

	public Integer getTravelDays() {
		return travelDays;
	}

	public Byte getType() {
		return type;
	}

	public void setBizId(Integer bizId) {
		this.bizId = bizId;
	}

  /*  public String getNameDuration() {
        return nameDuration;
    }

    public void setNameDuration(String nameDuration) {
        this.nameDuration = nameDuration == null ? null : nameDuration.trim();
    }

    public String getNameTravelMode() {
        return nameTravelMode;
    }

    public void setNameTravelMode(String nameTravelMode) {
        this.nameTravelMode = nameTravelMode == null ? null : nameTravelMode.trim();
    }

    public String getNameStarLevel() {
        return nameStarLevel;
    }

    public void setNameStarLevel(String nameStarLevel) {
        this.nameStarLevel = nameStarLevel == null ? null : nameStarLevel.trim();
    }*/

	public void setBrandId(Integer brandId) {
		this.brandId = brandId;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName == null ? null : brandName.trim();
	}

	public void setCode(String code) {
		this.code = code == null ? null : code.trim();
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public void setCreatorId(Integer creatorId) {
		this.creatorId = creatorId;
	}

	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName == null ? null : creatorName.trim();
	}

	public void setDestCityId(Integer destCityId) {
		this.destCityId = destCityId;
	}

	public void setDestCityName(String destCityName) {
		this.destCityName = destCityName == null ? null : destCityName.trim();
	}

	public void setDestProvinceId(Integer destProvinceId) {
		this.destProvinceId = destProvinceId;
	}

	public void setDestProvinceName(String destProvinceName) {
		this.destProvinceName = destProvinceName == null ? null : destProvinceName.trim();
	}

	public void setGroupSellers(List<ProductGroupSeller> groupSellers) {
		this.groupSellers = groupSellers;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setNameBrief(String nameBrief) {
		this.nameBrief = nameBrief == null ? null : nameBrief.trim();
	}

	public void setNameCity(String nameCity) {
		this.nameCity = nameCity == null ? null : nameCity.trim();
	}

	public void setObligate(Integer obligate) {
		this.obligate = obligate;
	}

	public void setObligateCount(Integer obligateCount) {
		this.obligateCount = obligateCount;
	}

	public void setObligateHour(Integer obligateHour) {
		this.obligateHour = obligateHour;
	}

	public void setObligateRemark(String obligateRemark) {
		this.obligateRemark = obligateRemark;
	}

	public void setOperatorId(Integer operatorId) {
		this.operatorId = operatorId;
	}

	public void setOperatorIds(String operatorIds) {
		this.operatorIds = operatorIds;
	}

	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}

	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}

	public void setOrgIds(String orgIds) {
		this.orgIds = orgIds;
	}

	public void setProductGroupList(List<ProductGroup> productGroupList) {
		this.productGroupList = productGroupList;
	}

	public void setProductRemark(ProductRemark productRemark) {
		this.productRemark = productRemark;
	}

	public void setProductRouteList(List<ProductRoute> productRouteList) {
		this.productRouteList = productRouteList;
	}

	public void setRowSpan(Integer rowSpan) {
		this.rowSpan = rowSpan;
	}

	public void setState(Byte state) {
		this.state = state;
	}

	public void setTravelDays(Integer travelDays) {
		this.travelDays = travelDays;
	}

	public void setType(Byte type) {
		this.type = type;
	}
	public Integer getSourceType() {
		return sourceType;
	}
	public void setSourceType(Integer sourceType) {
		this.sourceType = sourceType;
	}

	/*public String getNameMode() {
		return nameMode;
	}

	public void setNameMode(String nameMode) {
		this.nameMode = nameMode;
	}*/


}