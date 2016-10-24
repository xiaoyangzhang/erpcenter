package com.yimayhd.erpcenter.facade.sys.result;

import java.io.Serializable;

import com.yimayhd.erpcenter.dal.sys.vo.MenuOptVo;
/**
 * 
 * 描述：MenuOptVo对象的封装
 * @author liyong
 * 2016年10月21日
 */
public class MenuOptVoResult extends ResultSupport implements Serializable {

	private MenuOptVo menuOptVo = new MenuOptVo();
	public void setMenuOptVo(MenuOptVo menuOptVo) {
		this.menuOptVo = menuOptVo;
	}
	
	public MenuOptVo getMenuOptVo() {
		return menuOptVo;
	}
}
