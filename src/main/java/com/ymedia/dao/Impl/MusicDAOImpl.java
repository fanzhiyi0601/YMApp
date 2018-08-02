package com.ymedia.dao.Impl;

import com.DBConnection.DBConnection;
import com.ymedia.dao.MusicDAO;
import com.ymedia.model.MusicModel;
import com.ymedia.model.PersonInfoModel;
import com.ymedia.utils.Bean2Map;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class MusicDAOImpl implements MusicDAO{

    @Override
    public List<Map<String, Object>> getMusic() throws Exception{

        DBConnection dbConnection = new DBConnection();
        MusicModel musicModel = new MusicModel();
        Map<String, Object> map = new HashMap<>();
        List<Map<String, Object>> list = new ArrayList<>();

        try {
            Connection conn = null;
            conn = dbConnection.getConnection(conn);
            String sql = "select * from music order by hotorder";
            PreparedStatement ps = null;
            ps = conn.prepareStatement(sql);

            ResultSet rs=ps.executeQuery();

            while(rs.next()){                                                      //rs.next()   表示如果结果集rs还有下一条记录，那么返回true；否则，返回false
                musicModel.setMusicName(rs.getString("musicName"));
                musicModel.setSinger(rs.getString("singer"));
                musicModel.setAlbum(rs.getString("album")==null?"":rs.getString("album"));
                musicModel.setRecommendation(rs.getString("Recommendation")==null?"":rs.getString("Recommendation"));
                musicModel.setAuthority(rs.getString("Authority")==null?"":rs.getString("Authority"));

                map = Bean2Map.toMap(musicModel);
                list.add(map);
//                String json = gson.toJson(personInfoModel);
//                JSONObject jsonObject = new JSONObject(json);
//                return jsonObject;
            }
            conn.close();

        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Map<String, Object>> search(String input) throws Exception{

        DBConnection dbConnection = new DBConnection();
        MusicModel musicModel = new MusicModel();
        Map<String, Object> map = new HashMap<>();
        List<Map<String, Object>> list = new ArrayList<>();

        try {
            Connection conn = null;
            conn = dbConnection.getConnection(conn);
            String sql = "select * from music where (instr(musicName, ?)>0 or instr(singer, ?)>0)";
            PreparedStatement ps = null;
            ps = conn.prepareStatement(sql);

            ps.setString(1, input);
            ps.setString(2, input);
            ResultSet rs=ps.executeQuery();

            while(rs.next()){
                musicModel.setMusicName(rs.getString("musicName"));
                musicModel.setSinger(rs.getString("singer"));
                musicModel.setAlbum(rs.getString("album")==null?"":rs.getString("album"));
                musicModel.setRecommendation(rs.getString("Recommendation")==null?"":rs.getString("Recommendation"));
                musicModel.setAuthority(rs.getString("Authority")==null?"":rs.getString("Authority"));

                map = Bean2Map.toMap(musicModel);
                list.add(map);
            }
            conn.close();

        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }
}
