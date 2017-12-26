package main.java.dao;

import main.java.model.ResponseResult;
import main.java.model.User;
import main.java.util.ConnectMySql;
import main.java.util.IDGenerator;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/11/17.
 * 用户dao
 */
public class UserDao {

    private ConnectMySql connectMySql = new ConnectMySql();

    public ResponseResult searchAllUser(){
        String sql = "select * from user";
        Connection connection = connectMySql.connect();
        List<User> users = new ArrayList<User>();
        try{
            if(connection != null && !connection.isClosed()){
                // 执行sql的对象
                Statement statement = connection.createStatement();
                // 操作结果
                ResultSet resultSet = statement.executeQuery(sql);
                while(resultSet.next()){
                    User user = new User(resultSet.getString("id"),resultSet.getString("company_id"),
                            resultSet.getString("name"),resultSet.getString("pass"));
                    users.add(user);
                }
                resultSet.close();
                statement.close();
                connection.close();
            }
        } catch (SQLException e){
            e.printStackTrace();
            return ResponseResult.create(false,"","系统异常，请稍候重试");
        }
        return ResponseResult.create(true,users,"");
    }

    public ResponseResult insertUser(User user) {
        String sql = "insert into user VALUES (?,?,?,?)";
        Connection connection = connectMySql.connect();
        boolean success = true;
        try{
            if(connection != null && !connection.isClosed()){
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                //先对应SQL语句，给SQL语句传递参数
                preparedStatement.setLong(1, Long.parseLong(String.valueOf(IDGenerator.getId())));
                preparedStatement.setLong(2, Long.parseLong(String.valueOf(IDGenerator.getId())));
                preparedStatement.setString(3, user.getName());
                preparedStatement.setString(4, "admin");
                //执行SQL语句
                if(preparedStatement.executeUpdate() != 1){
                    success = false;
                }
                preparedStatement.close();
                connection.close();
            }
        } catch (SQLException e){
            e.printStackTrace();
            return ResponseResult.create(false,"","系统异常，请稍候重试");
        }
        return ResponseResult.create(success,"","新建成功");
    }

    public ResponseResult updateUser(User user) {
        String sql = "update user set name = ?,pass = ? where id = ?";
        Connection connection = connectMySql.connect();
        boolean success = false;
        try{
            if(connection != null && !connection.isClosed()){
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                //先对应SQL语句，给SQL语句传递参数
                preparedStatement.setString(1, user.getName());
                preparedStatement.setString(2, user.getPass());
                preparedStatement.setLong(3, Long.parseLong(user.getId()));
                //执行SQL语句
                success = preparedStatement.execute();
                preparedStatement.close();
                connection.close();
            }
        } catch (SQLException e){
            e.printStackTrace();
            return ResponseResult.create(success,"","系统异常，请稍候重试");
        }
        return ResponseResult.create(success,"","修改成功");
    }

    public ResponseResult deleteUser(User user) {
        String sql = "delete from user where id = ?";
        Connection connection = connectMySql.connect();
        boolean success = false;
        try{
            if(connection != null && !connection.isClosed()){
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                //先对应SQL语句，给SQL语句传递参数
                preparedStatement.setLong(1, Long.parseLong(user.getId()));
                //执行SQL语句
                success = preparedStatement.execute();
                preparedStatement.close();
                connection.close();
            }
        } catch (SQLException e){
            e.printStackTrace();
            return ResponseResult.create(success,"","系统异常，请稍候重试");
        }
        return ResponseResult.create(success,"","删除成功");
    }
}
