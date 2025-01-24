package FilaBancaria;

import java.text.DecimalFormat;
import java.util.ArrayList;

import FilaBancaria.fila.FilaAtendente;
import FilaBancaria.fila.FilaCliente;

public class AgenciaView {
    private final Agencia agencia;
    FilaCliente filaComum;
    FilaCliente filaPreferencial;
    FilaAtendente filaDescanso;
    ArrayList<Caixa> caixas;
    
    public AgenciaView(Agencia agencia) {
        this.agencia = agencia;
        this.filaComum = agencia.getFilaComum();
        this.filaPreferencial = agencia.getFilaPreferencial();
        this.filaDescanso = agencia.getFilaDescanso();
        this.caixas = agencia.getCaixas();
    }

    public String imprimirFilas() {
        String n = System.lineSeparator();
        StringBuilder sb = new StringBuilder();
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

    public String gerarRelatorio() {
        String n = System.lineSeparator();
        DecimalFormat df = new DecimalFormat("#,###.00");

        StringBuilder sb = new StringBuilder("--------------------------------------------");
        sb.append("--------------------------------------").append(n);
        sb.append("|     Tipo     | Quantidade de Clientes | Carga Horária | Tempo Méd. Atendimento |").append(n);

        sb.append("--------------------------------------------");
        sb.append("--------------------------------------").append(n);

        for (Caixa caixa : caixas) {
            sb.append(String.format(
                "| %12s | %22d | %13d | %22s |", 
                caixa.getNome(), caixa.getQtdAtendimentos(),
                caixa.getCargaHorariaAtendimentos(), df.format(caixa.getTempoMedioAtendimento())
            )).append(n);
        }
        sb.append("--------------------------------------------");
        sb.append("--------------------------------------").append(n);
        
        while (!filaDescanso.isEmpty()) {
            Atendente atendente = filaDescanso.desenfileirar();
            sb.append(String.format(
                "| %12s | %22d | %13d | %22s |", 
                atendente.getNome(), atendente.getQtdAtendimentos(),
                atendente.getCargaHorariaAtendimentos(), df.format(atendente.getTempoMedioAtendimento())
            )).append(n);
        }
        sb.append("--------------------------------------------");
        sb.append("--------------------------------------").append(n);
        
        sb.append(String.format(
            "|     Total    | %22d | %13d | %22s |", 
            agencia.getNumClientesTotal(),
            agencia.getCargaHorariaTotal(), df.format(agencia.getTempoMedioAtendimento())
        )).append(n);
        sb.append("--------------------------------------------");
        sb.append("--------------------------------------").append(n);

        return sb.toString();
    }
}
