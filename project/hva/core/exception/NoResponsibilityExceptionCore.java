package hva.core.exception;

public class NoResponsibilityExceptionCore extends Exception{

     /** @Serial Número de série para serialização. */
     private static final long serialVersionUID = 2222737473788L;
     private String _key;
     private String _nome; 
     public NoResponsibilityExceptionCore(String key,String nome){
        _key = key;
        _nome = nome;
     }
     public String getKey(){
        return _key;
     }

     public String getNome(){
      return _nome;
     }

}