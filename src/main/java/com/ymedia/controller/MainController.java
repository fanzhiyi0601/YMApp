package com.ymedia.controller;

import com.ymedia.service.MainService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.URLDecoder;

@Controller
@RequestMapping("/main")
public class MainController {

    @Autowired
    MainService mainService;

    @RequestMapping(value = "/getPersonInfo", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject login(@RequestBody String request) throws Exception{

        String param = URLDecoder.decode(request, "utf-8");
        String param1 = param.substring(0,param.length()-1);

        JSONObject result = mainService.getPersonInfo(param1);
        if(result == null) {
            return null;
        }else{
            return result;
        }
    }
}
