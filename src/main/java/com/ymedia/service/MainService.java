package com.ymedia.service;

import com.ymedia.model.LoginModel;
import org.json.JSONObject;

public interface MainService {
    JSONObject getPersonInfo(String token) throws Exception;
}
