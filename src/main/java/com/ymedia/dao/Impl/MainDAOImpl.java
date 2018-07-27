package com.ymedia.dao.Impl;

import com.DBConnection.DBConnection;
import com.google.gson.Gson;
import com.ymedia.dao.MainDAO;
import com.ymedia.model.PersonInfoModel;

import org.json.*;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
@Service
public class MainDAOImpl implements MainDAO {

    Gson gson = new Gson();

    @Override
    public JSONObject getPersonInfo(String token) throws Exception{

        DBConnection dbConnection = new DBConnection();
        PersonInfoModel personInfoModel = new PersonInfoModel();

        try {
            Connection conn = null;
            conn = dbConnection.getConnection(conn);
            String sql = "select * from client where token = ?";
            PreparedStatement ps = null;
            ps = conn.prepareStatement(sql);

            ps.setString(1,token);
            ResultSet rs=ps.executeQuery();

            while(rs.next()){                                                      //rs.next()   表示如果结果集rs还有下一条记录，那么返回true；否则，返回false
                personInfoModel.setUsername(rs.getString("username"));
                personInfoModel.setPassword(rs.getString("password"));
                personInfoModel.setTelephone(rs.getString("telephone")==null?"":rs.getString("telephone"));
                personInfoModel.setEmail(rs.getString("email")==null?"":rs.getString("email"));
                personInfoModel.setName(rs.getString("name")==null?"":rs.getString("name"));
                personInfoModel.setBirthday(rs.getString("birthday")==null?"":rs.getString("birthday"));
                personInfoModel.setSex(rs.getString("sex")==null?"":rs.getString("sex"));
                personInfoModel.setStatus(rs.getString("status")==null?"":rs.getString("status"));
                personInfoModel.setPersonalInfo(rs.getString("persinal_info")==null?"":rs.getString("persinal_info"));

                String json = gson.toJson(personInfoModel);
                JSONObject jsonObject = new JSONObject(json);
                return jsonObject;
            }
            conn.close();

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int savePersonInfo(PersonInfoModel personInfoModel) throws Exception{
        DBConnection dbConnection = new DBConnection();

        try {
            Connection conn = null;
            conn = dbConnection.getConnection(conn);
            String sql = "insert into client (username, password, telephone, email, name, birthday, status, \n" +
            "sex, persinal_info) values (?, ?,?,?,?,?,?,?,?)";

            PreparedStatement ps = null;
            ps = conn.prepareStatement(sql);

            ps.setString(1,personInfoModel.getUsername());
            ps.setString(2,personInfoModel.getPassword());
            ps.setString(3,personInfoModel.getTelephone());
            ps.setString(4,personInfoModel.getEmail());
            ps.setString(5,personInfoModel.getName());
            ps.setString(6,personInfoModel.getBirthday());
            ps.setString(7,personInfoModel.getStatus());
            ps.setString(8,personInfoModel.getSex());
            ps.setString(9,personInfoModel.getPersonalInfo());
            ps.executeQuery();

            conn.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return 1;
    }

}
