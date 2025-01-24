package FilaBancaria;
import java.util.Scanner;
public class App {

    private static final Object lock = new Object();
    private static boolean paused = false;

    public static void main(String[] args) {
        Agencia agencia = new Agencia();
        AgenciaView view = new AgenciaView(agencia);
        // Thread principal que executa a lógica do programa
        Thread mainThread = new Thread(() -> {
            try {
                while (!agencia.tempoAcabou()) {
                    synchronized (lock) {
                        while (paused) {
                            lock.wait(); // Aguarda até ser notificado
                        }
                    }
                    agencia.sortearClientes();
                    agencia.atualizarTudo();
                    agencia.incrementarTempo();
                    view.imprimirFilas();
                    Thread.sleep(1000); // Simula trabalho
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Thread interrompida.");
            }
        });

        mainThread.start();

        // Thread que lê comandos do terminal
        Thread inputThread = new Thread(() -> {
            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.println("Digite '.' para pausar:");
                String command = scanner.nextLine().trim().toLowerCase();

                if (command.equals(".")) {
                    pauseExecution();
                } else {
                    System.out.println("Comando desconhecido. Use '.'");
                }
            }
        });

        inputThread.setDaemon(true); // Torna a thread secundária como daemon (encerra junto com o programa principal)
        inputThread.start();
    }

    public static void pauseExecution() {
        synchronized (lock) {
            paused = true;
            System.out.println("Execução pausada.");
        }
    }

    public static void resumeExecution() {
        synchronized (lock) {
            paused = false;
            lock.notifyAll(); // Notifica todas as threads que estavam esperando
            System.out.println("Execução retomada.");
        }
    }
}