package commands;

import util.ThreadStorage;

public class WaitCommand extends CommandRequest {

    private final ThreadStorage threadStorage;

    public WaitCommand(String[] options, ThreadStorage storage) {
        super(options);
        this.threadStorage = storage;
    }

    @Override
    public void execute() throws Exception {
        String name = this.options[0];
        var t = new WaitThread(name);
        this.threadStorage.addThread(name, t);
        t.start();
    }
}
