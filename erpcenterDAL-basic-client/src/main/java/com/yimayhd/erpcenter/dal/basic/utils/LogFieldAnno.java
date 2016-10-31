//package com.yimayhd.erpcenter.dal.basic.utils;
//
//import java.lang.annotation.ElementType;
//import java.lang.annotation.Retention;
//import java.lang.annotation.Target;
//import java.lang.annotation.RetentionPolicy;
//
//@Retention(RetentionPolicy.RUNTIME)
//@Target(ElementType.FIELD)
//public @interface LogFieldAnno {
//	/**
//	 * 是否是主键字段
//	 * @return
//	 */
//	public boolean isKey() default false;
//	
//	/**
//     * 字段中文名称
//     * @return
//     */
//	public String description() default "";
//	
//	/**
//	 * 删除或插入时，是否需要记录本字段信息 如：（删除客人名单操作！姓名=张三   操作人：admin 2016-09-18 hh:mm:ss）
//	 * @return
//	 */
//	public boolean delOrIns() default false;
//	
//	/**
//	 * 当中文字段描述不完整时，可以扩展备注，格式：[{key:xxx, value:yyy}]（如：key:0,代表value:男，1代表女）
//	 * @return
//	 */
//	public String needExtDescription() default "";
//}
