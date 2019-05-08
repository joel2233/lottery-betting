package pers.joel.daos.impl;

import org.springframework.stereotype.Repository;
import pers.joel.common.utils.JdbcUtil;
import pers.joel.daos.LotteryResultDao;
import pers.joel.models.LotteryResult;

@Repository
public class LotteryResultDaoImpl extends GenericDaoImpl<LotteryResult> implements LotteryResultDao {
    public LotteryResultDaoImpl() {
        super(LotteryResult.class);
    }

    @Override
    public LotteryResult getLatestLotteryResult(String type) {
        String sql = "select * from lottery_result e where e.`type` = '" + type + "' order by id desc limit 1";
        return getAObject(sql);
    }

    @Override
    public void insertNewLotteryResult(LotteryResult result) {
        String sql = "insert into lottery_result(code,result,beginTime,endTime,type) values(" +
                JdbcUtil.getStringValue(result.getCode()) + "," +
                JdbcUtil.getStringValue(result.getResult()) + "," +
                JdbcUtil.getStringValue(result.getBeginTime()) + "," +
                JdbcUtil.getStringValue(result.getEndTime()) + "," +
                JdbcUtil.getStringValue(result.getType()) + ")";
        update(sql);
    }

    @Override
    public LotteryResult getLastLotteryResult(String type) {
        String sql = "select * from lottery_result e where e.`type` = '" + type + "' order by id desc limit 1,1";
        return getAObject(sql);
    }

    @Override
    public LotteryResult getResultByCode(String code) {
        String sql = "select * from lottery_result e where e.`code` = '" + code + "'";
        return getAObject(sql);
    }
}
