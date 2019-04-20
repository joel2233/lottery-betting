<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>会员登录-万艾</title>
    <link rel="stylesheet" href="/font-awesome-4.7.0/css/font-awesome.css"/>
    <link rel="stylesheet" href="/bootstrap/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="/css/common.css">
    <script src="/js/jquery.min.js"></script>
    <style type="text/css">
        .form .fa{font-size: 20px!important;padding-right: 1em;display: inline-block;width:10px;height: 10px;}
    </style>
</head>
<body>
<#include "../common/header.ftl" encoding="utf-8" parse=true>
<div class="content">
    <#--<div>-->

    <#--</div>-->
    <div class="form fright">
        <p style="font-size: 18px;font-weight: bold;text-align: center;">会员登录</p>
        <p style="margin-left: 25px;color: greenyellow;">${errmsg!}</p>
        <form id="loginform" method="post" action="/user/doLogin">
            <div class="form-item">
                <i class="fa fa-user"></i>
                <input type="text" name="username" placeholder="用户名/手机号"/>
            </div>
            <div class="form-item">
                <i class="fa fa-key"></i>
                <input type="password" name="password"/>
            </div>
            <p class="fleft" style="height: 20px;line-height: 20px;">
                <label for="rememberMe">
                    <input type="checkbox" id="rememberMe" name="rememberMe" value="1" style="width:15px;height: 15px;"/>
                    <span>记住我</span>
                </label>
            </p>
            <p style="text-align: right;"><a href="#" id="forgot">忘记密码?</a></p>
            <div class="form-item">
                <button class="fluid ui button orange">登 录</button>
            </div>
            <p style="text-align: right;margin-top: 10px;"><a href="/user/register/toRegister">免费注册</a></p>
        </form>
    </div>
</div>
<#--<#include "../common/footer.ftl" encoding="utf-8" parse=true>-->
</body>
</html>