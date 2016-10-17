package com.yimayhd.erpcenter.biz.basic.air.po;

import java.io.Serializable;

public class AirPort implements Serializable{
    /**
	 * Object Version
	 */
	private static final long serialVersionUID = -7151557921938690316L;

	private Integer id;

    private String cityCode;

    private String cityName;

    private String portCode;

    private String portName;

    private String portIntroduction;
    
    private String nameSpell;
    
    private String importantLevel;
    
    /**
     * 主键
     * @return
     */
    public Integer getId() {
        return id;
    }
    /**
     * 主键
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }
    /**
     * 城市代码
     * @return
     */
    public String getCityCode() {
        return cityCode;
    }
    /**
     * 城市代码
     * @param cityCode
     */
    public void setCityCode(String cityCode) {
        this.cityCode = cityCode == null ? null : cityCode.trim();
    }
    /**
     * 城市名称
     * @return
     */
    public String getCityName() {
        return cityName;
    }
    /**
     * 城市名称
     * @param cityName
     */
    public void setCityName(String cityName) {
        this.cityName = cityName == null ? null : cityName.trim();
    }
    /**
     * 机场代码
     * @return
     */
    public String getPortCode() {
        return portCode;
    }
    /**
     * 机场代码
     * @param portCode
     */
    public void setPortCode(String portCode) {
        this.portCode = portCode == null ? null : portCode.trim();
    }
    /**
     * 机场中文名称
     * @return
     */
    public String getPortName() {
        return portName;
    }
    /**
     * 机场中文名称
     * @param portName
     */
    public void setPortName(String portName) {
        this.portName = portName == null ? null : portName.trim();
    }
    /**
     * 机场简介
     * @return
     */
    public String getPortIntroduction() {
        return portIntroduction;
    }
    /**
     * 机场简介
     * @param portIntroduction
     */
    public void setPortIntroduction(String portIntroduction) {
        this.portIntroduction = portIntroduction == null ? null : portIntroduction.trim();
    }
    /**
     * 城市名字拼音（页面输入自动补全）
     * @return
     */
	public String getNameSpell() {
		return nameSpell;
	}
	/**
	 * 城市名字拼音（页面输入自动补全）
	 * @param nameSpell
	 */
	public void setNameSpell(String nameSpell) {
		this.nameSpell = nameSpell;
	}
	/**
	 * 城市重要等级（根据此项来排序）
	 * @return
	 */
	public String getImportantLevel() {
		return importantLevel;
	}
	/**
	 * 城市重要等级（根据此项来排序）
	 * @param importantLevel
	 */
	public void setImportantLevel(String importantLevel) {
		this.importantLevel = importantLevel;
	}
    
}