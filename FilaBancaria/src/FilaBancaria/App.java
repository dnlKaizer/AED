package FilaBancaria;
import java.util.Scanner;
import org.fusesource.jansi.AnsiConsole;

public class App {

    private static final Object trava = new Object();
    private static boolean pausa = false;
    private static AnsiUtils ansi = new AnsiUtils();
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
                ansi.moverCursorCima(1);
                while (!agencia.tempoAcabou()) {
                    synchronized (trava) {
                        while (pausa) {
                            trava.wait(); // Aguarda até ser notificado
                        }
                    }
                    if (agencia.getTempo() > 0) {
                        ansi.moverCursorCima(6 + numCaixas);
                        for (int i = 0; i < 5 + numCaixas; i++) {
                            ansi.limparLinha();
                            ansi.moverCursorBaixo(1);
                        }
                        ansi.moverCursorCima(5 + numCaixas);
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
        synchronized (trava) {
            pausa = true;
            moduloAdm();
        }
    }

    public static void resumeExecution() {
        synchronized (trava) {
            pausa = false;
            trava.notifyAll(); // Notifica todas as threads que estavam esperando
        }
    }

    public static void moduloAdm() {
        ansi.moverCursorCima(7 + numCaixas);
        for (int i = 0; i < 5 + numCaixas; i++) {
            ansi.limparLinha();
            ansi.moverCursorBaixo(1);
        }
        ansi.moverCursorCima(5 + numCaixas);

        @SuppressWarnings("resource")
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
                ansi.moverCursorCima(5);
                for (int i = 0; i < 5; i++) {
                    ansi.limparLinha();
                    ansi.moverCursorBaixo(1);
                }
                ansi.moverCursorBaixo(numCaixas + 1);
                resumeExecution();
                break;

            case 2:
                ansi.moverCursorCima(5);
                for (int i = 0; i < 5; i++) {
                    ansi.limparLinha();
                    ansi.moverCursorBaixo(1);
                }
                ansi.moverCursorCima(5);
                System.out.print("Digite o multiplicador: ");
                multiplicador = sc.nextInt();
                if (multiplicador < 1) multiplicador = 1;
                if (multiplicador > 200) multiplicador = 200;
                ansi.moverCursorCima(1);
                ansi.limparLinha();
                ansi.moverCursorBaixo(numCaixas + 6);
                resumeExecution();
                break;
        
            default:
                System.exit(0);
                break;
        }
    }
}