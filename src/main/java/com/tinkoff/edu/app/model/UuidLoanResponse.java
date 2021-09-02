package com.tinkoff.edu.app.model;

import com.tinkoff.edu.app.enums.LoanResponseType;

import java.util.UUID;

public class UuidLoanResponse extends AbstractLoanResponse {

    private LoanResponseType isAccepted;
    private final UUID requestId;

    public UuidLoanResponse(UUID requestId) {
        this.requestId = requestId;
        this.isAccepted = LoanResponseType.DECLINED;
    }

    public LoanResponseType getIsAccepted() {
        return isAccepted;
    }

    public UUID getRequestId() {
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
