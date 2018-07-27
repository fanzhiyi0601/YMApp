package com.ymedia.dao;


import com.ymedia.model.PersonInfoModel;
import org.json.JSONObject;

public interface MainDAO {
    JSONObject getPersonInfo(String token) throws Exception;
    int savePersonInfo(PersonInfoModel personInfoModel) throws Exception;
}
