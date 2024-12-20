import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class App {
    public static void main(String[] args) throws Exception {
        Editor editor = new Editor(readFile("text.txt"));
        System.out.println();
        System.out.println();
        System.out.println(editor.read());
    }

    public static String readFile(String url) {
        String text = "";
        try (BufferedReader br = new BufferedReader(new FileReader(url))) {
            text = br.readLine();
            if (text == null) text = "";
        } catch (IOException e) {
            e.printStackTrace();
        }
        return text;
    }
}
