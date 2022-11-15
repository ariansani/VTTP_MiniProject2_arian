package vttp.nusiss.arian.MiniProject2.model;

import java.util.Date;
import java.util.List;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;

public class SubPackage {

    private String packageUUID;

    public String getPackageUUID() {
        return packageUUID;
    }

    public void setPackageUUID(String packageUUID) {
        this.packageUUID = packageUUID;
    }



    private Integer userId;
    private Integer gymId;
    private String gymName;
    
    private Integer entryPasses;
    private Date expiryDate;
    private boolean expired;

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

    public Integer getEntryPasses() {
        return entryPasses;
    }

    public void setEntryPasses(Integer entryPasses) {
        this.entryPasses = entryPasses;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public boolean isExpired() {
        return expired;
    }

    public void setExpired(boolean expired) {
        this.expired = expired;
    }
    
    public String getGymName() {
        return gymName;
    }

    public void setGymName(String gymName) {
        this.gymName = gymName;
    }

   public static JsonObject toJson(SubPackage subPackage){
    return Json.createObjectBuilder()
    .add("packageUUID", subPackage.getPackageUUID())
    .add("gymId", subPackage.getGymId())
    .add("gymName", subPackage.getGymName())
    .add("userId", subPackage.getUserId())
    .add("entryPasses", subPackage.getEntryPasses())
    .add("expiryDate", subPackage.getExpiryDate().toString())
    .build();
   }

   public static JsonObject usePackageToJson(SubPackage subPackage){
    return Json.createObjectBuilder()
    .add("packageUUID", subPackage.getPackageUUID())
    .add("dateUsed", new Date().toString())
    .add("passesUsed", 1)
    .add("gymName", subPackage.getGymName())
    .add("userId", subPackage.getUserId())
    .add("gymId", subPackage.getGymId())
    .build();
   }

    
    public static JsonArray toJsonArray(List<SubPackage> packageList){
        JsonArrayBuilder array = Json.createArrayBuilder();
        for(SubPackage g : packageList){
            JsonObject jo = Json.createObjectBuilder()
            .add("packageUUID", g.getPackageUUID())
            .add("gymId", g.getGymId())
            .add("gymName", g.getGymName())
            .add("userId", g.getUserId())
            .add("entryPasses", g.getEntryPasses())
            .add("expiryDate", g.getExpiryDate().toString())
            .build();
            array.add(jo);
        }
        return array.build();
    }


}
