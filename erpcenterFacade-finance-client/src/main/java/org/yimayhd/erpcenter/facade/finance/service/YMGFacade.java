package org.yimayhd.erpcenter.facade.finance.service;

import java.text.ParseException;
import java.util.Set;

import org.yimayhd.erpcenter.facade.finance.result.QueryYmgOrderListByOpTable;
import org.yimayhd.erpcenter.facade.finance.result.QueryYmgOrderListTableDataResult;
import org.yimayhd.erpcenter.facade.finance.result.SaveSpecialGroupResult;
import org.yimayhd.erpcenter.facade.finance.result.ToEditTaobaoOrderResult;

import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrder;
import com.yimayhd.erpcenter.dal.sales.client.sales.vo.SpecialGroupOrderVO;
import com.yimayhd.erpcenter.dal.sys.po.PlatformEmployeePo;

/**
 * @author hongfei.guo
 * @create 2016年10月25日 上午10:55:42
 */
public interface YMGFacade{
	
	QueryYmgOrderListTableDataResult queryYmgOrderListTableData(String sidx, String sord, Integer rows, 
			GroupOrder groupOrder, Integer bizId, Set<Integer> dataUserIdSet)  throws ParseException;
	
	GroupOrder ymgOrderListPostFooter(GroupOrder groupOrder, Set<Integer> dataUserIdSet, Integer bizId) throws ParseException;
	
	
	QueryYmgOrderListByOpTable ymgOrderListByOpTable(GroupOrder groupOrder, Integer bizId, Set<Integer> dataUserIdSet) throws ParseException;
	
	SaveSpecialGroupResult saveSpecialGroup(SpecialGroupOrderVO vo, String ids, String id, 
			Integer GroupMode, String myBizCode, Integer bizId, PlatformEmployeePo curUser) throws ParseException;
	
	ToEditTaobaoOrderResult toEditTaobaoOrder(Integer see, Integer id, Integer operType, Integer parentId, Integer curUserId, Integer bizId);
}
