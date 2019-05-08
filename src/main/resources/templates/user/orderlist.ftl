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
            <div class="rTitle">投注单记录</div>
            <div class="rContent">
                <div class="qcondition">
                    <form class="qform" action="/orderCenter/doQuery" method="post">
                        <label for="qName">彩种:</label>
                        <select id="qName">
                            <option value="1">福彩</option>
                            <option value="2">体彩</option>
                        </select>

                        <div class="btn-group qtime" role="group" aria-label="Basic example">
                            <span>时间:</span>
                            <button type="button" class="btn btn-secondary">今天</button>
                            <button type="button" class="btn btn-light">昨天</button>
                            <button type="button" class="btn btn-light">七天</button>
                        </div>

                        <div class="btn-group qtype" role="group" aria-label="Basic example">
                            <span>类型:</span>
                            <button type="button" class="btn btn-secondary">全部</button>
                            <button type="button" class="btn btn-light">未支付</button>
                            <button type="button" class="btn btn-light">已支付</button>
                        </div>

                        <div class="btn-group qstate" role="group" aria-label="Basic example">
                            <span>状态:</span>
                            <button type="button" class="btn btn-secondary">全部</button>
                            <button type="button" class="btn btn-light">未过期</button>
                            <button type="button" class="btn btn-light">已过期</button>
                        </div>
                    </form>
                </div>
                <div class="qresult">
                    <table class="resultTable table">
                        <thead>
                            <tr>
                                <td>订单号</td>
                                <td>彩票类型</td>
                                <td>彩种</td>
                                <td>期号</td>
                                <td>状态</td>
                                <td>注数</td>
                                <td>投注金额</td>
                                <td>支付状态</td>
                                <td>创建时间</td>
                                <td>过期时间</td>
                                <td>操作</td>
                            </tr>
                        </thead>
                        <tbody>
                            <#list orderlist! as order>
                            <tr class="order-item">
                                <td class="orderId">${order.orderId!}</td>
                                <td><#if order.lotteryType! = 1>福彩<#else>体彩</#if></td>
                                <td>${order.lotteryName!}</td>
                                <td>${order.code!}</td>
                                <td><#if order.state = 1>未过期<#else>已过期</#if></td>
                                <td>${order.note!}</td>
                                <td>${order.total!?string('0.00')}</td>
                                <td><#if order.payState! = 1>已支付<#else>未支付</#if></td>
                                <#--<#setting datetime_format="yyyy-MM-dd HH:mm:ss">-->
                                <td>${formatTime(order.createTime,'MM-dd HH:mm')}</td>
                                <td>${formatTime(order.expireTime,'MM-dd HH:mm')}</td>
                                <td><#if order.state = 1 && order.payState = 0><a class="payBtn" href="#">支付</a></#if> <a href="#">删除</a></td>
                            </tr>
                            </#list>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
    $('.payBtn').on('click',function () {
        let orderId = $(this).closest('.order-item').find('.orderId').html();
        if(confirm('确认?')){
            $.ajax({
                url:'/payCenter/pay',
                type:'post',
                data:{orderId:orderId},
                dataType:'json',
                success:function (data) {
                    if(data.result === 0){
                        alert(data.msg);
                        return;
                    }
                    alert(data.msg);
                    window.location.reload();

                },
                error:function (error) {
                    alert('网络错误');
                }
            })
        }
    });
</script>
</body>
</html>