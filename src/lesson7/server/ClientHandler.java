package lesson7.server;

import lesson7.AuthService;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.net.Socket;
import java.util.Arrays;

public class ClientHandler {
    Socket socket = null;

    DataInputStream in;
    DataOutputStream out;
    Server server;
    String nick;

    public ClientHandler(Server server, Socket socket) {

        try {
            this.server = server;
            this.socket = socket;
            this.in = new DataInputStream(socket.getInputStream());
            this.out = new DataOutputStream(socket.getOutputStream());

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
                                    setMsg("/authOk " + newNick);
                                    nick = newNick;
                                    server.addClient(ClientHandler.this);
                                    server.broadcastMsg(newNick + " connected");
                                    break;
                                }
                                else if(!server.isNicknameFree(newNick)){
                                    setMsg("This login is busy");
                                }
                                else {
                                    setMsg("Wrong login/password");
                                }
                            }
                        }

                        while (true){
                            String str = in.readUTF();
                            if(str.equals("/end")){
                                out.writeUTF("/serverClosed");
                                break;
                            }
                            if(str.startsWith("/w")){
                                String[] tokens = str.split(" ");
                                String msg = "";
                                for (int i = 2; i < tokens.length; i++) {
                                    msg += tokens[i] + " ";
                                }
                                server.wisper(msg, tokens[1]);
                            }
                            else {
                                server.broadcastMsg(str);
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

    public void setMsg(String msg) {
        try {
            out.writeUTF(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
