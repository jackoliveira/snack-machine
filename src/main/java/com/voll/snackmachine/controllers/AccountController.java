package com.voll.snackmachine.controllers;

import com.voll.snackmachine.models.*;
import com.voll.snackmachine.repositories.AccountRepository;
import com.voll.snackmachine.repositories.SellHistoryRepository;
import com.voll.snackmachine.repositories.UserRepository;
import com.voll.snackmachine.utils.BalanceUtils;
import com.voll.snackmachine.validators.AccountValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.Optional;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    SellHistoryRepository sellHistoryRepository;

    @Transactional(readOnly = true)
    @GetMapping("{userId}/balance")
    public ResponseEntity get(@PathVariable(value = "userId") String userId)  {
        Optional<Account> account = accountRepository.findByUserId(userId);
        if (account.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(account);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Conta nao encontrada, id informado " + userId);
        }
    }

    @GetMapping("{userId}/balance/recharge")
    public ResponseEntity getBalance(@PathVariable(value = "userId") String userId)  {
        var rechargeAccount = accountRepository.findByUserId(userId);

        if (rechargeAccount.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        Float companyBudget = userRepository.findById(userId).map(User::getCompany).get().getBudgetPerUser();
        rechargeAccount.map(account -> {
            if(isRechargeNeeded(account)) {
                account.setBalance(companyBudget);
                return accountRepository.save(account);
            }

            return account;
        });

        return ResponseEntity.status(HttpStatus.OK).body(rechargeAccount);
    }

    @PostMapping("{userId}/purchase")
    public ResponseEntity purchase(@PathVariable(value = "userId") String userId,
                                   @Valid @RequestBody PurchaseData purchaseData) {
        var updateAccount = accountRepository.findByUserId(userId);

        if (updateAccount.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        updateAccount.map(account -> {
            AccountValidator.validate(account, purchaseData);

            account.setBalance(BalanceUtils.balancePurchaseCalculation(account.getBalance(), purchaseData.value));
            return accountRepository.save(account);
        });

        return ResponseEntity.status(HttpStatus.OK).body(updateAccount);
    }

    private boolean isRechargeNeeded(Account account) {
        return (!sellHistoryRepository.existsByUserAndDateWhen(account.getUser(), LocalDate.now()));
    }
}
