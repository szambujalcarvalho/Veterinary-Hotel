package hva.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Animal extends HotelEntity implements Serializable {

    /** @Serial Número de série para serialização. */
    private static final long serialVersionUID = -5755265040654157806L;

    /** Estado de saúde do animal */
    private List<String> _estadoSaude;
    
    /** ID do habitat */
    private String _habitatId;
    
    /** ID da espécie */
    private String _especieId;

    private List<String> _vacinacoes;

    /**
    * Main Construtor
    * @param id
    * @param nome
    * @param habitatId
    * @param especieId
    */

    public Animal(String id, String nome, String habitatId, String especieId ){
        super(id,nome);
        _habitatId = habitatId;
        _especieId = especieId;
        _estadoSaude = new ArrayList<>();
        _vacinacoes = new ArrayList<>();
    }


    /** @return o ID do habitat*/
    public String getHabitatId(){
        return _habitatId;
    }


    /** @return o ID da espécie*/
    public String getEspecieId(){
        return _especieId;
    }

    /** @return os registos de vacinacoes*/
    public List<String> getVacinacoes(){
        return _vacinacoes;
    }
    
    /** Altera o habitat quando ocorre transferencia*/
    public void setHabitat(String novoHabitatId){
        _habitatId = novoHabitatId;
    }

    /** @return o estado de saúde do animal */
    public List<String> getEstadoSaude(){
        return _estadoSaude;
    } 

    public void historialSaude(String estadoSaude){
        _estadoSaude.add(estadoSaude);
    }

    public void addVacinacao(String vacinacao){
        _vacinacoes.add(vacinacao);
    }
    
    public String toString(){
        if (_estadoSaude.isEmpty()){
            return "ANIMAL|" + getId() + "|" + getNome() + "|" + _especieId + "|" + "VOID" + "|" + _habitatId;
        }else{
            String estadoSaudeStr = String.join(",", _estadoSaude);
            return "ANIMAL|" + getId() + "|" + getNome() + "|" + _especieId + "|" + estadoSaudeStr + "|" + _habitatId;
        }
    }
    
}
