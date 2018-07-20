package com.ymedia.dao.Impl;

import com.DBConnection.DBConnection;
import com.ymedia.dao.LoginDAO;
import com.ymedia.model.LoginModel;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

@Service
public class LoginDAOImpl implements LoginDAO{

    @Override
    public int login(LoginModel loginModel) throws Exception{

        DBConnection dbConnection = new DBConnection();

        try {
            Connection conn = null;
            conn = dbConnection.getConnection(conn);
            String password = loginModel.getPassword();
            String passwordDB = null;
            String sql = "select * from client where username = ?";
            PreparedStatement ps = null;
            ps = conn.prepareStatement(sql);

            ps.setString(1,loginModel.getUsername());
            ResultSet rs=ps.executeQuery();

            while(rs.next()){                                                      //rs.next()   表示如果结果集rs还有下一条记录，那么返回true；否则，返回false
                 passwordDB = rs.getString("password");
            }

            conn.close();

            if(!password.equals(passwordDB)){
                return 0;
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return 1;
    }
}
