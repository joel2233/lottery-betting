package pers.joel.userCenter.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import pers.joel.common.controller.BaseController;

import java.util.Map;

@Controller
public class IndexController extends BaseController {

    //首页映射
    @RequestMapping({"/","/index"})
    public String index(Map<String,Object> map){
        getRequest().setAttribute("title","万艾彩票");
        return "index";
    }

    @RequestMapping({"/error-404","/error-400","/error-500"})
    public String toErrorPage(){
        return ERROR;
    }

}
