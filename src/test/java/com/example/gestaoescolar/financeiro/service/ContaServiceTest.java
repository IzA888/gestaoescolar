package com.example.gestaoescolar.financeiro.service;

import com.example.gestaoescolar.financeiro.dto.ContaDTO;
import com.example.gestaoescolar.financeiro.entity.Conta;
import com.example.gestaoescolar.financeiro.repository.ContaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AccountServiceTest {

    @Mock
    private ContaRepository accountRepository;

    @InjectMocks
    private ContaService accountService;

    private ContaDTO accountDTO;
    private Conta account;

    @BeforeEach
    void setUp() {
        accountDTO = new ContaDTO();
        accountDTO.setAccountHolder("Jane Smith");
        accountDTO.setAccountNumber("ACC001");
        accountDTO.setBalance(new BigDecimal("1000.00"));
        accountDTO.setCreditLimit(new BigDecimal("5000.00"));
        accountDTO.setAccountType("CHECKING");
        accountDTO.setStatus("ACTIVE");

        account = new Conta();
        account.setId(1L);
        account.setAccountHolder("Jane Smith");
        account.setAccountNumber("ACC001");
        account.setBalance(new BigDecimal("1000.00"));
        account.setCreditLimit(new BigDecimal("5000.00"));
        account.setAccountType(Conta.TipoConta.CHECKING);
        account.setStatus(Conta.StatusConta.ACTIVE);
    }

    @Test
    void testCreateAccount() {
        when(accountRepository.save(any(Conta.class))).thenReturn(account);

        ContaDTO result = accountService.create(accountDTO);

        assertNotNull(result);
        assertEquals("Jane Smith", result.getAccountHolder());
        assertEquals("ACC001", result.getAccountNumber());
        verify(accountRepository, times(1)).save(any(Conta.class));
    }

    @Test
    void testFindAccountById() {
        when(accountRepository.findById(1L)).thenReturn(Optional.of(account));

        Optional<ContaDTO> result = accountService.findById(1L);

        assertTrue(result.isPresent());
        assertEquals("Jane Smith", result.get().getAccountHolder());
        verify(accountRepository, times(1)).findById(1L);
    }

    @Test
    void testFindByAccountNumber() {
        when(accountRepository.findByAccountNumber("ACC001")).thenReturn(Optional.of(account));

        Optional<ContaDTO> result = accountService.findByAccountNumber("ACC001");

        assertTrue(result.isPresent());
        assertEquals("ACC001", result.get().getAccountNumber());
        verify(accountRepository, times(1)).findByAccountNumber("ACC001");
    }

    @Test
    void testDeleteAccount() {
        doNothing().when(accountRepository).deleteById(1L);

        accountService.delete(1L);

        verify(accountRepository, times(1)).deleteById(1L);
    }
}
