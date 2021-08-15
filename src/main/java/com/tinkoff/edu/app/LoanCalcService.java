package com.tinkoff.edu.app;

public class LoanCalcService {

    public static int createLoanRequest() {
        return LoanCalcRepository.save();
    }

}
