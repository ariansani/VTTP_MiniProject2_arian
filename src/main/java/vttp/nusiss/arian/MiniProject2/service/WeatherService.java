package vttp.nusiss.arian.MiniProject2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.json.JsonObject;

@Service
public class WeatherService {


    private static final String WEATHER_URL = "https://api.open-meteo.com/v1/forecast";

    public String getWeather(String latitude, String longitude) {

        String url = UriComponentsBuilder.fromUriString(WEATHER_URL)
                .queryParam("latitude", latitude)
                .queryParam("longitude", longitude)
                .queryParam("timezone", "auto")
                .queryParam("daily", "temperature_2m_max,precipitation_sum,rain_sum,windspeed_10m_max")
                .toUriString();

        RequestEntity<Void> req = RequestEntity
                .get(url)
                .accept(MediaType.APPLICATION_JSON)
                .build();
        RestTemplate template = new RestTemplate();

        ResponseEntity<String> resp = null;
        try {
            resp = template.exchange(req, String.class);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

        
        return resp.getBody();
    }

}
