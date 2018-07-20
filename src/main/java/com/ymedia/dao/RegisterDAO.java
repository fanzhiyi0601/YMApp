package com.ymedia.dao;

import com.ymedia.model.RegisterModel;

import java.util.List;
import java.util.Map;

public interface RegisterDAO {
    int register(RegisterModel registerModel) throws Exception;
    List<Map<String, Object>> queryUser(String username) throws Exception;
}
