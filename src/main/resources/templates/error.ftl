<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>ErrorTip</title>
    <link rel="stylesheet" href="/bootstrap/bootstrap.min.css">
    <link rel="stylesheet" href="/font-awesome-4.7.0/css/font-awesome.css"/>
    <link rel="stylesheet" href="/css/common.css"/>
    <script type="text/javascript" src="/js/jquery.min.js"></script>
    <script src="/bootstrap/bootstrap.min.js" type="text/javascript"></script>
    <style>
        p{font-size:20px;}
        #error{text-decoration: underline;}
    </style>
</head>
<body>
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
        </div>
    </div>
</div>
   <div class="main container" style="font-weight: bold;">
       <p>系统错误</p>
       <p><a href="/" id="error" style="color: #ff0000;">点击此处</a>返回首页</p>
   </div>
</body>
</html>