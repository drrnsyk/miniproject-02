package vttp2022.miniproject02.server.model;

import jakarta.json.Json;
import jakarta.json.JsonObject;

public class Store {

    String storeID; 
    String storeName; 
    String isActive;
    String imagesBanner; 
    String imagesLogo; 
    String imagesIcon;

    public String getStoreID() {
        return storeID;
    }
    public void setStoreID(String storeID) {
        this.storeID = storeID;
    }
    public String getStoreName() {
        return storeName;
    }
    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }
    public String getIsActive() {
        return isActive;
    }
    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }
    public String getImagesBanner() {
        return imagesBanner;
    }
    public void setImagesBanner(String imagesBanner) {
        this.imagesBanner = imagesBanner;
    }
    public String getImagesLogo() {
        return imagesLogo;
    }
    public void setImagesLogo(String imagesLogo) {
        this.imagesLogo = imagesLogo;
    }
    public String getImagesIcon() {
        return imagesIcon;
    }
    public void setImagesIcon(String imagesIcon) {
        this.imagesIcon = imagesIcon;
    }

    public static Store create(JsonObject joResult, JsonObject joImages) {
        Store s = new Store();
        s.setStoreID(joResult.getString("storeID"));
        s.setStoreName(joResult.getString("storeName"));
        s.setIsActive(Integer.toString(joResult.getInt("isActive")));
        s.setImagesBanner("https://cheapshark.com" + joImages.getString("banner"));
        s.setImagesLogo("https://cheapshark.com" + joImages.getString("logo"));
        s.setImagesIcon("https://cheapshark.com" + joImages.getString("icon"));
        return s;
    }

    public JsonObject toJson() {
        return Json.createObjectBuilder()
            .add("storeID", storeID)
            .add("storeName", storeName)
            .add("isActive", isActive)
            .add("imagesBanner", imagesBanner)
            .add("imagesLogo", imagesLogo)
            .add("imagesIcon", imagesIcon)
            .build();
    }

}   
