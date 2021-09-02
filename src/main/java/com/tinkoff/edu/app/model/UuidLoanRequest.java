package com.tinkoff.edu.app.model;

import com.tinkoff.edu.app.enums.LoanRequestType;

public class UuidLoanRequest extends AbstractLoanRequest {

    public UuidLoanRequest(int months, double amount, LoanRequestType loanType, String name) {
        super(months, amount, loanType, name);
    }

}
