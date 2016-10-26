package com.yimayhd.erpcenter.facade.sales.result;

import com.yimayhd.erpcenter.dal.basic.po.DicInfo;
import com.yimayhd.erpcenter.dal.basic.po.RegionInfo;
import com.yimayhd.erpcenter.dal.sales.client.sales.vo.TeamGroupVO;

import java.util.List;

/**
 * @ClassName: ${ClassName}
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @Auther xueshengdong
 * @Date 2016/10/26 10:12
 */
public class ToAddTeamGroupInfoResult extends ResultSupport {
    private TeamGroupVO teamGroupVO;
    private List<DicInfo> typeList;
    private List<DicInfo> sourceTypeList;
    private List<RegionInfo> allProvince;
    private List<DicInfo> jtfsList;
    private List<DicInfo> zjlxList;
    private List<DicInfo> lysfxmList;
    private String jsonStr;

    public List<DicInfo> getLysfxmList() {
        return lysfxmList;
    }

    public void setLysfxmList(List<DicInfo> lysfxmList) {
        this.lysfxmList = lysfxmList;
    }

    public TeamGroupVO getTeamGroupVO() {
        return teamGroupVO;
    }

    public void setTeamGroupVO(TeamGroupVO teamGroupVO) {
        this.teamGroupVO = teamGroupVO;
    }

    public List<DicInfo> getTypeList() {
        return typeList;
    }

    public void setTypeList(List<DicInfo> typeList) {
        this.typeList = typeList;
    }

    public List<DicInfo> getSourceTypeList() {
        return sourceTypeList;
    }

    public void setSourceTypeList(List<DicInfo> sourceTypeList) {
        this.sourceTypeList = sourceTypeList;
    }

    public List<RegionInfo> getAllProvince() {
        return allProvince;
    }

    public void setAllProvince(List<RegionInfo> allProvince) {
        this.allProvince = allProvince;
    }

    public List<DicInfo> getJtfsList() {
        return jtfsList;
    }

    public void setJtfsList(List<DicInfo> jtfsList) {
        this.jtfsList = jtfsList;
    }

    public List<DicInfo> getZjlxList() {
        return zjlxList;
    }

    public void setZjlxList(List<DicInfo> zjlxList) {
        this.zjlxList = zjlxList;
    }

    public String getJsonStr() {
        return jsonStr;
    }

    public void setJsonStr(String jsonStr) {
        this.jsonStr = jsonStr;
    }
}
