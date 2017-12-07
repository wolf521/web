package main.java.service_center;

import main.java.model.ResponseResult;
import main.java.model.User;

import java.util.List;

/**
 * Created by Administrator on 2017/11/17.
 */
public interface ServiceCenterUserService {
    /**
     * 查询所有员工
     */
    ResponseResult searchAllUser();

    ResponseResult insertUser(User user);
    ResponseResult updateUser(User user);
    ResponseResult deleteUser(User user);
}
