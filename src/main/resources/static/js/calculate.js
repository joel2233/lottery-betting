

function getPrice() {

    let notes = 0;
    let details = [];
    items.forEach((item,index) => {
        details.push(item.details.length);
    });
    ways.forEach((way,index) => {
        notes += getNotes(details,items.length,way);
    });

    $('.price').html(2*notes*beishu);
    getBonus();
}

function getNotes(a,n,m){
    if (n<m){
        return 0;
    }
    if (m === 1 ){
        return a[n-1]+getNotes(a,n-1,1);
    }
    return getNotes(a,n-1,m) + a[n-1]*getNotes(a,n-1,m-1);
}

function getBonus() {
    if(ways.length === 0) {
        $('.bonus').html(0);
        return;
    }

    let mins =[],maxs = [];
    items.forEach((item,index) => {
        let rname = 'rate'+index;
        rname =  new Array(0,0,0,0,0,0);
        item.details.forEach((detail,index2) => {
            let i;
            switch (detail.id) {
                case 'no1':i = 0;break;
                case 'no2':i = 1;break;
                case 'no3':i = 2;break;
                case 'l1':i = 3;break;
                case 'l2':i = 4;break;
                case 'l3':i = 5;break;
                default:break;
            }
            rname[i] = parseFloat(detail.rate);
        });
        let adds;
        if(item.details[0].letball > 0){//客队让球
            adds = [rname[0]+rname[3],rname[1]+rname[3],rname[2]+rname[4],rname[2]+rname[5]];
        }else {//主队让球
            adds = [rname[0]+rname[3],rname[0]+rname[4],rname[1]+rname[5],rname[2]+rname[5]];
        }
        adds.sort(sortNumber);
        let max = adds[adds.length - 1];
        // let sorted = rname.sort(sortNumber);
        let minr  = Array.from(new Set(rname.sort(sortNumber)));
        let min;
        if(minr.includes(0)){
            min = minr[1];
        }else {
            min = minr[0];
        }
        // let max = sorted[sorted.length-1];



        mins.push(min);
        maxs.push(max);
    });
    console.log('mins:',mins);
    console.log('maxs:',maxs);
    let minBonus = getMinBonus(mins);
    let maxBonus = getMaxBonus(maxs);
    // items.forEach((item,index) => {
    //     rates = [];
    //     item.details.forEach((detail,index) => {
    //        rates.push(detail.rate);
    //     });
    //     rates.sort(sortNumber);
    //     singleMin = rates[0];
    //     singleMax = rates[rates.length-1];
    //     minRates.push(singleMin);
    //     maxRates.push(singleMax);
    // });
    //
    // let minRateMulit = minRates.reduce(function(prev, cur){
    //     return prev * cur;
    // });
    // let maxRateMulit = maxRates.reduce(function(prev, cur){
    //     return prev * cur;
    // });
    // console.log('minRe:'+minRateMulit);
    console.log('minBonus:'+minBonus);
    console.log('maxBonus:'+maxBonus);
    // console.log('maxRe:'+maxRateMulit);
    // let minBonus = 2*minRateMulit;
    // let maxBonus = 2*maxRateMulit;
    if(minBonus === maxBonus){
        $('.bonus').html(minBonus.toFixed(2));
    }else {
        $('.bonus').html(minBonus.toFixed(2)+'~'+maxBonus.toFixed(2));
    }
}

function getMinBonus(a) {
    console.log('before a:',a)
    //ways的最小值
    ways.sort(sortNumber);
    let minway = parseInt(ways[0]);
    console.log('minway way:',minway)
    //数组的m个最小的相乘*2
    a.sort(sortNumber);
    let loopLength = a.length - minway;
    for(let i = 0;i < loopLength;i++){
        a.splice(-1,1);
    }
    console.log('after a:',a)
    return 2 * beishu * a.reduce(function (prev,next) {
        return prev*next;
    });
}

function getMaxBonus(a) {
    let maxB = 0;
    ways.forEach((way,index) => {
        maxB += getNotes(a,items.length,way);
    });
    return 2 * maxB * beishu;
}

