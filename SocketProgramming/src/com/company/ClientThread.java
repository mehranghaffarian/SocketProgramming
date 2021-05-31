package com.company;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
/**
 *a class for handling the clients orders
 * @author mehrabhgaffarian
 * */
public class ClientThread extends Thread{
    private Socket socket;
    private int number;

    public ClientThread(Socket socket, int number) {
        this.socket = socket;
        this.number = number;
    }

    /**
     * handles the clients orders
     * */
    @Override
    public void run() {
        //handling the probable exceptions
        try(DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
        DataInputStream dis = new DataInputStream(socket.getInputStream());){
        String str = "";
        boolean continueLoop = true;

        while(continueLoop) {
            String check =  dis.readUTF();

            //checking the ending condition
            if(!check.equalsIgnoreCase("over"))
                str = str.concat(check + " ");
            else
                continueLoop = false;

            dos.writeUTF(str);
        }
        //printing the whole messages
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
