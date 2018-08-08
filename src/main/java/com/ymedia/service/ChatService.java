package com.ymedia.service;

import java.util.List;

public interface ChatService {

    String getChatPeople(String username) throws Exception;

    String transMsg(String from, String to, Object msg, String time) throws Exception;

    List<String> getOffline(String username) throws Exception;
}
