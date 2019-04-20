package pers.joel.services;

import pers.joel.models.BetOrder;

import java.util.List;

public interface OrderManager {

    int insert(BetOrder betOrder);

    List<BetOrder> getBetOrdersByUid(int uid);
}
