package com.tinkoff.edu.app.repository;

import com.tinkoff.edu.app.model.AbstractLoanRequest;
import com.tinkoff.edu.app.model.IntIdLoanResponse;

/**
 * Describe data saving
 */
public class VariableLoanCalcRepository extends AbstractLoanCalcRepository {

    private int requestId;

    public VariableLoanCalcRepository(int requestId) {
        this.requestId = requestId;
    }

    public VariableLoanCalcRepository() {
        this(0);
    }

    @Override
    public IntIdLoanResponse save(AbstractLoanRequest request) {
        requestId++;
        IntIdLoanResponse response = new IntIdLoanResponse(requestId++);
        return response;
    }
}
