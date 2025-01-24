package FilaBancaria;

public class AnsiUtils {
    // Reset
    public static final String RESET = "\u001B[0m";

    /**
     * Move o cursor para cima <b>n</b> linhas.
     * 
     * @param num {@code int} n
      */
    public void moveCursorUp(int num) {
        System.out.print("\u001B[" + num + "A");
    }

    /**
     * Move o cursor para baixo <b>n</b> linhas.
     * 
     * @param num {@code int} n
      */
    public void moveCursorDown(int num) {
        System.out.print("\u001B[" + num + "B");
    }

    /**
     * Move o cursor para a esquerda <b>n</b> colunas.
     * 
     * @param num {@code int} n
      */
    public void moveCursorLeft(int num) {
        System.out.print("\u001B[" + num + "D");
    }

    /**
     * Move o cursor para a direita <b>n</b> colunas.
     * 
     * @param num {@code int} n
      */
    public void moveCursorRight(int num) {
        System.out.print("\u001B[" + num + "C");
    }

    /**
     * Salva a posição atual do cursor.
      */
    public void save() {
        System.out.print("\u001B[s");
    }

    /**
     * Retorna o cursor para a posição salva.
      */
    public void restore() {
        System.out.print("\u001B[u");
    }

    /**
     * Limpa a linha do cursor.
      */
    public void cleanLine() {
        System.out.print("\u001B[2K");
    }
    
    /**
     * Substitui a linha do cursor
     * pelo texto do parâmetro.
     * 
     * @param str {@code String} texto
      */
    public void replaceLine(String str) {
        cleanLine();
        System.out.print(str);
    }

    /**
     * Move o cursor para a primeira coluna
     * da linha do cursor.
      */
    public void start() {
        System.out.print("\u001b[1G");
    }

}
