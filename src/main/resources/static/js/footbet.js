$('#doBet').on('click',function () {
    //判断是否登录，未登录跳转登录页面
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
            if(data.result === '0'){//未登录，到首页
                alert(data.msg);
                window.open("../user/toLogin",'_self');
            }
        }
    })


});