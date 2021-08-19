package com.tinkoff.edu.app;


/**
 * Describe data counting
 */

public class LoanCalcService {

    public LoanResponse createLoanRequest(LoanRequest request) {
        LoanCalcRepository repository = new LoanCalcRepository();
        int requestId = repository.save();
        LoanResponse loanResponse = new LoanResponse(requestId);
        if (request.getAmount() > 0 && request.getMonths() < 24) {
            loanResponse.setIsAccepted(true);
        }
        return loanResponse;
    }

}
