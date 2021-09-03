package com.tinkoff.edu.app.service;


import com.tinkoff.edu.app.enums.LoanResponseType;
import com.tinkoff.edu.app.model.UuidLoanRequest;
import com.tinkoff.edu.app.model.UuidLoanResponse;
import com.tinkoff.edu.app.repository.LoanCalcRepository;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.UUID;

/**
 * Describe data counting
 */
public class CalculationsWithAllParamsService implements LoanCalcService {

    private LoanCalcRepository repository;

    public CalculationsWithAllParamsService(LoanCalcRepository repository) {
        this.repository = repository;
    }

    public UuidLoanResponse createLoanRequest(UuidLoanRequest request) {
        UUID requestId = repository.save();
        UuidLoanResponse loanResponse = new UuidLoanResponse(requestId);
        loanResponse.setIsAccepted(checkIfLoanAccepted(request));
        repository.saveResponse(loanResponse);
        return loanResponse;
    }

    public LoanResponseType getRequestById(UUID uuid) throws NoSuchElementException {
        ArrayList<UuidLoanResponse> responsesArray = repository.getResponses();
        for (UuidLoanResponse object : responsesArray) {
            if (object.getRequestId().equals(uuid)) {
                return object.getIsAccepted();
            }
        }
        throw new NoSuchElementException("Элемент с полученным id не найден");
    }

    public void setStatusRequestById(UUID uuid, LoanResponseType status) {
        repository.setStatusById(uuid, status);
    }

    public LoanResponseType checkIfLoanAccepted(UuidLoanRequest request) {
        if (request == null) {
            throw new IllegalArgumentException();
        } else if (request.getMonths() > 12) {
            return LoanResponseType.DECLINED;
        }

        switch (request.getLoanType()) {
            case OOO:
                return checkIfLoanAcceptedForOOO(request);
            case PERSON:
                return LoanResponseType.APPROVED;
            default:
                return LoanResponseType.DECLINED;
        }

    }

    public LoanResponseType checkIfLoanAcceptedForOOO(UuidLoanRequest request) {
        if (request.getAmount() <= 10000.0) {
            return LoanResponseType.DECLINED;
        } else if (request.getAmount() > 10000.0 && request.getMonths() < 12) {
            return LoanResponseType.APPROVED;
        } else {
            return LoanResponseType.DECLINED;
        }
    }

}
