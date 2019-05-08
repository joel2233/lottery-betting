package pers.joel.models;

import java.time.LocalDateTime;

public class LotteryResult {
    public final static String WUFEN = "WF";            //code从 ********001 —— ********500  前八位代表年月日    5min一期
    public final static String GUANGDONG = "GD";        //code从 ********501 —— ********999  前八位代表年月日    20min一期
    public final static String DALETOU = "DLT";         //code从 ****001 —— ****100          前四位代表年月      每周一、三、六 20:30开奖
    public final static String SHUANGSEQIU = "SSQ";     //code从 ****101 —— ****200          前四位代表年月      每周二、四、日 21:30开奖
    public final static String FUCAI3D = "F3D";         //code从 ****201 —— ****600          前四位代表年份      每天21:00开奖

    private int id;
    private String code; //期号
    private String result; //本期开奖号码
    private LocalDateTime beginTime; //本期开始时间
    private LocalDateTime endTime; //本期结束时间
    private String type; //11选5类型 GD / WF

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public LocalDateTime getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(LocalDateTime beginTime) {
        this.beginTime = beginTime;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }
}
