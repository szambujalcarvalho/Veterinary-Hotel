package hva.core.exception;

public class DuplicateEmployeeKeyExceptionCore extends Exception{

     /** @Serial Número de série para serialização. */
     private static final long serialVersionUID = 273647826277678812L;
     private String _key;
     public DuplicateEmployeeKeyExceptionCore(String key){
        _key = key;
     }
     public String getKey(){
        return _key;
     }
}
