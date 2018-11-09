package lesson6.additionaltask.server;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;
import lesson6.additionaltask.server.ClientHandler;

public class Server {

    private Vector<ClientHandler> clients;

    public Server() {

        clients = new Vector<>();

        ServerSocket server = null;
        Socket socket = null;

        try{
            server = new ServerSocket(8189);
            System.out.println("Server started");

            while (true){
                socket = server.accept();
                System.out.println("Client connected");
                ClientHandler clientHandler = new ClientHandler(this, socket);
                clients.add(clientHandler);
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
        }
    }

    public void broadcastMsg(String msg){
        for(ClientHandler c : clients){
            c.setMsg(msg);
        }
    }

    public void removeClient(ClientHandler clientHandler){
        clients.remove(clientHandler);
    }
}
