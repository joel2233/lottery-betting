package pers.joel.daos.impl;

import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.stereotype.Repository;
import pers.joel.common.utils.JdbcUtil;
import pers.joel.daos.BetOrderDao;
import pers.joel.models.BetOrder;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class BetOrderDaoImpl extends GenericDaoImpl<BetOrder> implements BetOrderDao {
    public BetOrderDaoImpl() {
        super(BetOrder.class);
    }

    @Override
    public int insert(BetOrder order){
        String sql = "insert into bet_order" +
                "(lotteryType,lotteryName,code,detail,note,uid,state,createTime,expireTime,payState,total) values(" +
                JdbcUtil.getStringValue(order.getLotteryType()) + "," +
                JdbcUtil.getStringValue(order.getLotteryName()) + "," +
                JdbcUtil.getStringValue(order.getCode()) + "," +
                StringEscapeUtils.unescapeHtml4(JdbcUtil.getStringValue(order.getDetail())) + "," +
                JdbcUtil.getStringValue(order.getNote()) + "," +
                JdbcUtil.getStringValue(order.getUid()) + "," +
                JdbcUtil.getStringValue(order.getState()) + "," +
                JdbcUtil.getStringValue(order.getCreateTime()) + "," +
                JdbcUtil.getStringValue(order.getExpireTime()) + "," +
                JdbcUtil.getStringValue(order.getPayState()) + "," +
                JdbcUtil.getStringValue(order.getTotal()) +
                ")";
        update(sql);
        return getPrimaryKey();
    }

    @Override
    public List<BetOrder> getBetOrdersByUid(int uid) {
        String sql = "select * from bet_order o where o.`uid` = " + uid + " order by createTime desc";
        return getObjects(sql);
    }

    @Override
    public void setOrderExpired(int orderId) {
        String sql = "update bet_order set state = 0 where orderId = " + orderId;
        update(sql);
    }

    @Override
    public BetOrder getOrderById(int orderId) {
        String sql = "select * from bet_order o where orderId = " + orderId;
        return getAObject(sql);
    }

    @Override
    public void updateOrderState(int orderId) {
        String sql = "update bet_order set payState = 1,payTime = " + JdbcUtil.getStringValue(LocalDateTime.now()) + " where orderId = " + orderId;
        update(sql);
    }

    @Override
    public List<BetOrder> getPaidOrderByUid(int uid) {
        String sql = "select * from bet_order o where o.`uid` = " + uid + " and payState = 1 order by createTime desc";
        return getObjects(sql);
    }

    @Override
    public List<BetOrder> getLetouPaidOrders() {
        String sql = "select * from bet_order o where o.`payState` = 1 and o.`lotteryName` = '大乐透' and calFlag = 0";
        return getObjects(sql);
    }

    @Override
    public void updateOrderBonus(BetOrder order) {
        String sql = "update bet_order set prize = " + JdbcUtil.getStringValue(order.getPrize()) +
                ",calFlag = "+JdbcUtil.getStringValue(order.getCalFlag()) +
                ",bonus = " + JdbcUtil.getStringValue(order.getBonus()) +
                " where orderId = " + order.getOrderId();
        update(sql);
    }

    @Override
    public List<BetOrder> getElevenFivePaidOrders() {
        String sql = "select * from bet_order o where o.`payState` = 1 and calFlag = 0 and (o.`lotteryName` = '广东11选5' or o.`lotteryName` = '5分11选5')";
        return getObjects(sql);
    }

    @Override
    public List<BetOrder> getSsqPaidOrders() {
        String sql = "select * from bet_order o where o.`payState` = 1 and calFlag = 0 and o.`lotteryName` = '双色球'";
        return getObjects(sql);
    }

    @Override
    public List<BetOrder> getFu3dPaidOrders() {
        String sql = "select * from bet_order o where o.`payState` = 1 and calFlag = 0 and o.`lotteryName` = '福彩3D'";
        return getObjects(sql);
    }


}
