package com.ymedia.dao;

import java.util.List;
import java.util.Map;

public interface MusicDAO {

    List<Map<String, Object>> getMusic() throws Exception;

    List<Map<String, Object>> getMusicByPage(String page) throws Exception;

    List<Map<String, Object>> search(String input) throws Exception;
}
