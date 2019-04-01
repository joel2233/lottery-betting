package pers.joel.common.services;

import pers.joel.newsCenter.models.ApiChannel;

public interface NewsManager {
    ApiChannel getChannelByCode(String code);
}
