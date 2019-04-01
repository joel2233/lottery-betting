package pers.joel.common.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.joel.common.services.NewsManager;
import pers.joel.newsCenter.daos.ApiChannelDao;
import pers.joel.newsCenter.models.ApiChannel;

@Service
public class NewsManagerImpl implements NewsManager {

    @Autowired
    private ApiChannelDao apiChannelDao;
    @Override
    public ApiChannel getChannelByCode(String code){
        return apiChannelDao.getChannelByCode(code);
    }
}
