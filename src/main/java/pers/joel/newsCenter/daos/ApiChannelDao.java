package pers.joel.newsCenter.daos;

import pers.joel.common.daos.GenericDao;
import pers.joel.newsCenter.models.ApiChannel;

public interface ApiChannelDao extends GenericDao<ApiChannel> {
    ApiChannel getChannelByCode(String code);
}
