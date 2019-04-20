package pers.joel.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.joel.daos.ApiChannelDao;
import pers.joel.models.ApiChannel;
import pers.joel.services.NewsManager;

@Service
public class NewsManagerImpl implements NewsManager {

    @Autowired
    private ApiChannelDao apiChannelDao;
    @Override
    public ApiChannel getChannelByCode(String code){
        return apiChannelDao.getChannelByCode(code);
    }
}
