<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>${title!}</title>
    <link rel="stylesheet" href="/bootstrap/bootstrap.min.css">
    <link rel="stylesheet" href="/font-awesome-4.7.0/css/font-awesome.css"/>
    <link type="text/css" rel="stylesheet" href="/css/common.css"/>
    <script type="text/javascript" src="/js/jquery.min.js"></script>
    <script type="text/javascript" src="/bootstrap/bootstrap.min.js"></script>
    <style type="text/css">

    </style>
</head>
<body>
    <#include "common/header.ftl" encoding="utf-8" parse=true>
    <div class="main"></div>
    <#--<#include "common/footer.ftl" encoding="utf-8" parse=true>-->

    前区:<input type="text" id="sp-font"/>
    后区:<input type="text" id="sp-back"/>
<br/>
命中：
前区：<input type="text" id="mz-f"/>
后区：<input type="text" id="mz-b"/>
<button class="btn btn-primary" id="cal">计算</button>

    <p id="winning"></p>
<script type="text/javascript">
    $('#cal').on('click',function () {
        fReqHit = 0;
        fOptHit = 0;
        bReqHit = 0;
        bOptHit = 0;

            fOptHit = + $('#mz-f').val();


            bOptHit = + $('#mz-b').val();

        win();
    });
let fNum = 5,
    bNum = 2,
    fMax = 35,
    bMax = 12,
    winners = [
        [[5, 2]],
        [[5, 1]],
        [[5, 0]],
        [[4, 2]],
        [[4, 1]],
        [[3, 2]],
        [[4, 0]],
        [[3, 1], [2, 2]],
        [[3, 0], [1, 2], [2, 1], [0, 2]]
    ],
    winningsList = [
        "A",
        "B",
        10000,
        3000,
        300,
        200,
        100,
        15,
        5
    ];
let fReq = 0,
    fOpt = 0,
    bReq = 0,
    bOpt = 0,
    fReqHit = 0,
    fOptHit = 0,
    bReqHit = 0,
    bOptHit = 0;
    function win() {
        fReq = 0;
        fOpt = 0;
        bReq = 0;
        bOpt = 0;

        fOpt = + $('#sp-font').val();
        bOpt = + $('#sp-back').val();

        var fHits = solveHits(fNum, fReq, fOpt, fReqHit, fOptHit),
            bHits = solveHits(bNum, bReq, bOpt, bReqHit, bOptHit),
            result = [];
        for (var i = 0; i < winners.length; ++ i) {
            var winner = winners[i],
                count = 0;
            for (var j = 0; j < winner.length; ++ j) {
                var item = winner[j];
                count += fHits[item[0]] * bHits[item[1]];
            }
            result[i] = count;
        }
        display(result);
    }

    function display(result) {
        var strs = [],
            total = 0,
            index = 4;


        result.forEach(function(t,i){
            var count = t;

            if (count > 0) {
                var value = winningsList[i];
                if (typeof value == "string") {
                    strs.push((count == 1 ? "" : count + "*") + value);
                } else {
                    total += count * value;
                }
            }
        });

        if (total > 0) {
            strs.push(total);
        }
        var str = strs.join(" + ") || "0";
        $('#winning').html(str);
    }

    // 计算前区或后区命中指定个数的方案注数
    function solveHits(num, req, opt, reqHit, optHit) {
        var optLeft = num - req,
            optMiss = opt - optHit,
            max = reqHit + optHit,
            hits = [];
        for (var i = 0; i <= num; ++ i) {
            if (i < reqHit || i > max) {
                hits[i] = 0;
            } else {
                var optNeed = i - reqHit;
                hits[i] = combine(optHit, optNeed) * combine(optMiss, optLeft - optNeed);
            }
        }
        return hits;
    }

    // 排列组合
    function combine(m, n) {
        if (m < n || n<0) {
            return 0;
        }
        return factorial(m, m - n + 1) / factorial(n, 1);
    }

    // 阶乘
    function factorial(max, min) {
        if (max >= min && max > 1) {
            return max * factorial(max - 1, min);
        } else {
            return 1;
        }
    }
</script>
</body>
</html>