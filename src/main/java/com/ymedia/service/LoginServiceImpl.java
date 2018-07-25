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
    public String login(LoginModel loginModel) throws Exception{

        // 查数据库
        long result = loginDAO.login(loginModel);

        if(result == 0) {
            return "0";
        } else {
            return String.valueOf(result);
        }
    }

    @Override
    public int register(RegisterModel registerModel){
        //Todo write into DB

        //if(regist success)
        return 1;
        //else
        //return 0;

    }
}
