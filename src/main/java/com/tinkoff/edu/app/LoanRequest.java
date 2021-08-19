package com.tinkoff.edu.app;

public class LoanRequest {

    private final int months;
    private final int amount;

    public LoanRequest(int months, int amount) {
        this.months = months;
        this.amount = amount;
    }

    public int getMonths() {
        return months;
    }

    public int getAmount() {
        return amount;
    }

}
