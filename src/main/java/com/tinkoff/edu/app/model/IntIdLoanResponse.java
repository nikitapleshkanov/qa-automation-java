package com.tinkoff.edu.app.model;

import com.tinkoff.edu.app.enums.LoanResponseType;

public class IntIdLoanResponse extends AbstractLoanResponse {

    private LoanResponseType isAccepted;
    private final int requestId;

    public IntIdLoanResponse(int requestId) {
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
