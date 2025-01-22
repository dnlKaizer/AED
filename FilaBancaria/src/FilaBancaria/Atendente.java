package FilaBancaria;

public class Atendente {
    private final int TEMPO_MAX_TRABALHO = 180;
    private final int TEMPO_DESCANSO = 30;

    private int qtdAtendimentos;
    private int cargaHorariaAtendimentos;

    private int tempoAtendimento;
    private int tempoDescanso;

    public Atendente() {
        this.qtdAtendimentos = 0;
        this.cargaHorariaAtendimentos = 0;
        this.tempoAtendimento = 0;
        this.tempoDescanso = 0;
    }

    public int getQtdAtendimentos() {
        return qtdAtendimentos;
    }

    public int cargaHorariaAtendimentos() {
        return cargaHorariaAtendimentos;
    }

    public void atender() {
        tempoAtendimento++;
        cargaHorariaAtendimentos++;
    }

    public void descansar() {
        tempoDescanso++;
    }

    public void irDescansar() {
        tempoAtendimento = 0;
    }

    public void irTrabalhar() {
        tempoDescanso = 0;
    }

    public void incrementarQtdAtendimentos() {
        qtdAtendimentos++;
    }

    public boolean acabouTurno() {
        return tempoAtendimento > TEMPO_MAX_TRABALHO;
    }

    public boolean estaDescansado() {
        return tempoDescanso > TEMPO_DESCANSO; 
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[ ");
        sb.append("Qtd Atendimentos: ").append(qtdAtendimentos).append(" , ");
        sb.append("Carga Horária: ").append(cargaHorariaAtendimentos).append(" , ");
        sb.append("Tempo atendimento: ").append(tempoAtendimento).append(" , ");
        sb.append("Tempo descanso: ").append(tempoDescanso);
        sb.append(" ]");
        return sb.toString();
    }

}
