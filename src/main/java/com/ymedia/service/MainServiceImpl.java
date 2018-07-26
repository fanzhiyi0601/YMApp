package com.ymedia.service;

import com.ymedia.dao.MainDAO;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MainServiceImpl implements MainService{

    @Autowired
    MainDAO mainDAO;

    @Override
    public JSONObject getPersonInfo(String token) throws Exception{

        // 查数据库
        JSONObject result = mainDAO.getPersonInfo(token);

        if(result == null) {
            return null;
        } else {
            return result;
        }
    }
}
