$('#doBet').on('click',function () {
    //选择比赛场数小于两场
    if(items.length < 2){
        $('.tip').attr('title','至少选择两场比赛');
        $('.tip').tooltip('show');
        setTimeout(function () {
            $('.tip').tooltip('hide');
        },2000);
        return;
    }
    //未选择过关方式
    if(ways.length === 0){
        $('.tipspan').attr('title','请选择过关方式');
        $('.tipspan').tooltip('show');
        setTimeout(function () {
            $('.tipspan').tooltip('hide');
        },2000);
        return;
    }

    //收集投注单信息
    let lotteryType = parseInt($('#lotteryType').val());
    let lotteryName = $('#lotteryName').val();
    let detail = items;
    let total = $('.price').html();
    $.ajax({
        url:'doBet',
        type:'post',
        data:{lotteryType:lotteryType,lotteryName:lotteryName,detail:JSON.stringify(detail),total:total},
        dataType:'json',
        success:function (data) {
            if(data.result === '0'){//未登录
                window.open("../user/toLogin",'_self');
                // $('#loginDiv').removeClass('hide')
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