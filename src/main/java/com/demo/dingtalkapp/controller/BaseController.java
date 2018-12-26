package com.demo.dingtalkapp.controller;

import javax.servlet.http.HttpSession;

public class BaseController {
	
	public static final String CURRENT_USERID = "current_userid";

	public String getCurrentUserId(HttpSession session) {
		return (String) session.getAttribute(CURRENT_USERID);
	}
}
