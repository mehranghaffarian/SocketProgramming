package com.company;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class ClientThread extends Thread{
    private Socket socket;
    private int number;

    public ClientThread(Socket socket, int number) {
        this.socket = socket;
        this.number = number;
    }

    @Override
    public void run() {
        try(DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
        DataInputStream dis = new DataInputStream(socket.getInputStream());){
        String str = "";
        boolean continueLoop = true;

        while(continueLoop) {
            String check =  dis.readUTF();

            if(!check.equalsIgnoreCase("over"))
                str = str.concat(check + " ");
            else {
                continueLoop = false;
                dos.writeUTF(str);
            }
        }
        System.out.println("The whole messages for client " + number + " is: " + str);

        dos.close();
        dis.close();
        socket.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
