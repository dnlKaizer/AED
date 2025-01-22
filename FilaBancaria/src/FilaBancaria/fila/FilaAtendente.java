package FilaBancaria.fila;
import FilaBancaria.Atendente;

public class FilaAtendente extends Fila<Atendente> {
    public void incrementarTempoDescanso() {
        for (Cell cell = head.prox; cell != null; cell = cell.prox) {
            Atendente atendente = cell.item;
            atendente.descansar();
            System.out.println(atendente);
        }
    }
}
