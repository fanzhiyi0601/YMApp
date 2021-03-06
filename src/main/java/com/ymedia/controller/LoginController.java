package com.ymedia.controller;

import com.google.gson.Gson;
import com.ymedia.service.LoginService;
import com.ymedia.service.LoginServiceImpl;
import com.ymedia.service.RegisterService;
import com.ymedia.utils.Constant;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.ymedia.model.LoginModel;
import com.ymedia.model.RegisterModel;

import javax.servlet.http.HttpServletRequest;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
@RequestMapping("/login")
public class LoginController {

    private Logger logger = LoggerFactory.getLogger(LoginController.class);


    @Autowired
    LoginService loginService;

    @Autowired
    RegisterService registerService;

    Gson gson = new Gson();


    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public String login(@RequestBody  String request) throws Exception{

        String param = URLDecoder.decode(request, "utf-8");
        LoginModel loginModel;
        loginModel = gson.fromJson(param, LoginModel.class);

        logger.info(loginModel.getUsername()+"登录中！");

        String result = loginService.login(loginModel);
        if(result.equals("0")) {
            return "failed";
        }else{
            return result;
        }
    }


    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public String register(@RequestBody String request) throws Exception{
        String param = URLDecoder.decode(request, "utf-8");
        RegisterModel registerModel;
        String param1 = param.substring(0,param.length()-1);
        registerModel = gson.fromJson(param1, RegisterModel.class);

        logger.info(registerModel.getUsername()+"注册中！");

        String result = registerService.register(registerModel);

        if(result.equals(Constant.REGISTER_SUCCESS)) {
            return "success";
        }
        else if(result.equals(Constant.REGISTER_FAILED)){
            return "fail";
        }
            return null;
    }

    @RequestMapping(value = "/check", method = RequestMethod.POST)
    @ResponseBody
    public String check(@RequestBody String request) throws Exception{
        String param = URLDecoder.decode(request, "utf-8");
        String param1 = param.substring(0,param.length()-1);
        String result = registerService.checkUsername(param1);

        if(result.equals(Constant.REGISTER_EXIST)){
            return "exist";
        }else{
            return "Unexsit";
        }
    }
}
