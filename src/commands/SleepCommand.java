package commands;

public class SleepCommand extends CommandRequest {

    public SleepCommand(String[] options) {
        super(options);
    }

    @Override
    public void execute() throws Exception {
        int delay = this.getDelayTime();
        System.out.println("Iniciando execução do comando Sleep com " + delay);
        Thread.sleep(this.getDelayTime());
        System.out.println("Finalizado execução do comando Sleep com " + delay);
    }

    private int getDelayTime() {
        String delayTime = this.options[0];
        try {
            return Integer.parseInt(delayTime);
        } catch (RuntimeException e) {
            throw new RuntimeException("Comando sleep deve especificar um tempo de delay");
        }
    }
}
