public class App {
    public static void main(String[] args) throws Exception {
        Conversor cv = new Conversor("text.txt");
        Editor editor = new Editor(cv.readText());
        String text = editor.read();
        System.out.println();
        System.out.println(text);
    }
}
