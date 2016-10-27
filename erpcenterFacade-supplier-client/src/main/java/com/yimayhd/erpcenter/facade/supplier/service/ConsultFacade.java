package com.yimayhd.erpcenter.facade.supplier.service;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.facade.supplier.query.GuestConsultDTO;
import com.yimayhd.erpcenter.facade.supplier.query.GuestConsultFollowDTO;
import com.yimayhd.erpcenter.facade.supplier.result.ConsultGuestListResult;
import com.yimayhd.erpcenter.facade.supplier.result.FollowConsultResult;
import com.yimayhd.erpcenter.facade.supplier.result.WebResult;
import com.yimayhd.erpresource.dal.po.GuestConsultFollow;


public interface ConsultFacade {
	/**
	 * 客户咨询登记 
	 * @author liyong
	 * 2016年10月25日 
	 * @param msglyxym 意向游玩编码
	 * @param msglxxqd 信息渠道编码
	 * @param bizId 商家id
	 * @return ConsultGuestListResult
	 */
	public ConsultGuestListResult consultGuestList(String msglyxym,String msglxxqd,Integer bizId);
	/**
	 * 客户登记列表
	 * @author liyong
	 * 2016年10月25日 
	 * @param pageBean
	 * @param bizId
	 * @return
	 */
	public WebResult<PageBean> getGuestConsultList(PageBean pageBean, Integer bizId);
	/**
	 * 
	 * @author liyong
	 * 2016年10月25日 
	 * @param msglkrly 客人来源
	 * @param msglyxyw 意向游玩
	 * @param msglxxqd 信息渠道
	 * @param bizId 
	 * @return
	 */
	public FollowConsultResult addConsult(String msglkrly,String msglyxyw,String msglxxqd,Integer bizId);
	
	public FollowConsultResult followConsult(String msglkrly,String msglyxyw,String msglxxqd,String msglgjfs,Integer guestId,Integer bizId);
	
	public FollowConsultResult viewConsult(String msglkrly,String msglyxyw,String msglxxqd,Integer guestId,Integer bizId);
	/**
	 * 验证咨询客户的唯一性
	 * @author liyong
	 * @param phone
	 * @return
	 */

	public String validatePhone(String phone);
	/**
	 * 保存咨询客户
	 * @author liyong
	 * @param guestConsult
	 * @return
	 */
	public WebResult<Integer> saveConsultGuest(GuestConsultDTO guestConsultDTO);
		
	/**
	 * 删除客户资料
	 * @author liyong
	 * 2016年10月25日 描述：
	 * @param guestId
	 * @return
	 */
	public Integer delConsultGuest(Integer guestId);
	/**
	 * 保存咨询跟进情况
	 * @param follow
	 * @return
	 */
	public Integer saveConsultFollow(Integer type,GuestConsultFollowDTO followDTO);
}
