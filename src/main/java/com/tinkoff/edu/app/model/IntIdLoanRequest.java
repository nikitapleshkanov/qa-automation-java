package com.tinkoff.edu.app.model;

import com.tinkoff.edu.app.enums.LoanRequestType;

public class IntIdLoanRequest extends AbstractLoanRequest {

    public IntIdLoanRequest(int months, double amount, LoanRequestType loanType, String name) {
        super(months, amount, loanType, name);
    }

}
