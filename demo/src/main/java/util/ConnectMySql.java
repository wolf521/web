package main.java.util;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Created by Administrator on 2017/11/16.
 */
public class ConnectMySql {
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String url = "jdbc:mysql://localhost:3306/myfirstl?serverTimezone=GMT&useUnicode=true&characterEncoding=utf-8&useSSL=false";
    private static final String user = "root";
    private static final String password = "czxmysql";

    public Connection connect() {
        try {
            // 加载驱动
            Class.forName(DRIVER);
            // 链接数据库
            Connection connection = DriverManager.getConnection(url, user, password);
            return connection;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
