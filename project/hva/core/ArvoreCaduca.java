package hva.core;

public class ArvoreCaduca extends Arvore {

    /** @Serial Número de série para serialização. */
    private static final long serialVersionUID = 27364278646664733L;

    /**
     * Construtor para árvore caduca
     * 
     * @param id
     * @param nome
     * @param dificuldadeLimpeza
     * @param idade
     */
    public ArvoreCaduca(String id, String nome, int dificuldadeLimpeza, int idade) {
        super(id, nome, "CADUCA", dificuldadeLimpeza, idade);
    }

    @Override
    public String calcularCicloBiologico() {
        String estacaoAtual = Estacao.getEstacaoAtual(); 

        switch (estacaoAtual) {
            case "Inverno":
                return "SEMFOLHAS";
            case "Primavera":
                return "GERARFOLHAS";
            case "Verao":
                return "COMFOLHAS";
            case "Outono":
                return "LARGARFOLHAS";
        }
        return null;
    }

    @Override
    public int calcularEsforcoLimpeza() {
        int esforcoSazonal = 0;
        String estacaoAtual = Estacao.getEstacaoAtual();

        switch (estacaoAtual) {
            case "Inverno":
                esforcoSazonal = 0;
                break;
            case "Primavera":
                esforcoSazonal = 1;
                break;
            case "Verao":
                esforcoSazonal = 2;
                break;
            case "Outono":
                esforcoSazonal = 5;
                break;
        }
        double logIdade = Math.log(getIdade() + 1);
        double resultado = getDificuldadeLimpeza() * esforcoSazonal * logIdade;
            
        return (int) Math.round(resultado);
        
    }
}