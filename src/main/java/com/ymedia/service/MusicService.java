package com.ymedia.service;

import java.util.List;
import java.util.Map;

public interface MusicService {

    List<Map<String, Object>> getMusic() throws Exception;

    List<Map<String, Object>> getMusicByPage(String page) throws Exception;

    List<Map<String, Object>> search(String input) throws Exception;
}
