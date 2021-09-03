package com.tinkoff.edu.app.repository;

import com.tinkoff.edu.app.enums.LoanResponseType;
import com.tinkoff.edu.app.model.UuidLoanResponse;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.UUID;

/**
 * Describe data saving
 */
public class VariableLoanCalcRepository implements LoanCalcRepository {

    private ArrayList<UuidLoanResponse> responsesArray;

    public VariableLoanCalcRepository() {
        this.responsesArray = new ArrayList<>();
    }

    public UUID save() {
        return UUID.randomUUID();
    }

    public void saveResponse(UuidLoanResponse response) {
        responsesArray.add(response);
    }

    public ArrayList<UuidLoanResponse> getResponses() {
        return responsesArray;
    }

    public void setStatusById(UUID uuid, LoanResponseType status) throws NoSuchElementException {
        for (UuidLoanResponse object : responsesArray) {
            if (object.getRequestId().equals(uuid)) {
                object.setIsAccepted(status);
                return;
            }
        }
        throw new NoSuchElementException("Элемент с полученным id не найден");
    }

}
