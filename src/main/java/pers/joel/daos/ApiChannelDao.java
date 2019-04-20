package pers.joel.daos;

import pers.joel.models.ApiChannel;

public interface ApiChannelDao extends GenericDao<ApiChannel> {
    ApiChannel getChannelByCode(String code);
}
