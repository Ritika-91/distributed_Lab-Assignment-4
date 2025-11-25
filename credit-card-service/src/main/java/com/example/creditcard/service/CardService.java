package com.example.creditcard.service;

import com.example.creditcard.model.CreditCard;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class CardService {

    private final ConcurrentHashMap<String, CreditCard> cards = new ConcurrentHashMap<>();

    public CreditCard registerCard(String cardNumber, String holderName, BigDecimal limit) {
        CreditCard card = new CreditCard(cardNumber, holderName, limit, BigDecimal.ZERO);
        cards.put(cardNumber, card);
        return card;
    }

    public CreditCard getCard(String cardNumber) {
        return cards.get(cardNumber);
    }

    public boolean canAuthorize(String cardNumber, BigDecimal amount) {
        CreditCard card = cards.get(cardNumber);
        if (card == null) return false;

        BigDecimal newBalance = card.getCurrentBalance().add(amount);
        return newBalance.compareTo(card.getCreditLimit()) <= 0;
    }

    public void applyCharge(String cardNumber, BigDecimal amount) {
        CreditCard card = cards.get(cardNumber);
        if (card != null) {
            card.setCurrentBalance(card.getCurrentBalance().add(amount));
        }
    }
}
