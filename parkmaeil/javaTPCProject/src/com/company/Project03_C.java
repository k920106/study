package com.company;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.util.Iterator;

public class Project03_C {
  public static void main(String[] args) {
    String fileName = "cellDataType.xlsx";
    try(FileInputStream fis = new FileInputStream(fileName)) {
//      HSSFWorkbook workbook = new HSSFWorkbook(fis); // xls
//      HSSFSheet sheet = workbook.getSheetAt(0); // xls
      XSSFWorkbook workbook = new XSSFWorkbook(fis); // xlsx
      XSSFSheet sheet = workbook.getSheetAt(0); // xlsx
      Iterator<Row> rows = sheet.rowIterator();
      while (rows.hasNext()) {
//        HSSFRow row = (HSSFRow) rows.next(); // xls
        XSSFRow row = (XSSFRow) rows.next();
        Iterator<Cell> cells = row.cellIterator();
        while (cells.hasNext()) {
//          HSSFCell cell = (HSSFCell) cells.next(); // xls
          XSSFCell cell = (XSSFCell) cells.next(); // xlsx
          CellType type = cell.getCellType();
          if(type == CellType.STRING) {
            System.out.println("[" +
                               cell.getRowIndex() +
                               "," +
                               cell.getColumnIndex() +
                               "] = STRING; Value=" +
                               cell.getRichStringCellValue().toString());
          } else if(type == CellType.NUMERIC) {
            System.out.println("[" +
                               cell.getRowIndex() +
                               "," +
                               cell.getColumnIndex() +
                               "] = NUMERIC; Value=" +
                               cell.getNumericCellValue());
          } else if(type == CellType.BOOLEAN) {
            System.out.println("[" +
                               cell.getRowIndex() +
                               "," +
                               cell.getColumnIndex() +
                               "] = BOOLEAN; Value=" +
                               cell.getBooleanCellValue());
          } else if(type == CellType.BLANK) {
            System.out.println("[" +
                               cell.getRowIndex() +
                               "," +
                               cell.getColumnIndex() + "] = BLANK CELL");
          }
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
