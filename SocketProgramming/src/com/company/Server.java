package com.company;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args){
        try (ServerSocket serverSocket = new ServerSocket(5000)){
            int count = 1;

            while(count < 4) {
                System.out.println("Waiting for a client" + count + "...");
                Socket socket = serverSocket.accept();
                System.out.println("Client " + count + " accepted.\n");
                ClientThread ct = new ClientThread(socket, count);
                ct.start();

                count++;
            }
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
