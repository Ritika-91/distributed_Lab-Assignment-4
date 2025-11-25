package com.example.creditcard.dto;

import java.math.BigDecimal;

public class RegisterCardRequest {
    private String cardNumber;
    private String holderName;
    private BigDecimal creditLimit;

    public String getCardNumber() { return cardNumber; }
    public void setCardNumber(String cardNumber) { this.cardNumber = cardNumber; }

    public String getHolderName() { return holderName; }
    public void setHolderName(String holderName) { this.holderName = holderName; }

    public BigDecimal getCreditLimit() { return creditLimit; }
    public void setCreditLimit(BigDecimal creditLimit) { this.creditLimit = creditLimit; }
}
