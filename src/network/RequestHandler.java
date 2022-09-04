package network;

import util.CommandArgsParser;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class RequestHandler implements Runnable {

    private final Socket socket;
    private final CommandArgsParser commandArgsParser;

    public RequestHandler(Socket socket, CommandArgsParser commandArgsParser) {
        this.socket = socket;
        this.commandArgsParser = commandArgsParser;
    }

    @Override
    public void run() {
        try {
            ObjectInputStream ois = new ObjectInputStream(this.socket.getInputStream());
            String command = (String) ois.readObject();
            System.out.println("Command Received: " + command);
            this.commandArgsParser.getCommand(command).execute();
            ObjectOutputStream oos = new ObjectOutputStream(this.socket.getOutputStream());
            //oos.writeObject(tape);
            ois.close();
            oos.close();
            this.socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}