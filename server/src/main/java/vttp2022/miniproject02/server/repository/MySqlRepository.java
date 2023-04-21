package vttp2022.miniproject02.server.repository;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import vttp2022.miniproject02.server.model.Account;
import vttp2022.miniproject02.server.model.Store;

import static vttp2022.miniproject02.server.repository.Queries.*;

@Repository
public class MySqlRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Account> getAllAccounts() {
        List<Account> accounts = new LinkedList<>();
        SqlRowSet rs = null;
        rs = jdbcTemplate.queryForRowSet(SQL_SELECT_ALL_ACCOUNTS);
        while (rs.next()) {
            accounts.add(Account.create(rs));
        }
        return accounts;
    }

    public Optional<Account> getAccountById(String id) {
        Account account = new Account();
        SqlRowSet rs = null;
        rs = jdbcTemplate.queryForRowSet(SQL_SELECT_ACCOUNT_BY_ID, id);
        while (rs.next()) {
            account = Account.create(rs);
        }
    
        if (null == account) {
            return Optional.empty();
        }

        return Optional.of(account);
    }

    public void updateAccountById(String id, Account updatedAccount) {
        jdbcTemplate.update(SQL_UPDATE_ACCOUNT_BY_ID, updatedAccount.getEmail(), updatedAccount.getName(), id);
    }

    public void deleteAccountById(String id) {
        jdbcTemplate.update(SQL_DELETE_ACCOUNT_BY_ID, id);
    }

    public void saveStoresToMySql(List<Store> stores) {
        
        for (Store store : stores) {
            jdbcTemplate.update(SQL_INSERT_STORES, store.getStoreID(), store.getStoreName(), store.getIsActive(), store.getImagesBanner(), store.getImagesLogo(), store.getImagesIcon());
        }
    }
    
}
