package pers.joel.controller;


import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Map;

//处理登登录请求controller
@Controller
public class LoginController {
    //首页映射
    @GetMapping({"/","/index.html"})
    public String index(){
        return "login";
    }

    //处理登录
    @PostMapping(value = "/login")
    public String Login(@RequestParam("phone") String phone,
                        @RequestParam("password") String password,
                        Map<String,Object> map, HttpSession session, Model model){
        String res = "";
        //账号密码为空
        if(StringUtils.isEmpty(phone) || StringUtils.isEmpty(password)){
            map.put("msg","账号密码不能为空");
            return "login";
        }

        return res;
    }
}
