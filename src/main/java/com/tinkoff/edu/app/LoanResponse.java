package com.tinkoff.edu.app;

public class LoanResponse {

    private LoanResponseType isAccepted;
    private final int requestId;

    public LoanResponse(int requestId) {
        this.requestId = requestId;
        this.isAccepted = LoanResponseType.DECLINED;
    }

    public LoanResponseType getIsAccepted() {
        return isAccepted;
    }

    public int getRequestId() {
        return requestId;
    }

    public void setIsAccepted(LoanResponseType isAccepted) {
        this.isAccepted = isAccepted;
    }

    public void printLoanResponseInfo() {
        System.out.println("Your request number is: " + this.getRequestId() + "; "
                + "Your request status is: " + this.getIsAccepted());
    }
}
