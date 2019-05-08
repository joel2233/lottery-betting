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
    <script type="text/javascript" src="/bootstrap/bootstrap.min.js"></script>
    <style>
        .left-nav{width: 10%;}
        .betNum{    max-width: 200px;
            display: inline-block;
            white-space: nowrap;
            text-overflow: ellipsis;
            overflow: hidden;}
    </style>
</head>
<body>
<#include "../common/header.ftl" encoding="utf-8" parse=true>
<div class="main">
    <div class="container clearfix">
        <#include "common/leftnav.ftl" encoding="utf-8" parse=true>
        <div class="r-container fleft">
            <div class="rTitle">投注记录</div>
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
                    </form>
                </div>
                <div class="qresult">
                    <table class="resultTable table">
                        <thead>
                        <tr>
                            <td>彩票类型</td>
                            <td>彩种</td>
                            <td>期号</td>
                            <td>投注号码</td>
                            <td>注数</td>
                            <td>投注金额</td>
                            <td>开奖号码</td>
                            <td>奖金状态</td>
                            <td>支付时间</td>
                            <td>操作</td>
                        </tr>
                        </thead>
                        <tbody>
                        <#list orderlist! as order>
                            <tr class="order-item">
                                <td><#if order.lotteryType! = 1>福彩<#else>体彩</#if></td>
                                <td>${order.lotteryName!}</td>
                                <td>${order.code!}</td>
                                <td>
                                    <#assign detail = order.detail?eval/>
                                    <#list detail as i>
                                        <span class="betNum">${i.ball}</span>
                                    </#list>

                                </td>
                                <td>${order.note!}</td>
                                <td>${order.total!?string('0.00')}</td>
                                <td><#if order.end = 1>${order.lotteryResult.result!}<#else>-</#if></td>
                                <td><#if order.end = 0>等待开奖<#elseif order.prize = 1>${order.bonus?string('0.00')}<#else>未中奖</#if></td>
                                <td>${formatTime(order.payTime,'yyyy-MM-dd HH:mm')?substring(5)}</td>
                                <td><a href="javascript:void(0);">查看</a> <a href="javascript:void(0)">删除</a></td>
                            </tr>
                        </#list>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>

<script>
</script>
</body>
</html>