package network;

import commands.CommandRequest;
import commands.NewCommand;
import util.CommandArgsParser;

import java.io.ObjectInputStream;
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
            CommandRequest commandRequest = this.commandArgsParser.getCommand(command, this.socket);
            commandRequest.execute();
            if (!(commandRequest instanceof NewCommand)) {
                this.socket.close();
                ois.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}