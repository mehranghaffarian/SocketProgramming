package com.company;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    public static void main(String[] args){
        try (ServerSocket serverSocket = new ServerSocket(5000)){
            ExecutorService es = Executors.newCachedThreadPool();
            int count = 1;

            while(count < 4) {
                System.out.println("Waiting for client" + count + "...");
                Socket socket = serverSocket.accept();
                System.out.println("Client " + count + " accepted.\n");
                ClientThread ct = new ClientThread(socket, count);
                es.execute(ct);

                count++;
            }
            es.shutdown();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
