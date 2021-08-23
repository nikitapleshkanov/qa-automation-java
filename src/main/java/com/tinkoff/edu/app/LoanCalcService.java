package com.tinkoff.edu.app;

public interface LoanCalcService {

    LoanResponse createLoanRequest(LoanRequest request);

    boolean checkIfLoanAccepted(LoanRequest request);

}
