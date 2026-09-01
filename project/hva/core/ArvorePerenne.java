package hva.core;

public class ArvorePerenne extends Arvore {

    /** @Serial Número de série para serialização. */
    private static final long serialVersionUID = 27364278646664734L;

    /**
     * Construtor para árvore perene
     * 
     * @param id
     * @param nome
     * @param dificuldadeLimpeza
     * @param idade
     */
    public ArvorePerenne(String id, String nome, int dificuldadeLimpeza, int idade) {
        super(id, nome, "PERENE", dificuldadeLimpeza, idade);
    }

    @Override
    public String calcularCicloBiologico() {
        String estacaoAtual = Estacao.getEstacaoAtual(); 

        switch (estacaoAtual) {
            case "Inverno":
                return "LARGARFOLHAS";
            case "Primavera":
                return "GERARFOLHAS";
            case "Verao":
                return "COMFOLHAS";
            case "Outono":
                return "COMFOLHAS";
        }
        return null;
    }

    @Override
    public int calcularEsforcoLimpeza() {
        int esforcoSazonal= 0;
        String estacaoAtual = Estacao.getEstacaoAtual();

        switch (estacaoAtual) {
            case "Inverno":
                esforcoSazonal = 2;
                break;
            case "Primavera":
                esforcoSazonal = 1;
                break;
            case "Verao":
                esforcoSazonal = 1;
                break;
            case "Outono":
                esforcoSazonal = 1;
                break;
        }

        double logIdade = Math.log(getIdade() + 1);
        double resultado = getDificuldadeLimpeza() * esforcoSazonal * logIdade;
    
        return (int) Math.round(resultado);
    }
}

