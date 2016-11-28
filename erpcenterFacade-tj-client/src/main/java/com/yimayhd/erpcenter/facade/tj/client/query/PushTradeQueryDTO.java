package com.yimayhd.erpcenter.facade.tj.client.query;

import java.io.Serializable;

public class PushTradeQueryDTO implements Serializable{
	
	private static final long serialVersionUID = -2161258962104381249L;
	
	private String tid;
	private String authClient;
	private String response;
	/**
	 * @return the tid
	 */
	public String getTid() {
		return tid;
	}
	/**
	 * @param tid the tid to set
	 */
	public void setTid(String tid) {
		this.tid = tid;
	}
	/**
	 * @return the authClient
	 */
	public String getAuthClient() {
		return authClient;
	}
	/**
	 * @param authClient the authClient to set
	 */
	public void setAuthClient(String authClient) {
		this.authClient = authClient;
	}
	/**
	 * @return the response
	 */
	public String getResponse() {
		return response;
	}
	/**
	 * @param response the response to set
	 */
	public void setResponse(String response) {
		this.response = response;
	}
	
	
}
