package FilaBancaria.fila;
import FilaBancaria.Atendente;

public class FilaAtendente extends Fila<Atendente> {
    public void incrementarTempoDescanso() {
        for (Cell cell = head.prox; cell != null; cell = cell.prox) {
            Atendente atendente = cell.item;
            atendente.descansar();
        }
    }

    public boolean proximoAtendenteDescansou() {
        return (head.prox != null && head.prox.item.estaDescansado());
    }
}
