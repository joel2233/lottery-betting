<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>我的账户</title>
    <link rel="stylesheet" href="/bootstrap/bootstrap.min.css">
    <link rel="stylesheet" href="/font-awesome-4.7.0/css/font-awesome.css"/>
    <link rel="stylesheet" href="/css/common.css"/>
    <script type="text/javascript" src="/js/jquery.min.js"></script>
    <script src="/bootstrap/bootstrap.min.js" type="text/javascript"></script>
    <style>
        .userInfo{padding-left: 100px;}
        .userInfo .info-item{height: 50px;line-height: 50px;}
        .userInfo .info-item .info-span{display:inline-block;width:100px;}
        .userInfo .info-item .info-con{display:inline-block;width: 250px;}
    </style>
</head>
<body>
<#include "../common/header.ftl" encoding="utf-8" parse=true>
<div class="main ">
    <div class="container clearfix">
        <#include "common/leftnav.ftl" encoding="utf-8" parse=true>
        <div class="r-container fleft">
            <div class="rTitle">个人信息</div>
            <div class="rContent">
                <nav class="userInfo">
                    <ul>
                        <li class="info-item">
                            <span class="info-span">用户名:</span>
                            <span class="info-con">${user.uname!}</span>
                        </li>
                        <li class="info-item">
                            <span class="info-span">手机号:</span>
                            <span class="info-con">${user.phone!}</span>
                        </li>
                        <li class="info-item">
                            <span class="info-span">注册时间:</span>
                            <span class="info-con">${user.registerTime!?split('T')[0]}</span>
                        </li>
                    </ul>
                </nav>
            </div>
        </div>
    </div>
</div>
</body>
</html>