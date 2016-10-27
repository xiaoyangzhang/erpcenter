package com.yimayhd.erpcenter.facade.supplier.result;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.yimayhd.erpcenter.dal.basic.po.DicInfo;
import com.yimayhd.erpcenter.dal.basic.po.RegionInfo;
import com.yimayhd.erpresource.dal.po.GuestConsult;
import com.yimayhd.erpresource.dal.po.GuestConsultFollow;
/**
 * 描述：TODO
 * @author liyong
 * 2016年10月25日 
 */
public class FollowConsultResult extends ResultSupport implements Serializable{
	
	/**
	 * @author liyong
	 * 2016年10月27日 描述：
	 */
	private static final long serialVersionUID = 1L;

	private List<RegionInfo> allProvince = new ArrayList<RegionInfo>();
	
	private List<DicInfo> guestSources = new ArrayList<DicInfo>();
	private List<DicInfo> intentionDests = new ArrayList<DicInfo>();
	private List<DicInfo> infoSources = new ArrayList<DicInfo>();
	private List<DicInfo> followWays = new ArrayList<DicInfo>();
	
	private GuestConsult guestConsult = new GuestConsult();
	
	private List<GuestConsultFollow> consultFollows = new ArrayList<GuestConsultFollow>();
	
	private List<RegionInfo> cityList = new ArrayList<RegionInfo>();

	/**
	 * @return getAllProvince获取 allProvince 的值
	 */
	public List<RegionInfo> getAllProvince() {
		return allProvince;
	}

	/**
	 * @param setAllProvince 设置  allProvince 的值
	 */
	public void setAllProvince(List<RegionInfo> allProvince) {
		this.allProvince = allProvince;
	}

	/**
	 * @return getGuestSources获取 guestSources 的值
	 */
	public List<DicInfo> getGuestSources() {
		return guestSources;
	}

	/**
	 * @param setGuestSources 设置  guestSources 的值
	 */
	public void setGuestSources(List<DicInfo> guestSources) {
		this.guestSources = guestSources;
	}

	/**
	 * @return getIntentionDests获取 intentionDests 的值
	 */
	public List<DicInfo> getIntentionDests() {
		return intentionDests;
	}

	/**
	 * @param setIntentionDests 设置  intentionDests 的值
	 */
	public void setIntentionDests(List<DicInfo> intentionDests) {
		this.intentionDests = intentionDests;
	}

	/**
	 * @return getInfoSources获取 infoSources 的值
	 */
	public List<DicInfo> getInfoSources() {
		return infoSources;
	}

	/**
	 * @param setInfoSources 设置  infoSources 的值
	 */
	public void setInfoSources(List<DicInfo> infoSources) {
		this.infoSources = infoSources;
	}

	/**
	 * @return getFollowWays获取 followWays 的值
	 */
	public List<DicInfo> getFollowWays() {
		return followWays;
	}

	/**
	 * @param setFollowWays 设置  followWays 的值
	 */
	public void setFollowWays(List<DicInfo> followWays) {
		this.followWays = followWays;
	}

	/**
	 * @return getGuestConsult获取 guestConsult 的值
	 */
	public GuestConsult getGuestConsult() {
		return guestConsult;
	}

	/**
	 * @param setGuestConsult 设置  guestConsult 的值
	 */
	public void setGuestConsult(GuestConsult guestConsult) {
		this.guestConsult = guestConsult;
	}

	/**
	 * @return getConsultFollows获取 consultFollows 的值
	 */
	public List<GuestConsultFollow> getConsultFollows() {
		return consultFollows;
	}

	/**
	 * @param setConsultFollows 设置  consultFollows 的值
	 */
	public void setConsultFollows(List<GuestConsultFollow> consultFollows) {
		this.consultFollows = consultFollows;
	}

	/**
	 * @return getCityList获取 cityList 的值
	 */
	public List<RegionInfo> getCityList() {
		return cityList;
	}

	/**
	 * @param setCityList 设置  cityList 的值
	 */
	public void setCityList(List<RegionInfo> cityList) {
		this.cityList = cityList;
	}
	
	

}
