package pers.joel.daos;

import pers.joel.models.LotteryResult;

public interface LotteryResultDao {
    LotteryResult getLatestLotteryResult(String type);

    void insertNewLotteryResult(LotteryResult eFive);

    LotteryResult getLastLotteryResult(String type);

    LotteryResult getResultByCode(String code);
}
