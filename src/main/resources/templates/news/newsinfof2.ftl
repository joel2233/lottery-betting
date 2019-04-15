<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>体彩咨讯-万艾</title>
    <link rel="stylesheet" href="/font-awesome-4.7.0/css/font-awesome.css"/>
    <link rel="stylesheet" href="/css/common.css"/>
    <script type="text/javascript" src="../js/jquery.min.js"></script>

    <style type="text/css">
        .header li{list-style: none;display: inline-block;}
        .item{line-height: 80px;}
        .bet-more-info .bet-btn{border:2px solid #eaf2fc;display: inline-block;width:60px;height: 40px;background-color: #fff;text-align: center;line-height: 55px;margin: 10px 2px;}
        .bet-more-info .bet-btn:hover{border:2px solid #68A4ED;cursor: pointer;}
        .bet-more-info{padding:30px 20px;background-color: #eaf2fc;}
    </style>
</head>
<body>
    <#include "../common/header.ftl" encoding="utf-8" parse=true>
    <input type="hidden" value="2" id="lotteryType"/>
    <input type="hidden" value="竞彩足球" id="lotteryName"/>
    <div class="main">
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
                            <li class="foot-li"><a class="li-on" href="/news/footNews?id=f2" id="foot2" url="">比分</a></li>
                            <li class="foot-li"><a href="/news/footNews?id=f3" id="foot3" url="http://jacksonyang.top:5000/football/half">半全场</a></li>
                            <li class="foot-li"><a href="/news/footNews?id=f4" id="foot4" url="http://jacksonyang.top:5000/football/total">进球数</a></li>
                        </ul>
                    </div>
                </div>
                <div class="info">
                    <div class="info-title">
                        <span class="item-no">编号</span>
                        <span class="item-type">赛事</span>
                        <span class="item-date">开赛时间</span>
                        <span class="item-home">主队</span>
                        <span class="item-span">VS</span>
                        <span class="item-away">客队</span>
                        <span class="bet-more-btn">投注区</span>
                    </div>
                    <div class="info-content">
                        <#list itemlist! as li>
                            <div class="item" id="${li.id!}">
                                <span class="item-no">${li.weekday!}</span>
                                <span class="item-type">${li.type!}</span>
                                <span class="item-date">${li.date!?substring(5)}</span>
                                <span class="item-home">${li.home!}</span>
                                <span class="item-span">VS</span>
                                <span class="item-away">${li.away!}</span>
                                <span class="bet-more-btn">展开投注</span>
                            </div>
                            <div class="bet-more-info hide">
                                <#list li?keys as key>
                                    <#assign k = key?replace("zero","0")?replace("win","胜")?replace("lose","负")?replace("even","平")?replace("other","其它")?replace("one","1")?replace("two","2")?replace("three","3")?replace("four","4")?replace("five","5")?replace("six","6")?replace("_",":")>
                                    <#if key != 'id' && key != 'date' && key != 'weekday' && key != 'type' && key != 'home' && key != 'away'>
                                    <span class="bet-btn ruby" data-v="${li[key]!}" data-s="${k}"><ruby>${li[key]!}<rt>${k}</rt></ruby></span>
                                    </#if>
                                    <#if key = 'win_other' || key = 'even_other' || key = 'lose_other'><br/></#if>
                                </#list>
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
    <#--<#include "../common/footer.ftl" encoding="utf-8" parse=true>-->
<script type="text/javascript">
    $(".bet-more-btn").on("click",function () {
        let btn = $(this);
        let info = $(this).closest(".item").next(".bet-more-info");
        if(info.hasClass("hide")){
            btn.html("收起投注");
        }else {
            btn.html("展开投注");
        }
        $(this).closest(".item").next(".bet-more-info").toggle(1000,function () {
            $(this).toggleClass("hide");
        });
    })
</script>
</body>
</html>