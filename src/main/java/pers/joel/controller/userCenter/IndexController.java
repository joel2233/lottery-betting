package pers.joel.controller.userCenter;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import pers.joel.controller.BaseController;

import java.util.Map;

@Controller
public class IndexController extends BaseController {

    //首页映射
    @RequestMapping({"/","/index"})
    public String index(Map<String,Object> map){
        getRequest().setAttribute("title","万艾彩票");

        getRequest().setAttribute("user",getCurrentUser());

        return "index";
    }

//    @RequestMapping({"/error-404"})
//    public String toErrorPage(){
//        return ERROR;
//    }

}
