package com.yimayhd.erpcenter.biz.basic.client.jpush;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import com.yimayhd.erpcenter.biz.basic.basic.contants.BasicConstants;
import com.yimayhd.erpcenter.biz.basic.jpush.JPushClientsService;

import cn.jpush.api.JPushClient;
import cn.jpush.api.common.resp.APIConnectionException;
import cn.jpush.api.common.resp.APIRequestException;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;

/**
 * Created by zzn on 2015/7/22.
 * 用于推送消息的工具类
 *
 *
 * platform	必填	推送平台设置
 * audience	必填	推送设备指定
 * notification	可选	通知内容体。是被推送到客户端的内容。与 message 一起二者必须有其一，可以二者并存
 * message	可选	消息内容体。是被推送到客户端的内容。与 notification 一起二者必须有其一，可以二者并存。或者称作：自定义消息，透传消息
 * options	可选	推送参数
 */

public class JPushClientsServiceImpl implements JPushClientsService {
    protected static final Logger LOG = Logger.getLogger(JPushClientsServiceImpl.class);

    /**
     * push推送（点对点推送Notification消息）
     * @param registrationId 极光注册ID
     * @param phoneType 设备类型（android、ios）
     * @param content 发送内容
     * @param extraMap 其他字段【附加参数】
     */
    public void sendNotificationPushWithRegistrationId(Integer userType, String registrationId,String phoneType,String content,Map<String,String> extraMap) {
        if(phoneType.equals("iphone")){
            sendPush_ios_registrationId_notification(userType, registrationId, content, extraMap);
        }else if (phoneType.equals("android")){
            sendPush_android_registrationId_notification(userType, registrationId, content,extraMap);
        }
    }




    /**
     *  给Android平台发送Notification通知消息
     *  方式为点对点：registrationId
     */
    private void sendPush_android_registrationId_notification(Integer userType, String registrationId,String content,Map<String,String> extraMap) {
        String masterSecret = "";
        String appKey = "";
        if (userType==1) {//游客
            masterSecret = BasicConstants.masterSecret;
            appKey = BasicConstants.appKey;
        }else if(userType==2){//导游
            masterSecret = BasicConstants.masterSecret2;
            appKey = BasicConstants.appKey2;
        }
        JPushClient jpushClient = new JPushClient(masterSecret, appKey);

        PushPayload payload = buildPushObject_android_registrationId_alert(registrationId, content, extraMap);
        try {
            PushResult result = jpushClient.sendPush(payload);
            LOG.debug("Got result - " + result);

        } catch (APIConnectionException e) {
            LOG.error("Connection error, should retry later", e);

        } catch (APIRequestException e) {
            LOG.error("Should review the error, and fix the request", e);
            LOG.info("HTTP Status: " + e.getStatus());
            LOG.info("Error Code: " + e.getErrorCode());
            LOG.info("Error Message: " + e.getErrorMessage());
        }
    }
    private PushPayload buildPushObject_android_registrationId_alert(String registrationId, String ALERT,Map<String,String> extraMap) {
        Map<String,String> extras = new HashMap<String,String>();
        if (extraMap!=null){
            if(extraMap.size()>0){
                Set<Map.Entry<String, String>> sets = extraMap.entrySet();
                for (Map.Entry<String, String> entry : sets) {
                    extras.put(entry.getKey(), entry.getValue());
                }
            }
        }

        return PushPayload.newBuilder()
                .setOptions(Options.newBuilder()
                        .setApnsProduction(true)
                        .build())
                .setAudience(Audience.registrationId(registrationId))
                .setPlatform(Platform.android())
                .setNotification(Notification.android(ALERT, "", extras)
                ).build();
    }




    /**
     *  给IOS平台发送Notification通知消息
     *  方式为点对点：registrationId
     */
    private void sendPush_ios_registrationId_notification(Integer userType, String registrationId,String content,Map<String,String> extraMap) {
        String masterSecret = "";
        String appKey = "";
        if (userType==1) {//游客
            masterSecret = BasicConstants.masterSecret;
            appKey = BasicConstants.appKey;
        }else if(userType==2){//导游
            masterSecret = BasicConstants.masterSecret2;
            appKey = BasicConstants.appKey2;
        }
        JPushClient jpushClient = new JPushClient(masterSecret, appKey);

        PushPayload payload = buildPushObject_ios_registrationId_notification(registrationId, content, extraMap);
        try {
            PushResult result = jpushClient.sendPush(payload);
            LOG.debug("Got result - " + result);
        } catch (APIConnectionException e) {
            LOG.error("Connection error, should retry later", e);

        } catch (APIRequestException e) {
            LOG.error("Should review the error, and fix the request", e);
            LOG.info("HTTP Status: " + e.getStatus());
            LOG.info("Error Code: " + e.getErrorCode());
            LOG.info("Error Message: " + e.getErrorMessage());
        }
    }
    private PushPayload buildPushObject_ios_registrationId_notification(String registrationId, String content,Map<String,String> extraMap) {
        Map<String,String> extras = new HashMap<String,String>();
        if (extraMap!=null){
            if(extraMap.size()>0){
                Set<Map.Entry<String, String>> sets = extraMap.entrySet();
                for (Map.Entry<String, String> entry : sets) {
                    extras.put(entry.getKey(), entry.getValue());
                }
            }
        }
        return PushPayload.newBuilder()
                .setPlatform(Platform.ios())
                .setAudience(Audience.registrationId(registrationId))
                .setNotification(Notification.newBuilder()
                        .addPlatformNotification(IosNotification.newBuilder()
                                .setAlert(content)
                                .setBadge(1)
                                .setSound("default")
                                .addExtras(extras)
                                .build())
                        .build())
                .setOptions(Options.newBuilder()
                        .setApnsProduction(true)
                        .build())
                .build();
    }







    /**
     *  通过tag标签发送Notification通知
     */
    @Override
    public void sendPush_ios_android_tag_notification(Integer userType,String tag,String content,Map<String,String> extraMap) {
        String masterSecret = "";
        String appKey = "";
        if (userType==1) {//游客
            masterSecret = BasicConstants.masterSecret;
            appKey = BasicConstants.appKey;
        }else if(userType==2){//导游
            masterSecret = BasicConstants.masterSecret2;
            appKey = BasicConstants.appKey2;
        }
        JPushClient jpushClient = new JPushClient(masterSecret, appKey);

        PushPayload payload_ios = buildPushObject_ios_tag_notification(tag, content, extraMap);
        PushPayload payload_android = buildPushObject_android_tag_notification(tag, content, extraMap);
        try {
            PushResult result_ios = jpushClient.sendPush(payload_ios);
            LOG.debug("Got result_ios - " + result_ios);
        } catch (APIConnectionException e) {
            LOG.error("Connection error, should retry later", e);
        } catch (APIRequestException e) {
            LOG.error("Should review the error, and fix the request", e);
            LOG.info("HTTP Status: " + e.getStatus());
            LOG.info("Error Code: " + e.getErrorCode());
            LOG.info("Error Message: " + e.getErrorMessage());
        }

        try {
            PushResult result_android = jpushClient.sendPush(payload_android);
            LOG.debug("Got result_android - " + result_android);
        } catch (APIConnectionException e) {
            LOG.error("Connection error, should retry later", e);
        } catch (APIRequestException e) {
            LOG.error("Should review the error, and fix the request", e);
            LOG.info("HTTP Status: " + e.getStatus());
            LOG.info("Error Code: " + e.getErrorCode());
            LOG.info("Error Message: " + e.getErrorMessage());
        }
    }
    private PushPayload buildPushObject_ios_tag_notification(String tag, String content, Map<String,String> extraMap) {
        Map<String,String> extras = new HashMap<String,String>();
        if (extraMap!=null){
            if(extraMap.size()>0){
                Set<Map.Entry<String, String>> sets = extraMap.entrySet();
                for (Map.Entry<String, String> entry : sets) {
                    extras.put(entry.getKey(), entry.getValue());
                }
            }
        }
        return PushPayload.newBuilder()
                .setPlatform(Platform.ios())
                .setOptions(Options.newBuilder()
                        .setApnsProduction(true)
                        .build())
                .setAudience(Audience.tag_and(tag))
                .setNotification(Notification.newBuilder()
                        .addPlatformNotification(IosNotification.newBuilder()
                                .setAlert(content)
                                .setSound("default")
                                .setBadge(1)
                                .addExtra("from", "JPush")
                                .build())
                        .build())
                .build();
    }

    private PushPayload buildPushObject_android_tag_notification(String tag, String content, Map<String,String> extraMap) {
        Map<String,String> extras = new HashMap<String,String>();
        if (extraMap!=null){
            if(extraMap.size()>0){
                Set<Map.Entry<String, String>> sets = extraMap.entrySet();
                for (Map.Entry<String, String> entry : sets) {
                    extras.put(entry.getKey(), entry.getValue());
                }
            }
        }
        return PushPayload.newBuilder()
                .setPlatform(Platform.android())
                .setOptions(Options.newBuilder()
                        .setApnsProduction(true)
                        .build())
                .setAudience(Audience.tag_and(tag))
                .setNotification(Notification.android(content, "您有新的通知！", null))
                .build();
    }








    /**
     *  给所有平台发送message自定义消息穿透消息.
     *  方式为点对点：registrationId
     */
    private void sendPushMessageForAliasWithMessage(String registrationId,String content,Map<String,String> extraMap) {
        JPushClient jpushClient = new JPushClient(BasicConstants.masterSecret,BasicConstants.appKey);
        PushPayload payload = buildPushObject_all_registrationId_message(registrationId, content,extraMap);
        try {
            PushResult result = jpushClient.sendPush(payload);
            LOG.debug("Got result - " + result);
        } catch (APIConnectionException e) {
            LOG.error("Connection error, should retry later", e);
        } catch (APIRequestException e) {
            LOG.error("Should review the error, and fix the request", e);
            LOG.info("HTTP Status: " + e.getStatus());
            LOG.info("Error Code: " + e.getErrorCode());
            LOG.info("Error Message: " + e.getErrorMessage());
        }
    }
    private PushPayload buildPushObject_all_registrationId_message(String registrationId, String content, Map<String,String> extraMap) {
        Message.Builder message = Message.newBuilder();
        message.setMsgContent(content);
        if (extraMap!=null){
            if(extraMap.size()>0){
                Set<Map.Entry<String, String>> sets = extraMap.entrySet();
                for (Map.Entry<String, String> entry : sets) {
                    message.addExtra(entry.getKey(), entry.getValue());
                }
            }
        }
        return PushPayload.newBuilder()
                .setPlatform(Platform.all())
                .setAudience(Audience.registrationId(registrationId))
                .setMessage(message.build())
                .setOptions(Options.newBuilder()
                        .setApnsProduction(true)
                        .build())
                .build();
    }








    /**
     * 【广播通知】所有平台，所有设备，内容为 ALERT 的通知
     */
    public void sendPushMessageForAll(String content) {
        JPushClient jpushClient = new JPushClient(BasicConstants.masterSecret,BasicConstants.appKey);
        PushPayload payload = buildPushObject_all_all_alert(content);
        try {
            PushResult result = jpushClient.sendPush(payload);
            LOG.debug("Got result - " + result);

        } catch (APIConnectionException e) {
            LOG.error("Connection error, should retry later", e);

        } catch (APIRequestException e) {
            LOG.error("Should review the error, and fix the request", e);
            LOG.info("HTTP Status: " + e.getStatus());
            LOG.info("Error Code: " + e.getErrorCode());
            LOG.info("Error Message: " + e.getErrorMessage());
        }
    }
    private PushPayload buildPushObject_all_all_alert(String ALERT) {
        return PushPayload.alertAll(ALERT);
    }



}
