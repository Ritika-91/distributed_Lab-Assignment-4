package com.example.payment.controller;

import com.example.payment.dto.CreatePaymentRequest;
import com.example.payment.model.Payment;
import com.example.payment.service.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
    public ResponseEntity<Payment> createPayment(@RequestBody CreatePaymentRequest req) {
        Payment payment = paymentService.createPayment(
                req.getCardNumber(), req.getAmount(), req.getCurrency()
        );
        return ResponseEntity.ok(payment);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Payment> getPayment(@PathVariable Long id) {
        Payment p = paymentService.getPayment(id);
        if (p == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(p);
    }

    @GetMapping
    public ResponseEntity<List<Payment>> listPayments() {
        return ResponseEntity.ok(paymentService.listPayments());
    }
}
