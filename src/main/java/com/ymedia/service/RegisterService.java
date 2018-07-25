package com.ymedia.service;

import com.ymedia.model.RegisterModel;

public interface RegisterService {

    String register(RegisterModel registerModel) throws Exception;
    String checkUsername(String username) throws Exception;
}
