package com.example.payment.client;

import java.math.BigDecimal;

public class AuthorizationRequest {
    private String cardNumber;
    private BigDecimal amount;
    private String currency;

    public AuthorizationRequest() {}

    public AuthorizationRequest(String cardNumber, BigDecimal amount, String currency) {
        this.cardNumber = cardNumber;
        this.amount = amount;
        this.currency = currency;
    }

    public String getCardNumber() { return cardNumber; }
    public void setCardNumber(String cardNumber) { this.cardNumber = cardNumber; }

    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }

    public String getCurrency() { return currency; }
    public void setCurrency(String currency) { this.currency = currency; }
}
