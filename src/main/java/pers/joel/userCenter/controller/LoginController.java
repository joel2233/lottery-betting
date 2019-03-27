package pers.joel.userCenter.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import pers.joel.common.controller.BaseController;

@Controller
@RequestMapping("/user")
public class LoginController extends BaseController {

    @RequestMapping("/toLogin")
    public String toLogin(){
        return "user/login";
    }
}
