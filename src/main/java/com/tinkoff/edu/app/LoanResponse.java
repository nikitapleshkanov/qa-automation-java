package com.tinkoff.edu.app;


public class LoanResponse {


    private boolean isAccepted;
    private final int requestId;

    public LoanResponse(int requestId) {
        this.requestId = requestId;
        this.isAccepted = false;
    }

    public boolean getIsAccepted() {
        return isAccepted;
    }

    public int getRequestId() {
        return requestId;
    }

    public void setIsAccepted(boolean isAccepted) {
        this.isAccepted = isAccepted;
    }
}
