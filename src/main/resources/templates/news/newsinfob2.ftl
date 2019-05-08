<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>体彩咨讯-万艾</title>
    <link rel="stylesheet" href="/bootstrap/bootstrap.min.css">
    <link rel="stylesheet" href="/font-awesome-4.7.0/css/font-awesome.css"/>
    <link rel="stylesheet" href="/css/common.css"/>
    <script type="text/javascript" src="/js/jquery.min.js"></script>
    <script src="/bootstrap/bootstrap.min.js"></script>

    <style type="text/css">
        .item span{line-height: 80px;}
        .item .bet-btn{
            margin-top: 20px;
            line-height: 40px;
            text-align: center;
            height: 50%;
            background:url("/imgs/btn-bg.png");
            font-weight: bold;
            color:#444;
            border:1px solid #fff;
            box-sizing: border-box;
        }
        .item .bet-btn:hover{border:1px solid #3C8EE4;cursor: pointer;}
    </style>
</head>
<body>
    <#include "../common/header.ftl" encoding="utf-8" parse=true>
    <input type="hidden" value="2" id="lotteryType"/>
    <input type="hidden" value="竞彩篮球" id="lotteryName"/>
    <div class="main">
        <div class="container">
            <div class="content clearfix">
                <div class="main-left fleft">
                    <div class="sports">
                        <ul class="sports-ul">
                            <li class="li-item"><a href="/news/footNews">竞彩足球</a></li>
                            <li class="li-item"><a class="li-on" href="/news/basketNews?id=b1">竞彩篮球</a></li>
                        </ul>
                    </div>
                </div>
                <div class="main-content fleft">
                    <div class="foot-ul">
                        <div>
                            <ul>
                                <li class="foot-li"><a href="/news/basketNews?id=b1" id="basket1">胜负/让分</a></li>
                                <li class="foot-li"><a class="li-on" href="/news/basketNews?id=b2" id="basket2">大小分</a></li>
                            </ul>
                        </div>
                    </div>
                    <div class="info">
                        <div class="info-title">
                            <span class="fleft item-no">编号</span>
                            <span class="fleft item-type">赛事</span>
                            <span class="fleft item-date">开赛时间</span>
                            <span class="fleft item-home">主队</span>
                            <span class="fleft">VS</span>
                            <span class="fleft item-away">客队</span>
                            <span class="fleft item-span">大</span>
                            <span class="fleft item-span">预设总分</span>
                            <span class="fleft item-span">小</span>
                        </div>
                        <div class="info-content">
                            <#list itemlist! as li>
                                <div class="item" id="${li.id!}">
                                    <span class="fleft item-no">${li.weekday!}</span>
                                    <span class="fleft item-type"><#if li.type! = '欧冠杯'>欧篮联<#else>${li.type!}</#if></span>
                                    <span class="fleft item-date"><#if li.date = '比赛时间:'>-<#else>${li.date!?substring(10)}</#if></span>
                                    <span class="fleft item-home">${li.home!}</span>
                                    <span class="fleft">VS</span>
                                    <span class="fleft item-away">${li.away!}</span>
                                    <span class="fleft item-span bet-btn" data-v="${li.big!}">${li.big!}</span>
                                    <span class="fleft item-span">${li.point!}</span>
                                    <span class="fleft item-span bet-btn" data-v="${li.small!}">${li.small!}</span>
                                </div>

                            </#list>
                            <#if itemlist?size = 0>
                                <span style="height:50px;line-height:35px;text-align:center;font-size:16px;background:white;">暂无赛事</span>
                            </#if>
                        </div>
                    </div>
                </div>
                <div class="main-right fright">

                </div>
            </div>
            <#include "../common/betzone.ftl" encoding="utf-8" parse=true>
        </div>
    </div>
    <#--<#include "../common/footer.ftl" encoding="utf-8" parse=true>-->


<script type="text/javascript">

</script>
</body>
</html>