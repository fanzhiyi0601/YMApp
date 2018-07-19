package com.ymedia.controller;

import com.ymedia.service.LoginService;
import com.ymedia.service.LoginServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.ymedia.model.LoginModel;
import com.ymedia.model.RegisterModel;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;


@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    LoginService loginService;
//    LoginServiceImpl loginService = new LoginServiceImpl();

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    @ResponseBody
    public String create() throws Exception{
        LoginModel loginModel = new LoginModel();
        loginModel.setUsername("fanzhiyi");
        loginModel.setPassword("123");
        int result = loginService.login(loginModel);
        if(result==1) {
            return "success";
        }else{
            return "failed";
        }
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    public String login() {

        Map map = new HashMap();

        map.put("msg", "����ɹ�");

        map.put("data", "�������ǿ�");

        return "login";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@RequestBody RegisterModel registerModel){
        int result = loginService.register(registerModel);

        if(result==1) {
            return "success";
        }else{
            return "failed";
        }
    }
}
