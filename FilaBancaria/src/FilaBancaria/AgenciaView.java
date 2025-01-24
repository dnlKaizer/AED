package FilaBancaria;

import java.util.ArrayList;

import FilaBancaria.fila.FilaAtendente;
import FilaBancaria.fila.FilaCliente;

public class AgenciaView {
    private final Agencia agencia;

    public AgenciaView(Agencia agencia) {
        this.agencia = agencia;
    }

    public void imprimirFilas() {
        FilaCliente filaComum = agencia.getFilaComum();
        FilaCliente filaPreferencial = agencia.getFilaPreferencial();
        FilaAtendente filaDescanso = agencia.getFilaDescanso();
        ArrayList<Caixa> caixas = agencia.getCaixas();

        System.out.println("Fila comum: " + filaComum);
        System.out.println("Fila preferencial: " + filaPreferencial);
        System.out.println("Fila descanso: " + filaDescanso);
        for (int i = 0; i < caixas.size(); i++) {
            System.out.println("Caixa " + (i + 1) + ": " + caixas.get(i));
        }
    }
}
