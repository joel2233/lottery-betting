/*  单个投注按钮点击事件common */
let items = [];
$('.info-content').on('click','.bet-btn',function () {

   let ispan = $(this).closest('.item');
   $(this).toggleClass('bet-btn-selected');

   //该按钮有关信息
   let item = {
      no:ispan.attr('id'),
      type:ispan.find('.item-type').html(),
      home:ispan.find('.item-home').html(),
      away:ispan.find('.item-away').html(),
      details:[]
   };
   let letFlag = $(this).parent().hasClass('let');
   let rate = $(this).html();
   let detail = {
      id:$(this).attr('tid'),
      detailType:letFlag?'let':'nolet',
      letball:ispan.find('.ball1').html(),
      rate:rate
   };
   if($(this).hasClass('bet-btn-selected')){//点击选中某个投注
      if(!$(this).closest('.item').hasClass('item-selected')){//此场比赛未选
         item.details.push(detail);
         items.push(item);//新增item
         //items更新显示
         updateItemView('add',item);

         $(this).closest('.item').addClass('item-selected');
      }else {//此厂比赛已选，对应item的details添加对应detail
         handleItemDetail('add',ispan.attr('id'),detail);
         updateDetailView('add',ispan.attr('id'),detail);
      }
   }else {//移除某个投注
      //遍历该场比赛下所有投注按钮是否有被选中的
      let flag = false;
      $(this).closest('.item').find('.bet-btn').each(function () {
         if($(this).hasClass('bet-btn-selected'))
            flag = true;
      });
      if(flag){//有其他被选中的按钮
         //删除对应item的details的对应detail
         handleItemDetail('delete',ispan.attr('id'),detail)
         updateDetailView('del',ispan.attr('id'),detail)
      }else {//没有其他被选中的按钮
         //删除item
         for(let i = 0;i < items.length;i++){
            if(ispan.attr('id') === items[i].no){
               items.splice(i,1);
               updateItemView('del',ispan.attr('id'));
            }
         }
         $(this).closest('.item').removeClass('item-selected');
      }
   }
   console.log(items);
   getPrice();
});

//处理选中投注的比赛信息
/**
 * type delete add
 * no 对应的no
 */
function handleItemDetail(type,no,detail) {
   for(let i = 0;i < items.length;i++){
      if(no === items[i].no) {
         if(type === 'add'){
            items[i].details.push(detail)
         }else {
            for(let j = 0;j<items[i].details.length;j++){
               if(detail.id === items[i].details[j].id){
                  items[i].details.splice(j,1);
               }
            }
         }
         break;
      }
   }
}

//更新显示的已选中信息
function updateDetailView(type,itemno,obj) {
   switch (type) {
      case 'add':
         let ttype;
         switch (obj.id) {
            case 'no1':case 'l1':ttype = '胜';break;
            case 'no2':case 'l2':ttype = '平';break;
            case 'no3':case 'l3':ttype = '负';break;
         }
         let detail = $("<span class='detail d" + obj.id + "'>" + (obj.id.search('no')!==-1?'':'['+obj.letball+']') + ttype +"@"+ obj.rate + "</span>");
         $('#b'+itemno).append(detail);
         break;
      case 'del':
         $('#b'+itemno).find('.d'+obj.id).remove();
         break;
      default:break;
   }
}

function updateItemView(type,obj) {
   switch (type) {
      case 'add':
         let ttype;
         switch (obj.details[0].id) {
            case 'no1':case 'l1':ttype = '胜';break;
            case 'no2':case 'l2':ttype = '平';break;
            case 'no3':case 'l3':ttype = '负';break;
         }
         let info = "<div class='binfo' id='b"+ obj.no +"'>" +
             "<span class='info-no'>" + obj.no +"</span>" +
             "<span class='info-type'>" + obj.type +"</span>" +
             "<span class='info-home'>" + obj.home +"</span>" +
             "<span class='info-away'>" + obj.away +"</span>" +
             "<span class='detail d" + obj.details[0].id + "'>" + (obj.details[0].id.search('no')!==-1?'':'['+obj.details[0].letball+']') + ttype +"@"+ obj.details[0].rate + "</span>" +
             "</div>";
         $('#bet-info').append(info);
         if(items.length>=2){

            var wayItem = $("<span class='way-item way"+items.length+"'><span class='chkbox' data-value='"+items.length+"串1' data-disabled='0'></span>"+items.length+"串1</span>");
            $('.bet-way').append(wayItem);
         }
         break;
      case 'del':
         $('#b'+obj).remove();
         $('.bet-way').find('.way'+(items.length+1)).remove();
         let index = ways.indexOf(items.length+1);
         if(index !== -1) ways.splice(index,1);
         break;
      default:break;
   }
   //已选场次数字变化
   $('#selectedCount').html(items.length);
   if(items.length >= 2){
      $('.tip').addClass('hide');
   }else {
      $('.tip').removeClass('hide');
      ways = [];
   }
}

function minusBs() {
   if(parseInt($('#buy_bs').val()) <= 1)
      return;

   $('#buy_bs').val(parseInt($('#buy_bs').val())-1);
   beishu = $('#buy_bs').val();
   getPrice();
}

function addBs() {
   if(parseInt($('#buy_bs').val()) >= 50)
      return;
   $('#buy_bs').val(parseInt($('#buy_bs').val())+1);
   beishu = $('#buy_bs').val();
   getPrice();
}

$('#buy_bs').on('keyup',function () {
   formatBeishu($(this));
});
$('#buy_bs').on('blur',function () {
   formatBeishu($(this));
});
//检测输入的是否为整数
function formatBeishu(obj) {
   let bs = obj.val();
   let nbs = bs.replace(/\D/g,'');
   if(nbs !== ''){
      $(this).val(nbs);
      if(nbs>=50){obj.val(50);}
      if(nbs<=1){obj.val(1);}
   }
   beishu = $('#buy_bs').val();
   if(beishu === '') beishu = 1;
   getPrice();
}
let ways = [];
let beishu = $('#buy_bs').val();
$('.bet-way').on('click','.way-item',function () {
   $(this).find('.chkbox').toggleClass('chkbox-on');
   if($(this).find('.chkbox').hasClass('chkbox-on')){//增
      ways.push($(this).find('.chkbox').attr('data-value').replace('串1',''));
   }else {//减
      let index = ways.indexOf($(this).find('.chkbox').attr('data-value').replace('串1',''));
      ways.splice(index,1);
   }
   getPrice();
});

function toggleBetZone() {
   $('.bet-zone').find('div').toggle(500);
   $('.toggleBtn').find('i').toggleClass('fa-angle-double-up');
   $('.toggleBtn').find('i').toggleClass('fa-angle-double-down');
}