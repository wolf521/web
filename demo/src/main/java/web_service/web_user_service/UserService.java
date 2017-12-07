package main.java.web_service.web_user_service;

import main.java.model.ResponseResult;
import main.java.model.User;
import main.java.service_center.ServiceCenterUserService;
import main.java.util.ErrorException;
import main.java.util.ExportExcel;
import main.java.util.ImportUserInfo;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.OutputStream;
import java.util.List;

/**
 * Created by Administrator on 2017/11/17.
 */
public class UserService {
    private ServiceCenterUserService serviceCenterUserService = new main.java.service.UserService();

    /**
     * 查询所有用户
     */
    public ResponseResult searchAllUser() {
        return serviceCenterUserService.searchAllUser();
    }
    public ResponseResult insertUser(User user) {
        return serviceCenterUserService.insertUser(user);
    }
    public ResponseResult updateUser(User user) {
        return serviceCenterUserService.updateUser(user);
    }
    public ResponseResult deleteUser(User user) {
        return serviceCenterUserService.deleteUser(user);
    }
    public void exportExcelUser(OutputStream outputStream,String imgPath)throws Exception{
        ExportExcel exportExcel = new ExportExcel();
        List<User> users = (List<User>)searchAllUser().getData();
        exportExcel.exportExcel(outputStream,users,imgPath);
    }
    public ResponseResult importUserInfo(File file) {
        ImportUserInfo importUserInfo = new ImportUserInfo();
        ResponseResult responseResult = null;
        try{
            responseResult = importUserInfo.importUserInfo(file);
        } catch (Exception e){
            e.printStackTrace();
        }
        return responseResult;
    }
}
