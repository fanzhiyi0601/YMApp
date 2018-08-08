package com.ymedia.service;

import com.ymedia.dao.ChatDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatServiceImpl implements ChatService{

    @Autowired
    ChatDAO chatDAO;

    @Override
    public String getChatPeople(String username) throws Exception{

        // 查数据库
        String result = chatDAO.getChatPeople(username);

        return result;
    }

    @Override
    public List<String> getOffline(String username) throws Exception{

        // 查数据库
        List<String> result = chatDAO.getOffline(username);

        return result;
    }

    @Override
    public String transMsg(String from, String to, Object msg, String time) throws Exception{

        // 查数据库
        String result = chatDAO.chat(from, to, msg, time);

        return result;
    }
}
