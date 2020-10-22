package br.ufrn.tads.ic.rainhas;

import java.security.SecureRandom;

public class Rainhas {
    private Integer[] tabuleiro;

    public Rainhas() {
        tabuleiro = new Integer[8];
        // Randomizar posições iniciais das rainhas
        SecureRandom random = new SecureRandom();

        for (int i=0; i<8; i++)
            tabuleiro[i] = Math.abs(random.nextInt()) % 8;
    }

    public Rainhas(Integer[] tabuleiro) {
        this.tabuleiro = tabuleiro;
    }

    public Integer[] getTabuleiro() {
        return tabuleiro;
    }

    public Integer valor() {
        Integer valor = 0;

        for (int coluna=0; coluna<8; coluna++) {
            // Verificar quando as rainhas se atacam
            for (int variacao=1, i=coluna+1; i<8 ; variacao++, i++) {
                if (tabuleiro[coluna]==tabuleiro[i]) valor++; // rainha atacando na mesma linha
                if (tabuleiro[coluna]==tabuleiro[i]+variacao) valor++; // rainha atacando na diagonal pra cima
                if (tabuleiro[coluna]==tabuleiro[i]-variacao) valor++; // rainha atacando diagonal pra baixo
            }
        }
        return valor;
    }

}
