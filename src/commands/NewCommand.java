package commands;

import util.ComplementaryStrand;

import java.io.ObjectOutputStream;
import java.net.Socket;

public class NewCommand extends CommandRequest {

    private final Socket socket;

    public NewCommand(String[] options, Socket socket) {
        super(options);
        this.socket = socket;
    }

    @Override
    public void execute() throws Exception {
        new Thread(() -> {
            String dna = this.options[0];
            String result = new ComplementaryStrand().getComplementaryStrand(dna.split(""));
            try {
                ObjectOutputStream oos = new ObjectOutputStream(this.socket.getOutputStream());
                System.out.println(result);
                oos.writeObject(result);
                oos.close();
                this.socket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        ).start();
    }
}
