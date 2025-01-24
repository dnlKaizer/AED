package FilaBancaria;

public class Caixa {
    private Atendente atendente;
    private Cliente cliente;

    private final String nome;
    private int qtdAtendimentos;
    private int cargaHorariaAtendimentos;

    public Caixa(String nome) {
        this.nome = nome;
        this.atendente = null;
        this.cliente = null;
        this.qtdAtendimentos = 0;
        this.cargaHorariaAtendimentos = 0;
    }

    public String getNome() {
        return nome;
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

    public float getTempoMedioAtendimento() {
        return (float)cargaHorariaAtendimentos / qtdAtendimentos;
    }

    public boolean temCliente() {
        return cliente != null;
    }

    public boolean temAtendente() {
        return atendente != null;
    }

    public void atender() {
        if (temAtendente()) {
            atendente.incrementarTempoAtendimento();
            if (temCliente()) {
                atendente.incrementarCargaHoraria();
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
        StringBuilder sb = new StringBuilder("[ ");
        sb.append(atendente == null ? "-":atendente.getNome()).append(" , ");
        sb.append(cliente == null ? "-":cliente.getNome()).append(" , ");
        sb.append("Quantidade: ").append(qtdAtendimentos).append(" , ");
        sb.append("Carga Horária: ").append(cargaHorariaAtendimentos);
        sb.append(" ]");
        return sb.toString();
    }

}
