<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>体彩-万艾</title>
    <link rel="stylesheet" href="/bootstrap/bootstrap.min.css">
    <link rel="stylesheet" href="/font-awesome-4.7.0/css/font-awesome.css"/>
    <link rel="stylesheet" href="/css/common.css"/>
    <script type="text/javascript" src="/js/jquery.min.js"></script>
    <script src="/bootstrap/bootstrap.min.js"></script>
    <style type="text/css">
        .info .item-no, .info .item-type {width: 10%;}
        .info .item-date, .info .item-home, .info .item-away, .info .bet-more-btn {width: 10%;}
        .info .item-span{width:20%;}
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
                                <li class="foot-li"><a class="li-on" href="/news/basketNews?id=b1" id="basket1">胜负/让分</a></li>
                                <li class="foot-li"><a href="/news/basketNews?id=b2" id="basket2">大小分</a></li>
                            </ul>
                        </div>
                    </div>
                    <div class="info">
                        <div class="info-title" style="height: 80px;line-height: 80px;">
                            <span class="fleft item-no">编号</span>
                            <span class="fleft item-type">赛事</span>
                            <span class="fleft item-date">开赛时间</span>
                            <span class="fleft item-home">主队</span>
                            <span class="fleft">VS</span>
                            <span class="fleft item-away">客队</span>
                            <span class="fleft item-span" style="line-height: 40px;">
                                <div class="th-div">
                                    <div class="th-bet-title fleft" style="width: 100%;height: 40px;">胜负</div>
                                    <div class="th-bet1 th-bet-l fleft" style="width: 50%;height: 40px;">主胜</div>
                                    <div class="th-bet1 th-bet-r fleft" style="width: 50%;height: 40px;">主负</div>
                                </div>
                            </span>
                            <span class="fleft item-span" style="line-height: 40px;">
                                <div class="th-div">
                                    <div class="th-bet-title" style="width: 100%;height: 40px;">让分胜负</div>
                                    <div class="th-bet2 th-bet-l fleft" style="width: 33%;height: 40px;">主胜</div>
                                    <div class="th-bet2 th-bet-m fleft" style="width: 33%;height: 40px;">预设总分</div>
                                    <div class="th-bet2 th-bet-r fleft" style="width: 33%;height: 40px;">主负</div>
                                </div>
                            </span>
                            </tr>
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
                                    <span class="fleft item-span">
                                <div class="th-div">
                                    <div class="bet-btn fleft" data-v="${li.noLetWin!}" style="width: 50%;">${li.noLetWin!}</div>
                                    <div class="bet-btn fleft" data-v="${li.noLetLose!}" style="width: 50%;">${li.noLetLose!}</div>
                                </div>
                            </span>
                                    <span class="fleft item-span">
                                <div class="th-div">
                                    <div class="bet-btn fleft" data-v="${li.letWin!}" style="width: 33%;">${li.letWin!}</div>
                                    <div class="fleft" style="width: 33%;">${li.letBall!}</div>
                                    <div class="bet-btn fleft" data-v="${li.letLose!}" style="width: 33%;">${li.letLose}</div>
                                </div>
                            </span>
                                </div>
                            </#list>
                            <#if itemlist?size = 0>
                                <span style="height:50px;line-height:35px;text-align:center;font-size:16px;background:white;">暂无赛事</span>
                            </#if>
                        </div>
                    </div>
                </div>
                <div class="main-right fright"></div>
            </div>
            <#include "../common/betzone.ftl" encoding="utf-8" parse=true>
        </div>
    </div>
<script type="text/javascript" src="/js/common.js"></script>
<#--<script type="text/javascript" src="/js/calcaulate.js"></script>-->
<script type="text/javascript">

</script>
</body>
</html>