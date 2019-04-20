package pers.joel.controller.userCenter;

import org.apache.logging.log4j.util.Strings;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import pers.joel.controller.BaseController;
import pers.joel.models.UcUser;

@Controller
@RequestMapping("/user")
public class LoginController extends BaseController {

    @RequestMapping("/toLogin")
    public String toLogin(){
        UcUser user = getCurrentUser();
        if(user!=null){
            return "redirect:/";
        }

        getRequest().setAttribute("user",user);

        return "user/login";
    }

    @RequestMapping("/doLogin")
    public String doLogin(){
        String username = getRequest().getParameter("username");
        String password = getRequest().getParameter("password");
        String rememberme = getRequest().getParameter("rememberMe");

        boolean rememberMe = !Strings.isEmpty(rememberme);


        UsernamePasswordToken token = new UsernamePasswordToken(username, password,rememberMe);
//        token.setRememberMe(true);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
        } catch (UnknownAccountException e) {
            saveErrorMessage("用户不存在");
        }catch (AuthenticationException e){
            saveErrorMessage("用户名/密码错误");
        }catch (Exception e){
            saveErrorMessage("其他错误，请联系管理员");
        }

        if(subject.isAuthenticated()){
            return "redirect:/index";
        }
        return "user/login";
    }

    @RequestMapping("/logout")
    public String logout(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "redirect:/";
    }
}
