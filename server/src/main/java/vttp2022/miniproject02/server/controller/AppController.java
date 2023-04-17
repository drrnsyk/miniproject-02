package vttp2022.miniproject02.server.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api")
public class AppController {

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

    @GetMapping("/admin/accounts")
    @ResponseBody
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> getAccountsData() {

        System.out.println("getAccountsData API called");

        return ResponseEntity.ok(null);
    }

    @GetMapping("/user/search")
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('USER','ADMIN')")
    public ResponseEntity<String> getSearch() {

        System.out.println("getSearch API called");

        return ResponseEntity.ok(null);
    }

}
