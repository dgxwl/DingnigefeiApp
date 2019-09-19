<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv=Content-Type content="text/html;charset=utf-8">
<meta charset="gbk">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta content="yes" name="apple-mobile-web-app-capable"/>
<meta content="black" name="apple-mobile-web-app-status-bar-style">
<meta content="telephone=no" name="format-detection"/>
<meta content="yes" name="apple-touch-fullscreen"/>
<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,minimum-scale=1.0,user-scalable=no" />
<!-- <link type="text/css" rel="stylesheet" href="stylesheets/style.css" /> -->
    
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">

<title>首页 - 个人信息</title>

<!-- <script type="text/javascript" src="javascripts/zepto.min.js"></script> -->
<script type="text/javascript" src="http://g.alicdn.com/dingding/open-develop/1.6.9/dingtalk.js"></script>
<!-- <script type="text/javascript" src="javascripts/logger.js"> -->
<!-- </script> -->
<!-- 免登相关代码 -->
<!-- <script type="text/javascript" src="javascripts/demo.js"> -->

<!-- </script> -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.3.1.min.js"></script>

</head>

<body >
<h3>测试页面</h3>
<p>当前用户信息:</p>
<p id="p1"></p>
<p id="p2"></p>
</body>
<script type="text/javascript">

//在此拿到jsAPI权限验证配置所需要的信息
$(function() {
	
	//获取免登授权码
	dd.ready(function() {
        dd.runtime.permission.requestAuthCode({
            corpId : '${cropId}',
            onSuccess : function(result) {
                var code = result.code;
                
　　　　　　　　	$.ajax({
　　　　　　　　		url: "/dingtalk/login",
        			data: "authCode=" + code,
        			type: "POST",
        			success: function(obj) {
						$("#p1").html(obj.result.userName);
						$("#p2").html(obj.result.userMobile);
					}
　　　　　　　　	});
            },
            onFail : function(err) {
                alert('出错了, ' + err);
            }
        });

    });
	
})

</script>
</html>
