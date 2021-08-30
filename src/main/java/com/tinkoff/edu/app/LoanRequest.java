package com.tinkoff.edu.app;

public class LoanRequest {

    private final int months;
    private final double amount;
    private final LoanRequestType loanType;

    public LoanRequest(int months, double amount, LoanRequestType loanType) {
        if (months > 0) {
            this.months = months;
        } else throw new IllegalArgumentException();
        if (amount > 0) {
            this.amount = amount;
        } else throw new IllegalArgumentException();
        this.loanType = loanType;
    }

    public int getMonths() {
        return months;
    }

    public double getAmount() {
        return amount;
    }

    public LoanRequestType getLoanType() {
        return loanType;
    }

}
