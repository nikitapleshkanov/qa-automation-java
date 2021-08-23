package com.tinkoff.edu.app;


/**
 * Describe data counting
 */

public class CalculationsWithAllParamsService implements LoanCalcService {

    private LoanCalcRepository repository;

    public CalculationsWithAllParamsService(LoanCalcRepository repository) {
        this.repository = repository;
    }

    public LoanResponse createLoanRequest(LoanRequest request) {
        int requestId = repository.save();
        LoanResponse loanResponse = new LoanResponse(requestId);
        loanResponse.setIsAccepted(checkIfLoanAccepted(request));
        return loanResponse;
    }

    public boolean checkIfLoanAccepted(LoanRequest request) {
        return request.getAmount() > 0 && request.getMonths() < 24 && request.getLoanType().equals(LoanType.IP);
    }

}
