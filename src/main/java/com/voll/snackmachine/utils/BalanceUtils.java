package com.voll.snackmachine.utils;

public class BalanceUtils {

    public static Float balancePurchaseCalculation(Float balanceValue, Float purchaseValue) {
        if (balanceValue.equals(purchaseValue)) return 0F;

        return (balanceValue - purchaseValue);
    }
}
