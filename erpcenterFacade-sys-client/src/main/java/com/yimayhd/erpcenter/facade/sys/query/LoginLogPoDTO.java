package com.yimayhd.erpcenter.facade.sys.query;

import java.io.Serializable;

import com.yimayhd.erpcenter.dal.sys.po.LoginLogPo;
/**
 * 
 * 描述：登陆日志参数封装
 * @author liyong
 * 2016年10月21日
 */
public class LoginLogPoDTO implements Serializable {

	/**
	 * 描述：
	 * @author liyong
	 * 2016年10月21日 
	 */
	private static final long serialVersionUID = 1L;
	
	private LoginLogPo loginLogPo = new LoginLogPo();
	
	public void setLoginLogPo(LoginLogPo loginLogPo) {
		this.loginLogPo = loginLogPo;
	}
	public LoginLogPo getLoginLogPo() {
		return loginLogPo;
	}
	
}
