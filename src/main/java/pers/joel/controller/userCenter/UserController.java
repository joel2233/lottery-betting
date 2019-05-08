package pers.joel.controller.userCenter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import pers.joel.common.utils.FormatDateTimeMethodModel;
import pers.joel.controller.BaseController;
import pers.joel.models.BetOrder;
import pers.joel.models.LotteryResult;
import pers.joel.services.NewsManager;
import pers.joel.services.OrderManager;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("userCenter")
public class UserController extends BaseController {
    protected transient final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private OrderManager orderManager;

    @RequestMapping({"/index","/personalInfo"})
    public String userIndex(){
        getRequest().setAttribute("user",getCurrentUser());
        return "user/userIndex";
    }

    @RequestMapping("/security")
    public String security(){
        return "user/usersecurity";
    }

    @RequestMapping("/recharge")
    public String recharge(){
        updateCache();
        getRequest().setAttribute("user",getCurrentUser());
//        getRequest().setAttribute("user",new FormatDateTimeMethodModel());
        return "user/recharge";
    }
    @RequestMapping("/transfer")
    public String transfer(){
        getRequest().setAttribute("user",getCurrentUser());
        return "user/transfer";
    }
    //投注单记录
    @RequestMapping("/orderlist")
    public String orderlist(){
        int uid = getCurrentUid();
        List<BetOrder> orderList = orderManager.getBetOrdersByUid(uid);
        for(BetOrder order : orderList){
            //判断是否过期
            if(order.getExpireTime().isBefore(LocalDateTime.now())){
                orderManager.setOrderExpired(order.getOrderId());
            }
        }
        getRequest().setAttribute("orderlist",orderList);
        getRequest().setAttribute("formatTime",new FormatDateTimeMethodModel());

        return "user/orderlist";
    }

    @Autowired
    private NewsManager newsManager;
    //投注记录
    @RequestMapping("/lotterylist")
    public String lotterylist(){
        int uid = getCurrentUid();
        List<BetOrder> orderList = orderManager.getPaidOrdersByUid(uid);
        for(BetOrder order:orderList){
            LotteryResult lotteryResult = newsManager.getResultByCode(order.getCode());
            order.setLotteryResult(lotteryResult);
            if(lotteryResult.getEndTime().isBefore(LocalDateTime.now())){
                order.setEnd(1);
            }else {
                order.setEnd(0);
            }

        }

        getRequest().setAttribute("orderlist",orderList);
        getRequest().setAttribute("formatTime",new FormatDateTimeMethodModel());
        return "user/lotterylist";
    }
}
