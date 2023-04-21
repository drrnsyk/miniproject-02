package vttp2022.miniproject02.server.model;

import jakarta.json.Json;
import jakarta.json.JsonObject;

public class Detail {
    
    String storeID;
    String gameID; 
    String name; 
    String salePrice; 
    String retailPrice; 
    String steamRatingText; 
    String steamRatingPercent; 
    String steamRatingCount; 
    String metacriticScore; 
    String metacriticLink; 
    String releaseDatev;
    String publisher; 
    String thumb; 
    String cheaperDealID; 
    String cheaperStoreID; 
    String cheaperSalePrice; 
    String cheapestPrice; 
    String cheapestPriceDate;

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
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getSalePrice() {
        return salePrice;
    }
    public void setSalePrice(String salePrice) {
        this.salePrice = salePrice;
    }
    public String getRetailPrice() {
        return retailPrice;
    }
    public void setRetailPrice(String retailPrice) {
        this.retailPrice = retailPrice;
    }
    public String getSteamRatingText() {
        return steamRatingText;
    }
    public void setSteamRatingText(String steamRatingText) {
        this.steamRatingText = steamRatingText;
    }
    public String getSteamRatingPercent() {
        return steamRatingPercent;
    }
    public void setSteamRatingPercent(String steamRatingPercent) {
        this.steamRatingPercent = steamRatingPercent;
    }
    public String getSteamRatingCount() {
        return steamRatingCount;
    }
    public void setSteamRatingCount(String steamRatingCount) {
        this.steamRatingCount = steamRatingCount;
    }
    public String getMetacriticScore() {
        return metacriticScore;
    }
    public void setMetacriticScore(String metacriticScore) {
        this.metacriticScore = metacriticScore;
    }
    public String getMetacriticLink() {
        return metacriticLink;
    }
    public void setMetacriticLink(String metacriticLink) {
        this.metacriticLink = metacriticLink;
    }
    public String getReleaseDatev() {
        return releaseDatev;
    }
    public void setReleaseDatev(String releaseDatev) {
        this.releaseDatev = releaseDatev;
    }
    public String getPublisher() {
        return publisher;
    }
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
    public String getThumb() {
        return thumb;
    }
    public void setThumb(String thumb) {
        this.thumb = thumb;
    }
    public String getCheaperDealID() {
        return cheaperDealID;
    }
    public void setCheaperDealID(String cheaperDealID) {
        this.cheaperDealID = cheaperDealID;
    }
    public String getCheaperStoreID() {
        return cheaperStoreID;
    }
    public void setCheaperStoreID(String cheaperStoreID) {
        this.cheaperStoreID = cheaperStoreID;
    }
    public String getCheaperSalePrice() {
        return cheaperSalePrice;
    }
    public void setCheaperSalePrice(String cheaperSalePrice) {
        this.cheaperSalePrice = cheaperSalePrice;
    }
    public String getCheapestPrice() {
        return cheapestPrice;
    }
    public void setCheapestPrice(String cheapestPrice) {
        this.cheapestPrice = cheapestPrice;
    }
    public String getCheapestPriceDate() {
        return cheapestPriceDate;
    }
    public void setCheapestPriceDate(String cheapestPriceDate) {
        this.cheapestPriceDate = cheapestPriceDate;
    }

    public static Detail create(JsonObject joGameInfo, JsonObject joCheapestPrice) {
        Detail d = new Detail();
        d.setStoreID(joGameInfo.getString("storeID"));
        d.setGameID(joGameInfo.getString("gameID"));
        d.setName(joGameInfo.getString("name"));
        d.setSalePrice(joGameInfo.getString("salePrice"));
        d.setRetailPrice(joGameInfo.getString("retailPrice"));
        d.setPublisher(joGameInfo.getString("publisher"));
        d.setThumb(joGameInfo.getString("thumb"));
        d.setCheapestPrice(joCheapestPrice.getString("price"));
        d.setCheapestPriceDate(joCheapestPrice.getString("date"));
        return d;
    }

    public JsonObject toJson() {
        return Json.createObjectBuilder()
            .add("storeID", storeID)
            .add("gameID", gameID)
            .add("name", name)
            .add("salePrice", salePrice)
            .add("retailPrice", retailPrice)
            .add("publisher", publisher)
            .add("thumb", thumb)
            .add("cheapestPrice", cheapestPrice)
            .add("cheapestPriceDate", cheapestPriceDate)
            .build();
    }

}
