package com.tinkoff.edu.app.model;

import com.tinkoff.edu.app.enums.LoanRequestType;

public abstract class AbstractLoanRequest {

    private final int months;
    private final double amount;
    private final LoanRequestType loanType;
    private final String name;

    public AbstractLoanRequest(int months, double amount, LoanRequestType loanType, String name) {
        if (months > 0) {
            this.months = months;
        } else throw new IllegalArgumentException("Указано значение month <= 0");
        if (amount > 0) {
            this.amount = amount;
        } else throw new IllegalArgumentException("Указано значение amount <= 0");
        this.loanType = loanType;
        this.name = name;
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

    public String getName() {
        return name;
    }


}
