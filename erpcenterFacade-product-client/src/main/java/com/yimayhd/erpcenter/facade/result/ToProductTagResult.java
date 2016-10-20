package com.yimayhd.erpcenter.facade.result;

import java.util.List;

import com.yimayhd.erpcenter.dal.product.vo.DictWithSelectInfoVo;

/**
 * 
* @ClassName: ToProductTagResult 
* @Description: 
* @author wangjun
* @date 2016年10月19日 上午10:11:29 
*
 */
public class ToProductTagResult extends ResultSupport{
   
    /**
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	*/
	private static final long serialVersionUID = 5313851393817942669L;

	/**
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	*/

	List<DictWithSelectInfoVo> lineThemeListPlus;
    
    List<DictWithSelectInfoVo> lineLevelListPlus;
    
    List<DictWithSelectInfoVo> attendMethodListPlus;
    
    List<DictWithSelectInfoVo> hotelLevelListPlus;
    
    List<DictWithSelectInfoVo> daysPeriodListPlus;
    
    List<DictWithSelectInfoVo> priceRangeListPlus;
    
    List<DictWithSelectInfoVo> exitDestinationListPlus;
    
    List<DictWithSelectInfoVo> domesticDestinationListPlus;
    
    List<DictWithSelectInfoVo> typeListPlus;

	public List<DictWithSelectInfoVo> getLineThemeListPlus() {
		return lineThemeListPlus;
	}

	public void setLineThemeListPlus(List<DictWithSelectInfoVo> lineThemeListPlus) {
		this.lineThemeListPlus = lineThemeListPlus;
	}

	public List<DictWithSelectInfoVo> getLineLevelListPlus() {
		return lineLevelListPlus;
	}

	public void setLineLevelListPlus(List<DictWithSelectInfoVo> lineLevelListPlus) {
		this.lineLevelListPlus = lineLevelListPlus;
	}

	public List<DictWithSelectInfoVo> getAttendMethodListPlus() {
		return attendMethodListPlus;
	}

	public void setAttendMethodListPlus(
			List<DictWithSelectInfoVo> attendMethodListPlus) {
		this.attendMethodListPlus = attendMethodListPlus;
	}

	public List<DictWithSelectInfoVo> getHotelLevelListPlus() {
		return hotelLevelListPlus;
	}

	public void setHotelLevelListPlus(List<DictWithSelectInfoVo> hotelLevelListPlus) {
		this.hotelLevelListPlus = hotelLevelListPlus;
	}

	public List<DictWithSelectInfoVo> getDaysPeriodListPlus() {
		return daysPeriodListPlus;
	}

	public void setDaysPeriodListPlus(List<DictWithSelectInfoVo> daysPeriodListPlus) {
		this.daysPeriodListPlus = daysPeriodListPlus;
	}

	public List<DictWithSelectInfoVo> getPriceRangeListPlus() {
		return priceRangeListPlus;
	}

	public void setPriceRangeListPlus(List<DictWithSelectInfoVo> priceRangeListPlus) {
		this.priceRangeListPlus = priceRangeListPlus;
	}

	public List<DictWithSelectInfoVo> getExitDestinationListPlus() {
		return exitDestinationListPlus;
	}

	public void setExitDestinationListPlus(
			List<DictWithSelectInfoVo> exitDestinationListPlus) {
		this.exitDestinationListPlus = exitDestinationListPlus;
	}

	public List<DictWithSelectInfoVo> getDomesticDestinationListPlus() {
		return domesticDestinationListPlus;
	}

	public void setDomesticDestinationListPlus(
			List<DictWithSelectInfoVo> domesticDestinationListPlus) {
		this.domesticDestinationListPlus = domesticDestinationListPlus;
	}

	public List<DictWithSelectInfoVo> getTypeListPlus() {
		return typeListPlus;
	}

	public void setTypeListPlus(List<DictWithSelectInfoVo> typeListPlus) {
		this.typeListPlus = typeListPlus;
	}
    
    
    
}
