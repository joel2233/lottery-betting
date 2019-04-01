package pers.joel.newsCenter.daos.impl;

import org.springframework.stereotype.Repository;
import pers.joel.common.daos.impl.GenericDaoImpl;
import pers.joel.newsCenter.daos.ApiChannelDao;
import pers.joel.newsCenter.models.ApiChannel;

@Repository
public class ApiChannelDaoImpl extends GenericDaoImpl<ApiChannel> implements ApiChannelDao {
    public ApiChannelDaoImpl() {
        super(ApiChannel.class);
    }

    @Override
    public ApiChannel getChannelByCode(String code){
        String sql = "select * from api_channel a where a.`apicode` = '"+code+"' limit 1";
        return getAObject(sql);
    }
}
