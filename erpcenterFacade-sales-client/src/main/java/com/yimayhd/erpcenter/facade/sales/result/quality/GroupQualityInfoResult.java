package com.yimayhd.erpcenter.facade.sales.result.quality;

import java.util.List;

import com.yimayhd.erpcenter.dal.sales.client.quality.vo.QualityGroupGuestFeedback;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.TourGroup;
import com.yimayhd.erpcenter.dal.sales.client.sales.vo.GroupFeedbackGroupStaticsVO;
import com.yimayhd.erpcenter.facade.sales.result.BaseStateResult;

public class GroupQualityInfoResult extends BaseStateResult {
	private static final long serialVersionUID = -3598475172350534796L;
	private TourGroup groupInfo;
	private GroupFeedbackGroupStaticsVO groupStaticsVO;
	private List<QualityGroupGuestFeedback> guestFeedbackStaticsList;

	public TourGroup getGroupInfo() {
		return groupInfo;
	}

	public void setGroupInfo(TourGroup groupInfo) {
		this.groupInfo = groupInfo;
	}

	public GroupFeedbackGroupStaticsVO getGroupStaticsVO() {
		return groupStaticsVO;
	}

	public void setGroupStaticsVO(GroupFeedbackGroupStaticsVO groupStaticsVO) {
		this.groupStaticsVO = groupStaticsVO;
	}

	public List<QualityGroupGuestFeedback> getGuestFeedbackStaticsList() {
		return guestFeedbackStaticsList;
	}

	public void setGuestFeedbackStaticsList(List<QualityGroupGuestFeedback> guestFeedbackStaticsList) {
		this.guestFeedbackStaticsList = guestFeedbackStaticsList;
	}
}
