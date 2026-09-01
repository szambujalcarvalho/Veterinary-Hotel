package hva.core.exception;

public class UnknownVaccineKeyExceptionCore extends Exception{

     /** @Serial Número de série para serialização. */
     private static final long serialVersionUID = 23762655666662L;
     private String _key;
     public UnknownVaccineKeyExceptionCore(String key){
        _key = key;
     }
     public String getKey(){
        return _key;
     }
}
