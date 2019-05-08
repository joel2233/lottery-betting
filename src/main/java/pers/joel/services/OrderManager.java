package pers.joel.services;

import pers.joel.models.BetOrder;

import java.util.List;

public interface OrderManager {

    int insert(BetOrder betOrder);

    List<BetOrder> getBetOrdersByUid(int uid);

    void setOrderExpired(int orderId);

    BetOrder getOrderById(int orderId);

    void updateOrderState(int orderId);

    List<BetOrder> getPaidOrdersByUid(int uid);

    List<BetOrder> getLetouPaidOrders();

    void updateOrderBonus(BetOrder order);

    List<BetOrder> getElevenFivePaidOrders();

    List<BetOrder> getSsqPaidOrders();

    List<BetOrder> getFu3dPaidOrders();
}
