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

    public boolean temCliente() {
        return cliente != null;
    }

    public boolean temAtendente() {
        return atendente != null;
    }

    public void atender() {
        if (temAtendente()) {
            atendente.atender();
            if (temCliente()) {
                cliente.incrementarTempoAtendimento();
                cargaHorariaAtendimentos++;
                if (cliente.finalizouAtendimento()) {
                    cliente = null;
                    qtdAtendimentos++;
                }
            }
        }
    }

    public boolean acabouTurnoAtendente() {
        return atendente != null ? atendente.acabouTurno() : false;
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
