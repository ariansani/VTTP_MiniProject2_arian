package vttp.nusiss.arian.MiniProject2.controller;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import vttp.nusiss.arian.MiniProject2.model.Gym;
import vttp.nusiss.arian.MiniProject2.service.GymService;
import vttp.nusiss.arian.MiniProject2.service.WeatherService;

@RestController
@RequestMapping("/api/weather")
public class WeatherRestController {
    
    @Autowired
    private WeatherService weatherSvc;

    @Autowired
    private GymService gymSvc;

    @GetMapping("/{id}")
    public ResponseEntity<String> CallWeatherApiWithGymId(@PathVariable Integer id){
        
        Gym gym = new Gym();
        JsonObject jo;
        try {

            gym = gymSvc.findGymById(id);
            
          String weather = weatherSvc.getWeather(gym.getLatitude(), gym.getLongitude());
          
          try (InputStream is = new ByteArrayInputStream(weather.getBytes(StandardCharsets.UTF_8))) {
            
            JsonReader r = Json.createReader(is);
            JsonObject o = r.readObject();
           jo=o;
           
        } catch (Exception e) {
            //TODO: handle exception
            e.printStackTrace();
            return null;
        }

          return ResponseEntity.ok(jo.toString());


        } catch (Exception e) {
            // TODO: handle exception
            JsonObject errJson = Json.createObjectBuilder()
            .add("errorMessage",e.getMessage()).build();
            return ResponseEntity.status(400).body(errJson.toString());
        }

    }

}
