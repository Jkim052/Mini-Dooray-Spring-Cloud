package com.nhnacademy.taskapi.repository;

import com.nhnacademy.taskapi.entity.Account;
import com.nhnacademy.taskapi.entity.Milestone;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class AccountRepositoryTest {

    @Autowired
    private AccountRepository accountRepository;

    @Test
    @Sql("account-test.sql")
    void findByAccountId() {
        Account account = accountRepository.findById(1L).orElse(null);

        assertNotNull(account);
        assertEquals(1L, account.getAccountId());
    }

    @Test
    @Sql("account-test.sql")
    void findByAccountId_notFound() {
        Account account = accountRepository.findById(2124L).orElse(null);
        assertNull(account);
    }

    @Test
    void saveAccount() {
        Account account = new Account(30L);
        Account savedAccount = accountRepository.save(account);
        assertNotNull(savedAccount);
    }

}