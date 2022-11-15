package vttp.nusiss.arian.MiniProject2.exceptions;

public class GymException extends Exception{

    private String reason;

    public GymException(String reason){
        super();
        this.reason = reason;
    }
    public String getReason(){
        return reason;
    }
    
}
