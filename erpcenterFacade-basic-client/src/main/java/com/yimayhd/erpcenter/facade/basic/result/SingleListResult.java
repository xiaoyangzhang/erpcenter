package com.yimayhd.erpcenter.facade.basic.result;


import java.io.Serializable;
import java.util.List;

import com.yimayhd.erpcenter.common.po.LogOperator;


/**
 * hongfei.guo
 */
public class SingleListResult implements Serializable {
    private static final long serialVersionUID = -2235152751651905167L;
   
    private List<LogOperator> list;
    private LogOperator log;
	public List<LogOperator> getList() {
		return list;
	}
	public void setList(List<LogOperator> list) {
		this.list = list;
	}
	public LogOperator getLog() {
		return log;
	}
	public void setLog(LogOperator log) {
		this.log = log;
	}
    
    
}
