package FilaBancaria.fila;
import FilaBancaria.Cliente;

public class FilaCliente extends Fila<Cliente> {
    
    public void incrementarTempoEspera() {
        for (Cell cell = head.prox; cell != null; cell = cell.prox) {
            Cliente cliente = cell.item;
            cliente.incrementarTempoEsperaFila();
        }
    }
}
