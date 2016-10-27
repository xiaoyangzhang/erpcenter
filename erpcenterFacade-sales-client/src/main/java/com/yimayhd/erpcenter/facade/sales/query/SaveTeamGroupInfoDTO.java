package com.yimayhd.erpcenter.facade.sales.query;

import com.yimayhd.erpcenter.dal.sales.client.sales.vo.TeamGroupVO;

import java.io.Serializable;

/**
 * @ClassName: ${ClassName}
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @Auther xueshengdong
 * @Date 2016/10/26 15:00
 */
public class SaveTeamGroupInfoDTO  implements Serializable {
    private static final long serialVersionUID = 1L;
    private TeamGroupVO teamGroupVO;
    private int curBizId;
    private int curUserId;
    private String curUserName;

    public TeamGroupVO getTeamGroupVO() {
        return teamGroupVO;
    }

    public void setTeamGroupVO(TeamGroupVO teamGroupVO) {
        this.teamGroupVO = teamGroupVO;
    }

    public int getCurBizId() {
        return curBizId;
    }

    public void setCurBizId(int curBizId) {
        this.curBizId = curBizId;
    }

    public int getCurUserId() {
        return curUserId;
    }

    public void setCurUserId(int curUserId) {
        this.curUserId = curUserId;
    }

    public String getCurUserName() {
        return curUserName;
    }

    public void setCurUserName(String curUserName) {
        this.curUserName = curUserName;
    }
}
