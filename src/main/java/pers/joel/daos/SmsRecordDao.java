package pers.joel.daos;

import pers.joel.models.SmsRecord;

public interface SmsRecordDao extends GenericDao<SmsRecord> {
    int insert(SmsRecord smsRecord);
}