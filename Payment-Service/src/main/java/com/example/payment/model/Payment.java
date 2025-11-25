package com.example.payment.model;

import java.math.BigDecimal;
import java.time.Instant;

public class Payment {
    private Long id;
    private String cardNumber;
    private BigDecimal amount;
    private String currency;
    private PaymentStatus status;
    private Instant createdAt;

    public Payment() {}

    public Payment(Long id, String cardNumber, BigDecimal amount,
                   String currency, PaymentStatus status, Instant createdAt) {
        this.id = id;
        this.cardNumber = cardNumber;
        this.amount = amount;
        this.currency = currency;
        this.status = status;
        this.createdAt = createdAt;
    }

    // getters/setters...
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCardNumber() { return cardNumber; }
    public void setCardNumber(String cardNumber) { this.cardNumber = cardNumber; }

    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }

    public String getCurrency() { return currency; }
    public void setCurrency(String currency) { this.currency = currency; }

    public PaymentStatus getStatus() { return status; }
    public void setStatus(PaymentStatus status) { this.status = status; }

    public Instant getCreatedAt() { return createdAt; }
    public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }
}
