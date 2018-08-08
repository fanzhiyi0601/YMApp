package com.ymedia.service;

public interface ChatService {

    String getChatPeople(String username) throws Exception;

    String transMsg(String from, String to, Object msg, String time) throws Exception;

    String getChat(String username) throws Exception;
}
