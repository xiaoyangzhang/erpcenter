package com.yimayhd.erpcenter.facade.basic.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.yimayhd.erpcenter.biz.basic.service.LogOperatorBiz;
import com.yimayhd.erpcenter.common.po.LogOperator;
import com.yimayhd.erpcenter.facade.basic.result.SingleListResult;
import com.yimayhd.erpcenter.facade.basic.service.LogFacade;

/**
 * @author hongfei.guo
 * @create 2016年10月25日 上午10:55:42
 */
public class LogFacadeImpl implements LogFacade {

	@Autowired
	private LogOperatorBiz logBiz;
	
	@Override
	public SingleListResult singleList(LogOperator log, Integer bizId) {
		
		if (null == log.getTableName())
			log.setTableName("无");
		log.setBizId(bizId);
		
		
		List<LogOperator> list = logBiz.getWhere_NotLogText(log);
		List<Map<String, Object>> logDetail = logBiz.getWhere_LogDetail(log);
		
		if (logDetail != null && logDetail.size() > 0) {
			for(LogOperator item : list){
				List<String> itemDetail = new ArrayList<String>();
				item.setListDetail(itemDetail);
				
				for (Map<String, Object> det : logDetail) {
					if (item.getBatchId().toString().equals(det.get("batch_id").toString())){
						LogOperator logDet = new LogOperator();
						logDet.setAction(det.get("action").toString());
						logDet.setTableName(det.get("table_name").toString());
						logDet.setTableTitle(det.get("table_title").toString());
						logDet.setLogBrief(det.get("log_brief").toString());
						logDet.setLogText(det.get("log_text").toString());
						itemDetail.add(JSON.toJSONString(logDet));
					}
				}
				
			}
		}
		
		SingleListResult result = new SingleListResult();
		result.setList(list);
		result.setLog(log);
		return result;
	}
}
