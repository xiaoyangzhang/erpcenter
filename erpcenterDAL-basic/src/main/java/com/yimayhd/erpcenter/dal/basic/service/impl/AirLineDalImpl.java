package com.yimayhd.erpcenter.dal.basic.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.ognl.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yimayhd.erpcenter.common.exception.ClientException;
import com.yimayhd.erpcenter.dal.basic.constant.BasicConstants;
import com.yimayhd.erpcenter.dal.basic.dao.AirLineMapper;
import com.yimayhd.erpcenter.dal.basic.dao.AirPortMapper;
import com.yimayhd.erpcenter.dal.basic.po.AirLine;
import com.yimayhd.erpcenter.dal.basic.po.AirPort;
import com.yimayhd.erpcenter.dal.basic.service.AirLineDal;
import com.yimayhd.erpcenter.dal.basic.utils.DateUtils;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

@Service("airLineDal")
public class AirLineDalImpl implements AirLineDal {
	
	@Autowired
	private AirLineMapper airLineMapper;
	@Autowired
	private AirPortMapper airPortMapper;
	@Autowired
	TransactionTemplate transactionTemplateBasic;
	
	private static final Logger log = LoggerFactory.getLogger(AirLineDalImpl.class);
	
	@Override
	public int save(AirLine airLine) {
		return airLineMapper.insert(airLine);
	}
	/**
	 * 起飞日期，出发城市，到达城市
	 * @throws IOException 
	 */
	@Override
	//@Transactional
	public List<AirLine> findAirLineByCity(Date date, String depCity,String arrCity) throws IOException{
		String dateStr = DateUtils.format(date,DateUtils.FORMAT_SHORT);
		return findAirLineByCity(dateStr, depCity, arrCity);
	}
	/**
	 * 起飞日期（字符串格式2015-08-20），出发城市名字（北京），到达城市名字（昆明）
	 * @throws IOException 
	 */
	@Override
	//@Transactional
	public List<AirLine> findAirLineByCity(String date, String depCity,String arrCity) throws IOException{
		if(StringUtils.isBlank(date) || StringUtils.isBlank(depCity) || StringUtils.isBlank(arrCity)){
			return null;
		}
		String depCityCode = airPortMapper.getCityCodeByCityName(depCity);
		String arrCityCode = airPortMapper.getCityCodeByCityName(arrCity);
		if(StringUtils.isBlank(depCityCode) || StringUtils.isBlank(arrCityCode)){
			return null;
		}
		return findAirLineByCityCode(date, depCityCode, arrCityCode); 
	}
	/**
	 * 根据城市代码来查询航线（北京：PEK，昆明：KMG）
	 * @param date
	 * @param depCityCode
	 * @param arrCityCode
	 * @return
	 * @throws IOException 
	 */
	//@Transactional
	private List<AirLine> findAirLineByCityCode(String date,String depCityCode,String arrCityCode) throws IOException{
		List<AirLine> list = airLineMapper.findAirLineByCity(date, depCityCode, arrCityCode);
		if(list == null || list.isEmpty() || list.size() == 0){
			Map<String,String> param = new HashMap<String,String>();
			param.put("date", date);
			param.put("start", depCityCode);
			param.put("end", arrCityCode);
			String result = requestFromJuhe("bc",param);
			List<AirLine> airLines = bcResultHandle(result);
			airLineInsertTransactional(airLines);
			list = airLineMapper.findAirLineByCity(date, depCityCode, arrCityCode);
		}
		return list;
	}

	public void airLineInsertTransactional(final List<AirLine> airLines){
		Boolean transactionResult = transactionTemplateBasic.execute(new TransactionCallback<Boolean>() {
			@Override
			public Boolean doInTransaction(TransactionStatus status) {
				try {
						for (AirLine airLine : airLines){
							airLineMapper.insert(airLine);
						}
					return true;
				} catch (Exception e) {
					log.error("新增航班事务失败!", e);
					status.setRollbackOnly();
					return false;
				}
			}
		});
		if (!transactionResult) {

		}
	}


	public void airLineUpdateTransactional(final List<AirLine> airLines){
		Boolean transactionResult = transactionTemplateBasic.execute(new TransactionCallback<Boolean>() {
			@Override
			public Boolean doInTransaction(TransactionStatus status) {
				try {
					for (AirLine airLine : airLines){
						airLineMapper.update(airLine);
					}
					return true;
				} catch (Exception e) {
					log.error("更新航班事务失败!", e);
					status.setRollbackOnly();
					return false;
				}
			}
		});
		if (!transactionResult) {

		}
	}


	/**
	 * 从聚合请求数据
	 * 		1、bc:航线查询
	 * 		2、snew：航班查询
	 * @throws IOException
	 */
	private String requestFromJuhe(String requestType,Map<String,String> param) throws IOException{
		StringBuilder result = new StringBuilder();
		String pathUrl = null;;
		if("bc".equals(requestType)){
			pathUrl =  BasicConstants.JUHE_PLAN_BC_URL + "&key=" + BasicConstants.JUHE_PLAN_APP_KEY + 
						"&date=" + param.get("date") + "&start=" + param.get("start") + "&end=" + param.get("end");
		}else if("snew".equals(requestType)){
			pathUrl = BasicConstants.JUHE_PLAN_SNEW_URL + "&key=" + BasicConstants.JUHE_PLAN_APP_KEY
						+ "&date=" + param.get("date") + "&name=" + param.get("name");
		}
		try{
			URL url = new URL(pathUrl);
			HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
			InputStream is = httpConn.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String readLine = null;
			while ((readLine = br.readLine()) != null) {
				result = result.append(readLine);
			}
			is.close();
			br.close();
			httpConn.disconnect();
		}catch(Exception e){
			log.error("Cannot request data from juhe:" + pathUrl, e);
		}
		return result.toString();
	}
	/**
	 * 航线查询接口返回数据处理
	 * @param result
	 * @throws IOException 
	 * @throws ParseException 
	 */
	//@Transactional
	private List<AirLine> bcResultHandle(String result) throws IOException{
		List<AirLine> airLineList = new ArrayList<AirLine>();
		String resultcode = "resultcode";
		JSONObject obj = JSON.parseObject(result);
		if(obj != null && obj.containsKey(resultcode) && "200".equals(obj.getString(resultcode))){
			JSONArray resultArray = obj.getJSONArray("result");
			for(int i = 0;i < resultArray.size();i++){
				JSONObject lineObj = resultArray.getJSONObject(i);
				AirLine airLine = new AirLine();
				airLine.setAirCode(lineObj.getString("FlightNum"));
				airLine.setAirline(lineObj.getString("Airline"));
				airLine.setAirlineCode(lineObj.getString("AirlineCode"));
				airLine.setArrCity(lineObj.getString("ArrCity"));
				airLine.setArrCityCode(lineObj.getString("ArrCode"));
				airLine.setArrPort(null);
				airLine.setArrTerminal(lineObj.getString("ArrTerminal"));
				airLine.setDepCity(lineObj.getString("DepCity"));
				airLine.setDepCityCode(lineObj.getString("DepCode"));
				airLine.setDepPort(null);
				airLine.setDepTerminal(lineObj.getString("DepTerminal"));
				airLine.setOnTimeRate(lineObj.getString("OnTimeRate"));
				//起飞日期
				airLine.setDepDate(DateUtils.parse(lineObj.getString("FlightDate"),DateUtils.FORMAT_SHORT));
				
				Calendar cal = Calendar.getInstance();
				cal.setTime(airLine.getDepDate());
				
				//起飞时间处理
				String[] depTime = lineObj.getString("DepTime").split(":");
				cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(depTime[0]));
				cal.set(Calendar.MINUTE, Integer.parseInt(depTime[1]));
				cal.set(Calendar.SECOND, 0);
				airLine.setDepTime(cal.getTime());
				
				//降落时间处理
				String arrTime[] = lineObj.getString("ArrTime").split(":");
				cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(arrTime[0]));
				cal.set(Calendar.MINUTE, Integer.parseInt(arrTime[1]));
				cal.set(Calendar.SECOND, 0);
				/*
				 * 时间比较：
				 * 	如果一个小时内到达或者降落时间的小时大于起飞时间的小时，说明为当天；否则为跨天降落
				 */
				cal.add(Calendar.DATE, Integer.parseInt(arrTime[0]) >= Integer.parseInt(depTime[0]) ? 0 : 1);
				airLine.setArrTime(cal.getTime());
				//airLineMapper.insert(airLine);
				
				if(StringUtils.isBlank(airLine.getAirCode()) || StringUtils.isBlank(airLine.getAirline()) || StringUtils.isBlank(airLine.getArrCity()) || StringUtils.isBlank(airLine.getDepCity())){
					log.error("Key name of bc result from juhe changed");
				}
				airLineList.add(airLine);
			}
		}
		return airLineList;
	}
	
	@Override
	//@Transactional
	public List<AirLine> findAirLineByAirCode(String date, String airCode) throws IOException {
		if(StringUtils.isBlank(date) || StringUtils.isBlank(airCode)){
			return null;
		}
		List<AirLine> list = airLineMapper.findAirLineByAirCode(date, airCode);
		if(list == null || list.isEmpty() || list.size() == 0){
			Map<String,String> param = new HashMap<String,String>();
			param.put("date", date);
			param.put("name", airCode);
			String result = requestFromJuhe("snew", param);
			List<AirLine> airLines = snewResultHandle(result);
			airLineUpdateTransactional(airLines);
			list = airLineMapper.findAirLineByAirCode(date, airCode);
		}
		return list;
	}
	
	public AirLine findAirLine(String date, String airCode, String depCity, String arrCity) throws IOException {
		List<AirLine> list = this.findAirLineByCity(date, depCity, arrCity);
		if(list==null || list.size()==0){throw new ClientException("没有找到 城市间的航线，请确认城市名称是否正确");}
		for(int i=0; i<list.size(); i++){
			if (list.get(i).getAirCode().equals(airCode)){
				return list.get(i);
			}
		}
		throw new ClientException("没有找到此航线信息，请人工确认航线正确");
	}
	
	/**
	 * 处理从航班查询（新）接口返回的数据(可能经停多次吗？目前只支持经停一次)
	 * @param result
	 * @throws IOException 
	 */
	//@Transactional
	private List<AirLine> snewResultHandle(String result) throws IOException{
		List<AirLine> airLines = new ArrayList<AirLine>();
		JSONObject obj = JSON.parseObject(result);
		if(obj != null && obj.containsKey("resultcode") && obj.getString("resultcode").equals("200")){
			JSONObject resultObj = obj.getJSONObject("result");
			JSONObject infoObj = resultObj.getJSONObject("info");
			JSONArray listArray = resultObj.getJSONArray("list");
			String depCityCode = infoObj.getString("from_code");
			if(StringUtils.isBlank(depCityCode)){
				depCityCode = infoObj.getString("form_code");
			}
			String arrCityCode = infoObj.getString("to_code");
			String date = infoObj.getString("date");
			String airCode = infoObj.getString("fno");
			findAirLineByCityCode(date, depCityCode, arrCityCode);
			if(StringUtils.isBlank(depCityCode) || StringUtils.isBlank(arrCityCode)){
				log.error("Cannot get depCityCode or arrCityCode from juhe snew api");
			}
			if(listArray.size() > 1){
				String stopCity = null;		//经停城市
				String stopCityCode = null;	//经停城市三字码
				Date jhddtime_full = null;	//经停城市计划到达时间
				Date jhqftime_full = null;	//经停城市计划起飞时间
				for(int i = 0;i < listArray.size();i++){
					JSONObject sepObj = listArray.getJSONObject(i);
					AirPort airPort = airPortMapper.getAirPortByPortName(sepObj.getString("dd_simple"));
					if(airPort == null){
						log.error("Cannot get airport by portName,juhe data has not changing key name");
						continue ;
					}
					stopCityCode = airPort.getCityCode();
					//如果到达城市代码跟最终到达目的地城市代码一样，说明list排序规则紊乱，因此要加逻辑判断
					if(stopCityCode.equals(arrCityCode)){
						jhqftime_full = sepObj.getDate("jhqftime_full");
						findAirLineByCityCode(date, stopCityCode, arrCityCode);
						stopCityCode = null;
					}else{
						stopCityCode = airPort.getCityCode();
						stopCity = airPort.getCityName();
						jhddtime_full = sepObj.getDate("jhddtime_full");
						findAirLineByCityCode(date, depCityCode, stopCityCode);
					}
				}
				AirLine finalAirLine = airLineMapper.getFinalAirLineByMultiConditon(date,depCityCode,arrCityCode,airCode);
				if(finalAirLine != null){
					finalAirLine.setStopCity(stopCity);
					finalAirLine.setStopCityCode(stopCityCode);
					finalAirLine.setStopArrTime(jhddtime_full);
					finalAirLine.setStopDepTime(jhqftime_full);
					airLines.add(finalAirLine);
					//airLineMapper.update(finalAirLine);
				}
			}
		}
		return airLines;
	}

}
