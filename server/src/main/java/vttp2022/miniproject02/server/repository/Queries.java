package vttp2022.miniproject02.server.repository;

public class Queries {
    
    public static final String SQL_SELECT_ALL_ACCOUNTS = "SELECT id, email, name, password, role FROM users";

    public static final String SQL_SELECT_ACCOUNT_BY_ID = "SELECT id, email, name, password, role FROM users WHERE id = ?";
    
    public static String SQL_UPDATE_ACCOUNT_BY_ID = "UPDATE users SET email = ?, name = ? WHERE id = ?";
    
    public static String SQL_DELETE_ACCOUNT_BY_ID = "DELETE FROM users WHERE id = ?";

    public static String SQL_INSERT_STORES = "INSERT INTO stores (storeID, storeName, isActive, imagesBanner, imagesLogo, imagesIcon) VALUES (?, ?, ?, ?, ?, ?) ON DUPLICATE KEY UPDATE storeName = VALUES(storeName), isActive = VALUES(isActive), imagesBanner = VALUES(imagesBanner), imagesLogo = VALUES(imagesLogo), imagesIcon = VALUES(imagesIcon)";
}
