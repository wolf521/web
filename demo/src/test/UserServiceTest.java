package test;

import main.java.model.ResponseResult;
import main.java.model.User;
import main.java.service.UserService;
import org.junit.Test;

import java.util.List;

/**
 * Created by Administrator on 2017/11/17.
 */
public class UserServiceTest {

    private UserService userService = new UserService();

    @Test
    public void searchAllUser() {
        ResponseResult responseResult = userService.searchAllUser();
        assert responseResult != null;
    }

    @Test
    public void insertUser() {
        User user = new User();
        user.setName("小黑");
        ResponseResult responseResult = userService.insertUser(user);
        assert responseResult != null;
    }

    @Test
    public void updateUser() {
        insertUser();
        List<User> users = (List<User>)userService.searchAllUser().getData();
        User user = new User();
        user.setName("小黑");
        user.setPass("admin");
        user.setId((users.get(0).getId()));
        ResponseResult responseResult = userService.updateUser(user);
        assert responseResult != null;
    }

    @Test
    public void deleteUser() {
        insertUser();
        List<User> users = (List<User>)userService.searchAllUser().getData();
        User user = new User();
        user.setId((users.get(0).getId()));
        ResponseResult responseResult = userService.deleteUser(user);
        assert responseResult != null;
    }
}
