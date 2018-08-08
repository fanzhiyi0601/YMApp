package com.ymedia.controller;

import com.google.gson.Gson;
import com.ymedia.service.ChatService;
import com.ymedia.service.MusicService;
import org.apache.tiles.autotag.core.runtime.annotation.Parameter;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/application")
public class ApplicationController {

    Gson gson = new Gson();

    @Autowired
    MusicService musicService;

    @Autowired
    ChatService chatService;

    @RequestMapping(value = "/getMusic", method = RequestMethod.GET)
    @ResponseBody
    public List<Map<String, Object>> getMusic(HttpServletRequest request) throws Exception {

        List<Map<String, Object>> result = musicService.getMusic();

        if (result == null) {
            return null;
        } else {
            return result;
        }
    }

    @RequestMapping(value = "/getMusicByPage", method = RequestMethod.POST)
    @ResponseBody
    public List<Map<String, Object>> getMusicByPage(@RequestBody String request) throws Exception {


        List<Map<String, Object>> result = musicService.getMusicByPage(request);

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

    @RequestMapping(value = "/getChatPeople", method = RequestMethod.POST)
    @ResponseBody
    public String getChatPeople(@RequestBody String request) throws Exception {

        String param = URLDecoder.decode(request, "utf-8");
        String result = chatService.getChatPeople(param);

        if (result == null) {
            return null;
        } else {
            return result;
        }
    }

    @RequestMapping(value = "/chat", method = RequestMethod.POST)
    @ResponseBody
    public String chat(@RequestBody String request) throws Exception {

        String param = URLDecoder.decode(request, "utf-8");
        Map<String, Object> map = new HashMap<>();
        map = gson.fromJson(param, Map.class);
        String sender = String.valueOf(map.get("from"));
        String receiver = String.valueOf(map.get("to"));
        Object message = map.get("msg");
        String time = String.valueOf(map.get("time"));
        String result = chatService.getChatPeople(param);

        if (result == null) {
            return null;
        } else {
            return result;
        }
    }

    @RequestMapping(value = "/getOffline", method = RequestMethod.POST)
    @ResponseBody
    public List<String> getOffline(@RequestBody String request) throws Exception {

        String param = URLDecoder.decode(request, "utf-8");

        List<String> result = chatService.getOffline(param);

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
