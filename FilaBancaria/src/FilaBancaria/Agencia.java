package FilaBancaria;

import java.util.ArrayList;
import java.util.Random;

import FilaBancaria.fila.FilaAtendente;
import FilaBancaria.fila.FilaCliente;

public class Agencia {
    private final int TEMPO_SIMULACAO = 360;
    private final int QUANTIDADE_MIN_CAIXAS = 1;
    private final int QUANTIDADE_MAX_CAIXAS = 5;
    private final int QUANTIDADE_MIN_ATENDENTE = 2 * QUANTIDADE_MAX_CAIXAS;
    private final int QUANTIDADE_MAX_ATENDENTE = 2 * QUANTIDADE_MAX_CAIXAS;
    private final int NUMERO_MAX_CLIENTES = QUANTIDADE_MAX_CAIXAS;

    private final FilaCliente filaComum;
    private final FilaCliente filaPreferencial;
    private final FilaAtendente filaDescanso;
    private final ArrayList<Caixa> caixas;
    private final int numCaixas;
    private final int numAtendentes;

    private int tempo;

    private final Random random = new Random();

    public Agencia() {
        this.filaComum = new FilaCliente();
        this.filaPreferencial = new FilaCliente();
        this.filaDescanso = new FilaAtendente();
        this.caixas = new ArrayList<>();
        this.tempo = 0;
        this.numCaixas = random.nextInt(QUANTIDADE_MIN_CAIXAS, QUANTIDADE_MAX_CAIXAS + 1);
        this.numAtendentes = random.nextInt(QUANTIDADE_MIN_ATENDENTE, QUANTIDADE_MAX_ATENDENTE + 1);
        this.init();
    }

    public void init() {
        for (int i = 0; i < numAtendentes; i++) {
            filaDescanso.enqueue(new Atendente());
        }
        for (int i = 0; i < numCaixas; i++) {
            caixas.add(new Caixa());
        }
        for (Caixa caixa : caixas) {
            caixa.addAtendente(filaDescanso.dequeue());
        }
    }

    public void sortearClientes() {
        int numClientes = random.nextInt(NUMERO_MAX_CLIENTES);
        for (int i = 0; i < numClientes; i++) {
            Cliente cliente = new Cliente();
            if (cliente.isPreferencial()) filaPreferencial.enqueue(cliente);
            else filaComum.enqueue(cliente); 
        }
    }
}
