package com.yimayhd.erpcenter.facade.sales.result;

import com.yimayhd.erpcenter.dal.sales.client.sales.vo.TeamGroupVO;

/**
 * @ClassName: ${ClassName}
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @Auther xueshengdong
 * @Date 2016/10/26 15:01
 */
public class SaveTeamGroupInfoResult extends ResultSupport {
    private TeamGroupVO teamGroupVO;

    public TeamGroupVO getTeamGroupVO() {
        return teamGroupVO;
    }

    public void setTeamGroupVO(TeamGroupVO teamGroupVO) {
        this.teamGroupVO = teamGroupVO;
    }
}
