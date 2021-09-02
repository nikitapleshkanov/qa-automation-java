package com.tinkoff.edu.app.service;


import com.tinkoff.edu.app.enums.LoanRequestType;
import com.tinkoff.edu.app.enums.LoanResponseType;
import com.tinkoff.edu.app.model.AbstractLoanRequest;
import com.tinkoff.edu.app.model.AbstractLoanResponse;
import com.tinkoff.edu.app.repository.LoanCalcRepository;

import java.util.ArrayList;

/**
 * Describe data counting
 */
public class CalculationsWithAllParamsService implements LoanCalcService {

    private LoanCalcRepository repository;
    private ArrayList<AbstractLoanResponse> responsesArray = new ArrayList<>();

    public CalculationsWithAllParamsService(LoanCalcRepository repository) {
        this.repository = repository;
    }

    public AbstractLoanResponse createLoanRequest(AbstractLoanRequest request) {
        AbstractLoanResponse response = repository.save(request);
//        LoanResponse loanResponse = new LoanResponse(requestId);
        response.setIsAccepted(checkIfLoanAccepted(request));
        responsesArray.add(response);
        return response;
    }

//    public AbstractLoanResponse getRequestById(AbstractLoanResponse response) {
//
//        for (AbstractLoanResponse object : responsesArray){
//            if object.
//        }
//        return response;
//    }

    public LoanResponseType checkIfLoanAccepted(AbstractLoanRequest request) {
        if (request == null) {
            throw new IllegalArgumentException();
        } else if (request.getMonths() > 12 || request.getLoanType().equals(LoanRequestType.IP)) {
            return LoanResponseType.DECLINED;
        } else if (request.getLoanType().equals(LoanRequestType.PERSON)) {
            return LoanResponseType.APPROVED;
        } else if (request.getLoanType().equals(LoanRequestType.OOO)) {
            return checkIfLoanAcceptedForOOO(request);
        }
        return LoanResponseType.DECLINED;
    }

    public LoanResponseType checkIfLoanAcceptedForOOO(AbstractLoanRequest request) {
        if (request.getAmount() <= 10000.0) {
            return LoanResponseType.DECLINED;
        } else if (request.getAmount() > 10000.0 && request.getMonths() < 12) {
            return LoanResponseType.APPROVED;
        } else if (request.getAmount() > 10000.0 && request.getMonths() == 12) {
            return LoanResponseType.DECLINED;
        }
        return LoanResponseType.DECLINED;
    }

}
