package com.banking.accountservice.controller;

import com.banking.accountservice.dto.AccountResponse;
import com.banking.accountservice.dto.CreateAccountRequest;
import com.banking.accountservice.service.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

// here we create all the END points
//SAGA step 1 is here -> deduct balance
//SAGA step 4 -> credit balance (receiver /sender)
@RestController
@RequestMapping("api/v1/accounts")
@Slf4j
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;


    @PostMapping
    public ResponseEntity<AccountResponse> createAccount(
            @Valid @RequestBody CreateAccountRequest request) {
       return ResponseEntity.status(HttpStatus.CREATED)
               .body(accountService.createAccount(request)) ;

    }

    @GetMapping("/{accountNumber}")
    public ResponseEntity<AccountResponse> getAccount(@PathVariable String accountNumber) {

        return ResponseEntity.ok(accountService.getAccount(accountNumber));
    }

    @GetMapping("/{accountNumber}/balance ")
    public ResponseEntity<AccountResponse> getBalance(@PathVariable String accountNumber) {

        return ResponseEntity.ok(accountService.getBalance(accountNumber));
    }


    @PutMapping("/{accountNumber}/block")
    public ResponseEntity<String> blockedAccount(@PathVariable String accountNumber) {
        accountService.blockAccount(accountNumber);
        return ResponseEntity.ok("Account blocked Successfully");
    }

    /*SAGA STEPP 1 - DEBUT BALANCE
    * Called by Transaction Service when transfer is initiated
    */

    @PutMapping("/{accountNumber}/deduct")
    public ResponseEntity<String> deductBalance(
            @PathVariable String accountNumber,
            @RequestParam BigDecimal amount) {

        accountService.deductBalance(accountNumber, amount);
        return ResponseEntity.ok("Balance deducted Successfully");
    }


    /*SAGA STEP 4 - Compensating transaction endpoint
    * CALLED BY TRANSACTION SERVICE IN TWO SCENARIOS:
    * 1.Fraud detected -> refund sender (undo step 1)
    * 2. Transaction completed -> Credit receiver
    */

    @PutMapping("/{accountNumber}/credit")
    public ResponseEntity<String> creditBalance(
            @PathVariable String accountNumber,
            @RequestParam BigDecimal amount) {
        accountService.creditBalance(accountNumber, amount);
        return ResponseEntity.ok("BALANCE CREDIT SUCCESSFULLY");
    }






}
