package com.yimayhd.erpcenter.dal.product.po;

import java.io.Serializable;

public class ProductAttachment implements Serializable {
    private static final long serialVersionUID = 1936079248368038482L;
    private Integer id;

    private Byte type;
    private Byte objType;
    private Integer objId;

    private String name;

    private String path;

    private Long createTime;

    
    public Byte getObjType() {
		return objType;
	}

	public void setObjType(Byte objType) {
		this.objType = objType;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public Integer getObjId() {
        return objId;
    }

    public void setObjId(Integer objId) {
        this.objId = objId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path == null ? null : path.trim();
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }
}