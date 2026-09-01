package hva.core.exception;

public class DuplicateVaccineKeyExceptionCore extends Exception{

     /** @Serial Número de série para serialização. */
     private static final long serialVersionUID = 544433232356577L;
     private String _key;
     public DuplicateVaccineKeyExceptionCore(String key){
        _key = key;
     }
     public String getKey(){
        return _key;
     }
}
