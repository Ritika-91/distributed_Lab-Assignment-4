package com.example.payment.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class CreditCardClient {

    private final RestTemplate restTemplate;
    private final String creditCardBaseUrl;

    public CreditCardClient(RestTemplate restTemplate,
                            @Value("${creditcard.service.url}") String creditCardBaseUrl) {
        this.restTemplate = restTemplate;
        this.creditCardBaseUrl = creditCardBaseUrl;
    }

    public AuthorizationResponse authorize(String cardNumber,
                                           java.math.BigDecimal amount,
                                           String currency) {
        AuthorizationRequest request = new AuthorizationRequest(cardNumber, amount, currency);

        try {
            return restTemplate.postForObject(
                    creditCardBaseUrl + "/cards/authorize",
                    request,
                    AuthorizationResponse.class
            );
        } catch (Exception e) {
            // Payment service continues but marks payment as declined due to error
            AuthorizationResponse fallback = new AuthorizationResponse();
            fallback.setApproved(false);
            fallback.setReason("credit-card-service unreachable: " + e.getMessage());
            return fallback;
        }
    }
}
