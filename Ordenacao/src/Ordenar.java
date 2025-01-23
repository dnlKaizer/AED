public class Ordenar {
    
    public void bolha(Item[] vet) {
        if (vet == null) throw new IllegalArgumentException("Vetor nulo");
        int n = vet.length;
        Item aux;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 1; j < n - i; j++) {
                if (vet[j].compara(vet[j - 1]) < 0) {
                    aux = vet[j];
                    vet[j] = vet[j - 1];
                    vet[j - 1] = aux;
                }
            }
        }
    }

    public void selecao(Item[] vet) {
        if (vet == null) throw new IllegalArgumentException("Vetor nulo");
        int n = vet.length;
        int min;
        Item aux;
        for (int i = 0; i < n - 1; i++) {
            min = i;
            for (int j = i + 1; j < n; j++) {
                if (vet[j].compara(vet[min]) < 0) min = j;
            }
            aux = vet[min];
            vet[min] = vet[i];
            vet[i] = aux;
        }
    }
}