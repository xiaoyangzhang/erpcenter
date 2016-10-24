package com.yimayhd.erpcenter.dal.sales.client.sales.po;

import java.io.Serializable;

public class AutocompleteInfo implements Serializable {

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 1L;
	
	private String Id ;
	
	private String Text ;
	
	private String Tag ;
	
	private String prefixText;
	private Integer count;
	private String contextKey ;

	public String getPrefixText() {
		return prefixText;
	}

	public void setPrefixText(String prefixText) {
		this.prefixText = prefixText;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public String getContextKey() {
		return contextKey;
	}

	public void setContextKey(String contextKey) {
		this.contextKey = contextKey;
	}

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public String getText() {
		return Text;
	}

	public void setText(String text) {
		Text = text;
	}

	public String getTag() {
		return Tag;
	}

	public void setTag(String tag) {
		Tag = tag;
	}

}
