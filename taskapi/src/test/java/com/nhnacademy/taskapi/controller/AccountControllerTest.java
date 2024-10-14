package com.nhnacademy.taskapi.controller;

import com.nhnacademy.taskapi.dto.response.DefaultDto;
import com.nhnacademy.taskapi.service.AccountService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AccountController.class)
class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccountService accountService;

    @Test
    void addAccount_shouldReturn201_whenAccountIsCreated() throws Exception {
        // given
        Long accountId = 1L;
        doNothing().when(accountService).addAccount(accountId); // 모의 서비스 호출 시 아무것도 하지 않음

        // when, then
        mockMvc.perform(post("/" + accountId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated()) // HTTP 201 Created 확인
                .andExpect(jsonPath("$.code").value(201)) // DefaultDto의 code 필드가 201인지 확인
                .andExpect(jsonPath("$.data").isEmpty());  // data 필드가 null인지 확인
    }
}
