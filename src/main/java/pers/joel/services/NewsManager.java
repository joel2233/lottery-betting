package pers.joel.services;

import pers.joel.models.ApiChannel;
import pers.joel.models.LotteryResult;

public interface NewsManager {
    ApiChannel getChannelByCode(String code);

    LotteryResult getLatestLotteryResult(String type);

    LotteryResult getLastLotteryResult(String type);

    void insertNewLotteryResult(LotteryResult eFive);

    /**
     * 根据期号获取当期开奖结果
     * @param code
     * @return
     */
    LotteryResult getResultByCode(String code);
}
