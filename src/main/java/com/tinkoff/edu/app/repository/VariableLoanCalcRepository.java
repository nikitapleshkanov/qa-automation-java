package com.tinkoff.edu.app.repository;

import com.tinkoff.edu.app.enums.LoanResponseType;
import com.tinkoff.edu.app.model.UuidLoanResponse;

import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.UUID;

import static com.tinkoff.edu.app.flow.LoanRequestFlow.getLoanResponseByUUID;

/**
 * Describe data saving
 */
public class VariableLoanCalcRepository implements LoanCalcRepository {

    private HashMap<UUID, UuidLoanResponse> responses;

    public VariableLoanCalcRepository() {
        this.responses = new HashMap<>();
    }

    public UUID save() {
        return UUID.randomUUID();
    }

    public void saveResponse(UuidLoanResponse response) {
        responses.put(response.getRequestId(), response);
    }

    public HashMap<UUID, UuidLoanResponse> getResponses() {
        return responses;
    }

    public void setStatusById(UUID uuid, LoanResponseType status) throws NoSuchElementException {
        getLoanResponseByUUID(responses, uuid).setIsAccepted(status);
    }

}
