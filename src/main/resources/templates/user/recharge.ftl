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
    <script src="/bootstrap/bootstrap.min.js"></script>
    <style>
        .qcondition{background-color: #fbfbfb;margin-bottom: 30px;border-bottom: 1px solid #f0f0f0;text-align: center;}
    </style>
</head>
<body>
<#include "../common/header.ftl" encoding="utf-8" parse=true>
<div class="main">
    <div class="container clearfix">
        <#include "common/leftnav.ftl" encoding="utf-8" parse=true>
        <div class="r-container fleft">
            <div class="rTitle">我要充值</div>
            <div class="rContent">
                <div class="">
                    <p>
                        <span>当前余额:</span>
                        <span class="left-money">${user.money!?string('0.00')}</span>
                    </p>
                    <p>
                        <span>充值金额:</span>
                        <input type="text" id="rechargeMoney"/>
                    </p>
                    <button type="button" class="btn btn-primary" id="recharge">充值</button>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="/js/common.js"></script>
<script type="text/javascript">
    $('#recharge').on('click',function () {
       let money = $('#rechargeMoney').val();
       if(money.length === 0) return;
       if(!(/^\d+$/g.test(money))){
           alert("充值金额格式错误");
           return;
       }
       $.ajax({
           url:'/payCenter/recharge',
           type:'post',
           data:{money:formatMoney($('#rechargeMoney').val())},
           dataType:'json',
           beforeSend:function () {

           },
           success:function (data) {
               if(data.result === 0){
                   alert(data.msg);
                   return;
               }
               alert(data.msg);
               window.location.reload();
           },
           error:function () {
               alert('网络出错');
           }
       })
    });
</script>
</body>
</html>