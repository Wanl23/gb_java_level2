package lesson7.server;
import lesson7.AuthService;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

public class Server {

    private Vector<ClientHandler> clients;

    static Date dateNow = new Date();
    static SimpleDateFormat formatForDateNow = new SimpleDateFormat("MM.dd hh:mm:ss");
    static String date = formatForDateNow.format(dateNow);

    public Server() throws SQLException {

        clients = new Vector<>();

        ServerSocket server = null;
        Socket socket = null;



        try{
            AuthService.connect();
//            AuthService.addUser("login1", "pass1", "nick1");
//            AuthService.addUser("login2", "pass2", "nick2");
//            AuthService.addUser("login3", "pass3", "nick3");
            server = new ServerSocket(8189);

            System.out.println("Server started");

            while (true){
                socket = server.accept();
                System.out.println("Client connected");
                new ClientHandler(this, socket);

                checkSocketsAlive();

//                TimerToCloseSocket timerToCloseSocket = new TimerToCloseSocket(socket, new ClientHandler(this, socket));
//                timerToCloseSocket.start();
            }
        }
        catch (SocketException e){
            e.printStackTrace();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                server.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            AuthService.disconnect();
        }
    }

    public void broadcastMsg(ClientHandler from, String msg){


        for(ClientHandler c : clients){
            if(!c.checkBL(from.nick)) {
                c.sendMsg(date + " " + from.nick + ": " + msg);
            }
        }
    }

    public void whisper(ClientHandler from, String msg, String nickname){
        for(ClientHandler c : clients){
            if(c.nick.equals(nickname)){
                if(!c.checkBL(from.nick)) {
                    c.sendMsg(date + " whisper from " + from.nick + ": " + msg);
                    from.sendMsg(date + " whisper to " + nickname + ": " + msg);
                    return;
                }
                from.sendMsg("You can't sent messages to this user");
                return;
            }
        }
//        from.sendMsg("There are no clients with nickname: " + nickname); In the last version we don't need this message
    }

    public boolean isNicknameFree(String nickname){
        for (ClientHandler c: clients) {
            if(c.nick.equals(nickname)){
                return false;
            }
        }
        return true;
    }

    public void removeClient(ClientHandler clientHandler){
        clients.remove(clientHandler);
        broadcastClientList();
    }

    public void addClient(ClientHandler clientHandler){
        clients.add(clientHandler);
        broadcastClientList();
    }

    public void broadcastClientList(){
        StringBuilder sb = new StringBuilder();
        sb.append(("/clientlist "));

        for(ClientHandler c : clients){
            sb.append(c.nick + " ");
        }

        String out = sb.toString();

        for(ClientHandler c : clients){
            c.sendMsg(out);
        }
    }

    public boolean addUserToBL(String nickToBl, String forWhomBlocking){
        return AuthService.addUserToBL(nickToBl, forWhomBlocking);
    }

    public void checkSocketsAlive(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true) {
                    for (ClientHandler c : clients) {
                        if (c.socket.isClosed()) {
                            try {
                                c.socket.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            removeClient(c);
                        }
                    }
                    broadcastClientList();
                    try {
                        Thread.sleep(60000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

    }
}
