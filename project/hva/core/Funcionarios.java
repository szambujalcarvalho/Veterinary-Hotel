package hva.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class Funcionarios extends HotelEntity implements Serializable {

    /** Serial number for serialization. */
    private static final long serialVersionUID = -2371248272319L;

    /** Função do Funcionario (Tratador ou Veterinário, por exemplo). */
    private String _funcao;

    /** Lista de IDs das responsabilidades (habitats ou espécies). */
    protected List<String> _idResponsabilidades; 

    /**
     * Construtor principal da classe Funcionarios.
     * @param funcao Função do funcionário (ex.: "Tratador" ou "Veterinário").
     * @param id Identificador único do funcionário.
     * @param nome Nome do funcionário.
     * @param idresponsabilidades Lista de IDs das responsabilidades (habitats ou espécies).
     */
    public Funcionarios(String funcao, String id, String nome, List<String> idResponsabilidades) {
        super(id, nome);  
        _funcao = funcao; 
        _idResponsabilidades = (idResponsabilidades != null) ? idResponsabilidades : new ArrayList<>();
    }

    /** @return a função do funcionário (ex.: Tratador ou Veterinário) */
    public String getFuncao() {
        return _funcao;
    }

    /** @return a lista de IDs das responsabilidades (habitats ou espécies) */
    public abstract List<String> getIdResponsabilidades(); 

    public void addResponsabilidade(String idResponsabilidade){
        _idResponsabilidades.add(idResponsabilidade);
     }
 
     public void removeResponsabilidade(String idResponsabilidade){
        _idResponsabilidades.remove(idResponsabilidade);
     }

    /** Método toString, que deve ser implementado pelas subclasses */
    public String toString(){
        List<String> responsabilidades = getIdResponsabilidades();
        String responsabilidadesString = (responsabilidades != null && !responsabilidades.isEmpty())
            ? String.join(",", responsabilidades)
            : "";
    // Retorna a string formatada, com verificação condicional para adicionar ou não as responsabilidades
    return getFuncao()+ "|" + getId() + "|" + getNome() +
           (!responsabilidadesString.isEmpty() ? "|" + responsabilidadesString : "");
    }
}