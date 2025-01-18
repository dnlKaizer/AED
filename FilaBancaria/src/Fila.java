import java.util.NoSuchElementException;

public class Fila<T> {
    
    private class Cell {
        Cell prox;
        T item;
        
        public Cell(T item) {
            this.item = item;
            this.prox = null;
        }
    }
    
    private Cell head;
    private Cell tail;

    private int size;

    public Fila() {
        head = new Cell(null);
        tail = head;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return head == tail;
    }

    public void enqueue(T item) {
        if (item == null) throw new IllegalArgumentException("Item nulo");
        tail.prox = new Cell(item);
        tail = tail.prox;
        size++;
    }

    public T dequeue() {
        if (size == 0) throw new NoSuchElementException("Lista vazia");
        T item = head.prox.item;
        head = head.prox;
        size--;
        return item;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[ ");
        for (Cell cell = head.prox; cell != null; cell = cell.prox) {
            sb.append(cell.item).append(" ");
        }
        sb.append("]");
        return sb.toString();
    }

}
