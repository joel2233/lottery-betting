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
        .info .item-no, .info .item-type {width: 10%;}
        .info .item-date, .info .item-home, .info .item-away, .info .bet-more-btn {width: 10%;}
        .info .item-span{width:5%;}
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
        }
        .item .bet-btn:hover{border:1px solid #3C8EE4;cursor: pointer;}
    </style>
</head>
<body>
    <#include "../common/header.ftl" encoding="utf-8" parse=true>
    <input type="hidden" value="2" id="lotteryType"/>
    <input type="hidden" value="竞彩足球" id="lotteryName"/>
    <div class="main">
        <div class="container">
            <div class="content clearfix">
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
                                <li class="foot-li"><a href="/news/footNews?id=f1">胜平负/让球</a></li>
                                <li class="foot-li"><a href="/news/footNews?id=f2" id="foot2" url="">比分</a></li>
                                <li class="foot-li"><a href="/news/footNews?id=f3" id="foot3" url="http://jacksonyang.top:5000/football/half">半全场</a></li>
                                <li class="foot-li"><a class="li-on" href="/news/footNews?id=f4" id="foot4" url="http://jacksonyang.top:5000/football/total">进球数</a></li>
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
                            <span class="fleft item-span">0球</span>
                            <span class="fleft item-span">1球</span>
                            <span class="fleft item-span">2球</span>
                            <span class="fleft item-span">3球</span>
                            <span class="fleft item-span">4球</span>
                            <span class="fleft item-span">5球</span>
                            <span class="fleft item-span">6球</span>
                            <span class="fleft item-span">7球</span>
                        </div>
                        <#list itemlist! as li>
                            <div class="info-content">
                                <div class="item" id="${li.id!}">
                                    <span class="fleft item-no">${li.weekday!}</span>
                                    <span class="fleft item-type">${li.type!}</span>
                                    <span class="fleft item-date">${li.date!?substring(5)}</span>
                                    <span class="fleft item-home">${li.home!}</span>
                                    <span class="fleft">VS</span>
                                    <span class="fleft item-away">${li.away!}</span>
                                    <span class="fleft item-span bet-btn" data-v="${li.zero!}">${li.zero!}</span>
                                    <span class="fleft item-span bet-btn" data-v="${li.one!}">${li.one!}</span>
                                    <span class="fleft item-span bet-btn" data-v="${li.two!}">${li.two!}</span>
                                    <span class="fleft item-span bet-btn" data-v="${li.three!}">${li.three!}</span>
                                    <span class="fleft item-span bet-btn" data-v="${li.four!}">${li.four!}</span>
                                    <span class="fleft item-span bet-btn" data-v="${li.five!}">${li.five!}</span>
                                    <span class="fleft item-span bet-btn" data-v="${li.six!}">${li.six!}</span>
                                    <span class="fleft item-span bet-btn" data-v="${li.seven!}">${li.seven!}</span>
                                </div>
                            </div>
                        </#list>
                        <#if itemlist?size = 0>
                            <span style="height:50px;line-height:35px;text-align:center;font-size:16px;background:white;">暂无赛事</span>
                        </#if>
                    </div>
                </div>
                <div class="main-right fright"></div>
            </div>
            <#include "../common/betzone.ftl" encoding="utf-8" parse=true>
            <#--<#include "../common/footer.ftl" encoding="utf-8" parse=true>-->
        </div>
    </div>

<script type="text/javascript">

</script>
</body>
</html>