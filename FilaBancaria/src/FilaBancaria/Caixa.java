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

    public Atendente getAtendente() {
        Atendente at = this.atendente;
        this.atendente = null;
        return at;
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
                    atendente.incrementarQtdAtendimentos();
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

    @Override
    public String toString() {
        String n = System.lineSeparator();
        StringBuilder sb = new StringBuilder("{").append(n);
        sb.append("Atendente: ").append(atendente).append(n);
        sb.append("Cliente: ").append(cliente).append(n);
        sb.append("Carga Horária: ").append(cargaHorariaAtendimentos).append(n);
        sb.append("Quantidade: ").append(qtdAtendimentos).append(n);
        sb.append("}");
        return sb.toString();
    }

}
