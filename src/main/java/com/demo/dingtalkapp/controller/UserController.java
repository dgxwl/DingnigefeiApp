package com.demo.dingtalkapp.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.demo.dingtalkapp.service.inter.ICropService;
import com.demo.dingtalkapp.service.inter.IUserService;
import com.demo.dingtalkapp.utils.AccessTokenUtil;
import com.demo.dingtalkapp.utils.Env;
import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiUserGetuserinfoRequest;
import com.dingtalk.api.response.OapiUserGetuserinfoResponse;
import com.taobao.api.ApiException;

@Controller
@RequestMapping("/dingtalk")
public class UserController extends BaseController {
	
	@Resource
	private ICropService cropService;
	@Resource
	private IUserService userService;
	
	@RequestMapping("/test")
	public String test(String com, ModelMap modelMap) {
		String cropId = cropService.getCropIdByCropCode(com);
		modelMap.addAttribute("cropId", cropId);
		return "test";
	}
	
	/**
     * 钉钉用户登录
     *
     * @param requestAuthCode 免登临时code
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> login(@RequestParam(value = "authCode") String requestAuthCode, HttpSession session) {
        //获取accessToken,注意正是代码要有异常流处理
        String accessToken = AccessTokenUtil.getToken();

        //获取用户信息
        DingTalkClient client = new DefaultDingTalkClient(Env.URL_GET_USER_INFO);
        OapiUserGetuserinfoRequest request = new OapiUserGetuserinfoRequest();
        request.setCode(requestAuthCode);
        request.setHttpMethod("GET");

        OapiUserGetuserinfoResponse response;
        try {
            response = client.execute(request, accessToken);
        } catch (ApiException e) {
            e.printStackTrace();
            return null;
        }
        //3.查询得到当前用户的userId
        String userId = response.getUserid();
        System.out.println(userId);
        // 获得到userId之后应用应该处理应用自身的登录会话管理（session）,避免后续的业务交互（前端到应用服务端）每次都要重新获取用户身份，提升用户体验
        session.setAttribute(BaseController.CURRENT_USERID, userId);

        String userName = userService.getUserName(accessToken, userId);
        System.out.println(userName);
        
        String userMobile = userService.getUserInfo(userId);
        System.out.println(userMobile);
        
        //返回结果
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("userId", userId);
        resultMap.put("userName", userName);
        resultMap.put("userMobile", userMobile);
        return resultMap;
    }
    
    

    
}
