package vttp.nusiss.arian.MiniProject2.exceptions;

public class PackageException extends Exception{

    private String reason;

    public PackageException(String reason){
        super();
        this.reason = reason;
    }

    public String getReason(){
        return reason;
    }
    
}
