public class App {

    /*
     * Faça o gráfico dos tempos de execução dos algoritmos de ordenação.
     * 
     * – Implemente os métodos Bolha, Seleção, Inserção Direta e Shellsort;
     * 
     * – Inicialmente o vetor está ordenado (0% desordenado), a cada passo o vetor
     * terá aumento de 5% dos elementos fora de ordem. O último teste o vetor estará
     * 100% desordenado;
     * 
     * – Teste com um vetor de 50000 elementos;
     * 
     * – Com o objetivo de evitar interferência de outros processos, para cada
     * combinação [algoritmo | % de desordenado] deve-se calcular a média de 100
     * execuções;
     * 
     * – Construa a tabela e o gráfico (na planilha eletrônica) dos tempos de
     * execução para comparar o tempo de ordenação dos algoritmos pela quantidade de
     * elementos fora de ordem;
     * 
     * – Deve ser entregue um arquivo compactado (zip) com o(s) programa(s) .java e
     * um arquivo .xls (excel) com as tomadas de tempo;
     * 
     * – Esta atividade equivale a 2 h/a da disciplina AED1.
     */

    public static void main(String[] args) throws Exception {
        Ordenar ordenar = new Ordenar();
        int tam = 100;
        Dado[] dados = new Dado[tam];
        for (int i = 0; i < tam; i++) {
            dados[i] = new Dado(tam - i);
        }
        imprimirDados(dados);
    }

    public static void imprimirDados(Dado[] vet) {
        int tam = vet.length;
        if (tam == 0) return;
        System.out.println();
        System.out.print("[ ");
        for (int i = 0; i < tam - 1; i++) {
            System.out.print(vet[i]);
            System.out.print(" , ");
        }
        System.out.print(vet[tam - 1]);
        System.out.print(" ]");
        System.out.println();
    }
}
