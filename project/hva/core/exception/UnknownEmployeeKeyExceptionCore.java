package hva.core.exception;

public class UnknownEmployeeKeyExceptionCore extends Exception{

     /** @Serial Número de série para serialização. */
     private static final long serialVersionUID = 1123438738266L;
     private String _key;
     public UnknownEmployeeKeyExceptionCore(String key){
        _key = key;
     }
     public String getKey(){
        return _key;
     }
}