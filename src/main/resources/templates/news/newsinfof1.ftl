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
        body{position: relative;}
        .info .item{height: 80px;}
        .info .item-no, .info .item-type {width: 8%;}
        .info .item-date, .info .item-home, .info .item-away, .info .bet-more-btn {width: 10%;}
        .info .item-span{width:10%;}
        .item>.it{line-height: 80px;}
        .item-span .t-item{width:100%;height: 50%;line-height: 40px;box-sizing: border-box;}
        .item-span .bet-btn{background:url("/imgs/btn-bg.png");font-weight: bold;color:#444;border:1px solid #fff;}
        .item-span .notsale{background-color: #e8e8e8;width: 310%;border:1px solid #f0f0f0;}
        .item-span .bet-btn:hover{border:1px solid #3C8EE4;cursor: pointer;}
        .bet-span{display:inline-block;width:30%;height: 50%;}
        .bet-span .bet-btn{display: inline-block;width:30%;line-height: 40px;cursor: pointer;}
    </style>
</head>
<body>
    <#include "../common/header.ftl" encoding="utf-8" parse=true>
    <input type="hidden" value="2" id="lotteryType"/>
    <input type="hidden" value="竞彩足球" id="lotteryName"/>
    <div class="main">
        <div class="content container clearfix">
            <div class="main-left fleft">
                <div class="sports">
                    <ul class="sports-ul">
                        <li class="li-item"><a class="li-on" href="/news/footNews">竞彩足球</a></li>
                        <li class="li-item"><a href="/news/basketNews?id=b1">竞彩篮球</a></li>
                    </ul>
                </div>
            </div>
            <div class="main-content fleft">
                <div class="foot-ul">
                    <div>
                        <ul>
                            <li class="foot-li"><a class="li-on" href="/news/footNews?id=f1">胜平负/让球</a></li>
                            <li class="foot-li"><a href="/news/footNews?id=f2" id="foot2">比分</a></li>
                            <li class="foot-li"><a href="/news/footNews?id=f3" id="foot3">半全场</a></li>
                            <li class="foot-li"><a href="/news/footNews?id=f4" id="foot4">进球数</a></li>
                        </ul>
                    </div>
                </div>
                <div class="info">
                    <div class="info-title">
                        <span class="fleft item-no">编号</span>
                        <span class="fleft item-type">赛事</span>
                        <span class="fleft item-date">开赛时间</span>
                        <span class="fleft item-home">主队</span>
                        <span class="fleft ">VS</span>
                        <span class="fleft item-away">客队</span>
                        <span class="fleft item-span">让球</span>
                        <span class="fleft item-span">胜</span>
                        <span class="fleft item-span">平</span>
                        <span class="fleft item-span">负</span>
                    </div>
                    <div class="info-content">
                        <#list itemlist! as li>
                            <div class="item" id="${li.weekday!}">
                                <span class="fleft it item-no">${li.weekday!}</span>
                                <span class="fleft it item-type">${li.type!}</span>
                                <span class="fleft it item-date">${li.date!?substring(5)}</span>
                                <span class="fleft it item-home">${li.home!}</span>
                                <span class="fleft it">VS</span>
                                <span class="fleft it item-away">${li.away!}</span>
                                <span class="fleft item-span">
                                    <span class="t-item ball0">0</span>
                                    <span class="t-item ball1">${li.letBall!}</span>
                                </span>
                                <#if li.noLetWin != "未开售">
                                    <span class="fleft bet-span nolet">
                                        <span tid="no1" class="bet-btn" data-v="${li.noLetWin!}">${li.noLetWin!}</span>
                                        <span tid="no2" class="bet-btn" data-v="${li.noLetEven!}">${li.noLetEven!}</span>
                                        <span tid="no3" class="bet-btn" data-v="${li.noLetLose!}">${li.noLetLose!}</span>
                                    </span>
                                    <span class="fleft bet-span let">
                                        <span tid="l1" class="bet-btn" data-v="${li.letWin!}">${li.letWin!}</span>
                                        <span tid="l2" class="bet-btn" data-v="${li.letEven!}">${li.letEven!}</span>
                                        <span tid="l3" class="bet-btn" data-v="${li.letLose!}">${li.letLose!}</span>
                                    </span>
                                <#else>
                                    <span class="fleft bet-span nolet">
                                        <span style="display:inline-block;width:96%;height: 40px;line-height: 40px;background-color: #f0f0f0" data-v="${li.noLetWin!}">${li.noLetWin!}</span>
                                        <span class="" data-v="${li.noLetEven!}">${li.noLetEven!}</span>
                                        <span class="" data-v="${li.noLetLose!}">${li.noLetLose!}</span>
                                    </span>
                                    <span class="fleft bet-span let">
                                        <span tid="l1" class="bet-btn" data-v="${li.letWin!}">${li.letWin!}</span>
                                        <span tid="l2" class="bet-btn" data-v="${li.letEven!}">${li.letEven!}</span>
                                        <span tid="l3" class="bet-btn" data-v="${li.letLose!}">${li.letLose!}</span>
                                    </span>
                                </#if>
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
        <#--<#include "../common/loginform.ftl" encoding="utf-8" parse=true>-->
    </div>
<script type="text/javascript" src="/js/common.js"></script>
<script type="text/javascript" src="/js/footbet.js"></script>
<script type="text/javascript" src="/js/calculate.js"></script>
<script>
</script>
</body>
</html>