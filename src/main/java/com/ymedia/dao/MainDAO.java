package com.ymedia.dao;


import org.json.JSONObject;

public interface MainDAO {
    JSONObject getPersonInfo(String token) throws Exception;
}
