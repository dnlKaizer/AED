public class TADFila<T> {
    
    class Cell {
        T item;
        Cell prox;

        public Cell(T item) {
            this.item = item;
            this.prox = null;
        }
    }

    private Cell head;
    private Cell tail;

    public int size;

    public TADFila() {
        head = new Cell(null);
        tail = head;
    }

    public int size() {
        return size;
    }

    public void add(T item) {
        if (item == null) throw new IllegalArgumentException("Item nulo");
        tail.prox = new Cell(item);
        tail = tail.prox;
        size++;
    }

    public T get() {
        if (size == 0) throw new NullPointerException("Lista vazia");
        T item = head.prox.item;
        head = head.prox;
        size--;
        return item;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[ ");
        Cell cell = head;
        for (int i = 0; i < size; i++) {
            cell = cell.prox;
            sb.append(cell.item + " ");
        }
        sb.append("]");
        return sb.toString();
    }

}
