<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>${title}</title>
    <#--<meta name="keywords" content="$!{WEBkeyword}"/>-->
    <#--<meta name="description" content="$!{WEBdescription}"/>-->
    <#--<link rel="shortcut icon" href="/favicon.ico">-->
    <#--<link rel="bookmark" href="/favicon.ico" />-->
    <#--<link rel="canonical" href="https://$!{defaultDomain}/" />-->
    <link rel="stylesheet" href="/font-awesome-4.7.0/css/font-awesome.css"/>
    <script type="text/javascript" src="../js/jquery.min.js"></script>
    <style type="text/css">
        *{padding: 0;margin: 0;height: 100%;font-size: 14px;font-family: Consolas;}
        a{text-decoration: none;color:#444;}
        .clearfix:after{content: "";display: table;clear:both;}
        li{list-style: none;display: inline-block;}
        .fleft{float: left;}
        .fright{float: right;}
        /*div{border-bottom: 1px solid;}*/
        .header{height: 90px;width: 100%;background-color: #f2f2f9;box-shadow: 0 10px 20px 0 #f1f1f1;}
        .header-left{width:250px;margin-left: 30px;}
        .logo{display: inline-block;width:90%;height:100%;background: url("../imgs/logo.png") no-repeat 40% 80%;background-size: 90%;}
        .header-nav{background-color: ;width:60%;margin: 52px auto 0;height: 30px;}
        .header-nav li a{display: inline-block;margin-left:3px;width:80px;text-align: center;height: 30px;line-height: 30px;}
        .header-nav li:hover{background-color: #99999966;}
        .header-right{line-height: 30px;margin:52px 90px 0 0;height: 30px;}
        .header-right li a{display: inline-block;width:80px;text-align: center;}
        .header-right li a:hover{color:orange;}
        .hide{display: none;}

        .main{max-height: 70%;}

        .footer{height: 200px;position: fixed;margin-bottom: 0;}
    </style>
</head>
<body>
    <#include "common/header.ftl" encoding="utf-8" parse=true>
    <div class="main"></div>
    <div class="footer"></div>
</body>
</html>