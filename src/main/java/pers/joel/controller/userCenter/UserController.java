package pers.joel.controller.userCenter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import pers.joel.controller.BaseController;
import pers.joel.models.BetOrder;
import pers.joel.services.OrderManager;

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


    //投注单记录
    @RequestMapping("/orderlist")
    public String orderlist(){
        int uid = getCurrentUid();
        List<BetOrder> orderList = orderManager.getBetOrdersByUid(uid);
        getRequest().setAttribute("orderlist",orderList);
        return "user/orderlist";
    }

    //投注记录
    @RequestMapping("/lotterylist")
    public String lotterylist(){

        return "user/lotterylist";
    }
}
