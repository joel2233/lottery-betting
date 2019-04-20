<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>大乐透</title>
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" href="/font-awesome-4.7.0/css/font-awesome.css"/>
    <link rel="stylesheet" href="/css/common.css"/>
    <script type="text/javascript" src="../js/jquery.min.js"></script>
    <#--<script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>-->
    <script src="https://cdn.bootcss.com/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://cdn.bootcss.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
    <style>
        .c-tab{width:95%;margin: 0 auto;}
        .c-tit{text-align: center;padding: 15px 0;}
        .tab-l,.tab-r{height: 300px;}
        .tab-l,.tab-r{height: 300px;}
        .c-num .balls-ul{padding: 20px 138px;height: 250px;}
        .c-num .num-li{font-size:20px;font-weight: bold;display: inline-block;width: 50px;height: 50px;border: 1px solid #a0a0a0;line-height: 50px;text-align: center;border-radius: 50%;margin-bottom: 20px;cursor: pointer;}
        .l-num-selected{background-color: #ff8a00;color:#fff;border-color: #ff8a00!important;}
        .r-num-selected{background-color: #338bff;color:#fff;border-color: #338bff!important;}
        .num-info{width: 50%;text-align: center;margin: 100px auto 30px;}

        .selection{display: inline-block;}
        .btn-ok,.bet-ok{width: 200px;}
        .c-rst-ok{margin-bottom: 30px;}
        .c-rst-l{border:1px solid #a0a0a0;width:60%;}
        .c-rst-r{width:30%;margin-left: 50px;}
        .list-info{text-align: right;width: 100%;height: 20px;border: 1px solid #;background-color: beige;padding-top: 3px;padding-right: 10px;}
        #notesTable tr{height: 50px;line-height: 50px;border-bottom: 1px dotted #b0b0b0;}
    </style>
</head>
<body>
<#include "../common/header.ftl" encoding="utf-8" parse=true>
<div class="center" style="margin: 50px auto;">
    <div class="clearfix">
        <div class="fleft">
            <div class="logo-text" style="display: inline-block;margin-right: 40px;">
                <h2>超级大乐透</h2>
            </div>
            <div class="logo-info fright" style="width:472px;">
                <p class="gray">3元可中1800万！每周一、三、六 20:30 开奖</p>
                <p class="gray">奖池滚存<span class="red">62.74亿</span>元　截止时间：04-17 20:00</p>
            </div>
        </div>
        <div class="fright">
            <ul class="hd-link">
                <li id="wfjs_btn"><a href="http://help.500.com/kefu/rule.php?id=2" target="_blank" id="link128"><i class="ico ico-wfjs"></i>游戏规则</a></li>
            </ul>
        </div>
    </div>
</div>
<div class="center">
    <div class="clearfix">
        <div class="cont">
            <ul class="nav nav-tabs">
                <li class="nav-item">
                    <a class="nav-link active" href="#">标准选号</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">胆拖投注</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">定胆杀号</a>
                </li>
            </ul>
            <!-- 普通投注 -->
            <div class="c-tab" style="display:block;" id="pttz">
                <div class="clearfix">
                    <#--前区-->
                    <div class="tab-l fleft" style="width: 60%;">
                        <div class="c-tit">— <span class="red">前区</span> —　至少选 <em class="red">5</em> 个</div>
                        <div class="c-num">
                            <ul class="clearfix balls-ul">
                                <#list 1..35 as i>
                                   <li class="num-li"><b class="ball" data-num="${i}">${i}</b></li>
                                </#list>
                            </ul>
                        </div>
                        <div class="c-info l-info tcenter">
                            <div class="selection">
                                <select autocomplete="off" name="sele">
                                    <option value="5" selected="">5</option>
                                    <#list 6..35 as i>
                                        <option value="${i}">${i}</option>
                                    </#list>
                                </select>
                            </div>
                            <div class="btn btn-white" onclick="randomse('font')">机选前区</div>
                            <span class="c-del c-l-del gray" title="清除所选前区号码" onclick ="clear('font')">清除</span>
                        </div>
                    </div>
                    <#--后区-->
                    <div class="tab-r fleft" style="width: 40%;">
                        <div class="c-tit">— <span class="blue f18">后区</span> —　至少选 <em class="red">2</em> 个</div>
                        <div class="c-num">
                            <ul class="balls-ul clearfix">
                                <#list 1..12 as i>
                                    <li class="num-li"><b class="ball" data-num="${i}">${i}</b></li>
                                </#list>
                            </ul>
                        </div>
                        <div class="c-info r-info tcenter">
                            <div class="selection">
                                <select autocomplete="off" name="sele" data-change="set(afterRndSize, $value)">
                                    <option value="2" selected="">2</option>
                                    <#list 3..12 as i>
                                        <option value="${i}">${i}</option>
                                    </#list>
                                </select>
                                <div class="btn btn-white" onclick="randomse('back')">机选后区</div>
                                <span class="c-del c-l-del gray" title="清除所选后区号码" onclick="clear('back')">清除</span>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="num-info">
                    <p class="">您选择了 <em class="red fonts">0</em> 个前区号码， <em class="blue backs">0</em> 个后区号码，共 <em class="red notes">0</em> 注，共 <em class="red price">0.00</em> 元</p>
                </div>
            </div>


            <!-- 胆拖投注 -->
            <div class="c-tab c-dttz" style="display:none;" id="dttz">
                <div class="c-select clearfix">
                    <div class="c-s-l">
                        <div class="c-tit"><span class="r">我认为必出的号码</span>— <span class="orange3 f18">前区胆码</span> —　<span class="cl-gray2">至多选择<em class="red">4</em>个</span><span class="c-slt-del m-l20" title="清除所选前区胆码" data-click="clear(beforeD)">清</span></div>
                        <div class="c-num">
                            <ul class="ball-orange clearfix j-b-d">
                                <li><b class="ball" data-num="01">01</b><em class="num-yl">2</em><em class="num-lr">0</em></li>
                                <li><b class="ball" data-num="02">02</b><em class="num-yl">9</em><em class="num-lr">0</em></li>
                                <li><b class="ball" data-num="03">03</b><em class="num-yl">2</em><em class="num-lr">0</em></li>
                                <li><b class="ball" data-num="04">04</b><em class="num-yl">0</em><em class="num-lr">0</em></li>
                                <li><b class="ball" data-num="05">05</b><em class="num-yl">4</em><em class="num-lr">0</em></li>
                                <li><b class="ball" data-num="06">06</b><em class="num-yl">5</em><em class="num-lr">0</em></li>
                                <li><b class="ball" data-num="07">07</b><em class="num-yl">7</em><em class="num-lr">0</em></li>
                                <li><b class="ball" data-num="08">08</b><em class="num-yl">8</em><em class="num-lr">0</em></li>
                                <li><b class="ball" data-num="09">09</b><em class="num-yl">6</em><em class="num-lr">0</em></li>
                                <li><b class="ball" data-num="10">10</b><em class="num-yl">0</em><em class="num-lr">0</em></li>
                                <li><b class="ball" data-num="11">11</b><em class="num-yl">4</em><em class="num-lr">0</em></li>
                                <li><b class="ball" data-num="12">12</b><em class="num-yl">1</em><em class="num-lr">0</em></li>
                                <li><b class="ball" data-num="13">13</b><em class="num-yl">0</em><em class="num-lr">0</em></li>
                                <li><b class="ball" data-num="14">14</b><em class="num-yl">12</em><em class="num-lr">0</em></li>
                                <li><b class="ball" data-num="15">15</b><em class="num-yl">8</em><em class="num-lr">0</em></li>
                                <li><b class="ball" data-num="16">16</b><em class="num-yl">4</em><em class="num-lr">0</em></li>
                                <li><b class="ball" data-num="17">17</b><em class="num-yl">16</em><em class="num-lr">0</em></li>
                                <li><b class="ball" data-num="18">18</b><em class="num-yl">3</em><em class="num-lr">0</em></li>
                                <li><b class="ball" data-num="19">19</b><em class="num-yl">1</em><em class="num-lr">0</em></li>
                                <li><b class="ball" data-num="20">20</b><em class="num-yl">1</em><em class="num-lr">0</em></li>
                                <li><b class="ball" data-num="21">21</b><em class="num-yl">6</em><em class="num-lr">0</em></li>
                                <li><b class="ball" data-num="22">22</b><em class="num-yl">1</em><em class="num-lr">0</em></li>
                                <li><b class="ball" data-num="23">23</b><em class="num-yl"><span style="color:red">41</span></em><em class="num-lr">0</em></li>
                                <li><b class="ball" data-num="24">24</b><em class="num-yl">9</em><em class="num-lr">0</em></li>
                                <li><b class="ball" data-num="25">25</b><em class="num-yl">15</em><em class="num-lr">0</em></li>
                                <li><b class="ball" data-num="26">26</b><em class="num-yl">3</em><em class="num-lr">0</em></li>
                                <li><b class="ball" data-num="27">27</b><em class="num-yl">4</em><em class="num-lr">0</em></li>
                                <li><b class="ball" data-num="28">28</b><em class="num-yl">0</em><em class="num-lr">0</em></li>
                                <li><b class="ball" data-num="29">29</b><em class="num-yl">14</em><em class="num-lr">0</em></li>
                                <li><b class="ball" data-num="30">30</b><em class="num-yl">6</em><em class="num-lr">0</em></li>
                                <li><b class="ball" data-num="31">31</b><em class="num-yl">2</em><em class="num-lr">0</em></li>
                                <li><b class="ball" data-num="32">32</b><em class="num-yl">2</em><em class="num-lr">0</em></li>
                                <li><b class="ball" data-num="33">33</b><em class="num-yl">0</em><em class="num-lr">0</em></li>
                                <li><b class="ball" data-num="34">34</b><em class="num-yl">2</em><em class="num-lr">0</em></li>
                                <li><b class="ball" data-num="35">35</b><em class="num-yl">3</em><em class="num-lr">0</em></li>
                            </ul>
                        </div>
                    </div>
                    <div class="c-s-filter">
                        <p class="filter-cur"> <span class="filter-yl">遗漏 <i class="ico"></i> </span> <span class="filter-yl-tips">指此号码自上次开出至本期未出现的期数<i class="ico"></i></span> </p>
                        <p> <span class="filter-lr">冷热 <i class="ico"></i> </span> <span class="filter-lr-tips">指此号码近100期出现次数<i class="ico"></i></span> </p>
                    </div>
                </div>
                <div class="c-select clearfix p-t">
                    <div class="c-s-l">
                        <div class="c-tit"><span class="r">我认为可能出的号码</span>— <span class="orange3 f18">前区拖码</span> —　<span class="cl-gray2">至少选择<em class="red">2</em>个</span>
                            <select autocomplete="off" id="dt_tuo_opts" class="m-l20" data-change="set(beforeRndSize, $value)">
                                <option value="1">1</option>
                                <option value="2">2</option>
                                <option value="3">3</option>
                                <option value="4">4</option>
                                <option value="5" selected="">5</option>
                                <option value="6">6</option>
                                <option value="7">7</option>
                                <option value="8">8</option>
                                <option value="9">9</option>
                                <option value="10">10</option>
                                <option value="11">11</option>
                                <option value="12">12</option>
                                <option value="13">13</option>
                                <option value="14">14</option>
                                <option value="15">15</option>
                                <option value="16">16</option>
                                <option value="17">17</option>
                                <option value="18">18</option>
                                <option value="19">19</option>
                                <option value="20">20</option>
                                <option value="21">21</option>
                                <option value="22">22</option>
                                <option value="23">23</option>
                                <option value="24">24</option>
                                <option value="25">25</option>
                                <option value="26">26</option>
                                <option value="27">27</option>
                                <option value="28">28</option>
                                <option value="29">29</option>
                                <option value="30">30</option>
                                <option value="31">31</option>
                                <option value="32">32</option>
                                <option value="33">33</option>
                                <option value="34">34</option>
                                <option value="35">35</option>
                            </select>
                            <a href="javascript:void(0);" class="btn" data-click="random(beforeT)" id="link129">机选</a> <span class="c-slt-del" title="清除所选前区拖码" data-click="clear(beforeT)">清</span></div>
                        <div class="c-num">
                            <ul class="ball-orange clearfix j-b-t">
                                <li><b class="ball" data-num="01">01</b><em class="num-yl">2</em><em class="num-lr">0</em></li>
                                <li><b class="ball" data-num="02">02</b><em class="num-yl">9</em><em class="num-lr">0</em></li>
                                <li><b class="ball" data-num="03">03</b><em class="num-yl">2</em><em class="num-lr">0</em></li>
                                <li><b class="ball" data-num="04">04</b><em class="num-yl">0</em><em class="num-lr">0</em></li>
                                <li><b class="ball" data-num="05">05</b><em class="num-yl">4</em><em class="num-lr">0</em></li>
                                <li><b class="ball" data-num="06">06</b><em class="num-yl">5</em><em class="num-lr">0</em></li>
                                <li><b class="ball" data-num="07">07</b><em class="num-yl">7</em><em class="num-lr">0</em></li>
                                <li><b class="ball" data-num="08">08</b><em class="num-yl">8</em><em class="num-lr">0</em></li>
                                <li><b class="ball" data-num="09">09</b><em class="num-yl">6</em><em class="num-lr">0</em></li>
                                <li><b class="ball" data-num="10">10</b><em class="num-yl">0</em><em class="num-lr">0</em></li>
                                <li><b class="ball" data-num="11">11</b><em class="num-yl">4</em><em class="num-lr">0</em></li>
                                <li><b class="ball" data-num="12">12</b><em class="num-yl">1</em><em class="num-lr">0</em></li>
                                <li><b class="ball" data-num="13">13</b><em class="num-yl">0</em><em class="num-lr">0</em></li>
                                <li><b class="ball" data-num="14">14</b><em class="num-yl">12</em><em class="num-lr">0</em></li>
                                <li><b class="ball" data-num="15">15</b><em class="num-yl">8</em><em class="num-lr">0</em></li>
                                <li><b class="ball" data-num="16">16</b><em class="num-yl">4</em><em class="num-lr">0</em></li>
                                <li><b class="ball" data-num="17">17</b><em class="num-yl">16</em><em class="num-lr">0</em></li>
                                <li><b class="ball" data-num="18">18</b><em class="num-yl">3</em><em class="num-lr">0</em></li>
                                <li><b class="ball" data-num="19">19</b><em class="num-yl">1</em><em class="num-lr">0</em></li>
                                <li><b class="ball" data-num="20">20</b><em class="num-yl">1</em><em class="num-lr">0</em></li>
                                <li><b class="ball" data-num="21">21</b><em class="num-yl">6</em><em class="num-lr">0</em></li>
                                <li><b class="ball" data-num="22">22</b><em class="num-yl">1</em><em class="num-lr">0</em></li>
                                <li><b class="ball" data-num="23">23</b><em class="num-yl"><span style="color:red">41</span></em><em class="num-lr">0</em></li>
                                <li><b class="ball" data-num="24">24</b><em class="num-yl">9</em><em class="num-lr">0</em></li>
                                <li><b class="ball" data-num="25">25</b><em class="num-yl">15</em><em class="num-lr">0</em></li>
                                <li><b class="ball" data-num="26">26</b><em class="num-yl">3</em><em class="num-lr">0</em></li>
                                <li><b class="ball" data-num="27">27</b><em class="num-yl">4</em><em class="num-lr">0</em></li>
                                <li><b class="ball" data-num="28">28</b><em class="num-yl">0</em><em class="num-lr">0</em></li>
                                <li><b class="ball" data-num="29">29</b><em class="num-yl">14</em><em class="num-lr">0</em></li>
                                <li><b class="ball" data-num="30">30</b><em class="num-yl">6</em><em class="num-lr">0</em></li>
                                <li><b class="ball" data-num="31">31</b><em class="num-yl">2</em><em class="num-lr">0</em></li>
                                <li><b class="ball" data-num="32">32</b><em class="num-yl">2</em><em class="num-lr">0</em></li>
                                <li><b class="ball" data-num="33">33</b><em class="num-yl">0</em><em class="num-lr">0</em></li>
                                <li><b class="ball" data-num="34">34</b><em class="num-yl">2</em><em class="num-lr">0</em></li>
                                <li><b class="ball" data-num="35">35</b><em class="num-yl">3</em><em class="num-lr">0</em></li>
                            </ul>
                        </div>
                    </div>
                </div>
                <div class="c-select clearfix p-t">
                    <div class="c-s-r">
                        <div class="c-tit"><span class="r">我认为必出的号码</span>— <span class="blue f18">后区胆码</span> —　<span class="cl-gray2"> 至多选择<em class="red">1</em>个</span><span class="c-slt-del m-l20" title="清除所选后区胆码" data-click="clear(afterD)">清</span></div>
                        <div class="c-num">
                            <ul class="ball-blue clearfix  j-a-d">
                                <li><b class="ball" data-num="01">01</b><em class="num-yl">2</em><em class="num-lr">0</em></li>
                                <li><b class="ball" data-num="02">02</b><em class="num-yl">1</em><em class="num-lr">0</em></li>
                                <li><b class="ball" data-num="03">03</b><em class="num-yl">2</em><em class="num-lr">0</em></li>
                                <li><b class="ball" data-num="04">04</b><em class="num-yl">5</em><em class="num-lr">0</em></li>
                                <li><b class="ball" data-num="05">05</b><em class="num-yl">11</em><em class="num-lr">0</em></li>
                                <li><b class="ball" data-num="06">06</b><em class="num-yl">1</em><em class="num-lr">0</em></li>
                                <li><b class="ball" data-num="07">07</b><em class="num-yl">4</em><em class="num-lr">0</em></li>
                                <li><b class="ball" data-num="08">08</b><em class="num-yl">5</em><em class="num-lr">0</em></li>
                                <li><b class="ball" data-num="09">09</b><em class="num-yl">7</em><em class="num-lr">0</em></li>
                                <li><b class="ball" data-num="10">10</b><em class="num-yl"><span style="color:red">21</span></em><em class="num-lr">0</em></li>
                                <li><b class="ball" data-num="11">11</b><em class="num-yl">0</em><em class="num-lr">0</em></li>
                                <li><b class="ball" data-num="12">12</b><em class="num-yl">0</em><em class="num-lr">0</em></li>
                            </ul>
                        </div>
                    </div>
                </div>
                <div class="c-select clearfix p-t">
                    <div class="c-s-r">
                        <div class="c-tit"><span class="r">我认为可能出的号码</span>— <span class="blue f18">后区拖码</span> —　<span class="cl-gray2">至少选择<em class="red">2</em>个</span>
                            <select autocomplete="off" id="dt_tuo_opts" class="m-l20" data-change="set(afterRndSize, $value)">
                                <option value="1">1</option>
                                <option value="2">2</option>
                                <option value="3">3</option>
                                <option value="4">4</option>
                                <option value="5" selected="">5</option>
                                <option value="6">6</option>
                                <option value="7">7</option>
                                <option value="8">8</option>
                                <option value="9">9</option>
                                <option value="10">10</option>
                                <option value="11">11</option>
                                <option value="12">12</option>
                            </select>
                            <a href="javascript:void(0);" class="btn" data-click="random(afterT)" id="link130">机选</a> <span class="c-slt-del" title="清除所选后区拖码" data-click="clear(afterT)">清</span></div>
                        <div class="c-num">
                            <ul class="ball-blue clearfix  j-a-t">
                                <li><b class="ball" data-num="01">01</b><em class="num-yl">2</em><em class="num-lr">0</em></li>
                                <li><b class="ball" data-num="02">02</b><em class="num-yl">1</em><em class="num-lr">0</em></li>
                                <li><b class="ball" data-num="03">03</b><em class="num-yl">2</em><em class="num-lr">0</em></li>
                                <li><b class="ball" data-num="04">04</b><em class="num-yl">5</em><em class="num-lr">0</em></li>
                                <li><b class="ball" data-num="05">05</b><em class="num-yl">11</em><em class="num-lr">0</em></li>
                                <li><b class="ball" data-num="06">06</b><em class="num-yl">1</em><em class="num-lr">0</em></li>
                                <li><b class="ball" data-num="07">07</b><em class="num-yl">4</em><em class="num-lr">0</em></li>
                                <li><b class="ball" data-num="08">08</b><em class="num-yl">5</em><em class="num-lr">0</em></li>
                                <li><b class="ball" data-num="09">09</b><em class="num-yl">7</em><em class="num-lr">0</em></li>
                                <li><b class="ball" data-num="10">10</b><em class="num-yl"><span style="color:red">21</span></em><em class="num-lr">0</em></li>
                                <li><b class="ball" data-num="11">11</b><em class="num-yl">0</em><em class="num-lr">0</em></li>
                                <li><b class="ball" data-num="12">12</b><em class="num-yl">0</em><em class="num-lr">0</em></li>
                            </ul>
                        </div>
                    </div>
                </div>
                <div class="num-info m-t20">
                    <p class="cl-gray2 f14 m-b5">【前区：胆码 <em class="orange2">0</em> 个，拖码 <em class="orange2">0</em> 个；后区：胆码 <em class="blue">0</em> 个，拖码 <em class="blue">0</em> 个；共 <em class="red">0</em> 注，共 <em class="red">0.00</em> 元】</p>
                </div>
            </div>


            <!-- 定胆投注 -->
            <!--定胆杀号-->
            <div class="c-tab" id="ddsh" style="display:none;">
                <p class="f14 cl-gray2 m-b">同一号码点击一下为“定胆”、点击两下为“杀号”、点击三下“还原”</p>
                <div class="c-select clearfix">
                    <div class="c-s-l">
                        <div class="c-tit">— <span class="orange3 f18">前区</span> —　<span class="c-slt-del" title="清除所选前区号码" data-click="clear(before)">清</span></div>
                        <div class="c-num">
                            <ul class="ball-orange clearfix">
                                <li><b class="ball" data-num="01">01</b><em class="num-yl">2</em><em class="num-lr">0</em></li>
                                <li><b class="ball" data-num="02">02</b><em class="num-yl">9</em><em class="num-lr">0</em></li>
                                <li><b class="ball" data-num="03">03</b><em class="num-yl">2</em><em class="num-lr">0</em></li>
                                <li><b class="ball" data-num="04">04</b><em class="num-yl">0</em><em class="num-lr">0</em></li>
                                <li><b class="ball" data-num="05">05</b><em class="num-yl">4</em><em class="num-lr">0</em></li>
                                <li><b class="ball" data-num="06">06</b><em class="num-yl">5</em><em class="num-lr">0</em></li>
                                <li><b class="ball" data-num="07">07</b><em class="num-yl">7</em><em class="num-lr">0</em></li>
                                <li><b class="ball" data-num="08">08</b><em class="num-yl">8</em><em class="num-lr">0</em></li>
                                <li><b class="ball" data-num="09">09</b><em class="num-yl">6</em><em class="num-lr">0</em></li>
                                <li><b class="ball" data-num="10">10</b><em class="num-yl">0</em><em class="num-lr">0</em></li>
                                <li><b class="ball" data-num="11">11</b><em class="num-yl">4</em><em class="num-lr">0</em></li>
                                <li><b class="ball" data-num="12">12</b><em class="num-yl">1</em><em class="num-lr">0</em></li>
                                <li><b class="ball" data-num="13">13</b><em class="num-yl">0</em><em class="num-lr">0</em></li>
                                <li><b class="ball" data-num="14">14</b><em class="num-yl">12</em><em class="num-lr">0</em></li>
                                <li><b class="ball" data-num="15">15</b><em class="num-yl">8</em><em class="num-lr">0</em></li>
                                <li><b class="ball" data-num="16">16</b><em class="num-yl">4</em><em class="num-lr">0</em></li>
                                <li><b class="ball" data-num="17">17</b><em class="num-yl">16</em><em class="num-lr">0</em></li>
                                <li><b class="ball" data-num="18">18</b><em class="num-yl">3</em><em class="num-lr">0</em></li>
                                <li><b class="ball" data-num="19">19</b><em class="num-yl">1</em><em class="num-lr">0</em></li>
                                <li><b class="ball" data-num="20">20</b><em class="num-yl">1</em><em class="num-lr">0</em></li>
                                <li><b class="ball" data-num="21">21</b><em class="num-yl">6</em><em class="num-lr">0</em></li>
                                <li><b class="ball" data-num="22">22</b><em class="num-yl">1</em><em class="num-lr">0</em></li>
                                <li><b class="ball" data-num="23">23</b><em class="num-yl"><span style="color:red">41</span></em><em class="num-lr">0</em></li>
                                <li><b class="ball" data-num="24">24</b><em class="num-yl">9</em><em class="num-lr">0</em></li>
                                <li><b class="ball" data-num="25">25</b><em class="num-yl">15</em><em class="num-lr">0</em></li>
                                <li><b class="ball" data-num="26">26</b><em class="num-yl">3</em><em class="num-lr">0</em></li>
                                <li><b class="ball" data-num="27">27</b><em class="num-yl">4</em><em class="num-lr">0</em></li>
                                <li><b class="ball" data-num="28">28</b><em class="num-yl">0</em><em class="num-lr">0</em></li>
                                <li><b class="ball" data-num="29">29</b><em class="num-yl">14</em><em class="num-lr">0</em></li>
                                <li><b class="ball" data-num="30">30</b><em class="num-yl">6</em><em class="num-lr">0</em></li>
                                <li><b class="ball" data-num="31">31</b><em class="num-yl">2</em><em class="num-lr">0</em></li>
                                <li><b class="ball" data-num="32">32</b><em class="num-yl">2</em><em class="num-lr">0</em></li>
                                <li><b class="ball" data-num="33">33</b><em class="num-yl">0</em><em class="num-lr">0</em></li>
                                <li><b class="ball" data-num="34">34</b><em class="num-yl">2</em><em class="num-lr">0</em></li>
                                <li><b class="ball" data-num="35">35</b><em class="num-yl">3</em><em class="num-lr">0</em></li>
                            </ul>
                        </div>
                    </div>
                    <div class="c-s-r">
                        <div class="c-tit">— <span class="blue f18">后区</span> —　<span class="c-slt-del" data-click="clear(after)">清</span></div>
                        <div class="c-num">
                            <ul class="ball-blue clearfix">
                                <li><b class="ball" data-num="01">01</b><em class="num-yl">2</em><em class="num-lr">0</em></li>
                                <li><b class="ball" data-num="02">02</b><em class="num-yl">1</em><em class="num-lr">0</em></li>
                                <li><b class="ball" data-num="03">03</b><em class="num-yl">2</em><em class="num-lr">0</em></li>
                                <li><b class="ball" data-num="04">04</b><em class="num-yl">5</em><em class="num-lr">0</em></li>
                                <li><b class="ball" data-num="05">05</b><em class="num-yl">11</em><em class="num-lr">0</em></li>
                                <li><b class="ball" data-num="06">06</b><em class="num-yl">1</em><em class="num-lr">0</em></li>
                                <li><b class="ball" data-num="07">07</b><em class="num-yl">4</em><em class="num-lr">0</em></li>
                                <li><b class="ball" data-num="08">08</b><em class="num-yl">5</em><em class="num-lr">0</em></li>
                                <li><b class="ball" data-num="09">09</b><em class="num-yl">7</em><em class="num-lr">0</em></li>
                                <li><b class="ball" data-num="10">10</b><em class="num-yl"><span style="color:red">21</span></em><em class="num-lr">0</em></li>
                                <li><b class="ball" data-num="11">11</b><em class="num-yl">0</em><em class="num-lr">0</em></li>
                                <li><b class="ball" data-num="12">12</b><em class="num-yl">0</em><em class="num-lr">0</em></li>
                            </ul>
                        </div>
                    </div>
                    <div class="c-jx clear"><span class="cl-gray2">机选</span>
                        <select autocomplete="off" name="sele" id="before_dd_rnd_list">
                            <option value="5" selected="">5</option>
                            <option value="6">6</option>
                            <option value="7">7</option>
                            <option value="8">8</option>
                            <option value="9">9</option>
                            <option value="10">10</option>
                            <option value="11">11</option>
                            <option value="12">12</option>
                            <option value="13">13</option>
                            <option value="14">14</option>
                            <option value="15">15</option>
                            <option value="16">16</option>
                            <option value="17">17</option>
                            <option value="18">18</option>
                        </select>
                        <span class="orange2">前区号码</span>
                        <select autocomplete="off" name="sele" id="after_dd_rnd_list">
                            <option value="2" selected="">2</option>
                            <option value="3">3</option>
                            <option value="4">4</option>
                            <option value="5">5</option>
                            <option value="6">6</option>
                            <option value="7">7</option>
                            <option value="8">8</option>
                            <option value="9">9</option>
                            <option value="10">10</option>
                            <option value="11">11</option>
                            <option value="12">12</option>
                        </select>
                        <span class="blue">后区号码</span>
                        <select autocomplete="off" id="dd_group_opts" name="sele">
                            <option value="1">1</option>
                            <option value="2">2</option>
                            <option value="5">5</option>
                            <option value="10">10</option>
                            <option value="20">20</option>
                            <option value="50">50</option>
                            <option value="100">100</option>
                        </select>
                        <span class="cl-gray2">组号码</span> </div>
                    <div class="c-s-filter">
                        <p class="filter-cur"> <span class="filter-yl">遗漏 <i class="ico"></i> </span> <span class="filter-yl-tips">指此号码自上次开出至本期未出现的期数<i class="ico"></i></span> </p>
                        <p> <span class="filter-lr">冷热 <i class="ico"></i> </span> <span class="filter-lr-tips">指此号码近100期出现次数<i class="ico"></i></span> </p>
                    </div>
                </div>
                <div class="num-info" style="display: none;">
                    <p class="cl-gray2 f14 m-b5">您选择了 <em class="orange2">2</em> 个前区号码， <em class="blue">0</em> 个后区号码，共 <em class="red">0</em> 注，共 <em class="red">0.00</em> 元</p>
                </div>
            </div>


            <!-- 号码框 -->
            <div class="c-result clearfix center">
                <div class="c-rst-ok center tcenter">
                    <a class="btn-ok btn btn-primary btn-lg active" data-toggle="tooltip" data-placement="top" title="前区至少5个,后区至少2个"
                       role="button" aria-pressed="true" href="javascript:void(0);">选好了</a>
                </div>
                <div class="c-rst-l fleft">
                    <div id="notelist" style="height:200px;overflow-y: scroll;">
                        <table border="0" width="100%" cellspacing="0" cellpadding="0" class="tcenter">
                            <tbody id="notesTable">

                            </tbody>
                        </table>
                    </div>
                    <#--<div class="list-info">-->
                        <#--<p class="list-info-zj"><span>追加 1 元，单注奖金可达1800万</span>-->
                            <#--<label>-->
                                <#--<input type="checkbox" onchange="getPrice()" id="zhuijia"/>-->
                                <#--全部追加</label>-->
                        <#--</p>-->
                    <#--</div>-->
                </div>
                <div class="c-rst-r fleft">
                    <a class="btn btn-outline-secondary" style="margin-bottom: 15px;width: 50%;" title="点击机选1注" href="javascript:void 0" onclick="randomNotes(1)" id="link132">机选1注</a><br/>
                    <a class="btn btn-outline-secondary" style="margin-bottom: 15px;width: 50%;" title="点击机选5注" href="javascript:void 0" onclick="randomNotes(5)" id="link133">机选5注</a><br/>
                    <a class="btn btn-outline-secondary" style="margin-bottom: 15px;width: 50%;" title="点击清空列表" href="javascript:void 0" onclick="clearNotes()" id="link135">清空列表</a>

                </div>
                <div class="c-rst-info fleft center tcenter" style="margin-top: 40px;width: 100%;">
                    <div class="info-money">
                        <div class="" >
                            <span class="numsel" style="width: auto;">
                                倍数
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
                        </div>
                    </div>
                    <div style="margin-top: 30px;">
                        <a href="javascript:void(0);" data-toggle="tooltip" data-placement="top" title="至少选择一注号码"
                           role="button" class="bet-ok btn btn-primary btn-lg active" data-click="start" id="saveNote">保存方案</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="/js/common.js"></script>
<script type="text/javascript">
    let fonts = [];
    let backs = [];
    $('.tab-l').on('click','.num-li',function () {
        // let font = {};
        $(this).toggleClass('l-num-selected');
        if($(this).hasClass('l-num-selected')){
            fonts.push($(this).find('.ball').attr('data-num'));
        }else {
           fonts.splice(fonts.indexOf($(this).find('.ball').attr('data-num')),1);
        }
        console.log(fonts);
        $(".num-info .fonts").html(fonts.length);
        calculateNotes();
    });

    $('.tab-r').on('click','.num-li',function () {
        // let font = {};
        $(this).toggleClass('r-num-selected');
        if($(this).hasClass('r-num-selected')){
            backs.push($(this).find('.ball').attr('data-num'));
        }else {
            backs.splice(fonts.indexOf($(this).find('.ball').attr('data-num')),1);
        }
        console.log(backs);
        $(".num-info .backs").html(backs.length);
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

    let noteList = [];
    let totalnotes = 0;
    let totalprice = 0.00;

    //选好了
    $('.btn-ok').on('click',function () {
        if(fonts.length < 5 || backs.length < 2){
            $(this).tooltip('show');
            setTimeout(function () {
                $(this).tooltip('hide');
            },2000);
            return;
        }
        //加入已选table中
        let note = {};
        note.fonts = fonts;
        note.backs = backs;
        note.notes = $('.notes').html();
        note.price = $('.price').html();
        noteList.push(note);
        let noteItem = $("<tr class='note-item'><td style='width: 50px;'><a href='javascript:void(0);' class='deleteNote'> x </a></td><td>" + fonts + "</td><td>" + backs +"</td><td>" + $('.notes').html() + "注</td><td>" + $('.price').html() + "元</td></tr>");
        $('#notesTable').append(noteItem);

        totalnotes += parseInt($('.notes').html());
        totalprice += parseFloat($('.price').html());
        $('.t-price').html(formatMoney(totalprice));
        $('.t-notes').html(totalnotes);
        fonts = [];
        backs = [];
        $('.tab-l .num-li').removeClass('l-num-selected');
        $('.tab-r .num-li').removeClass('r-num-selected');
        $('.fonts').html(0);
        $('.backs').html(0);
        $('.notes').html(0);
        $('.price').html(0.00);
    });

    //保存方案
    $('.bet-ok').on('click',function () {
       if($('#notesTable').find('.note-item').length === 0){
           $(this).tooltip('show');
           setTimeout(function () {
               $(this).tooltip('hide');
           },2000);
           return;
       }
       $.ajax({
           url:'doBet',
           type:'post',
           dataType:'json',
           data:{lotteryType: 2,lotteryName:'大乐透',detail:JSON.stringify(noteList),total:$('.t-price').html()},
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

    function getPrice() {
        console.log($('#zhuijia').prop('checked'));
        let checked = $('#zhuijia').prop('checked');
        if(!checked){
            $('.t-price').html(formatMoney(parseInt($('#buy_bs').val())*totalprice));
        }else {
            $('.t-price').html(formatMoney(parseInt($('#buy_bs').val())*3*parseInt($('.t-notes').html())));
        }
    }
</script>
</body>
</html>