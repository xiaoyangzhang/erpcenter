package com.yimayhd.erpcenter.common.po;
import java.io.Serializable;
public class LogFieldAnnoInfo implements Serializable {
	
	/**
	 * 系统日志处理类（对应com.yihg.basic.util -> LogFieldAnno
	 */
	private static final long serialVersionUID = 5229752818905387605L;
	private String fieldDescription;
	private String fieldName;
	private String fieldType;
	private String fieldAction;
	private String valueEdit;
	private String valueOrigin;

	
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	public String getFieldType() {
		return fieldType;
	}
	public void setFieldType(String fieldType) {
		this.fieldType = fieldType;
	}
	public String getValueEdit() {
		return valueEdit;
	}
	public void setValueEdit(String valueEdit) {
		this.valueEdit = valueEdit;
	}
	public String getValueOrigin() {
		return valueOrigin;
	}
	public void setValueOrigin(String valueOrigin) {
		this.valueOrigin = valueOrigin;
	}

	public String getFieldDescription() {
		return fieldDescription;
	}
	public void setFieldDescription(String fieldDescription) {
		this.fieldDescription = fieldDescription;
	}
	public String getFieldAction() {
		return fieldAction;
	}
	public void setFieldAction(String fieldAction) {
		this.fieldAction = fieldAction;
	}
}
