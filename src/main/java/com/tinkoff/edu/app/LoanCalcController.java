package com.tinkoff.edu.app;

/**
 * Describe working with api
 */

public class LoanCalcController {

    public static int createRequest() {
        int requestId = LoanCalcService.createLoanRequest();
        LoanCalcLogger.log(requestId);
        return requestId;
    }

}
