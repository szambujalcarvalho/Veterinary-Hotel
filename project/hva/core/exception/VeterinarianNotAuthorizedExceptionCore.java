package hva.core.exception;

public class VeterinarianNotAuthorizedExceptionCore extends Exception{

     /** @Serial Número de série para serialização. */
     private static final long serialVersionUID = 234726645767676L;
     private String _key;
     private String _name;
     public VeterinarianNotAuthorizedExceptionCore(String key, String name){
        _key = key;
        _name = name;
     }
     public String getKey(){
        return _key;
     }
     public String getName(){
        return _name;
     }
}