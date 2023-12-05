package com.tml.util;

import java.sql.*;

public class JdbcUtil {
    //获取用户数据库链接
    public static Connection getUserConnection(){
        Connection connection = null;
        try {
            //注册驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            // 用户名和密码都是root
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/pbhz?" +
                    "characterEncoding=UTF-8" +
                    "&serverTimezone=Asia/Shanghai&useSSL=false" +
                    "&allowPublicKeyRetrieval=true","root", "910513qlovet");
            System.out.println("database access success");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("fail to access database");
        }catch (SQLException e) {
            e.printStackTrace();
            System.out.println("fail to access database");
        }
        return connection;
    }

    //关闭的方法
    public static void close(PreparedStatement pstmt){
        if(pstmt != null){						//避免出现空指针异常
            try{
                pstmt.close();
            }catch(SQLException e){
                e.printStackTrace();
            }

        }
    }

    public static void close(Connection conn){
        if(conn != null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void close(ResultSet rs){
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


}
