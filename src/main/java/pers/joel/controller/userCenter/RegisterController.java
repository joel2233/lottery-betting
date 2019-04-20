package pers.joel.controller.userCenter;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pers.joel.common.utils.JSONUtil;
import pers.joel.common.utils.SecurityUtil;
import pers.joel.common.utils.SmsUtil;
import pers.joel.controller.BaseController;
import pers.joel.daos.SmsRecordDao;
import pers.joel.models.SmsRecord;
import pers.joel.models.UcUser;
import pers.joel.services.UserManager;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Controller
@RequestMapping("/user/register")
public class RegisterController extends BaseController {

    protected transient final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserManager userManager;
    @Autowired
    private SmsRecordDao smsRecordDao;

    @GetMapping(value = "/toRegister")
    public String Login(Map<String,Object> map){
//        map.put("msg","hahh");
        return "user/register";
    }

    /**
     * 检查手机号是否被注册
     * @return
     */
    @PostMapping("/ajaxCheckPhone")
    @ResponseBody
    public Object ajaxCheckPhone(){
        Map<String,Object> map = new HashMap<>();
        map.put("result","0");
        try{
            String phone = getRequest().getParameter("phone");
            UcUser user = userManager.findByPhone(phone);
            if(user == null || user.getChecked() == UcUser.PHONE_UNCHECKED){
                map.put("result","1");
                map.put("message","尚未注册的手机号");
            }else {
                map.put("message","该手机号已注册");
            }
        }catch (Exception e){
            map.put("message", transformExceptionMessage(e));
            log.error("获取该手机号相关信息异常："+e);
        }
        return map;
    }

    //处理登录
    @PostMapping(value = "/sendVerifyCode")
    @ResponseBody
    public Object sendVerifyCode(){
        Map<String,Object> res = new HashMap<>();
        res.put("result","0");
        String phone = getRequest().getParameter("phone");
        //产生6位验证码
        String verifyCode = generateVerifyCode();
        System.out.println("phone:"+phone);
        System.out.println("verifyCode:"+verifyCode);

        UcUser user = userManager.findByPhone(phone);

        if(user == null){
            user = new UcUser();
            user.setPhone(phone);
            user.setVerifyCode(verifyCode);
            user = userManager.createUser(user);
        }else{//已有记录但是未验证,更新验证码
            user.setVerifyCode(verifyCode);
            userManager.updateUserVerifyCode(user);
        }
        try {
            String content = "验证码为: " + verifyCode + ",请在页面输入完成验证，如非本人操作请忽略。";
            String result = SmsUtil.sendMessage(phone,content);
            //插入sms_record
            SmsRecord smsRecord = new SmsRecord();
            smsRecord.setUid(user.getUid());
            smsRecord.setPhone(phone);
            smsRecord.setContent(content);
            smsRecord.setSendTime(LocalDateTime.now());


            String resultCode = JSONUtil.readJson2Map(result).get("code").toString();
            String resultMsg = JSONUtil.readJson2Map(result).get("data").toString();

            smsRecord.setResult(result==""?SmsRecord.SMS_RESULT_FAULT:(resultCode.equals("0")?SmsRecord.SMS_RESULT_SUCCESS:SmsRecord.SMS_RESULT_FAILURE));
            smsRecord.setDescription(resultMsg);
            smsRecordDao.insert(smsRecord);
            if(resultCode.equals("0")){
                res.put("result","1");
                res.put("message","验证码发送成功");
            }else {
                res.put("message","发送失败,请稍后重试");
            }
            log.info("发送验证码结果:"+result);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("发送验证码异常:"+e.getMessage());
        }
        return res;
    }

    @PostMapping("/ajaxVerifyCode")
    @ResponseBody
    public Object ajaxVerifyCode(){
        Map<String,Object> map = new HashMap<>();
        map.put("result","0");
        String phone = getRequest().getParameter("phone");
        String verifyCode = getRequest().getParameter("verifyCode");
        try{
            UcUser user = userManager.findByPhone(phone);
            if(user.getVerifyCode().equals(verifyCode)){
                userManager.updateUserChecked(user.getUid());
                map.put("result","1");
                map.put("message","验证成功");
                map.put("uid",user.getUid());
            }else {
                map.put("message","验证码错误");
            }
            log.info("用户-{}验证完成,code-{}",phone,verifyCode);
        }catch (Exception e){
            map.put("message",transformExceptionMessage(e));
            log.error("用户-{}验证异常",phone);
        }
        return map;
    }

    @GetMapping("/ajaxUsername")
    @ResponseBody
    public Object ajaxUsername(){
        Map<String,Object> map = new HashMap<>();
        map.put("result","0");
        String name = getRequest().getParameter("name");
        try{
            UcUser user = userManager.findByName(name);
            if(user == null){
                map.put("result","1");
                map.put("msg","此用户名可用");
            }else {
                map.put("msg","此用户名已被占用");
            }
        }catch (Exception e){
            map.put("msg",transformExceptionMessage(e));
            log.error("查询用户名是否可用异常",e.getMessage());
        }
        return map;
    }

    @PostMapping("/updateUserInfo")
    @ResponseBody
    public Object updateUserInfo(){
        Map<String,Object> map = new HashMap<>();
        map.put("result","0");
        int uid = Integer.valueOf(getRequest().getParameter("uid"));
        String uname = getRequest().getParameter("uname");
        String pwd = getRequest().getParameter("pwd");
        try{
            String securityPwd = SecurityUtil.aesEncrypt(pwd,SecurityUtil.AESPASSWORD);
            userManager.updateUserInfo(uid,uname,securityPwd);
            map.put("result","1");
            map.put("uname",uname);
        }catch (Exception e){
            log.error("保存用户uid:{},{}的信息异常,{}",uid,uname,e.getMessage());
        }
        return map;
    }

    /**
     *  设置随机字符
     * @return
     */
    public String generateVerifyCode() {
        int codeLength = 6;
        String code = "";
        Random random = new Random();
        for (int i = 0; i < codeLength; i++) {
            code += random.nextInt(10);
        }
        return code;
    }
}
