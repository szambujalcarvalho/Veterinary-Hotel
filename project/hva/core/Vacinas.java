package hva.core;
import java.io.Serializable;

public class Vacinas extends HotelEntity implements Serializable {

     /** @Serial Número de série para serialização. */
    private static final long serialVersionUID = 8276382377L;


    /* Número de aplicações da vacina*/
    private int _aplicacoes;

    /* Espécies que podem tomar a vacina*/
    private String[] _idEspecies;


    /**
    * Main Construtor
    * @param id
    * @param nome
    * @param aplicacoes
    * @param idEspecies
    */

    public Vacinas(String id, String nome, int aplicacoes, String[] idEspecies){
        super(id, nome);
        _aplicacoes = aplicacoes;
        _idEspecies = idEspecies;

    }


    /** @return Número de aplicações da vacina */
    public int getAplicacoes(){
        return _aplicacoes;
    }

    public void aumentaAplicacao(){
        _aplicacoes +=1;
    }

    /** @return nome da vacina */
    public String [] getIDEspecies(){
        return _idEspecies;
    }



    
    public String toString() {
        StringBuilder especiesString = new StringBuilder();
        for (int i = 0; i < _idEspecies.length; i++) {
            especiesString.append(_idEspecies[i]);
            if (i < _idEspecies.length - 1) {
                especiesString.append(",");  
            }
        }

        return "VACINA" + "|" + getId() + "|" + getNome() + "|" + getAplicacoes() +
               (_idEspecies.length != 0 ? "|" + especiesString : "");
    }

}