package com.ymedia.service;

import com.ymedia.model.LoginModel;
import com.ymedia.model.PersonInfoModel;
import org.json.JSONObject;

public interface MainService {
    JSONObject getPersonInfo(String token) throws Exception;
    int savePersonInfo(PersonInfoModel personInfoModel) throws Exception;
}
