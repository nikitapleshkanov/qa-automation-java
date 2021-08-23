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

    public LoanResponseType checkIfLoanAccepted(LoanRequest request) {
        if (request.getAmount() > 0 && request.getMonths() < 24 && request.getLoanType().equals(LoanType.IP)) {
            return LoanResponseType.APPROVED;
        } else return LoanResponseType.DENIED;
    }

}
