package main.java.util;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import main.java.model.ResponseResult;
import main.java.model.User;
import main.java.service_center.ServiceCenterUserService;

import java.io.File;

/**
 * Created by Administrator on 2017/11/20.
 */
public class ImportUserInfo {
    private ServiceCenterUserService serviceCenterUserService = new main.java.service.UserService();

    public ResponseResult importUserInfo(File file) throws Exception {
        Workbook workbook = Workbook.getWorkbook(file);
        Sheet[] sheets = workbook.getSheets();
        StringBuffer message = new StringBuffer();
        message.append("一共有" + sheets.length + "个sheet页<br/>");
        boolean success = true;
        for (Sheet sheet : sheets) {
            message.append(sheet.getName() + "中共有" + sheet.getRows() + "行" + sheet.getColumns() + "列<br/>");
            for (int i = 2; i < sheet.getRows(); i++) {
                User user = new User();
                Cell cell = sheet.getCell(0, i);
                if (cell.getContents() == null || cell.getContents() == " ") {
                    message.append(sheet.getName() + "中第" + i + "行" + 0 + "列信息不能为空<br/>");
                    success = false;
                } else {
                    user.setId(cell.getContents());
                }
                Cell cell1 = sheet.getCell(1, i);
                if (cell1.getContents() == null || cell1.getContents() == " ") {
                    message.append(sheet.getName() + "中第" + i + "行" + 1 + "列信息不能为空<br/>");
                    success = false;
                } else {
                    user.setCompanyId(cell1.getContents());
                }
                Cell cell2 = sheet.getCell(2, i);
                if (cell2.getContents() == null || cell2.getContents() == " ") {
                    message.append(sheet.getName() + "中第" + i + "行" + 2 + "列信息不能为空<br/>");
                    success = false;
                } else {
                    user.setName(cell2.getContents());
                }
                Cell cell3 = sheet.getCell(3, i);
                if (cell3.getContents() == null || cell3.getContents() == " ") {
                    message.append(sheet.getName() + "中第" + i + "行" + 3 + "列信息不能为空<br/>");
                    success = false;
                } else {
                    user.setPass(cell3.getContents());
                }
                if(serviceCenterUserService.insertUser(user).isSuccess()){
                    message.append(sheet.getName() + "中第" + i + "行导入成功<br/>");
                } else {
                    message.append(sheet.getName() + "中第" + i + "行导入失败<br/>");
                }
            }
        }
        return ResponseResult.create(success, "", message.toString());
    }
}
