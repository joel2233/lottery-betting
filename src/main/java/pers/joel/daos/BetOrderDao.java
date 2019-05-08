package pers.joel.daos;

import pers.joel.models.BetOrder;

import java.util.List;

public interface BetOrderDao {
    int insert(BetOrder order);

    List<BetOrder> getBetOrdersByUid(int uid);

    void setOrderExpired(int orderId);

    BetOrder getOrderById(int orderId);

    void updateOrderState(int orderId);

    List<BetOrder> getPaidOrderByUid(int uid);

    List<BetOrder> getLetouPaidOrders();

    void updateOrderBonus(BetOrder order);

    List<BetOrder> getElevenFivePaidOrders();

    List<BetOrder> getSsqPaidOrders();

    List<BetOrder> getFu3dPaidOrders();
}
