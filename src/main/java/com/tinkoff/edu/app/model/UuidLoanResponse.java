package com.tinkoff.edu.app.model;

import com.tinkoff.edu.app.enums.LoanRequestType;
import com.tinkoff.edu.app.enums.LoanResponseType;

import java.util.UUID;

public class UuidLoanResponse {

    private LoanResponseType isAccepted;
    private final UUID requestId;
    private final LoanRequestType loanType;
    private final Double amount;

    public UuidLoanResponse(UUID requestId, LoanRequestType loanType, Double amount) {
        this.requestId = requestId;
        this.loanType = loanType;
        this.amount = amount;
        this.isAccepted = LoanResponseType.DECLINED;
    }

    public LoanResponseType getIsAccepted() {
        return isAccepted;
    }

    public UUID getRequestId() {
        return requestId;
    }

    public LoanRequestType getLoanType() {
        return loanType;
    }

    public Double getAmount() {
        return amount;
    }

    public void setIsAccepted(LoanResponseType isAccepted) {
        this.isAccepted = isAccepted;
    }

    public void printLoanResponseInfo() {
        System.out.println("Your request number is: " + this.getRequestId() + "; "
                + "Your request status is: " + this.getIsAccepted());
    }

    @Override
    public String toString() {
        return "requestId: " + requestId + " loanType: " + loanType.toString() + " amount: " + amount + " isAccepted: " + isAccepted.toString() + "\n";
    }

}
