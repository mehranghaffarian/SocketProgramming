package com.company;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/**
 * a class for the server
 * @author mehran ghaffarian
 * */
public class Server {

    public static void main(String[] args){
        //handling the probable exceptions
        try (ServerSocket serverSocket = new ServerSocket(5000)){
            ExecutorService es = Executors.newCachedThreadPool();
            int count = 0;

            //accepts 3 clients
            while(count++ <= 2) {
                System.out.println("Waiting for client" + count + "...");
                Socket socket = serverSocket.accept();
                System.out.println("Client " + count + " accepted.\n");
                ClientThread ct = new ClientThread(socket, count);
                es.execute(ct);
            }
            es.shutdown();
            serverSocket.close();//closes the socket
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
