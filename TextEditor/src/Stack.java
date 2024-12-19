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

    public T pop(int index) throws Exception {
        if (index < 0 || index >= top) throw new ArrayIndexOutOfBoundsException(index);
        else if (isEmpty()) throw new Exception("Erro: a pilha está vazia");
        else return array[--top];
    }
    
    public boolean isEmpty() {
        return top == 0;
    }

    public int size() {
        return top;
    }

}