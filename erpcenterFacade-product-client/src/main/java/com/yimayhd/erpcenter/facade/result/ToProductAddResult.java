package com.yimayhd.erpcenter.facade.result;

import com.yimayhd.erpcenter.dal.basic.po.DicInfo;
import com.yimayhd.erpcenter.dal.basic.po.RegionInfo;
import com.yimayhd.erpcenter.dal.product.po.ProductRemark;
import com.yimayhd.erpcenter.dal.product.vo.ProductInfoVo;

import java.util.List;

/**
 * 
* @ClassName: ToProductAddResult 
* @Description: 
* @author wangjun
* @date 2016年10月18日 下午5:36:27 
*
 */
public class ToProductAddResult extends ResultSupport{
    private static final long serialVersionUID = -1L;
    private List<RegionInfo> allProvince;
    private List<DicInfo> brandList;
    
    private ProductInfoVo productInfoVo;
    
    private ProductRemark productRemark;

    public List<RegionInfo> getAllProvince() {
		return allProvince;
	}

	public void setAllProvince(List<RegionInfo> allProvince) {
		this.allProvince = allProvince;
	}

	public List<DicInfo> getBrandList() {
        return brandList;
    }

    public void setBrandList(List<DicInfo> brandList) {
        this.brandList = brandList;
    }

	public ProductInfoVo getProductInfoVo() {
		return productInfoVo;
	}

	public void setProductInfoVo(ProductInfoVo productInfoVo) {
		this.productInfoVo = productInfoVo;
	}

	public ProductRemark getProductRemark() {
		return productRemark;
	}

	public void setProductRemark(ProductRemark productRemark) {
		this.productRemark = productRemark;
	}
    
    
}
