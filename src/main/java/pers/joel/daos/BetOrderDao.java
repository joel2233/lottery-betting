package pers.joel.daos;

import pers.joel.models.BetOrder;

import java.util.List;

public interface BetOrderDao {
    int insert(BetOrder order);

    List<BetOrder> getBetOrdersByUid(int uid);
}
