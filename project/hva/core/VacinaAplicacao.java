package hva.core;

import java.io.Serializable;

public class VacinaAplicacao implements Serializable {
    
    /** Serial number for serialization. */
    private static final long serialVersionUID = 20231022L;

    /** Identificador da vacina aplicada */
    private String _idVacina;

    /** Identificador do funcionário (veterinário) que aplicou a vacina */
    private String _idVeterinario;

    /** Identificador da especie que recebeu a vacina */
    private String _idEspecie;

    /**
     * Construtor da classe VacinaAplicacao.
     * 
     * @param idVacina o ID da vacina aplicada
     * @param idVeterinario o ID do veterinário que aplicou a vacina
     * @param idEspecie o ID do animal que recebeu a vacina
     */
    public VacinaAplicacao(String idVacina, String idVeterinario, String idEspecie) {
        _idVacina = idVacina;
        _idVeterinario = idVeterinario;
        _idEspecie = idEspecie;
    }

    /** @return o ID da vacina aplicada */
    public String getIdVacina() {
        return _idVacina;
    }

    /** @return o ID do veterinário que aplicou a vacina */
    public String getIdVeterinario() {
        return _idVeterinario;
    }

    /** @return o ID do animal que recebeu a vacina */
    public String getIdAnimal() {
        return _idEspecie;
    }

    /** @return uma representação em string da aplicação da vacina no formato desejado */
    @Override
    public String toString() {
        return "REGISTO-VACINA|" + _idVacina + "|" + _idVeterinario + "|" + _idEspecie;
    }
}

