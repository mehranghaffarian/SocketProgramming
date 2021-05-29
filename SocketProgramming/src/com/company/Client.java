package com.company;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {
        try (Socket socket = new Socket("192.168.0.111", 5000);
             DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
             DataInputStream dis = new DataInputStream(socket.getInputStream())){
            System.out.println("connected");
            Scanner scan = new Scanner(System.in);
            String str = "", finalInput = "";

            while(!str.equalsIgnoreCase("over")){
                dos.writeUTF(str);
                str = scan.next();
            }
            if(str.equalsIgnoreCase("over"))
                dos.writeUTF(str);

            finalInput = dis.readUTF();
            socket.close();
            System.out.println("Whole messages are: " + finalInput);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
