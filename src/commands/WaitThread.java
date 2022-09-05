package commands;

public class WaitThread extends Thread {

    private final String name;

    public WaitThread(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        synchronized (this) {
            System.out.println("Iniciando Thread [" + this.name + "]");
            try {
                System.out.println("Thread [" + this.name + "] entrou em modo de espera por wait");
                this.wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Finalizando Thread [" + this.name + "] do modo de espera");
        }
    }
}
