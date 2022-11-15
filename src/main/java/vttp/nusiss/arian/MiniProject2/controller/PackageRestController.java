package vttp.nusiss.arian.MiniProject2.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import org.bson.Document;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import vttp.nusiss.arian.MiniProject2.exceptions.PackageException;
import vttp.nusiss.arian.MiniProject2.model.SubPackage;
import vttp.nusiss.arian.MiniProject2.service.PackageService;

@RestController
@RequestMapping("/api/package")
public class PackageRestController {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private PackageService packageSvc;

    @GetMapping("/{id}")
    public ResponseEntity<String> findPackagesByUserId(@PathVariable Integer id) {
        List<SubPackage> packages = new LinkedList<>();
        try {

            packages = packageSvc.findPackagesByUserId(id);

            return ResponseEntity.ok(SubPackage.toJsonArray(packages).toString());

        } catch (PackageException e) {
            // TODO: handle exception
            JsonObject errJson = Json.createObjectBuilder()
                    .add("errorMessage", e.getReason()).build();
            return ResponseEntity.status(400).body(errJson.toString());
        }

    }

    @GetMapping("/history/{id}")
    public ResponseEntity<String> findHistoryByUserId(@PathVariable Integer id) {
  
        Criteria criterial = Criteria.where("userId").is(id);
        Query query = Query.query(criterial);

        List<Document> result = mongoTemplate.find(
                query, Document.class, "packagesHistory");

        JsonArrayBuilder array = Json.createArrayBuilder();
        for (Document document : result) {
            JsonObject jo = Json.createObjectBuilder()
                    .add("packageUUID", document.getString("packageUUID"))
                    .add("dateUsed", document.getString("dateUsed"))
                    .add("passesUsed", document.getInteger("passesUsed").toString())
                    .add("gymName", document.getString("gymName"))
                    .add("userId", document.getInteger("userId").toString())
                    .add("gymId", document.getInteger("gymId").toString())
                    .build();
            array.add(jo);
        }
        JsonArray packageHistoryListJson = array.build();

        try {

            return ResponseEntity.ok(packageHistoryListJson.toString());

        } catch (Exception e) {
            // TODO: handle exception
            JsonObject errJson = Json.createObjectBuilder()
                    .add("errorMessage", e.getMessage()).build();
            return ResponseEntity.status(400).body(errJson.toString());
        }

    }

    @GetMapping("/uniquepackage/{uuid}")
    public ResponseEntity<String> findPackagesByUserId(@PathVariable String uuid) {
        SubPackage subPackage = new SubPackage();

        try {

            subPackage = packageSvc.findPackageByUUID(uuid);

            return ResponseEntity.ok(SubPackage.toJson(subPackage).toString());

        } catch (PackageException e) {
            // TODO: handle exception
            JsonObject errJson = Json.createObjectBuilder()
                    .add("errorMessage", e.getReason()).build();
            return ResponseEntity.status(400).body(errJson.toString());
        }

    }

    @PostMapping(path = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addSubPackage(@RequestBody String payload) {

        JsonObject responseJson;
        try (InputStream is = new ByteArrayInputStream(payload.getBytes())) {

            JsonReader reader = Json.createReader(is);
            JsonObject o = reader.readObject();

            SubPackage subPackage = new SubPackage();
            UUID generateUUID = UUID.randomUUID();

            String uuid = generateUUID.toString().substring(0, 8);

            subPackage.setPackageUUID(uuid);

            subPackage.setGymId(o.getInt("gymId"));
            subPackage.setUserId(o.getInt("userId"));
            subPackage.setEntryPasses(o.getInt("entryPasses"));

            // date parsing
            String dateString = o.getString("expiryDate");
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
            subPackage.setExpiryDate(formatter.parse(dateString));

            subPackage.setExpired(o.getBoolean("expired"));

            boolean addedSuccessfully = packageSvc.addSubPackage(subPackage);

            responseJson = Json.createObjectBuilder()
                    .add("status", addedSuccessfully)
                    .build();

            return ResponseEntity.ok(responseJson.toString());

        } catch (PackageException e) {
            JsonObject errJson = Json.createObjectBuilder()
                    .add("errorMessage", e.getReason()).build();
            return ResponseEntity.status(400).body(errJson.toString());

        } catch (ParseException e) {
            // TODO Auto-generated catch block
            JsonObject errJson = Json.createObjectBuilder()
                    .add("errorMessage", e.getMessage()).build();
            return ResponseEntity.status(400).body(errJson.toString());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            JsonObject errJson = Json.createObjectBuilder()
                    .add("errorMessage", e.getMessage()).build();
            return ResponseEntity.status(400).body(errJson.toString());
        }
    }

    @PutMapping(path = "/usepackage/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> usePackage(@RequestBody String payload) {
        boolean success = false;
        JsonObject responseJson;
        try (InputStream is = new ByteArrayInputStream(payload.getBytes())) {

            JsonReader reader = Json.createReader(is);
            JsonObject o = reader.readObject();
            String uuid = o.getString("uuid");

            boolean editSuccess = packageSvc.usePackage(uuid);

            responseJson = Json.createObjectBuilder()
                    .add("status", editSuccess)
                    .build();

        } catch (PackageException e) {
            // TODO: handle exception
            JsonObject errJson = Json.createObjectBuilder()
                    .add("errorMessage", e.getReason()).build();
            return ResponseEntity.status(400).body(errJson.toString());
        } catch (IOException e) {
            // TODO: handle exception
            JsonObject errJson = Json.createObjectBuilder()
                    .add("errorMessage", e.getMessage()).build();
            return ResponseEntity.status(400).body(errJson.toString());
        }
        return ResponseEntity.ok(responseJson.toString());
    }

    @PutMapping(path = "/edit", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> editSubPackage(@RequestBody String payload) {

        JsonObject responseJson;
        try (InputStream is = new ByteArrayInputStream(payload.getBytes())) {

            JsonReader reader = Json.createReader(is);
            JsonObject o = reader.readObject();

            SubPackage subPackage = new SubPackage();
            subPackage.setGymId(o.getInt("gymId"));
            subPackage.setUserId(o.getInt("userId"));
            subPackage.setEntryPasses(o.getInt("entryPasses"));
            subPackage.setPackageUUID(o.getString("packageUUID"));
            // date parsing
            String dateString = o.getString("expiryDate");
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
            subPackage.setExpiryDate(formatter.parse(dateString));

            subPackage.setExpired(o.getBoolean("expired"));

            boolean editSuccess = packageSvc.editSubPackage(subPackage);

            responseJson = Json.createObjectBuilder()
                    .add("status", editSuccess)
                    .build();

        } catch (PackageException e) {
            // TODO: handle exception
            JsonObject errJson = Json.createObjectBuilder()
                    .add("errorMessage", e.getReason()).build();
            return ResponseEntity.status(400).body(errJson.toString());
        } catch (ParseException e) {
            // TODO: handle exception
            JsonObject errJson = Json.createObjectBuilder()
                    .add("errorMessage", e.getMessage()).build();
            return ResponseEntity.status(400).body(errJson.toString());
        } catch (IOException e) {
            // TODO: handle exception
            JsonObject errJson = Json.createObjectBuilder()
                    .add("errorMessage", e.getMessage()).build();
            return ResponseEntity.status(400).body(errJson.toString());
        }
        return ResponseEntity.ok(responseJson.toString());
    }

    @DeleteMapping("/deletepackage/{uuid}")
    public ResponseEntity<String> deletePackageByUUID(@PathVariable String uuid) {

        boolean deleted = false;

        try {

            deleted = packageSvc.deletePackageByUUID(uuid);
            JsonObject response = Json.createObjectBuilder()
                    .add("status", deleted)
                    .build();
            return ResponseEntity.ok(response.toString());

        } catch (PackageException e) {
            // TODO: handle exception
            JsonObject errJson = Json.createObjectBuilder()
                    .add("errorMessage", e.getReason()).build();
            return ResponseEntity.status(400).body(errJson.toString());
        }

    }

}
