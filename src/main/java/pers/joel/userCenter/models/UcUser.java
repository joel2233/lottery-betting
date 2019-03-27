package pers.joel.userCenter.models;

import java.time.LocalDateTime;

public class UcUser {
    public static final int PHONE_CHECKED = 1;
    public static final int PHONE_UNCHECKED = 0;
    private int uid;
    private String uname;
    private String phone;
    private String password;
    private String verifyCode;
    private int checked;
    private LocalDateTime regieterTime;
    private int bUser;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getChecked() {
        return checked;
    }

    public void setChecked(int checked) {
        this.checked = checked;
    }

    public LocalDateTime getRegieterTime() {
        return regieterTime;
    }

    public void setRegieterTime(LocalDateTime regieterTime) {
        this.regieterTime = regieterTime;
    }

    public int getbUser() {
        return bUser;
    }

    public void setbUser(int bUser) {
        this.bUser = bUser;
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }
}
