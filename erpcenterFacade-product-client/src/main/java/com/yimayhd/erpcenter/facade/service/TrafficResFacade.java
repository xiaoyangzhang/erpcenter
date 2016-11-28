
package com.yimayhd.erpcenter.facade.service;

import com.alibaba.fastjson.JSONArray;
import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.dal.product.po.ProductGroupSupplier;
import com.yimayhd.erpcenter.dal.product.po.ProductInfo;
import com.yimayhd.erpcenter.dal.product.po.ProductRemark;
import com.yimayhd.erpcenter.dal.product.po.TrafficRes;
import com.yimayhd.erpcenter.dal.product.vo.ProductGroupSupplierVo;
import com.yimayhd.erpcenter.dal.product.vo.ProductInfoVo;
import com.yimayhd.erpcenter.dal.product.vo.ProductRouteVo;
import com.yimayhd.erpcenter.dal.product.vo.StockStaticCondition;
import com.yimayhd.erpcenter.dal.sys.po.PlatformEmployeePo;
import com.yimayhd.erpcenter.facade.query.*;
import com.yimayhd.erpcenter.facade.result.*;

import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * 
* @ClassName: ProductFacade 
* @Description: 
* @author czy
* @date 2016年10月17日 下午4:26:09 
*
 */
public interface TrafficResFacade {

	TrafficRes selectTrafficResAndLineInfoById1(Integer id);

}
