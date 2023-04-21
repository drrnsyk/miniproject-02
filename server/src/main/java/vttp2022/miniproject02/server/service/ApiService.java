package vttp2022.miniproject02.server.service;

import java.io.Reader;
import java.io.StringReader;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import vttp2022.miniproject02.server.model.Deal;
import vttp2022.miniproject02.server.model.Game;
import vttp2022.miniproject02.server.model.Store;
import vttp2022.miniproject02.server.repository.MySqlRepository;

@Service
public class ApiService {

    @Autowired
    private MySqlRepository mySqlRepo;
    
    // api call from cheapshark
    public static final String URL_NAME_DEALS = "https://www.cheapshark.com/api/1.0/deals";
    public static final String URL_NAME_GAMES = "https://www.cheapshark.com/api/1.0/games";
    public static final String URL_NAME_DETAIL = "https://www.cheapshark.com/api/1.0/deals";
    public static final String URL_NAME_STORES = "https://www.cheapshark.com/api/1.0/stores";


    public String getListOfDeals() {

        String payload;
        System.out.println(">>> Getting deals from cheapshark api");

        try {
            
            // construct the url
            String urlName = UriComponentsBuilder.fromUriString(URL_NAME_DEALS)
                .toUriString();
            
            // build the url
            RequestEntity<Void> req = RequestEntity.get(urlName).build();

            // declare template and response entity
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> resp;

            resp = restTemplate.exchange(req, String.class);

            payload = resp.getBody();

            // print out the payload to check
            // System.out.println("payload: " + payload);


        } catch (Exception e) {
            System.err.printf("error: %s\n", e.getMessage());
            return "";
        }

        Reader strReader = new StringReader(payload);
        JsonReader jsonReader = Json.createReader(strReader);
        JsonArray results = jsonReader.readArray();

        List<Deal> deals = new LinkedList<>();
        
        for (int i = 0; i < results.size(); i++) {
            JsonObject joResult = results.getJsonObject(i);
            deals.add(Deal.create(joResult));
        }

        JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();
        deals.stream()
            .forEach(d -> {
                jsonArrayBuilder.add(d.toJson());
            });
        
        JsonArray jsonArrayDeals = jsonArrayBuilder.build();
        // cache to redis here if needed
        String jsonArrayStringDeals = jsonArrayDeals.toString();

        return jsonArrayStringDeals;
    }


    public String getListOfGames(String newSearchTerm) {

        String payload;
        System.out.println(">>> Getting games from cheapshark api");

        try {
            
            // construct the url
            String urlName = UriComponentsBuilder.fromUriString(URL_NAME_GAMES)
                .queryParam("title", newSearchTerm)
                .toUriString();
            
            System.out.println(urlName);

            // build the url
            RequestEntity<Void> req = RequestEntity.get(urlName).build();

            // declare template and response entity
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> resp;

            resp = restTemplate.exchange(req, String.class);

            payload = resp.getBody();

            // print out the payload to check
            // System.out.println("payload: " + payload);


        } catch (Exception e) {
            System.err.printf("error: %s\n", e.getMessage());
            return "";
        }

        Reader strReader = new StringReader(payload);
        JsonReader jsonReader = Json.createReader(strReader);
        JsonArray results = jsonReader.readArray();

        List<Game> games = new LinkedList<>();
        
        for (int i = 0; i < results.size(); i++) {
            JsonObject joResult = results.getJsonObject(i);
            games.add(Game.create(joResult));
        }

        JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();
        games.stream()
            .forEach(g -> {
                jsonArrayBuilder.add(g.toJson());
            });
        
        JsonArray jsonArrayDeals = jsonArrayBuilder.build();
        // cache to redis here if needed
        String jsonArrayStringDeals = jsonArrayDeals.toString();

        return jsonArrayStringDeals;
    }


    public String getDealDetail(String dealID) {

        String payload;
        System.out.println(">>> Getting deal detail from cheapshark api");

        try {
            
            // construct the url
            String urlName = UriComponentsBuilder.fromUriString(URL_NAME_DETAIL)
                .queryParam("id", dealID)
                // .build(true)
                .toUriString();
            
            System.out.println(urlName);

            // build the url
            RequestEntity<Void> req = RequestEntity.get(urlName).build();

            // declare template and response entity
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> resp;

            resp = restTemplate.exchange(req, String.class);

            payload = resp.getBody();

            // print out the payload to check
            System.out.println("payload: " + payload);


        } catch (Exception e) {
            System.err.printf("error: %s\n", e.getMessage());
            return "";
        }

        // Reader strReader = new StringReader(payload);
        // JsonReader jsonReader = Json.createReader(strReader);
        // JsonObject jo = jsonReader.readObject();

        // JsonObject joGameInfo = jo.getJsonObject("gameInfo");
        // JsonObject joCheapestPrice = jo.getJsonObject("cheapestPrice");
        // Detail detail = new Detail();
        // detail = Detail.create(joGameInfo, joCheapestPrice);
 
        // JsonObject jsonDetail = detail.toJson();

        // String jsonStringDetail = jsonDetail.toString();

        return null;
    }


    public String getListOfStores() {

        String payload;
        System.out.println(">>> Getting stores from cheapshark api");

        try {
            
            // construct the url
            String urlName = UriComponentsBuilder.fromUriString(URL_NAME_STORES)
                .toUriString();
            
            // build the url
            RequestEntity<Void> req = RequestEntity.get(urlName).build();

            // declare template and response entity
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> resp;

            resp = restTemplate.exchange(req, String.class);

            payload = resp.getBody();

            // print out the payload to check
            System.out.println("payload: " + payload);


        } catch (Exception e) {
            System.err.printf("error: %s\n", e.getMessage());
            return "";
        }

        Reader strReader = new StringReader(payload);
        JsonReader jsonReader = Json.createReader(strReader);
        JsonArray results = jsonReader.readArray();

        List<Store> stores = new LinkedList<>();
        
        for (int i = 0; i < results.size(); i++) {
            JsonObject joResult = results.getJsonObject(i);
            JsonObject joImages = joResult.getJsonObject("images");
            stores.add(Store.create(joResult, joImages));
        }

        mySqlRepo.saveStoresToMySql(stores);

        JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();
        stores.stream()
            .forEach(s -> {
                jsonArrayBuilder.add(s.toJson());
            });
        
        JsonArray jsonArrayDeals = jsonArrayBuilder.build();

        String jsonArrayStringDeals = jsonArrayDeals.toString();

        return jsonArrayStringDeals;
    }


}
