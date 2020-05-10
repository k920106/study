package com.company;

import com.company.kr.inflearn.ExcelVO;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class Project03_D {
  public static void main(String[] args) {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    try {
      System.out.print("책제목 : ");
      String title = br.readLine();
      System.out.print("책저자 : ");
      String author = br.readLine();
      System.out.print("출판사 : ");
      String company = br.readLine();

      ExcelVO vo = new ExcelVO(title, author, company);
      getIsbnImage(vo);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private static void getIsbnImage(ExcelVO vo) {
    try {
      String openApi = "https://openapi.naver.com/v1/search/book_adv.xml?" +
              "d_titl=" + URLEncoder.encode(vo.getTitle(), "UTF-8") +
              "&d_auth=" + URLEncoder.encode(vo.getAuthor(), "UTF-8") +
              "&d_publ=" + URLEncoder.encode(vo.getCompany(), "UTF-8");

      URL url = new URL(openApi);
      HttpURLConnection con = (HttpURLConnection) url.openConnection();
      con.setRequestMethod("GET");
      con.setRequestProperty("X-Naver-Client-Id", "UO16RC6RLOS_MjcEGjn8");
      con.setRequestProperty("X-Naver-Client-Secret", "rhppS5swhs");

      int responseCode = con.getResponseCode();
      BufferedReader br1 = null;
      if(responseCode == 200) {
        br1 = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
      } else {
        br1 = new BufferedReader(new InputStreamReader(con.getErrorStream()));
      }

      String inputLine;
      StringBuffer response = new StringBuffer();

      while((inputLine = br1.readLine()) != null) {
        response.append(inputLine);
      }

      br1.close();

      Document doc = Jsoup.parse(response.toString());
      Element total = doc.select("total").first();

      if(!(total.text().equals("0"))) {
        Element isbn = doc.select("isbn").first();
        String isbnStr = isbn.text();
        String isbn_find = isbnStr.split(" ")[1];
        vo.setIsbn(isbn_find);
        String imgDoc = doc.toString();
        String imgTag = imgDoc.substring(imgDoc.indexOf("<img>") + 5);
        String imgURL = imgTag.substring(0, imgTag.indexOf("?"));
        System.out.println(imgURL);
        String fileName = imgURL.substring(imgURL.lastIndexOf("/") + 1);
        System.out.println(fileName);
        vo.setImgurl(fileName);

        System.out.println(vo);
      } else {
        System.out.println("검색데이터가 없습니다.");
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
