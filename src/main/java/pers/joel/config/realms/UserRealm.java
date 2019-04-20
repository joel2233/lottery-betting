package pers.joel.config.realms;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import pers.joel.common.utils.SecurityUtil;
import pers.joel.models.UcUser;
import pers.joel.services.UserManager;

public class UserRealm extends AuthorizingRealm {


    @Autowired
    private UserManager userManager;

    @Override
    public String getName() {
        return "UserRealm";
    }

    /**
     * 授权用户权限
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        UcUser user = (UcUser) SecurityUtils.getSubject().getPrincipal();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        return info;
    }

    /**
     * 验证用户身份
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        String username = (String) token.getPrincipal();
        UcUser user = userManager.findByPhone(username);
        // 账号不存在
        if (user == null) {
            throw new UnknownAccountException("账号或密码不正确");
        }
        Object credentials = token.getCredentials();
        if (credentials == null) {
            throw new UnknownAccountException("账号或密码不正确");
        }
        String password = new String((char[]) credentials);
        // 密码错误
        if (!SecurityUtil.aesEncrypt(password,SecurityUtil.AESPASSWORD).equals(user.getPassword())) {
            throw new IncorrectCredentialsException("账号或密码不正确");
        }
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, password, getName());

        return info;
    }

    /**
     * 将一些数据放到ShiroSession中,以便于其它地方使用,将用户存放到session中
     * @see  // 比如Controller,使用时直接用HttpSession.getAttribute(key)就可以取到
     */
    private void setSession(Object key, Object value){
        Subject currentUser = SecurityUtils.getSubject();
        if(currentUser != null){
            Session session = currentUser.getSession();
            System.out.println(session.getId());
            if(null != session){
                // 2小时
                session.setTimeout(7200000);
                session.setAttribute(key, value);
            }
        }
    }
}
