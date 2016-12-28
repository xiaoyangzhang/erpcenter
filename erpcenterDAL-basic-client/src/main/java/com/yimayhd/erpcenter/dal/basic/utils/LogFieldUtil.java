package com.yimayhd.erpcenter.dal.basic.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yimayhd.erpcenter.dal.basic.constant.BasicConstants;
import com.yimayhd.erpcenter.dal.basic.po.LogFieldAnnoInfo;
import com.yimayhd.erpcenter.dal.basic.po.LogOperator;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author ouzy 2016-9-14
 * descript:传入两个对象，实现数据库mapping的model对象的logFieldAnno注解字段的对比
 *
 */
public class LogFieldUtil {
	
	/**
	 * 保存前，生成修改日志
	 * @param objEdit 修改后的页面对象
	 * @param objOrgin 从数据库取出来的对象
	 * @return 
	 */
	private static String getLogText_EditCompare(Object objEdit, Object objOrgin) {
		List<LogFieldAnnoInfo> logList = new ArrayList<LogFieldAnnoInfo>();
		
		Class<? extends Object> clazz = objEdit.getClass();
		Field[] fields = clazz.getDeclaredFields(); 

		for(Field f : fields){  
			//获取字段中包含LogField的注解 
			if (f.isAnnotationPresent(LogFieldAnno.class)){
				LogFieldAnno logf = f.getAnnotation(LogFieldAnno.class); 
				//logf.description()
				
				PropertyDescriptor pd;
				try {
					pd = new PropertyDescriptor(f.getName(), clazz);
					Method getMethod = pd.getReadMethod();//获得get方法 
		            try {
						Object oVal1 = getMethod.invoke(objEdit); //执行get方法返回一个Object
						Object oVal2 = getMethod.invoke(objOrgin);
						
						if (!Compare_ObjValueIsEqual(oVal1, oVal2, f.getType())){
							LogFieldAnnoInfo info = new LogFieldAnnoInfo();
							info.setFieldName(f.getName());
							info.setFieldType(f.getType().getName());
							
							info.setFieldDescription(logf.description());
							info.setFieldAction(BasicConstants.LOG_ACTION.UPDATE.toString());
							if (java.util.Date.class.equals(f.getType())){
								info.setValueEdit(null==oVal1?"":NumberUtil.formatDate((java.util.Date)oVal1));
								info.setValueOrigin(null==oVal2?"":NumberUtil.formatDate((java.util.Date)oVal2));
							}else{
								if (!"".equals(logf.needExtDescription())){
										oVal1 = get_ParseJSONStringByKey(logf.needExtDescription(), oVal1.toString());
										oVal2 = get_ParseJSONStringByKey(logf.needExtDescription(), oVal2.toString());
								}
								
								info.setValueEdit(oVal1.toString());
								info.setValueOrigin(oVal2.toString());
								
							}
							logList.add(info);
						}
						
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						e.printStackTrace();
					}
				} catch (IntrospectionException e) {
					e.printStackTrace();
				}  
	             
			}
             
        }
		String ret = "";
		if (!logList.isEmpty()) ret = JSON.toJSONString(logList);
		return ret;
	}
	
	private static String getLogText_DelOrInsertCompare(BasicConstants.LOG_ACTION action, Object objUpdate) {
		List<LogFieldAnnoInfo> logList = new ArrayList<LogFieldAnnoInfo>();
		
		Class<? extends Object> clazz = objUpdate.getClass();
		Field[] fields = clazz.getDeclaredFields(); 

		for(Field f : fields){  
			//获取字段中包含LogField的注解 
			if (f.isAnnotationPresent(LogFieldAnno.class)){
				LogFieldAnno logf = f.getAnnotation(LogFieldAnno.class); 
				//logf.description()
				
				PropertyDescriptor pd;
				try {
					pd = new PropertyDescriptor(f.getName(), clazz);
					Method getMethod = pd.getReadMethod();//获得get方法 
		            try {
						Object oVal1 = getMethod.invoke(objUpdate); //执行get方法返回一个Object
						if (null!=oVal1 && logf.delOrIns()){
							LogFieldAnnoInfo info = new LogFieldAnnoInfo();
							info.setFieldName(f.getName());
							info.setFieldType(f.getType().toString());
							info.setFieldDescription(logf.description());
							info.setFieldAction(action.toString());
							if (java.util.Date.class.equals(f.getType())){
								info.setValueEdit(null==oVal1?"":NumberUtil.formatDate((java.util.Date)oVal1));
							}else{
								if (!"".equals(logf.needExtDescription())){
									oVal1 = get_ParseJSONStringByKey(logf.needExtDescription(), oVal1.toString());
								}
								info.setValueEdit(oVal1.toString());
							}
							info.setValueOrigin("");
							logList.add(info);
						}
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						e.printStackTrace();
					}
				} catch (IntrospectionException e) {
					e.printStackTrace();
				}  
	             
			}
             
        }
		return JSON.toJSONString(logList);
	}
	

	private static String getLogText_InstantKeyValue(Object objUpdate) {
		String ret = "";
		
		Class<? extends Object> clazz = objUpdate.getClass();
		Field[] fields = clazz.getDeclaredFields(); 

		for(Field f : fields){  
			//获取字段中包含LogField的注解 
			if (f.isAnnotationPresent(LogFieldAnno.class)){
				LogFieldAnno logf = f.getAnnotation(LogFieldAnno.class); 
				if (!logf.isKey()) continue;
				
				PropertyDescriptor pd;
				try {
					pd = new PropertyDescriptor(f.getName(), clazz);
					Method getMethod = pd.getReadMethod();//获得get方法 
		            try {
						Object oVal = getMethod.invoke(objUpdate); //执行get方法返回一个Object
						if (null != oVal)
							ret = oVal.toString();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						e.printStackTrace();
					}
				} catch (IntrospectionException e) {
					e.printStackTrace();
				}  
	             
			}
             
        }
		return ret;
	}
	
	private static LogOperator getLog_Object(int bizId, String editUser, BasicConstants.LOG_ACTION action, String tableName, Integer tableId,
											 Integer tableParentId, String logBrief, String logText) {
		LogOperator log = new LogOperator();
		log.setId(0);
		log.setBizId(bizId);
		log.setBatchId(0);
		log.setAction(action.toString());
		log.setTableName(tableName);
		log.setTableId((null == tableId?0:tableId));
		log.setTableParentId(tableParentId);
		log.setLogBrief(logBrief);
		log.setLogText(logText);
		log.setEditUser(editUser);
		log.setLogText(logText);
		
		return log;
	}
	
	private static String get_ParseJSONStringByKey(String JsonStr, String Key){
		String ret = "";
		JSONArray jary = JSON.parseArray(JsonStr);
		for(int i = 0 ; i < jary.size() ; i++){
			JSONObject Obj = jary.getJSONObject(i);
			if ( Key.equals(Obj.getString("key"))){
				ret = Obj.getString("value");
			}
		}
		return ret;
	}
	
	/**
	 * 获取单条日志记录对象
	 * @param bizId
	 * @param editUser
	 * @param action
	 * @param tableName
	 * @param tableId
	 * @param logBrief
	 * @param editObj
	 * @param originObj
	 * @return
	 */
	public static LogOperator getLog_Instant(int bizId, String editUser, BasicConstants.LOG_ACTION action, String tableName, Integer tableId,
											 Integer tableParentId, String logBrief, Object editObj, Object originObj) {
		
		String logText = "";
		if (null != originObj){
			logText = getLogText_EditCompare(editObj, originObj); //原始数据库里的对象不为null，说明是更改，而不是 新增或删除
		}else{
			if (null != editObj)
				logText = getLogText_DelOrInsertCompare(action, editObj);
		}

		LogOperator log = getLog_Object(bizId,editUser, action, tableName, tableId, tableParentId, logBrief, logText);
		return log;
	}
	
	/**
	 * 获取多条日志记录对象
	 * @param bizId
	 * @param editUser
	 * @param tableName
	 * @param editList
	 * @param originList
	 * @return
	 */
	public static List<LogOperator> getLog_InstantList(int bizId, String editUser, String tableName, Integer tableParentId,
			List<?> editList, List<?> originList) {
		List<LogOperator> logList= new ArrayList<LogOperator>();
		
		//begin  遍历两个List 组成一个大字符串
		String key1 = "", key2 = "", logText = "", logBrief = "";
		boolean isExists;
		BasicConstants.LOG_ACTION rowAction = BasicConstants.LOG_ACTION.INSERT;
		
		//两个List对比（新增、更新部份）
		if (null != editList){
			for(Object objEd : editList){
				key1 = getLogText_InstantKeyValue(objEd);
				isExists = false;
				if(null != originList){
				for(Object objOri : originList){
					key2 = getLogText_InstantKeyValue(objOri);
					if (key1.equals(key2)){
						isExists = true;
						rowAction = BasicConstants.LOG_ACTION.UPDATE;
						logBrief = "修改记录";
						logText = getLogText_EditCompare(objEd, objOri);
						break;
					}
				}
				}
				if (!isExists){
					rowAction = BasicConstants.LOG_ACTION.INSERT;
					logText = getLogText_DelOrInsertCompare(rowAction, objEd);
					logBrief = "创建记录";
					
				}
				if (!"".equals(logText))
				{
					key1 = "".equals(key1)? "0" : key1;
					LogOperator log = getLog_Object(bizId, editUser, rowAction, tableName, Integer.valueOf(key1), tableParentId, logBrief, logText);
					logList.add(log);
				}
			}
		}
		
		//两个List对比（删除部份）
		if(null !=originList){
		for(Object objOri : originList){
			key1 = getLogText_InstantKeyValue(objOri);
			isExists = false;
			if (null != editList){
				for(Object objEd : editList){
					key2 = getLogText_InstantKeyValue(objEd);
					if (key1.equals(key2)){
						isExists = true;
						break;
					}
				}
			}
			if (!isExists){
				rowAction = BasicConstants.LOG_ACTION.DELETE;
				logText = getLogText_DelOrInsertCompare(rowAction, objOri);
				logBrief = "删除记录";
				LogOperator log = getLog_Object(bizId, editUser, rowAction, tableName, Integer.valueOf(key1), tableParentId, logBrief, logText);
				logList.add(log);
			}
		}
		}
		return logList;
	}
	
	private static boolean Compare_ObjValueIsEqual(Object val1, Object val2, Class<?> fieldType) {
		boolean ret = true;
		if (null != val1 && null != val2){
			if (fieldType == BigDecimal.class)
				ret = ((BigDecimal)val1).compareTo(((BigDecimal)val2)) == 0;
			else {
				ret = val1.equals(val2);
			}
		}
	
		return ret;
	}
	
}
