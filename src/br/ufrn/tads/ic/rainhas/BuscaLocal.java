package br.ufrn.tads.ic.rainhas;

import java.util.*;

public class BuscaLocal {
    private Rainhas melhorSolucao;

    public BuscaLocal() {
        this.melhorSolucao = new Rainhas();
    }

    public void run() {
        // Aqui estou usando uma estrutura auto-ordenável para armazenar a vizinhança de uma solução
        // Com isso, não preciso buscar a melhor solução após gerar todos os vizinhos, basta recuperar a primeira
        TreeSet<Rainhas> vizinhanca = new TreeSet<Rainhas>(new Comparator<Rainhas>() {
            @Override
            public int compare(Rainhas o1, Rainhas o2) {
                return Integer.compare(o1.valor(), o2.valor());
            }
        });

        for (int rodada=0; true; rodada++) {
            vizinhanca.clear(); // Limpar o conjunto da vizinhança, pois estou em uma nova iteração
            for (int coluna=0; coluna<8; coluna++) {
                // Variar cada coluna individualmente, gerando 7 individuos diferentes
                for (int variacao=1;variacao<8;variacao++) {
                    Integer[] filho = melhorSolucao.getTabuleiro().clone(); // Clonando para fugir dos ponteiros implícitos
                    filho[coluna] = (filho[coluna] + variacao) % 8; // Com o operador %, eu garanto que vou permanecer
                                                                    // dentro dos valores válidos, não precisando tratar
                                                                    // situações onde posicionaria uma rainha fora do
                                                                    // tabuleiro
                    Rainhas rFilho = new Rainhas(filho);
                    vizinhanca.add(rFilho);
                }
            }
            // Recuperar menor valor
            Rainhas melhorVizinho = vizinhanca.first();
            // Testar se houve melhoria no valor
            if (melhorVizinho.valor() < melhorSolucao.valor()) {
                melhorSolucao = melhorVizinho;
                System.out.print("#"+rodada+" Nova melhor: ");
                printSolucao(melhorSolucao);

            } else {
                break;
            }
        }
    }

    public void printMelhor() {
        printSolucao(melhorSolucao);
    }

    public void printSolucao(Rainhas solucao) {
        for (int i=0; i<8;i++) {
            System.out.print(solucao.getTabuleiro()[i]+" ");
        }
        System.out.println("| valor: "+solucao.valor());
    }

    public static void main(String[] args) {
        BuscaLocal busca = new BuscaLocal();

        System.out.print("Inicial: ");
        busca.printMelhor(); // Imprime o melhor inicialmente, que foi gerado aleatoriamente
        busca.run(); // Executa busca local
        System.out.print("Final: ");
        busca.printMelhor(); // O melhor, aqui, será o melhor das vizinhanças
    }
}
