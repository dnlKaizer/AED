package FilaBancaria;

import java.util.ArrayList;
import java.util.Random;

import FilaBancaria.fila.FilaAtendente;
import FilaBancaria.fila.FilaCliente;

public class Agencia {
    private final int TEMPO_SIMULACAO = 360;
    private final int QUANTIDADE_MIN_CAIXAS = 1;
    private final int QUANTIDADE_MAX_CAIXAS = 5;
    private final int NUMERO_MAX_CLIENTES = 3;

    private final FilaCliente filaComum;
    private final FilaCliente filaPreferencial;
    private final FilaAtendente filaDescanso;
    private final ArrayList<Caixa> caixas;

    private int tempo;
    private int preferenciasSeguidas;   
    private double coeficienteClientes; 
    private int numClientesExcedeuTempoFila;
    private int numClientesTotal;
    private int cargaHorariaTotal;

    private final Random random = new Random();

    public Agencia() {
        this.filaComum = new FilaCliente();
        this.filaPreferencial = new FilaCliente();
        this.filaDescanso = new FilaAtendente();
        this.caixas = new ArrayList<>();
        this.tempo = 0;
        this.preferenciasSeguidas = 0;
        this.coeficienteClientes = 0;
        this.numClientesExcedeuTempoFila = 0;
        this.init();
    }

    public void init() {
        int numCaixas = random.nextInt(QUANTIDADE_MIN_CAIXAS, QUANTIDADE_MAX_CAIXAS + 1);
        this.coeficienteClientes = ((double)numCaixas / 6) / NUMERO_MAX_CLIENTES;
        for (int i = 0; i < numCaixas; i++) {
            caixas.add(new Caixa());
        }
        int numAtendentes = 2 * numCaixas;
        for (int i = 0; i < numAtendentes; i++) {
            filaDescanso.enqueue(new Atendente());
        }
        for (Caixa caixa : caixas) {
            caixa.addAtendente(filaDescanso.dequeue());
        }
    }

    public FilaCliente getFilaComum() {
        return (FilaCliente) filaComum.clone();
    }

    public FilaCliente getFilaPreferencial() {
        return (FilaCliente) filaPreferencial.clone();
    }

    public FilaAtendente getFilaDescanso() {
        return (FilaAtendente) filaDescanso.clone();
    }

    public ArrayList<Caixa> getCaixas() {
        return new ArrayList<>(caixas);
    }

    public int getNumClientesExcedeuTempoFila() {
        return numClientesExcedeuTempoFila;
    }

    public int getCargaHorariaTotal() {
        return cargaHorariaTotal;
    }

    public int getNumClientesTotal() {
        return numClientesTotal;
    }

    public float getTempoMedioAtendimento() {
        return (float)cargaHorariaTotal / numClientesTotal;
    }

    public void execute() {
        // Executa durante o tempo determinado
        while (tempo < TEMPO_SIMULACAO) {
            sortearClientes();
            atualizarTudo();
            tempo++;
        }
        // Executa enquanto tiver algum cliente na fila
        while (!filaComum.isEmpty() || !filaPreferencial.isEmpty()) {
            atualizarTudo();
        }
        // Executa enquanto tiver algum cliente em qualquer caixa
        for (Caixa caixa : caixas) {
            while (caixa.temCliente()) {
                atualizarCaixas();
            }
        }
        // Coloca todos os atendentes na fila de descanso
        for (Caixa caixa : caixas) {
            if (caixa.temAtendente()) {
                Atendente atendente = caixa.getAtendente();
                atendente.irDescansar();
                filaDescanso.enqueue(atendente);
                numClientesTotal += caixa.getQtdAtendimentos();
                cargaHorariaTotal += caixa.getCargaHorariaAtendimentos();
            }
        }
    }

    public void sortearClientes() {
        int numClientes = random.nextDouble() < coeficienteClientes ? random.nextInt(1, NUMERO_MAX_CLIENTES + 1) : 0;
        for (int i = 0; i < numClientes; i++) {
            Cliente cliente = new Cliente();
            if (cliente.isPreferencial()) filaPreferencial.enqueue(cliente);
            else filaComum.enqueue(cliente); 
        }
    }

    public void atualizarTudo() {
        atualizarCaixas();
        filaComum.incrementarTempoEspera();
        filaPreferencial.incrementarTempoEspera();
        filaDescanso.incrementarTempoDescanso();
    }

    public void atualizarCaixas() {
        for (Caixa caixa : caixas) {
            if (caixa.acabouTurnoAtendente()) {
                Atendente atendente = caixa.getAtendente();
                atendente.irDescansar();
                filaDescanso.enqueue(atendente);
            }
            if (!caixa.temAtendente()) {
                if (filaDescanso.proximoAtendenteDescansou()) {
                    Atendente atendente = filaDescanso.dequeue();
                    atendente.irTrabalhar();
                    caixa.addAtendente(atendente);
                } else continue;
            }
            if (!caixa.temCliente()) {
                FilaCliente fila = verificarPreferencia();
                if (fila != null) {
                    Cliente cliente = fila.dequeue();
                    if (cliente.excedeuTempoFila()) numClientesExcedeuTempoFila++;
                    caixa.addCliente(cliente);
                }
            }
            caixa.atender();
        }
    }

    public FilaCliente verificarPreferencia() {
        if (!filaComum.isEmpty() && (filaPreferencial.isEmpty() || preferenciasSeguidas >= 2)) {
            preferenciasSeguidas = 0;
            return filaComum;
        }
        if (!filaPreferencial.isEmpty()) {
            preferenciasSeguidas++;
            return filaPreferencial;
        }
        return null;
    }

    public void relatorio() {
        for (Caixa caixa : caixas) {
            System.out.println(caixa);
        }
        while (!filaDescanso.isEmpty()) {
            System.out.println(filaDescanso.dequeue());
        }
        System.out.println("Carga Horária Total: " + cargaHorariaTotal);
        System.out.println("Quantidade de Clientes: " + numClientesTotal);
        System.out.println("Tempo Médio de Atendimentos: " + getTempoMedioAtendimento());
    }

}
