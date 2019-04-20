package pers.joel.services;

import pers.joel.models.ApiChannel;

public interface NewsManager {
    ApiChannel getChannelByCode(String code);
}
