package pers.joel.controller.betCenter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pers.joel.common.utils.FormatDateTimeMethodModel;
import pers.joel.common.utils.HttpUtil;
import pers.joel.common.utils.JSONUtil;
import pers.joel.controller.BaseController;
import pers.joel.models.ApiChannel;
import pers.joel.models.BetOrder;
import pers.joel.models.LotteryResult;
import pers.joel.services.NewsManager;
import pers.joel.services.OrderManager;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Controller
@RequestMapping("/news")
public class NewsController extends BaseController {
    protected transient final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private NewsManager newsManager;

    @RequestMapping({"/footNews","basketNews"})
    public String footNews(){
        String code = getRequest().getParameter("id");
        if(code == null)
            code="f1";
        try {
            ApiChannel apiChannel = newsManager.getChannelByCode(code);
            if(apiChannel == null){
               saveErrorMessage("系统错误，请稍后重试!");
               return ERROR;
            }
            String url = apiChannel.getUrl();
            String result = HttpUtil.getResponseData(url);
            if(JSONUtil.readJson2Map(result).get("code").toString().equals("200")){
                getRequest().setAttribute("itemlist", JSONUtil.readJson2Map(result).get("data"));
            }else {
                getRequest().setAttribute("itemlist", "获取数据失败，请重试");
            }
        } catch (Exception e) {
            saveErrorMessage("系统错误，请稍后重试!");
            log.error("获取数据异常");
            return ERROR;
        }
        return "/news/newsinfo" +code;
    }

    @RequestMapping("/getNewsInfo")
    @ResponseBody
    public Object getNewsInfo(){
        Map<String,Object> map = new HashMap<>();
        map.put("code","1");

        String url = getRequest().getParameter("url");
        try {
            String result = HttpUtil.getResponseData(url);
            if(JSONUtil.readJson2Map(result).get("code").toString().equals("200")){
                map.put("code","0");
                map.put("msg","");
                map.put("count",JSONUtil.readJson2Map(result).size());
                map.put("data", JSONUtil.readJson2Map(result).get("data"));
            }else {
                map.put("msg","");
                map.put("count",0);
                map.put("data", "");
            }

        } catch (Exception e) {
            map.put("msg","获取数据异常");
            log.error("获取数据异常");
        }

        return map;
    }

    @Autowired
    private OrderManager orderManager;

    @RequestMapping("doBet")
    @ResponseBody
    public Object doBet(){
        Map<String,Object> map = new HashMap<>();
        map.put("result","0");
        //用户未登录
        if(getCurrentUser() == null){
            map.put("msg","未登录");
            return map;
        }
        //保存订单信息
//        Order
        BetOrder betOrder = new BetOrder();

        betOrder.setLotteryType(Integer.parseInt(getRequest().getParameter("lotteryType")));
        betOrder.setLotteryName(getRequest().getParameter("lotteryName"));
        betOrder.setCode(getRequest().getParameter("code"));
        betOrder.setDetail(getRequest().getParameter("detail"));
        betOrder.setNote(Integer.parseInt(getRequest().getParameter("note")));
        betOrder.setTotal(Double.valueOf(getRequest().getParameter("total")));
        betOrder.setUid(getCurrentUid());
        betOrder.setState(BetOrder.ORDER_VALID);
        betOrder.setCreateTime(LocalDateTime.now());
        betOrder.setExpireTime(LocalDateTime.now().plusMinutes(10));
        betOrder.setPayState(BetOrder.ORDER_NOT_PAID);
        try{
            int orderId = orderManager.insert(betOrder);
            map.put("result","1");
            map.put("msg","保存成功");
        }catch (Exception e){
            log.error("生成投注单",transformExceptionMessage(e));
            map.put("result","2");
            map.put("msg","生成投注单异常");
        }
        return map;
    }


    @RequestMapping("letou")
    public String toLeTou(){
        LotteryResult thisResult = newsManager.getLatestLotteryResult(LotteryResult.DALETOU);
        LotteryResult lastResult = newsManager.getLastLotteryResult(LotteryResult.DALETOU);

        getRequest().setAttribute("lastResult",lastResult);
        getRequest().setAttribute("thisResult",thisResult);
        getRequest().setAttribute("formatTime",new FormatDateTimeMethodModel());

        return "news/letou";
    }

    @RequestMapping("tossq")
    public String tossq(){
        LotteryResult thisResult = newsManager.getLatestLotteryResult(LotteryResult.SHUANGSEQIU);
        LotteryResult lastResult = newsManager.getLastLotteryResult(LotteryResult.SHUANGSEQIU);

        getRequest().setAttribute("lastResult",lastResult);
        getRequest().setAttribute("thisResult",thisResult);
        getRequest().setAttribute("formatTime",new FormatDateTimeMethodModel());
        return "news/ssq";
    }

    @RequestMapping("tofu3d")
    public String tofu3d(){
        LotteryResult thisResult = newsManager.getLatestLotteryResult(LotteryResult.FUCAI3D);
        LotteryResult lastResult = newsManager.getLastLotteryResult(LotteryResult.FUCAI3D);

        getRequest().setAttribute("lastResult",lastResult);
        getRequest().setAttribute("thisResult",thisResult);
        getRequest().setAttribute("formatTime",new FormatDateTimeMethodModel());
        return "news/fu3d";
    }

    @RequestMapping("wf11x5")
    public String wf11x5(){
//        LotteryResult lastResult = newsManager.getLastElevenFive(LotteryResult.WUFEN);
        LotteryResult thisResult = newsManager.getLatestLotteryResult(LotteryResult.WUFEN);

        if(thisResult.getEndTime().isBefore(LocalDateTime.now())) {
            LotteryResult neweFive = new LotteryResult();
            String newCode = "";
            if (thisResult.getBeginTime().toLocalDate().isEqual(LocalDate.now())) {
                newCode = String.valueOf(Integer.valueOf(thisResult.getCode().substring(5)) + 1);
                newCode = thisResult.getCode().substring(0, 5).concat(newCode);
            } else {
                newCode = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")) + "001";
            }
            neweFive.setCode(newCode);
            neweFive.setResult(randomResult(1,11));
            neweFive.setBeginTime(LocalDateTime.now());
            neweFive.setEndTime(LocalDateTime.now().plusMinutes(5));
            neweFive.setType(LotteryResult.WUFEN);

            newsManager.insertNewLotteryResult(neweFive);
            thisResult = newsManager.getLatestLotteryResult(LotteryResult.WUFEN);
        }
        LotteryResult lastResult = newsManager.getLastLotteryResult(LotteryResult.WUFEN);

        getRequest().setAttribute("lastResult",lastResult);
        getRequest().setAttribute("thisResult",thisResult);
        getRequest().setAttribute("formatTime",new FormatDateTimeMethodModel());
        return "news/wf11x5";
    }

    @RequestMapping("gd11x5")
    public String gd11x5(){
        LotteryResult thisResult = newsManager.getLatestLotteryResult(LotteryResult.GUANGDONG);
        if(thisResult.getEndTime().isBefore(LocalDateTime.now()) && LocalDateTime.now().getHour() >= 9 && LocalDateTime.now().getHour() <= 23){

            LotteryResult neweFive = new LotteryResult();
            String newCode = "";
            if(thisResult.getBeginTime().toLocalDate().isEqual(LocalDate.now())){
                newCode = String.valueOf(Integer.valueOf(thisResult.getCode().substring(5))+1);
                newCode = thisResult.getCode().substring(0,5).concat(newCode);
            }else {
                newCode = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"))+"501";
            }

            neweFive.setCode(newCode);
            neweFive.setResult(randomResult(1,11));
            neweFive.setBeginTime(LocalDateTime.now());
            neweFive.setEndTime(LocalDateTime.now().plusMinutes(20));
            neweFive.setType(LotteryResult.GUANGDONG);

            newsManager.insertNewLotteryResult(neweFive);
            thisResult = newsManager.getLatestLotteryResult(LotteryResult.GUANGDONG);
        }
        LotteryResult lastResult = newsManager.getLastLotteryResult(LotteryResult.GUANGDONG);
        getRequest().setAttribute("lastResult",lastResult);
        getRequest().setAttribute("thisResult",thisResult);
        getRequest().setAttribute("formatTime",new FormatDateTimeMethodModel());
        return "news/gd11x5";
    }

    private static String randomResult(int min,int max) {
        Random ran = new Random();
        String result = "";

        for(int i = 1;i < 6;i++){
            int num = ran.nextInt(max) + min;
            System.out.println("第"+ i +"个随机数 =="+num);
            if(result.indexOf(String.valueOf(num)) != -1){
                i--;
                continue;
            }
            result +=  String.valueOf(num);
            if(i != 5){
                result += ",";
            }
        }
        return result;
    }
}
