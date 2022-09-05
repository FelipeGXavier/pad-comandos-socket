package util;

import commands.*;
import network.SocketServer;

import java.net.Socket;
import java.util.List;

public class CommandArgsParser {

    public CommandRequest getCommand(String args, Socket socket) {
        return this.parseArguments(args, socket);
    }

    private CommandRequest parseArguments(String args, Socket socket) {
        CommandArgs commandArgs = this.getValidCommand(args);
        if (args.isEmpty() || commandArgs == null) {
            throw new RuntimeException("Invalid Command");
        }
        switch (commandArgs.getCommandName()) {
            case "sleep":
                return new SleepCommand(commandArgs.getOptions());
            case "new":
                return new NewCommand(commandArgs.getOptions(), socket);
            case "wait":
                return new WaitCommand(commandArgs.getOptions(), SocketServer.threadStorage);
            case "notify":
                return new NotifyCommand(commandArgs.getOptions(), SocketServer.threadStorage);
            default:
                return null;
        }
    }

    private CommandArgs getValidCommand(String args) {
        List<String> validCommandNames = List.of("new", "sleep", "wait", "notify");
        String[] commandData = args.split(" ");
        String firstArg = commandData[0];
        if (firstArg == null || !validCommandNames.contains(firstArg.toLowerCase())) {
            return null;
        }
        String[] extraArgs = new String[commandData.length - 1];
        System.arraycopy(commandData, 1, extraArgs, 0, commandData.length - 1);
        return new CommandArgs(firstArg, extraArgs);
    }
}
