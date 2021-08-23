package com.tinkoff.edu.app;

/**
 * Describe working with api
 */

public class LoanCalcController {

    private LoanCalcService service;

    public LoanCalcController(LoanCalcService service) {
        this.service = service;

    }

    public LoanResponse createRequest(LoanRequest request) {
        LoanResponse loanResponse = service.createLoanRequest(request);
        LoanCalcLogger.log(loanResponse.getRequestId());
        return loanResponse;
    }

}
