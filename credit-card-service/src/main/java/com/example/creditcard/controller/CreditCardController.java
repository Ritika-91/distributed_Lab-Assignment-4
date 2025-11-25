package com.example.creditcard.controller;

import com.example.creditcard.dto.AuthorizationRequest;
import com.example.creditcard.dto.AuthorizationResponse;
import com.example.creditcard.dto.RegisterCardRequest;
import com.example.creditcard.model.CreditCard;
import com.example.creditcard.service.CardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cards")
public class CreditCardController {

    private final CardService cardService;

    public CreditCardController(CardService cardService) {
        this.cardService = cardService;
    }

    @PostMapping
    public ResponseEntity<CreditCard> registerCard(@RequestBody RegisterCardRequest req) {
        CreditCard card = cardService.registerCard(
                req.getCardNumber(),
                req.getHolderName(),
                req.getCreditLimit()
        );
        return ResponseEntity.ok(card);
    }

    @GetMapping("/{cardNumber}")
    public ResponseEntity<CreditCard> getCard(@PathVariable String cardNumber) {
        CreditCard card = cardService.getCard(cardNumber);
        if (card == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(card);
    }

    @PostMapping("/authorize")
    public ResponseEntity<AuthorizationResponse> authorize(@RequestBody AuthorizationRequest req) {
        CreditCard card = cardService.getCard(req.getCardNumber());
        if (card == null) {
            return ResponseEntity.ok(
                    new AuthorizationResponse(false, "Card not found")
            );
        }

        if (!"EUR".equalsIgnoreCase(req.getCurrency())) {
            // Example “business rule”
            return ResponseEntity.ok(
                    new AuthorizationResponse(false, "Unsupported currency")
            );
        }

        if (cardService.canAuthorize(req.getCardNumber(), req.getAmount())) {
            cardService.applyCharge(req.getCardNumber(), req.getAmount());
            return ResponseEntity.ok(
                    new AuthorizationResponse(true, "Approved")
            );
        } else {
            return ResponseEntity.ok(
                    new AuthorizationResponse(false, "Credit limit exceeded")
            );
        }
    }
}
