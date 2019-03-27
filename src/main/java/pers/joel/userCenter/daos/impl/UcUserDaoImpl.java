package pers.joel.userCenter.daos.impl;

import org.springframework.stereotype.Repository;
import pers.joel.common.daos.impl.GenericDaoImpl;
import pers.joel.common.utils.JdbcUtil;
import pers.joel.userCenter.daos.UcUserDao;
import pers.joel.userCenter.models.UcUser;

@Repository
public class UcUserDaoImpl extends GenericDaoImpl<UcUser> implements UcUserDao{
    public UcUserDaoImpl() {
        super(UcUser.class);
    }

    @Override
    public UcUser createUser(UcUser user){
        String sql = "insert into uc_users(phone,verifycode) values(" +
                JdbcUtil.getStringValue(user.getPhone())+","+
                JdbcUtil.getStringValue(user.getVerifyCode())+")";
        update(sql);
        user.setUid(getPrimaryKey());
        return user;
    }

    @Override
    public UcUser findById(int uid){
        String sql = "select * from uc_users u where u.`uid` = " + uid + " limit 1";
        return getAObject(sql);
    }

    @Override
    public UcUser findByPhone(String phone){
        String sql = "select * from uc_users u where u.`phone` = '" + phone + "' limit 1";
        return getAObject(sql);
    }

    @Override
    public void updateUserVerifyCode(UcUser user){
        String sql = "update uc_users u set verifycode = " + JdbcUtil.getStringValue(user.getVerifyCode()) + " where u.`uid` = " + JdbcUtil.getStringValue(user.getUid());
        update(sql);
    }

    @Override
    public UcUser findByName(String name){
        String sql = "select * from uc_users u where u.`uname` = '" + name + "' limit 1";
        return getAObject(sql);
    }

    @Override
    public void updateUserChecked(int uid){
        String sql = "update uc_users u set checked = 1 where u.`uid` = " + uid;
        update(sql);
    }

    @Override
    public void updateUserInfo(int uid, String uname, String pwd){
        String sql = "update uc_users u set uname = '" + uname + "'," +
                "password = '" + pwd + "' where u.`uid` = " + uid;
        update(sql);
    }
}
