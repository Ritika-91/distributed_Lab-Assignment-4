package com.example.creditcard.model;

import java.math.BigDecimal;

public class CreditCard {
    private String cardNumber;
    private String holderName;
    private BigDecimal creditLimit;
    private BigDecimal currentBalance;

    public CreditCard() {}

    public CreditCard(String cardNumber, String holderName,
                      BigDecimal creditLimit, BigDecimal currentBalance) {
        this.cardNumber = cardNumber;
        this.holderName = holderName;
        this.creditLimit = creditLimit;
        this.currentBalance = currentBalance;
    }

    // getters/setters...
    public String getCardNumber() { return cardNumber; }
    public void setCardNumber(String cardNumber) { this.cardNumber = cardNumber; }

    public String getHolderName() { return holderName; }
    public void setHolderName(String holderName) { this.holderName = holderName; }

    public BigDecimal getCreditLimit() { return creditLimit; }
    public void setCreditLimit(BigDecimal creditLimit) { this.creditLimit = creditLimit; }

    public BigDecimal getCurrentBalance() { return currentBalance; }
    public void setCurrentBalance(BigDecimal currentBalance) { this.currentBalance = currentBalance; }
}
