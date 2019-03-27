package pers.joel.userCenter.daos;

import pers.joel.common.daos.GenericDao;
import pers.joel.userCenter.models.UcUser;

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
}
