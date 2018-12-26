package com.demo.dingtalkapp.service.inter;

public interface IUserService {
	String getUserInfo(String userId);
	String getUserName(String accessToken, String userId);
}
