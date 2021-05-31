package com.company;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
/**
 * a class for clients
 * @author mehra ghaffarian
 * */
public class Client {

    public static void main(String[] args) {
        //handling the probable exceptions
        try (Socket socket = new Socket("127.0.0.1", 5000);
             DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
             DataInputStream dis = new DataInputStream(socket.getInputStream())){
            System.out.println("connected");
            Scanner scan = new Scanner(System.in);
            String str = "", finalInput = "";

            while(!str.equalsIgnoreCase("over")){
                str = scan.next();
                dos.writeUTF(str);
                System.out.println("Server: " + dis.readUTF());
            }
            //avoiding writing the <over> word
            if(str.equalsIgnoreCase("over"))
                dos.writeUTF(str);

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
