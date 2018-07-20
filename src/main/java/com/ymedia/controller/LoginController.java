package com.ymedia.controller;

import com.google.gson.Gson;
import com.ymedia.service.LoginService;
import com.ymedia.service.LoginServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.ymedia.model.LoginModel;
import com.ymedia.model.RegisterModel;

import javax.servlet.http.HttpServletRequest;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    LoginService loginService;
//    LoginServiceImpl loginService = new LoginServiceImpl();

    Gson gson = new Gson();


    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public String login(@RequestBody  String request) throws Exception{
//        loginModel.setUsername("lishuopu");
//        loginModel.setPassword("123");
        String param = URLDecoder.decode(request, "utf-8");
        LoginModel loginModel = new LoginModel();
        loginModel = gson.fromJson(param.substring(0,param.length()-1), LoginModel.class);
        int result = loginService.login(loginModel);
        if(result==1) {
            return "success";
        }else{
            return "failed";
        }
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    public String home() {

        Map map = new HashMap();

        map.put("msg", "1");

        map.put("data", "2");

        return "home";
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
