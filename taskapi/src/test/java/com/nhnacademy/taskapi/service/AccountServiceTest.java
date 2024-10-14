package com.nhnacademy.taskapi.service;

import com.nhnacademy.taskapi.entity.Account;
import com.nhnacademy.taskapi.exception.ResourceAlreadyExistException;
import com.nhnacademy.taskapi.repository.AccountRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AccountService accountService;

    @Test
    void addAccount_shouldThrowException_whenAccountAlreadyExists() {
        // given
        Long existingAccountId = 1L;
        when(accountRepository.existsById(existingAccountId)).thenReturn(true);

        // when, then
        ResourceAlreadyExistException exception = assertThrows(
                ResourceAlreadyExistException.class,
                () -> accountService.addAccount(existingAccountId)
        );

        // Verify exception message
        assertEquals("AccountId: 1 already exist", exception.getMessage());

        // verify that save was never called
        verify(accountRepository, never()).save(any(Account.class));
    }

    @Test
    void addAccount_shouldSaveNewAccount_whenAccountDoesNotExist() {
        // given
        Long newAccountId = 2L;
        when(accountRepository.existsById(newAccountId)).thenReturn(false);

        // when
        accountService.addAccount(newAccountId);

        // then
        verify(accountRepository).existsById(newAccountId); // Check if existsById was called
        verify(accountRepository).save(any(Account.class)); // Check if save was called
    }
}
