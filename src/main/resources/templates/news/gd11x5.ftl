<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>高频彩-万艾</title>
    <link rel="stylesheet" href="/bootstrap/bootstrap.min.css">
    <link rel="stylesheet" href="/font-awesome-4.7.0/css/font-awesome.css"/>
    <link rel="stylesheet" href="/css/common.css"/>
    <script type="text/javascript" src="/js/jquery.min.js"></script>
    <script type="text/javascript" src="/bootstrap/popper.min.js"></script>
    <script src="/bootstrap/bootstrap.min.js"></script>
    <style type="text/css">
        .rules{color:#b0b0b0;font-size: 12px;padding: 8px;margin-bottom: 50px;}
        .rules .fa{color:orangered;}
        .numzone{border-radius: 10px;background: url("/imgs/betBg.png");padding: 20px;}
        .numzone .num{padding: 20px 0 0 0;border-bottom: 1px dotted #b0b0b0;}
        .numzone .numtip{display:inline-block;width:65px;padding:3px 0;text-align: center;margin: 15px 0;position: relative;background-color: #455467;color:#fff;}
        .num .numtip .tipicon{display: inline-block;width:10px;height: 10px;position: absolute;top:9px;right:-5px;transform: rotate(45deg);background-color: #455467;}
        .num .num-group{display: inline-block;margin-left:4em;}
        .num .num-item{display: inline-block;box-shadow:1px 1px 1px 2px darkslategrey;cursor:pointer;margin-left:1em;font-size:18px;width: 40px;height: 40px;text-align: center;line-height: 40px;border-radius: 50%;background: radial-gradient(circle, white, lightgray);}
        .num .num-selected{background:radial-gradient(circle, white, darkblue);}
        .left-container{width: 72%;border-right: 1px solid #f0f0f0;}
        .right-container{width: 25%;border-right: 1px solid #f0f0f0;}
        .bet-info{text-align: center;margin: 50px 0;}
        #notelist{border:1px solid #b9b9b9;margin-top: 30px;height:200px;overflow:auto;}
        .last-result,.left-time{background-color: #cccccc;border-radius: 5px;}
        .left-time .time{width: 300px;height: 50px;line-height: 50px;background-color: rgb(69,84,103);color:#fff;font-size: 20px;font-weight: bold;}
        .last-result ul{display: inline-block;}
        .last-result ul li{display: inline-block;width:42px;height:48px;line-height: 42px;text-align: center;background: url("/imgs/ballOpenBg.png");text-shadow: 1px 1px 1px #f5f5f5;color:#a12836;font-size: 18px;}
    </style>
</head>
<body>
<#include "../common/header.ftl" encoding="utf-8" parse=true>
<input type="hidden" value="2" id="lotteryType"/>
<input type="hidden" value="广东11选5" id="lotteryName"/>
<div class="main">
    <div class="container clearfix">
        <div class="left-container fleft">
            <div class="l-info" style="margin-bottom: 30px;">
                <h2>广东11选5</h2>
            </div>
            <div class="tab-group">
                <section id="tab1" title="前三直选复式">
                    <div class="rules"><i class="fa fa-hand-o-right"></i><span> 从01-11共11个号码中选择3个不重复的号码组成一注，所选号码与当期顺序摇出的5个号码中的前3个号码相同，且顺序一致，即为中奖</span></div>
                    <div class="numzone">
                        <div class="firstnum num">
                            <span class="numtip">第一位<i class="tipicon"></i></span>
                            <ul class="num-group">
                                <#list 1..11 as i>
                                    <li data-v="${i!}" class="num-item">${i!?string('00')}</li>
                                </#list>
                            </ul>
                        </div>
                        <div class="secondnum num">
                            <span class="numtip">第二位<i class="tipicon"></i></span>
                            <ul class="num-group">
                                <#list 1..11 as i>
                                    <li data-v="${i!}" class="num-item">${i!?string('00')}</li>
                                </#list>
                            </ul>
                        </div>
                        <div class="thirdnum num">
                            <span class="numtip">第三位<i class="tipicon"></i></span>
                            <ul class="num-group">
                                <#list 1..11 as i>
                                    <li data-v="${i!}" class="num-item">${i!?string('00')}</li>
                                </#list>
                            </ul>
                        </div>
                    </div>
                </section>
                <section id="tab2" title="前二直选复式">
                    <div class="rules"><i class="fa fa-hand-o-right"></i><span> 从01-11共11个号码中选择2个不重复的号码组成一注，所选号码与当期顺序摇出的5个号码中 的前2个号码相同，且顺序一致，即为中奖</span></div>
                    <div class="numzone">
                        <div class="firstnum num">
                            <span class="numtip">第一位<i class="tipicon"></i></span>
                            <ul class="num-group">
                                <#list 1..11 as i>
                                    <li data-v="${i!}" class="num-item">${i!?string('00')}</li>
                                </#list>
                            </ul>
                        </div>
                        <div class="secondnum num">
                            <span class="numtip">第二位<i class="tipicon"></i></span>
                            <ul class="num-group">
                                <#list 1..11 as i>
                                    <li data-v="${i!}" class="num-item">${i!?string('00')}</li>
                                </#list>
                            </ul>
                        </div>
                    </div>
                </section>
                <section id="tab3" title="前三一码不定位">
                    <div class="rules"><i class="fa fa-hand-o-right"></i><span> 从01-11共11个号码中选择1个号码，每注由1个号码组成，只要当期顺序摇出的第一位、第二位、第三位开奖号码中包含所选号码</span></div>
                    <div class="numzone">
                        <div class="bdwnum num">
                            <span class="numtip" style="vertical-align: top;">不定位<i class="tipicon"></i></span>
                            <ul class="num-group" style="width: 80%;">
                                <#list 1..11 as i>
                                    <li data-v="${i!}" class="num-item" style="margin-bottom: 20px;">${i!?string('00')}</li>
                                </#list>
                            </ul>
                        </div>
                    </div>
                </section>
            </div>

            <#--选中后的投注信息-->
            <div class="bet-info">
                <p class="">您选择了 <em class="red notes">0</em> 注，
                    共 <em class="red price">0.00</em> 元</p>
                <p class="tcenter" style="margin-top: 40px;"><button id="select-ok" class="btn btn-dark btn-lg" style="width: 200px;">确认选号</button></p>
                <div id="notelist">
                    <table border="0" width="100%" cellspacing="0" cellpadding="0" class="tcenter table">
                        <tbody id="notesTable">

                        </tbody>
                    </table>
                </div>
                <p class="tcenter" style="margin-top: 40px;">
                    倍数
                    <span class="numsel" style="width: auto;">
                            <span class="bei-jian" onclick="minusBs()">
                                -
                            </span>
                            <span class="bei-show">
                                <input type="text" value="1" maxlength="5" id="buy_bs">
                            </span>
                            <span class="bei-jia" onclick="addBs()">
                                +
                            </span>
                        </span>
                    <span class="info-money-je">金额 <em class="t-price red">0.00</em> 元&nbsp;&nbsp;( <em class="t-notes blue">0</em> 注)</span>
                </p>
                <p class="tcenter" style="margin-top: 40px;"><button id="save-bet" class="btn btn-dark btn-lg" style="width: 200px;">保存方案</button></p>
            </div>
        </div>
        <#--开奖公告-->
        <div class="right-container fleft">
            <div class="right-top">
                <div class="left-time">
                    <p>距 <span class="red" id="nowcode">${thisResult.code!}</span> 期投注截止还有：</p>
                    <p class="time" id="left-time">00:00:00</p>
                </div>
                <div class="last-result">
                    <p>第 <span class="red">${lastResult.code!}</span> 期开奖号码</p>
                    <#assign nums = lastResult.result?split(',')>
                    <ul>
                        <#list nums as n>
                            <li><#if n!?length = 1>${0+n}<#else>${n!}</#if></li>
                        </#list>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="/js/common.js"></script>
<script type="text/javascript" src="/js/jquery-tab.js"></script>
<#--<script type="text/javascript" src="/js/calcaulate.js"></script>-->
<script type="text/javascript">
    $(function(){
        $('.tab-group').tabify();
        //计算时间
        let l = new Date('${thisResult.endTime}');
        let n = new Date();
        setInterval(function () {
            let n = new Date();
        }, 1000);
        let t =  l.getTime()-n.getTime();
        maxtime = t/1000; //按秒计算
        timer = setInterval(CountDown, 1000);
    });

    function CountDown() {
        if (maxtime >= 0) {
            minutes = Math.floor(maxtime / 60);
            seconds = Math.floor(maxtime % 60);
            if(minutes.toString().length === 1) minutes = '0'+minutes;
            if(seconds.toString().length === 1) seconds = '0'+seconds;
            msg = "00:" + minutes + ":" + seconds + "";
            $('#left-time').html(msg)
            --maxtime;
        } else{
            window.location.reload();
            clearInterval(timer);
        }
    }
    //切换形式时去除已选择的
    $('.tab-group').on('click','.tab-nav li a',function () {
        $('.num-item').removeClass('num-selected');
    });

    let first = [], second = [], third = [];
    let total = 0,notes = 0;
    //选中单个球
    $('.num-group').on('click','.num-item',function () {
        $(this).toggleClass('num-selected');
        if($(this).hasClass('num-selected')){//点击选中
            if($(this).closest('.num').hasClass('firstnum')) first.push(parseInt($(this).attr('data-v')));
            if($(this).closest('.num').hasClass('secondnum')) second.push(parseInt($(this).attr('data-v')));
            if($(this).closest('.num').hasClass('thirdnum')) third.push(parseInt($(this).attr('data-v')));
        }else {//点击去除
            if($(this).closest('.num').hasClass('firstnum')) first.splice(first.indexOf(parseInt($(this).attr('data-v'))),1);
            if($(this).closest('.num').hasClass('secondnum')) second.splice(second.indexOf(parseInt($(this).attr('data-v'))),1);
            if($(this).closest('.num').hasClass('thirdnum')) third.splice(third.indexOf(parseInt($(this).attr('data-v'))),1);
        }
        console.log(first,second,third);
        let note = 0;
        if(first.length === 0 || second.length === 0 || third.length === 0){
            $('.notes').html(0);
            $('.price').html(formatMoney(0));
            return;
        }

        for(let i = 0;i < first.length;i++){
            for(let j = 0;j < second.length; j++){
                if(first[i] === second[j]){
                    continue;
                }
                for(let k = 0;k < third.length;k++){
                    if(first[i] === third[k] || second[j] === third[k]){
                        continue;
                    }
                    note++;
                }
            }
        }
        $('.notes').html(note);
        $('.price').html(formatMoney(2*note));
        console.log('note:',note)
    });

    //确认选号
    $('#select-ok').on('click',function () {
        if($('.notes').html() === '0'){
            $(this).attr('title','至少选择3位不重复的号码');
            $(this).tooltip('show');
            setTimeout(function () {
                $('#select-ok').tooltip('dispose');
            },3000);
            return;
        }
        //加入已选table中
        let way;
        $('section').each(function() {
            if($(this).hasClass('active')){
                way = $(this).attr('title');
                return;
            }
        });
        let note = $("<tr class='note-item'><td class='td-way'>" + way + "</td><td class='td-ball'>" + first.sort(sortNumber) + "|" + second.sort(sortNumber) + "|" + third.sort(sortNumber) + "</td><td class='td-note'>" + $('.notes').html() +"注</td><td>" + $('.price').html() + "元</td><td><a href='javascript:void(0);' onclick='deleteNote()'>x</a></td></tr>");
        $('#notesTable').append(note);
        total = parseInt(total) + parseInt($('.price').html());
        notes = parseInt(notes) + parseInt($('.notes').html());
        $('.t-price').html(formatMoney(total));
        $('.t-notes').html(notes);

        //清空上一次选中的
        $('.num-item').removeClass('num-selected');
        first = [];second = [];third = [];
        $('.notes').html(0);
        $('.price').html(0.00);
    });

    //保存方案
    $('#save-bet').on('click',function () {
        if($('#notesTable').find('.note-item').length === 0){
            $(this).attr('title','至少投一注号码');
            $(this).tooltip('show');
            setTimeout(function () {
                $('#save-bet').tooltip('dispose');
            },3000);
            return;
        }

        let code = $('#nowcode').html();
        let lotteryName = $('#lotteryName').val();
        let lotteryType = $('#lotteryType').val();
        let note = $('.t-notes').html();
        let total = $('.t-price').html();
        let details = [];
        $('#notesTable').find('tr').each(function () {
            let detail = {};
            detail.way = $(this).find('.td-way').html();
            detail.ball = $(this).find('.td-ball').html();
            detail.note = $(this).find('.td-note').html().replace('注','');
            details.push(detail);
        });
        $.ajax({
            url:'doBet',
            type:'post',
            dataType:'json',
            data:{code:code,lotteryType: lotteryType,lotteryName:lotteryName,detail:JSON.stringify(details),note:note,total:total},
            success:function (data) {
                if(data.result === '0'){//未登录
                    window.open("../user/toLogin",'_self');
                    return;
                }

                //已认证用户生成投注单
                alert(data.msg);
            },
            error:function (error) {
                alert("网络中断");
            }
        })
    });

</script>
</body>
</html>