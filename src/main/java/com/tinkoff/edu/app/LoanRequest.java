package com.tinkoff.edu.app;

public class LoanRequest {

    private final int months;
    private final int amount;
    private final LoanType loanType;

    public LoanRequest(int months, int amount, LoanType loanType) {
        this.months = months;
        this.amount = amount;
        this.loanType = loanType;
    }

    public int getMonths() {
        return months;
    }

    public int getAmount() {
        return amount;
    }

    public LoanType getLoanType() {
        return loanType;
    }

}
