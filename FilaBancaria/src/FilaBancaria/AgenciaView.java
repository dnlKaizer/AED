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

        System.out.println();
        System.out.println("-----------------------------------------------------------------------------------------");
        System.out.println("Tempo: " + agencia.getTempo());
        System.out.println();
        System.out.println("Fila comum: " + filaComum);
        System.out.println("Fila preferencial: " + filaPreferencial);
        System.out.println("Fila descanso: " + filaDescanso);
        for (int i = 0; i < caixas.size(); i++) {
            Caixa caixa = caixas.get(i);
            System.out.println(caixa.getNome() + ": " + caixa);
        }
        System.out.println("-----------------------------------------------------------------------------------------");
    }
}
