package com.tinkoff.edu.app.repository;

import com.tinkoff.edu.app.enums.LoanResponseType;
import com.tinkoff.edu.app.model.UuidLoanResponse;

import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.UUID;

/**
 * Describe data saving
 */
public class VariableLoanCalcRepository implements LoanCalcRepository {

    private HashMap<UUID, UuidLoanResponse> responsesArray;

    public VariableLoanCalcRepository() {
        this.responsesArray = new HashMap<>();
    }

    public UUID save() {
        return UUID.randomUUID();
    }

    public void saveResponse(UuidLoanResponse response) {
        responsesArray.put(response.getRequestId(), response);
    }

    public HashMap<UUID, UuidLoanResponse> getResponses() {
        return responsesArray;
    }

    public void setStatusById(UUID uuid, LoanResponseType status) throws NoSuchElementException {
        try {
            responsesArray.get(uuid).setIsAccepted(status);
        } catch (NullPointerException e) {
            throw new NoSuchElementException("Элемент массива с полученным id не найден");
        }
    }

}
