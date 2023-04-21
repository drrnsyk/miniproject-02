package vttp2022.miniproject02.server.model;

import jakarta.json.Json;
import jakarta.json.JsonObject;

public class Game {

    String gameID;
    String cheapest;
    String cheapestDealID;
    String external;
    String thumb;

    public String getGameID() {
        return gameID;
    }
    public void setGameID(String gameID) {
        this.gameID = gameID;
    }
    public String getCheapest() {
        return cheapest;
    }
    public void setCheapest(String cheapest) {
        this.cheapest = cheapest;
    }
    public String getCheapestDealID() {
        return cheapestDealID;
    }
    public void setCheapestDealID(String cheapestDealID) {
        this.cheapestDealID = cheapestDealID;
    }
    public String getExternal() {
        return external;
    }
    public void setExternal(String external) {
        this.external = external;
    }
    public String getThumb() {
        return thumb;
    }
    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public static Game create(JsonObject joResult) {
        Game g = new Game();
        g.setGameID(joResult.getString("gameID"));
        g.setCheapest(joResult.getString("cheapest"));
        g.setCheapestDealID(joResult.getString("cheapestDealID"));
        g.setExternal(joResult.getString("external"));
        g.setThumb(joResult.getString("thumb"));
        return g;
    }

    public JsonObject toJson() {
        return Json.createObjectBuilder()
            .add("gameID", gameID)
            .add("cheapest", cheapest)
            .add("cheapestDealID", cheapestDealID)
            .add("external", external)
            .add("thumb", thumb)
            .build();
    }

}
