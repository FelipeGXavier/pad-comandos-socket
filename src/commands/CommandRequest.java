package commands;

abstract public class CommandRequest {
    protected final String[] options;

    public CommandRequest(String[] options) {
        this.options = options;
    }

    public abstract void execute() throws Exception;
}
