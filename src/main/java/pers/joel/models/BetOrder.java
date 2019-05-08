package pers.joel.models;

import java.time.LocalDateTime;

public class BetOrder {
    public final static int FUCAI = 1;
    public final static int TICAI = 2;
    public final static int ORDER_VALID = 1;
    public final static int ORDER_INVALID = 0;
    public final static int ORDER_NOT_PAID = 0;
    public final static int ORDER_PAID = 1;

    private int orderId;
    private int lotteryType; // 1-福彩 2-体彩
    private String lotteryName; //彩种名称 如双色球
    private String detail; //体彩是比赛信息Json，大乐透是前区和后区号码
    private int uid; //订单所属用户id
    private int state; //方案是否有效（过期） 1-有效 0-无效
    private LocalDateTime createTime; //订单创建时间
    private LocalDateTime expireTime; //方案过期时间
    private LocalDateTime payTime; //订单支付时间
    private int note; //注数
    private int payState; //订单支付状态 0-未支付 1-已支付
    private Double total; //订单总额
    private String code; //期号
    private int end; //标识当期投注是否截止
    private LotteryResult lotteryResult; //本期开奖结果
    private int calFlag; //是否计算过奖金
    private int prize;
    private Double bonus;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getLotteryType() {
        return lotteryType;
    }

    public void setLotteryType(int lotteryType) {
        this.lotteryType = lotteryType;
    }

    public String getLotteryName() {
        return lotteryName;
    }

    public void setLotteryName(String lotteryName) {
        this.lotteryName = lotteryName;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(LocalDateTime expireTime) {
        this.expireTime = expireTime;
    }

    public int getPayState() {
        return payState;
    }

    public void setPayState(int payState) {
        this.payState = payState;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public LocalDateTime getPayTime() {
        return payTime;
    }

    public void setPayTime(LocalDateTime payTime) {
        this.payTime = payTime;
    }

    public LotteryResult getLotteryResult() {
        return lotteryResult;
    }

    public void setLotteryResult(LotteryResult lotteryResult) {
        this.lotteryResult = lotteryResult;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public int getCalFlag() {
        return calFlag;
    }

    public void setCalFlag(int calFlag) {
        this.calFlag = calFlag;
    }

    public int getPrize() {
        return prize;
    }

    public void setPrize(int prize) {
        this.prize = prize;
    }

    public Double getBonus() {
        return bonus;
    }

    public void setBonus(Double bonus) {
        this.bonus = bonus;
    }
}
