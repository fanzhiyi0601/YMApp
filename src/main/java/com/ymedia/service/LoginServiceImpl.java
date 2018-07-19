package com.ymedia.service;

import com.ymedia.dao.Impl.LoginDAOImpl;
import com.ymedia.dao.LoginDAO;
import com.ymedia.model.LoginModel;
import com.ymedia.model.RegisterModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService{

    @Autowired
    LoginDAO loginDAO;
//    LoginDAOImpl loginDAO = new LoginDAOImpl();

    @Override
    public int login(LoginModel loginModel) throws Exception{

        // 查数据库
        int result = loginDAO.login(loginModel);

        if(result == 1) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public int register(RegisterModel registerModel){
        //Todo 写入数据库

        //if(注册成功)
        return 1;
        //else
        //return 0;

    }
}
