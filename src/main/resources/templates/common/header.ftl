<div class="navbar navbar-expand-lg fixed-top navbar-dark bg-dark header">
    <div class="container">
        <a href="#" class="navbar-brand">万艾彩票</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link" href="/">首页</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/news/footNews">体彩</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/news/letou">大乐透</a>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#" id="download">Dropdown <span class="caret"></span></a>
                    <div class="dropdown-menu" aria-labelledby="download">
                        <a class="dropdown-item" href="#">Action</a>
                        <a class="dropdown-item" href="#">Another action</a>
                        <a class="dropdown-item" href="#">Something else here</a>
                        <div class="dropdown-divider"></div>
                        <a class="dropdown-item" href="#">Separated link</a>
                        <a class="dropdown-item" href="#">One more separated link</a>
                    </div>
                </li>
            </ul>
            <@shiro.guest>
                <ul class="nav navbar-nav ml-auto">
                    <li class="nav-item">
                        <a href="/user/toLogin" target="_self" class="nav-link"><i class="fa fa-user-o"></i> 请登录</a>
                    </li>
                    <li class="nav-item">
                        <a href="/user/register/toRegister" target="_blank" class="nav-link"><i class="fa fa-pencil-square-o"></i> 免费注册</a>
                    </li>
                </ul>
            </@shiro.guest>

            <@shiro.user>
                <ul class="nav navbar-nav ml-auto">
                    <li class="nav-item">
                        <a href="/userCenter/index" target="_self" class="nav-link"><i class="fa fa-user-o"></i> <@shiro.principal property="uname"/></a>
                    </li>
                    <li class="nav-item">
                        <a href="/user/logout" class="nav-link"><i class="fa fa-pencil-square-o"></i> 退出</a>
                    </li>
                </ul>
            </@shiro.user>
        </div>
    </div>
</div>