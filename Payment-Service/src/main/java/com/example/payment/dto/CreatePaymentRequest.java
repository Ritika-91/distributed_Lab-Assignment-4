package com.example.payment.dto;

import java.math.BigDecimal;

public class CreatePaymentRequest {
    private String cardNumber;
    private BigDecimal amount;
    private String currency;

    public String getCardNumber() { return cardNumber; }
    public void setCardNumber(String cardNumber) { this.cardNumber = cardNumber; }

    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }

    public String getCurrency() { return currency; }
    public void setCurrency(String currency) { this.currency = currency; }
}
