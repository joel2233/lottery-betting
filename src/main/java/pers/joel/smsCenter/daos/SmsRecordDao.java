package pers.joel.smsCenter.daos;

import pers.joel.common.daos.GenericDao;
import pers.joel.smsCenter.models.SmsRecord;

public interface SmsRecordDao extends GenericDao<SmsRecord> {
    int insert(SmsRecord smsRecord);
}