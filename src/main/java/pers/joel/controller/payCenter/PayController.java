package pers.joel.controller.payCenter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pers.joel.controller.BaseController;
import pers.joel.models.BetOrder;
import pers.joel.models.UcUser;
import pers.joel.services.OrderManager;
import pers.joel.services.PayManager;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/payCenter")
public class PayController extends BaseController {
    protected transient final Logger log = LoggerFactory.getLogger(getClass());


    @Autowired
    private PayManager payManager;

    @RequestMapping("/recharge")
    @ResponseBody
    public Object recharge(){
        Map<String,Object> map = new HashMap<>();
        UcUser u = getCurrentUser();
        map.put("result","0");
        try{
            Double money = Double.valueOf(getRequest().getParameter("money"));
            payManager.updateUserMoney(u.getUid(),money+u.getMoney());
            map.put("result","1");
            map.put("msg","充值成功");
        }catch (Exception e){
            map.put("msg",transformExceptionMessage(e));
            log.error("账户充值异常-{}",e.getMessage());
        }
        return map;
    }

    @Autowired
    private OrderManager orderManager;

    @RequestMapping("/pay")
    @ResponseBody
    public Object pay(){
        Map<String,Object> map = new HashMap<>();
        map.put("result","0");
        try{
            int orderId = Integer.valueOf(getRequest().getParameter("orderId"));
            BetOrder order = orderManager.getOrderById(orderId);
            if(order.getExpireTime().isBefore(LocalDateTime.now())){
                map.put("msg","订单已过期");
                return map;
            }

            orderManager.updateOrderState(orderId);
            payManager.updateUserMoney(getCurrentUid(),getCurrentUser().getMoney() - order.getTotal());
            updateCache();

            //交易记录，流水记录

            map.put("result","1");
            map.put("msg","支付成功");
        }catch (Exception e){
            map.put("msg",transformExceptionMessage(e));
            log.error("订单支付异常-{}",e.getMessage());
        }
        return map;
    }
}
