package commands;

public class CommandArgs {

    private final String commandName;
    private final String[] options;

    public CommandArgs(String commandName, String[] options) {
        this.commandName = commandName;
        this.options = options;
    }

    public String getCommandName() {
        return commandName;
    }

    public String[] getOptions() {
        return options;
    }
}
