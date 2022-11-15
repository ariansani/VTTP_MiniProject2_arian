package vttp.nusiss.arian.MiniProject2.model;

public class MongoPackageHistory {
    
    private String packageUUID;
    private Integer userId;
    private Integer gymId;
    private String gymName;
    private String dateUsed;
    private Integer passesUsed;
    
    public String getPackageUUID() {
        return packageUUID;
    }
    public void setPackageUUID(String packageUUID) {
        this.packageUUID = packageUUID;
    }
    public Integer getUserId() {
        return userId;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public Integer getGymId() {
        return gymId;
    }
    public void setGymId(Integer gymId) {
        this.gymId = gymId;
    }
    public String getGymName() {
        return gymName;
    }
    public void setGymName(String gymName) {
        this.gymName = gymName;
    }
    public String getDateUsed() {
        return dateUsed;
    }
    public void setDateUsed(String dateUsed) {
        this.dateUsed = dateUsed;
    }
    public Integer getPassesUsed() {
        return passesUsed;
    }
    public void setPassesUsed(Integer passesUsed) {
        this.passesUsed = passesUsed;
    }
    

}
