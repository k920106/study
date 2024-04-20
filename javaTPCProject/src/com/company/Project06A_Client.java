package com.company;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Project06A_Client {
  public static void main(String[] args) {
    try {
      Socket socket = new Socket("172.30.1.29", 9999);
      System.out.println("Connection Success!");

      Scanner scanner = new Scanner(System.in);
      String message = scanner.nextLine();

      OutputStream out = socket.getOutputStream();
      DataOutputStream dos = new DataOutputStream(out);
      dos.writeUTF(message);

      InputStream in = socket.getInputStream();
      DataInputStream dis = new DataInputStream(in);
      System.out.println("Resceive : " + dis.readUTF());

      dis.close();
      dos.close();
      socket.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
