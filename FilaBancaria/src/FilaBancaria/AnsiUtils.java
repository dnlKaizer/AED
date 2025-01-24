package FilaBancaria;

public class AnsiUtils {
    // Reset
    public static final String RESET = "\u001B[0m";

    /**
     * Move o cursor para cima <b>n</b> linhas.
     * 
     * @param num {@code int} n
      */
    public void moverCursorCima(int num) {
        System.out.print("\u001B[" + num + "A");
    }

    /**
     * Move o cursor para baixo <b>n</b> linhas.
     * 
     * @param num {@code int} n
      */
    public void moverCursorBaixo(int num) {
        System.out.print("\u001B[" + num + "B");
    }

    /**
     * Limpa a linha do cursor.
      */
    public void limparLinha() {
        System.out.print("\u001B[2K");
    }

}
