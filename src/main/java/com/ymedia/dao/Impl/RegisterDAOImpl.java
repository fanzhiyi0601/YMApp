package com.ymedia.dao.Impl;

import com.DBConnection.DBConnection;
import com.ymedia.dao.RegisterDAO;
import com.ymedia.model.LoginModel;
import com.ymedia.model.RegisterModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RegisterDAOImpl implements RegisterDAO{

    @Override
    public int register(RegisterModel registerModel) throws Exception{

        DBConnection dbConnection = new DBConnection();

        try {
            Connection conn = null;
            conn = dbConnection.getConnection(conn);
            String sql = "insert into client (usernmae, password, telephone, email) values (?, ?, ?, ?)";
            PreparedStatement ps = null;
            ps = conn.prepareStatement(sql);

            ps.setString(1,registerModel.getUsername());
            ps.setString(2,registerModel.getPassword());
            ps.setString(3,registerModel.getTelephone());
            ps.setString(4,registerModel.getEmail());

            ps.executeQuery();

            conn.commit();
            conn.close();


        }catch (Exception e){
            e.printStackTrace();
        }
        return 1;
    }

    @Override
    public List<Map<String, Object>> queryUser(String username) throws Exception{
        DBConnection dbConnection = new DBConnection();
        Connection conn = null;
        conn = dbConnection.getConnection(conn);
        String sql = "select * from client where username = '"+username+"'";
        PreparedStatement ps = null;
        ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        List<Map<String, Object>> list = null;

        while(rs.next()){
            Map<String,Object> map = new HashMap<>();
            map.put("username",rs.getString("username"));
            map.put("password",rs.getString("password"));
            map.put("telephone",rs.getString("telephone"));
            map.put("email",rs.getString("email"));
            list.add(map);
        }
        return list;
    }
}
