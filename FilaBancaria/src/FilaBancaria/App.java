package FilaBancaria;
import java.util.Scanner;
import org.fusesource.jansi.AnsiConsole;

public class App {

    private static final Object lock = new Object();
    private static boolean paused = false;
    private static AnsiUtils ansi = new AnsiUtils();
    private static boolean working = true;
    private static int multiplicador = 1;
    private static int numCaixas;

    public static void main(String[] args) {
        AnsiConsole.systemInstall();

        Agencia agencia = new Agencia();
        AgenciaView view = new AgenciaView(agencia);
        System.out.println();
        System.out.println();
        numCaixas = agencia.getNumCaixas();
        // Thread principal que executa a lógica do programa
        Thread mainThread = new Thread(() -> {
            try {
                Thread.sleep(1000); // Simula trabalho
                System.out.println("---------------------------------------------------------------" + 
                "-------------------------------------------");
                System.out.println();
                ansi.moveCursorUp(1);
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
                        ansi.moveCursorUp(5 + numCaixas);
                    }
                    agencia.sortearClientes();
                    agencia.atualizarTudo();
                    agencia.incrementarTempo();
                    System.out.println(view.imprimirFilas());
                    Thread.sleep(1000 / multiplicador); // Simula trabalho
                }
                // Executa enquanto tiver algum cliente na fila
                while (!agencia.isFilasVazias()) {
                    agencia.atualizarTudo();
                }
                // Executa enquanto tiver algum cliente em qualquer caixa
                while (agencia.isCaixasVazios()) {
                    agencia.atualizarCaixas();
                }
                // Coloca todos os atendentes na fila de descanso
                agencia.colocarAtendentesDescanso();
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
            moduloAdm();
        }
    }

    public static void resumeExecution() {
        synchronized (lock) {
            paused = false;
            lock.notifyAll(); // Notifica todas as threads que estavam esperando
        }
    }

    public static void moduloAdm() {
        ansi.moveCursorUp(7 + numCaixas);
        for (int i = 0; i < 5 + numCaixas; i++) {
            ansi.cleanLine();
            ansi.moveCursorDown(1);
        }
        ansi.moveCursorUp(5 + numCaixas);
        Scanner sc = new Scanner(System.in);
        System.out.println("0. Fechar programa");
        System.out.println("1. Finalizar execução (Pular para final)");
        System.out.println("2. Acelerar execução");
        System.out.println();
        System.out.print("Digite o código: ");
        int codigo = sc.nextInt();
        switch (codigo) {
            case 0:
                System.exit(0);
                break;
            case 1:
                multiplicador = 1000;
                ansi.moveCursorUp(5);
                for (int i = 0; i < 5; i++) {
                    ansi.cleanLine();
                    ansi.moveCursorDown(1);
                }
                ansi.moveCursorDown(numCaixas + 1);
                resumeExecution();
                break;

            case 2:
                ansi.moveCursorUp(5);
                for (int i = 0; i < 5; i++) {
                    ansi.cleanLine();
                    ansi.moveCursorDown(1);
                }
                ansi.moveCursorUp(5);
                System.out.print("Digite o multiplicador: ");
                multiplicador = sc.nextInt();
                if (multiplicador < 1) multiplicador = 1;
                if (multiplicador > 200) multiplicador = 200;
                ansi.moveCursorUp(1);
                ansi.cleanLine();
                ansi.moveCursorDown(numCaixas + 6);
                resumeExecution();
                break;
        
            default:
                break;
        }
    }
}