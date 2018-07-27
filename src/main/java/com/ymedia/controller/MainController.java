package com.ymedia.controller;

import com.google.gson.Gson;
import com.ymedia.model.PersonInfoModel;
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
@RequestMapping(value = "/main", produces ="application/json;charset=UTF-8")
public class MainController {

    @Autowired
    MainService mainService;

    Gson gson = new Gson();

    @RequestMapping(value = "/getPersonInfo", method = RequestMethod.POST)
    @ResponseBody
    public String getPersonInfo(@RequestBody String request) throws Exception{

        String param = URLDecoder.decode(request, "utf-8");
        String param1 = param.substring(0,param.length()-1);

        JSONObject result = mainService.getPersonInfo(param1);
        if(result == null) {
            return null;
        }else{
            return result.toString();
        }
    }

    @RequestMapping(value = "/savePersonInfo", method = RequestMethod.POST)
    @ResponseBody
    public String savePersonInfo(@RequestBody String request) throws Exception{


       String param = URLDecoder.decode(request, "UTF-8");
            String  param1 = param.substring(0,param.length()-1);


        PersonInfoModel personInfoModel;
        personInfoModel = gson.fromJson(param1, PersonInfoModel.class);
        int result = mainService.savePersonInfo(personInfoModel);

        if(result == 1) {
            return "success";
        }else{
            return "failed";
        }
    }

}
