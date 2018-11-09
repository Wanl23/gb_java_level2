package lesson6.maintask;

import java.awt.*;
import java.io.*;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        final String IP_ADDRESS = "localhost";
        int PORT = 8189;
        Socket socket = null;
        DataOutputStream out;
        BufferedReader bufferedReader;


        try {
            socket = new Socket(IP_ADDRESS, PORT);
            out = new DataOutputStream(socket.getOutputStream());
            bufferedReader = new BufferedReader(new InputStreamReader(System.in));

            Receiver receiver = new Receiver(socket);
            receiver.start();

            while (true){
                String msg = bufferedReader.readLine();
                out.writeUTF(msg);
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
