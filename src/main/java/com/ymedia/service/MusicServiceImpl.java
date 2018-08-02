package com.ymedia.service;

import com.ymedia.dao.MusicDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class MusicServiceImpl implements MusicService{

    @Autowired
    MusicDAO musicDAO;

    @Override
    public List<Map<String, Object>> getMusic() throws Exception{
        List<Map<String, Object>> list = musicDAO.getMusic();

        if(list.size() != 0 ){
            return list;
        }else{
            return null;
        }

    }

    @Override
    public List<Map<String, Object>> search(String input) throws Exception{
        List<Map<String, Object>> list = musicDAO.search(input);

        if(list.size() != 0 ){
            return list;
        }else{
            return null;
        }

    }
}
