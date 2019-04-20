<div id="loginDiv" class="center hide" style="position: absolute;">
    <div class="form">
        <p style="font-size: 18px;font-weight: bold;text-align: center;">会员登录</p>
        <p style="margin-left: 25px;color: greenyellow;">${errmsg!}</p>
        <form id="loginform" method="post" action="/user/doLogin">
            <div class="form-item">
                <i class="fa fa-user"></i>
                <input type="text" name="username" placeholder="用户名/手机号"/>
            </div>
            <div class="form-item">
                <i class="fa fa-key"></i>
                <input type="password" name="password"/>
            </div>
            <p class="fleft" style="height: 20px;line-height: 20px;">
                <label for="rememberMe">
                    <input type="checkbox" id="rememberMe" name="rememberMe" value="1" checked="checked" style="width:15px;height: 15px;"/>
                    <span>记住我</span>
                </label>
            </p>
            <p style="text-align: right;"><a href="#" id="forgot">忘记密码?</a></p>
            <div class="form-item">
                <button class="fluid ui button orange">登 录</button>
            </div>
            <p style="text-align: right;margin-top: 10px;"><a href="/user/register/toRegister">免费注册</a></p>
        </form>
    </div>
</div>