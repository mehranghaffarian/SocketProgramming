package com.company;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * a class for the server
 *
 * @author mehran ghaffarian
 */
public class Server {

    public static void main(String[] args) {
        //handling the probable exceptions
        try (ServerSocket serverSocket = new ServerSocket(5000)) {
            ExecutorService es = Executors.newCachedThreadPool();

            System.out.println("Waiting for client...");
            Socket socket = serverSocket.accept();
            System.out.println("Client accepted.\n");

            //handling the probable exceptions
            try (DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
                 DataInputStream dis = new DataInputStream(socket.getInputStream());) {
                String str = "";
                boolean continueLoop = true;

                while (continueLoop) {
                    String check = dis.readUTF();

                    //checking the ending condition
                    if (!check.equalsIgnoreCase("over")) {
                        str = str.concat(check + " ");
                    }
                    else {
                        continueLoop = false;
                    }
                    dos.writeUTF(str);
                }
                //printing the whole messages
                System.out.println("The whole messages for client is: " + str);

                dos.close();
                dis.close();
                socket.close();//closes the socket
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
