package com.ymedia.dao;

import java.util.List;

public interface ChatDAO {

    String getChatPeople(String username) throws Exception;
    String chat(String from, String to, Object msg, String time) throws Exception;
    List<String> getOffline(String username) throws Exception;
    String insertOffline(String sender, String receiver, String msg, String time) throws Exception;

}
