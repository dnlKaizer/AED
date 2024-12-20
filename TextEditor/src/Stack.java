public class Stack<T> {

    T[] array;
    int top;

    @SuppressWarnings("unchecked")
    public Stack(int length) {
        this.array = (T[]) new Object[length];
        this.top = 0;
    }

    public void push(T item) throws Exception {
        if (item == null) throw new NullPointerException();
        else if (top == this.array.length) throw new Exception("Erro: a pilha está cheia");
        else array[top++] = item;
    }

    public T pop() throws Exception {
        if (isEmpty()) throw new Exception("Erro: a pilha está vazia");
        return array[--top];
    }
    
    public boolean isEmpty() {
        return top == 0;
    }

    public int size() {
        return top;
    }

    public void clear() {
        while (!isEmpty()) {
            try {
                pop();
            } catch (Exception e) {}
        }
    }

    @Override
    public String toString() {
        if (isEmpty()) return "";
        StringBuilder sb = new StringBuilder("[ ");
        sb.append(array[0]);
        for (int i = 1; i < top; i++) {
            sb.append(", " + array[i]);
        }
        sb.append(" ]");
        return sb.toString();
    }

}