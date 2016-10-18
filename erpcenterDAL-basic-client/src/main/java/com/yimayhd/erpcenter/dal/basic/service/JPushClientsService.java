package com.yimayhd.erpcenter.dal.basic.service;

import java.util.Map;

/**
 * Created by zzn on 2015/7/22.
 * 用于推送消息的工具类
 */

public interface JPushClientsService {

    /**
     * push发送
     * @param registrationId 极光注册ID
     * @param phoneType 设备类型（android、ios）
     * @param content 发送内容
     * @param extraMap 其他字段【附加参数】
     */
    public void sendNotificationPushWithRegistrationId(Integer userTyp,String registrationId,String phoneType,String content,Map<String,String> extraMap);


    /**
     *  通过tag标签发送Notification通知
     *  @param tag 标签
     *  @param phoneType 设备类型（android、ios）
     *  @param content 发送内容
     *  @param extraMap 其他字段【附加参数】
     */
    public void sendPush_ios_android_tag_notification(Integer userTyp,String tag,String content,Map<String,String> extraMap);


    /**
     * 【广播通知】所有平台，所有设备，内容为 ALERT 的通知
     */
    public void sendPushMessageForAll(String content);

}
