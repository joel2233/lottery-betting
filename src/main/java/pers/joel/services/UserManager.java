package pers.joel.services;

import pers.joel.models.UcUser;

public interface UserManager{
    UcUser createUser(UcUser user);

    UcUser findById(int currentUid);

    UcUser findByPhone(String phone);

    void updateUserVerifyCode(UcUser user);

    UcUser findByName(String name);

    void updateUserChecked(int uid);

    void updateUserInfo(int uid, String uname, String pwd);

//    void updateUserMoney(int uid, Double money);
}