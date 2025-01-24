package FilaBancaria;

import java.util.ArrayList;

import FilaBancaria.fila.FilaAtendente;
import FilaBancaria.fila.FilaCliente;

public class AgenciaView {
    private final Agencia agencia;

    public AgenciaView(Agencia agencia) {
        this.agencia = agencia;
    }

    public String imprimirFilas() {
        FilaCliente filaComum = agencia.getFilaComum();
        FilaCliente filaPreferencial = agencia.getFilaPreferencial();
        FilaAtendente filaDescanso = agencia.getFilaDescanso();
        ArrayList<Caixa> caixas = agencia.getCaixas();

        String n = System.lineSeparator();
        StringBuilder sb = new StringBuilder(n);
        sb.append("Tempo: ").append(agencia.getTempo()).append(n);
        sb.append(n);
        sb.append("Fila comum: ").append(filaComum).append(n);
        sb.append("Fila preferencial: ").append(filaPreferencial).append(n);
        sb.append("Fila descanso: ").append(filaDescanso).append(n);
        for (int i = 0; i < caixas.size(); i++) {
            Caixa caixa = caixas.get(i);
            sb.append(caixa.getNome()).append(": ").append(caixa).append(n);
        }
        return sb.toString();
    }
}
