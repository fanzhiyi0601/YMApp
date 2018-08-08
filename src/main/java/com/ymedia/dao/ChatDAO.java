package com.ymedia.dao;

public interface ChatDAO {

    String getChatPeople(String username) throws Exception;
    String chat(String from, String to, Object msg, String time) throws Exception;
    String getChat(String username) throws Exception;
}
