package com.DBConnection;

import java.sql.*;
import java.util.Properties;

public class DBConnection {



    private static Properties prop;

    static {



        prop = new Properties();

        try {

            prop.load(DBConnection.class.getClassLoader().getResourceAsStream(

            "db.properties"));

            Class.forName(prop.getProperty("driver"));



        } catch (Exception e) {

            throw new RuntimeException(e);

        }



    }



    public Connection getConnection(Connection conn) {

        if (conn==null) {

            try {

                conn= DriverManager.getConnection(prop.getProperty("url"),

                prop.getProperty("username"),prop.getProperty("password"));

            } catch (SQLException e) {

                throw new RuntimeException(e);

            }



        }

        return conn;

    }

    public static void close(Connection conn, PreparedStatement prep, ResultSet rs){

        if(rs != null){

            try {

                rs.close();

            } catch (SQLException e) {

                e.printStackTrace();

            } finally{

                rs = null;

            }

        }

        if(prep != null){

            try {

                prep.close();

            } catch (SQLException e) {

                e.printStackTrace();

            } finally{

                prep = null;

            }

        }

        if(conn != null){

            try {

                conn.close();

            } catch (SQLException e) {

                e.printStackTrace();

            } finally{

                conn = null;

            }

        }



    }


}