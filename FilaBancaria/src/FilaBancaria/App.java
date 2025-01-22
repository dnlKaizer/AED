package FilaBancaria;
public class App {
    public static void main(String[] args) throws Exception {
        Agencia agencia = new Agencia();
        agencia.execute();
        agencia.relatorio();
    }
}
