package com.yimayhd.erpcenter.dal.sales.client.sales.po;

public class AssistantSupplierImgType {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column assistant_supplier_img_type.id
	 * @mbggenerated
	 */
	private Integer id;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column assistant_supplier_img_type.imgtype_id
	 * @mbggenerated
	 */
	private Integer imgtypeId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column assistant_supplier_img_type.supplier_id
	 * @mbggenerated
	 */
	private Integer supplierId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column assistant_supplier_img_type.bussiness_type
	 * @mbggenerated
	 */
	private Integer bussinessType;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column assistant_supplier_img_type.type_code
	 * @mbggenerated
	 */
	private String typeCode;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column assistant_supplier_img_type.type_name
	 * @mbggenerated
	 */
	private String typeName;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column assistant_supplier_img_type.id
	 * @return  the value of assistant_supplier_img_type.id
	 * @mbggenerated
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column assistant_supplier_img_type.id
	 * @param id  the value for assistant_supplier_img_type.id
	 * @mbggenerated
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column assistant_supplier_img_type.imgtype_id
	 * @return  the value of assistant_supplier_img_type.imgtype_id
	 * @mbggenerated
	 */
	public Integer getImgtypeId() {
		return imgtypeId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column assistant_supplier_img_type.imgtype_id
	 * @param imgtypeId  the value for assistant_supplier_img_type.imgtype_id
	 * @mbggenerated
	 */
	public void setImgtypeId(Integer imgtypeId) {
		this.imgtypeId = imgtypeId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column assistant_supplier_img_type.supplier_id
	 * @return  the value of assistant_supplier_img_type.supplier_id
	 * @mbggenerated
	 */
	public Integer getSupplierId() {
		return supplierId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column assistant_supplier_img_type.supplier_id
	 * @param supplierId  the value for assistant_supplier_img_type.supplier_id
	 * @mbggenerated
	 */
	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column assistant_supplier_img_type.bussiness_type
	 * @return  the value of assistant_supplier_img_type.bussiness_type
	 * @mbggenerated
	 */
	public Integer getBussinessType() {
		return bussinessType;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column assistant_supplier_img_type.bussiness_type
	 * @param bussinessType  the value for assistant_supplier_img_type.bussiness_type
	 * @mbggenerated
	 */
	public void setBussinessType(Integer bussinessType) {
		this.bussinessType = bussinessType;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column assistant_supplier_img_type.type_code
	 * @return  the value of assistant_supplier_img_type.type_code
	 * @mbggenerated
	 */
	public String getTypeCode() {
		return typeCode;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column assistant_supplier_img_type.type_code
	 * @param typeCode  the value for assistant_supplier_img_type.type_code
	 * @mbggenerated
	 */
	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode == null ? null : typeCode.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column assistant_supplier_img_type.type_name
	 * @return  the value of assistant_supplier_img_type.type_name
	 * @mbggenerated
	 */
	public String getTypeName() {
		return typeName;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column assistant_supplier_img_type.type_name
	 * @param typeName  the value for assistant_supplier_img_type.type_name
	 * @mbggenerated
	 */
	public void setTypeName(String typeName) {
		this.typeName = typeName == null ? null : typeName.trim();
	}
}