package com.example.payment.client;

public class AuthorizationResponse {
    private boolean approved;
    private String reason;

    public boolean isApproved() { return approved; }
    public void setApproved(boolean approved) { this.approved = approved; }

    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }
}
