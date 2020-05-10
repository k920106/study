package com.company;

import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;

public class Project04_C {
  public static void main(String[] args) {
    Document doc = new Document();

    try {
      PdfWriter.getInstance(doc, new FileOutputStream("ImageDemo.pdf"));
      doc.open();

      String fileName = "inflearn.png";
      Image image = Image.getInstance(fileName);
      doc.add(image);

      String url = "https://cdn.inflearn.com/public/main/profile/default_profile.png";
      image = Image.getInstance(url);
      doc.add(image);

      System.out.println("생성완료");
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      doc.close();
    }
  }
}
