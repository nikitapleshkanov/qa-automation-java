package com.tinkoff.edu.app.service;


import com.tinkoff.edu.app.enums.LoanRequestType;
import com.tinkoff.edu.app.enums.LoanResponseType;
import com.tinkoff.edu.app.model.UuidLoanRequest;
import com.tinkoff.edu.app.model.UuidLoanResponse;
import com.tinkoff.edu.app.repository.LoanCalcRepository;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.tinkoff.edu.app.flow.LoanRequestFlow.getLoanResponseByUUID;

/**
 * Describe data counting
 */
public class CalculationsWithAllParamsService implements LoanCalcService {

    private LoanCalcRepository repository;

    public CalculationsWithAllParamsService(LoanCalcRepository repository) {
        this.repository = repository;
    }

    public UuidLoanResponse createLoanRequest(UuidLoanRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Значение request передано = null");
        }
        UUID requestId = repository.save();
        UuidLoanResponse loanResponse = new UuidLoanResponse(requestId, request.getLoanType(), request.getAmount());
        loanResponse.setIsAccepted(checkIfLoanAccepted(request));
        repository.saveResponse(loanResponse);
        return loanResponse;
    }

    public LoanResponseType getRequestById(UUID uuid) throws NoSuchElementException {
        HashMap<UUID, UuidLoanResponse> responses = repository.getResponses();
        return getLoanResponseByUUID(responses, uuid).getIsAccepted();
    }

    public List<UuidLoanResponse> getAllRequestsWithType(LoanRequestType type) throws NoSuchElementException {
        Collection<UuidLoanResponse> responses = repository.getResponses().values();
        List<UuidLoanResponse> response = responses.stream()
                .filter(x -> x.getLoanType().equals(type))
                .collect(Collectors.toList());
        return response;
    }

    public Double getAllRequestsAmountWithType(LoanRequestType type) throws NoSuchElementException {
        Collection<UuidLoanResponse> responses = repository.getResponses().values();
        Double sum = responses.stream()
                .filter(x -> x.getLoanType().equals(type))
                .map(UuidLoanResponse::getAmount)
                .reduce(0.0, Double::sum);
        return sum;
    }

    public void setStatusRequestById(UUID uuid, LoanResponseType status) {
        repository.setStatusById(uuid, status);
    }

    public LoanResponseType checkIfLoanAccepted(UuidLoanRequest request) throws IllegalArgumentException {
        if (request.getMonths() > 12) {
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
