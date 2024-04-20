package com.company;

import com.company.kr.inflearn.ExcelVO;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Project04_F {
  public static void main(String[] args) {
    String fileName = "isbn.xls";
    List<ExcelVO> data = new ArrayList<ExcelVO>();

    try(FileInputStream fis = new FileInputStream(fileName)) {
      HSSFWorkbook workbook = new HSSFWorkbook(fis); // xls
      HSSFSheet sheet = workbook.getSheetAt(0); // xls
//      XSSFWorkbook workbook = new XSSFWorkbook(fis); // xlsx
//      XSSFSheet sheet = workbook.getSheetAt(0); // xlsx
      Iterator<Row> rows = sheet.rowIterator();
      rows.next();

      String[] imsi = new String[5];
      while (rows.hasNext()) {
        HSSFRow row = (HSSFRow) rows.next(); // xls
//        XSSFRow row = (XSSFRow) rows.next(); // xlsx
        Iterator<Cell> cells = row.cellIterator();
        int i=0;
        while (cells.hasNext()) {
          HSSFCell cell = (HSSFCell) cells.next(); // xls
//          XSSFCell cell = (XSSFCell) cells.next(); // xlsx
          imsi[i] = cell.toString();
          i++;
          if(i==5) break;
        }
        ExcelVO vo = new ExcelVO(imsi[0], imsi[1], imsi[2], imsi[3], imsi[4]);
        data.add(vo);
      }

      pdf_maker(data);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private static void pdf_maker(List<ExcelVO> data) {
    String[] headers = new String[] { "제목", "저자", "출판사", "이미지"};
    Document doc = new Document();

    try {
      PdfWriter.getInstance(doc, new FileOutputStream(new File("booklist.pdf")));
      doc.open();

      BaseFont bFont = BaseFont.createFont("ArialUnicode.ttf", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
      Font fontHearder = new Font(bFont, 12);
      Font fontRow = new Font(bFont, 10);

      PdfPTable table = new PdfPTable(headers.length);

      for(String hearder : headers) {
        PdfPCell cell = new PdfPCell();
        cell.setGrayFill(0.9f);
        cell.setPhrase(new Phrase(hearder.toUpperCase(), fontHearder));
        table.addCell(cell);
      }

      table.completeRow();

      for(ExcelVO vo : data) {
        Phrase phrase = new Phrase(vo.getTitle(), fontRow);
        table.addCell(new PdfPCell(phrase));

        phrase = new Phrase(vo.getAuthor(), fontRow);
        table.addCell(new PdfPCell(phrase));

        phrase = new Phrase(vo.getCompany(), fontRow);
        table.addCell(new PdfPCell(phrase));

        Image image = Image.getInstance(vo.getImgurl());
        table.addCell(image);

        table.completeRow();
      }
      doc.addTitle("PDF Table Demo");
      doc.add(table);
      System.out.println("bookList 생성완료");
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      doc.close();
    }
  }
}
