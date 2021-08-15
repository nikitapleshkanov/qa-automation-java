package com.tinkoff.edu.app;

public class LoanCalcController {

    public static int createRequest() {
        LoanCalcLogger.log();
        return LoanCalcService.createLoanRequest();
    }

}
