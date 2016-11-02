package com.yimayhd.erpcenter.facade.sales.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONArray;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.GroupOrderBiz;
import com.yimayhd.erpcenter.dal.sales.client.sales.constants.Constants;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.AiYouBean;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrder;
import com.yimayhd.erpcenter.facade.sales.errorcode.OperationErrorCode;
import com.yimayhd.erpcenter.facade.sales.result.ListResultSupport;
import com.yimayhd.erpcenter.facade.sales.service.GroupOrderFacade;
import com.yimayhd.erpcenter.facade.sales.service.GroupOrderFacade_aiyou;


/**
 * GroupOrderFacadeImpl
 *
 * @author lilin
 * @date 16/10/25
 */
public class GroupOrderFacadeImpl_aiyou implements GroupOrderFacade_aiyou {
   
	private static final Logger LOGGER = LoggerFactory.getLogger(GroupOrderFacadeImpl_aiyou.class);
   
    @Autowired
    private GroupOrderBiz groupOrderBiz;
    
    @Override
    public ListResultSupport<AiYouBean> getAiYourOrders(String code, String port, String startDate, String endDate, String groupNum, Integer bizId) {

        ListResultSupport<AiYouBean> result = new ListResultSupport<AiYouBean>();

        // 访问爱游系统 (yihg-aiyou-api[数据查询系统])
        CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
        // 访问地址
        HttpPost httpPost = new HttpPost(Constants.AIYOU_API_URL + "/opBill/orderList.do");
        // 访问参数
        List<NameValuePair> nameValuePairList = new
                ArrayList<NameValuePair>();
        nameValuePairList.add(new BasicNameValuePair("dbType", port));
        nameValuePairList.add(new BasicNameValuePair("code", code));
        nameValuePairList.add(new BasicNameValuePair("startDate",
                startDate));
        nameValuePairList.add(new BasicNameValuePair("endDate", endDate));
        nameValuePairList.add(new BasicNameValuePair("groupNum", groupNum));
        List<AiYouBean> aiyouOrderList = new ArrayList<AiYouBean>();
        try {

            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairList));

            CloseableHttpResponse closeableHttpResponse;
            closeableHttpResponse = closeableHttpClient.execute(httpPost);

            String orderString = "";
            try {
                HttpEntity httpEntity = closeableHttpResponse.getEntity();
                orderString = EntityUtils.toString(httpEntity);
            } catch (IOException e) {
                LOGGER.error("", e);
                result.setErrorCode(OperationErrorCode.QUERY_ERROR);
                return result;
            } finally {
                closeableHttpResponse.close();
            }

            List<AiYouBean> aiYouBeans = JSONArray.parseArray(orderString, AiYouBean.class);

            if (aiYouBeans != null && aiYouBeans.size() > 0) {
                List<GroupOrder> orders = groupOrderBiz.selectAiYouOrders(bizId, startDate,
                        endDate);
                for (AiYouBean aiYouBean : aiYouBeans) {
                    aiYouBean.setSupplierCode(port);
                    String yyyy = aiYouBean.getGroup_num().substring(0, 4);
                    String MM = aiYouBean.getGroup_num().substring(4, 6);
                    String dd = aiYouBean.getGroup_num().substring(6, 8);
                    aiYouBean.setDate(yyyy + "-" + MM + "-" + dd);
                    if (orders != null && orders.size() > 0) {
                        for (GroupOrder groupOrder : orders) {
                            if (aiYouBean.getGroup_id().equals(groupOrder.getAiyouGroupId())) {
                                aiYouBean.setIsImport(1);
                                break;
                            }
                        }
                    }
                }
                aiyouOrderList.addAll(aiYouBeans);
            }

            httpPost.abort();
        } catch (Exception ex) {
            LOGGER.error("",ex);
            result.setErrorCode(OperationErrorCode.QUERY_ERROR);
            return result;
        }
        result.setValues(aiyouOrderList);
        return result;
    }
}
