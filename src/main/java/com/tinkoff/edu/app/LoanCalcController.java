package com.tinkoff.edu.app;

/**
 * Describe working with api
 */

public class LoanCalcController {

    public LoanResponse createRequest(LoanRequest request) {
        LoanCalcService service = new LoanCalcService();
        LoanResponse loanResponse = service.createLoanRequest(request);
        LoanCalcLogger.log(loanResponse.getRequestId());
        return loanResponse;
    }

}
