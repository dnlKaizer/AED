package FilaBancaria;
import java.util.Scanner;
import org.fusesource.jansi.AnsiConsole;

public class App {

    private static final Object lock = new Object();
    private static boolean paused = false;
    private static AnsiUtils ansi = new AnsiUtils();

    public static void main(String[] args) {
        AnsiConsole.systemInstall();

        Agencia agencia = new Agencia();
        AgenciaView view = new AgenciaView(agencia);
        System.out.println();
        System.out.println();
        int numCaixas = agencia.getNumCaixas();
        // Thread principal que executa a lógica do programa
        Thread mainThread = new Thread(() -> {
            try {
                Thread.sleep(1000); // Simula trabalho
                System.out.println("---------------------------------------------------------------------------------------");
                System.out.println();
                ansi.moveCursorUp(2);
                while (!agencia.tempoAcabou()) {
                    synchronized (lock) {
                        while (paused) {
                            lock.wait(); // Aguarda até ser notificado
                        }
                    }
                    if (agencia.getTempo() > 0) {
                        ansi.moveCursorUp(6 + numCaixas);
                        for (int i = 0; i < 5 + numCaixas; i++) {
                            ansi.cleanLine();
                            ansi.moveCursorDown(1);
                        }
                        ansi.moveCursorUp(6 + numCaixas);
                    }
                    agencia.sortearClientes();
                    agencia.atualizarTudo();
                    agencia.incrementarTempo();
                    System.out.println(view.imprimirFilas());
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
            @SuppressWarnings("resource")
            Scanner scanner = new Scanner(System.in);
            System.out.println("Digite 'ENTER' para pausar quando achar necessário. Uma nova interface irá te guiar. Programa iniciando...");
            while (true) {
                String command = scanner.nextLine().trim().toLowerCase();

                if (command.equals("")) {
                    pauseExecution();
                } else {
                    System.out.println("Comando desconhecido. Use '.'");
                }
            }
        });

        inputThread.setDaemon(true); // Torna a thread secundária como daemon (encerra junto com o programa principal)
        inputThread.start();

        AnsiConsole.systemUninstall();
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

    public static void moduloAdm() {
        System.out.println();
        System.out.println("---------------------------------------");
        System.out.println("1. Finalizar execução");
        System.out.println("2. Acelerar execução");
    }
}