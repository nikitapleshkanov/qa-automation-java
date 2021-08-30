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
        if (request == null) {
            throw new IllegalArgumentException();
        } else if (request.getLoanType().equals(LoanRequestType.PERSON) && request.getAmount() <= 10000.0 && request.getMonths() <= 12) {
            return LoanResponseType.APPROVED;
        } else if (request.getLoanType().equals(LoanRequestType.PERSON) && request.getAmount() <= 10000.0 && request.getMonths() > 12) {
            return LoanResponseType.DECLINED;
        } else if (request.getLoanType().equals(LoanRequestType.PERSON) && request.getAmount() > 10000.0 && request.getMonths() > 12) {
            return LoanResponseType.DECLINED;
        } else if (request.getLoanType().equals(LoanRequestType.PERSON) && request.getAmount() > 10000.0 && request.getMonths() <= 12) {
            return LoanResponseType.APPROVED;
        } else if (request.getLoanType().equals(LoanRequestType.OOO) && request.getAmount() <= 10000.0) {
            return LoanResponseType.DECLINED;
        } else if (request.getLoanType().equals(LoanRequestType.OOO) && request.getAmount() > 10000.0 && request.getMonths() < 12) {
            return LoanResponseType.APPROVED;
        } else if (request.getLoanType().equals(LoanRequestType.OOO) && request.getAmount() > 10000.0 && request.getMonths() >= 12) {
            return LoanResponseType.DECLINED;
        } else if (request.getLoanType().equals(LoanRequestType.IP)) {
            return LoanResponseType.DECLINED;
        }
        return LoanResponseType.DECLINED;
    }

}
