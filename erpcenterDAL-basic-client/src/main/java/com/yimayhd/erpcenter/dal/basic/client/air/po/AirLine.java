package com.yimayhd.erpcenter.dal.basic.client.air.po;

import java.io.Serializable;
import java.util.Date;

public class AirLine implements Serializable{
    /**
	 * Object Vesion
	 */
	private static final long serialVersionUID = -2298128200099200556L;

	private Integer id;

    private String airCode;

    private String airline;

    private String airlineCode;

    private String depCityCode;

    private String depCity;

    private String depPort;

    private String depTerminal;

    private Date depDate;

    private Date depTime;

    private String arrCityCode;

    private String arrCity;

    private String arrPort;

    private String arrTerminal;

    private Date arrTime;

    private String onTimeRate;
    
    private String stopCity;
    
    private String stopCityCode;
    
    private String stopPort;
    
    private String stopTerminal;
    
    private Date stopArrTime;
    
    private Date stopDepTime;
    
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
     * 航班号
     * @return
     */
    public String getAirCode() {
        return airCode;
    }
    /**
     * 航班号
     * @param airCode
     */
    public void setAirCode(String airCode) {
        this.airCode = airCode == null ? null : airCode.trim();
    }
    /**
     * 航空公司
     * @return
     */
    public String getAirline() {
        return airline;
    }
    /**
     * 航空公司
     * @param airline
     */
    public void setAirline(String airline) {
        this.airline = airline == null ? null : airline.trim();
    }
    /**
     * 航空公司代码
     * @return
     */
    public String getAirlineCode() {
        return airlineCode;
    }
    /**
     * 航空公司代码
     * @param airlineCode
     */
    public void setAirlineCode(String airlineCode) {
        this.airlineCode = airlineCode == null ? null : airlineCode.trim();
    }
    /**
     * 出发城市三字码
     * @return
     */
    public String getDepCityCode() {
        return depCityCode;
    }
    /**
     * 出发城市三字码
     * @param depCityCode
     */
    public void setDepCityCode(String depCityCode) {
        this.depCityCode = depCityCode == null ? null : depCityCode.trim();
    }
    /**
     * 出发城市
     * @return
     */
    public String getDepCity() {
        return depCity;
    }
    /**
     * 出发城市
     * @param depCity
     */
    public void setDepCity(String depCity) {
        this.depCity = depCity == null ? null : depCity.trim();
    }
    /**
     * 出发机场名称
     * @return
     */
    public String getDepPort() {
        return depPort;
    }
    /**
     * 出发机场名称
     * @param depPort
     */
    public void setDepPort(String depPort) {
        this.depPort = depPort == null ? null : depPort.trim();
    }
    /**
     * 出发机场航站楼
     * @return
     */
    public String getDepTerminal() {
        return depTerminal;
    }
    /**
     * 出发机场航站楼
     * @param depTerminal
     */
    public void setDepTerminal(String depTerminal) {
        this.depTerminal = depTerminal == null ? null : depTerminal.trim();
    }
    /**
     * 出发日期
     * @return
     */
    public Date getDepDate() {
        return depDate;
    }
    /**
     * 出发日期
     * @param depDate
     */
    public void setDepDate(Date depDate) {
        this.depDate = depDate;
    }
    /**
     * 出发时间
     * @return
     */
    public Date getDepTime() {
        return depTime;
    }
    /**
     * 出发时间
     * @param depTime
     */
    public void setDepTime(Date depTime) {
        this.depTime = depTime;
    }
    /**
     * 到达城市三字码
     * @return
     */
    public String getArrCityCode() {
        return arrCityCode;
    }
    /**
     * 到达城市三字码
     * @param arrCityCode
     */
    public void setArrCityCode(String arrCityCode) {
        this.arrCityCode = arrCityCode == null ? null : arrCityCode.trim();
    }
    /**
     * 到达城市
     * @return
     */
    public String getArrCity() {
        return arrCity;
    }
    /**
     * 到达城市
     * @param arrCity
     */
    public void setArrCity(String arrCity) {
        this.arrCity = arrCity == null ? null : arrCity.trim();
    }
    /**
     * 到达机场名称
     * @return
     */
    public String getArrPort() {
        return arrPort;
    }
    /**
     * 到达机场名称
     * @param arrPort
     */
    public void setArrPort(String arrPort) {
        this.arrPort = arrPort == null ? null : arrPort.trim();
    }
    /**
     * 到达机场航站楼
     * @return
     */
    public String getArrTerminal() {
        return arrTerminal;
    }
    /**
     * 到达机场航站楼
     * @param arrTerminal
     */
    public void setArrTerminal(String arrTerminal) {
        this.arrTerminal = arrTerminal == null ? null : arrTerminal.trim();
    }
    /**
     * 到达时间
     * @return
     */
    public Date getArrTime() {
        return arrTime;
    }
    /**
     * 到达时间
     * @param arrTime
     */
    public void setArrTime(Date arrTime) {
        this.arrTime = arrTime;
    }
    /**
     * 正点率
     * @return
     */
    public String getOnTimeRate() {
        return onTimeRate;
    }
    /**
     * 正点率
     * @param onTimeRate
     */
    public void setOnTimeRate(String onTimeRate) {
        this.onTimeRate = onTimeRate == null ? null : onTimeRate.trim();
    }
    /**
     * 经停城市
     * @return
     */
	public String getStopCity() {
		return stopCity;
	}
	/**
	 * 经停城市
	 * @param stopCity
	 */
	public void setStopCity(String stopCity) {
		this.stopCity = stopCity;
	}
	/**
	 * 经停城市三字码
	 * @return
	 */
	public String getStopCityCode() {
		return stopCityCode;
	}
	/**
	 * 经停城市三字码
	 * @param stopCityCode
	 */
	public void setStopCityCode(String stopCityCode) {
		this.stopCityCode = stopCityCode;
	}
	/**
	 * 经停城市机场
	 * @return
	 */
	public String getStopPort() {
		return stopPort;
	}
	/**
	 * 经停城市机场
	 * @param stopPort
	 */
	public void setStopPort(String stopPort) {
		this.stopPort = stopPort;
	}
	/**
	 * 经停城市机场航站楼
	 * @return
	 */
	public String getStopTerminal() {
		return stopTerminal;
	}
	/**
	 * 经停城市机场航站楼
	 * @param stopTerminal
	 */
	public void setStopTerminal(String stopTerminal) {
		this.stopTerminal = stopTerminal;
	}
	/**
	 * 经停城市计划到达时间
	 * @return
	 */
	public Date getStopArrTime() {
		return stopArrTime;
	}
	/**
	 * 经停城市计划到达时间
	 * @param stopArrTime
	 */
	public void setStopArrTime(Date stopArrTime) {
		this.stopArrTime = stopArrTime;
	}
	/**
	 * 经停城市计划起飞时间
	 * @return
	 */
	public Date getStopDepTime() {
		return stopDepTime;
	}
	/**
	 * 经停城市计划起飞时间
	 * @param stopDepTime
	 */
	public void setStopDepTime(Date stopDepTime) {
		this.stopDepTime = stopDepTime;
	}
    
}