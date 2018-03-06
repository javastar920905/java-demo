package com.javastar920905;

import com.javastar920905.outer.ExcelUtil;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ouzhx on 2017/4/7.
 */
public class ExcelUtilTest {
  public static void main(String[] args) {
    List<Map<String, String>> domeList = new ArrayList<>();
    Map<String, String> item = new HashMap<>();
    item.put("date", "2017-01-02");
    item.put("resumeNo", "001");
    item.put("price", "11");
    item.put("hr", "ouzhx");
    item.put("resumeDeliverUserName", "13534138318");
    domeList.add(item);
    domeList.add(item);
    domeList.add(item);
    domeList.add(item);
    domeList.add(item);
    domeList.add(item);

    String[] headers = new String[] {"日期", "简历编号", "简历价格", "hr名称", "投递人"};
    String[] fields = new String[] {"date", "resumeNo", "price", "hr", "resumeDeliverUserName"};

    XSSFWorkbook excel = ExcelUtil.createXSSFWorkbookToExport("sheetName人才加 业绩统计表", "title人才加 业绩统计",
        headers, fields, domeList);

    try {
      // 输出Excel文件
      FileOutputStream fileOutputStream = new FileOutputStream("d://test.xls");
      excel.write(fileOutputStream);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    }


  }
}
