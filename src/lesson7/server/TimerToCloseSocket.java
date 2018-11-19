package lesson7.server;

import javafx.concurrent.Task;
import lesson7.client.Controller;

import java.io.IOException;
import java.net.Socket;
import java.util.Vector;

public class TimerToCloseSocket extends Thread {

    private int timeOut = 120;
    private Socket socket;
    ClientHandler clientHandler;

    TimerToCloseSocket(Socket socket, ClientHandler clientHandler){
        this.socket = socket;
        this.clientHandler = clientHandler;
    }

    @Override
    public void run() {
        for (int i = 0; i < timeOut; i++) {
            if (clientHandler.authorised == true) {
                break;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (i == timeOut - 1) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }
}