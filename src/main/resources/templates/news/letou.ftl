<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>大乐透</title>
    <link rel="stylesheet" href="/bootstrap/bootstrap.min.css">
    <link rel="stylesheet" href="/font-awesome-4.7.0/css/font-awesome.css"/>
    <link rel="stylesheet" href="/css/common.css"/>
    <script type="text/javascript" src="/js/jquery.min.js"></script>
    <script type="text/javascript" src="/bootstrap/popper.min.js"></script>
    <script src="/bootstrap/bootstrap.min.js"></script>
    <style>
        .rules{color:#b0b0b0;font-size: 12px;padding: 8px;margin-bottom: 50px;}
        .rules .fa{color:orangered;}
        .numzone{border-radius: 10px;background: url("/imgs/betBg.png");padding: 20px;}
        .numzone .num{padding: 10px 0 0 0;border-bottom: 1px dotted #b0b0b0;}
        .numzone .numtip{vertical-align:top;display:inline-block;width:80px;padding:3px 0;text-align: center;margin: 15px 0;position: relative;background-color: #455467;color:#fff;}
        .num .numtip .tipicon{display: inline-block;width:10px;height: 10px;position: absolute;top:9px;right:-5px;transform: rotate(45deg);background-color: #455467;}
        .num .num-group{display: inline-block;margin-left:2em;width: 86%;}
        .num .num-item{margin-bottom:20px;display: inline-block;box-shadow:1px 1px 1px 2px darkslategrey;cursor:pointer;margin-left:1em;font-size:18px;width: 40px;height: 40px;text-align: center;line-height: 40px;border-radius: 50%;background: radial-gradient(circle, white, lightgray);}
        .num .num-selected{background:radial-gradient(circle, white, darkblue);}

        .bet-info{text-align: center;margin: 50px 0;}
        #notelist{border:1px solid #b9b9b9;margin-top: 30px;height:200px;overflow:auto;}
        #notesTable tr{border-bottom: 1px dashed #d0d0d0;height: 50px;line-height: 50px;}
        .last-result,.left-time{background-color: #cccccc;border-radius: 5px;}
        .left-time .time{width: 300px;height: 50px;line-height: 50px;background-color: rgb(69,84,103);color:#fff;font-size: 20px;font-weight: bold;}
        .last-result ul{display: inline-block;}
        .last-result ul li{display: inline-block;width:42px;height:48px;line-height: 42px;text-align: center;background: url("/imgs/ballOpenBg.png");text-shadow: 1px 1px 1px #f5f5f5;font-size: 18px;}
        .last-result ul .fontBalls{color:red;}
        .last-result ul .backBalls{color:blue;}

    </style>
</head>
<body>
<#include "../common/header.ftl" encoding="utf-8" parse=true>
<input type="hidden" value="2" id="lotteryType"/>
<input type="hidden" value="大乐透" id="lotteryName"/>
<div class="main">
    <div class="container clearfix">
        <div class="left-container fleft">
            <div class="l-info" style="margin-bottom: 30px;">
                <h2>超级大乐透</h2>
                <p class="gray">每周一、三、六 20:30 开奖</p>
                <div class="fright">
                    <ul class="hd-link">
                        <li id="wfjs_btn"><a href="http://help.500.com/kefu/rule.php?id=2" target="_blank" id="link128"><i class="ico ico-wfjs"></i>游戏规则</a></li>
                    </ul>
                </div>
            </div>
            <div class="tab-group">
                <section id="tab1" title="标准选号">
                    <div class="rules"><i class="fa fa-hand-o-right"></i><span> 前区至少选择5个号码,后区至少选择2个号码,组成一注(无序)即中奖.</span></div>
                    <div class="numzone">
                        <div class="fontnum num">
                            <span class="numtip">前区<i class="tipicon"></i></span>
                            <ul class="num-group">
                                <#list 01..35 as i>
                                    <li class="num-item"><b class="ball" data-num="${i}">${i?string('00')}</b></li>
                                </#list>
                            </ul>
                        </div>
                        <div class="backnum num">
                            <span class="numtip">后区<i class="tipicon"></i></span>
                            <ul class="num-group">
                                <#list 01..12 as i>
                                    <li class="num-item"><b class="ball" data-num="${i}">${i?string('00')}</b></li>
                                </#list>
                            </ul>
                        </div>
                    </div>
                </section>
                <section id="tab2" title="胆拖投注">
                    <div class="rules"><i class="fa fa-hand-o-right"></i><span> 每位至少选择一个号码，竞猜开奖号码，号码和位置都对应即中奖</span></div>
                    <div class="numzone">
                        <div class="firstnum num">
                            <span class="numtip">百位<i class="tipicon"></i></span>
                            <ul class="num-group">
                                <#list 0..9 as i>
                                    <li data-v="${i}" class="num-item">${i!}</li>
                                </#list>
                            </ul>
                        </div>
                        <div class="secondnum num">
                            <span class="numtip">十位<i class="tipicon"></i></span>
                            <ul class="num-group">
                                <#list 0..9 as i>
                                    <li data-v="${i!}" class="num-item">${i!}</li>
                                </#list>
                            </ul>
                        </div>
                        <div class="thirdnum num">
                            <span class="numtip">个位<i class="tipicon"></i></span>
                            <ul class="num-group">
                                <#list 0..9 as i>
                                    <li data-v="${i!}" class="num-item">${i!}</li>
                                </#list>
                            </ul>
                        </div>
                    </div>
                </section>
                <section id="tab3" title="定胆杀号">
                    <div class="rules"><i class="fa fa-hand-o-right"></i><span> 每位至少选择一个号码，竞猜开奖号码，号码和位置都对应即中奖</span></div>
                    <div class="numzone">
                        <div class="firstnum num">
                            <span class="numtip">百位<i class="tipicon"></i></span>
                            <ul class="num-group">
                                <#list 0..9 as i>
                                    <li data-v="${i}" class="num-item">${i!}</li>
                                </#list>
                            </ul>
                        </div>
                        <div class="secondnum num">
                            <span class="numtip">十位<i class="tipicon"></i></span>
                            <ul class="num-group">
                                <#list 0..9 as i>
                                    <li data-v="${i!}" class="num-item">${i!}</li>
                                </#list>
                            </ul>
                        </div>
                        <div class="thirdnum num">
                            <span class="numtip">个位<i class="tipicon"></i></span>
                            <ul class="num-group">
                                <#list 0..9 as i>
                                    <li data-v="${i!}" class="num-item">${i!}</li>
                                </#list>
                            </ul>
                        </div>
                    </div>
                </section>

            </div>

            <#--选中后的投注信息-->
            <div class="bet-info">
                <p class="">您选择了 <em class="red fonts">0</em> 个前区号码, <em class="blue backs">0</em> 个后区号码,
                    共 <em class="red notes">0</em> 注,共 <em class="red price">0.00</em> 元</p>
                <p class="tcenter" style="margin-top: 40px;"><button class="btn btn-dark btn-lg" id="select-ok" style="width: 200px;">确认选号</button></p>
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
                <p class="tcenter" style="margin-top: 40px;"><button class="btn btn-dark btn-lg" id="save-bet" style="width: 200px;">保存方案</button></p>
            </div>
        </div>

        <#--开奖公告-->
        <div class="right-container fleft">
            <div class="right-top">
                <div class="left-time">
                    <p>第 <span class="red" id="nowcode">${thisResult.code!}</span> 期投注截止时间：</p>
                    <p class="time" id="left-time">${formatTime(thisResult.endTime!,"MM-dd HH:mm")}</p>
                </div>
                <#if lastResult??>
                    <div class="last-result">
                        <p>第 <span class="red">${lastResult.code!}</span> 期开奖号码</p>
                        <#assign nums = lastResult.result?split('|')>
                        <#assign font = nums[0]?split(',')>
                        <#assign back = nums[1]?split(',')>
                        <ul>
                            <#list font as f>
                                <li class="fontBalls"><#if f!?length = 1>${0+f}<#else>${f!}</#if></li>
                            </#list>
                            <#list back as b>
                                <li class="backBalls"><#if b!?length = 1>${0+b}<#else>${b!}</#if></li>
                            </#list>
                        </ul>
                    </div>
                </#if>
            </div>
        </div>

    </div>

</div>


<script type="text/javascript" src="/js/common.js"></script>
<script type="text/javascript" src="/js/jquery-tab.js"></script>
<script type="text/javascript">
    $(function(){
        $('.tab-group').tabify();
    });

    let fonts = [],backs = [];
    let total = 0,notes = 0;
    //选中或去除单个球
    $('.num-group').on('click','.num-item',function () {
        $(this).toggleClass('num-selected');
        if($(this).hasClass('num-selected')){
            if($(this).closest('.num').hasClass('fontnum')){
                fonts.push(parseInt($(this).find('.ball').attr('data-num')));
            }
            if($(this).closest('.num').hasClass('backnum')){
                backs.push(parseInt($(this).find('.ball').attr('data-num')));
            }
        }else {
            if($(this).closest('.num').hasClass('fontnum')){
                fonts.splice(fonts.indexOf($(this).find('.ball').attr('data-num')),1);
            }
            if($(this).closest('.num').hasClass('backnum')){
                backs.splice(fonts.indexOf($(this).find('.ball').attr('data-num')),1);
            }

        }
        console.log(fonts,backs);
        $(".bet-info .fonts").html(fonts.length);
        $(".bet-info .backs").html(backs.length);
        calculateNotes();
    });

    function calculateNotes() {
        if(fonts.length < 5 || backs.length < 2){
            $('.notes').html(0);
            return;
        }
        let notes = combination(fonts.length,5)*combination(backs.length,2);
        $('.notes').html(notes);
        $('.price').html(formatMoney(2*notes));
    }

    //选好了
    $('#select-ok').on('click',function () {
        if(fonts.length < 5 || backs.length < 2){
            $(this).attr('title','前区至少5个,后区至少2个');
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
        let note = $("<tr class='note-item'><td class='td-way'>" + way + "</td><td class='td-ball'>" + fonts.sort(sortNumber) + "|" + backs.sort(sortNumber) + "</td><td class='td-note'>" + $('.notes').html() +"注</td><td>" + $('.price').html() + "元</td><td><a href='javascript:void(0);' onclick='deleteNote()'>x</a></td></tr>");
        $('#notesTable').append(note);
        total = parseInt(total) + parseInt($('.price').html());
        notes = parseInt(notes) + parseInt($('.notes').html());
        $('.t-price').html(formatMoney(total));
        $('.t-notes').html(notes);

        //清空上一次选中的
        $('.num-item').removeClass('num-selected');
        fonts = [];backs = [];
        $('.fonts').html(0);
        $('.backs').html(0);
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