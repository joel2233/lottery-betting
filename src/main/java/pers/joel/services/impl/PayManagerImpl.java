package pers.joel.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.joel.daos.UcUserDao;
import pers.joel.services.PayManager;

@Service
public class PayManagerImpl implements PayManager {
    @Autowired
    private UcUserDao userDao;

    @Override
    public void updateUserMoney(int uid, Double money) {
        userDao.updateUserMoney(uid,money);
    }
}
