package vttp2022.miniproject02.server.model;

import jakarta.json.Json;
import jakarta.json.JsonObject;

public class Deal {
    
    String dealID;
    String dealRating;
    String storeID;
    String gameID;
    String title;
    String metacriticScore;
    String isOnSale;
    String salePrice;
    String normalPrice;
    String thumb;

    public String getDealID() {
        return dealID;
    }

    public void setDealID(String dealID) {
        this.dealID = dealID;
    }

    public String getDealRating() {
        return dealRating;
    }

    public void setDealRating(String dealRating) {
        this.dealRating = dealRating;
    }

    public String getStoreID() {
        return storeID;
    }

    public void setStoreID(String storeID) {
        this.storeID = storeID;
    }

    public String getGameID() {
        return gameID;
    }

    public void setGameID(String gameID) {
        this.gameID = gameID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMetacriticScore() {
        return metacriticScore;
    }

    public void setMetacriticScore(String metacriticScore) {
        this.metacriticScore = metacriticScore;
    }

    public String getIsOnSale() {
        return isOnSale;
    }

    public void setIsOnSale(String isOnSale) {
        this.isOnSale = isOnSale;
    }

    public String getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(String salePrice) {
        this.salePrice = salePrice;
    }

    public String getNormalPrice() {
        return normalPrice;
    }

    public void setNormalPrice(String normalPrice) {
        this.normalPrice = normalPrice;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public static Deal create(JsonObject joResult) {
        Deal d = new Deal();
        d.setDealID(joResult.getString("dealID"));
        d.setDealRating(joResult.getString("dealRating"));
        d.setStoreID(joResult.getString("storeID"));
        d.setGameID(joResult.getString("gameID"));
        d.setTitle(joResult.getString("title"));
        d.setMetacriticScore(joResult.getString("metacriticScore"));
        d.setIsOnSale(joResult.getString("isOnSale"));
        d.setSalePrice(joResult.getString("salePrice"));
        d.setNormalPrice(joResult.getString("normalPrice"));
        d.setThumb(joResult.getString("thumb"));
        return d;
    }

    public JsonObject toJson() {
        return Json.createObjectBuilder()
            .add("dealID", dealID)
            .add("dealRating", dealRating)
            .add("storeID", storeID)
            .add("gameID", gameID)
            .add("title", title)
            .add("metacriticScore", metacriticScore)
            .add("isOnSale", isOnSale)
            .add("salePrice", salePrice)
            .add("normalPrice", normalPrice)
            .add("thumb", thumb)
            .build();
    }

}
