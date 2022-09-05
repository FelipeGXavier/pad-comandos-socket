package commands;

import util.ComplementaryStrand;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class NewCommand extends CommandRequest{

    private final Socket socket;

    public NewCommand(String[] options, Socket socket) {
        super(options);
        this.socket = socket;
    }

    @Override
    public void execute() throws Exception {
        new Thread(()->{ String dna = this.options[0];
            String result = new ComplementaryStrand().getComplementaryStrand(dna.split(""));
            try {
                ObjectInputStream ois = new ObjectInputStream(this.socket.getInputStream());
                String message = (String) ois.readObject();
                System.out.println("Message Received: " + message);
                ObjectOutputStream oos = new ObjectOutputStream(this.socket.getOutputStream());
                oos.writeObject(result);
                ois.close();
                oos.close();
                this.socket.close();
            }catch (Exception e) {
                e.printStackTrace();
            }}
        ).start();
    }
}
