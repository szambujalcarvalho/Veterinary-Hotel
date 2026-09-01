package hva.core;

import java.util.ArrayList;
import java.util.List;

public class Veterinario extends Funcionarios {

    private List<String> _vacinacoes;

    public Veterinario(String id, String nome, List<String> idEspecies) {
        super("VET", id, nome, idEspecies); 
        _vacinacoes = new ArrayList<>();
    }

    /** @return os registos de vacinacoes*/
    public List<String> getVacinacoes(){
        return _vacinacoes;
    }

    public void addVacinacao(String vacinacao){
        _vacinacoes.add(vacinacao);
    }

    @Override
    public List<String> getIdResponsabilidades() {
        return super._idResponsabilidades; 
    }
}