package FilaBancaria;

import java.util.Random;

public class Cliente {
    private final int TEMPO_MAX_ESPERA = 30;
    private final int TEMPO_MAX_ATENDIMENTO = 10;
    private final int TEMPO_MIN_ATENDIMENTO = 1; 
    private final double CHANCE_PREFERENCIAL = 0.2;

    private final boolean isPreferencial;
    private final int tempoNecessarioAtendimento;

    private int tempoAtendimento;
    private int tempoEsperaFila;

    private final Random random = new Random();

    public Cliente() {
        this.isPreferencial = random.nextDouble() < CHANCE_PREFERENCIAL;
        this.tempoNecessarioAtendimento = random.nextInt(TEMPO_MIN_ATENDIMENTO, TEMPO_MAX_ATENDIMENTO + 1);
        this.tempoAtendimento = 0;
        this.tempoEsperaFila = 0;
    }

    public boolean isPreferencial() {
        return isPreferencial;
    }

    public int getTempoNecessarioAtendimento() {
        return tempoAtendimento;
    }

    public void incrementarTempoAtendimento() {
        this.tempoAtendimento++;
    }

    public void incrementarTempoEsperaFila() {
        this.tempoEsperaFila++;
    }

    public boolean excedeuTempoFila() {
        return tempoEsperaFila > TEMPO_MAX_ESPERA;
    }

    public boolean finalizouAtendimento() {
        return tempoAtendimento >= tempoNecessarioAtendimento;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[ ");
        sb.append("Preferencial: ").append(isPreferencial ? "Sim":"Não").append(" , ");
        sb.append("Tempo espera: ").append(tempoEsperaFila).append(" , ");
        sb.append("Tempo atendimento: ").append(tempoAtendimento).append(" , ");
        sb.append("Tempo atendimento total: ").append(tempoNecessarioAtendimento);
        sb.append(" ]");
        return sb.toString();
    }

    
}
