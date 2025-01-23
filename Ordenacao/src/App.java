import java.util.Arrays;
import java.util.Random;

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

    public static Random random = new Random();
    
    public static void main(String[] args) throws Exception {
        Ordenar ordenar = new Ordenar();
        long tf, ti, soma;
        int porcentagem = 5;
        int tam = 5000;
        int nVezes = 100;
        int qtd = tam * porcentagem / 100;
        Dado[] dados = new Dado[tam];
        for (int i = 0; i < tam; i++) {
            dados[i] = new Dado(i + 1);
        }
        Dado[] copiaDados = Arrays.copyOf(dados, tam);

        System.out.println();
        System.out.println();
        System.out.println("Perc\tBol\tSel\tIns\tShel");

        for (int index = 0; index <= tam - qtd; index += qtd) {
            desordena(dados, qtd, index);
            System.out.print(((porcentagem * index / qtd) + 5));
            System.out.print("%\t");
            soma = 0;
            // Bolha
            for (int i = 0; i < nVezes; i++) {
                copiaDados = Arrays.copyOf(dados, tam);
                ti = System.currentTimeMillis();
                ordenar.bolha(copiaDados);
                tf = System.currentTimeMillis();
                soma += (tf - ti);
            }
            System.out.print((float)soma / nVezes);
            System.out.print("\t");
            soma = 0;
            // Seleção
            for (int i = 0; i < nVezes; i++) {
                copiaDados = Arrays.copyOf(dados, tam);
                ti = System.currentTimeMillis();
                ordenar.selecao(copiaDados);
                tf = System.currentTimeMillis();
                soma += (tf - ti);
            }
            System.out.print((float)soma / nVezes);
            System.out.print("\t");
            soma = 0;
            // Inserção Direta
            for (int i = 0; i < nVezes; i++) {
                copiaDados = Arrays.copyOf(dados, tam);
                ti = System.currentTimeMillis();
                ordenar.insercaoDireta(copiaDados);
                tf = System.currentTimeMillis();
                soma += (tf - ti);
            }
            System.out.print((float)soma / nVezes);
            System.out.print("\t");
            soma = 0;
            // Shell Sort
            for (int i = 0; i < nVezes; i++) {
                copiaDados = Arrays.copyOf(dados, tam);
                ti = System.currentTimeMillis();
                ordenar.shellSort(copiaDados);
                tf = System.currentTimeMillis();
                soma += (tf - ti);
            }
            System.out.print((float)soma / nVezes);
            System.out.println();
        }
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

    public static void desordena(Dado[] vet, int qtd, int index) {
        int tam = vet.length;
        for (int i = index; i < index + qtd; i++) {
            vet[i].setChave(random.nextInt(tam) + 1);
        }
    }

}
