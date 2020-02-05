package com.newedu.Reptile.Mysql;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.net.URL;
import java.sql.*;

public class JDPC {
    private static DataSource dataSource=null;

    static {
        // 左边是接口
        URL resource = JDPC.class.getResource("/hikari.properties");
        HikariConfig configuration = new HikariConfig(resource.getPath());
        dataSource = new HikariDataSource(configuration);
    }

    public static Connection getConnection() throws SQLException {
        Connection connection = null;
        // 第二步 创建连接对象
        connection = dataSource.getConnection();
        return connection;
    }

    public static DataSource getDataSource(){
        return dataSource;
    }

    public static void close(Connection zjl, PreparedStatement sql, ResultSet rs) throws Exception {
        if (rs != null) {
            rs.close();
        }
        if (sql != null) {
            sql.close();//关闭
        }
        if (zjl != null) {
            zjl.close();//关闭
        }
    }

}
