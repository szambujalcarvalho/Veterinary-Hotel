package hva.core;

import java.io.Serializable;

public abstract class HotelEntity implements Serializable {

    /** Serial number for serialization. */
    private static final long serialVersionUID = -809010677570144248L;

    /** id da entidade do hotel */
    private String _id;

    /** nome da entidade */
    private String _nome;

    /**
     * Main Construtor
     * @param id
     * @param nome
     */
    public HotelEntity (String id, String nome){
        _id = id;
        _nome = nome;
    }
    
    /** @return id da entidade */
    protected String getId(){
        return _id;
    }

    /** @return nome da entidade */
    protected String getNome(){
        return _nome;
    }

     /** Verificar se há duas entidades iguais*/
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        
        if (obj == null || getClass() != obj.getClass()) {
            return false; 
        }
    
        HotelEntity outraEntidade = (HotelEntity) obj;
    
        return (_nome != null ? _nome.equals(outraEntidade.getNome()) : outraEntidade.getNome() == null) &&
               (_id != null ? _id.equals(outraEntidade.getId()) : outraEntidade.getId() == null);
    }

    
    public int hashCode() {
        int result = (_nome != null) ? _nome.hashCode() : 0;
        result = 31 * result + ((_id != null) ? _id.hashCode() : 0);
        return result;
    }
}
