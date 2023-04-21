package vttp2022.miniproject02.server.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.json.JsonObject;
import vttp2022.miniproject02.server.model.Account;
import vttp2022.miniproject02.server.service.ApiService;
import vttp2022.miniproject02.server.service.AppService;

@Controller
@RequestMapping("/api")
public class AppController {

    @Autowired
    private AppService appSvc;

    @Autowired
    private ApiService apiSvc;

    // @GetMapping("/user")
    // @ResponseBody
    // @PreAuthorize("hasAnyAuthority('USER','ADMIN')")
    // public ResponseEntity<String> getUser() {
    //     return ResponseEntity.ok("USER DASHBOARD: Both users and admins have access to this page");
    // }

    // @GetMapping("/admin")
    // @ResponseBody
    // @PreAuthorize("hasAuthority('ADMIN')")
    // public ResponseEntity<String> getAdmin() {
    //     return ResponseEntity.ok("ADMIN DASHBOARD: Only admins have access to this page");
    // }

    @GetMapping("/dashboard/accounts")
    @ResponseBody
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> getAccounts() {

        System.out.println("getAccounts API called");

        String jsonArrAccountsStr = appSvc.getAllAccounts();

        return ResponseEntity.ok(jsonArrAccountsStr);
    }

    @GetMapping("/dashboard/account/{id}")
    @ResponseBody
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> getAccountById(@PathVariable String id) {
        
        System.out.printf(">>> path variable: id=%s\n", id);

        Optional<Account> opt = appSvc.getAccountById(id);

        if (opt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No account found");
        }

        Account account = opt.get();
        JsonObject joAccount = account.toJson();
        String joStrAccount = joAccount.toString();

        System.out.println(joStrAccount);

        return ResponseEntity.ok(joStrAccount);
    }

    @PutMapping(value="/dashboard/account/update/{id}", produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> updateAccountById(@PathVariable String id, @RequestBody Account updatedAccount) {

        System.out.printf(">>> path variable: id=%s\n", id);
        System.out.printf(">>> query param: name=%s\n", updatedAccount.getName());
        System.out.printf(">>> query param: email=%s\n", updatedAccount.getEmail());

        appSvc.updateAccountById(id, updatedAccount);

        // Create a map to hold the data
        Map<String, String> responseMap = new HashMap<>();
        responseMap.put("message", "Update successfull!");

        // Convert the map to JSON using Jackson ObjectMapper
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonResponse;
        try {
            jsonResponse = objectMapper.writeValueAsString(responseMap);
        } catch (JsonProcessingException e) {
            // Handle the exception
            jsonResponse = "{\"error\":\"Failed to create JSON response\"}";
        }

        return ResponseEntity.ok(jsonResponse);
    }

    @DeleteMapping("/dashboard/account/delete")
    @ResponseBody
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> deleteAccountById(@RequestParam String id) {

        System.out.printf(">>> query param: id=%s\n", id);

        appSvc.deleteAccountById(id);

        // Create a map to hold the data
        Map<String, String> responseMap = new HashMap<>();
        responseMap.put("message", "Delete successfull!");

        // Convert the map to JSON using Jackson ObjectMapper
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonResponse;
        try {
            jsonResponse = objectMapper.writeValueAsString(responseMap);
        } catch (JsonProcessingException e) {
            // Handle the exception
            jsonResponse = "{\"error\":\"Failed to create JSON response\"}";
        }

        return ResponseEntity.ok(jsonResponse);
    }

    @GetMapping("/home")
    @ResponseBody
    public ResponseEntity<String> getListOfDeals() {

        System.out.println("getListOfDeals() API called");

        // call the api
        String jsonArrayStringDeals = apiSvc.getListOfDeals();

        return ResponseEntity.ok(jsonArrayStringDeals);
    }

    @GetMapping("/search")
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('USER','ADMIN')")
    public ResponseEntity<String> getSearch(@RequestParam String searchTerm) {

        System.out.println("getListOfGames() API called");

        String newSearchTerm = searchTerm.replaceAll(" ", "");

        System.out.printf(">>> query param: searchTerm=%s\n", newSearchTerm);

        String jsonArrayStringGames = apiSvc.getListOfGames(newSearchTerm);

        return ResponseEntity.ok(jsonArrayStringGames);
    }

    @GetMapping("/deal/detail")
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('USER','ADMIN')")
    public ResponseEntity<String> getDealDetail(@RequestParam String dealID) {

        System.out.println("getDealDetail() API called");
        System.out.printf(">>> query param: dealID=%s\n", dealID);

        // String encodedDealID = URLEncoder.encode(dealID, "UTF-8");

        String jsonStringDetail = apiSvc.getDealDetail(dealID);

        return ResponseEntity.ok(jsonStringDetail);
    }

    @GetMapping("/stores")
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('USER','ADMIN')")
    public ResponseEntity<String> getListOfStores() {
    
        System.out.println("getListOfStores() API called");

        String jsonArrayStringStores = apiSvc.getListOfStores();

        return ResponseEntity.ok(jsonArrayStringStores);
    }
}
