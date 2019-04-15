<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>Error</title>
    <link rel="stylesheet" href="/font-awesome-4.7.0/css/font-awesome.css"/>
    <link rel="stylesheet" href="/css/common.css"/>
    <script type="text/javascript" src="../js/jquery.min.js"></script>
    <style>
        p{font-size:20px;}
        #error{text-decoration: underline;}
    </style>
</head>
<body>
<#include "common/header.ftl" encoding="utf-8" parse=true>
   <div class="main" style="margin-top: 40px;font-weight: bold;">
       <#if errmsg??>
           <p>${errmsg!}</p>
       <#else>
           <p>系统出错</p>
       </#if>

       <p><a id="error" href="/" style="color:red;">点击此处</a>返回网站首页<br/></p>
   </div>
</body>
</html>