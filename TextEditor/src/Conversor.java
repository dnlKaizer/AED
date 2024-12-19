import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Conversor {
    private final String url;

    public Conversor(String url) {
        this.url = url;
    }

    public String readText() {
        String text = "";
        try (BufferedReader br = new BufferedReader(new FileReader(url))) {
            text = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return text;
    }
}

