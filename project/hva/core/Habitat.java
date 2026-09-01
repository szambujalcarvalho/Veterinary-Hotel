package hva.core;
import java.io.Serializable;
import java.util.*;

public class Habitat extends HotelEntity implements Serializable {

    /** @Serial Número de série para serialização. */
    private static final long serialVersionUID = -475388683668L;

    /** área do habitat */
    private int _area;

    private String _adequacao;

    private List<String> _especies;


    /** animais de um habitat */
    private List<String> _animaisId;

    /** IDs das árvores de um habitat */
    private List<String> _arvoresId;

    /**
    * Construtor principal do Habitat.
    * 
    * @param id        o ID do habitat
    * @param nome      o nome do habitat
    * @param area      a área do habitat
    * @param arvoresId lista de IDs das árvores associadas ao habitat
    */
    public Habitat (String id, String nome, int area, String adequacao, List<String> arvoresId) {
        super(id, nome);
        _area = area;
        _arvoresId = (arvoresId != null) ? arvoresId : new ArrayList<>();
        _animaisId = new ArrayList<>();
        _adequacao = adequacao;
        _especies = new ArrayList<>();
    }


    public int getPopulacao() {
        return _animaisId.size();
    }

    public List <String> getEspecies(){
        return _especies;
    }
    /**
     * @return a área do habitat
     */
    public int getArea() {
        return _area;
    }

    public String getAdequacao(){
        if(_adequacao == null){
            _adequacao = "NEU";
        }
        return _adequacao;
    }

    public void setAdequacao(String adequacaoNova, String especieId ){
        _adequacao = adequacaoNova;
        _especies.add(especieId);
    }

    /**
     * altera a area do habitat
     */
    public void setArea(int novaArea) {
        _area = novaArea;
    }

    /**
     * @return a lista de IDs dos animais do habitat
     */
    public List<String> getAnimais() {
        return _animaisId;
    }

    /**
     * @return a lista de IDs das árvores associadas ao habitat
     */
    public List<String> getArvoresId() {
        return _arvoresId;
    }

    /**
     * Adiciona um animal ao habitat.
     * 
     * @param animalId o ID do animal a ser adicionado
     */
    public void addAnimal(String animalId) {
        _animaisId.add(animalId);
    }

    /**
     * Remove um animal do habitat.
     * 
     * @param animalId o ID do animal a ser removido
     */
    public void removeAnimal(String animalId) {
        _animaisId.remove(animalId);
    }

    /**
     * Adiciona um ID de árvore à lista de árvores do habitat.
     * 
     * @param arvoresId o ID da árvore a ser adicionado
     */
    public void addArvoreId(String arvoresId) {
        if (_arvoresId == null) {
            _arvoresId = new ArrayList<>();
        }
        _arvoresId.add(arvoresId);
    }

    /**
     * Retorna uma representação textual do habitat, incluindo o número de árvores.
     * 
     * @return uma string representando o habitat
     */
    @Override
    public String toString() {
        int numeroDeArvores = (_arvoresId != null) ? _arvoresId.size() : 0;

        return "HABITAT|" + getId() + "|" + getNome() + "|" + getArea() + "|" + numeroDeArvores;
    }
}