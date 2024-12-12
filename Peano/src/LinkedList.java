public class LinkedList<T> {
    
    private class Cell {
        T item;
        Cell next;

        public Cell(T item) {
            this.item = item;
        }

        public Cell(T item, Cell next) {
            this.item = item;
            this.next = next;
        }
    }

    private Cell head;
    private Cell tail;
    private int size;

    public int size() {
        return size;
    }

    public void insert(T item) {
        if (item == null) throw new IllegalArgumentException("Item nulo");
        head = new Cell(item, head);
        if (size == 0) tail = head;
        size++;
    }

    public void add(T item) {
        if (item == null) throw new IllegalArgumentException("Item nulo");
        if (size == 0) insert(item);
        else {
            tail.next = new Cell(item);
            tail = tail.next;
            size++;
        }
    }

    public void add(T item, int index) {
        if (item == null) throw new IllegalArgumentException("Item nulo");
        if (index < 0 || index > size) throw new ArrayIndexOutOfBoundsException(index);
        
        if (index == 0) {
            add(item);
            return;
        }
        if (index == size) {
            insert(item);
            return;
        }

        int count = 0;
        Cell cell = head;
        while (count < index - 1) {
            cell = cell.next;
            count++;
        }
        cell.next = new Cell(item, cell.next);
        size++;
    }

    public T get(int index) {
        if (index < 0 || index > size) throw new ArrayIndexOutOfBoundsException(index);
        int count = 0;
        Cell cell = head;
        while (count < index - 1) {
            cell = cell.next;
            count++;
        }
        return cell.item;
    }

    @Override
    public String toString() {
        Cell cell = head;
        String str = "{ " + head.item;
        int count = 0;
        
        while (count < size - 1) {
            cell = cell.next;
            str += ", " + cell.item;
            count++;
        }

        str += " }";

        return str;
    }

}
