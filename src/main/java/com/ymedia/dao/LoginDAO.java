package com.ymedia.dao;

import com.DBConnection.DBConnection;
import com.ymedia.model.LoginModel;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public interface LoginDAO {
    long login(LoginModel loginModel) throws Exception;
}
