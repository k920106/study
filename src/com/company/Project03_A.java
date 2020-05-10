package com.company;

import com.company.kr.inflearn.ExcelVO;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Project03_A {
  public static void main(String[] args) {
    String fileName = "test.xlsx";

    List<ExcelVO> data = new ArrayList<ExcelVO>();

    try(FileInputStream fis = new FileInputStream(fileName)) {
//      HSSFWorkbook workbook = new HSSFWorkbook(fis); // xls
//      HSSFSheet sheet = workbook.getSheetAt(0); // xls
      XSSFWorkbook workbook = new XSSFWorkbook(fis); // xlsx
      XSSFSheet sheet = workbook.getSheetAt(0); // xlsx
      Iterator<Row> rows = sheet.rowIterator();
      rows.next();

      String[] imsi = new String[5];
      while (rows.hasNext()) {
//        HSSFRow row = (HSSFRow) rows.next(); // xls
        XSSFRow row = (XSSFRow) rows.next(); // xlsx
        Iterator<Cell> cells = row.cellIterator();
        int i=0;
        while (cells.hasNext()) {
//          HSSFCell cell = (HSSFCell) cells.next(); // xls
          XSSFCell cell = (XSSFCell) cells.next(); // xlsx
          imsi[i] = cell.toString();
          i++;
        }
        ExcelVO vo = new ExcelVO(imsi[0], imsi[1], imsi[2], imsi[3], imsi[4]);
        data.add(vo);
      }

      showExcelData(data);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  public static void showExcelData(List<ExcelVO> data) {
    for(ExcelVO vo : data) {
      System.out.println(vo);
    }
  }
}
