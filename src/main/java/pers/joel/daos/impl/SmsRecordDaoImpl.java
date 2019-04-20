package pers.joel.daos.impl;

import org.springframework.stereotype.Repository;
import pers.joel.common.utils.JdbcUtil;
import pers.joel.daos.SmsRecordDao;
import pers.joel.models.SmsRecord;

@Repository
public class SmsRecordDaoImpl extends GenericDaoImpl<SmsRecord> implements SmsRecordDao {
    protected SmsRecordDaoImpl (){super(SmsRecord.class);}
    @Override
    public int insert(SmsRecord smsRecord){
        String sql = "insert into sms_record(uid,phone,content,sendtime,result,description) values(" +
                JdbcUtil.getStringValue(smsRecord.getUid()) + "," +
                JdbcUtil.getStringValue(smsRecord.getPhone()) + "," +
                JdbcUtil.getStringValue(smsRecord.getContent()) + "," +
                JdbcUtil.getStringValue(smsRecord.getSendTime()) + "," +
                JdbcUtil.getStringValue(smsRecord.getResult()) + "," +
                JdbcUtil.getStringValue(smsRecord.getDescription()) + ")";
        update(sql);
        return getPrimaryKey();
    }
}
