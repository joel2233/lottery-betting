package pers.joel.common.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.joel.common.services.UserManager;
import pers.joel.userCenter.daos.UcUserDao;
import pers.joel.userCenter.models.UcUser;

@Service
public class UserManagerImpl implements UserManager {

    protected transient final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private UcUserDao userDao;

    @Override
    public UcUser createUser(UcUser user){
        return userDao.createUser(user);
    }

    @Override
    public UcUser findById(int uid){
        return userDao.findById(uid);
    }

    @Override
    public UcUser findByPhone(String phone){
        return userDao.findByPhone(phone);
    }

    @Override
    public void updateUserVerifyCode(UcUser user){
        userDao.updateUserVerifyCode(user);
    }

    @Override
    public UcUser findByName(String name){
        return userDao.findByName(name);
    }

    @Override
    public void updateUserChecked(int uid){
        userDao.updateUserChecked(uid);
    }

    @Override
    public void updateUserInfo(int uid, String uname, String pwd){
        userDao.updateUserInfo(uid, uname, pwd);
    }
}