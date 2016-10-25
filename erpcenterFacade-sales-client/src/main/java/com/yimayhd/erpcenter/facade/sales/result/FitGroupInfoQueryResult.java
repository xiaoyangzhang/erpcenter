package com.yimayhd.erpcenter.facade.sales.result;

import java.io.Serializable;
import java.util.List;

import com.yimayhd.erpcenter.dal.basic.po.DicInfo;
import com.yimayhd.erpcenter.dal.sales.client.sales.vo.FitGroupInfoVO;

public class FitGroupInfoQueryResult implements Serializable {

	private static final long serialVersionUID = -4421057037686131286L;

	private FitGroupInfoVO fitGroupInfoVO;
	private List<DicInfo> pp;

	public FitGroupInfoVO getFitGroupInfoVO() {
		return fitGroupInfoVO;
	}

	public void setFitGroupInfoVO(FitGroupInfoVO fitGroupInfoVO) {
		this.fitGroupInfoVO = fitGroupInfoVO;
	}

	public List<DicInfo> getPp() {
		return pp;
	}

	public void setPp(List<DicInfo> pp) {
		this.pp = pp;
	}
}
