
-----------------------------------------------1201_v21----begin----------------------------------------
---------新增页面及导出Excel：产品利润统计-----------------------
INSERT INTO `yihg_erp_user`.`platform_menu` (`sys_id`, `parent_id`, `name`, `url`, `code`, `css`, `resource_type`, `comment`, `seq_num`, `status`, `del_status`, `create_time`, `update_time`) VALUES ('0', '365', '产品利润统计', '/taobao/ProductProfitStatistics.htm', 'TAOBAO_PPS', '', '1', '', '88', '1', '1', '2016-11-25 11:25:21', '2016-11-25 11:25:21');
-------【供应商管理】添加 组团社按权限使用功能-------------------
INSERT INTO `yihg_erp_user`.`sys_biz_config` (`id`, `biz_id`, `item_code`, `item_value`, `item_desp`) VALUES ('16', '1', 'SUPPLIER_CHOOSE', '1', '组团社按权限使用开关(1=开，0=关)');

CREATE TABLE `sys_data_right_supplier` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '组团社权限',
    `biz_id`  int(10) NOT NULL DEFAULT 1 COMMENT '商家id',
  `org_id` int(11) NOT NULL COMMENT 'platform_org.org_id',
  `supplier_id` int(11) NOT NULL COMMENT 'yihg_erp_supplier.supplier_info.id',
  PRIMARY KEY (`id`),
  KEY `idx_org_id` (`org_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='组团社权限过滤';
------------------------------------------------1201_v21----end------------------------------------------------------------------