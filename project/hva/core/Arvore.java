package hva.core;

import java.io.Serializable;

public abstract class Arvore extends HotelEntity implements Serializable {

    /** @Serial Número de série para serialização. */
    private static final long serialVersionUID = 27364278646664732L;

    /** Tipo de folha (caduca ou perene) */
    private String _tipoFolha;

    /** Dificuldade de limpeza */
    private int _dificuldadeLimpeza;

    /** Idade da árvore */
    private int _idade;

    /** Esteção em que foi plantada */
    private int _estacaoPlantada;

    /**
     * Construtor principal
     * 
     * @param id
     * @param nome
     * @param tipoFolha
     * @param dificuldadeLimpeza
     * @param idade
     */
    public Arvore(String id, String nome, String tipoFolha, int dificuldadeLimpeza, int idade) {
        super(id, nome);
        _tipoFolha = tipoFolha;
        _dificuldadeLimpeza = dificuldadeLimpeza;
        _idade = idade;
        _estacaoPlantada = Estacao.getEstacaoAtualIndex();
    }

    /** @return o tipo de folha da árvore */
    public String getTipoFolha() {
        return _tipoFolha;
    }

    /** @return a dificuldade de limpeza */
    public int getDificuldadeLimpeza() {
        return _dificuldadeLimpeza;
    }

    /** @return a idade da árvore */
    public int getIdade() {
        return _idade;
    }

    /**
     * calcula o esforço de limpeza da arvore.
     * 
     * @return o valor equivalente
     */
    public abstract int calcularEsforcoLimpeza();

    /**
     * aumenta a idade.
     * 
     * @param estaçãoAtual estação em que se encontra
     */
    public void incrementarIdadeSeForEstacaoPlantio(int estacaoAtual) {
        if (estacaoAtual == _estacaoPlantada) {
            _idade++;
        }
    }

    /** Método abstrato para calcular o ciclo biológico */
    public abstract String calcularCicloBiologico();

    @Override
    public String toString() {
        String cicloBiologico = calcularCicloBiologico();
        return "ÁRVORE|" + getId() + "|" + getNome() + "|" + _idade + "|" + _dificuldadeLimpeza + "|" + _tipoFolha + "|" + cicloBiologico;
    }
}