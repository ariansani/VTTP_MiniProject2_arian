package vttp.nusiss.arian.MiniProject2.controller;


import java.util.LinkedList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import vttp.nusiss.arian.MiniProject2.exceptions.GymException;
import vttp.nusiss.arian.MiniProject2.model.Gym;
import vttp.nusiss.arian.MiniProject2.service.GymService;

@RestController
@RequestMapping("/api/gym")
public class GymRestController {
    
    @Autowired
    private GymService gymSvc;

    @GetMapping("/{id}")
    public ResponseEntity<String> findPackagesByUserId(@PathVariable Integer id){
        Gym gym = new Gym();


        try {

            gym = gymSvc.findGymById(id);
          
          return ResponseEntity.ok(Gym.toJson(gym).toString());


        } catch (GymException e) {
            // TODO: handle exception
            JsonObject errJson = Json.createObjectBuilder()
            .add("errorMessage",e.getReason()).build();
            return ResponseEntity.status(400).body(errJson.toString());
        }

    }

    @GetMapping("/gyms")
    public ResponseEntity<String> getAllGyms(){
        List<Gym> gyms = new LinkedList<>();
        try {

          gyms = gymSvc.selectAllGyms();
          
          return ResponseEntity.ok(Gym.toJson(gyms).toString());


        } catch (GymException e) {
            // TODO: handle exception
            JsonObject errJson = Json.createObjectBuilder()
            .add("errorMessage",e.getReason()).build();
            return ResponseEntity.status(400).body(errJson.toString());
        }

    }



   
}
