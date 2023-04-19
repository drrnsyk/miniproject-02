package vttp2022.miniproject02.server.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import vttp2022.miniproject02.server.model.Account;
import vttp2022.miniproject02.server.repository.MySqlRepository;

@Service
public class AppService {

    @Autowired
    private MySqlRepository mySqlRepo;

    public String getAllAccounts() {

        List<Account> accounts = this.mySqlRepo.getAllAccounts();

        JsonArrayBuilder arrBuilder = Json.createArrayBuilder();

        for (Account acc: accounts)
            arrBuilder.add(acc.toJson());

        JsonArray jsonArrAccounts = arrBuilder.build();

        String jsonArrAccountsStr = jsonArrAccounts.toString();

        return jsonArrAccountsStr;
    }

    public Optional<Account> getAccountById(String id) {
        return this.mySqlRepo.getAccountById(id);
    }

    public void updateAccountById(String id, Account updatedAccount) {
        mySqlRepo.updateAccountById(id, updatedAccount);
    }

    public void deleteAccountById(String id) {
        mySqlRepo.deleteAccountById(id);
    }



}
