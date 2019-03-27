<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>注册会员-万艾</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <link rel="stylesheet" href="/font-awesome-4.7.0/css/font-awesome.min.css" type="text/css"/>
    <style type="text/css">
        .header{height: 100px;box-shadow: 0 10px 20px 0 #f1f1f1;}
        .clearfix:after{content: "";display: table;clear: both;}
        .wrapper{margin-top: 30px;}
        h3{font-size: 15px;}
        .footer{    border-top: 1px solid #e8e8e8;
            padding-bottom: 10px;
            color: #666;
            margin-top: 20px;
            padding-top: 6px;
            font-size: 12px;
            background: #fff;
            width: auto;
            /*margin: 0 auto;*/
            font-family: Microsoft YaHei;
            text-align: center;}
        .inner{width:1080px;margin: 0 auto;padding: 30px 50px;}
        .reg-con{min-height: 480px;height: auto;}
        .step-con-box{width:440px;margin: 0 auto;}
        .hide{display: none!important;}
        .steps{margin: 0 auto;padding: 40px 0;width: 415px;}
        ul,ol,li{list-style: none;}
        .steps li{float:left;vertical-align: middle;}
        .step-line{width:186px;display: inline-block;height: 4px;margin-top:7px;background-color: #e5e5e5;}
        .overline{background-color: blue;}
        .step-item{position: relative;}
        /*.step1 li:nth-child(1) .step-title,.*/
        .step-title{color:#333;}
        .step-title{position:absolute;top:-40px;width:100px;text-align: center;
            left: 50%;margin-left: -50px;}
        .step-point{
            display: inline-block;
            width: 14px;
            height: 14px;
            border-radius: 50%;
            overflow: hidden;
            background-color: #e5e5e5;
            position: relative;
        }
        .step-point-after{
            position: absolute;
            left: 12%;
            top: 14%;
            margin-left: -5px;
            margin-top: -4px;
            display: inline-block;
            width: 20px;
            height: 20px;
            border-radius: 50%;
            background: blue;
        }
        .field-group {
            margin-bottom: 30px;
            position: relative;
        }
        .radius {
            border-radius: 3px;
        }
        .field input[type=number], .field input[type=search], .field input[type=text], .field input[type=password], .field input[type=datetime], .field input[type=datetime-local], .field input[type=date], .field input[type=month], .field input[type=time], .field input[type=week], .field input[type=email], .field input[type=url], .field input[type=tel], .field input[type=color], .field select, .field textarea, .field-input {
            box-sizing: border-box;
            display: block;
            width: 100%;
            padding: 20px 30px;
            font-size: 14px;
            line-height: 1.25;
            color: #555;
            vertical-align: middle;
            background-color: #fff;
            background-image: none;
            border: 1px solid #ddd;
            border-radius: 0;
            -webkit-appearance: none;
            -webkit-transition: border-color .15s ease-in-out, -webkit-box-shadow .15s ease-in-out;
            transition: border-color .15s ease-in-out, -webkit-box-shadow .15s ease-in-out;
            transition: border-color .15s ease-in-out, box-shadow .15s ease-in-out;
            transition: border-color .15s ease-in-out, box-shadow .15s ease-in-out, -webkit-box-shadow .15s ease-in-out;
            font-family: "microsoft yahei", sans-serif;
            outline: none;
        }
        .field-error{
            position: absolute;
            left: 100%;
            top: 0;
            height: 59px;
            line-height: 59px;
            max-width: 300px;
            min-width: 100px;
            color: #ff3f13;
            font-size: 12px;
            padding-left: 26px;
            white-space: nowrap;
        }
        .btn.disabled, .btn[disabled], fieldset[disabled] .btn {
            pointer-events: none;
            cursor: not-allowed;
            background-image: none;
            -webkit-box-shadow: none;
            box-shadow: none;
            opacity: 0.5;
        }
        a.btn {
            text-decoration: none;
        }
        .block {
            display: block !important;
            padding-left: 0;
            padding-right: 0;
            width: 100%;
            text-align: center;
        }
        .btn-primary {
            color: #fff;
            background-color: #68A4ED;
            border-color: #68A4ED;
        }
        .btn {
            border: 1px solid transparent;
            border-radius: 0;
            box-sizing: border-box;
            background-image: none;
            font-family: "microsoft yahei", sans-serif;
            display: inline-block;
            margin-bottom: 0;
            font-size: 20px;
            font-weight: 400;
            padding: 20px 20px;
            line-height: 1;
            text-align: center;
            white-space: nowrap;
            cursor: pointer;
            outline: 0;
            -webkit-appearance: none;
            -webkit-user-select: none;
            -moz-user-select: none;
            -ms-user-select: none;
            user-select: none;
            -webkit-transition: background-color .3s ease-out, border-color .3s ease-out;
            transition: background-color .3s ease-out, border-color .3s ease-out;
        }


        .btn-vcode {
            position: absolute;
            right: 1px;
            top: 1px;
            bottom: 1px;
            padding: 0 !important;
            width: 116px;
            vertical-align: middle;
            border-left: 1px solid #ddd;
            background: #f5f5f5;
            font-size: 14px !important;
        }
        .btn-vcode:focus{box-shadow: none;}
        .show_pwd{
            width: 30px;
            height: 30px;
            position: absolute;
            top: 15px;
            right: 10px;
            background-position: -133px 5px;
            cursor: pointer;
            color:#8c8c8c;
            line-height: 30px;
        }
        .user-info li {
            font-size: 18px;
            padding: 10px 0;
        }
        .user-info label {
            color: #aaaaaa;
        }
        .btn-light {
            color: #616161;
            background-color: #fff;
            border-color: #ccc;
        }
        .tips{width: 100%;font-size: 12px;text-align: center;display: inline-block;}
        #login_span{text-align: right;}
    </style>
</head>
<body>
    <div class="header clearfix">

    </div>
    <div class="wrapper clearfix">
        <div class="inner">
            <div class="reg-con clearfix">
                <div class="step-box">
                    <ul class="steps clearfix">
                        <li class="li1">
                            <div class="step-item">
                                <h3 class="step-title">验证手机</h3>
                                <span class="step-point"></span>
                                <span class="step-point-after"></span>
                            </div>
                        </li>
                        <li class="step-line li2"></li>
                        <li class="li3">
                            <div class="step-item">
                                <h3 class="step-title">填写账户信息</h3>
                                <span class="step-point"></span>
                                <span class="step-point-after hide"></span>
                            </div>
                        </li>
                        <li class="step-line li4"></li>
                        <li class="li5">
                            <div class="step-item">
                                <h3 class="step-title">完成注册</h3>
                                <span class="step-point"></span>
                                <span class="step-point-after hide"></span>
                            </div>
                        </li>

                    </ul>

                </div>
                <div class="step-con-box" id="step1">
                    <form action class="reg-form">
                        <div class="field-group">
                            <input type="tel" maxlength="13" class="field-input radius" name="phone" id="phone" placeholder="手机号"/>
                            <input type="hidden" id="truephone"/>
                            <div class="field-error"></div>
                        </div>
                        <div class="field-group" id="vcode">
                            <input type="text" disabled="true" class="field-input vcode-ipt radius" maxlength="6" id="phonecode" placeholder="输入6位短信验证码">
                            <button type="button" class="btn btn-vcode disabled">获取验证码</button>
                            <div class="field-error"></div>
                        </div>
                        <div class="field-group">
                            <a href="javascript:void(0);" class="btn btn-primary radius block disabled js-step1">下一步</a>
                        </div>
                    </form>
                </div>
                <div class="step-con-box hide" id="step2">
                    <form action class="reg-form">
                        <div class="field-group">
                            <input type="text" minlength="4" maxlength="16" class="field-input radius" name="username" id="username" placeholder="4~16位用户名"/>
                            <div class="field-error"></div>
                        </div>
                        <div class="field-group">
                            <input type="password" minlength="6" maxlength="20" class="field-input radius" name="pwd" id="pwd" placeholder="设置6~20位密码"/>
                            <i class="fa fa-eye show_pwd"></i>
                            <div class="field-error"></div>
                        </div>
                        <div class="field-group">
                            <input type="password" minlength="6" maxlength="20" class="field-input radius" name="confirmpwd" id="confirmpwd" placeholder="确认密码"/>
                            <i class="fa fa-eye show_pwd"></i>
                            <div class="field-error"></div>
                        </div>
                        <div class="field-group">
                            <input type="hidden" id="uid"/>
                            <a href="javascript:void(0);" class="btn btn-primary radius block js-step2">下一步</a>
                        </div>
                    </form>
                </div>
                <div class="step-con-box hide" id="step3">
                    <form action class="reg-form">
                        <div class="field-group">
                            <span class="tips">注册完成,您可以使用账户用户名或手机号登录</span>
                            <ul class="user-info">
                                <li>
                                    <label for="g_uname">用户名：</label>
                                    <span id="g_uname"></span>
                                </li>
                                <li>
                                    <label for="g_phone">手机：</label>
                                    <span id="g_phone"></span>
                                </li>
                            </ul>
                        </div>
                        <div class="field-group">
                            <a href="javascript:void(0)" class="btn btn-light radius block" id="finishReg">完成注册</a>
                        </div>
                    </form>
                </div>
                <div class="step-con-box" id="login_span">
                    <span>已有账号,</span>
                    <a href="/user/toLogin">去登录></a>
                </div>
            </div>
        </div>
    </div>
    <div class="footer"></div>
    <script type="text/javascript" src="/js/jquery.min.js"></script>
    <script type="text/javascript">
        //格式化手机号
        $("#phone").bind("input propertychange",function(){
            var val = $(this).val();
            var phoneArr = ["",""];
            if (val.length >= 3 || val.length >= 7 || val.length >= 11) {
                phoneArr = formatPhone(val);
                $(this).val(phoneArr[1]);
            }
            if(phoneArr[0].length == 11){
                $("#truephone").val(phoneArr[0]);
                $(".btn-vcode").removeClass("disabled");

            }else {
                $(".btn-vcode").addClass("disabled");
            }
        });

        //输入验证码
        $("#phonecode").bind("input propertychange",function(){
            if ($(this).val().length == 6) {
                $(".js-step1").removeClass("disabled");
            }else {
                $(".js-step1").addClass("disabled");
            }
        });

        //验证手机后点击下一步
        $(".js-step1").on("click",function () {
           var phone = $("#truephone").val();
           var code = $("#phonecode").val();
           //验证码是否正确判断
            $.ajax({
                url:"/user/register/ajaxVerifyCode",
                type:"post",
                dataType:"json",
                data:{phone:phone,verifyCode:code},
                success:function (data) {
                    if(data.result != 1){
                        showErrTip($("#phonecode"),"fail",data.message);
                        return;
                    }
                    //进入第二步
                    $(".li2").addClass("overline");
                    $(".li3").find(".step-point-after").removeClass("hide");
                    $("#uid").val(data.uid);
                    $("#step1").addClass("hide");
                    $("#step2").removeClass("hide");
                    $("#username").focus();
                }
            })
        });

        //实时监测用户名是否可用
        $("#username").on("input propertychange",function () {
            var obj = $(this).siblings(".field-error");
            var name = $(this).val().replace(/ /g,"");
            if(name.length < 4){
                return;
            }
            $.ajax({
                url:"/user/register/ajaxUsername",
                type:"get",
                dataType:"json",
                data:"name="+name,
                beforeSend:function(){
                    obj.html("<i class='fa fa-spinner fa-pulse' style='color:#333;font-size:18px;'></i>");
                },
                success:function (data) {
                    setTimeout(function () {
                        if(data.result != 1){
                            obj.html("<i class='fa fa-ban'></i> " + data.msg);
                            obj.css("color","#ff3f13");
                        }else {
                            obj.html("<i class='fa fa-check'></i> " + data.msg);
                            obj.css("color","#333");
                        }
                    },1000);
                }
            })
        });
        // 密码框显示隐藏输入
        $(".show_pwd").on("click",function () {
            if($(this).hasClass("fa-eye")){
                $(this).removeClass("fa-eye");
                $(this).addClass("fa-eye-slash");
                $(this).prev("input").attr("type","text");
                // return;
            }else {
                $(this).addClass("fa-eye");
                $(this).removeClass("fa-eye-slash");
                $(this).prev("input").attr("type","password");
            }
        });

        var reg = new RegExp(/ /g);
        //点击填写信息下一步
        $(".js-step2").on("click",function () {
            var regform = $(this).closest(".reg-form");
            regform.find(".field-error").each(function () {
                if($(this).html()!=""){
                    return;
                }
            })
            showErrTip($("#username"),0);
            showErrTip($("#confirmpwd"),0);

            if($("#username").val().replace(/ /g,"") == ""){
               showErrTip($("#username"),"fail","此项为必填");
               return;
            }
            if($("#username").val().replace(/ /g,"").length < 4){
                showErrTip($("#username"),"fail","长度为4~16");
                return;
            }
            if($("#pwd").val().replace(/ /g,"")==""){
                showErrTip($("#confirmpwd"),"fail","请设置密码");
                return;
            }
            if($("#pwd").val().replace(/ /g,"").length < 6){
                showErrTip($("#confirmpwd"),"fail","长度为6~20");
                return;
            }
            if($("#confirmpwd").val().replace(/ /g,"")==""){
                showErrTip($("#confirmpwd"),"fail","请确认密码");
                return;
            }
            if($("#pwd").val().replace(/ /g,"")!=$("#confirmpwd").val().replace(/ /g,"")){
                showErrTip($("#confirmpwd"),"fail","密码不一致");
                return;
            }
            $(this).addClass("disabled");
            $.ajax({
                url:"/user/register/updateUserInfo",
                type:"post",
                dataType:"json",
                data:{uid:$("#uid").val(),uname:$("#username").val().replace(/ /g,""),pwd:$("#pwd").val().replace(/ /g,"")},
                error:function () {
                    $(".js-step2").removeClass("disabled");
                    alert("保存信息出错，请重试");
                },
                success:function (data) {
                    if(data.result != 1){
                        $(".js-step2").removeClass("disabled");
                        alert("保存信息出错，请重试");
                        return;
                    }
                    //注册第三步
                    $(".li4").addClass("overline");
                    $(".li5").find(".step-point-after").removeClass("hide");
                    $("#g_phone").html($("#truephone").val());
                    $("#g_uname").html(data.uname);
                    $("#step2").addClass("hide");
                    $("#step3").removeClass("hide");
                }
            });



        });

        //格式化手机号
        function formatPhone(phone) {
            phone = phone.replace(/[\s\xA0]+$/, "");
            var truephone = phone.replace(/[^\d]/g, ''), len = truephone.length, restr = '';
            if (len > 0) {
                for (var i = 0; i < len; i++) {
                    restr += truephone[i];
                    if (i == 2 || i == 6) restr += ' ';
                }
            }
            if (phone + " " == restr) {
                return [truephone, phone.replace(/[\s\n\t]+$/g, "")];
            }
            console.log([truephone,restr]);
            return [truephone, restr];
        };

        //获取手机验证码
        $(".btn-vcode").on('click', function () {
            //验证手机号是否已注册
            var phone = $("#truephone").val();
            $.ajax({
                url: '/user/register/ajaxCheckPhone',
                type: 'post',
                dataType: "json",
                data: {"phone": phone},
                success: function (data) {
                    if (data.result != 1) {
                        showErrTip($('#phone'), 'fail', data.message);
                    } else {
                        //发送手机验证码
                        sendMobile(phone);
                    }
                }
            });
        });

        //发送手机验证码
        function sendMobile(phone){
            Countdown($(".btn-vcode"));
            $.ajax({
                url:"/user/register/sendVerifyCode",
                type:"post",
                dataType:"json",
                data:{"phone":phone},
                success:function (data) {
                    $("#phonecode").attr("disabled",false);
                    if(data.result != 1){
                        showErrTip($('.btn-vcode'),'fail',data.message);
                    }else{
                        showErrTip($('.btn-vcode'),'suc',data.message);
                        // $("#phonecode").attr("disabled",false);
                    }
                }
            });
        }

        //错误提示
        function showErrTip(obj,status,msg){
            var parent = obj.closest(".field-group");
            var msg = msg || '';
            var tipmsg = '';
            if(status == 'suc'){
                tipmsg = '<i class="fa fa-check"></i> ' + msg;
            }
            if(status == 'fail'){
                tipmsg = '<i class="fa fa-ban"></i> ' + msg;
            }
            if(status == 0){
                tipmsg = '';
            }
            parent.find(".field-error").html(tipmsg);
        }

        //发送验证码倒计时
        function Countdown (obj) {
            obj.addClass("disabled");
            var count = 60;
            var timer = setInterval(function () {
                if (count != 1) {
                    count--;
                    obj.html(count+'秒后可重新发送');
                } else {
                    clearInterval(timer);
                    obj.html('重新获取');
                    obj.removeClass("disabled");
                }
            }, 1000);
        }
    </script>
</body>
</html>