package lesson6.maintask;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    public static void main(String[] args) {
        ServerSocket server = null;
        Socket socket = null;

        try {
            server = new ServerSocket(8189);
            System.out.println("Server started");

            socket = server.accept();
            System.out.println("Client connected");

            final Scanner in = new Scanner(socket.getInputStream());
            final PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            final Scanner consol = new Scanner(System.in);

            Thread t1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        String str = in.nextLine();
                        if (str.equals("/end")) {
                            out.println("/end");
                            break;
                        }
                        System.out.println("Client: " + str);
                    }
                }
            });
            t1.start();

            Thread t2 = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        System.out.println("Enter your message");
                        String str = consol.nextLine();
                        out.println(str);
                    }
                }
            });
            t2.setDaemon(true);
            t2.start();

            try{
                t1.join();
            }
            catch (InterruptedException e){
                e.printStackTrace();
            }

        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try{
                socket.close();
            }catch (IOException e){
                e.printStackTrace();
            }
            try {
                server.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}
