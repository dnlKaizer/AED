public class Editor {
    String text;
    Stack<Character> charStack;
    Stack<String> lineStack;

    public Editor(String text, int linesLimit) {
        this.text = text;
        this.charStack = new Stack<>(70);
        this.lineStack = new Stack<>(linesLimit);
    }

}
