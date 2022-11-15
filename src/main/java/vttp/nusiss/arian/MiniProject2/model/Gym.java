package vttp.nusiss.arian.MiniProject2.model;

import java.sql.Date;
import java.util.List;

import org.springframework.jdbc.support.rowset.SqlRowSet;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;

public class Gym {
    
    private Integer id;
    private String name;
    private String location;
    private Date routeReset;
    private String latitude;
    private String longitude;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public Date getRouteReset() {
        
        return routeReset;
    }
    public void setRouteReset(Date routeReset) {
        this.routeReset = routeReset;
    }

    public String getLatitude() {
        return latitude;
    }
    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }
    public String getLongitude() {
        return longitude;
    }
    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public static Gym create(SqlRowSet rs) {
        Gym gym = new Gym();

        gym.id = rs.getInt("id");
        gym.name = rs.getString("name");
        gym.location=rs.getString("location");
        gym.routeReset = rs.getDate("route_reset");
        gym.latitude=rs.getString("latitude");
        gym.longitude=rs.getString("longitude");

        return gym;
    }
    

    public static JsonArray toJson(List<Gym> gymList){
        JsonArrayBuilder array = Json.createArrayBuilder();
        for(Gym g : gymList){
            JsonObject jo = Json.createObjectBuilder()
            .add("id", g.getId())
            .add("name", g.getName())
            .add("location", g.getLocation())
            .add("routeReset", g.getRouteReset().toString())
            .add("latitude", Double.parseDouble(g.getLatitude()))
            .add("longitude", Double.parseDouble(g.getLongitude()))
            .build();
            array.add(jo);
        }
        return array.build();
    }

    public static JsonObject toJson(Gym g){
        
        return Json.createObjectBuilder()
        .add("id", g.getId())
            .add("name", g.getName())
            .add("location", g.getLocation())
            .add("routeReset", g.getRouteReset().toString())
            .add("latitude", Double.parseDouble(g.getLatitude()))
            .add("longitude", Double.parseDouble(g.getLongitude()))
        .build();
       }
    
    
}
