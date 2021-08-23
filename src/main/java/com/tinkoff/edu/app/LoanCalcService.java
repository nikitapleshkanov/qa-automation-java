package com.tinkoff.edu.app;

public interface LoanCalcService {

    LoanResponse createLoanRequest(LoanRequest request);

    LoanResponseType checkIfLoanAccepted(LoanRequest request);

}
