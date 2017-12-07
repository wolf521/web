package main.java.util;

import jxl.Workbook;
import jxl.format.*;
import jxl.format.Alignment;
import jxl.format.Colour;
import jxl.format.VerticalAlignment;
import jxl.write.*;
import main.java.model.User;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 * Created by Administrator on 2017/11/20.
 */
public class ExportExcel {
    public void exportExcel(OutputStream outputStream, List<User> users,String imgPath)throws IOException,WriteException{
        WritableWorkbook workbook = Workbook.createWorkbook(outputStream);
        WritableSheet sheet = workbook.createSheet("用户信息",0);
        // 添加一个合并单元格(1行3列)
        sheet.mergeCells(0,0,3,0);
        // 字体对象，样式，大小，加粗
        WritableFont font = new WritableFont(WritableFont.ARIAL,10,WritableFont.BOLD);
        // 单元格样式控制对象
        WritableCellFormat cellFormat = new WritableCellFormat(font);
        // 单元格水平居中
        cellFormat.setAlignment(Alignment.CENTRE);
        // 单元格垂直居中
        cellFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
        sheet.setRowView(0,600,false);
        sheet.addCell(new Label(0,0,"用户详细信息",cellFormat));

        WritableFont font1 = new WritableFont(WritableFont.ARIAL);
        // 字体颜色
        font1.setColour(Colour.RED);
        WritableCellFormat cellFormat1 = new WritableCellFormat(font1);

        sheet.addCell(new Label(0,1,"员工id",cellFormat1));
        sheet.addCell(new Label(1,1,"公司id",cellFormat1));
        sheet.addCell(new Label(2,1,"员工姓名",cellFormat1));
        sheet.addCell(new Label(3,1,"密码",cellFormat1));
        for(int i = 2;i < users.size();i++){
            if(i == 2){
                WritableFont font2 = new WritableFont(WritableFont.ARIAL,WritableFont.DEFAULT_POINT_SIZE,WritableFont.NO_BOLD,false,UnderlineStyle.SINGLE);
                WritableCellFormat cellFormatColor = new WritableCellFormat(font2);
                // 设置背景颜色
                cellFormatColor.setBackground(Colour.RED);
                sheet.addCell(new Label(0,i,users.get(i).getId(),cellFormatColor));
                sheet.addCell(new Label(1,i,users.get(i).getCompanyId(),cellFormatColor));
                sheet.addCell(new Label(2,i,users.get(i).getName(),cellFormatColor));
                sheet.addCell(new Label(3,i,users.get(i).getPass(),cellFormatColor));
                try{
                    // 添加图片
                    WritableImage writableImage = new WritableImage(4,i,2,8,new File(imgPath));
                    sheet.addImage(writableImage);
                } catch (Exception e){
                    e.printStackTrace();
                }
            } else if(i == 3){
                WritableFont font2 = new WritableFont(WritableFont.ARIAL,10,WritableFont.BOLD);
                WritableCellFormat cellFormatBold = new WritableCellFormat(font2);
                sheet.addCell(new Label(0,i,users.get(i).getId(),cellFormatBold));
                sheet.addCell(new Label(1,i,users.get(i).getCompanyId(),cellFormatBold));
                sheet.addCell(new Label(2,i,users.get(i).getName(),cellFormatBold));
                sheet.addCell(new Label(3,i,users.get(i).getPass(),cellFormatBold));
            } if(i == 4){
                WritableFont font2 = new WritableFont(WritableFont.ARIAL,WritableFont.DEFAULT_POINT_SIZE,WritableFont.BOLD,false,UnderlineStyle.SINGLE);
                WritableCellFormat cellFormat2 = new WritableCellFormat(font2);
                sheet.addCell(new Label(0,i,users.get(i).getId(),cellFormat2));
                sheet.addCell(new Label(1,i,users.get(i).getCompanyId(),cellFormat2));
                sheet.addCell(new Label(2,i,users.get(i).getName(),cellFormat2));
                sheet.addCell(new Label(3,i,users.get(i).getPass(),cellFormat2));
            } else {
                sheet.addCell(new Label(0,i,users.get(i).getId()));
                sheet.addCell(new Label(1,i,users.get(i).getCompanyId()));
                sheet.addCell(new Label(2,i,users.get(i).getName()));
                sheet.addCell(new Label(3,i,users.get(i).getPass()));
            }
        }
        workbook.write();
        workbook.close();
        outputStream.close();
    }
}
