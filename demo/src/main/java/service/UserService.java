package main.java.service;

import main.java.dao.UserDao;
import main.java.model.ResponseResult;
import main.java.model.User;
import main.java.service_center.ServiceCenterUserService;

/**
 * Created by Administrator on 2017/11/17.
 */
public class UserService implements ServiceCenterUserService {

    private UserDao userDao = new UserDao();

    @Override
    public ResponseResult searchAllUser() {
        return userDao.searchAllUser();
    }

    @Override
    public ResponseResult insertUser(User user) {
        return userDao.insertUser(user);
    }

    @Override
    public ResponseResult updateUser(User user) {
        return userDao.updateUser(user);
    }

    @Override
    public ResponseResult deleteUser(User user) {
        return userDao.deleteUser(user);
    }
}
