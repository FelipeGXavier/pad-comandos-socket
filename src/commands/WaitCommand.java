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
        if(this.threadStorage.getStorage().containsKey(name) &&
                this.threadStorage.getStorage().get(name).getState().equals(Thread.State.WAITING)){
            throw new RuntimeException("Thread [" + name + "] já está em estado de espera");
        }
        var t = new WaitThread(name);
        this.threadStorage.addThread(name, t);
        t.start();
    }
}
