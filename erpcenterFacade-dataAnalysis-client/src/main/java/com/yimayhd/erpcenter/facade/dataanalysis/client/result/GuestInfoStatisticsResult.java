package com.yimayhd.erpcenter.facade.dataanalysis.client.result;

/**
 * @ClassName: ${ClassName}
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @Auther xueshengdong
 * @Date 2016/11/1 19:55
 */
public class GuestInfoStatisticsResult extends BaseResult {
    private String jsonSexStr;
    private String jsonAgeStr;
    private String jsonAirStr;
    private String jsonSourceStr;

    public String getJsonSexStr() {
        return jsonSexStr;
    }

    public void setJsonSexStr(String jsonSexStr) {
        this.jsonSexStr = jsonSexStr;
    }

    public String getJsonAgeStr() {
        return jsonAgeStr;
    }

    public void setJsonAgeStr(String jsonAgeStr) {
        this.jsonAgeStr = jsonAgeStr;
    }

    public String getJsonAirStr() {
        return jsonAirStr;
    }

    public void setJsonAirStr(String jsonAirStr) {
        this.jsonAirStr = jsonAirStr;
    }

    public String getJsonSourceStr() {
        return jsonSourceStr;
    }

    public void setJsonSourceStr(String jsonSourceStr) {
        this.jsonSourceStr = jsonSourceStr;
    }
}
