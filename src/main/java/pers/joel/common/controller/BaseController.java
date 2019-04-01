package pers.joel.common.controller;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import pers.joel.common.services.UserManager;
import pers.joel.common.utils.JSONUtil;
import pers.joel.userCenter.models.UcUser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Implementation of <strong>ActionSupport</strong> that contains convenience
 * methods for subclasses. For example, getting the current user and saving
 * messages/errors. This class is intended to be a base class for all Action
 * classes.<br/>
 * <br/>
 * class 上加<br/>
 * &nbsp;&nbsp;{@literal @}ParentPackage("struts-default")  //表示继承的父包<br/>
 * &nbsp;&nbsp;{@literal @}Namespace(value="/user") //表示当前Action所在命名空间<br/>
 * <br/>
 * method 上加<br/>
 * &nbsp;&nbsp;{@literal @}Action( //表示请求的Action及处理方法<br/>
 * &nbsp;&nbsp;&nbsp;&nbsp;value="login",  //表示action的请求名称<br/>
 * &nbsp;&nbsp;&nbsp;&nbsp;results={  //表示结果跳转<br/>
 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@literal @}Result(name="success",location="/success.jsp",type="redirect"),<br/>
 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@literal @}Result(name="login",location="/login.jsp",type="redirect"),<br/>
 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@literal @}Result(name="error",location="/error.jsp",type="redirect")<br/>
 * &nbsp;&nbsp;&nbsp;&nbsp;},<br/>
 * &nbsp;&nbsp;&nbsp;&nbsp;interceptorRefs={ //表示拦截器引用<br/>
 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@literal @}InterceptorRef("defaultStack"),<br/>
 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@literal @}InterceptorRef("timer")<br/>
 * &nbsp;&nbsp;&nbsp;&nbsp;},<br/>
 * &nbsp;&nbsp;&nbsp;&nbsp;exceptionMappings={  //映射映射声明<br/>
 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@literal @}ExceptionMapping(exception="java.lang.Exception",result="error")<br/>
 * &nbsp;&nbsp;&nbsp;&nbsp;}<br/>
 * &nbsp;&nbsp;)<br/>
 * <br/>
 *
 * @author <a href="mailto:javaworld@qq.com">Woody</a> on 2014-06-09
 * @since 1.0
 */
@Controller
public abstract class BaseController{
    public static String baseDir = "";
    public static String companyTemplateName = "";
    protected static final String AJAX = "ajax";
    protected static final String HEADER = "header";
    protected static final String ERROR = "/common/error";
    protected static final String RESULT = "result";
    protected static final String MESSAGE = "message";
    protected static final String ERRORMESSAGE = "errmsg";
    protected static final String MOBILE_MESSAGE = "mobile_message";
    protected static final String MESSAGE2C = "message2c";

    protected static final String EXTRAMESSAGE = "extraMessage";

    protected static final String SUCCESS = "success";
    protected static final String MOBILE_SUCCESS = "mobile_success";
    protected static final String TOURGUIDE_SUCCESS = "tourguide_success";
    protected static final String WEIXIN_SUCCESS = "weixin_success";

//    private static final String location = "/WEB-INF/content";
    /**
     * Transient log to prevent session synchronization issues - children can
     * use instance for logging.
     */
    protected final transient Logger log = LoggerFactory.getLogger(getClass());
    protected String sysTitle;

    /**
     * （不断扩充中）
     * 以用户更易理解的方式显示转换异常信息
     */
    protected String transformExceptionMessage(Exception e) {
        if (e.getClass().toString().toLowerCase().contains("sql")) {
            return "数据库操作错误";
        } else {
            return e.getMessage();
        }
    }

    protected void saveMessage(String msg) {
        getRequest().setAttribute(MESSAGE, msg);
    }
    protected void saveErrorMessage(String msg) {
        getRequest().setAttribute(ERRORMESSAGE, msg);
    }

    protected void saveMessage(String msg, String extraMsg) {
        getRequest().setAttribute(MESSAGE, msg);
        getRequest().setAttribute(EXTRAMESSAGE, extraMsg);
    }

    protected void saveStatus(int status) {
        getRequest().setAttribute("status", status);
    }

    protected void clearMessage() {
        getRequest().removeAttribute(MESSAGE);
    }

    /**
     * Convenience method to get the request
     *
     * @return current request
     */
    protected HttpServletRequest getRequest() {

        return ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
    }

    /**
     * Convenience method to get the response
     *
     * @return current response
     */
    protected HttpServletResponse getResponse() {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        return servletRequestAttributes.getResponse();
    }

    /**
     * Convenience method to get the session. This will create a session if one
     * doesn't exist.
     *
     * @return the shiro session, not from the request (request.getSession()).
     */
    protected Session getSession() {
        return SecurityUtils.getSubject().getSession();
    }
//
//    public String getLocation() {
//        return location;
//    }

    public String getBaseDir() {
        return getRequest().getContextPath();
    }

    /**
     * 用于当前保险公司的资源路径（部分）名
     * 不要用来做保险公司的判断
     */


    public String getSysTitle() {
        return sysTitle;
    }

    public void setSysTitle(String title) {
        getRequest().setAttribute("title",title);
    }
//
//    @Autowired
//    protected EditableConfigManager editableConfigManager;


    /**
     * 记录action的业务操作、数据信息等
     */
    protected void saveActLog(String detail) {
        String actLogDetail = detail;
        getRequest().setAttribute("actLogDetail", actLogDetail);
    }


    /**
     * 通过session里面存的权限信息确定是否有相应操作的权限
     *
     * @param action
     * @return
     */
    protected boolean uCan(String action) {
        if (!uCanB(action)) {
            saveMessage("您没有权限 ！！");
            return false;
        } else {
            return true;
        }
    }

    protected boolean uCanAjax(String action) {
        if (!uCanB(action)) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("result", 0);
            map.put("message", "您没有权限 ！！");
            saveMessage(JSONUtil.toJson(map));
            return false;
        } else {
            return true;
        }
    }

    private boolean uCanB(String action) {
        boolean needCheckDataPermissions = false;//是否需要检查数据权限
        UcUser user = getCurrentUser();
//        if (user.getCurrentIdentity() == -1 &&
//                user.getDataPermissions() != null && user.getDataPermissions().get(1) != null) {
//            String dataPermDetail = "|" + user.getDataPermissions().get(1).getDataPermDetail() + "|";
//            if (!dataPermDetail.contains("|" + user.getCurrentSubCompany() + "|")) {
//                needCheckDataPermissions = true;
//            }
//        }
        if (needCheckDataPermissions) {
            // 当前为分公司身份，且分公司不在该用户的分公司数据权限内
            // 此时需要判断该功能权限是否绑定数据权限，以及绑定类型（dataOnly or data&func）
            // 若绑定类型为功能和数据控制，则认定为无该功能权限
            return SecurityUtils.getSubject().isPermitted(action + "+function");
        } else {
            return SecurityUtils.getSubject().isPermitted(action);
        }
    }

    //仅获取当前用户基本信息，用于判别用户
    protected UcUser getCurrentUser() {
        return (UcUser) SecurityUtils.getSubject().getPrincipal();
    }

    //获取当前登录用户身份id
    public int getCurrentUid() {
        int uid = 0;
        if (getCurrentUser() != null) {
            uid = getCurrentUser().getUid();
        }
        return uid;
    }

    /**
     * 获取当前用户信息(从数据库获取)
     */
    public UcUser getCurrentUserInfo() {
        return userManager.findById(getCurrentUid());
    }

//    protected boolean isBUser() {
//        Subject subject = SecurityUtils.getSubject();
//
//        Object o = subject.getPrincipal();
//        if (o == null || !(o instanceof UcUser))
//            return false;
//
//        UcUser user = (UcUser) o;
//
//        if (user.getCurrentIdentity() == -1) {
//            return true;
//        }
//
//        return user.getIdentities().get(user.getCurrentIdentity()).isbUser();
//    }

    @Autowired
    protected UserManager userManager;

    /**
     * B端移动端页面请求判断
     */
    public boolean isMobileRequestB() {
        final String userAgent = getRequest().getHeader("USER-AGENT");
        boolean device = false; //设备（是否移动端）
        if (StringUtils.isNotBlank(userAgent)) {
            //Pattern pattern=Pattern.compile("iPhone|iPad|Android|Windows Phone|BlackBerry", Pattern.CASE_INSENSITIVE);
            Pattern pattern = Pattern.compile("iPhone|Android|Windows Phone|BlackBerry", Pattern.CASE_INSENSITIVE);
            device = pattern.matcher(userAgent).find();
        }
        if (device) { //Mobile device
            return !"1".equals(getSession().getAttribute("PCMode"));
        } else { //PC device
            return false;
        }
    }

    /**
     * 微信浏览器内页面请求判断(UA)
     */
    public boolean isWeixinRequest() {
        final String userAgent = getRequest().getHeader("USER-AGENT");
        if (StringUtils.isNotBlank(userAgent)) {
            Pattern pattern = Pattern.compile("MicroMessenger", Pattern.CASE_INSENSITIVE);
            return pattern.matcher(userAgent).find();
        }
        return false;
    }

    /**
     * B端action根据设备请求返回对应result（默认）
     */
    protected String resultByDeviceB() {
        return isMobileRequestB() ? MOBILE_SUCCESS : SUCCESS;
    }

    /**
     * B端action根据设备请求返回对应result
     */
    protected String resultByDeviceB(String pcResult, String mobileResult) {
        return isMobileRequestB() ? mobileResult : pcResult;
    }
}
