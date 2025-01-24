package FilaBancaria.fila;
import FilaBancaria.Cliente;

public class FilaCliente extends Fila<Cliente> {
    
    public void incrementarTempoEspera() {
        for (Cell cell = head.prox; cell != null; cell = cell.prox) {
            Cliente cliente = cell.item;
            cliente.incrementarTempoEsperaFila();
        }
    }

    @Override
    public Object clone() {
        FilaCliente novaFila = new FilaCliente();
        for (Cell cell = this.head.prox; cell != null; cell = cell.prox) {
            novaFila.enqueue(cell.item);
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
