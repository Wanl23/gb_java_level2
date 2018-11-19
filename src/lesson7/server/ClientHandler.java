package lesson7.server;

import lesson7.AuthService;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler {
    Socket socket = null;

    DataInputStream in;
    DataOutputStream out;
    Server server;
    String nick;
    ArrayList<String> blacklist;
    boolean authorised = false;

    public ClientHandler(Server server, Socket socket) {

        try {
            this.server = server;
            this.socket = socket;
            this.in = new DataInputStream(socket.getInputStream());
            this.out = new DataOutputStream(socket.getOutputStream());
            this.blacklist = new ArrayList<>();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try{
                        while (true){
                            String str = in.readUTF();
                            if(str.startsWith("/auth")){
                                String[] tokens = str.split(" ");
                                String newNick = AuthService.getNickByLoginAndPass(tokens[1], tokens[2]);
                                if(newNick != null && server.isNicknameFree(newNick)){
                                    authorised = true;
                                    sendMsg("/authOk " + newNick);
                                    nick = newNick;
                                    server.addClient(ClientHandler.this);
                                    break;
                                }
                                else if(!server.isNicknameFree(newNick)){
                                    sendMsg("This login is busy");
                                }
                                else {
                                    sendMsg("Wrong login/password");
                                }
                            }
                        }

                        while (true) {
                            String str = in.readUTF();
                            if (str.startsWith("/")){
                                if (str.equals("/end")) {
                                    out.writeUTF("/serverClosed");
                                    break;
                                }
                                if (str.startsWith("/w ")) {
                                    String[] tokens = str.split(" ", 3);
                                    server.whisper(ClientHandler.this, tokens[2], tokens[1]);
                                }
                                if(str.startsWith("/blacklist ")){
                                    String[] tokens = str.split(" ");
                                    if(server.addUserToBL(tokens[1], ClientHandler.this.nick)){
                                        sendMsg(tokens[1] + " added to black list");
                                    }
                                    else {
                                        sendMsg("You can't add yourself to the black list");
                                    }
                                }
                            }
                            else {
                                server.broadcastMsg(ClientHandler.this, str);
                            }
                        }

                    }catch (IOException e){
                        e.printStackTrace();
                    }finally {
                        try {
                            in.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        try {
                            out.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        try {
                            socket.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        server.removeClient(ClientHandler.this);
                    }
                }
            }).start();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public boolean checkBL(String nickFrom){
        return AuthService.checkBL(nickFrom, this.nick);
    }

    public void sendMsg(String msg) {
        try {
            this.out.writeUTF(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
