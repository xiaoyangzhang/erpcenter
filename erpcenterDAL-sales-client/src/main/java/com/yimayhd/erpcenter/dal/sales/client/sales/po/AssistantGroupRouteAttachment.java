package com.yimayhd.erpcenter.dal.sales.client.sales.po;

import java.io.Serializable;

public class AssistantGroupRouteAttachment  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3479242392010135325L;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column assistant_group_route_attachment.id
	 * @mbggenerated
	 */
	private Integer id;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column assistant_group_route_attachment.route_id
	 * @mbggenerated
	 */
	private Integer routeId;
	private Integer groupId;
	private Integer orderId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column assistant_group_route_attachment.name
	 * @mbggenerated
	 */
	private String name;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column assistant_group_route_attachment.path
	 * @mbggenerated
	 */
	private String path;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column assistant_group_route_attachment.id
	 * @return  the value of assistant_group_route_attachment.id
	 * @mbggenerated
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column assistant_group_route_attachment.id
	 * @param id  the value for assistant_group_route_attachment.id
	 * @mbggenerated
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column assistant_group_route_attachment.route_id
	 * @return  the value of assistant_group_route_attachment.route_id
	 * @mbggenerated
	 */
	public Integer getRouteId() {
		return routeId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column assistant_group_route_attachment.route_id
	 * @param routeId  the value for assistant_group_route_attachment.route_id
	 * @mbggenerated
	 */
	public void setRouteId(Integer routeId) {
		this.routeId = routeId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column assistant_group_route_attachment.name
	 * @return  the value of assistant_group_route_attachment.name
	 * @mbggenerated
	 */
	public String getName() {
		return name;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column assistant_group_route_attachment.name
	 * @param name  the value for assistant_group_route_attachment.name
	 * @mbggenerated
	 */
	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column assistant_group_route_attachment.path
	 * @return  the value of assistant_group_route_attachment.path
	 * @mbggenerated
	 */
	public String getPath() {
		return path;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column assistant_group_route_attachment.path
	 * @param path  the value for assistant_group_route_attachment.path
	 * @mbggenerated
	 */
	public void setPath(String path) {
		this.path = path == null ? null : path.trim();
	}

	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
}