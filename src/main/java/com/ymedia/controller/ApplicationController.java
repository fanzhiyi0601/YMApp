package com.ymedia.controller;

import com.ymedia.service.MusicService;
import org.apache.tiles.autotag.core.runtime.annotation.Parameter;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.URLDecoder;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/application")
public class ApplicationController {


    @Autowired
    MusicService musicService;

    @RequestMapping(value = "/getMusic", method = RequestMethod.GET)
    @ResponseBody
    public List<Map<String, Object>> getPersonInfo(HttpServletRequest request) throws Exception {

        List<Map<String, Object>> result = musicService.getMusic();

        if (result == null) {
            return null;
        } else {
            return result;
        }
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    @ResponseBody
    public List<Map<String, Object>> search(@RequestBody String request) throws Exception {

        byte[] b=request.getBytes("ISO-8859-1");//用tomcat的格式（iso-8859-1）方式去读。
        String str=new String(b,"utf-8");//采用utf-8去接string

        String param = URLDecoder.decode(request, "utf-8");
        List<Map<String, Object>> result = musicService.search(param);

        if (result == null) {
            return null;
        } else {
            return result;
        }
    }

    @RequestMapping(value = "/ajax")
    @ResponseBody
    public String ajax(@RequestBody String requestInfoBean,HttpServletRequest request) {
        System.out.println(requestInfoBean);
        System.out.println(request.getHeader("Content-Type"));
        return null;
    }
}
