package lesson6.maintask;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        ServerSocket server = null;
        Socket socket = null;
        DataOutputStream out;
        BufferedReader bufferedReader;

        try {
            server = new ServerSocket(8189);
            System.out.println("Server started");

            socket = server.accept();
            System.out.println("Client connected");

            Receiver receiver = new Receiver(socket);
            receiver.start();

            out = new DataOutputStream(socket.getOutputStream());
            bufferedReader = new BufferedReader(new InputStreamReader(System.in));

            while (true){
                String msg = bufferedReader.readLine();
                out.writeUTF(msg);
            }

        }catch (IOException e){
            e.printStackTrace();
        }finally {

            try {

                socket.close();
                server.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
