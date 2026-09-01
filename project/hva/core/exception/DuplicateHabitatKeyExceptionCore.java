package hva.core.exception;

public class DuplicateHabitatKeyExceptionCore extends Exception {
    
    private static final long serialVersionUID = -7523967970034938905L;
    private String _key;

    public DuplicateHabitatKeyExceptionCore(String key){
        _key = key;
    }

    public String getKey(){
        return _key;
    }
}
