package hva.core;

import java.io.Serializable;

public class Estacao implements Serializable {

    /** @Serial Número de série para serialização. */
    private static final long serialVersionUID = 87886362626627L;

    // Array de estações
    private static final String[] ESTACOES = {"Primavera", "Verao", "Outono", "Inverno"};

    // Índice da estação atual (0 = Primavera, 1 = Verão, 2 = Outono, 3 = Inverno)
    private static int _estacaoAtual = 0;

    /** 
     * @return o nome da estação atual.
     */
    public static String getEstacaoAtual() {
        return ESTACOES[_estacaoAtual];
    }

    /**
     * Avança para a próxima estação.
     * Se a estação atual for Inverno (índice 3), volta para Primavera (índice 0).
     */
    public int proximaEstacao() {
       return _estacaoAtual = (_estacaoAtual + 1) % ESTACOES.length;  
    }

    public static int getEstacaoAtualIndex() {
        return _estacaoAtual;
    }
}
