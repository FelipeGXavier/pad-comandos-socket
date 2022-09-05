package commands;

import util.ThreadStorage;

public class NotifyCommand extends CommandRequest{

    private final ThreadStorage threadStorage;

    public NotifyCommand(String[] options, ThreadStorage threadStorage) {
        super(options);
        this.threadStorage = threadStorage;
    }

    @Override
    public void execute() throws Exception {
        String nome = this.options[0];

        Thread thread = this.threadStorage.get(nome);

        System.out.println("Resumindo thread "+nome);

        synchronized (thread){
            thread.notify();
        }
    }
}
