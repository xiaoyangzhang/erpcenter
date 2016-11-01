package com.yimayhd.erpcenter.facade.sales.query;

import com.yimayhd.erpcenter.dal.sales.client.sales.vo.TeamGroupVO;

import java.io.Serializable;

/**
 * @ClassName: ${ClassName}
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @Auther xueshengdong
 * @Date 2016/10/27 10:34
 */
public class SaveRequireMentDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private TeamGroupVO teamGroupVO;
    private Integer curBizId;
    private String curUserName;

    public TeamGroupVO getTeamGroupVO() {
        return teamGroupVO;
    }

    public void setTeamGroupVO(TeamGroupVO teamGroupVO) {
        this.teamGroupVO = teamGroupVO;
    }

    public Integer getCurBizId() {
        return curBizId;
    }

    public void setCurBizId(Integer curBizId) {
        this.curBizId = curBizId;
    }

    public String getCurUserName() {
        return curUserName;
    }

    public void setCurUserName(String curUserName) {
        this.curUserName = curUserName;
    }
}
