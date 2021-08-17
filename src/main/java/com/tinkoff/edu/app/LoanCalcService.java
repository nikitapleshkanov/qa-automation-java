package com.tinkoff.edu.app;


/**
 * Describe data counting
 */

public class LoanCalcService {

    public static int createLoanRequest() {
        return LoanCalcRepository.save();
    }

}
