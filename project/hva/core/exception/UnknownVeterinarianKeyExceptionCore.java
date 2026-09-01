package hva.core.exception;

public class UnknownVeterinarianKeyExceptionCore extends Exception{

     /** @Serial Número de série para serialização. */
     private static final long serialVersionUID = 9783264532765L;
     private String _key;
     public UnknownVeterinarianKeyExceptionCore(String key){
        _key = key;
     }
     public String getKey(){
        return _key;
     }
}
