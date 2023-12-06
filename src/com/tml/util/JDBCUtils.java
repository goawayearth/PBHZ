package com.tml.util;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBCUtils {

    public static DataSource ds;

    static {
        Properties properties = new Properties();
        /**
         *  利用反射获取类加载器,以此来得到加载项目中的其他文件
         */
        InputStream resourceAsStream = JDBCUtils.class.getClassLoader().getResourceAsStream("druid.properties");
        try {
            // 加载配置文件 druid.properties
            properties.load(resourceAsStream);
            // 获取连接池对象
            ds = DruidDataSourceFactory.createDataSource(properties);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(resourceAsStream != null) {
                try {
                    resourceAsStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static DataSource getDataSource(){
        return ds;
    }


    /**
     *  从连接池中获取一个数据库的连接
     * @return
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }


    /**
     *
     *  关闭数据库的连接
     * @param set
     * @param connection
     * @param statement
     * @throws SQLException
     */
    public static void close(ResultSet set, Connection connection, Statement statement) throws SQLException {
        if(set != null) {
            set.close();
        }

        if(connection != null){
            connection.close();
        }

        if(statement != null) {
            statement.close();
        }
    }
}

