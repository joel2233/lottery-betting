package pers.joel.daos;

import pers.joel.models.UcUser;

public interface UcUserDao extends GenericDao<UcUser> {
    /**
     * 创建用户
     */
    UcUser createUser(UcUser user);

    UcUser findById(int uid);

    UcUser findByPhone(String phone);

    void updateUserVerifyCode(UcUser user);

    UcUser findByName(String name);

    void updateUserChecked(int uid);

    void updateUserInfo(int uid, String uname, String pwd);

    void updateUserMoney(int uid, Double money);
}
