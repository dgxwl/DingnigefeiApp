package com.demo.dingtalkapp.service.impl;

import org.springframework.stereotype.Service;

import com.demo.dingtalkapp.service.inter.IUserService;
import com.demo.dingtalkapp.utils.AccessTokenUtil;
import com.demo.dingtalkapp.utils.Env;
import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiUserGetRequest;
import com.dingtalk.api.response.OapiUserGetResponse;
import com.taobao.api.ApiException;

@Service
public class UserService implements IUserService {
	
	/**
     * 获取用户信息
     * @return
     */
    public String getUserInfo(String userId) {
    	try {
    		if (userId == null || userId.isEmpty()) {
    			return "未登录!";
    		}
            DingTalkClient client = new DefaultDingTalkClient(Env.URL_USER_GET);
            OapiUserGetRequest request = new OapiUserGetRequest();
            
            request.setUserid(userId);
            request.setHttpMethod("GET");
            String accessToken = AccessTokenUtil.getToken();
            OapiUserGetResponse response = client.execute(request, accessToken);
            
            return response.getMobile();
        } catch (ApiException e) {
            e.printStackTrace();
            return null;
        }
    }
	
	/**
     * 获取用户姓名
     *
     * @param accessToken
     * @param userId
     * @return
     */
    public String getUserName(String accessToken, String userId) {
        try {
            DingTalkClient client = new DefaultDingTalkClient(Env.URL_USER_GET);
            OapiUserGetRequest request = new OapiUserGetRequest();
            request.setUserid(userId);
            request.setHttpMethod("GET");
            OapiUserGetResponse response = client.execute(request, accessToken);
            return response.getName();
        } catch (ApiException e) {
            e.printStackTrace();
            return null;
        }
    }
}
