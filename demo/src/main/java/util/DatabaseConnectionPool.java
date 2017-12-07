package main.java.util;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by Administrator on 2017/11/20.
 */
public class DatabaseConnectionPool {

    public void getConnection() throws Exception {
        Context ctx = new InitialContext();

        DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/myfirstl");

        Connection conn = ds.getConnection();

        Statement stmt = conn.createStatement();

        ResultSet rs = stmt.executeQuery("select * from user");

        while (rs.next()) {

            System.out.println(rs.getString(1) + "," + rs.getString(2));
        }

        rs.close();

        stmt.close();

        conn.close();
    }

    /**
     * 测试链接是否可以正常释放
     */
    public void testRelease()throws Exception{
        Context ctx = new InitialContext();

        DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/myfirstl");

        for(int i = 0;i < 40;i++){
            Connection conn = ds.getConnection();
            conn.close();
        }
        System.out.println("释放成功");
    }
}
