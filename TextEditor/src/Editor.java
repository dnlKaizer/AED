public class Editor {
    private String textFile;
    private Stack<Character> stack;

    private final char CANCEL_CHAR = '#';
    private final char CANCEL_LINE = '\\';
    private final char BREAK_LINE = '*';
    private final char BREAK_TEXT = '~';

    public Editor(String textFile) {
        this.textFile = textFile;
        this.stack = new Stack<>(70);
    }

    public String read() {
        String text = "";
        int index = 0;
        GetText:
        while (true) {
            Loop:
            for (; ; index++) {
                if (index >= textFile.length()) {
                    text += System.lineSeparator() + stackToString();
                    break GetText;
                }
                char character = textFile.charAt(index);

                if (character == BREAK_LINE) {
                    index++;
                    break Loop;
                }

                if (character == CANCEL_LINE) {
                    stack = new Stack<>(70);
                    index++;
                    continue GetText;
                }

                if (character == CANCEL_CHAR) {
                    try {
                        stack.pop();
                    } catch (Exception e) {}
                    continue Loop;
                }

                if (character == BREAK_TEXT) {
                    text += System.lineSeparator() + stackToString();
                    break GetText;
                }
    
                try {
                    stack.push(character);
                } catch(Exception e) {}
            }
            text += stackToString() + System.lineSeparator();
        }
        return text;
    }

    public String stackToString() {
        String line = "";
        while (stack.size() > 0) {
            try {
                line = stack.pop() + line;
            } catch(Exception e) {}
        }
        return line;
    } 

}
