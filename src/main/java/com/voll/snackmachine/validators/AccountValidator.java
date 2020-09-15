package com.voll.snackmachine.validators;

import com.voll.snackmachine.models.Account;
import com.voll.snackmachine.models.PurchaseData;
import com.voll.snackmachine.repositories.SellHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class AccountValidator {

    @Autowired
    private SellHistoryRepository sellHistoryRepository;

    public static void validate(Account account, PurchaseData purchaseData) {
        validatePurchaseData(purchaseData);
        validateBalance(account, purchaseData);
    }

    public static void validateBalance(Account account, PurchaseData purchaseData) {
        if (account.getBalance() < purchaseData.value) {
            throw new RuntimeException("Saldo insuficiente para realizar a operacao");
        }
    }

    public static void validatePurchaseData(PurchaseData purchaseData) {
        if (purchaseData.value == null || purchaseData.value < 0) {
            throw new RuntimeException("Valor do produto invalido ou nulo");
        }
    }

}
