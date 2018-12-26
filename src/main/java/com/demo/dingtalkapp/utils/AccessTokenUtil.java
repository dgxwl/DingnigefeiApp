package com.demo.dingtalkapp.utils;

import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.request.OapiGettokenRequest;
import com.dingtalk.api.response.OapiGettokenResponse;
import com.taobao.api.ApiException;

/**
 * 获取access_token工具类
 */
public class AccessTokenUtil {

    public static String getToken() {
        try {
            DefaultDingTalkClient client = new DefaultDingTalkClient(Env.URL_GET_TOKKEN);
            OapiGettokenRequest request = new OapiGettokenRequest();

            request.setAppkey(Env.APP_KEY);
            request.setAppsecret(Env.APP_SECRET);
            request.setHttpMethod("GET");
            OapiGettokenResponse response = client.execute(request);
            String accessToken = response.getAccessToken();
            return accessToken;
        } catch (ApiException e) {
            throw new RuntimeException(e);
        }

    }

    public static void main(String[] args)throws ApiException{
        String accessToken = AccessTokenUtil.getToken();
        System.out.println(accessToken);
    }
}
