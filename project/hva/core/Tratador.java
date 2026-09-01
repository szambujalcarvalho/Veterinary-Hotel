package hva.core;

import java.util.List;

public class Tratador extends Funcionarios {

    public Tratador(String id, String nome, List<String> idHabitats) {
        super("TRT", id, nome, idHabitats); 
    }

    @Override
    public List<String> getIdResponsabilidades() {
        return super._idResponsabilidades; 
    }

}