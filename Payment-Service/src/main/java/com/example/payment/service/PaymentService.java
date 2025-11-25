package com.example.payment.service;

import com.example.payment.client.AuthorizationResponse;
import com.example.payment.client.CreditCardClient;
import com.example.payment.model.Payment;
import com.example.payment.model.PaymentStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class PaymentService {

    private final ConcurrentHashMap<Long, Payment> payments = new ConcurrentHashMap<>();
    private final AtomicLong nextId = new AtomicLong(1);
    private final CreditCardClient creditCardClient;

    public PaymentService(CreditCardClient creditCardClient) {
        this.creditCardClient = creditCardClient;
    }

    public Payment createPayment(String cardNumber, BigDecimal amount, String currency) {
        Long id = nextId.getAndIncrement();
        Payment payment = new Payment(
                id, cardNumber, amount, currency,
                PaymentStatus.PENDING, Instant.now()
        );
        payments.put(id, payment);

        // Call CreditCard service synchronously
        AuthorizationResponse auth = creditCardClient.authorize(cardNumber, amount, currency);

        if (auth != null && auth.isApproved()) {
            payment.setStatus(PaymentStatus.AUTHORIZED);
        } else {
            payment.setStatus(PaymentStatus.DECLINED);
        }

        return payment;
    }

    public Payment getPayment(Long id) {
        return payments.get(id);
    }

    public List<Payment> listPayments() {
        return new ArrayList<>(payments.values());
    }
}
