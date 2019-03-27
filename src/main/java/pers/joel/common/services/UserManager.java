package pers.joel.common.services;

import pers.joel.userCenter.models.UcUser;

public interface UserManager{
    UcUser createUser(UcUser user);

    UcUser findById(int currentUid);

    UcUser findByPhone(String phone);

    void updateUserVerifyCode(UcUser user);

    UcUser findByName(String name);

    void updateUserChecked(int uid);

    void updateUserInfo(int uid, String uname, String pwd);
}