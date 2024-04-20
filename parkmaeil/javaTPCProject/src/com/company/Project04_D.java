package com.company;

import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;

public class Project04_D {
  public static void main(String[] args) {
    Document doc = new Document();

    try {
      PdfWriter.getInstance(doc, new FileOutputStream("imageScaling.pdf"));
      doc.open();

      String fileName = "google.png";
      Image image = Image.getInstance(fileName);
      doc.add(image);

      fileName = "google.png";
      image = Image.getInstance(fileName);
      image.scaleAbsolute(200f, 200f);
      doc.add(image);

      String url = "https://www.google.com/images/branding/googlelogo/2x/googlelogo_color_272x92dp.png";
      image = Image.getInstance(url);
      image.scalePercent(200f);
      doc.add(image);

      image = Image.getInstance(url);
      image.scaleToFit(100f, 200f);
      doc.add(image);

      System.out.println("크기조절 성공");
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      doc.close();
    }
  }
}