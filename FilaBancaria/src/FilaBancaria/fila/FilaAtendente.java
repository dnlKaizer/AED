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

    @Override
    public Object clone() {
        FilaAtendente novaFila = new FilaAtendente();
        for (Cell cell = this.head.prox; cell != null; cell = cell.prox) {
            novaFila.enfileirar(cell.item);
        }
        return novaFila;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[ ");
        for (Cell cell = head.prox; cell != null; cell = cell.prox) {
            sb.append(cell.item.getNome());
            if (cell.prox != null) sb.append(" , ");
        }
        sb.append(" ]");
        return sb.toString();
    }
    
}
