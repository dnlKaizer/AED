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

    public void insercaoDireta(Item[] vet) {
        if (vet == null) throw new IllegalArgumentException("Vetor nulo");
        int i, j, n = vet.length;
        Item aux;
        for (i = 1; i < n; i++) {
            aux = vet[i];
            j = i - 1;
            while ((j >= 0) && (aux.compara(vet[j]) < 0)) {
                vet[j + 1] = vet[j];
                j--;
            }
            vet[j + 1] = aux;
        }
    }

    public void shellSort(Item[] vet) {
        if (vet == null) throw new IllegalArgumentException("Vetor nulo");
        int n = vet.length;
        int i, j;
        int h = 1;
        Item aux;
        do {
            h = h * 3 + 1;
        } while (h < n);
        do {
            h /= 3;
            for (i = h; i < n; i++) {
                aux = vet[i];
                j = i;
                while ((j >= h) && aux.compara(vet[j - h]) < 0) {
                    vet[j] = vet[j - h];
                    j -= h;
                }
                vet[j] = aux;
            }
        } while (h != 1);
    }
}