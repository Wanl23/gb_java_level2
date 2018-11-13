package lesson7.server;
import lesson7.AuthService;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.Vector;

public class Server {

    private Vector<ClientHandler> clients;

    public Server() throws SQLException {

        clients = new Vector<>();

        ServerSocket server = null;
        Socket socket = null;

        try{
            AuthService.connect();
            server = new ServerSocket(8189);
            System.out.println("Server started");

            while (true){
                socket = server.accept();
                System.out.println("Client connected");
                new ClientHandler(this, socket);
            }
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

    public void broadcastMsg(String msg){
        for(ClientHandler c : clients){
            c.setMsg(msg);
        }
    }

    public void wisper(String msg, String nickname){
        for(ClientHandler c : clients){
            if(c.nick.equals(nickname)){
                c.setMsg(msg);
            }
        }
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
    }

    public void addClient(ClientHandler clientHandler){
        clients.add(clientHandler);
    }
}
