package com.ymedia.service;

import com.ymedia.dao.MainDAO;

import com.ymedia.model.PersonInfoModel;
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

    @Override
    public int savePersonInfo(PersonInfoModel personInfoModel) throws Exception{

        // 查数据库
        int result = mainDAO.savePersonInfo(personInfoModel);

        if(result == 1) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public String getOnline(String token) throws Exception{

        // 查数据库
        String result = mainDAO.getOnline(token);

        return result;
    }
}
