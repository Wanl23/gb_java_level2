package lesson6.maintask;

import java.io.*;
import java.net.Socket;

public class Receiver extends Thread{
    Socket socket;
    DataInputStream in;

    Receiver(Socket socket){
        this.socket = socket;
    }

    public void run(){
        try {
            while (true) {
                in = new DataInputStream(socket.getInputStream());
                String str = in.readUTF();
                System.out.println(str);
            }

        } catch (IOException e) {
                e.printStackTrace();
        }finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

