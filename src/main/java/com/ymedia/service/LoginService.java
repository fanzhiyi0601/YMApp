package com.ymedia.service;

import com.ymedia.model.LoginModel;
import com.ymedia.model.RegisterModel;

public interface LoginService {

    int login(LoginModel loginModel) throws Exception;
    int register(RegisterModel registerModel);
}
