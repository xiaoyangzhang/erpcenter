/**
 * 
 */
package org.yimayhd.erpcenter.facade.finance.result;

import java.util.List;
import java.util.Map;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.dal.basic.po.DicInfo;
import com.yimayhd.erpcenter.dal.basic.po.RegionInfo;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingDelivery;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingGuide;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.TourGroup;
import com.yimayhd.erpcenter.dal.sales.client.sales.vo.SpecialGroupOrderVO;
import com.yimayhd.erpcenter.dal.sales.client.taobao.po.PlatTaobaoTrade;
import com.yimayhd.erpcenter.dal.sys.po.MsgInfo;
import com.yimayhd.erpresource.dal.constants.BasicConstants;

/**
 * @ClassName: SettleSealListResult
 * @author hongfei.guo
 * @date 2016年10月19日
 */
public class ToEditTaobaoOrderResult extends ResultSupport{

	private static final long serialVersionUID = -5895100754940754874L;

	private SpecialGroupOrderVO vo;
	private int count = 0;
	private List<RegionInfo> cityList;
	private List<RegionInfo> DepartCityList;
	private String guideStr;
	private List<PlatTaobaoTrade> orders;
	private String tbOrderIds;
	private Boolean groupCanEdit;
	private Integer groupId;
	private Map<String, Object> datas;
	private List<BookingDelivery> list;
	private TourGroup tg;
	private List<MsgInfo> msgInfo;
	
	
	
	public List<MsgInfo> getMsgInfo() {
		return msgInfo;
	}
	public void setMsgInfo(List<MsgInfo> msgInfo) {
		this.msgInfo = msgInfo;
	}
	public SpecialGroupOrderVO getVo() {
		return vo;
	}
	public void setVo(SpecialGroupOrderVO vo) {
		this.vo = vo;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public List<RegionInfo> getCityList() {
		return cityList;
	}
	public void setCityList(List<RegionInfo> cityList) {
		this.cityList = cityList;
	}
	public List<RegionInfo> getDepartCityList() {
		return DepartCityList;
	}
	public void setDepartCityList(List<RegionInfo> departCityList) {
		DepartCityList = departCityList;
	}
	public String getGuideStr() {
		return guideStr;
	}
	public void setGuideStr(String guideStr) {
		this.guideStr = guideStr;
	}
	public List<PlatTaobaoTrade> getOrders() {
		return orders;
	}
	public void setOrders(List<PlatTaobaoTrade> orders) {
		this.orders = orders;
	}
	public String getTbOrderIds() {
		return tbOrderIds;
	}
	public void setTbOrderIds(String tbOrderIds) {
		this.tbOrderIds = tbOrderIds;
	}
	public Boolean getGroupCanEdit() {
		return groupCanEdit;
	}
	public void setGroupCanEdit(Boolean groupCanEdit) {
		this.groupCanEdit = groupCanEdit;
	}
	public Integer getGroupId() {
		return groupId;
	}
	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}
	public Map<String, Object> getDatas() {
		return datas;
	}
	public void setDatas(Map<String, Object> datas) {
		this.datas = datas;
	}
	public List<BookingDelivery> getList() {
		return list;
	}
	public void setList(List<BookingDelivery> list) {
		this.list = list;
	}
	public TourGroup getTg() {
		return tg;
	}
	public void setTg(TourGroup tg) {
		this.tg = tg;
	}
}
