package com.ymedia.dao.Impl;

import com.DBConnection.DBConnection;
import com.google.gson.Gson;
import com.ymedia.dao.ChatDAO;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ChatDAOImpl implements ChatDAO{

    Gson gson = new Gson();
    @Override
    public String getChatPeople(String username) throws Exception{
        DBConnection dbConnection = new DBConnection();

        try {
            Connection conn = null;
            conn = dbConnection.getConnection(conn);
            String sql = "select * from client where instr(username, ?) > 0";

            PreparedStatement ps = null;
            ps = conn.prepareStatement(sql);

            ps.setString(1,username);

            ResultSet rs = ps.executeQuery();

            int i = 0;
            Map<Integer, String> map = new HashMap<>();

            while(rs.next()){
                username = rs.getString("username");
                map.put(i++,username);
            }

            conn.close();
            return gson.toJson(map);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String chat(String from, String to, Object msg, String time) throws Exception{
        DBConnection dbConnection = new DBConnection();

        try {
            Connection conn = null;
            conn = dbConnection.getConnection(conn);
            String sql = "insert into message (SENDER, RECEIVER, MSG, CHATTIME) values (?, ?, ?, ?)";

            PreparedStatement ps = null;
            ps = conn.prepareStatement(sql);

            ps.setString(1,from);
            ps.setString(2,to);
            ps.setString(3,String.valueOf(msg));
            ps.setString(4,time);

            ps.executeQuery();

            conn.close();

        }catch (Exception e){
            e.printStackTrace();
        }
        return "yes";
    }

    @Override
    public String insertOffline(String sender, String receiver, String msg, String time) throws Exception{
        DBConnection dbConnection = new DBConnection();

        try {
            Connection conn = null;
            conn = dbConnection.getConnection(conn);
            String sql = "insert into message (sender,receiver,msg, chatTime) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = null;
            ps = conn.prepareStatement(sql);

            ps.setString(1,sender);
            ps.setString(2,receiver);
            ps.setString(3,msg);
            ps.setString(4,time);

            ps.executeQuery();

            conn.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return "insert offline success";
    }

    @Override
    public List<String> getOffline(String username) throws Exception{
        DBConnection dbConnection = new DBConnection();

        try {
            Connection conn = null;
            conn = dbConnection.getConnection(conn);
            String sql = "select * from message where receiver = ? and flag = 0";
            String updateSql = "update message set flag = 1 where receiver = ?";
            PreparedStatement ps = null;
            PreparedStatement pst = null;
            ps = conn.prepareStatement(sql);
            pst = conn.prepareStatement(updateSql);
            ps.setString(1,username);
            pst.setString(1,username);
            ResultSet rs = ps.executeQuery();

            int i = 0;
            Map<Integer, String> map = new HashMap<>();
            String sender, msg, time;
            List<String> list = new ArrayList<>();

            while(rs.next()){
                sender = rs.getString("sender");
                msg = rs.getString("msg");
                time = rs.getString("chattime");
                list.add("来自"+sender+"的离线消息: ["+time+"] <br>"+msg);
            }
            pst.executeUpdate();
            conn.close();
            return list;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
