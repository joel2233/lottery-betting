package pers.joel.daos.impl;

import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.stereotype.Repository;
import pers.joel.common.utils.JdbcUtil;
import pers.joel.daos.BetOrderDao;
import pers.joel.models.BetOrder;

import java.util.List;

@Repository
public class BetOrderDaoImpl extends GenericDaoImpl<BetOrder> implements BetOrderDao {
    public BetOrderDaoImpl() {
        super(BetOrder.class);
    }

    @Override
    public int insert(BetOrder order){
        String sql = "insert into bet_order" +
                "(lotteryType,lotteryName,detail,uid,state,createTime,expireTime,payState,total) values(" +
                JdbcUtil.getStringValue(order.getLotteryType()) + "," +
                JdbcUtil.getStringValue(order.getLotteryName()) + "," +
                StringEscapeUtils.unescapeHtml4(JdbcUtil.getStringValue(order.getDetail())) + "," +
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
        String sql = "select * from bet_order o where o.`uid` = " + uid;
        return getObjects(sql);
    }


}
