package network;

import util.CommandArgsParser;
import util.ThreadStorage;

import java.net.ServerSocket;
import java.io.IOException;
import java.lang.ClassNotFoundException;
import java.net.Socket;

public class SocketServer {


    //static ServerSocket variable
    private static ServerSocket server;
    //socket server port on which it will listen
    private static int port = 9876;

    public static ThreadStorage threadStorage = new ThreadStorage();

    public static void main(String args[]) throws IOException {
        server = new ServerSocket(port);
        try {
            while (true) {
                Socket socket = server.accept();
                new Thread(new RequestHandler(socket, new CommandArgsParser())).start();
            }
        } catch (Exception ex) {
            server.close();
        }
    }

}
