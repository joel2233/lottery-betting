package pers.joel.common.utils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pers.joel.models.BetOrder;
import pers.joel.models.LotteryResult;
import pers.joel.services.NewsManager;
import pers.joel.services.OrderManager;
import pers.joel.services.PayManager;
import pers.joel.services.UserManager;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Component
@EnableScheduling   // 1.开启定时任务
@EnableAsync        // 2.开启多线程
public class MultithreadScheduleTask {
    protected transient final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private NewsManager newsManager;

    @Async
//    @Scheduled(cron = "0 0/5 0-21 * * ?")  //间隔5min 正式
    @Scheduled(fixedDelay = 1000*60)  //间隔1min 测试
    public void wuFenTask() throws InterruptedException {


        LotteryResult eFive = newsManager.getLatestLotteryResult(LotteryResult.WUFEN);

        //当期未结束
        if(eFive.getEndTime().isAfter(LocalDateTime.now())){
            return;
        }
        LotteryResult neweFive = new LotteryResult();
        //上期时间是今天
        String newCode = "";
        if(eFive.getBeginTime().toLocalDate().isEqual(LocalDate.now())){
            newCode = String.valueOf(Integer.valueOf(eFive.getCode().substring(5))+1);
            newCode = eFive.getCode().substring(0,5).concat(newCode);
        }else {//上期时间是昨天,创建今日第一期
            newCode = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"))+"001";
        }
//
        System.out.println("newcode:"+newCode);
        neweFive.setCode(newCode);
        neweFive.setBeginTime(LocalDateTime.now());
        neweFive.setEndTime(LocalDateTime.now().plusMinutes(5));
        neweFive.setResult(randomResult(1,11,5));
        neweFive.setType(LotteryResult.WUFEN);

        newsManager.insertNewLotteryResult(neweFive);
        log.info("5分11选5定时任务 : " + LocalDateTime.now().toLocalTime() + ",线程 : " + Thread.currentThread().getName());
//        Thread.sleep(20000);
//        Thread.currentThread().interrupt();
    }



    @Async
//    @Scheduled(cron = "0 0/20 9-23 * * ?")   //间隔20min  正式
    @Scheduled(fixedDelay = 1000*60)   //间隔1min  测试
    public void guangDongTask() throws InterruptedException {

//        if(LocalDateTime.now().getHour() < 9 || LocalDateTime.now().getHour() > 23){
//            return;
//        }
        LotteryResult eFive = newsManager.getLatestLotteryResult(LotteryResult.GUANGDONG);

        //当期未结束
        if(eFive.getEndTime().isAfter(LocalDateTime.now())){
            return;
        }
        LotteryResult neweFive = new LotteryResult();
        //上期时间是今天
        String newCode = "";
        if(eFive.getBeginTime().getDayOfYear() == LocalDate.now().getDayOfYear()){
            newCode = String.valueOf(Integer.valueOf(eFive.getCode().substring(5))+1);
            newCode = eFive.getCode().substring(0,5).concat(newCode);
        }else {//上期时间是昨天,创建今日第一期
            newCode = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"))+"501";
        }
//
        System.out.println("newcode:"+newCode);
        neweFive.setCode(newCode);
        neweFive.setBeginTime(LocalDateTime.now());
        neweFive.setEndTime(LocalDateTime.now().plusMinutes(20));
        neweFive.setResult(randomResult(1,11,5));
        neweFive.setType(LotteryResult.GUANGDONG);

        newsManager.insertNewLotteryResult(neweFive);
        log.info("广东11选5定时任务 : " + LocalDateTime.now().toLocalTime() + ",线程 : " + Thread.currentThread().getName());
//        Thread.sleep(20000);
//        Thread.currentThread().interrupt();
    }


    @Async
//    @Scheduled(cron = "0 0 12 * * ?")   //每天中午12点触发  正式
    @Scheduled(fixedDelay = 3600000)   //间隔1h 用于测试
    public void openGloaryTask() throws InterruptedException {


        int day = 0;

        /* 大乐透开奖定时任务开始 */
//        if(day == 1 || day == 3 || day == 6) {
//            if(LocalDateTime.now().getHour() >= 20){//新一期
                LotteryResult letoulatest = newsManager.getLatestLotteryResult(LotteryResult.DALETOU);
                if(letoulatest.getEndTime().isBefore(LocalDateTime.now())){
                    day = letoulatest.getBeginTime().getDayOfWeek().getValue();
                    LotteryResult newResult1 = new LotteryResult();
                    newResult1.setType(LotteryResult.DALETOU);
                    newResult1.setBeginTime(letoulatest.getEndTime().plusSeconds(30));
                    newResult1.setResult(randomResult(1, 35, 5) + "|" + randomResult(1, 12, 2));
                    int month = letoulatest.getBeginTime().getMonthValue();
                    String newCode = "";
                    if (month == LocalDateTime.now().getMonthValue()) {//若是当月,则code+1
                        newCode = String.valueOf(Integer.valueOf(letoulatest.getCode()) + 1);
                    } else {//新的月份，从001开始
                        newCode = LocalDate.now().format(DateTimeFormatter.ofPattern("yyMM")) + "001";
                    }
                    newResult1.setCode(newCode);
                    if (day == 1 || day == 6) {
                        newResult1.setEndTime(letoulatest.getEndTime().plusDays(2));
                    } else {
                        newResult1.setEndTime(letoulatest.getEndTime().plusDays(3));
                    }
                    newsManager.insertNewLotteryResult(newResult1);
                    log.info("大乐透开奖定时任务 : " + LocalDateTime.now().toLocalTime() + ",线程 : " + Thread.currentThread().getName());
                }
//            }
//        }
        /* 大乐透开奖定时任务结束 */


        /* 双色球开奖定时任务开始 */
//        if(day == 2 || day == 4 || day == 7) {
//            if(LocalDateTime.now().getHour() >= 21){//新一期
                LotteryResult ssqlatest = newsManager.getLatestLotteryResult(LotteryResult.SHUANGSEQIU);
                if(ssqlatest.getEndTime().isBefore(LocalDateTime.now())){
                    day = ssqlatest.getBeginTime().getDayOfWeek().getValue();
                    LotteryResult newResult2 = new LotteryResult();
                    newResult2.setType(LotteryResult.SHUANGSEQIU);
                    newResult2.setBeginTime(ssqlatest.getEndTime().plusSeconds(30));
                    newResult2.setResult(randomResult(1, 33, 6) + "|" + randomResult(1, 16, 1));
                    int month = ssqlatest.getBeginTime().getMonthValue();
                    String newCode = "";
                    if (month == LocalDateTime.now().getMonthValue()) {//若是当月,则code+1
                        newCode = String.valueOf(Integer.valueOf(ssqlatest.getCode()) + 1);
                    } else {//新的月份，从101开始
                        newCode = LocalDate.now().format(DateTimeFormatter.ofPattern("yyMM")) + "101";
                    }
                    newResult2.setCode(newCode);
                    if (day == 2 || day == 7) {
                        newResult2.setEndTime(ssqlatest.getEndTime().plusDays(2));
                    } else {
                        newResult2.setEndTime(ssqlatest.getEndTime().plusDays(3));
                    }
                    newsManager.insertNewLotteryResult(newResult2);
                    log.info("双色球开奖定时任务 : " + LocalDateTime.now().toLocalTime() + ",线程 : " + Thread.currentThread().getName());
                }
//            }
//        }
        /* 双色球开奖定时任务结束 */

        /* 福彩3d开奖定时任务开始 */
        LotteryResult f3dlatest = newsManager.getLatestLotteryResult(LotteryResult.FUCAI3D);
        if(f3dlatest.getBeginTime().getDayOfYear() + 1 == LocalDateTime.now().getDayOfYear() || (f3dlatest.getBeginTime().getDayOfYear() == LocalDateTime.now().getDayOfYear() && LocalDateTime.now().getHour() > 21)){
            if(f3dlatest.getBeginTime().getDayOfYear() - 1 == LocalDateTime.now().getDayOfYear()) return;
            LotteryResult newResult3 = new LotteryResult();
            newResult3.setType(LotteryResult.FUCAI3D);
            newResult3.setBeginTime(f3dlatest.getBeginTime().plusDays(1));
            newResult3.setResult(randomResult(0, 9, 1) + "," + randomResult(0, 9, 1) + "," + randomResult(0,9,1));
            int year = f3dlatest.getBeginTime().getYear();
            String newCode = "";
            if (year == LocalDateTime.now().getYear()) {//若是当年,则code+1
                newCode = String.valueOf(Integer.valueOf(f3dlatest.getCode()) + 1);
            } else {//新的年份，从001开始
                newCode = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy")) + "001";
            }
            newResult3.setCode(newCode);
            newResult3.setEndTime(f3dlatest.getEndTime().plusDays(1));
            newsManager.insertNewLotteryResult(newResult3);
            log.info("福彩3d开奖定时任务 : " + LocalDateTime.now().toLocalTime() + ",线程 : " + Thread.currentThread().getName());
        }
        /* 福彩3d开奖定时任务结束 */
    }

    @Autowired
    private OrderManager orderManager;
    @Autowired
    private UserManager userManager;
    @Autowired
    private PayManager payManager;

    @Async
    @Scheduled(fixedDelay = 10000)  //间隔10s  查询数据库判断是否有中奖的订单
    public void checkForPrize() throws InterruptedException{

        /*大乐透奖金计算*/
        List<BetOrder> letouOrderList = orderManager.getLetouPaidOrders();
        for(BetOrder order : letouOrderList){
            String code = order.getCode();
            LotteryResult result = newsManager.getResultByCode(code);
            order.setLotteryResult(result);
            if(result.getEndTime().isBefore(LocalDateTime.now())){
                Double bonus = 0.00;
                order.setEnd(1);//投注已截止
                System.out.println("已截止");
                //判断是否中奖
                System.out.println("开奖结果："+result.getResult());
                String[] kaif = result.getResult().split("\\|")[0].split(",");
                String[] kaib = result.getResult().split("\\|")[1].split(",");

                JSONArray jsonArray =  JSONArray.fromObject(order.getDetail());
                for(int i = 0; i < jsonArray.size(); i++ ){
                    JSONObject object = JSONObject.fromObject(jsonArray.get(i));
                    String ball = object.get("ball").toString();
                    System.out.println("投注号码:"+ball);
                    String[] tzf = ball.split("\\|")[0].split(",");
                    String[] tzb = ball.split("\\|")[1].split(",");
                    Set<String> sames = getSamesLambda(tzf, kaif);
                    int ft = 0,bt = 0;
                    for (String s : sames) {
                        ft ++;
//                        System.out.println("");
//                        System.out.println(s);
                    }
                    System.out.println("前区命中："+ft);
                    Set<String> sames2 = getSamesLambda(tzb, kaib);
                    for (String s : sames2) {
                        bt++;
                    }
                    System.out.println("后区命中："+bt);
                    bonus += getBonus(tzf.length,tzb.length,ft,bt);
                }
                order.setBonus(bonus);
                order.setCalFlag(1);
                if(bonus > 0){
                    order.setPrize(1);
                    int uid = order.getUid();
                    payManager.updateUserMoney(uid,userManager.findById(uid).getMoney() + bonus);
                }else{
                    order.setPrize(0);
                }
                orderManager.updateOrderBonus(order);
            }else {
                order.setEnd(0);
                System.out.println("未截止");
            }

        }

        /*5分11选5奖金计算*/
        List<BetOrder> eleFiveOrderList = orderManager.getElevenFivePaidOrders();
        for(BetOrder order : eleFiveOrderList){
            System.out.println("asd");
            String code = order.getCode();
            LotteryResult result = newsManager.getResultByCode(code);
            order.setLotteryResult(result);
            if(result.getEndTime().isBefore(LocalDateTime.now())){
                Double bonus = 0.00;
                order.setEnd(1);

                System.out.println("开奖结果："+result.getResult());
                String[] kaifirst = {result.getResult().split(",")[0]};
                String[] kaisecond = {result.getResult().split(",")[1]};
                String[] kaithird = {result.getResult().split(",")[2]};

//
                JSONArray jsonArray =  JSONArray.fromObject(order.getDetail());
                for(int i = 0; i < jsonArray.size(); i++ ){
                    JSONObject object = JSONObject.fromObject(jsonArray.get(i));
                    String ball = object.get("ball").toString();
                    System.out.println("投注号码:"+ball);
                    String[] tzfirst = ball.split("\\|")[0].split(",");
                    String[] tzsecond = ball.split("\\|")[1].split(",");
                    String[] tzthird = ball.split("\\|")[2].split(",");
                    Set<String> sames = getSamesLambda(tzfirst, kaifirst);
                    Set<String> sames2 = getSamesLambda(tzsecond, kaisecond);
                    Set<String> sames3 = getSamesLambda(tzthird, kaithird);
                    boolean fz = false, sz = false, tz = false;
                    for (String s : sames) {
                        fz = true;
                       System.out.println("first:"+s);
                    }
                    for (String s : sames2) {
                        sz = true;
                        System.out.println("second:"+s);
                    }
                    for(String s : sames3){
                        tz = true;
                        System.out.println("third:"+s);
                    }
                    System.out.println("中奖："+(fz&&sz&&tz));
                    if(fz && sz && tz)
                        bonus += 1930.50;
                }
                order.setBonus(bonus);
                order.setCalFlag(1);
                if(bonus > 0){
                    order.setPrize(1);
                    int uid = order.getUid();
                    payManager.updateUserMoney(uid,userManager.findById(uid).getMoney() + bonus);
                } else {
                    order.setPrize(0);
                }
                orderManager.updateOrderBonus(order);
            }else {
                System.out.println("未截止");
            }
        }


        /*  双色球中奖计算  */
        List<BetOrder> ssqOrderList = orderManager.getSsqPaidOrders();
        for(BetOrder order : ssqOrderList){
            String code = order.getCode();
            LotteryResult result = newsManager.getResultByCode(code);
            order.setLotteryResult(result);
            if(result.getEndTime().isBefore(LocalDateTime.now())){
                Double bonus = 0.00;
                order.setEnd(1);//投注已截止
                System.out.println("已截止");
                //判断是否中奖
                System.out.println("开奖结果："+result.getResult());
                String[] kaif = result.getResult().split("\\|")[0].split(",");
                String[] kaib = result.getResult().split("\\|")[1].split(",");
//
                JSONArray jsonArray =  JSONArray.fromObject(order.getDetail());
                for(int i = 0; i < jsonArray.size(); i++ ){
                    JSONObject object = JSONObject.fromObject(jsonArray.get(i));
                    String ball = object.get("ball").toString ();
                    System.out.println("投注号码:"+ball);
                    String[] tzf = ball.split("\\|")[0].split(",");
                    String[] tzb = ball.split("\\|")[1].split(",");
                    Set<String> sames = getSamesLambda(tzf, kaif);
                    Set<String> sames2 = getSamesLambda(tzb, kaib);
                    int ft = 0,bt = 0;
                    for (String s : sames) {
                        ft ++;
////                        System.out.println("");
//                        System.out.println("红球中:"+s);
                    }
                    System.out.println("红球命中："+ft);
//                    Set<String> sames2 = getSamesLambda(tzb, kaib);
                    for (String s : sames2) {
                        bt++;
//                        System.out.println(s);
                    }
                    System.out.println("蓝球命中："+bt);
                    bonus += getBonus2(tzf.length,tzb.length,ft,bt);
                }
                order.setBonus(bonus);
                order.setCalFlag(1);
                if(bonus > 0){
                    order.setPrize(1);
                    int uid = order.getUid();
                    payManager.updateUserMoney(uid,userManager.findById(uid).getMoney() + bonus);
                } else {
                    order.setPrize(0);
                }
                orderManager.updateOrderBonus(order);
            }else {
                order.setEnd(0);
                System.out.println("未截止");
            }

        }


        /* 福彩3d中奖计算*/
        List<BetOrder> f3dOrderList = orderManager.getFu3dPaidOrders();
        for(BetOrder order : f3dOrderList) {
            String code = order.getCode();
            LotteryResult result = newsManager.getResultByCode(code);
            order.setLotteryResult(result);
            if (result.getEndTime().isBefore(LocalDateTime.now())) {
                Double bonus = 0.00;
                order.setEnd(1);

                System.out.println("开奖结果：" + result.getResult());
                String[] kaifirst = {result.getResult().split(",")[0]};
                String[] kaisecond = {result.getResult().split(",")[1]};
                String[] kaithird = {result.getResult().split(",")[2]};

//
                JSONArray jsonArray = JSONArray.fromObject(order.getDetail());
                for (int i = 0; i < jsonArray.size(); i++) {
                    JSONObject object = JSONObject.fromObject(jsonArray.get(i));
                    String ball = object.get("ball").toString();
                    System.out.println("投注号码:" + ball);
                    String[] tzfirst = ball.split("\\|")[0].split(",");
                    String[] tzsecond = ball.split("\\|")[1].split(",");
                    String[] tzthird = ball.split("\\|")[2].split(",");
                    Set<String> sames = getSamesLambda(tzfirst, kaifirst);
                    Set<String> sames2 = getSamesLambda(tzsecond, kaisecond);
                    Set<String> sames3 = getSamesLambda(tzthird, kaithird);
                    boolean fz = false, sz = false, tz = false;
                    for (String s : sames) {
                        fz = true;
                        System.out.println("first:" + s);
                    }
                    for (String s : sames2) {
                        sz = true;
                        System.out.println("second:" + s);
                    }
                    for (String s : sames3) {
                        tz = true;
                        System.out.println("third:" + s);
                    }
                    System.out.println("中奖：" + (fz && sz && tz));
                    if(fz && sz && tz)
                        bonus += 1930.50;
                }
                order.setBonus(bonus);
                order.setCalFlag(1);
                if (bonus > 0){
                    order.setPrize(1);
                    int uid = order.getUid();
                    payManager.updateUserMoney(uid,userManager.findById(uid).getMoney() + bonus);
                } else {
                    order.setPrize(0);
                }
                orderManager.updateOrderBonus(order);
            } else {
                System.out.println("未截止");
            }
        }

    }
    private static int fReq = 0,fOpt = 0,bReq = 0, bOpt = 0,fNum = 5,bNum = 2,fMax = 35,bMax = 12,fReqHit = 0, fOptHit = 0, bReqHit = 0, bOptHit = 0;;
    private static int[][][] winners ={{{5,2}},{{5, 1}},{{5, 0}},{{4, 2}},{{4, 1}},{{3, 2}},{{4, 0}},{{3, 1}, {2, 2}},{{3, 0}, {1, 2}, {2, 1}, {0, 2}}};
    private static int[] winningsList = {5000000,5000000,10000,3000, 300, 200, 100, 15, 5};


    private static int fReq2 = 0,fOpt2 = 0,bReq2 = 0, bOpt2 = 0,fNum2 = 6,bNum2 = 1,fMax2 = 33,bMax2  = 16,fReqHit2 = 0, fOptHit2 = 0, bReqHit2 = 0, bOptHit2 = 0;;
    private static int[][][] winners2 ={{{6,1}},{{6, 0}},{{5, 1}},{{5, 0},{4, 1}},{{4, 0},{3, 1}},{{2, 1}, {1, 1},{0, 1}}};
    private static int[] winningsList2 = {5000000,15000,3000, 200, 10, 5};
//    [[[5, 2]],[[5, 1]],[[5, 0]],[[4, 2]],[[4, 1]],[[3, 2]],[[4, 0]],[[3, 1], [2, 2]],[[3, 0], [1, 2], [2, 1], [0, 2]]];
    private static Double getBonus(int spf, int spb, int ft,int bt) {
        fReqHit = 0; fOptHit = 0; bReqHit = 0;bOptHit = 0;
        fOptHit = +ft;
        bOptHit = +bt;
        return win(spf,spb);
    }
    private static Double getBonus2(int spf, int spb, int ft,int bt) {
        fReqHit2 = 0; fOptHit2 = 0; bReqHit2 = 0;bOptHit2 = 0;
        fOptHit2 = +ft;
        bOptHit2 = +bt;
        return win2(spf,spb);
    }

    private static double win(int spf,int spb){
        fReq = 0;
        fOpt = 0;
        bReq = 0;
        bOpt = 0;

        fOpt = + spf;
        bOpt = + spb;

        int[] fHits = solveHits(fNum, fReq, fOpt, fReqHit, fOptHit);
        int[] bHits = solveHits(bNum, bReq, bOpt, bReqHit, bOptHit);
        int[] result = new int[9];
        for (int i = 0; i < winners.length; ++ i) {
            int[][] winner = winners[i];
                  int  count = 0;
            for (int j = 0; j < winner.length; ++ j) {
                int[] item = winner[j];
                count += fHits[item[0]] * bHits[item[1]];
            }
            result[i] = count;
        }
        return display(result);
    }

    private static double win2(int spf,int spb){
        fReq2 = 0;
        fOpt2 = 0;
        bReq2 = 0;
        bOpt2 = 0;

        fOpt2 = + spf;
        bOpt2 = + spb;

        int[] fHits = solveHits2(fNum2, fReq2, fOpt2, fReqHit2, fOptHit2);
        int[] bHits = solveHits2(bNum2, bReq2, bOpt2, bReqHit2, bOptHit2);
        int[] result = new int[6];
        for (int i = 0; i < winners2.length; ++ i) {
            int[][] winner = winners2[i];
            int  count = 0;
            for (int j = 0; j < winner.length; ++ j) {
                int[] item = winner[j];
                count += fHits[item[0]] * bHits[item[1]];
            }
            result[i] = count;
        }
        return display2(result);
    }

    private static Double display(int[] result) {
        int[] strs = {};
        double total = 0;
        for(int i = 0;i < result.length; i++) {
            int count = result[i];
            if(count > 0) {
                int value = winningsList[i];
                total += count * value;
            }
        }
        System.out.println("奖金："+total);
        return total;
    }


    private static Double display2(int[] result) {
        int[] strs = {};
        double total = 0;
        for(int i = 0;i < result.length; i++) {
            int count = result[i];
            if(count > 0) {
                int value = winningsList2[i];
                total += count * value;
            }
        }
        System.out.println("奖金："+total);
        return total;
    }

    // 计算前区或后区命中指定个数的方案注数
    private static int[] solveHits(int num,int req,int opt,int reqHit,int optHit) {
        int optLeft = num - req,
                optMiss = opt - optHit,
                max = reqHit + optHit;
        int[] hits = new int[6];
        for (int i = 0; i <= num; ++ i) {
            if (i < reqHit || i > max) {
                hits[i] = 0;
            } else {
                int optNeed = i - reqHit;
                hits[i] = combine(optHit, optNeed) * combine(optMiss, optLeft - optNeed);
            }
        }
        return hits;
    }
    // 计算前区或后区命中指定个数的方案注数
    private static int[] solveHits2(int num,int req,int opt,int reqHit,int optHit) {
        int optLeft = num - req,
                optMiss = opt - optHit,
                max = reqHit + optHit;
        int[] hits = new int[7];
        for (int i = 0; i <= num; ++ i) {
            if (i < reqHit || i > max) {
                hits[i] = 0;
            } else {
                int optNeed = i - reqHit;
                hits[i] = combine(optHit, optNeed) * combine(optMiss, optLeft - optNeed);
            }
        }
        return hits;
    }
    // 排列组合
    private static int combine(int m, int n) {
        if (m < n || n<0) {
            return 0;
        }
        return factorial(m, m - n + 1) / factorial(n, 1);
    }

    // 阶乘
    private static int factorial(int max, int min) {
        if (max >= min && max > 1) {
            return max * factorial(max - 1, min);
        } else {
            return 1;
        }
    }

    private static Set<String> getSamesLambda(String[] a, String[] b) {
        Set<String> bb = new HashSet<String>(Arrays.asList(b));
        List<String> sames = Arrays.asList(a).stream().filter(i -> !bb.add(i)).collect(Collectors.toList());
        return new HashSet<String>(sames);
    }

    public static void main(String[] args){
        String newCode = "20190401001";
        newCode = String.valueOf(Integer.valueOf(newCode.substring(5))+1);
        newCode = "20190401001".substring(0,5).concat(newCode);

        System.out.println(newCode);
    }

    private static String randomResult(int min,int max,int length) {
        Random ran = new Random();
        String result = "";

        for(int i = 1;i < length + 1;i++){
            int num = ran.nextInt(max)+min;
            if(result.indexOf(String.valueOf(num)) != -1){
                i--;
                continue;
            }
            result +=  String.valueOf(num);
            if(i != length){
                result += ",";
            }
        }
        return result;
    }
}

