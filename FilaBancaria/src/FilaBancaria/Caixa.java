package FilaBancaria;

public class Caixa {
    Atendente atendente;
    Cliente cliente;

    private int qtdAtendimentos;
    private int cargaHorariaAtendimentos;

    public Caixa() {
        this.atendente = null;
        this.cliente = null;
        this.qtdAtendimentos = 0;
        this.cargaHorariaAtendimentos = 0;
    }

    public int getQtdAtendimentos() {
        return qtdAtendimentos;
    }

    public int getCargaHorariaAtendimentos() {
        return cargaHorariaAtendimentos;
    }

    public void addCliente(Cliente cliente) {
        if (cliente == null) throw new IllegalArgumentException("Cliente nulo");
        this.cliente = cliente;
    }

    public void addAtendente(Atendente atendente) {
        if (atendente == null) throw new IllegalArgumentException("Atendente nulo");
        this.atendente = atendente;
    }

}
