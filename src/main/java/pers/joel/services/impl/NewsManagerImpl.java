package pers.joel.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.joel.daos.ApiChannelDao;
import pers.joel.daos.LotteryResultDao;
import pers.joel.models.ApiChannel;
import pers.joel.models.LotteryResult;
import pers.joel.services.NewsManager;

@Service
public class NewsManagerImpl implements NewsManager {

    @Autowired
    private ApiChannelDao apiChannelDao;
    @Autowired
    private LotteryResultDao resultDao;
    @Override
    public ApiChannel getChannelByCode(String code){
        return apiChannelDao.getChannelByCode(code);
    }

    @Override
    public LotteryResult getLatestLotteryResult(String type) {
        return resultDao.getLatestLotteryResult(type);
    }

    @Override
    public LotteryResult getLastLotteryResult(String type) {
        return resultDao.getLastLotteryResult(type);
    }

    @Override
    public void insertNewLotteryResult(LotteryResult result) {
        resultDao.insertNewLotteryResult(result);
    }

    @Override
    public LotteryResult getResultByCode(String code) {
        return resultDao.getResultByCode(code);
    }

}
