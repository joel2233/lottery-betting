package pers.joel.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.joel.daos.BetOrderDao;
import pers.joel.models.BetOrder;
import pers.joel.services.OrderManager;

import java.util.List;

@Service
public class OrderManagerImpl implements OrderManager {

    @Autowired
    private BetOrderDao orderDao;
    @Override
    public int insert(BetOrder order){
        return orderDao.insert(order);
    }

    @Override
    public List<BetOrder> getBetOrdersByUid(int uid) {
        return orderDao.getBetOrdersByUid(uid);
    }

    @Override
    public void setOrderExpired(int orderId) {
        orderDao.setOrderExpired(orderId);
    }

    @Override
    public BetOrder getOrderById(int orderId) {
        return orderDao.getOrderById(orderId);
    }

    @Override
    public void updateOrderState(int orderId) {
        orderDao.updateOrderState(orderId);
    }

    @Override
    public List<BetOrder> getPaidOrdersByUid(int uid) {
        return orderDao.getPaidOrderByUid(uid);
    }

    @Override
    public List<BetOrder> getLetouPaidOrders() {
        return orderDao.getLetouPaidOrders();
    }

    @Override
    public void updateOrderBonus(BetOrder order) {
        orderDao.updateOrderBonus(order);
    }

    @Override
    public List<BetOrder> getElevenFivePaidOrders() {
        return orderDao.getElevenFivePaidOrders();
    }

    @Override
    public List<BetOrder> getSsqPaidOrders() {
        return orderDao.getSsqPaidOrders();
    }

    @Override
    public List<BetOrder> getFu3dPaidOrders() {
        return orderDao.getFu3dPaidOrders();
    }
}
