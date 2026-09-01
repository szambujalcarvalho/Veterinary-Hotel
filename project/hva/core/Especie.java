package hva.core;

import java.io.Serializable;
import java.util.ArrayList;


public class Especie extends HotelEntity implements Serializable {

    /** Número de série para serialização. */
    private static final long serialVersionUID = 2838788769932805382L;;

    /**
    * Main Construtor
    * @param id
    * @param nome
    */

    public Especie (String id, String nome){
        super(id,nome);
    }
    
}
