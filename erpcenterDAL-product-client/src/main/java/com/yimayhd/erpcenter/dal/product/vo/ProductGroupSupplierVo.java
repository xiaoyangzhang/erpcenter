package com.yimayhd.erpcenter.dal.product.vo;

import java.io.Serializable;
import java.util.List;

import com.yimayhd.erpcenter.dal.product.po.ProductGroupSupplier;
import com.yimayhd.erpcenter.dal.product.po.ProductInfo;


/**
 * Created by Administrator on 2015/8/21.
 */
public class ProductGroupSupplierVo implements Serializable{

    private static final long serialVersionUID = 18454655721671570L;
    private Integer groupId;

    private Integer supplierId;
    private Integer id;
    private String supplierName;
    private String province;
    private String city;
    private String area;
    private String town;
    private Long createTime;
    private Byte state;
    private ProductInfo productInfo;
    private List<ProductGroupSupplier> productGroupSupplierList;
    
    public ProductInfo getProductInfo() {
		return productInfo;
	}

	public void setProductInfo(ProductInfo productInfo) {
		this.productInfo = productInfo;
	}

	public List<ProductGroupSupplier> getProductGroupSupplierList() {
		return productGroupSupplierList;
	}

	public void setProductGroupSupplierList(
			List<ProductGroupSupplier> productGroupSupplierList) {
		this.productGroupSupplierList = productGroupSupplierList;
	}

	private Integer stock;//库存

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Integer getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Byte getState() {
        return state;
    }

    public void setState(Byte state) {
        this.state = state;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }
}
