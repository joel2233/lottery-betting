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
}
